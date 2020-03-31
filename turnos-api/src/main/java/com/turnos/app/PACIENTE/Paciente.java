package com.turnos.app.PACIENTE;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.turnos.app.USUARIO.Usuario;

@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name="id_usuario")
public class Paciente extends Usuario {

	private static final long serialVersionUID = 5048310680793495937L;

	@Column(name="documento", nullable=false, length=10, unique=true )
	@NotEmpty(message = "Documento: Campo requerido")
	private String documento;
	
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	/*
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", documento=" + documento + ", sexo=" + sexo + ", fecha_de_nacimiento=" + fecha_de_nacimiento + "]";
	}		
	*/
}
