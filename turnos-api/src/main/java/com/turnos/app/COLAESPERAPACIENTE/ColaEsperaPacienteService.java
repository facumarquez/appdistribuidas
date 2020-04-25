package com.turnos.app.COLAESPERAPACIENTE;

import java.util.Optional;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.PACIENTE.Paciente;

public interface ColaEsperaPacienteService {
	
	public ColaEsperaPaciente agregarPacienteAColaDeEspera(ColaEsperaPaciente colaEsperaPaciente);
	
	public Optional<ColaEsperaPaciente> findByPacienteAndEspecialidad(Paciente paciente,Especialidad especialidad);

}
