package com.turnos.app.PAGOSPACIENTE;

import java.util.Optional;

import com.turnos.app.PACIENTE.Paciente;

public interface PagoPacienteService {
	
	public Optional<PagoPaciente> findByPacienteAndMesAndAnio(Paciente paciente,int mes, int anio);

}
