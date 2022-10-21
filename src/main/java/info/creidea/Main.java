package info.creidea;

import info.creidea.controller.AbsenceViewController;
import info.creidea.controller.LoginController;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        final var templateEngine = new VelocityTemplateEngine();
        final var login = new LoginController();
        final var absence = new AbsenceViewController();

        login.boot(templateEngine);
        absence.boot(templateEngine);
    }
}