package info.creidea.controller;

import info.creidea.client.SubjectsFetchAble;
import info.creidea.domain.AuthUser;
import info.creidea.domain.SubjectInfo;
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
        final var subjects = subjectsFetcher.fetch(4, 2);
         final var model = new HashMap<String, Object>();
         model.put("subjects", subjects
                 .stream()
                 .map(SubjectInfo::科目名)
                 .toList()
         );
        return new ModelAndView(model, "absence.vm");
    };
}
