package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeZoom(this);
    }

    private void initializeZoom(Context context) {
        ZoomSDK sdk = ZoomSDK.getInstance();
        ZoomSDKInitParams params = new ZoomSDKInitParams();
        params.appKey = "gJei1Cadk8RptTiWuX4rvaOU8mye3TZo2DTS";
        params.appSecret = "soTPsVs8Nl0YbAe8ttRZ02sBq8uQ3pQwGNgd";
        params.domain = "zoom.us";
        params.enableLog = true;

        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
            @Override
            public void onZoomSDKInitializeResult(int i, int i1) {

            }

            @Override
            public void onZoomAuthIdentityExpired() {

            }
        };


        sdk.initialize(context, listener, params);

    }
}