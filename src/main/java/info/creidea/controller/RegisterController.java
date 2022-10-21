package info.creidea.controller;

import info.creidea.domain.AuthUser;
import info.creidea.presentation.PersonalSubjectContent;
import info.creidea.repository.AbsenceRegisterAble;
import info.creidea.repository.SubjectAbsenceFetchAble;
import spark.ModelAndView;
import spark.Route;
import spark.TemplateEngine;
import spark.TemplateViewRoute;

import static spark.Spark.*;

public class RegisterController {

    private SubjectAbsenceFetchAble subjectAbsenceFetcher;
    private AbsenceRegisterAble absenceRegister;

    public RegisterController(SubjectAbsenceFetchAble subjectAbsenceFetcher, AbsenceRegisterAble absenceRegister) {
        this.subjectAbsenceFetcher = subjectAbsenceFetcher;
        this.absenceRegister = absenceRegister;
    }

    public void boot(TemplateEngine engine) {
        path("/register/:subjectID",() -> {
           get("",index, engine);
           post("", register);
        });
    }

    public TemplateViewRoute index = (req, res) -> {
//        final AuthUser user = req.session().attribute("user");
        final AuthUser user = new AuthUser("10550");
        final var 科目ID = req.params("subjectID");
        final var subject = subjectAbsenceFetcher.fetch(user, 科目ID);
        final var content = new PersonalSubjectContent(subject);
        return new ModelAndView(content.model(), "register.vm");
    };

    public Route register = (req, res) -> {
//        final AuthUser user = req.session().attribute("user");
        final AuthUser user = new AuthUser("10550");
        final var 科目ID = req.params("subjectID");
        final var date = req.queryParams("date");
        final var absence = Integer.parseInt(req.queryParams("absence"));
        final var late = req.queryParams("late") != null;
        absenceRegister.register(user, 科目ID, date, absence, late);
        res.redirect("/");
        return null;
    };
}
