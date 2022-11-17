package whatsappclone.proyecto_javier_juan_uceda.zoomcloneandroid

import android.content.Context
import us.zoom.sdk.MeetingError
import us.zoom.sdk.StartMeetingOptions
import us.zoom.sdk.StartMeetingParamsWithoutLogin
import us.zoom.sdk.ZoomSDK

class ZoomMeetingOwn {


    fun startMeeting(meetingNumber: String, context: Context, meetingServiceListener: us.zoom.sdk.MeetingServiceListener, zak: String) {
        val meetingService = ZoomSDK.getInstance().meetingService
        val startParams = StartMeetingParamsWithoutLogin().apply {
            zoomAccessToken = zak
            meetingNo = meetingNumber
        }
        meetingService.addListener(meetingServiceListener)
        val result = meetingService.startMeetingWithParams(context, startParams, StartMeetingOptions())
        if (result == MeetingError.MEETING_ERROR_SUCCESS) {
            // The SDK will attempt to join the meeting.
        }
    }
}