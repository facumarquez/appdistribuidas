package com.turnos.app.AGENDAPACIENTE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.PACIENTE.Paciente;

@Service
@Transactional(readOnly = false)
public class AgendaPacienteServiceImpl implements AgendaPacienteService {
	
	@Autowired
	AgendaPacienteDAO agendaPacienteDAO;
	
	
	public AgendaPaciente crearAgenda(AgendaPaciente agenda) {
		return agendaPacienteDAO.save(agenda);
	}

	@Override
	public Optional<AgendaPaciente> findByPacienteAndMedicoAndEspecialidad(Optional<Paciente> paciente, 
																	Optional<Medico> medico, Optional<Especialidad> especialidad) {
		
		return agendaPacienteDAO.findByPacienteAndMedicoAndEspecialidad(paciente, medico, especialidad);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaPaciente> findAll() {
		return agendaPacienteDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaPaciente> findById(Long id) {
		return agendaPacienteDAO.findById(id);
	}
}
