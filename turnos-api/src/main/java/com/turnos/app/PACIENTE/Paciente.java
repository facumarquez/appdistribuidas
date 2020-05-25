package com.turnos.app.PACIENTE;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turnos.app.AGENDAPACIENTE.AgendaPaciente;
import com.turnos.app.USUARIO.Usuario;

@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name="id_usuario")
public class Paciente extends Usuario {

	private static final long serialVersionUID = 5048310680793495937L;

	@Column(name="documento", nullable=false, length=10, unique=true )
	@NotEmpty(message = "Documento: Campo requerido")
	private String documento;
	
	@Column(nullable=false)
	private Boolean cuotaAlDia;
	
	@OneToMany(mappedBy = "paciente")
	@JsonIgnore
    private List<AgendaPaciente> agendas;
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	public Boolean getCuotaAlDia() {
		return cuotaAlDia;
	}

	public void setCuotaAlDia(Boolean cuotaAlDia) {
		this.cuotaAlDia = cuotaAlDia;
	}
	
	public List<AgendaPaciente> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<AgendaPaciente> agendas) {
		this.agendas = agendas;
	}
	
	/*
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", documento=" + documento + ", sexo=" + sexo + ", fecha_de_nacimiento=" + fecha_de_nacimiento + "]";
	}		
	*/
}
