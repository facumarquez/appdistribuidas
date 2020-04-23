package com.turnos.app.AGENDAMEDICOTURNO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoTurnoServiceImpl implements AgendaMedicoTurnoService{
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	
	@Transactional(readOnly = true)
	public List<AgendaMedicoTurno> findAll() {
		return agendaMedicoTurnoDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaMedicoTurno> findById(Long id) {
		return agendaMedicoTurnoDAO.findById(id);
	}
	
	@Override
	public AgendaMedicoTurno crearTurnosDeAgenda(AgendaMedicoTurno turnosAgenda) {
		return agendaMedicoTurnoDAO.save(turnosAgenda);
	}

	@Override
	public Optional<AgendaMedicoTurno> buscarPorRangoDeTurnoYHorario(String turnoDesde, String turnoHasta,
			Optional<AgendaMedicoHorario> agendaMedicoHorario) {
		return agendaMedicoTurnoDAO.findByTurnoDesdeAndTurnoHastaAndAgendaMedicoHorario(turnoDesde, turnoHasta, agendaMedicoHorario);
	}
}
