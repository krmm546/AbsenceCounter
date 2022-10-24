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
import info.creidea.database.query.Logger;
import info.creidea.database.seed.SampleSeeder;
import info.creidea.middleware.LoggerMiddleware;
import info.creidea.middleware.LoginMiddleware;
import spark.template.velocity.VelocityTemplateEngine;
import java.sql.SQLException;

import static spark.Spark.*;
public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        // Databases
        final var connection = DatabaseFactory.create();
        new AllMigration().prepare(connection);
        new SampleSeeder().seed(connection);

        // Dependencies
        final var authenticator = new Authenticator(connection);
        final var subjectsFetcher = new SubjectsFetcher();
        final var absenceFetcher = new AbsenceFetcher(connection);
        final var subjectAbsenceFetcher = new SubjectAbsenceFetcher(connection, subjectsFetcher);
        final var absenceRegister = new AbsenceRegister(connection);
        final var logger = new Logger(connection);

        final var templateEngine = new VelocityTemplateEngine();

        // Controllers
        final var login = new LoginController(authenticator);
        final var absence = new AbsenceViewController(subjectsFetcher, absenceFetcher);
        final var register = new RegisterController(subjectAbsenceFetcher, absenceRegister);

        // Middlewares
        final var logMiddleware = new LoggerMiddleware(logger);
        final var loginMiddleware = new LoginMiddleware();


        staticFiles.location("/public");

        before("*", logMiddleware::before);
        before("/", loginMiddleware::before);
        before("/register", loginMiddleware::before);
        before("/register/*", loginMiddleware::before);

        login.boot(templateEngine);
        absence.boot(templateEngine);
        register.boot(templateEngine);
    }
}