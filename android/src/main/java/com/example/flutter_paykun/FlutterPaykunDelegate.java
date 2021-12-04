package com.example.flutter_paykun;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class FlutterPaykunDelegate implements  PluginRegistry.ActivityResultListener {
    private Activity activity;
    private static final String TAG = "PAYKUN";


    FlutterPaykunDelegate(Activity activity){
        this.activity = activity;
    }



    void startPay(MethodCall call, MethodChannel.Result result){
        Log.d(TAG, "startPay: Started");

    }


    @Override
    public boolean onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        return false;
    }


}
