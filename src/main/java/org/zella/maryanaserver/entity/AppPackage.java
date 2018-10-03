package org.zella.maryanaserver.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Cache
public class AppPackage {
    @Id
    String app;
    public Mark mark;

    public AppPackage(String app, Mark mark) {
        this.app = app;
        this.mark = mark;
    }

    public AppPackage() {

    }
}
