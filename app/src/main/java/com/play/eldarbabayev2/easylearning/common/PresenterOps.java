package com.play.eldarbabayev2.easylearning.common;


public interface PresenterOps<RequiredViewOps> {

    void onCreate(RequiredViewOps view);


    void onConfigurationChange(RequiredViewOps view);


    void onDestroy(boolean isChangingConfigurations);
}
