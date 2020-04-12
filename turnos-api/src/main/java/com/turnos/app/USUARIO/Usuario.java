package com.turnos.app.USUARIO;

import java.io.Serializable;

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
@Table(name="usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable  {
	
	
	private static final long serialVersionUID = -3359031128100969764L;

	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(nullable=false,length=15)
	private String nombre;
	
	@Column(nullable=false,length=15)
	private String apellido;
	
	@Column(nullable=false, length=20)
	private String usuario;
	
	@Column(nullable=false, length=20)
	private String password;
	
	@Column(nullable=false, length=30)
	private String mail;
	
	@Column(nullable=false, length=1)
	@NotEmpty(message = "Sexo: Campo requerido")
	private String sexo;
	
	@Column(nullable=false, length=8)
	@NotNull(message = "Fecha de nacimiento: Campo requerido")
	private String fecha_nacimiento;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
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

	

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Usuario() {
    }
	
    public Usuario(String nombre,String apellido, String usuario, String pass, String mail,String sexo, String fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = pass;
        this.mail = mail;
        this.sexo = sexo;
        this.fecha_nacimiento= fechaNac;
    }
}