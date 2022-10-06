package com.example.securemeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Dashboard extends AppCompatActivity {
    TextView toggle_text, back, dashboard;
    Button create_btn, join_btn, share_btn,enter,create_meet, theme_toggle;
    TextInputLayout room_code;
    TextInputEditText room_layout_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
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

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room_code.setVisibility(View.VISIBLE);
                create_meet.setVisibility(View.VISIBLE);
                share_btn.setVisibility(View.VISIBLE);
                create_btn.setVisibility(View.GONE);
                join_btn.setVisibility(View.GONE);
                toggle_text.setText("Create an Instant Meet");
                room_code.setHint("Create Room Code");
                room_layout_text.getText().clear();
                back.setVisibility(View.VISIBLE);
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
                room_layout_text.getText().clear();
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
                toggle_text.setText("MEETING ROOM");
                room_layout_text.setText("Clear Text");
                back.setVisibility(View.INVISIBLE);
            }
        });

        create_meet.setOnClickListener(new View.OnClickListener() {
            String room_text=room_layout_text.getText().toString();
            String hint=toggle_text.getText().toString();
            @Override
            public void onClick(View view) {
                if (hint!="Create Room Code" && room_text.isEmpty()){
                    room_layout_text.setError("Room Code is required.!");
                }
                else {
                    Toast.makeText(Dashboard.this, "Meeting Created", Toast.LENGTH_SHORT).show();
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            String room_text=room_layout_text.getText().toString();
            String hint=toggle_text.getText().toString();
            @Override
            public void onClick(View view) {
                if ((hint!="Join a Meet") && room_text.isEmpty()){
                    room_layout_text.setError("Please enter Room Code.!");
                }
                else if (!(room_text.isEmpty())){
                    Toast.makeText(Dashboard.this, "Meeting Created", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Dashboard.this, "Meeting Created", Toast.LENGTH_SHORT).show();
                }
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            String room_text=room_layout_text.getText().toString();
            String hint=room_layout_text.getHint().toString();
            @Override
            public void onClick(View view) {
                if (hint!="Create Room Code" && room_text.isEmpty()){
                    room_layout_text.setError("Room Code can't be empty.!");
                }
                else {
                    Toast.makeText(Dashboard.this, "Share Eaabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        theme_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

    }

    public void onBackPressed() {
        //super.onBackPressed();
    }
}