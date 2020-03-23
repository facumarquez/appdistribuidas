package com.turnos.app.ENTITIES;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="especialidades")
public class Especialidad {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String Especialidad;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(String especialidad) {
		Especialidad = especialidad;
	}

}








