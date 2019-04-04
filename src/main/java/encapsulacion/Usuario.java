package encapsulacion;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({@NamedQuery(name = "Usuario.findAllUsuario", query = "select u from Usuario u"),
        @NamedQuery(name = "Usuario.findUsuariobyId", query = "select u from Usuario u where u.id = :id"),
        @NamedQuery(name = "Usuario.validateLogIn", query = "select u from Usuario u where u.username = :username and u.password = :pass")})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Usuario_ID")
    private long id;
    @Column
    private String username;
    @Column
    private String nombre;
    @Column
    private String password;
    @Column
    private Boolean administrator;
    @Column
    private Boolean autor;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    Set<Likes> likes = new HashSet<>();

    public Usuario() {
    }

    public Usuario(long id, String username, String nombre, String password, Boolean administrator, Boolean autor) {
        this.setId(id);
        this.setUsername(username);
        this.setNombre(nombre);
        this.setPassword(password);
        this.setAdministrator(administrator);
        this.setAutor(autor);
    }

    public Usuario(String username, String nombre, String password, Boolean administrator, Boolean autor) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.administrator = administrator;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public Boolean getAutor() {
        return autor;
    }

    public void setAutor(Boolean autor) {
        this.autor = autor;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }
}
