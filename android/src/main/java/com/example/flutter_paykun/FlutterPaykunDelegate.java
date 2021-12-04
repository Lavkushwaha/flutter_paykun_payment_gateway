package com.example.flutter_paykun;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.paykun.sdk.PaykunApiCall;
import com.paykun.sdk.eventbus.Events;
import com.paykun.sdk.eventbus.GlobalBus;
import com.paykun.sdk.helper.PaykunHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class FlutterPaykunDelegate implements  PluginRegistry.ActivityResultListener {
    private Activity activity;
    private static final String TAG = "PAYKUN";

    private String merchantIdLive="710730426562225"; // merchant id for live mode application id  = com.paykunsandbox.live
    private String accessTokenLive="6E51763DF010B981F214533F294D2A0C"; // access token for live mode application id  = com.paykunsandbox.live

    private String merchantIdSandbox="895775588965854"; // merchant id for sandbox mode application id = com.paykun.sandbox
    private String accessTokenSandbox="74B92BC0C039D9AD7D02FA4993253E8B"; // access token for sandbox application id = com.paykun.sandbox

    private String customerName="Bhavik",customerPhone="8256400020",customerEmail="bhavik.makvana@paykun.com";
    private String productName="Paykun Test Product",orderNo="7895812590123",amount="10";



    FlutterPaykunDelegate(Activity activity){
        this.activity = activity;
//        GlobalBus.getBus().register(this);
    }





    void startPay(MethodCall call, MethodChannel.Result result){
        Log.d(TAG, "startPay: Started");
        JSONObject object = new JSONObject();
        try {
            object.put("merchant_id",merchantIdSandbox);
            object.put("access_token",accessTokenSandbox);
            object.put("customer_name",customerName);
            object.put("customer_email",customerEmail);
            object.put("customer_phone",customerPhone);
            object.put("product_name",productName);
            object.put("order_no",System.currentTimeMillis()); // order no. should have 10 to 30 character in numeric format
            object.put("amount",amount);  // minimum amount should be 10
            object.put("isLive",false); // need to send false if you are in sandbox mode
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new PaykunApiCall.Builder(activity).sendJsonObject(object); // Paykun api to initialize your payment and send info.

        EventBus.getDefault().post(object);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getResults(Events.PaymentMessage message) {
        if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SUCCESS)){
            // do your stuff here
            // message.getTransactionId() will return your failed or succeed transaction id
            /* if you want to get your transaction detail call message.getTransactionDetail()
             *  getTransactionDetail return all the field from server and you can use it here as per your need
             *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId */
            if(!TextUtils.isEmpty(message.getTransactionId())) {
                Toast.makeText(activity, "Your Transaction is succeed with transaction id : "+message.getTransactionId() , Toast.LENGTH_SHORT).show();
                Log.v(" order id "," getting order id value : "+message.getTransactionDetail().order.orderId);
            }
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_FAILED)){
            // do your stuff here
            Toast.makeText(activity,"Your Transaction is failed",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SERVER_ISSUE)){
            // do your stuff here
            Toast.makeText(activity,PaykunHelper.MESSAGE_SERVER_ISSUE,Toast.LENGTH_SHORT).show();
        }else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING)){
            // do your stuff here
            Toast.makeText(activity,"Access Token missing",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_MERCHANT_ID_MISSING)){
            // do your stuff here
            Toast.makeText(activity,"Merchant Id is missing",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_INVALID_REQUEST)){
            Toast.makeText(activity,"Invalid Request",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE)){
            Toast.makeText(activity,"Network is not available",Toast.LENGTH_SHORT).show();
        }
    }






    @Override
    public boolean onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        return false;
    }


}
