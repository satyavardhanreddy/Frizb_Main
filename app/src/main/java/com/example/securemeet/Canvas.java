package com.example.securemeet;

import static com.example.securemeet.R.id.eraserbnt;
import static com.example.securemeet.R.id.txtpensize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Canvas extends AppCompatActivity {
SignatureView snview;
SeekBar bar;
TextView pensize;
ImageButton eraser,save,color;
int defaultcolor;
   // @SuppressLint("MissingInflatedId")
    public static String filenmae;
    File path=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/SecureMeet-Sheets");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_canvas);
        snview=findViewById(R.id.signature_view);
        bar=findViewById(R.id.pensize);
        eraser=findViewById(eraserbnt);
        color=findViewById(R.id.color);
        save=findViewById(R.id.save);
        pensize=findViewById(R.id.txtpensize);
        askPermission();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd_MM_yyyy,HH:mm:ss");
        String date= simpleDateFormat.format(new Date());
        //filenmae=path+"/"+date+".png";
        filenmae="SecureMeet "+"-"+date+".png";
        if (!path.exists()){
            path.mkdirs();
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!snview.isBitmapEmpty()){
                    try {
                        //saveImg();
                       saveImage(snview.getSignatureBitmap());
                    } catch (Exception e) {
                       e.printStackTrace();
                        Toast.makeText(Canvas.this, "couldn't save "+e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snview.clearCanvas();
            }
        });
        defaultcolor= ContextCompat.getColor(this,R.color.black);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                snview.setPenSize(i);
                pensize.setText(i+"dp");
                bar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPicker();
            }
        });

    }

    private void saveImg() throws IOException {
File file=new File(filenmae);
        Bitmap bitmap=snview.getSignatureBitmap();
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
        byte[] bitmapdata= bos.toByteArray();

            FileOutputStream fos=new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Painting Saved!", Toast.LENGTH_SHORT).show();


    }

    private void openPicker() {
        AmbilWarnaDialog ambilWarnaDialog=new AmbilWarnaDialog(this, defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
defaultcolor=color;
snview.setPenColor(color);
            }
        });
        ambilWarnaDialog.show();
    }

    private void askPermission() {
Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
               // Toast.makeText(Canvas.this, "granted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
permissionToken.continuePermissionRequest();
            }
        }).check();


    }

    private void saveImage(Bitmap bitmap) {
        OutputStream fos; try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                ContentResolver resolver=getContentResolver();
                ContentValues contentValues = new ContentValues();
               // contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, String.valueOf(path));
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,filenmae );
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                Uri imageUri = resolver.insert(MediaStore.Images.Media. EXTERNAL_CONTENT_URI, contentValues);
                fos= resolver.openOutputStream(Objects.requireNonNull(imageUri));
                bitmap.compress (Bitmap.CompressFormat.JPEG, 100, fos); Objects.requireNonNull(fos);
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }


}