package com.turnos.app.HORARIOS;
import javax.persistence.*;


@Entity
@Table(name="Horarios")
public class Horario {

	@Id
	@Column(name="idHorario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String HorarioDesde;
	
	@Column
	private String HorarioHasta;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHorarioDesde() {
		return HorarioDesde;
	}

	public void setHorarioDesde(String horarioDesde) {
		HorarioDesde = horarioDesde;
	}

	public String getHorarioHasta() {
		return HorarioHasta;
	}

	public void setHorarioHasta(String horarioHasta) {
		HorarioHasta = horarioHasta;
	}
}
