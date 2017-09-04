package com.example.root.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.dialogs.VKShareDialog;
import com.vk.sdk.dialogs.VKShareDialogBuilder;

import org.json.JSONObject;

import java.util.Iterator;

public class VKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.v("1", "SUCCESS");
            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void loginVK(View view) {
        VKSdk.login(this, "F");
        VKRequest request = new VKRequest("friends.get", VKParameters.from(VKApiConst.FIELDS, "sex,bdate,city"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                JSONObject ob = response.json;
                Iterator it = ob.keys();
                while (it.hasNext())
                    Log.v("1", it.next().toString());
//Do complete stuff
            }
            @Override
            public void onError(VKError error) {
                Log.v("FAI", "ERROR");
            }
            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                Log.v("FAI", "FAILES");
            }
        });
    }

    public void postVkData(View view) {
        VKShareDialogBuilder builder = new VKShareDialogBuilder();
        builder.setText("I created this post with VK Android SDK" +
                "\nSee additional information below\n#vksdk");
        builder.setAttachmentLink("VK Android SDK information",
                "https://vk.com/dev/android_sdk");
        builder.setShareDialogListener(new VKShareDialog.VKShareDialogListener() {
            @Override
            public void onVkShareComplete(int postId) {
                Log.v("ShareOK"," OK");
            }
            @Override
            public void onVkShareCancel() {
                Log.v("FAI", "CANCEL");
            }
            @Override
            public void onVkShareError(VKError error) {
                Log.v("ER", error.errorMessage.toString());
            }
        });
        builder.show(getFragmentManager(), "VK_SHARE_DIALOG");
    }
}
