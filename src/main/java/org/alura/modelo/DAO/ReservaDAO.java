package org.alura.modelo.DAO;

import org.alura.modelo.Huesped;
import org.alura.modelo.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.EntityManager;
import java.util.List;

public class ReservaDAO {

    private EntityManager entityManager;

    public ReservaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void guardar(Reserva reserva) {
        this.entityManager.persist(reserva);
    }
    public Reserva consultarPorId(int id) {
        return this.entityManager.find(Reserva.class, id);
    }

    public List<Reserva> listaReservas() {
        String jpql = "SELECT r FROM Reserva AS r ";
        return this.entityManager.createQuery(jpql, Reserva.class).getResultList();
    }
    public void eliminar(int id) {
        //con esto garantizamos que la entidad se encuentre en managed
        Reserva reserva = null;
        reserva = this.entityManager.merge(new Reserva(id));
        this.entityManager.remove(reserva);
    }

    public void actualizar(Reserva reserva) {
        this.entityManager.merge(reserva);
    }

    public List<Reserva> consultarPoriD(int idR) {
        String jpql = "SELECT r FROM Reserva AS r WHERE r.idR=:idR";
        return this.entityManager.createQuery(jpql, Reserva.class).setParameter("idR",idR).getResultList();
    }
}
