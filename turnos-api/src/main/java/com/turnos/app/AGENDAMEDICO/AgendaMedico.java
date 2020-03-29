package com.turnos.app.AGENDAMEDICO;
import java.util.Set;

import javax.persistence.*;

import com.turnos.app.AGENDAMEDICOFECHA.AgendaMedicoFecha;
import com.turnos.app.MEDICO.Medico;
import com.turnos.app.USUARIO.Usuario;


@Entity
@Table(name="AgendaMedicos")
public class AgendaMedico {
	@Id
	@Column(name="idAgendaMedico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Medico medico;
	
	@Column
	private int mes;
	
	@Column
	private int anio;


	@OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.LAZY,
	            mappedBy = "AgendaMedicos")

	private Set<AgendaMedicoFecha> fechasAgenda;
	
	
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
	
	public Set<AgendaMedicoFecha> getFechasAgenda() {
		return fechasAgenda;
	}

	public void setFechasAgenda(Set<AgendaMedicoFecha> fechasAgenda) {
		this.fechasAgenda = fechasAgenda;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
}
