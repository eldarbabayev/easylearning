package com.play.eldarbabayev2.easylearning;

import com.firebase.client.Firebase;
import com.firebase.client.Logger;

public class EasyLearningApplication extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);

    }

}
