package com.turnos.app.COLAESPERAPACIENTE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.PACIENTE.Paciente;


@Entity
@Table(name="cola_espera_pacientes")
public class ColaEsperaPaciente {

	@Id
	@Column(name="id_cola_espera_paciente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_paciente",nullable=false)
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="id_especialidad",nullable=false)
	private Especialidad especialidad;
	
	
	@Column(nullable=false,length=8)
	private String fecha;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public Especialidad getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
