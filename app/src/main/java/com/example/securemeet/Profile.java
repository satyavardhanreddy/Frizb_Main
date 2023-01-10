package com.example.securemeet;

import static com.example.securemeet.R.id.edit_layer;
import static com.example.securemeet.R.id.fab_expand_menu_button;
import static com.example.securemeet.R.id.fab_expand_menu_button_profile;
import static com.example.securemeet.R.id.profile_view_layout;
import static com.example.securemeet.R.id.uremail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.securemeet.Models.User;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ncorti.slidetoact.SlideToActView;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    LinearLayout editll, viewll,retrivell;
    Button logout, changepassword,set;
    TextInputEditText name;
    TextView tvname, tvemail,ttg;
    FirebaseAuth auth;
    EditText upcode;
    Dialog dialog, dialog_confirm,dialog_retrive;
    Button dontsave,savenam;
    String password,cpassword,inpass,pas,rpassword;
    SlideToActView slider;
    ImageButton editprofile;
    FirebaseUser firebaseUser;
    ProgressDialog pd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Profile");
        setContentView(R.layout.activity_profile);
        editll = findViewById(edit_layer);
        retrivell =findViewById(R.id.retrivell);
        retrivell.setVisibility(View.GONE);
        auth=FirebaseAuth.getInstance();
        savenam=findViewById(R.id.savename);
        ttg=findViewById(R.id.ttg);
        upcode=findViewById(R.id.oldpasswordedittext);
        set=findViewById(R.id.set);
        viewll = findViewById(profile_view_layout);
        FloatingActionsMenu fabmenu= (FloatingActionsMenu) findViewById(fab_expand_menu_button_profile);
        logout = findViewById(R.id.logout_btn);
        pd= new ProgressDialog(Profile.this);
        pd.setMessage(" Updating...,please wait!");
        pd.setCancelable(false);
        changepassword = findViewById(R.id.change_password_btn);
        name = findViewById(R.id.edit_name);
        tvname = findViewById(R.id.tv_name);
        dontsave = findViewById(R.id.no_change);
        tvemail = findViewById(R.id.tv_email);
        //retrivepass = findViewById(R.id.retrive_password_btn);
        slider = findViewById(R.id.save_slider);
        slider.setVisibility(View.GONE);
        editprofile = findViewById(R.id.edit_profile);

        dialog_retrive = new Dialog(Profile.this);
        dialog_retrive.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog_retrive.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog_retrive.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_retrive.setCancelable(false);
        //  dialog.setTitle("Choose old password ");
        dialog_retrive.getWindow().getAttributes().windowAnimations = R.style.animation;
        //TextView tv2=dialog_retrive.findViewById(R.id.header1);
        //tv2.setText("Choose old Password...");






        dialog = new Dialog(Profile.this);
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
      //  dialog.setTitle("Choose old password ");
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        TextView tv2=dialog.findViewById(R.id.header1);
        tv2.setText("Choose old Password...");






        dialog_confirm = new Dialog(Profile.this);
        dialog_confirm.setContentView(R.layout.custom_dialog_2);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog_confirm.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog_confirm.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_confirm.setCancelable(false);
        TextView tv1=dialog_confirm.findViewById(R.id.header2);
        tv1.setText("Choose new Password...");
        dialog_confirm.getWindow().getAttributes().windowAnimations = R.style.animation;

        fabmenu.findViewById(R.id.fab_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Dashboard.class));
            }
        });
        fabmenu.findViewById(R.id.fab_canvasp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Canvas.class));
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                User user = datasnapshot.getValue(User.class);
                tvname.setText(user.getName());
                tvemail.setText(user.getEmail());
                // bio.setText(user.getBio());
                //Glide.with(getApplicationContext()).load(user.getImageurl()).into(imageProfile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

