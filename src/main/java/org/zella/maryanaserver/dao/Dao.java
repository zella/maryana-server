package org.zella.maryanaserver.dao;

import com.googlecode.objectify.*;
import org.zella.maryanaserver.entity.AppPackage;
import org.zella.maryanaserver.entity.AppPackageSuggestions;
import org.zella.maryanaserver.entity.Mark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Dao {

    public AppPackage getAppPackage(String app) {
        return ObjectifyService.run(() -> ofy().load().key(Key.create(AppPackage.class, app)).now());
    }

    public Collection<AppPackage> getAppPackages(List<String> apps) {
        return ObjectifyService.run(() -> {
            List<Key<AppPackage>> keys = apps.stream().map(a -> Key.create(AppPackage.class, a)).collect(Collectors.toList());
            Map<Key<AppPackage>, AppPackage> result = ofy().load().keys(keys);
            return result.values();
        });
    }

    public void addSuggestion(String app, Mark mark) {
        ObjectifyService.run((Work<Void>) () -> {
            AppPackageSuggestions source = ofy().load().key(Key.create(AppPackageSuggestions.class, app)).now();
            if (source == null) {
                source = new AppPackageSuggestions(app, new ArrayList<>());
            }
            source.addMark(mark);
            ofy().save().entities(source).now();
            return null;
        });

    }

    public void updateAppPackageMark(String app, Mark mark) {
        ObjectifyService.run((Work<Void>) () -> {
            AppPackage source = new AppPackage(app, mark);
            ofy().save().entities(source).now();
            return null;
        });
    }

}
