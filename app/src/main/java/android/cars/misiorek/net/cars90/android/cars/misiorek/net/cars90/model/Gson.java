package android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model;

import android.graphics.Bitmap;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;

/**
 * Created by marcin on 09.04.16.
 */
public class Gson {

    public static com.google.gson.Gson getGson() {
        return new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class) || f.getDeclaredClass().equals(Bitmap.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        })
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'")
        .create();
    }

}
