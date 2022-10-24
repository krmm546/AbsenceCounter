package info.creidea.middleware;

import spark.Request;
import spark.Response;

public interface Middleware {
    void before(Request req, Response res) throws Exception;
    void after(Request req, Response res) throws Exception;

    public static final Middleware DEFAULT = new Middleware() {
        @Override
        public void before(Request req, Response res) {}

        @Override
        public void after(Request req, Response res) {}
    };
}
