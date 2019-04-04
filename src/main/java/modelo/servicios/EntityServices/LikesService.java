package modelo.servicios.EntityServices;

import encapsulacion.Likes;
import encapsulacion.TipoLike;
import encapsulacion.Usuario;
import modelo.dao.Implementations.LikesDAOImp;
import modelo.dao.interfaces.LikesDAO;
import modelo.servicios.Utils.CRUD;

public class LikesService implements LikesDAO {

    private LikesDAOImp likesDAOImp;

    private static LikesService instancia;

    public static LikesService getInstancia(){
        if (instancia == null)
            instancia = new LikesService();

        return instancia;
    }

    public LikesService(){
        likesDAOImp = new LikesDAOImp(Likes.class);
    }


    @Override
    public void guardar(Likes likes) {
        likesDAOImp.guardar(likes);
    }

    @Override
    public int LikesByArticuloId(long id) {
        return likesDAOImp.LikesByArticuloId(id);
    }

    @Override
    public int DislikesByArticuloId(long id) {
        return likesDAOImp.DislikesByArticuloId(id);
    }

    @Override
    public boolean existsUsuario(long id, TipoLike tipoLike) {
        return likesDAOImp.existsUsuario(id, tipoLike);
    }

    @Override
    public void borrar(Likes likes) {
        likesDAOImp.borrar(likes);
    }

    @Override
    public Likes buscarByUsuario(Usuario usuario) {
        return likesDAOImp.buscarByUsuario(usuario);
    }

    public void actualizar(Likes likes){

        new CRUD<>(Likes.class).editar(likes);

    }
}
