package org.alura.modelo.DAO;

import org.alura.modelo.Huesped;
import org.alura.modelo.Reserva;

import javax.persistence.EntityManager;
import java.util.List;

public class HuespedDAO {
    private EntityManager entityManager;

    public HuespedDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void guardar(Huesped huesped) {

        this.entityManager.persist(huesped);
    }

    public Huesped consultarPorId(Integer id) {
        return this.entityManager.find(Huesped.class, id);
    }

    public List<Huesped> listaHuespedes() {
        String jpql = "SELECT h FROM Huesped AS h";
        return this.entityManager.createQuery(jpql, Huesped.class).getResultList();
    }

    public List<Huesped> consultarPorApellido(String apellido) {
        String jpql = "SELECT h FROM Huesped AS h WHERE h.apellido=:apellido";
        return this.entityManager.createQuery(jpql, Huesped.class).setParameter("apellido",apellido).getResultList();
    }

    public void eliminar(int id) {
        //con esto garantizamos que la entidad se encuentre en managed
        Huesped huesped = null;
        huesped = this.entityManager.merge(new Huesped(id));
        this.entityManager.remove(huesped);
    }

    public void actualizar(Huesped huesped) {
        this.entityManager.merge(huesped);
    }

}
