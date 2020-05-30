package com.turnos.app.AGENDAMEDICOFECHA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;

@Service
@Transactional(readOnly = false)
public class AgendaMedicoFechaServiceImpl implements AgendaMedicoFechaService{
	
	@Autowired
	AgendaMedicoFechaDAO agendaMedicoFechaDAO;
	
	
	@Transactional(readOnly = true)
	public Optional<AgendaMedicoFecha> findById(Long id) {
		return agendaMedicoFechaDAO.findById(id);
	}
	
	@Override
	public AgendaMedicoFecha crearFechasDeAgenda(AgendaMedicoFecha fechasAgenda) {
		return agendaMedicoFechaDAO.save(fechasAgenda);
	}

	@Override
	public List<AgendaMedicoFecha> buscarPorEspecialidad_Medico_Fecha_Horario(String fecha,
			Optional<AgendaMedico> agendaMedico, Optional<Especialidad> especialidad) {
		return agendaMedicoFechaDAO.findByFechaAndAgendaMedicoAndEspecialidad(fecha, agendaMedico, especialidad);
	}
}
