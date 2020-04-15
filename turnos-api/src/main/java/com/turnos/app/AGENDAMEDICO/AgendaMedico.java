package com.turnos.app.AGENDAMEDICO;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.MEDICO.Medico;


@Entity
@Table(name="agenda_medicos")
public class AgendaMedico {
	@Id
	@Column(name="id_agenda_medico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario",nullable=false)
	private Medico medico;
	
	
	@Column(nullable=false)
	private int mes;
	
	@Column(nullable=false)
	private int anio;

	@OneToMany(mappedBy = "agendaMedico")
	@JsonIgnoreProperties("agendaMedico")
    private List<AgendaMedicoFecha> fechas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public List<AgendaMedicoFecha> getFechas() {
		return fechas;
	}

	public void setFechas(List<AgendaMedicoFecha> fechas) {
		this.fechas = fechas;
	}

	public AgendaMedico(Medico medico, int mes, int anio) {
		super();
		this.medico = medico;
		this.mes = mes;
		this.anio = anio;
	}

	public AgendaMedico() {
		super();
		// TODO Auto-generated constructor stub
	}
}
