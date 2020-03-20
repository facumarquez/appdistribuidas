package com.turnos.app.ENTITIES;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="medicos")
public class Medico {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	@Column
		private String legajo;
	@Column
		private String nombre;
	@Column
		private String apellido;
	@Column
		private String documento;
	
	@ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	    @JoinTable(name = "medico_especialidad",
	            joinColumns = { @JoinColumn(name = "id_medico") },
	            inverseJoinColumns = { @JoinColumn(name = "id_especialidad") })
	
	private Set<Especialidad> especialidades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Set<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}	
	
	
}



