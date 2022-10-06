package com.example.securemeet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextView dont,sign, toggle_text;
    TextInputEditText email_login;
    LottieAnimationView lottieAnimationView;
    Dialog dialog1;
    Button login, password_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        dont=findViewById(R.id.createone);
        lottieAnimationView = findViewById(R.id.loader1);
        lottieAnimationView.animate().setStartDelay(7000).start();
        sign=findViewById(R.id.no_acc);
        password_btn=findViewById(R.id.pass_btn1);
        email_login=findViewById(R.id.user_mail);
        dialog1 = new Dialog(LoginActivity.this);
        dialog1.setContentView(R.layout.custom_dialog);
        login=findViewById(R.id.login_to_app);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));

        }


        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setCancelable(false);
        dialog1.getWindow().getAttributes().windowAnimations = R.style.animation;

        password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_login.getText().toString();

                if (email.isEmpty()){
                    email_login.setError("Mail Cannot Be Empty.!");
                    login.setEnabled(false);
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    email_login.getText().clear();
                    email_login.setError("Invalid Email.!");
                }
                else{
                    toggle_text=dialog1.findViewById(R.id.header);
                    toggle_text.setText("Enter your password sequence");
                    dialog1.show();
                    Toast.makeText(LoginActivity.this, "Password dialg box...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_login.getText().toString();

                if (email.isEmpty()){
                    email_login.setError("Mail Cannot Be Empty.!");
                    login.setEnabled(false);
                }
                else{
                    startActivity(new Intent(LoginActivity.this, Dashboard.class));
                }
            }
        });

        dialog1.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
                Toast.makeText(LoginActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
                login.setEnabled(true);
            }
        });
        //Marvel Grid
        dialog1.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog1.findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog1.findViewById(R.id.bt11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog1.findViewById(R.id.bt12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.bt33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });


        dialog1.findViewById(R.id.DC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.findViewById(R.id.DC).setEnabled(false);
                dialog1.findViewById(R.id.marvel).setEnabled(true);
                Toast.makeText(LoginActivity.this, "DC COMICS", Toast.LENGTH_SHORT).show();
                dialog1.findViewById(R.id.marvel_grid).setVisibility(View.GONE);
                dialog1.findViewById(R.id.dc_grid).setVisibility(View.VISIBLE);
            }
        });
        dialog1.findViewById(R.id.marvel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.findViewById(R.id.DC).setEnabled(true);
                dialog1.findViewById(R.id.marvel).setEnabled(false);
                Toast.makeText(LoginActivity.this, "MARVEL UNIVERSE", Toast.LENGTH_SHORT).show();
                dialog1.findViewById(R.id.marvel_grid).setVisibility(View.VISIBLE);
                dialog1.findViewById(R.id.dc_grid).setVisibility(View.GONE);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(MainActivity.this,OnBoarding.class));
                //Intent intent = new Intent(Splash_Screen.this,Main_Activity.class);
               // startActivity(intent);
            }
        }, 3*1000); // wait for 4 seconds



        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });



    }
    public void onBackPressed() {
        //super.onBackPressed();
    }
}