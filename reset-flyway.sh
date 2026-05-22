#!/bin/bash

# Script para resetar o Flyway e as tabelas do banco Oracle (Macos/Linux)
# Use este script para voltar ao estado inicial do Flyway
echo "Resetando Flyway..."
echo ""

# Configurações
DB_URL="jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL"
# Alterar para seu usuario e senha do Oracle
DB_USER="RM562016"
DB_PASSWORD="010696"

# Comando SQL para resetar
RESET_SQL="
BEGIN
  FOR c IN (SELECT table_name FROM user_tables WHERE table_name LIKE 'TB_%')
  LOOP
    EXECUTE IMMEDIATE 'DROP TABLE ' || c.table_name || ' CASCADE CONSTRAINTS';
  END LOOP;
END;
/

BEGIN
  BEGIN
    DROP TABLE flyway_schema_history;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
  END;
END;
/

COMMIT;
"

# Executar com sqlplus (se disponível)
if command -v sqlplus &> /dev/null; then
    echo "$RESET_SQL" | sqlplus -S "$DB_USER/$DB_PASSWORD@$DB_URL"
    echo ""
    echo "Flyway resetado com sucesso!"
    echo ""
    echo "Próximos passos:"
    echo "1. Execute: docker-compose up --build"
    echo "2. As migrations serão executadas novamente do zero"
else
    echo "sqlplus não encontrado no sistema"
    echo ""
    echo "Alternativa: Execute o script SQL manualmente"
    echo "- Abra SQL Developer ou similar"
    echo "- Conecte ao banco Oracle"
    echo "- Execute o arquivo: src/main/resources/db/migration/RESET_FLYWAY.sql"
fi

