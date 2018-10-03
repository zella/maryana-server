package org.zella.maryanaserver.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.zella.maryanaserver.dao.Dao;
import org.zella.maryanaserver.entity.AppPackage;
import org.zella.maryanaserver.entity.Mark;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MaryanaServer {

    private final Dao dao;
    private final int port;

    public MaryanaServer(Dao dao, int port) {
        this.dao = dao;
        this.port = port;
    }

    public void start() {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.get("/status").handler(ctx -> ctx.response().end("alive"));
        router.post().handler(BodyHandler.create());
        router.get("/apps").blockingHandler(ctx -> {
            List<String> apps = ctx.request().params().getAll("app");
            Collection<AppPackage> appPackages = dao.getAppPackages(apps);
            ctx.response().end(Json.encode(appPackages));
        });
        router.get("/app").blockingHandler(ctx -> {
            String app = ctx.request().getParam("app");
            System.out.println(app);
            AppPackage appPackage = dao.getAppPackage(app);
            ctx.response().end(Json.encode(appPackage.mark));
        });
        router.post("/app").blockingHandler(ctx -> {
            String app = ctx.request().getFormAttribute("app");
            String mark = ctx.request().getFormAttribute("mark");
            String type = ctx.request().getFormAttribute("type");
            dao.updateAppPackageMark(app, new Mark(Integer.parseInt(type), mark));
            ctx.response().end();
        });
        router.post("/suggestion").blockingHandler(ctx -> {
            String app = ctx.request().getFormAttribute("app");
            String mark = ctx.request().getFormAttribute("mark");
            String type = ctx.request().getFormAttribute("type");
            dao.addSuggestion(app, new Mark(Integer.parseInt(type), mark));
            ctx.response().end();
        });

        server.requestHandler(router::accept);

        server.listen(port);
    }

}
