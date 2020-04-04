package com.turnos.app.AGENDAMEDICO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.MEDICO.Medico;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoServiceImpl implements AgendaMedicoService {
	
	@Autowired
	AgendaMedicoDAO agendaMedicoDAO;
	
	
	public AgendaMedico crearAgenda(AgendaMedico agenda) {
		return agendaMedicoDAO.save(agenda);
	}

	@Override
	public Optional<AgendaMedico> findByMedicoYPeriodo(Optional<Medico> medico, int mes, int anio) {
		return agendaMedicoDAO.findByMedicoAndMesAndAnio(medico,mes,anio);
	}
	
	@Transactional(readOnly = true)
	public List<AgendaMedico> findAll() {
		return agendaMedicoDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaMedico> findById(Long id) {
		return agendaMedicoDAO.findById(id);
	}
	
	public ResponseEntity<Void> borrarAgendaPorID(Long agendaMedicoID) {
		agendaMedicoDAO.deleteById(agendaMedicoID);
		return ResponseEntity.ok(null);
	}
}
