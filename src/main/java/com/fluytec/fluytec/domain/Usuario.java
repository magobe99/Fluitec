package com.fluytec.fluytec.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private Integer id_usuarios;
    private String nombre;

    
    private String apellido;
    private Integer identificacion;
    private Integer edad;
    private Integer telefono;
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)*/
    //private String fec_nacimiento;
    private String correo;
    @Column(name = "contraseña")
    private String clave;

    @ManyToMany()
    @JoinTable(name = "roles_usuarios", joinColumns = @JoinColumn(name = "usuarios_id_usuarios"),
            inverseJoinColumns = @JoinColumn(name = "roles_id_rol"))//@JoinTable is used to map Join table in database
    private List<Rol> roles;
    
    
   /* public Usuario(Integer id_usuarios, String nombre, String apellido, Integer identificacion, Integer edad, Integer telefono, String correo, String clave, List<Rol> roles) {
        this.id_usuarios = id_usuarios;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.roles = roles;
    }

    
    public Integer getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Integer id_usuarios) {
        this.id_usuarios = id_usuarios;
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
    
    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }
    
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

     public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
*/
    
}

