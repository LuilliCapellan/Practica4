package encapsulacion;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({@NamedQuery(name = "Likes.findLikeByUsuario", query = "select c from Likes c where c.usuario.id = :id"),
    @NamedQuery(name = "Likes.deleteById", query = "delete from Likes l where l.id = :id")})
public class Likes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Likes_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Articulo_id")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "Usuario_ID")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoLike tipoLike;

    public Likes() {
    }

    public Likes(Articulo articulo, Usuario usuario, TipoLike tipoLike) {
        this.articulo = articulo;
        this.usuario = usuario;
        this.tipoLike = tipoLike;
    }
    public Likes(Long id, Articulo articulo, Usuario usuario, TipoLike tipoLike) {
        this.id = id;
        this.articulo = articulo;
        this.usuario = usuario;
        this.tipoLike = tipoLike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoLike getTipoLike() {
        return tipoLike;
    }

    public void setTipoLike(TipoLike tipoLike) {
        this.tipoLike = tipoLike;
    }
}
