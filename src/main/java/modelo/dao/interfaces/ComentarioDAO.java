package modelo.dao.interfaces;

import encapsulacion.Comentario;

import java.util.List;

public interface ComentarioDAO {

    void insert(Comentario e);

    void update(Comentario e);

    void delete(Comentario e);

    List<Comentario> getAll();

    Comentario getById(long id);

    /**
     * Función para obtener los comentarios de un artículo.
     * @param id del articulo
     * @return lista de comentarios.
     */
    List<Comentario> getByArticulo(long id);
}
