package encapsulacion;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name = "Etiqueta.findAllEtiqueta", query = "select e from Etiqueta e"),
        @NamedQuery(name = "Etiqueta.findEtiquetaById", query = "select e from Etiqueta e where e.id = :id"),
        @NamedQuery(name = "Etiqueta.findAllEtiquetaByArticuloId", query = "select e from Etiqueta e join Articulo a on a.id = :id")})
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String etiqueta;
    @ManyToOne
    private Articulo articulo;

    public Etiqueta() {
    }

    public Etiqueta(long id, String etiqueta, Articulo articulo) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.articulo = articulo;
    }
    public Etiqueta(String etiqueta, Articulo articulo) {
        this.etiqueta = etiqueta;
        this.articulo = articulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
