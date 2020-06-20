package com.turnos.app.AGENDAMEDICOHORARIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;

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
	@Transactional(readOnly = false)
	public List<AgendaMedicoHorario> crearHorariosDeAgenda(List <AgendaMedicoHorario> horariosAgenda) {
		
		List<AgendaMedicoHorario> horariosCreados = new ArrayList<AgendaMedicoHorario>();
		
		for (AgendaMedicoHorario horario : horariosAgenda) {
			Optional <AgendaMedicoHorario> horarioEspecifico  = agendaMedicoHorarioDAO.findByHoraDesdeAndHoraHastaAndAgendaMedicoFecha
																		(horario.getHoraDesde(), horario.getHoraHasta(), horario.getAgendaMedicoFecha());
			
			if (!horarioEspecifico.isPresent()) {
				horariosCreados.add(agendaMedicoHorarioDAO.save(horario));
			}else {
				horariosCreados.add(horarioEspecifico.get());
			}
		}

		return horariosCreados;
	}

	@Override
	public Optional<AgendaMedicoHorario> buscarPorRangoHorarioYFecha(String horaDesde, String horaHasta,
			Optional<AgendaMedicoFecha> agendaMedicoFecha) {
		return agendaMedicoHorarioDAO.findByHoraDesdeAndHoraHastaAndAgendaMedicoFecha(horaDesde, horaHasta, agendaMedicoFecha.get());
	}
	
	public ResponseEntity<Void> deleteByID(Long idAgendaMedicoHorario) throws Exception {
		
		Optional<AgendaMedicoHorario> horario = agendaMedicoHorarioDAO.findById(idAgendaMedicoHorario);
		
		if (horario.isPresent()) {
			List<AgendaMedicoTurno> turnosDelHorario = horario.get().getTurnos();
			if (turnosDelHorario != null) {
				for (AgendaMedicoTurno turno : turnosDelHorario) {
					if (turno.getEstado().equals(EstadoTurno.RESERVADO)) {
						throw new Exception("No se puede eliminar el horario porque tiene turnos reservados");
					}
				}
			}
			agendaMedicoHorarioDAO.deleteById(idAgendaMedicoHorario);
		}else {
			throw new Exception("Error al obtener el horario");
		}

		return ResponseEntity.ok(null);
	}
	
	@Transactional(readOnly = false)
	public ResponseEntity<Void> deleteHorarios(List <AgendaMedicoHorario> horariosAgenda) {
		
		for (AgendaMedicoHorario horario : horariosAgenda) {
			agendaMedicoHorarioDAO.deleteById(horario.getId());
		}
		return ResponseEntity.ok(null);
	}
}
