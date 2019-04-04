package modelo.dao.Implementations;

import encapsulacion.Comentario;
import modelo.dao.interfaces.ComentarioDAO;
import modelo.servicios.Utils.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ComentarioDAOImpl extends CRUD<Comentario> implements ComentarioDAO {

    public ComentarioDAOImpl(Class<Comentario> comentarioClass) {
        super(comentarioClass);
    }

    @Override
    public void insert(Comentario e) {
        crear(e);

    }

    @Override
    public void update(Comentario e) {
        editar(e);


    }

    @Override
    public void delete(Comentario e) {
        eliminar(e);
    }

    @Override
    public List<Comentario> getAll() {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Comentario.findAllComentario");
        return (List<Comentario>) query.getSingleResult();
    }

    @Override
    public Comentario getById(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Comentario.findComentarioById");
        query.setParameter("id", id);
        return (Comentario) query.getSingleResult();
    }

    @Override
    public List<Comentario> getByArticulo(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Comentario.findAllComentarioByArticulo");
        query.setParameter("id", id);
        return (List<Comentario>) query.getResultList();
    }
}
