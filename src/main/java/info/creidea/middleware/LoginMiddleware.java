package info.creidea.middleware;

import info.creidea.domain.AuthUser;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class LoginMiddleware implements Middleware {

    @Override
    public void before(Request req, Response res) {
        final Optional<AuthUser> user = Optional.ofNullable(req.session().attribute("user"));
        if (user.isEmpty()) res.redirect("/login");
    }

    @Override
    public void after(Request req, Response res) {}
}
