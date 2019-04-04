package modelo.dao.Implementations;

import encapsulacion.Etiqueta;
import modelo.dao.interfaces.EtiquetaDAO;
import modelo.servicios.Utils.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EtiquetaDAOImpl extends CRUD<Etiqueta> implements EtiquetaDAO {

    public EtiquetaDAOImpl(Class<Etiqueta> etiquetaClass) {
        super(etiquetaClass);
    }

    @Override
    public void insert(Etiqueta e) {
        crear(e);
    }

    @Override
    public void update(Etiqueta e) {
        editar(e);
    }

    @Override
    public void delete(Etiqueta e) {
        eliminar(e);
    }

    @Override
    public List<Etiqueta> getAll() {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Etiqueta.findAllEtiqueta");
        return (List<Etiqueta>) query.getResultList();
    }

    @Override
    public Etiqueta getById(long id) {

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Etiqueta.findEtiquetaById");
        query.setParameter("id", id);
        return (Etiqueta) query.getSingleResult();
    }


    @Override
    public List<Etiqueta> getByArticulo(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Etiqueta.findAllEtiquetaByArticuloId");
        query.setParameter("id", id);
        return (List<Etiqueta>) query.getResultList();
    }
}
