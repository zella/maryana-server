package org.zella.maryanaserver.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Cache
public class AppPackageSuggestions {


    public AppPackageSuggestions(String app, List<Mark> marks) {
        this.app = app;
        this.marks = marks;
    }

    public AppPackageSuggestions() {
    }


    @Id
    String app;
    List<Mark> marks;

    public void addMark(Mark m) {
        if (marks == null) marks = new ArrayList<>();
        marks.add(m);
    }
}
