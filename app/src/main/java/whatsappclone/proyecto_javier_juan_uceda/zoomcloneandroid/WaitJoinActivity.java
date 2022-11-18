package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import us.zoom.androidlib.utils.ZmAppUtils;
import us.zoom.androidlib.utils.ZmMimeTypeUtils;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.ZoomSDK;

public class WaitJoinActivity extends AppCompatActivity implements MeetingServiceListener {

    private TextView tvMeetingTopic, tvMeetingDate, tvMeetingTime, tvMeetingId;

    private String topic, date, time;

    private Button btnLeaveSession;

    private long meetingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_join);
        setUI();
    }

    private void setUI() {

        Intent intent = getIntent();

        btnLeaveSession = findViewById(R.id.button4);
        tvMeetingTopic = findViewById(R.id.tvMeetingTopic);
        tvMeetingDate = findViewById(R.id.tvMeetingDate);
        tvMeetingTime = findViewById(R.id.tvMeetingTime);
        tvMeetingId = findViewById(R.id.tvMeetingId);

        meetingId = intent.getLongExtra(ZmMimeTypeUtils.EXTRA_MEETING_ID, 0);

        topic = intent.getStringExtra(ZmMimeTypeUtils.EXTRA_TOPIC);
        date = intent.getStringExtra(ZmMimeTypeUtils.EXTRA_DATE);
        time = intent.getStringExtra(ZmMimeTypeUtils.EXTRA_TIME);

        if (topic != null){
            tvMeetingTopic.setText(topic);
        }

        if (date != null){
            tvMeetingDate.setText(date);
        }

        if (time != null){
            tvMeetingTime.setText(time);
        }

        if (meetingId > 0){
            tvMeetingId.setText(Math.toIntExact(meetingId));
        }

        btnLeaveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZoomSDK zoomSDK = ZoomSDK.getInstance();
                MeetingService meetingService = zoomSDK.getMeetingService();
                if (meetingService != null) {
                    meetingService.leaveCurrentMeeting(false);
                }
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        MeetingService meetingService = zoomSDK.getMeetingService();
        if (zoomSDK.isInitialized()) {
            meetingService.removeListener(this);
        }
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int i, int i1) {
        if (meetingStatus != MeetingStatus.MEETING_STATUS_WAITINGFORHOST){
            finish();
        }
    }
}