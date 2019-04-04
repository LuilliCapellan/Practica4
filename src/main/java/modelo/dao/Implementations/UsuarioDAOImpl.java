package modelo.dao.Implementations;

import encapsulacion.Usuario;
import modelo.dao.interfaces.UsuarioDAO;
import modelo.servicios.Utils.CRUD;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class UsuarioDAOImpl extends CRUD<Usuario> implements UsuarioDAO {


    public UsuarioDAOImpl(Class<Usuario> usuarioClass) {
        super(usuarioClass);
    }

    @Override
    public void insert(Usuario e) {
        crear(e);
    }

    @Override
    public void update(Usuario e) {
        editar(e);

    }

    @Override
    public void delete(Usuario e) {
        eliminar(e);
    }

    @Override
    public List<Usuario> getAll() {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Usuario.findAllUsuario");
        return (List<Usuario>) query.getResultList();
    }

    @Override
    public Usuario getById(long id) {

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Usuario.findUsuariobyId");
        query.setParameter("id", id);
        return (Usuario) query.getSingleResult();
    }

    @Override
    public Usuario validateLogIn(String user, String pass) {

        Usuario usuario = null;
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Usuario.validateLogIn");
        query.setParameter("username", user);
        query.setParameter("pass", pass);
        try {
            usuario = (Usuario) query.getSingleResult();
        } catch (NoResultException e) {

            System.out.println("No se encontro el usuario.");
        }

        return usuario;


    }


}
