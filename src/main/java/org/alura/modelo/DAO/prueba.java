package org.alura.modelo.DAO;

import org.alura.modelo.Reserva;
import org.alura.util.JPAUtils;

import javax.persistence.EntityManager;

public class prueba {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtils.getEntityManager();
       // HuespedDAO huespedDAO = new HuespedDAO(entityManager);
        ReservaDAO reservaDDAO = new ReservaDAO(entityManager);
        Reserva reserva = new Reserva("20/10/1997","20/11/1997",200.4,"Tarjeta");
        entityManager.getTransaction().begin();
        reservaDDAO.guardar(reserva);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