ttg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (retrivell.getVisibility()==View.GONE){
            retrivell.setVisibility(View.VISIBLE);
        }else{
            retrivell.setVisibility(View.GONE);
        }
    }
});

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewll.setVisibility(View.GONE);
                editll.setVisibility(View.VISIBLE);
                retrivell.setVisibility(View.GONE);
         reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        User user = datasnapshot.getValue(User.class);
                        name.setText(user.getName());
                        inpass=user.getPassword();
                        pas= user.getPassword();
                        Toast.makeText(Profile.this, pas, Toast.LENGTH_SHORT).show();
                        //temp=user.getPassword();
                        //tvemail.setText(user.getEmail());
                        // bio.setText(user.getBio());
                        //Glide.with(getApplicationContext()).load(user.getImageurl()).into(imageProfile);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Profile.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });










//




        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!password.isEmpty()){
                    password=null;
                    dialog.setTitle("enter old password");
                dialog.show();
            }
        });

        dialog_retrive.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev11sat";

                //Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog_retrive.findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev12arg";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev13van";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev21ram";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev22sat";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev23argat";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev31vanrg";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev32ramsat";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"lev33argvan";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog_retrive.findViewById(R.id.btnd11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();
                rpassword=rpassword+"tya11cd";
            }
        });

        dialog_retrive.findViewById(R.id.btnd12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
                rpassword=rpassword+"ayg12cd";
            }
        });

        dialog_retrive.findViewById(R.id.btnd13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"ina13cd";
                //  Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btnd21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"mar21dc";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btnd22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"yta22dd";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btnd23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"rva23cc";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btnd31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"nav31cdcd";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btnd32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"yga32ddcc";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_retrive.findViewById(R.id.btnd33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassword=rpassword+"jra33cdd";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        dialog_retrive.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               UpdatePassword(tvname.getText().toString(),upcode.getText().toString(),rpassword);
               dialog_retrive.dismiss();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upcode.getText().toString().isEmpty()){
                    upcode.setError("Can't be empty!");
                }else if(upcode.getText().toString().equals(pas)){
                    dialog_retrive.setTitle("Choose graphical password");
                    dialog_retrive.show();
                }else{
                    Toast.makeText(Profile.this, "password not matched..", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Profile.this, "inpass .."+pas, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Profile.this, "upcode "+upcode.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

//*******************************************************************************************************************************************
        dialog.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev11sat";
                //Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev12arg";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev13van";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev21ram";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev22sat";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev23argat";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev31vanrg";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev32ramsat";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"lev33argvan";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog.findViewById(R.id.btnd11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();
                password=password+"tya11cd";
            }
        });

        dialog.findViewById(R.id.btnd12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
                password=password+"ayg12cd";
            }
        });

        dialog.findViewById(R.id.btnd13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"ina13cd";
                //  Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"mar21dc";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"yta22dd";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"rva23cc";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"nav31cdcd";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"yga32ddcc";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.findViewById(R.id.btnd33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password+"jra33cdd";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
//***************************************************************************************************************************************************

        //dialog confirm

        dialog_confirm.findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev11sat";
                // Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();

            }
        });
        dialog_confirm.findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev12arg";
                //Toast.makeText(P.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev13van";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev21ram";
               // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev22sat";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev23argat";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev31vanrg";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev32ramsat";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.btn_33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"lev33argvan";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });

        //DC GRID
        dialog_confirm.findViewById(R.id.bt_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, "btn11 dialg box...", Toast.LENGTH_SHORT).show();
                cpassword=cpassword+"tya11cd";

            }
        });
        dialog_confirm.findViewById(R.id.bt_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"ayg12cd";

                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"ina13cd";

                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"mar21dc";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"yta22dd";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"rva23cc";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"nav31cdcd";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"yga32ddcc";
                //Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_confirm.findViewById(R.id.bt_33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpassword=cpassword+"jra33cdd";
                // Toast.makeText(MainActivity.this, "btn12 dialg box...", Toast.LENGTH_SHORT).show();
            }
        });
//***********************************************************************************************************************************************

        dialog.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //final String[] inpass = new String[1];
