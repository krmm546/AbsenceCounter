package info.creidea.middleware;

import spark.Request;
import spark.Response;

public record Middlewares(Middleware... middlewares) implements Middleware {
    @Override
    public void before(Request req, Response res) throws Exception {
        for (var middleware: middlewares) middleware.before(req, res);
    }

    @Override
    public void after(Request req, Response res) throws Exception {
        for (var middleware: middlewares) middleware.after(req, res);
    }
}
