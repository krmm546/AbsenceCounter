package info.creidea.middleware;

import info.creidea.domain.AuthUser;
import info.creidea.repository.Loggable;
import spark.Request;
import spark.Response;

import java.util.Optional;

public record LoggerMiddleware(Loggable logger) implements Middleware {
    @Override
    public void before(Request req, Response res) throws Exception {
        final Optional<AuthUser> user = Optional.ofNullable(req.session().attribute("user"));
        final Optional<String> id = user.isEmpty() ? Optional.empty() : Optional.of(user.get().id());
        logger.log(id, req.pathInfo(), req.ip());
    }

    @Override
    public void after(Request req, Response res) {}
}
