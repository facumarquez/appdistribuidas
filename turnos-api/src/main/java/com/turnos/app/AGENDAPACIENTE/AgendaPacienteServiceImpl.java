package com.turnos.app.AGENDAPACIENTE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurnoDAO;
import com.turnos.app.AGENDAMEDICOTURNO.EstadoTurno;
import com.turnos.app.HELPERS.TurnoHelper;

@Service
@Transactional(readOnly = false)
public class AgendaPacienteServiceImpl implements AgendaPacienteService {
	
	@Autowired
	AgendaPacienteDAO agendaPacienteDAO;
	
	@Autowired
	AgendaMedicoTurnoDAO agendaMedicoTurnoDAO;
	
	@Transactional(readOnly = false)
	public AgendaPaciente guardarAgenda(AgendaPaciente agenda) {

		return agendaPacienteDAO.save(agenda);
	}
	
	@Transactional(readOnly = false)
	public AgendaPaciente confirmarTurnoAgenda(AgendaPaciente agenda) throws Exception {
		
		if(TurnoHelper.sePuedeConfirmarElTurno(agenda.getFechaTurno(), agenda.getTurnoDesde())) {
			return agendaPacienteDAO.save(agenda);
		}else {
			throw new Exception("El turno puede ser confirmado desde 12 hasta 1 hora antes");
		}
	}
	
	@Transactional(readOnly = false)
	public AgendaPaciente guardarAgenda_ReservarTurno(AgendaPaciente agenda) {
		
		AgendaPaciente agendaPacienteNuevo = agendaPacienteDAO.save(agenda);
		AgendaMedicoTurno turno = agendaPacienteNuevo.getTurno();
		turno.setEstado(EstadoTurno.RESERVADO);
		agendaMedicoTurnoDAO.save(turno);
		agendaPacienteNuevo.setTurno(turno);
		return agendaPacienteNuevo;
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
