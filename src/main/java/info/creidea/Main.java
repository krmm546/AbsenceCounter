package info.creidea;

import info.creidea.client.SubjectsFetcher;
import info.creidea.controller.AbsenceViewController;
import info.creidea.controller.LoginController;
import info.creidea.controller.RegisterController;
import info.creidea.database.DatabaseFactory;
import info.creidea.database.SubjectAbsenceFetcher;
import info.creidea.database.migration.AllMigration;
import info.creidea.database.query.AbsenceFetcher;
import info.creidea.database.query.AbsenceRegister;
import info.creidea.database.query.Authenticator;
import spark.template.velocity.VelocityTemplateEngine;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        final var connection = DatabaseFactory.create();
        new AllMigration().prepare(connection);

        // Dependencies
        final var authenticator = new Authenticator(connection);
        final var subjectsFetcher = new SubjectsFetcher();
        final var absenceFetcher = new AbsenceFetcher(connection);
        final var subjectAbsenceFetcher = new SubjectAbsenceFetcher(connection, subjectsFetcher);
        final var absenceRegister = new AbsenceRegister(connection);

        final var templateEngine = new VelocityTemplateEngine();
        final var login = new LoginController(authenticator);
        final var absence = new AbsenceViewController(subjectsFetcher, absenceFetcher);
        final var register = new RegisterController(subjectAbsenceFetcher, absenceRegister);

        login.boot(templateEngine);
        absence.boot(templateEngine);
        register.boot(templateEngine);
    }
}