package com.turnos.app.DAO;

import com.turnos.app.ENTITIES.Paciente;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class PacientesDAOImpl implements PacientesDAOCustom{
	
	@PersistenceContext
    EntityManager entityManager;
	
    @Override
    public List<Paciente> findByLegajo(String legajo) {
        Query query = entityManager.createNativeQuery("SELECT p.* FROM Pacientes p", Paciente.class);
        return query.getResultList(); //arreglado, agregar el parametro y probar la validacion
    }
}
