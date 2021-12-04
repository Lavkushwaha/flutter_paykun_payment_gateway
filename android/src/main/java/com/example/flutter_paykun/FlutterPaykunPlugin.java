package com.example.flutter_paykun;

import android.app.Activity;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;



/** FlutterPaykunPlugin */
public class FlutterPaykunPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity


  private static final String CHANNEL = "flutter_paykun";
  private MethodChannel channel;
  private static FlutterPaykunPlugin instance;
  private FlutterPaykunDelegate delegate;

  private Activity activity;


  public static void registerWith(Registrar registrar) {
    if(instance == null) {
      instance = new FlutterPaykunPlugin();
    }

    if(registrar.activity() != null) {
      instance.onAttachedToEngine((FlutterPluginBinding) registrar.messenger());
      instance.onAttachedToActivity((ActivityPluginBinding) registrar.activity());
      registrar.addActivityResultListener(instance.getActivityResultListener());
    }

  }

  private PluginRegistry.ActivityResultListener getActivityResultListener() {
    return delegate;
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL);
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {

    if(activity == null || delegate == null) {
      result.error("no_activity", "esewa_pnp plugin requires a foreground activity.", null);
    }

    if (call.method.equals("startPayment")) {
//      result.success("Android " + android.os.Build.VERSION.RELEASE);

      delegate.startPay(call,result);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
    channel= null;

  }

  private void onAttachedToActivity(Activity activity) {
    this.activity = activity;
    delegate = new FlutterPaykunDelegate(activity);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding activityPluginBinding) {
    if(getActivityResultListener() != null) {
      activityPluginBinding.removeActivityResultListener(getActivityResultListener());
    }
    onAttachedToActivity(activityPluginBinding.getActivity());
    activityPluginBinding.addActivityResultListener(getActivityResultListener());
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    activity = null;
    delegate =null;
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding activityPluginBinding) {
    if(getActivityResultListener() != null) {
      activityPluginBinding.removeActivityResultListener(getActivityResultListener());
    }
    onAttachedToActivity((ActivityPluginBinding) activityPluginBinding.getActivity());
    activityPluginBinding.addActivityResultListener(getActivityResultListener());
  }

  @Override
  public void onDetachedFromActivity() {
    activity = null;
    delegate = null;
  }
}
