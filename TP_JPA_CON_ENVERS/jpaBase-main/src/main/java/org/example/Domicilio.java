package org.example;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;



@Entity
@Audited
public class Domicilio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCalle;
    private int numero;

    public Domicilio() {
    }

    public Domicilio(String nombreCalle, int numero) {

        this.nombreCalle = nombreCalle;
        this.numero = numero;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNombreCalle() {

        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {

        this.nombreCalle = nombreCalle;
    }

    public int getNumero() {

        return numero;
    }

    public void setNumero(int numero) {

        this.numero = numero;
    }



}
