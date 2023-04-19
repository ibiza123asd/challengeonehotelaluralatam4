package org.alura.modelo;

import javax.persistence.*;

@Entity
//se pone por si se diferencia el nombre con el de la BD
@Table(name="Reserva")
public class Reserva {

    @Id
    //darle la responsabilidad a la base de datos
    //representar la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idR")
    private Integer idR;
    private String fechaEntrada;
    private String fechaSalida;
    private Double valor;
    private String formaPago;

    public Reserva() {
    }

    public Reserva(Integer idR) {
        this.idR = idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public Reserva(String fechaEntrada, String fechaSalida, Double valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Integer getIdR() {
        return idR;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
}
