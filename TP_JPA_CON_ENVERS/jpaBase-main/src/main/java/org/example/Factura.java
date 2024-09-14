package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
@Audited
@Entity
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private int numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Set<DetalleFactura> facturas = new HashSet<>();

    // Constructor por defecto
    public Factura() {
        this.facturas = new HashSet<>(); // Inicialización del conjunto
    }

    // Constructor con parámetros
    public Factura(String fecha, int numero) {
        this.fecha = fecha;
        this.numero = numero;
        this.facturas = new HashSet<>(); // Inicialización del conjunto
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<DetalleFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Set<DetalleFactura> facturas) {
        this.facturas = facturas;
    }
}
