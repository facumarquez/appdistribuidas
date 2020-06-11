package com.turnos.app.AGENDAPACIENTE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.turnos.app.AGENDAMEDICOTURNO.AgendaMedicoTurno;
import com.turnos.app.ESPECIALIDAD.Especialidad;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.PACIENTE.Paciente;


@Entity
@Table(name="agenda_pacientes")
public class AgendaPaciente {

	@Id
	@Column(name="id_agenda_paciente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_paciente",nullable=false)
	private Paciente paciente;
	
	
	@ManyToOne
    @JoinColumn(name = "id_turno",nullable=false)
	private AgendaMedicoTurno turno;
	

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public AgendaMedicoTurno getTurno() {
		return turno;
	}

	public void setTurno(AgendaMedicoTurno turno) {
		this.turno = turno;
	}

	@Transient
	public Especialidad getEspecialidad() {
		return this.getTurno().getAgendaMedicoHorario().getAgendaMedicoFecha().getEspecialidad();
	}
	
	@Transient
	public Medico getMedico() {
		return this.getTurno().getAgendaMedicoHorario().getAgendaMedicoFecha().getAgendaMedico().getMedico();
	}
	
	@Transient
	public String getFechaTurno() {
		return this.getTurno().getAgendaMedicoHorario().getAgendaMedicoFecha().getFecha();
	}

	@Transient
	public String getTurnoDesde() {
		return this.getTurno().getTurnoDesde();
	}
	
	@Transient
	public String getTurnoHasta() {
		return this.getTurno().getTurnoHasta();
	}
	
	@Transient
	public String getEstadoTurno() {
		return this.getTurno().getEstado().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
