
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
            Cliente cliente = new Cliente("Emi", "Ledesma", 48828);

            cliente.setDomicilio(domicilio);
            factura1.setCliente(cliente);

            Categoria perecederos = new Categoria("Perecederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");
            Categoria helados = new Categoria("Helados");
            Categoria gaseosa = new Categoria("Gaseosas");

            Articulo articulo1 = new Articulo(10, "Yogurt sabor Vainilla", 200);
            Articulo articulo2 = new Articulo(15, "Detergente magistral", 80);
            Articulo articulo3 = new Articulo(20, "Helado de Pistacho", 300);
            Articulo articulo4 = new Articulo(25, "Coca-Cola", 150);

            articulo1.getCategorias().add(perecederos);
            articulo1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(articulo1);
            perecederos.getArticulos().add(articulo1);

            articulo2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo2);

            articulo3.getCategorias().add(perecederos);
            articulo3.getCategorias().add(lacteos);
            articulo3.getCategorias().add(helados);
            lacteos.getArticulos().add(articulo3);
            perecederos.getArticulos().add(articulo3);
            helados.getArticulos().add(articulo3);

            articulo4.getCategorias().add(gaseosa);
            gaseosa.getArticulos().add(articulo4);



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

            DetalleFactura detalleFactura3 = new DetalleFactura();
            detalleFactura3.setArticulo(articulo3);
            detalleFactura3.setCantidad(1);
            detalleFactura3.setSubtotal(300);
            factura1.getFacturas().add(detalleFactura3);

            DetalleFactura detalleFactura4 = new DetalleFactura();
            detalleFactura4.setArticulo(articulo4);
            detalleFactura4.setCantidad(3);
            detalleFactura4.setSubtotal(450);
            factura1.getFacturas().add(detalleFactura4);



            entityManager.persist(factura1);
            entityManager.flush();
            entityManager.getTransaction().commit();

            System.out.println("Factura persistida correctamente");



            Factura factura2 = entityManager.find(Factura.class, 1L);
            factura2.setNumero(85);
            entityManager.merge(factura2);
            factura2.getFacturas().add(detalleFactura3);




            System.out.println("Factura actualizada correctamente");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Error, no se pudo generar la factura");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
