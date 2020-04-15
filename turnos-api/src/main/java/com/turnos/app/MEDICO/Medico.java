package com.turnos.app.MEDICO;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.USUARIO.Usuario;

@Entity
@Table(name="medicos")
@PrimaryKeyJoinColumn(name="id_usuario")
public class Medico extends Usuario {
	
	private static final long serialVersionUID = -1478467835353334404L;

	@Column(nullable=false, length=10)
	private String legajo;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	    @JoinTable(name = "medicos_especialidades",
	            joinColumns = { @JoinColumn(name = "id_usuario") },
	            inverseJoinColumns = { @JoinColumn(name = "id_especialidad") })
	
	private List<Especialidad> especialidades;

	@OneToMany(mappedBy = "medico")
	@JsonIgnoreProperties("medico")
    private List<AgendaMedico> agendas;
	
	
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	
	public List<AgendaMedico> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<AgendaMedico> agendas) {
		this.agendas = agendas;
	}
}