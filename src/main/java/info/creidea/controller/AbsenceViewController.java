package info.creidea.controller;

import info.creidea.client.SubjectsFetchAble;
import info.creidea.domain.AuthUser;
import info.creidea.presentation.PersonalSubjectsContent;
import info.creidea.repository.AbsenceFetchAble;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.TemplateViewRoute;

import static spark.Spark.get;
import static spark.Spark.path;

public class AbsenceViewController implements Controller {
    private SubjectsFetchAble subjectsFetcher;
    private AbsenceFetchAble absenceFetcher;

    public AbsenceViewController(SubjectsFetchAble subjectsFetcher, AbsenceFetchAble absenceFetcher) {
        this.subjectsFetcher = subjectsFetcher;
        this.absenceFetcher = absenceFetcher;
    }

    @Override
    public void boot(TemplateEngine engine) {
        path("/", () -> {
            get("", index, engine);
        });
    }

    public TemplateViewRoute index = (req, res) -> {
        final AuthUser user = req.session().attribute("user");
        final var subjects = subjectsFetcher.fetch(4, 2);
        final var personals = absenceFetcher.fetch(subjects, user);
        final var content = new PersonalSubjectsContent(personals);
        return new ModelAndView(content.model(), "absence.vm");
    };
}
