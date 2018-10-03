package org.zella.maryanaserver;

import com.googlecode.objectify.ObjectifyService;
import org.zella.maryanaserver.dao.Dao;
import org.zella.maryanaserver.entity.AppPackage;
import org.zella.maryanaserver.entity.AppPackageSuggestions;
import org.zella.maryanaserver.server.MaryanaServer;

public class Runner {
    public static void main(String[] args) {
        ObjectifyService.init();
        ObjectifyService.register(AppPackage.class);
        ObjectifyService.register(AppPackageSuggestions.class);

        Dao dao = new Dao();

        String port = System.getenv("HTTP_PORT");
        if (port == null) port = "9999";

        MaryanaServer server = new MaryanaServer(dao, Integer.parseInt(port));
        server.start();
    }
}
