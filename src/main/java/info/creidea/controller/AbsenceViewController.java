package info.creidea.controller;

import info.creidea.client.SubjectsFetchAble;
import info.creidea.domain.AuthUser;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.TemplateViewRoute;
import static spark.Spark.*;

import java.util.HashMap;

public class AbsenceViewController {
    private SubjectsFetchAble subjectsFetcher;

    public AbsenceViewController(SubjectsFetchAble subjectsFetcher) {
        this.subjectsFetcher = subjectsFetcher;
    }

    public void boot(TemplateEngine engine) {
        path("/", () -> {
            get("", index, engine);
        });
    }

    public TemplateViewRoute index = (req, res) -> {
        final AuthUser attribute = req.session().attribute("user");
        System.out.println(attribute);
        System.out.println(subjectsFetcher.fetch(4, 2));
        return new ModelAndView(new HashMap<String, Object>(), "absence.vm");
    };
}
