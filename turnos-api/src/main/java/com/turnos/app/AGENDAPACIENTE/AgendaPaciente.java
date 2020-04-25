package com.turnos.app.AGENDAPACIENTE;

import javax.persistence.*;

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
	@JoinColumn(name="id_especialidad",nullable=false)
	private Especialidad especialidad;
	
	
	@ManyToOne
    @JoinColumn(name = "id_medico",nullable=false)
	private Medico medico;
	
	@ManyToOne
    @JoinColumn(name = "id_turno",nullable=false)
	private AgendaMedicoTurno turno;
	

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

	public AgendaMedicoTurno getTurno() {
		return turno;
	}

	public void setTurno(AgendaMedicoTurno turno) {
		this.turno = turno;
	}

	@Transient
	public String getFechaTurno() {
		return this.getTurno().getAgendaMedicoHorario().getAgendaMedicoFecha().getFecha();
	}

	@Transient
	public String getHorarioTurno() {
		return this.getTurno().getAgendaMedicoHorario().getHoraDesde() + "-" + 
				this.getTurno().getAgendaMedicoHorario().getHoraHasta();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
}
