package com.turnos.app.AGENDAMEDICOFECHA;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.ESPECIALIDAD.Especialidad;


@Entity
@Table(name="agenda_medico_fechas")

public class AgendaMedicoFecha {
	@Id
	@Column(name="id_agendamedico_fecha")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable=false,length=8)
	private String fecha;
	
	
	@ManyToOne
	@JoinColumn(name="id_agenda_medico",nullable=false)
	private AgendaMedico agendaMedico;
	
	
	@ManyToOne
	@JoinColumn(name="id_especialidad",nullable=false)
	private Especialidad especialidad;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public Especialidad getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public AgendaMedico getAgendaMedico() {
		return agendaMedico;
	}


	public void setAgendaMedico(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}
}
