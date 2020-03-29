package com.turnos.app.ESPECIALIDAD;

import javax.persistence.*;

@Entity
@Table(name="especialidades")
public class Especialidad {
	
	@Id
	@Column(name="idEspecialidad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String especialidad;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	

}








