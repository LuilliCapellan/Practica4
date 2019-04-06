package main;

import encapsulacion.*;
import freemarker.template.Configuration;
import freemarker.template.Version;
import modelo.servicios.EntityServices.*;
import modelo.servicios.Utils.Crypto;
import modelo.servicios.Utils.Filtros;
import modelo.servicios.Utils.ServiceInit;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;


public class Main {

    public static Usuario usuario;
    static final String iv = "0123456789123456"; // This has to be 16 characters
    static final String secretKeyUSer = "qwerty987654321";
    static final String secretKeyContra = "123456789klk";

    public static void main(String[] args) throws SQLException {

        ServiceInit.startDb();

        ServiceInit.crearTablas();

        ArticuloService.getInstancia();

//        UsuarioService.getInstancia() UsuarioService.getInstancia() = new UsuarioService.getInstancia()();
//        ArticuloService.getInstancia() ArticuloService.getInstancia() = new ArticuloService.getInstancia()();
//        EtiquetaService.getInstancia() EtiquetaService.getInstancia() = new EtiquetaService.getInstancia()();
//        ComentarioService.getInstancia() ComentarioService.getInstancia() = new ComentarioService.getInstancia()();
//        LikesService.getInstancia LikesService.getInstancia = new LikesService.getInstancia();


        staticFiles.location("/templates");

        Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Map<String, String> cookies = request.cookies();

            String[] llaveValor = new String[2];
            request.cookie("login");
            for (String key : cookies.keySet()) {
                System.out.println("llave: " + key + " valor: " + cookies.get(key));
                llaveValor = cookies.get(key).split(",");

            }


            if (llaveValor.length > 1) {
                Crypto crypto = new Crypto();

                System.out.println(llaveValor[0] + " contra: " + llaveValor[1]);
                String user = crypto.decrypt(llaveValor[0], iv, secretKeyUSer);
                String contra = crypto.decrypt(llaveValor[1], iv, secretKeyContra);

                Usuario usuario1 = UsuarioService.getInstancia().validateLogIn(user, contra);
                if (usuario1 != null) {
                    usuario = usuario1;
                    request.session(true);
                    request.session().attribute("usuario", usuario);
                    response.redirect("/inicio/1");
                }
            }
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        get("/inicio/:pag", (request, response) -> {

            String p = request.params("pag");
            int pagina = Integer.parseInt(p);
            Map<String, Object> attributes = new HashMap<>();
            userLevel(attributes);
            attributes.put("titulo", "Inicio");
            System.out.println("Paginacion " + ArticuloService.getInstancia().getPagination(1));
            attributes.put("list", ArticuloService.getInstancia().getPagination(pagina));
            attributes.put("actual", pagina);
            attributes.put("paginas", Math.ceil(ArticuloService.getInstancia().cantPaginas() / 5f));
            attributes.put("etiquetas", EtiquetaService.getInstancia().getAll());
            attributes.put("usuario", usuario);

            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);


        get("/verMas/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String idArticulo = request.params("id");
            userLevel(attributes);
            Articulo articulo2 = ArticuloService.getInstancia().getById(Integer.parseInt(idArticulo));
            attributes.put("articulo", articulo2);
            attributes.put("comentarios", articulo2.getListaComentarios());
            attributes.put("etiquetas", articulo2.getListaEtiquetas());
            System.out.println("eto" + articulo2.getListaEtiquetas());
            attributes.put("cantLikes", LikesService.getInstancia().LikesByArticuloId(articulo2.getId()));
            attributes.put("cantDislikes", LikesService.getInstancia().DislikesByArticuloId(articulo2.getId()));
            attributes.put("usuario", usuario);

            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);
        get("/articulos/:etiqueta", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String etiqueta = request.params("etiqueta");
            userLevel(attributes);
            attributes.put("titulo", "Articulos por: " + etiqueta);
            attributes.put("list", ArticuloService.getInstancia().getAllByEtiqueta(etiqueta));
            attributes.put("etiquetas", EtiquetaService.getInstancia().getAll());
            System.out.println("eto" + ArticuloService.getInstancia().getAllByEtiqueta(etiqueta));
            attributes.put("usuario", usuario);
            attributes.put("paginas", 1);
            attributes.put("actual", 1);
            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);


        post("/agregarComentario", (request, response) -> {

            if (usuario == null)
                response.redirect("/errorPost");

            String comentario = request.queryParams("comentario");
            String articulo = request.queryParams("articulo");
            String autor = request.queryParams("autor");

            Usuario usuario1 = UsuarioService.getInstancia().getById(Integer.parseInt(autor));
            Articulo articulo1 = ArticuloService.getInstancia().getById(Integer.parseInt(articulo));

            Comentario comentario1 = new Comentario(comentario, usuario1, articulo1);

            ComentarioService.getInstancia().insert(comentario1);

            response.redirect("/verMas/" + articulo);
            return "";
        });

        get("/agregarPost", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            userLevel(attributes);
            return new ModelAndView(attributes, "agregarPost.ftl");

        }, freeMarkerEngine);
        get("/editarPost/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            userLevel(attributes);
            String idArticulo = request.params("id");
            long articleid = Integer.parseInt(idArticulo);

            List<Articulo> a = new ArrayList<>();

            if (usuario != null) {

                a = ArticuloService.getInstancia().getbyAutor(usuario.getId());
            } else {
                response.redirect("/errorPost");
            }

            if (ArticuloService.getInstancia().buscarPost(a, articleid) || usuario.getAdministrator()) {

                Articulo editar = ArticuloService.getInstancia().getById(Integer.parseInt(idArticulo));
                attributes.put("articulo", editar);
                List<Etiqueta> tags = EtiquetaService.getInstancia().getByArticulo(Integer.parseInt(idArticulo));
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < tags.size(); i++) {
                    if (i == tags.size() - 1) {
                        res.append(tags.get(i).getEtiqueta());
                    } else {
                        res.append(tags.get(i).getEtiqueta()).append(",");
                    }
                }
                String ResultingTagString = String.valueOf(res);
                attributes.put("etiquetas", ResultingTagString);
            } else {
                response.redirect("/errorPost");
            }


            return new ModelAndView(attributes, "editarPost.ftl");
        }, freeMarkerEngine);

        post("/guardarPost", (request, response) -> {
            Usuario autor = usuario;
            String titulo = request.queryParams("titulo");
            String cuerpo = request.queryParams("cuerpo");
            long now = System.currentTimeMillis();
            java.sql.Date nowsql = new java.sql.Date(now);

            String etiquetas = request.queryParams("etiquetas");

            String[] tagsarray = etiquetas.split(",");
            Articulo art = new Articulo(titulo, cuerpo, autor, nowsql);

            Set<Etiqueta> etiquetas1 = new HashSet<>();

            for (String s : tagsarray) {
                Etiqueta e = new Etiqueta(s, art);
                etiquetas1.add(e);
            }

            art.setListaEtiquetas(etiquetas1);
            ArticuloService.getInstancia().insert(art);

            response.redirect("/inicio/1");
            return "";
        });

        post("/iniciarSesion", (request, response) -> {

            String user = request.queryParams("usuario");
            String contra = request.queryParams("password");
            String recordar = request.queryParams("remember");

            System.out.println(recordar);


            System.out.println(user + " pass : " + contra);
            Usuario usuario1 = UsuarioService.getInstancia().validateLogIn(user, contra);

            if (usuario1 != null) {

                if (recordar != null && recordar.equalsIgnoreCase("on")) {


                    Crypto crypto = new Crypto();
                    String userEncrypt = crypto.encrypt(user, iv, secretKeyUSer);
                    String contraEncrypt = crypto.encrypt(contra, iv, secretKeyContra);


                    System.out.println("user encryp: " + userEncrypt + " contra encryp: " + contraEncrypt);

                    response.cookie("/", "login", userEncrypt + "," + contraEncrypt, 604800, false); //incluyendo el path del cookie.
                }

                usuario = usuario1;
                request.session(true);
                request.session().attribute("usuario", usuario);
                response.redirect("/inicio/1");
            }
            return "";
        });

        post("/editarPost/:id", (request, response) -> {


            String idArticulo = request.params("id");
            Long articleid = Long.parseLong(idArticulo);


            Usuario autor = usuario;

            String titulo = request.queryParams("titulo");
            String cuerpo = request.queryParams("cuerpo");
            long now = System.currentTimeMillis();
            java.sql.Date nowsql = new java.sql.Date(now);
            Articulo art = new Articulo(articleid, titulo, cuerpo, autor, nowsql);
            ArticuloService.getInstancia().update(art);
            String tags = request.queryParams("etiquetas");

            String[] tagArray = tags.split(",");

            List<Etiqueta> l = EtiquetaService.getInstancia().getByArticulo(articleid);

            for (String aTagArray : tagArray) {
                boolean exists = false;
                for (Etiqueta e : l) {
                    if (aTagArray.equalsIgnoreCase(e.getEtiqueta())) {
                        exists = true;
                    }
                }
                if (!exists) {
                    EtiquetaService.getInstancia().insert(new Etiqueta(aTagArray, art));
                }
            }
            response.redirect("/verMas/" + idArticulo);


            return "";
        });

        get("/eliminarPost/:id/:articulo", (request, response) -> {

            String id = request.params("id");
            String articulo = request.params("articulo");

            long idAutor = Integer.parseInt(id);
            long idArticulo = Integer.parseInt(articulo);

            if (usuario != null && (idAutor == usuario.getId() || usuario.getAdministrator())) {
                ArticuloService.getInstancia().delete(ArticuloService.getInstancia().getById(idArticulo));
                response.redirect("/inicio/1");
            } else {
                response.redirect("/errorPost");
            }

            return "";
        });

        get("/agregarUsuario", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            userLevel(attributes);
            return new ModelAndView(attributes, "agregarUsuario.ftl");
        }, freeMarkerEngine);

        post("/guardarUsuario", (request, response) -> {
            String username = request.queryParams("usuario");
            String nombre = request.queryParams("nombre");
            String pass = request.queryParams("pass");
            String autor = request.queryParams("autor");
            String admin = request.queryParams("admin");
            Usuario u = new Usuario(username, nombre, pass, admin != null, autor != null);
            UsuarioService.getInstancia().insert(u);
            response.redirect("/inicio/1");
            return "";
        });


        get("/logOut", (request, response) -> {

            usuario = null;
            Session session = request.session(true);
            session.invalidate();
            response.removeCookie("/", "login");
            response.redirect("/inicio/1");

            return "";
        });

        get("/errorPost", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("mensaje", "Usted no tiene permisos para esta area!");

            return new ModelAndView(attributes, "error.ftl");
        }, freeMarkerEngine);

        get("/verUsuarios", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("usuarios", UsuarioService.getInstancia().getAll());

            return new ModelAndView(attributes, "verUsuarios.ftl");
        }, freeMarkerEngine);


        get("/ver/:id", (request, response) -> {
            String id = request.params("id");

            Usuario usuario = UsuarioService.getInstancia().getById(Integer.parseInt(id));


            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Posts de " + usuario.getNombre());
            attributes.put("usuario", usuario);
            attributes.put("etiquetas", EtiquetaService.getInstancia().getAll());
            attributes.put("list", ArticuloService.getInstancia().getbyAutor(usuario.getId()));
            userLevel(attributes);
            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);


        get("/like/:post", (request, response) -> {

            String post = request.params("post");

            int idPost = Integer.parseInt(post);

            Usuario usuario = request.session(true).attribute("usuario");
            Articulo articulo = ArticuloService.getInstancia().getById(idPost);
            if (usuario != null) {

                if (!LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.LIKE) && !LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.DISLIKE)) {


                    Likes likes1 = new Likes(articulo, usuario, TipoLike.LIKE);

                    LikesService.getInstancia().guardar(likes1);


                    response.redirect("/verMas/" + articulo.getId());

                } else {

                    if (LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.LIKE)) {

                        Likes likes = LikesService.getInstancia().buscarByUsuario(usuario);
                        LikesService.getInstancia().borrar(likes);
                    }

                    if (LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.DISLIKE)) {

                        Likes likes = LikesService.getInstancia().buscarByUsuario(usuario);
                        LikesService.getInstancia().borrar(likes);

                        Likes likes1 = new Likes(articulo, usuario, TipoLike.LIKE);
                        LikesService.getInstancia().guardar(likes1);


                    }


                    response.redirect("/verMas/" + idPost);
                }

            } else {
                response.redirect("/errorPost");
            }
            return "";
        });

        get("dislike/:post", (request, response) -> {

            String post = request.params("post");

            int idPost = Integer.parseInt(post);
            Usuario usuario = request.session(true).attribute("usuario");
            Articulo articulo = ArticuloService.getInstancia().getById(idPost);

            if (usuario != null) {

                if (!LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.DISLIKE) && !LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.LIKE)) {


                    Likes likes1 = new Likes(articulo, usuario, TipoLike.DISLIKE);
                    LikesService.getInstancia().guardar(likes1);


                    response.redirect("/verMas/" + articulo.getId());

                } else {

                    if (LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.DISLIKE)) {
                        Likes likes = LikesService.getInstancia().buscarByUsuario(usuario);
                        LikesService.getInstancia().borrar(likes);

                    }

                    if (LikesService.getInstancia().existsUsuario(usuario.getId(), TipoLike.LIKE)) {

                        Likes likes = LikesService.getInstancia().buscarByUsuario(usuario);
                        LikesService.getInstancia().borrar(likes);

                        Likes likes1 = new Likes(articulo, usuario, TipoLike.DISLIKE);
                        LikesService.getInstancia().guardar(likes1);


                    }


                    response.redirect("/verMas/" + idPost);
                }

            } else {
                response.redirect("/errorPost");
            }

            return "";
        });


        new Filtros().filtros();
    }

    private static void userLevel(Map<String, Object> attributes) {
        attributes.put("usuario", usuario);

        if (usuario != null) {
            attributes.put("admin", usuario.getAdministrator());
            attributes.put("autor", usuario.getAutor());
            attributes.put("usuarioName", usuario.getNombre());
        }
    }
}

