package com.play.eldarbabayev2.easylearning;

import com.firebase.client.Firebase;
import com.firebase.client.Logger;
import com.play.eldarbabayev2.easylearning.common.Utils;

public class EasyLearningApplication extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);

    }

}
