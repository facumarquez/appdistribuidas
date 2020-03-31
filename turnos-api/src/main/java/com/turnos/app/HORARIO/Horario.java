package com.turnos.app.HORARIO;
import javax.persistence.*;


@Entity
@Table(name="horarios")
public class Horario {

	@Id
	@Column(name="id_horario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=10)
	private String horarioDesde;
	
	@Column(nullable=false,length=10)
	private String horarioHasta;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHorarioDesde() {
		return horarioDesde;
	}

	public void setHorarioDesde(String horarioDesde) {
		this.horarioDesde = horarioDesde;
	}

	public String getHorarioHasta() {
		return horarioHasta;
	}

	public void setHorarioHasta(String horarioHasta) {
		this.horarioHasta = horarioHasta;
	}
	

}
