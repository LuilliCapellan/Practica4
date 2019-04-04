package modelo.dao.interfaces;

import encapsulacion.Etiqueta;

import java.util.List;

public interface EtiquetaDAO {

    void insert(Etiqueta e);

    void update(Etiqueta e);

    void delete(Etiqueta e);

    List<Etiqueta> getAll();

    Etiqueta getById(long id);

    /**
     * Función para obtener las etiquetas de un artículo.
     * @param id del articulo
     * @return lista de etiquetas.
     */
    List<Etiqueta> getByArticulo(long id);


}
