package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Log;

import org.jetbrains.annotations.NotNull;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingError;
import us.zoom.sdk.MeetingParameter;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.StartMeetingOptions;
import us.zoom.sdk.StartMeetingParams;
import us.zoom.sdk.StartMeetingParams4NormalUser;
import us.zoom.sdk.StartMeetingParamsWithoutLogin;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;


public class MainActivity extends AppCompatActivity implements MeetingServiceListener {


//    private static final String ZAK = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6IldhTVFYSzRCVHFHMEJtTWZRRjNBMVEiLCJleHAiOjE2NjkyODA2NDEsImlhdCI6MTY2ODY3NTg0N30.hw6X-RLdIyExve_pXJg5IrTS6l9NMPPil71MtZKHh2Q";
private static final String ZAK = "pF6iqSV2STKQPLyqdXzGCw";


    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText etTextPersonName, etMeetingNumber, etMeetingPassword, etEmail, etPassword;
    private Button btnJoinMeeting, btnLogIn;

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

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnJoinMeeting = findViewById(R.id.btnJoinMeeting);
        btnLogIn = findViewById(R.id.btnSignIn);

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

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isValidEditText(etEmail) && isValidEditText(etPassword)){
                        String email = etEmail.getText().toString();
                        String password = etPassword.getText().toString();

                        login();
                    }
                } catch (Exception e) {

                }
            }
        });

        initializeZoom(this);
    }

    private void login() {
        //int result = getInstance().loginWithZoom(email, password);
        startMeeting(etMeetingNumber.getText().toString(),MainActivity.this, this,ZAK);

    }

    public void startMeeting(@NotNull String meetingNumber, @NotNull Context context, @NotNull MeetingServiceListener meetingServiceListener, @NotNull String zak) {
        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
        StartMeetingParamsWithoutLogin startMeetingParamsWithoutLogin = new StartMeetingParamsWithoutLogin();
        startMeetingParamsWithoutLogin.zoomAccessToken = zak;
        startMeetingParamsWithoutLogin.meetingNo = meetingNumber;
        meetingService.addListener(meetingServiceListener);
        int result = meetingService.startMeetingWithParams(context, startMeetingParamsWithoutLogin, new StartMeetingOptions());

        if(result == MeetingError.MEETING_ERROR_SUCCESS){
            Log.i(TAG, "MeetingError.MEETING_ERROR_SUCCESS");
        }
        else {
            Home();

            Log.i(TAG, "MeetingError.MEETING_ERROR_SUCCESS");
        }
    }

    private boolean isValidEditText(EditText editText) throws Exception {
        boolean isValid = editText != null && editText.getText() != null && !editText.getText().toString().trim().isEmpty();

        if (!isValid) {
            if (editText == null){
                throw new Exception("Is null editText");
            }
            else if (editText.getText() == null){
                throw new Exception("Text editText is null");
            }
            else {
                throw new Exception("Text editText is empty");
            }
        }


        return isValid;
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

    private void Home() {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int i, int i1) {

    }

    @Override
    public void onMeetingParameterNotification(MeetingParameter meetingParameter) {

    }
}