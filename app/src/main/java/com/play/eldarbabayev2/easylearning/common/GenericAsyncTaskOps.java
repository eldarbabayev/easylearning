package com.play.eldarbabayev2.easylearning.common;


public interface GenericAsyncTaskOps<Params, Progress, Result> {

    @SuppressWarnings("unchecked")
    Result doInBackground(Params... params);

    void onPostExecute(Result result);
}

