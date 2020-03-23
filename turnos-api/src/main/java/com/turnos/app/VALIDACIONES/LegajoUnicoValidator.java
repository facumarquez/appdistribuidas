package com.turnos.app.VALIDACIONES;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turnos.app.DAO.PacientesDAO;
import com.turnos.app.DAO.PacientesDAOImpl;

@Component
public class LegajoUnicoValidator implements ConstraintValidator<LegajoUnico, String>{

	@Autowired
	private PacientesDAO pacientesDAO;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return true;
		//return pacientesDAO.findByLegajo(value).isEmpty();
	}
}