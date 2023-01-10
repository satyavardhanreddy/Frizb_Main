package com.example.securemeet;

import static com.example.securemeet.R.id.*;
import static com.example.securemeet.R.id.code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class forgotpassword extends AppCompatActivity {
    TextView code;
    EditText email,uremail,pcode;
    String m,password;
    Button submit1,submit2;
    FirebaseAuth auth;
    LinearLayout log;
ProgressDialog dialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        code = findViewById(R.id.code);
        uremail = findViewById(R.id.uremail);
        submit2 = findViewById(flbtn);
        log=findViewById(R.id.id1);
        pcode=findViewById(maccode);
        log.setVisibility(View.GONE);
        code.setTextIsSelectable(true);
        code.setVisibility(View.GONE);
        dialog= new ProgressDialog(this);
        dialog.setMessage("please wait...");
        dialog.setCancelable(false);
        email = findViewById(femail);
        submit1 = findViewById(fbtn);
        auth = FirebaseAuth.getInstance();
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m = email.getText().toString().trim();
                password=pcode.getText().toString();
                code.setText("CODE:         "+GetPassword(12));
                if (TextUtils.isEmpty(m)) {
                    email.setError("To Be Filled!!");
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                     return;
                }else{
                    code.setVisibility(View.VISIBLE);
                    log.setVisibility(View.VISIBLE);
                }

                auth.sendPasswordResetEmail(m)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgotpassword.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                   // startActivity(new Intent(forgotpassword.this, LoginActivity.class));
                                    //finish();
                                } else {
                                    Toast.makeText(forgotpassword.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String email=email_login.getText().toString();

                if (uremail.getText().toString().isEmpty() && password.isEmpty()){
                    uremail.setError("Mail Cannot Be Empty.!");
                  //  submit2.setEnabled(false);
                   // password=null;

                }
                else{
//                    Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
//                   Intent intent=new Intent(LoginActivity.this,Dashboard.class);
//                   startActivity(intent);
//                   finishAffinity();
                   // dialog.show();

                    // Toast.makeText(login.this,"Logged in ...",Toast.LENGTH_SHORT).show();
                    auth.signInWithEmailAndPassword(uremail.getText().toString(), pcode.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                checkIfEmailVerified();
                                UploadProfile(pcode.getText().toString());
                            } else {

                                dialog.dismiss();
                                Toast.makeText(forgotpassword.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                                password=null;
                            }
                        }

                        private void checkIfEmailVerified() {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                // user is verified, so you can finish this activity or send user to activity which you want.
                                //finish();
                                Intent intent = new Intent(forgotpassword.this, Dashboard.class);
                                startActivity(intent);
                                finishAffinity();
                                Toast.makeText(forgotpassword.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            } else {
                                // email is not verified, so just prompt the message to the user and restart this activity.
                                // NOTE: don't forget to log out the user.
                                Toast.makeText(forgotpassword.this, "You Are Not a Verified User,Please Check Your Email Box For Verification Email..", Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                                password=null;

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            password=null;
                            Toast.makeText(forgotpassword.this, "Check your details and Retry!!", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                        }
                    });
                }
            }
        });













    }

        public String GetPassword ( int length){
            char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+|<>?{}[],./?".toCharArray();
            StringBuilder stringBuilder = new StringBuilder();

            Random rand = new Random();

            for (int i = 0; i < length; i++) {
                char c = chars[rand.nextInt(chars.length)];
                stringBuilder.append(c);
            }

            return stringBuilder.toString();
        }


    private void UploadProfile(String password) {

       // FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("name").setValue(name);
        FirebaseDatabase.getInstance().getReference("Users").child(auth.getCurrentUser().getUid()).child("password").setValue(password);

        Toast.makeText(this, "Logging in Successfully!!", Toast.LENGTH_SHORT).show();
       // pd.dismiss();
        startActivity(new Intent(forgotpassword.this,Dashboard.class));
    }












}
