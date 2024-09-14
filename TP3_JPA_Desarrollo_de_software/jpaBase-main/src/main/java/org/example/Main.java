
package org.example;

import lombok.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ToString
@Setter
@Getter
@Builder
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

        Factura factura1 = new Factura();
        factura1.setNumero(12);
        factura1.setFecha("04/09/2024");
        Domicilio domicilio = new Domicilio("P.Olguin", 1432);
        Cliente cliente = new Cliente("Emi","Ledesma",48828);

        cliente.setDomicilio(domicilio);
        factura1.setCliente(cliente);

        Categoria perecederos = new Categoria("Perecederos");

        Categoria lacteos = new Categoria("Lacteos");

        Categoria limpieza = new Categoria("Limpieza");

        Articulo articulo1 = new Articulo(10, "Yogurt sabor Vainilla", 200);

        Articulo articulo2 = new Articulo(15,"Detergente magistral", 80);

        articulo1.getCategorias().add(perecederos);
        articulo1.getCategorias().add(lacteos);
        lacteos.getArticulos().add(articulo1);
        perecederos.getArticulos().add(articulo1);

        articulo2.getCategorias().add(limpieza);
        limpieza.getArticulos().add(articulo2);

        DetalleFactura detalleFactura1 = new DetalleFactura();

        detalleFactura1.setArticulo(articulo1);
        detalleFactura1.setCantidad(2);
        detalleFactura1.setSubtotal(400);

        factura1.getFacturas().add(detalleFactura1);


        DetalleFactura detalleFactura2 = new DetalleFactura();

        detalleFactura2.setArticulo(articulo2);
        detalleFactura2.setCantidad(2);
        detalleFactura2.setSubtotal(160);

        factura1.getFacturas().add(detalleFactura2);



            entityManager.persist(factura1);


            entityManager.flush();

            entityManager.getTransaction().commit();


        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Error, no se pudo generar la factura");}
        entityManager.close();
        entityManagerFactory.close();
    }
}
