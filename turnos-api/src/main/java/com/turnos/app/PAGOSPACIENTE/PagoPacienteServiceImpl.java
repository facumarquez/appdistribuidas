package com.turnos.app.PAGOSPACIENTE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.PACIENTE.Paciente;

@Service
@Transactional(readOnly = false)
public class PagoPacienteServiceImpl implements PagoPacienteService {
	
	@Autowired
	PagoPacienteDAO pagoPacienteDAO;

	@Transactional(readOnly = true)
	public Optional<PagoPaciente> findByPacienteAndMesAndAnio(Paciente paciente,int mes, int anio) {
		return pagoPacienteDAO.findByPacienteAndMesAndAnio(paciente, mes, anio);
	}
}
