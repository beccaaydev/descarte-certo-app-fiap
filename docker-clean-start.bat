@echo off

echo.
echo Limpando Docker Compose...
docker-compose down --rmi local -v

echo.
echo Iniciando Docker Compose...
docker-compose up --build

pause

