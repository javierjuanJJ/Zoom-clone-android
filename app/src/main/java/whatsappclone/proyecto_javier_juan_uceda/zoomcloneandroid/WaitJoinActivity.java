package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import us.zoom.libtools.utils.*;
import us.zoom.net.CertVerifyStatusAndroid;
import us.zoom.sdk.InMeetingService;
import us.zoom.sdk.MeetingActivity;
import us.zoom.sdk.MeetingInviteMenuItem;
import us.zoom.sdk.MeetingItem;
import us.zoom.sdk.MeetingOptions;
import us.zoom.sdk.MeetingParameter;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.ZoomMeetingChatStatus;
import us.zoom.sdk.ZoomSDK;
import us.zoom.libtools.utils.ZmMimeTypeUtils.b;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKRawDataType;

import com.zipow.cmmlib.AppUtil;
import com.zipow.videobox.util.*;
public class WaitJoinActivity extends AppCompatActivity implements MeetingServiceListener {

    private Button btnLeaveSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_join);
        setUI();
    }

    private void setUI() {
        btnLeaveSession = findViewById(R.id.button4);

        Intent intent = getIntent();

        MeetingService meetingService1 = ZoomSDK.getInstance().getMeetingService();

        meetingService1.getCurrentRtcMeetingID();
        meetingService1.getCurrentRtcMeetingNumber();



        btnLeaveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick2();
            }
        });

        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        MeetingService meetingService = zoomSDK.getMeetingService();
        if (meetingService != null) {
            meetingService.addListener(this);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClick2();
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

    private void onClick2(){
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        MeetingService meetingService = zoomSDK.getMeetingService();
        if (meetingService != null) {
            meetingService.leaveCurrentMeeting(false);
        }
        finish();
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int i, int i1) {
        if (meetingStatus != MeetingStatus.MEETING_STATUS_WAITINGFORHOST){
            finish();
        }
    }

    @Override
    public void onMeetingParameterNotification(MeetingParameter meetingParameter) {

    }
}