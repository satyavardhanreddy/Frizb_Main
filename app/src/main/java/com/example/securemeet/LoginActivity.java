package com.example.securemeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PublicKey;

public class LoginActivity extends AppCompatActivity {
    TextView dont,sign, toggle_text,forgot;
    TextInputEditText email_login;
    LottieAnimationView lottieAnimationView;
    Dialog dialog1;
    String email,password;
    MediaPlayer mp3;
    FirebaseAuth auth;
    ProgressDialog dialog;
    Button login, password_btn;
    GridLayout mgridLayout,dgridLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        dont=findViewById(R.id.createone);
        forgot=findViewById(R.id.forgot_pass);
        mp3=MediaPlayer.create(LoginActivity.this,R.raw.click);
        lottieAnimationView = findViewById(R.id.loader1);
        lottieAnimationView.animate().setStartDelay(7000).start();
        sign=findViewById(R.id.no_acc);
        mgridLayout=findViewById(R.id.marvel_grid);
        dgridLayout=findViewById(R.id.dc_grid);
        password_btn=findViewById(R.id.pass_btn1);
        email_login=findViewById(R.id.user_mail);
        dialog1 = new Dialog(LoginActivity.this);
        dialog1.setContentView(R.layout.custom_dialog);
        login=findViewById(R.id.login_to_app);
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity( new Intent(LoginActivity.this,Dashboard.class));
            finish();
        }
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,forgotpassword.class));
            }
        });
        dialog= new ProgressDialog(this);
        dialog.setMessage("please wait...");
        dialog.setCancelable(false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));

        }
          email=email_login.getText().toString();

        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setCancelable(false);
        dialog1.getWindow().getAttributes().windowAnimations = R.style.animation;


        password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=email_login.getText().toString();
                if (email.isEmpty()){
                    email_login.setError("Invalid Email!");
                    password=null;
                    login.setEnabled(false);
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   //email_login.getText().clear();
                    password=null;
                   email_login.setError("Invalid Email.!");
              }else {
                    password=null;
                    dialog1.show();
                }
//                if (email.isEmpty()){
//                    email_login.setError("Mail Cannot Be Empty.!");
//                    login.setEnabled(false);
//                }
//                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                    email_login.getText().clear();
//                   // email_login.setError("Invalid Email.!");
//                }
//                else{
//                    toggle_text=dialog1.findViewById(R.id.header);
//                    toggle_text.setText("Enter your password sequence");
//                    dialog1.show();
//                    Toast.makeText(LoginActivity.this, "Password dialg box...", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        //Marvel Grid

        dialog1.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev11sat";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog1.findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev12arg";
                mp3.start();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev13van";
                mp3.start();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev21ram";
                mp3.start();
                //   dialog.dismiss();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                password=password+"lev22sat";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev23argat";
                mp3.start();
                // dialog.dismiss();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev31vanrg";
                mp3.start();
                //  dialog.dismiss();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                password=password+"lev32ramsat";
                mp3.start();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                password=password+"lev33argvan";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog1.findViewById(R.id.btnd11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"tya11cd";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog1.findViewById(R.id.btnd12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  dialog.dismiss();
                password=password+"ayg12cd";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   dialog.dismiss();
                password=password+"ina13cd";
                mp3.start();
              //  Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   dialog.dismiss();
                password=password+"mar21dc";
                mp3.start();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                password=password+"yta22dd";
                mp3.start();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                password=password+"rva23cc";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  dialog.dismiss();
                password=password+"nav31cdcd";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                password=password+"yga32ddcc";
                mp3.start();
               // Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog1.findViewById(R.id.btnd33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog.dismiss();
                password=password+"jra33cdd";
                mp3.start();
                //Toast.makeText(LoginActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

//proceed button
        dialog1.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "select password", Toast.LENGTH_SHORT).show();
                }
//                    dialog1.dismiss();
//                    Toast.makeText(LoginActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
                    if (!email.isEmpty() && !password.isEmpty()){
                        Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
                        login.setEnabled(true);
                        dialog1.dismiss();
                    }

                }

        });

        dialog1.findViewById(R.id.marvel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.findViewById(R.id.DC).setEnabled(true);
                dialog1.findViewById(R.id.marvel).setEnabled(false);
                //Toast.makeText(LoginActivity.this, "MARVEL UNIVERSE", Toast.LENGTH_SHORT).show();
                dialog1.findViewById(R.id.marvel_grid).setVisibility(View.VISIBLE);
                dialog1.findViewById(R.id.dc_grid).setVisibility(View.GONE);
            }
        });
        dialog1.findViewById(R.id.DC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.findViewById(R.id.DC).setEnabled(false);
                dialog1.findViewById(R.id.marvel).setEnabled(true);
                //Toast.makeText(LoginActivity.this, "DC COMICS", Toast.LENGTH_SHORT).show();
                dialog1.findViewById(R.id.marvel_grid).setVisibility(View.GONE);
                dialog1.findViewById(R.id.dc_grid).setVisibility(View.VISIBLE);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String email=email_login.getText().toString();

                if (email.isEmpty() && password.isEmpty()){
                    email_login.setError("Mail Cannot Be Empty.!");
                    login.setEnabled(false);
                    password=null;
                }
                else{
//                    Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
//                   Intent intent=new Intent(LoginActivity.this,Dashboard.class);
//                   startActivity(intent);
//                   finishAffinity();
                    dialog.show();

                    // Toast.makeText(login.this,"Logged in ...",Toast.LENGTH_SHORT).show();
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                checkIfEmailVerified();

                            } else {

                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                                password=null;
                            }
                        }

                        private void checkIfEmailVerified() {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                // user is verified, so you can finish this activity or send user to activity which you want.
                                //finish();
                                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                startActivity(intent);
                                finishAffinity();
                                Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            } else {
                                // email is not verified, so just prompt the message to the user and restart this activity.
                                // NOTE: don't forget to log out the user.
                                Toast.makeText(LoginActivity.this, "You Are Not a Verified User,Please Check Your Email Box For Verification Email..", Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                                password=null;

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            password=null;
                            Toast.makeText(LoginActivity.this, "Check your details and Retry!!", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                        }
                    });
                }
            }
        });
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