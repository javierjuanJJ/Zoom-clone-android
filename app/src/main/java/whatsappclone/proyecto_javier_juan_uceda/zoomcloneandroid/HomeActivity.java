package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import us.zoom.sdk.MeetingService;
import us.zoom.sdk.StartMeetingOptions;
import us.zoom.sdk.ZoomSDK;

public class HomeActivity extends AppCompatActivity {

    private Button btnStartIntentMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUI();
    }

    private void setUI() {
        btnStartIntentMeeting = findViewById(R.id.btnStartIntentMeeting);

        btnStartIntentMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstantMeeting();
            }
        });

    }

    private void InstantMeeting() {
        ZoomSDK sdk = ZoomSDK.getInstance();

        MeetingService meetingService = sdk.getMeetingService();
        StartMeetingOptions startMeetingOptions = new StartMeetingOptions();


        int instantMeeting = meetingService.startInstantMeeting(this, startMeetingOptions);
        Log.i("idInstantMeeting", String.valueOf(instantMeeting));
    }

}