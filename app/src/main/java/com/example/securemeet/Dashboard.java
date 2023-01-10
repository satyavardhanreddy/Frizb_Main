package com.example.securemeet;

import static com.example.securemeet.R.id.fab_expand_menu_button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;
import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard extends AppCompatActivity {
    TextView toggle_text, back, dashboard;
    Button create_btn, join_btn, share_btn,enter,create_meet, theme_toggle;
    TextInputLayout room_code;
    String share,passcode,shurl;
    TextInputEditText room_layout_text;
//FloatingActionButton fabmenu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        FloatingActionsMenu fabmenu= (FloatingActionsMenu) findViewById(fab_expand_menu_button);
        toggle_text=findViewById(R.id.dashboard_title);
        create_btn=findViewById(R.id.create_button);
        join_btn=findViewById(R.id.join_button);
        share_btn=findViewById(R.id.share);
        room_code=findViewById(R.id.room_layout);
        enter=findViewById(R.id.enter_room);
        create_meet=findViewById(R.id.create_room);
        back=findViewById(R.id.back_text);
        theme_toggle=findViewById(R.id.theme);
        room_layout_text=findViewById(R.id.room_text);
        dashboard=findViewById(R.id.dashboard_title);
        back.setVisibility(View.GONE);
        toggle_text.setText("Create an Instant Meet");
         //passcode=room_layout_text.getText().toString();
        URL serverurl;
        try {
            serverurl = new URL("https://meet.jit.si");
            share = String.valueOf(serverurl);
            shurl = String.valueOf(serverurl);
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder().setServerURL(serverurl)
                  // .setToken("MyJWT")
                    .setFeatureFlag("toolbox.enabled", true)
                    .setFeatureFlag("recording.enabled", true)
                    .setFeatureFlag("filmstrip.enabled", false)
                    .setFeatureFlag("moderator.enabled",true)
                    .setFeatureFlag("call-integration.enable", true).setVideoMuted(true).setAudioMuted(true).build();

            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            finish();
        }

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room_code.setVisibility(View.VISIBLE);
                create_meet.setVisibility(View.VISIBLE);
                share_btn.setVisibility(View.VISIBLE);
                create_btn.setVisibility(View.GONE);
                join_btn.setVisibility(View.GONE);
                room_code.setHint("Create Room Code");
                toggle_text.setText("Create a Meet");
                //room_layout_text.getText().clear();
                back.setVisibility(View.VISIBLE);
            }
        });

fabmenu.findViewById(R.id.fab_profile).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Dashboard.this,Profile.class));
    }
});
        fabmenu.findViewById(R.id.fab_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Canvas.class));
            }
        });
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room_code.setVisibility(View.VISIBLE);
                enter.setVisibility(View.VISIBLE);
                create_btn.setVisibility(View.GONE);
                join_btn.setVisibility(View.GONE);
                toggle_text.setText("Join a Meet");
                room_code.setHint("Enter Room Code");
               // room_layout_text.getText().clear();
                back.setVisibility(View.VISIBLE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                room_code.setVisibility(View.GONE);
                create_meet.setVisibility(View.GONE);
                share_btn.setVisibility(View.GONE);
                room_code.setVisibility(View.GONE);
                enter.setVisibility(View.GONE);
                create_btn.setVisibility(View.VISIBLE);
                join_btn.setVisibility(View.VISIBLE);
                //room_layout_text.setText("Clear Text");
                toggle_text.setText("Create an Instant Meet");
                back.setVisibility(View.INVISIBLE);
                room_layout_text.setError(null);
            }
        });

        create_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passcode=room_layout_text.getText().toString();
               // toggle_text.setText("Create a Meet");
                if (passcode.isEmpty()){
                    room_layout_text.setError("Room Code is required.!");
                }
                else {
                    meet(passcode);
                    room_layout_text.setText("");
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passcode=room_layout_text.getText().toString();
                if (passcode.isEmpty()){
                    room_layout_text.setError("Please enter Room Code.!");
                }
                else if (!passcode.isEmpty()){
                    meet(passcode);
                    room_layout_text.setText("");
                }

            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passcode=room_layout_text.getText().toString();
                if (passcode.isEmpty()){
                    room_layout_text.setError("Room Code can't be empty.!");
                }
                else {
                    //URL sh=new URL("https://meet.jit.si");
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Click the following link to the join Secure Meet:\n\n" + shurl+"/"+passcode+"\n \nOR \n enter the Below Code to join from app:\n\n "+passcode+"\n***************************\n Just want to dial-in on your phone?:\n\n"+"Click this link to see the dial-in phone numbers for this meeting\n"+shurl+"/"+passcode+"/static/dialinfo.html?room="+passcode+"\n\n HAPPY Meeting!! :)");
                    sendIntent.setType("text/url/password");
                    Intent.createChooser(sendIntent, "Share Passcode via");
                    startActivity(sendIntent);
                    //Toast.makeText(Dashboard.this, "Share Eaabled", Toast.LENGTH_SHORT).show();
                }
            }
        });






//        theme_toggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            }
//        });

    }
    public void meet(String code){
        //String code = passcode.getText().toString();
        if (code.isEmpty()) {
            Toast.makeText(this, "Invalid Roomcode!", Toast.LENGTH_SHORT).show();
        }else {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setFeatureFlag("record.enable",true).setRoom(code).build();
            JitsiMeetActivity.launch(Dashboard.this, options);
        }

    }

//    public void onBackPressed() {
//
//        //super.onBackPressed();
//    }
}