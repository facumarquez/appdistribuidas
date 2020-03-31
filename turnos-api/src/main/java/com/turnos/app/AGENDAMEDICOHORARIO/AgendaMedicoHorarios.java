package com.turnos.app.AGENDAMEDICOHORARIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;


@Entity
@Table(name="agenda_medico_horarios")

public class AgendaMedicoHorarios {
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
}
