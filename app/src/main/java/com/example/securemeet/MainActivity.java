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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ncorti.slidetoact.SlideToActView;

import java.util.HashMap;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {
    SlideToActView slide;
    TextView create,noacc;
    TextInputEditText mail, name;
    TextInputLayout name_signup, mail_signup;
    FirebaseAuth auth;
    MediaPlayer mp3;
   // FirebaseFirestore database;
    ProgressDialog pd;

    
   DatabaseReference reference;
    LottieAnimationView lottieAnimationView;
    Dialog dialog, dialog_confirm;
    Button passbtn,btn11;
    String password,cpassword;
    SlideToActView slider;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        pd= new ProgressDialog(MainActivity.this);
        pd.setMessage("Signing up");
        pd.setCancelable(false);
        lottieAnimationView = findViewById(R.id.loader);
        lottieAnimationView.animate().setStartDelay(7000).start();
        slide = findViewById(R.id.event_slider);
        create=findViewById(R.id.in);
        //ok_pass = findViewById(R.id.ok_pass);
        mp3=MediaPlayer.create(MainActivity.this,R.raw.click);
        auth=FirebaseAuth.getInstance();
        name=findViewById(R.id.user_name);
        mail=findViewById(R.id.user_mail);
        noacc=findViewById(R.id.acc);
        passbtn=findViewById(R.id.pbtn);
        //btn11=findViewById(R.id.btn11);
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




        dialog.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev11sat";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev12arg";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev13van";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev21ram";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev22sat";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev23argat";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev31vanrg";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev32ramsat";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev33argvan";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog.findViewById(R.id.btnd11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();
                password=password+"tya11cd";
                mp3.start();
            }
        });
        dialog.findViewById(R.id.btnd12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
                password=password+"ayg12cd";
                mp3.start();
            }
        });
        dialog.findViewById(R.id.btnd13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"ina13cd";
                mp3.start();
              //  Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"mar21dc";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"yta22dd";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"rva23cc";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"nav31cdcd";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"yga32ddcc";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"jra33cdd";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
//***************************************************************************************************************************************************

        //dialog confirm

        dialog_confirm.findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev11sat";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog_confirm.findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev12arg";
                mp3.start();
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev13van";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev21ram";
                mp3.start();
                Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev22sat";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev23argat";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev31vanrg";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev32ramsat";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev33argvan";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog_confirm.findViewById(R.id.bt_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();
                cpassword=cpassword+"tya11cd";
                mp3.start();
            }
        });
        dialog_confirm.findViewById(R.id.bt_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"ayg12cd";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"ina13cd";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"mar21dc";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"yta22dd";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"rva23cc";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"nav31cdcd";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"yga32ddcc";
                mp3.start();
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"jra33cdd";
                mp3.start();
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
//***********************************************************************************************************************************************

        dialog.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                Toast.makeText(MainActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
               dialog_confirm.show();
            }
        });

        dialog_confirm.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
//
                if (password.equals(cpassword)){
                    dialog_confirm.dismiss();
                    slider.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Passwords Matched...", Toast.LENGTH_SHORT).show();
                }else{
                    dialog_confirm.dismiss();
                    password="";
                    cpassword="";
                    Toast.makeText(MainActivity.this, "Passwords not matched,please Re-enter...", Toast.LENGTH_SHORT).show();
                }
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
                String email=mail.getText().toString();
                String user_name1=name.getText().toString();
                if (password.equals(cpassword) && (!email.isEmpty()) && (!user_name1.isEmpty()) && (!password.isEmpty())){
                    Toast.makeText(MainActivity.this, "Password Matches...", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    pd.show();
                    Register(user_name1,email,password);
                    password="";
                    mail.setText("");
                    name.setText("");
                }

            }
        });


//        slide.setOnSlideUserFailedListener(new SlideToActView.OnSlideUserFailedListener() {
//            @Override
//            public void onSlideFailed(@NonNull SlideToActView view, boolean isOutside) {
//                //log.append("\n" + getTime() + " onSlideUserFailed - Clicked outside: " + isOutside);
//                Toast.makeText(MainActivity.this, "Listener failed...", Toast.LENGTH_SHORT).show();
//            }
//        });
//        slide.setOnSlideResetListener(new SlideToActView.OnSlideResetListener() {
//            @Override
//            public void onSlideReset(@NonNull SlideToActView view) {
//                //log.append("\n" + getTime() + " onSlideReset");
//                Toast.makeText(MainActivity.this, "Reset....", Toast.LENGTH_SHORT).show();
//            }
//        });



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
    public void Register(String username,String email,String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String uid=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                    HashMap<String ,Object> hashMap=new HashMap<>();
                    hashMap.put("id",uid);
                    hashMap.put("email",email);
                    hashMap.put("name",username);
                    hashMap.put("password",password);
                    hashMap.put("bio"," ");
                    hashMap.put("imageurl","https://firebasestorage.googleapis.com/v0/b/chatgram-cdbe8.appspot.com/o/place.jpg?alt=media&token=7c5411fb-e7e5-4ceb-b001-b4f67652509f");
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            pd.dismiss();
                                            Toast.makeText(MainActivity.this, "please check email for verification.", Toast.LENGTH_LONG).show();
                                            auth.signOut();
                                            startActivity(new Intent(MainActivity.this,LoginActivity.class));

                                            // loadingBar.dismiss();
                                        } else {

                                            pd.dismiss();
                                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            }
                        }
                    });

                }else {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this,  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}