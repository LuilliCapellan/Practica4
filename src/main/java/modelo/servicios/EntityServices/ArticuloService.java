package modelo.servicios.EntityServices;

import encapsulacion.Articulo;
import modelo.dao.Implementations.ArticuloDAOImpl;
import modelo.dao.interfaces.ArticuloDAO;

import java.util.List;

public class ArticuloService implements ArticuloDAO {
    private ArticuloDAOImpl articuloDAO;
    private static ArticuloService instancia;

    public ArticuloService() {
        articuloDAO = new ArticuloDAOImpl(Articulo.class);
    }

    public static ArticuloService getInstancia() {
        if (instancia == null)
            instancia = new ArticuloService();

        return instancia;
    }


    @Override
    public void insert(Articulo e) {
        articuloDAO.insert(e);
    }

    @Override
    public void update(Articulo e) {
        articuloDAO.update(e);
    }

    @Override
    public void delete(Articulo e) {
        articuloDAO.delete(e);
    }

    @Override
    public List<Articulo> getAll() {
        return articuloDAO.getAll();
    }

    @Override
    public Articulo getById(long id) {
        return articuloDAO.getById(id);
    }

    @Override
    public List<Articulo> getAllByEtiqueta(String etiqueta) {
        return articuloDAO.getAllByEtiqueta(etiqueta);
    }

    @Override
    public int cantPaginas() {
        return articuloDAO.cantPaginas();
    }

    @Override
    public List<Articulo> getbyAutor(long id) {
        return articuloDAO.getbyAutor(id);
    }

    @Override
    public List<Articulo> getPagination(int pag) {
        return articuloDAO.getPagination(pag);
    }


    public boolean buscarPost(List<Articulo> articulos, long id) {

        for (Articulo articulo : articulos) {
            if (articulo.getId() == id)
                return true;
        }

        return false;
    }

}
