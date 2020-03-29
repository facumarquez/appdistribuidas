package com.turnos.app.AGENDAMEDICOFECHA;
import java.util.Set;

import javax.persistence.*;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.AGENDAMEDICOHORARIO.AgendaMedicoHorarios;
import com.turnos.app.ESPECIALIDAD.Especialidad;


@Entity
@Table(name="AgendaMedico_Fechas")

public class AgendaMedicoFecha {
	@Id
	@Column(name="idAgendaMedico_Fecha")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String fecha;
	
	
	@ManyToOne
	@JoinColumn(name="idAgendaMedico")
	private AgendaMedico agendaMedico;
	
	
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	private Especialidad especialidad;

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)//,
            //mappedBy = "AgendaMedico_Fecha")

	private Set<AgendaMedicoHorarios> horariosAgenda;
	

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
	
	public Set<AgendaMedicoHorarios> getHorarios() {
		return horariosAgenda;
	}


	public void setHorarios(Set<AgendaMedicoHorarios> horarios) {
		this.horariosAgenda = horarios;
	}
}
