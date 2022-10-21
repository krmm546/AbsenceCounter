package info.creidea;

import info.creidea.controller.AbsenceViewController;
import info.creidea.controller.LoginController;
import info.creidea.database.DatabaseFactory;
import info.creidea.database.migration.AllMigration;
import spark.template.velocity.VelocityTemplateEngine;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        final var connection = DatabaseFactory.create();
        new AllMigration().prepare(connection);

        final var templateEngine = new VelocityTemplateEngine();
        final var login = new LoginController();
        final var absence = new AbsenceViewController();

        login.boot(templateEngine);
        absence.boot(templateEngine);
    }
}