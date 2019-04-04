package modelo.dao.interfaces;

import encapsulacion.Articulo;

import java.util.List;

public interface ArticuloDAO {

    void insert(Articulo e);

    void update(Articulo e);

    void delete(Articulo e);

    List<Articulo> getAll();

    List<Articulo> getbyAutor(long id);

    List<Articulo> getPagination(int pag);

    Articulo getById(long id);

    List<Articulo> getAllByEtiqueta(String etiqueta);

    int cantPaginas();


}
