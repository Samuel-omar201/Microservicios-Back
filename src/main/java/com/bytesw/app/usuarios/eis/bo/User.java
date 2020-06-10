package com.bytesw.app.usuarios.eis.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class User implements Serializable {


	private static final long serialVersionUID = 5905201468183002401L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String nombre;
    private String apellido;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String identificacion;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    


}