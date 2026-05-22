-- Script para resetar o Flyway e todas as tabelas ao estado inicial
-- Executar este script quando quiser começar do zero

-- Desabilitar constraints para poder droppar tabelas
BEGIN
  FOR c IN (SELECT table_name FROM user_tables WHERE table_name LIKE 'TB_%')
  LOOP
    EXECUTE IMMEDIATE 'DROP TABLE ' || c.table_name || ' CASCADE CONSTRAINTS';
  END LOOP;
END;
/

-- Limpar histórico do Flyway
BEGIN
  BEGIN
    DROP TABLE flyway_schema_history;
  EXCEPTION
    WHEN OTHERS THEN
      NULL;
  END;
END;
/

-- Confirmar operações
COMMIT;

-- Mensagem de confirmação
SELECT 'Flyway resetado com sucesso! Todas as tabelas foram removidas.' AS mensagem FROM dual;

