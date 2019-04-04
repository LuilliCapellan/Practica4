package encapsulacion;


import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name = "Comentario.findAllComentario", query = "select c from Comentario c"),
        @NamedQuery(name = "Comentario.findComentarioById", query = "select c from Comentario c where c.id = :id"),
        @NamedQuery(name = "Comentario.findAllComentarioByArticulo", query = "select c from Comentario c join Articulo a on a.id = :id")})
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String comentario;
    @OneToOne
    private Usuario autor;
    @ManyToOne
    private Articulo articulo;

    public Comentario() {
    }

    public Comentario(String comentario, Usuario autor, Articulo articulo) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
