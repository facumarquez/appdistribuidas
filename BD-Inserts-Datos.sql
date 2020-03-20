

--CARGO LOS DATOS DE PRUEBA
--Especialidades
INSERT INTO especialidades 
	(especialidad)
VALUES 
	('Dentista'),
	('Traumatologo'),
	('Oftalmologo'),
	('Nutricionista'),
	('Otorrinolaringolo'),
	('Cardiologo'),
	('Deportologo')

--Medicoss
INSERT INTO medicos 
	(legajo,documento,nombre,apellido)
VALUES 
	(123,33455444,'Diego','Maradona'),
	(124,39499999,'Lionel','Messi'),
	(125,31765777,'Javier','Mascherano')

--Medico_Especialidad
INSERT INTO medico_especialidad 
	(id_medico,id_especialidad)
VALUES 
	(1,1),
	(2,2),
	(3,3),
	(1,7)