package com.turnos.app.COLAESPERAPACIENTE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.PACIENTE.Paciente;

@Service
@Transactional(readOnly = false)
public class ColaEsperaPacienteServiceImpl implements ColaEsperaPacienteService {
	
	@Autowired
	ColaEsperaPacienteDAO colaEsperaPacienteDAO;


	@Transactional(readOnly = true)
	public Optional<ColaEsperaPaciente> findByPacienteAndEspecialidad(Paciente paciente,Especialidad especialidad) {
		return colaEsperaPacienteDAO.findByPacienteAndEspecialidad(paciente, especialidad);
	}


	@Override
	public ColaEsperaPaciente agregarPacienteAColaDeEspera(ColaEsperaPaciente colaEsperaPaciente) {
		return colaEsperaPacienteDAO.save(colaEsperaPaciente);
	}
}
