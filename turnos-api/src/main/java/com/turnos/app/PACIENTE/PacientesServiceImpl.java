package com.turnos.app.PACIENTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PacientesServiceImpl implements PacientesService {
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Autowired
	PacientesDAO pacientesDAO;
	
    //@SuppressWarnings("unchecked")
	public List<Paciente> findByLegajo(String legajo) {
    	return pacientesDAO.findByLegajo(legajo);
    }    

	public Paciente save(@Valid Paciente paciente) {
		return pacientesDAO.save(paciente);
	}

	public Paciente findOneByLegajo(String value) {
		// TODO Auto-generated method stub
		return null;
		//return pacientesDAO.findOneByLegajo(value);
	}

	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		return pacientesDAO.findAll();
	}

	public Optional<Paciente> findById(Long id) {
		// TODO Auto-generated method stub
		return pacientesDAO.findById(id);
	}
}
