package com.play.eldarbabayev2.easylearning.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Utils {


    public static String uppercaseInput(Context context,
                                        String input,
                                        boolean showToast) {
        if (input.isEmpty()) {
            if (showToast)
                Utils.showToast(context,
                        "no input provided");
            return null;
        } else

            return input.toUpperCase(Locale.ENGLISH);
    }


    public static void showToast(Context context,
                                 String message) {
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {
        InputMethodManager mgr =
            (InputMethodManager) activity.getSystemService
            (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public static void setActivityResult(Activity activity,
                                         Uri pathToContent,
                                         String failureReason) {
        if (pathToContent == null)
            activity.setResult
                (Activity.RESULT_CANCELED,
                 new Intent("").putExtra("reason",
                                         failureReason));
        else
            activity.setResult(Activity.RESULT_OK,
                               new Intent("",
                                          pathToContent));
    }


    public static void setActivityResult(Activity activity,
                                         int resultCode,
                                         String failureReason) {
        if (resultCode == Activity.RESULT_CANCELED)
            activity.setResult(Activity.RESULT_CANCELED,
                 new Intent("").putExtra("reason",
                                         failureReason));
        else 
            activity.setResult(Activity.RESULT_OK);
    }

    private Utils() {
        throw new AssertionError();
    }

    public static List<Integer> getDisplayParameters(Activity activity) {
        List<Integer> list = new ArrayList<>();
        int display_height;
        int display_width;
        Point size = new Point();
        WindowManager w = activity.getWindowManager();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            w.getDefaultDisplay().getSize(size);
            display_width = size.x;
            display_height = size.y;
        } else {
            Display d = w.getDefaultDisplay();
            display_width = d.getWidth();
            display_height = d.getHeight();
        }
        list.add(0,display_height);
        list.add(1, display_width);
        return list;
    }

    public static List<Float> displayParametersDP(Activity activity) {
        List<Float> list = new ArrayList<>();
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float density  = activity.getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;
        list.add(0, dpHeight);
        list.add(1, dpWidth);
        return list;
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static String escapeEmailAddress(String email) {
        if (email == null) {
            return "";
        } else {
            email = email.toLowerCase();
            email = email.replace(".", ",");
            return email;
        }
    }
}
