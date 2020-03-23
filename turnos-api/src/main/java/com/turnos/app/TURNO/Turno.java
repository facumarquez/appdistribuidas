package com.turnos.app.TURNO;

import javax.persistence.*;

import com.turnos.app.MEDICO.Medico;
import com.turnos.app.PACIENTE.Paciente;


@Entity
@Table(name="turnos")
public class Turno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_medico",nullable=false)
	private Medico medico;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente",nullable=true)	
	private Paciente id_paciente;
	
	@Column(nullable=false)
	private java.sql.Timestamp fecha_inicio;
	
	@Column(nullable=false)
	private java.sql.Timestamp fecha_fin;
	
	@Column(nullable=false)
	private String estado;

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

	public Paciente getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(Paciente id_paciente) {
		this.id_paciente = id_paciente;
	}

	public java.sql.Timestamp getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(java.sql.Timestamp fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public java.sql.Timestamp getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(java.sql.Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
