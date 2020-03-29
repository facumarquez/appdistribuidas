package com.turnos.app.MEDICO;

import java.util.Set;

import javax.persistence.*;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.USUARIO.Usuario;

@Entity
@Table(name="medicos")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Medico extends Usuario {
	
	private static final long serialVersionUID = -1478467835353334404L;

	@Column(nullable=false)
	private String legajo;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	    @JoinTable(name = "Medicos_Especialidades",
	            joinColumns = { @JoinColumn(name = "id_usuario") },
	            inverseJoinColumns = { @JoinColumn(name = "id_especialidad") })
	
	private Set<Especialidad> especialidades;

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)//,
            //mappedBy = "medicos")

	private Set<AgendaMedico> agendas;
	
	

	public Set<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public Set<AgendaMedico> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<AgendaMedico> agendas) {
		this.agendas = agendas;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}		
	
	
}