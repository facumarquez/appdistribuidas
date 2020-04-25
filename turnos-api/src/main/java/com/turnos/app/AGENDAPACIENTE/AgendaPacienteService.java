package com.turnos.app.AGENDAPACIENTE;

import java.util.Optional;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.PACIENTE.Paciente;

public interface AgendaPacienteService {
	
	
	public AgendaPaciente crearAgenda(AgendaPaciente agenda);
	
	public Optional<AgendaPaciente> findByPacienteAndMedicoAndEspecialidad(Optional<Paciente> paciente, 
														Optional<Medico> medico, Optional<Especialidad> especialidad);
	
}
