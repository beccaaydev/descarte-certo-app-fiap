@echo off
REM Script para resetar o Flyway e as tabelas do banco Oracle (Windows)
REM Use este script para voltar ao estado inicial do Flyway

echo.
echo Resetando Flyway...
echo.

REM Configurações
set DB_URL=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
REM Alterar para seu usuario e senha do Oracle
set DB_USER=RM562016
set DB_PASSWORD=010696

echo Instrucoes:
echo 1. Abra SQL Developer ou SQL*Plus
echo 2. Conecte ao banco de dados Oracle com:
echo    User: %DB_USER%
echo    Password: %DB_PASSWORD%
echo    Connection String: %DB_URL%
echo.
echo 3. Execute o script SQL em:
echo    src\main\resources\db\migration\RESET_FLYWAY.sql
echo.
echo 4. Apos a execucao, rode novamente:
echo    docker-compose up --build
echo.
echo Isso recriaras todas as tabelas do zero!
echo.
pause

