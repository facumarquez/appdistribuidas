package com.turnos.app.DIA;

import javax.persistence.*;

@Entity
@Table(name="Dias")
public class Dia {
	
	@Id
	@Column(name="idDia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String dia;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}
}








