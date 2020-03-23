
use turnos
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

--Pacientes
INSERT INTO pacientes 
	(nombre,apellido,legajo,documento,fecha_de_nacimiento,sexo)
VALUES 
	('Laura','Lopez',1,1,'1988-08-04','Femenino'),
	('Marcelo','Ferreiro',12899,29233222,'1980-12-04','Masculino'),
	('Eduardo','Pirez',92344,23233222,'1978-01-04','Masculino')

--Medicos
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

--Turnos
INSERT INTO turnos 
	(estado, fecha_inicio, fecha_fin, id_paciente,id_medico)
VALUES 
	('Disponible','2020-03-01','2020-03-01',null,1),
	('Reservado','2020-03-01','2020-03-01',1,1),
	('Reservado','2020-03-01','2020-03-01',2,1),
	('Reservado','2020-03-02','2020-03-02',2,2),
	('Reservado','2020-03-01','2020-03-01',3,1)

select * from turnos
select * from pacientes

SELECT * FROM pacientes