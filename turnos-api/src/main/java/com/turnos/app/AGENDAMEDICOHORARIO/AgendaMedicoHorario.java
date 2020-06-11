package com.turnos.app.AGENDAMEDICOHORARIO;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;


@Entity
@Table(name="agenda_medico_horarios")

public class AgendaMedicoHorario {
	@Id
	@Column(name="id_agenda_medico_horario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=8)
	private String horaDesde;
	
	@Column(nullable=false,length=8)
	private String horaHasta;
	
	
	@ManyToOne
	@JoinColumn(name="id_agenda_medico_fecha",nullable=false)
	private AgendaMedicoFecha agendaMedicoFecha;

	@OneToMany(mappedBy = "agendaMedicoHorario")
	@JsonIgnore
    private List<AgendaMedicoTurno> turnos;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getHoraDesde() {
		return horaDesde;
	}


	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}


	public String getHoraHasta() {
		return horaHasta;
	}


	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}


	public AgendaMedicoFecha getAgendaMedicoFecha() {
		return agendaMedicoFecha;
	}


	public void setAgendaMedicoFecha(AgendaMedicoFecha agendaMedicoFecha) {
		this.agendaMedicoFecha = agendaMedicoFecha;
	}
	
	public List<AgendaMedicoTurno> getTurnos() {
		return turnos;
	}


	public void setTurnos(List<AgendaMedicoTurno> turnos) {
		this.turnos = turnos;
	}
}
