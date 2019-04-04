package modelo.dao.interfaces;

import encapsulacion.Likes;
import encapsulacion.TipoLike;
import encapsulacion.Usuario;

public interface LikesDAO {

    void guardar(Likes likes);

    int LikesByArticuloId(long id);
    int DislikesByArticuloId(long id);

    boolean existsUsuario(long id, TipoLike tipoLike);

    void borrar(Likes likes);

    Likes buscarByUsuario(Usuario usuario);
}
