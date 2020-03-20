--BORRO LA BASE SI EXISTE
USE [master] 
if exists(select * from sys.sysdatabases where name ='turnos') DROP DATABASE turnos;
GO

--CREO LA BASE
USE [master] 
CREATE DATABASE turnos;
GO

--CREO EL LOGIN DEL USUARIO EN EL MOTOR
USE [master] 
CREATE LOGIN turnosUser WITH PASSWORD=N'turnosUser'
CREATE USER turnosUser FOR LOGIN turnosUser
GO
-- CREO EL USUARIO EN LA BASE turnos con el login del paso anterior
USE turnos
CREATE USER turnosUser FOR LOGIN turnosUser
GO

-- LE DOY PERMISOS DE DB_OWNER a la base turnos
USE turnos 
ALTER ROLE [db_owner] ADD MEMBER turnosUser
