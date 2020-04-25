package com.turnos.app.AGENDAMEDICOTURNO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorario;


@Entity
@Table(name="agenda_medico_turnos")

public class AgendaMedicoTurno {
	@Id
	@Column(name="id_agenda_medico_turno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=8)
	private String turnoDesde;
	
	@Column(nullable=false,length=8)
	private String turnoHasta;
	
	@Column(nullable=false)
	private Boolean disponible;
	
	@Column(nullable=false)
	private Boolean confirmado;
	
	@ManyToOne
	@JoinColumn(name="id_agenda_medico_horario",nullable=false)
	private AgendaMedicoHorario agendaMedicoHorario;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTurnoDesde() {
		return turnoDesde;
	}


	public void setTurnoDesde(String turnoDesde) {
		this.turnoDesde = turnoDesde;
	}


	public String getTurnoHasta() {
		return turnoHasta;
	}


	public void setTurnoHasta(String turnoHasta) {
		this.turnoHasta = turnoHasta;
	}


	public AgendaMedicoHorario getAgendaMedicoHorario() {
		return agendaMedicoHorario;
	}


	public void setAgendaMedicoHorario(AgendaMedicoHorario agendaMedicoHorario) {
		this.agendaMedicoHorario = agendaMedicoHorario;
	}
	
	public Boolean getDisponible() {
		return disponible;
	}


	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}


	public Boolean getConfirmado() {
		return confirmado;
	}


	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
}
