package com.turnos.app.MEDICO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turnos.app.USUARIO.Usuario;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class MedicosServiceImpl implements MedicosService {
	
	@Autowired
	MedicosDAO medicosDAO;
	
    @Transactional(readOnly = true)
	public List<Medico> findByLegajo(String legajo) {
    	return medicosDAO.findByLegajo(legajo);
    }    

	public Medico save(Medico medico) {
			return medicosDAO.save(medico);
	}
	
	@Transactional(readOnly = true)
	public Medico findOneByLegajo(String value) {
		return medicosDAO.findOneByLegajo(value);
	}
	
	@Transactional(readOnly = true)
	public List<Medico> findAll() {
		return medicosDAO.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Medico> findById(Long id) {
		return medicosDAO.findById(id);
	}
	
	public ResponseEntity<Void> deleteByID(Long medicoID) {
		medicosDAO.deleteById(medicoID);
		return ResponseEntity.ok(null);
	}
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByUserAndPass(String user, String pass) {
		return medicosDAO.findByUsuarioAndPassword(user, pass);
	}
	
	@Transactional(readOnly = true)
	public Optional<Medico> findByUsuario(String user) {
		return medicosDAO.findByUsuario(user);
	}
}
