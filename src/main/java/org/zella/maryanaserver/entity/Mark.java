package org.zella.maryanaserver.entity;

import com.googlecode.objectify.annotation.Entity;

public class Mark {
    int type;
    String text;

    public Mark(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public Mark() {

    }

}
