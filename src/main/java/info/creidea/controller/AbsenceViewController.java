package info.creidea.controller;

import info.creidea.domain.AuthUser;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.TemplateViewRoute;
import static spark.Spark.*;

import java.util.HashMap;

public class AbsenceViewController {
    public void boot(TemplateEngine engine) {
        get("/", index, engine);
    }

    public TemplateViewRoute index = (req, res) -> {
        final AuthUser attribute = req.session().attribute("user");
        System.out.println(attribute);
        return new ModelAndView(new HashMap<String, Object>(), "absence.vm");
    };
}
