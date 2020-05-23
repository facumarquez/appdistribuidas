package com.turnos.app.AGENDAMEDICOHORARIO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoHorarioServiceImpl implements AgendaMedicoHorarioService{
	
	@Autowired
	AgendaMedicoHorarioDAO agendaMedicoHorarioDAO;
	
	
	@Transactional(readOnly = true)
	public List<AgendaMedicoHorario> findAll() {
		return agendaMedicoHorarioDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<AgendaMedicoHorario> findById(Long id) {
		return agendaMedicoHorarioDAO.findById(id);
	}
	
	@Override
	public AgendaMedicoHorario guardarHorarioDeAgenda(AgendaMedicoHorario horarioAgenda) {
		return agendaMedicoHorarioDAO.save(horarioAgenda);
	}

	@Override
	public Optional<AgendaMedicoHorario> buscarPorRangoHorarioYFecha(String horaDesde, String horaHasta,
			Optional<AgendaMedicoFecha> agendaMedicoFecha) {
		return agendaMedicoHorarioDAO.findByHoraDesdeAndHoraHastaAndAgendaMedicoFecha(horaDesde, horaHasta, agendaMedicoFecha);
	}
	
	public ResponseEntity<Void> deleteByID(Long idAgendaMedicoHorario) {
		agendaMedicoHorarioDAO.deleteById(idAgendaMedicoHorario);
		return ResponseEntity.ok(null);
	}
}
