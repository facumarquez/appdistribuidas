package com.turnos.app.PACIENTE;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.turnos.app.AGENDAMEDICO.AgendaMedico;
import com.turnos.app.PAGOSPACIENTE.PagoPaciente;
import com.turnos.app.USUARIO.Usuario;
import com.turnos.app.VALIDACIONES.*;

@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name="idUsuario")
public class Paciente extends Usuario {

	private static final long serialVersionUID = 5048310680793495937L;

	@Column(name="dni", nullable=false, unique=true )
	@NotEmpty(message = "Documento: Campo requerido")
	private String documento;
	
	//TODO
	/*
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "pacientes")

	private Set<AgendaPaciente> agendas;
	*/

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)//,
            //mappedBy = "pacientes")

	private Set<PagoPaciente> pagos;
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Set<PagoPaciente> getPagos() {
		return pagos;
	}

	public void setPagos(Set<PagoPaciente> pagos) {
		this.pagos = pagos;
	}

	/*
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", documento=" + documento + ", sexo=" + sexo + ", fecha_de_nacimiento=" + fecha_de_nacimiento + "]";
	}		
	*/
}
