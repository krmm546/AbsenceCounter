package info.creidea.controller;

import info.creidea.client.SubjectsFetchAble;
import info.creidea.domain.AuthUser;
import info.creidea.presentation.PersonalSubjectsContent;
import info.creidea.repository.AbsenceFetchAble;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.TemplateViewRoute;
import static spark.Spark.*;

public class AbsenceViewController {
    private SubjectsFetchAble subjectsFetcher;
    private AbsenceFetchAble absenceFetcher;

    public AbsenceViewController(SubjectsFetchAble subjectsFetcher, AbsenceFetchAble absenceFetcher) {
        this.subjectsFetcher = subjectsFetcher;
        this.absenceFetcher = absenceFetcher;
    }

    public void boot(TemplateEngine engine) {
        path("/", () -> {
            get("", index, engine);
        });
    }

    public TemplateViewRoute index = (req, res) -> {
//        final AuthUser user = req.session().attribute("user");
        final var user = new AuthUser("10550");
        final var subjects = subjectsFetcher.fetch(4, 2);
        final var personals = absenceFetcher.fetch(subjects, user);
        System.out.println(personals);
        final var content = new PersonalSubjectsContent(personals);
        return new ModelAndView(content.model(), "absence.vm");
    };
}
