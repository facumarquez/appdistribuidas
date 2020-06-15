package com.turnos.app.AGENDAPACIENTE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurnoDAO;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;

@Service
@Transactional(readOnly = false)
public class AgendaPacienteServiceImpl implements AgendaPacienteService {
	
	@Autowired
	AgendaPacienteDAO agendaPacienteDAO;
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	@Transactional(readOnly = false)
	public AgendaPaciente guardarAgenda(AgendaPaciente agenda) {
		
		AgendaPaciente pacienteNuevo = agendaPacienteDAO.save(agenda);
		AgendaMedicoTurno turno = pacienteNuevo.getTurno();
		turno.setEstado(EstadoTurno.RESERVADO);
		agendaMedicoTurnoDAO.save(turno);
		pacienteNuevo.setTurno(turno);
		return pacienteNuevo;
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
