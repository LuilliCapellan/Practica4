package modelo.servicios.Utils;

import encapsulacion.Usuario;

import static spark.Spark.before;

public class Filtros {
    public void filtros() {


        before((request, response) -> System.out.println("Ruta antes: " + request.pathInfo()));
        before("/inicio", (request, response) -> response.redirect("/inicio/1"));
        before("/agregarPost", (request, response) -> {

            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null || (!usuario.getAutor() && !usuario.getAdministrator())) {
                response.redirect("/inicio/1");

            }

        });

        before("/agregarUsuario", (request, response) -> {

            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null || !usuario.getAdministrator()) {
                response.redirect("/inicio/1");

            }

        });

        before("/agregarComentario", (request, response) -> {

            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null) {
                response.redirect("/inicio/1");

            }

        });

    }

}
