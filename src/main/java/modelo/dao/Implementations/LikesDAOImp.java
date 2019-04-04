package modelo.dao.Implementations;

import encapsulacion.Likes;
import encapsulacion.TipoLike;
import encapsulacion.Usuario;
import modelo.dao.interfaces.LikesDAO;
import modelo.servicios.Utils.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LikesDAOImp  extends CRUD<Likes> implements LikesDAO {

    public LikesDAOImp(Class<Likes> likesClass) {
        super(likesClass);
    }

    @Override
    public void guardar(Likes likes) {
        crear(likes);
    }

    @Override
    public int LikesByArticuloId(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select l from Likes l WHERE l.articulo.id = :articulo AND l.tipoLike = :tipo");
        query.setParameter("articulo", id);
        query.setParameter("tipo", TipoLike.LIKE);

        return query.getResultList().size();
    }

    @Override
    public int DislikesByArticuloId(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select l from Likes l WHERE l.articulo.id = :articulo AND l.tipoLike = :tipo");
        query.setParameter("articulo", id);
        query.setParameter("tipo", TipoLike.DISLIKE);

        return query.getResultList().size();
    }

    @Override
    public boolean existsUsuario(long id, TipoLike tipoLike) {
        boolean existe = false;
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select l from Likes l WHERE l.usuario.id = :id and l.tipoLike = :tipo ");
        query.setParameter("id", id);
        query.setParameter("tipo", tipoLike);

        int cant = query.getResultList().size() ;

        if (cant > 0)
        {
            existe = true;
        }
        return existe;
    }

    @Override
    public void borrar(Likes likes) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select la from Likes la where la.id = :id");
        query.setParameter("id", likes.getId());
        List<Likes> resultado = query.getResultList();
        for (Likes likes2: resultado) {
            eliminar(likes2.getId());
        }

        return;
    }

    @Override
    public Likes buscarByUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Likes.findLikeByUsuario");
        query.setParameter("id", usuario.getId());
        return (Likes) query.getSingleResult();
    }
}