package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class MainActivity extends AppCompatActivity {


    private EditText etTextPersonName, etMeetingNumber, etMeetingPassword;
    private Button btnJoinMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();

    }

    private void setUI() {
        etMeetingNumber = findViewById(R.id.etMeetingNumber);
        etTextPersonName = findViewById(R.id.etTextPersonName);
        etMeetingPassword = findViewById(R.id.etMeetingPassword);
        btnJoinMeeting = findViewById(R.id.btnJoinMeeting);

        btnJoinMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meetingNumber = etMeetingNumber.getText().toString();
                String personName = etTextPersonName.getText().toString();
                String meetingPassword = etMeetingPassword.getText().toString();

                if (isNotEmptyStrings(meetingPassword, personName, meetingNumber)){
                    joinMeeting(MainActivity.this, meetingPassword, personName, meetingNumber);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid inputs", Toast.LENGTH_SHORT).show();

                    if (!isNotEmptyString(meetingNumber)){
                        Toast.makeText(MainActivity.this, "Invalid input meeting number", Toast.LENGTH_SHORT).show();
                    }
                    else if (!isNotEmptyString(personName)){
                        Toast.makeText(MainActivity.this, "Invalid input name person", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid input meeting password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        initializeZoom(this);
    }

    private void joinMeeting(Context mainActivity, String meetingPassword, String personName, String meetingNumber) {
        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
        JoinMeetingOptions options = new JoinMeetingOptions();
        JoinMeetingParams params = new JoinMeetingParams();
        params.displayName = personName;
        params.meetingNo = meetingNumber;
        params.password = meetingPassword;

        meetingService.joinMeetingWithParams(mainActivity, params, options);
    }

    private boolean isNotEmptyStrings(String... stringsToProve) {
        boolean isEmptyStrings = true;

        for (int counter = 0; counter < stringsToProve.length && isEmptyStrings; counter++) {
            isEmptyStrings = isNotEmptyString(stringsToProve[counter]);
        }

        return isEmptyStrings;
    }

    private boolean isNotEmptyString(String stringToProve) {
        return stringToProve.trim().length() > 0;
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