package com.example.securemeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ncorti.slidetoact.SlideToActView;

import java.util.regex.*;

public class MainActivity extends AppCompatActivity {
    SlideToActView slide;
    TextView create,noacc;
    TextInputEditText mail, name;
    TextInputLayout name_signup, mail_signup;
    LottieAnimationView lottieAnimationView;
    Dialog dialog, dialog_confirm;
    Button passbtn,btn11;
    SlideToActView slider;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        lottieAnimationView = findViewById(R.id.loader);
        lottieAnimationView.animate().setStartDelay(7000).start();
        slide = findViewById(R.id.event_slider);
        create=findViewById(R.id.in);
        //ok_pass = findViewById(R.id.ok_pass);
        name=findViewById(R.id.user_name);
        mail=findViewById(R.id.user_mail);
        noacc=findViewById(R.id.acc);
        passbtn=findViewById(R.id.pbtn);
        btn11=findViewById(R.id.btn11);
        slider=findViewById(R.id.event_slider);
        name_signup=findViewById(R.id.name_signup);
        mail_signup=findViewById(R.id.email_signup);

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        dialog_confirm = new Dialog(MainActivity.this);
        dialog_confirm.setContentView(R.layout.custom_dialog_2);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog_confirm.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog_confirm.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_confirm.setCancelable(false);
        dialog_confirm.getWindow().getAttributes().windowAnimations = R.style.animation;


        passbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mail.getText().toString();
                String user_name1=name.getText().toString();

                if (user_name1.isEmpty()){
                    name.setError("Name Cannot Be Empty.!");

                }
                else if (email.isEmpty()){
                    mail.setError("Mail Cannot Be Empty.!");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mail.getText().clear();
                    mail.setError("Invalid Email.!");
                }
                else{
                    dialog.show();
                    slider.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Password dialg box...", Toast.LENGTH_SHORT).show();
                }
            }
        });



        dialog.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
                dialog_confirm.show();
            }
        });

        dialog_confirm.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_confirm.dismiss();
                Toast.makeText(MainActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
                slider.setVisibility(View.VISIBLE);
            }
        });

        dialog.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog.findViewById(R.id.bt11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.findViewById(R.id.bt12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.bt33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });


        //dialog confirm

        dialog_confirm.findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog_confirm.findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog_confirm.findViewById(R.id.bt_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog_confirm.findViewById(R.id.bt_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });



        if (!(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(mail.getText().toString()))){
            slider.setVisibility(View.VISIBLE);
        }


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
        noacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
        slide.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView view) {
                //log.append("\n" + getTime() + " onSlideComplete");
                Toast.makeText(MainActivity.this, "sliding completed...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });


        slide.setOnSlideUserFailedListener(new SlideToActView.OnSlideUserFailedListener() {
            @Override
            public void onSlideFailed(@NonNull SlideToActView view, boolean isOutside) {
                //log.append("\n" + getTime() + " onSlideUserFailed - Clicked outside: " + isOutside);
                Toast.makeText(MainActivity.this, "Listener failed...", Toast.LENGTH_SHORT).show();
            }
        });
        slide.setOnSlideResetListener(new SlideToActView.OnSlideResetListener() {
            @Override
            public void onSlideReset(@NonNull SlideToActView view) {
                //log.append("\n" + getTime() + " onSlideReset");
                Toast.makeText(MainActivity.this, "Reset....", Toast.LENGTH_SHORT).show();
            }
        });



        dialog.findViewById(R.id.DC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.findViewById(R.id.DC).setEnabled(false);
                dialog.findViewById(R.id.marvel).setEnabled(true);
                Toast.makeText(MainActivity.this, "DC COMICS", Toast.LENGTH_SHORT).show();
                dialog.findViewById(R.id.marvel_grid).setVisibility(View.GONE);
                dialog.findViewById(R.id.dc_grid).setVisibility(View.VISIBLE);
            }
        });
        dialog.findViewById(R.id.marvel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.findViewById(R.id.DC).setEnabled(true);
                dialog.findViewById(R.id.marvel).setEnabled(false);
                Toast.makeText(MainActivity.this, "MARVEL UNIVERSE", Toast.LENGTH_SHORT).show();
                dialog.findViewById(R.id.marvel_grid).setVisibility(View.VISIBLE);
                dialog.findViewById(R.id.dc_grid).setVisibility(View.GONE);
            }
        });

        dialog_confirm.findViewById(R.id.DC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_confirm.findViewById(R.id.DC).setEnabled(false);
                dialog_confirm.findViewById(R.id.marvel).setEnabled(true);
                Toast.makeText(MainActivity.this, "DC COMICS", Toast.LENGTH_SHORT).show();
                dialog_confirm.findViewById(R.id.marvel_grid).setVisibility(View.GONE);
                dialog_confirm.findViewById(R.id.dc_grid).setVisibility(View.VISIBLE);
            }
        });
        dialog_confirm.findViewById(R.id.marvel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_confirm.findViewById(R.id.DC).setEnabled(true);
                dialog_confirm.findViewById(R.id.marvel).setEnabled(false);
                Toast.makeText(MainActivity.this, "MARVEL UNIVERSE", Toast.LENGTH_SHORT).show();
                dialog_confirm.findViewById(R.id.marvel_grid).setVisibility(View.VISIBLE);
                dialog_confirm.findViewById(R.id.dc_grid).setVisibility(View.GONE);
            }
        });

    }
    public void onBackPressed() {
        //super.onBackPressed();
    }
}