//                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//
//              //  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    reference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            User user = snapshot.getValue(User.class);
//                           inpass[0] =user.getPassword();
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                Toast.makeText(MainActivity.this, "dismiss dialg box...", Toast.LENGTH_SHORT).show();
//                if (password.equals(inpass[0])){
//                    Toast.makeText(Profile.this, "matched...", Toast.LENGTH_SHORT).show();
//                }else {
//
//                }

                if(inpass.equals(password)){
                    dialog_confirm.show();
                }else {
                    Toast.makeText(Profile.this, "not matched", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog_confirm.findViewById(R.id.ok_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!password.isEmpty()){
                    Toast.makeText(Profile.this, "updating password", Toast.LENGTH_SHORT).show();
                    dialog_confirm.dismiss();
                    slider.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(Profile.this, "password can't be empty..", Toast.LENGTH_SHORT).show();
                    dialog_confirm.dismiss();
                    slider.setVisibility(View.GONE);
                }

//
//                if (password.equals(cpassword)){
//                    dialog_confirm.dismiss();
//                    if (!password.isEmpty()){
//                        slider.setVisibility(View.VISIBLE);
//                    }
//                    Toast.makeText(Profile.this, "Passwords Matched...", Toast.LENGTH_SHORT).show();
//                }else{
//                    dialog_confirm.dismiss();
//
//                    password="";
//                    cpassword="";
//                    Toast.makeText(Profile.this, "Passwords not matched,please Re-enter...", Toast.LENGTH_SHORT).show();
//                }

                slider.setVisibility(View.VISIBLE);



            }
        });

//                if (password.equals(cpassword)){
//                    dialog_confirm.dismiss();
//                    if (!password.isEmpty()){
//                        slider.setVisibility(View.VISIBLE);
//                    }
//                    Toast.makeText(Profile.this, "Passwords Matched...", Toast.LENGTH_SHORT).show();
//                }else{
//                    dialog_confirm.dismiss();
//
//                    password="";
//                    cpassword="";
//                    Toast.makeText(Profile.this, "Passwords not matched,please Re-enter...", Toast.LENGTH_SHORT).show();
//                }

        slider.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView view) {
                String user_name1=name.getText().toString();
               // if (password.equals(cpassword)  && (!user_name1.isEmpty()) && (!password.isEmpty())){
                   // Toast.makeText(Profile.this, "Password Matches...", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    pd.show();
                    UpdatePassword(user_name1,inpass,cpassword);
                   //UploadProfile(user_name1,password);

                   // mail.setText("");
                   // name.setText("");
                    viewll.setVisibility(View.VISIBLE);
                    editll.setVisibility(View.GONE);
                    slider.setVisibility(View.GONE);
                password="";
                }

           // }
        });


//

dontsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
     editll.setVisibility(View.GONE);
     viewll.setVisibility(View.VISIBLE);
    }
});
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        auth.signOut();
        Toast.makeText(Profile.this, "Signing off...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Profile.this,LoginActivity.class));
        finishAffinity();

    }
});

    }
    private void UpdatePassword(String name,String oldpass,String newpass){
      //  final String[] name = new String[1];
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), oldpass);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //Log.d(TAG, "Password updated");
                                        Toast.makeText(Profile.this, "Password Updating...", Toast.LENGTH_SHORT).show();
                                        UploadProfile(name,newpass);
                                    } else {
                                        //Log.d(TAG, "Error password not updated")
                                        Toast.makeText(Profile.this, "Unexpected error,couldn't make changes...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                           // Log.d(TAG, "Error auth failed")
                            Toast.makeText(Profile.this, "Task unsuccessful...", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            startActivity(new Intent(Profile.this,Dashboard.class));
                        }
                    }
                });
    }

    private void UploadProfile(String name, String password) {
          //  DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("name").setValue(name);
        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("password").setValue(password);

//        HashMap<String,Object> hashMap=new HashMap<>();
//        hashMap.put("name",name);
//        hashMap.put("password",password);
//            //hashMap.put("bio",bio);
//        reference.updateChildren(hashMap);
            Toast.makeText(this, "Updated Successfully!!", Toast.LENGTH_SHORT).show();
          pd.dismiss();
        startActivity(new Intent(Profile.this,Dashboard.class));
       }

    @Override
    public void onBackPressed() {
     //super.onBackPressed();
       // startActivity(new Intent(Profile.this,Dashboard.class));
    }
}

