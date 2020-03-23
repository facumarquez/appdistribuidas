package com.turnos.app.ENTITIES;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.turnos.app.VALIDACIONES.*;

@Entity
@Table(name="pacientes")
public class Paciente {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty(message = "Legajo: Campo requerido")
	@LegajoUnico
	private String legajo;
	
	@Column(nullable=false)
	@NotEmpty(message = "Nombre: Campo requerido")
	private String nombre;
	
	@Column(nullable=false)
	@NotEmpty(message = "Apellido: Campo requerido")
	private String apellido;
	
	@Column(nullable=false, unique=true )
	@NotEmpty(message = "Documento: Campo requerido")
	private String documento;
	
	@Column(nullable=false)
	@NotEmpty(message = "Sexo: Campo requerido")
	private String sexo;
	
	@Column(nullable=false)
	@NotNull(message = "Fecha de nacimiento: Campo requerido")
	private java.sql.Date fecha_de_nacimiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegajo() {
		return legajo;
	}
	
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public java.sql.Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(java.sql.Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}	
	
}
