package com.turnos.app.USUARIO;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable  {
	
	private static final long serialVersionUID = -3359031128100969764L;

	@Id
	@Column(name="idUsuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Column(nullable=false)
	private String usuario;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String mail;
	
	@Column(nullable=false)
	@NotEmpty(message = "Sexo: Campo requerido")
	private String sexo;
	
	@Column(nullable=false)
	@NotNull(message = "Fecha de nacimiento: Campo requerido")
	private java.sql.Date fecha_de_nacimiento;
	
	

	public Long getId() {
		return idUsuario;
	}

	public void setId(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
	
	
	public Usuario() {
    }
	
    public Usuario(String nombre,String apellido, String usuario, String pass, String mail,String sexo, Date fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = pass;
        this.mail = mail;
        this.sexo = sexo;
        this.fecha_de_nacimiento= fechaNac;
    }
}