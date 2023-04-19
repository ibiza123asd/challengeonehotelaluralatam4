package org.alura.modelo;

import javax.persistence.*;

@Entity
//se pone por si se diferencia el nombre con el de la BD
@Table(name="Huesped")
public class Huesped {
    @Id
    //darle la responsabilidad a la base de datos
    //representar la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String nombre;
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private String fecha;

    private String nacionalidad;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "idR")
    private Reserva reserva;

    public Huesped() {
    }

    public Huesped(String nombre, String apellido, String fecha, String nacionalidad, String telefono, Reserva reserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reserva = reserva;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Huesped(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha='" + fecha + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", reserva=" + reserva +
                '}';
    }
}
