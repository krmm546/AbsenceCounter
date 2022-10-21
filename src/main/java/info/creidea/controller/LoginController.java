package info.creidea.controller;

import spark.ModelAndView;
import spark.TemplateEngine;
import spark.TemplateViewRoute;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Optional;

public class LoginController {
    public void boot(TemplateEngine engine) {
        path("/login", () -> {
            get("", index, engine);
            post("", login, engine);
        });
    }

    public TemplateViewRoute index = (req, res) -> view(false);

    public TemplateViewRoute login = (req, res) -> {
        final var id = Optional.ofNullable(req.queryParams("id")).orElseThrow();
        final var pass = Optional.ofNullable(req.queryParams("password")).orElseThrow();

        if (!(id.equals("test") && pass.equals("pas"))) return view(true);

        res.redirect("/");
        return null;
    };

    private ModelAndView view(Boolean isFail) {
        return new ModelAndView(new HashMap<String, Object>() {{
            put("isFail", isFail);
        }}, "login.vm");
    }

}