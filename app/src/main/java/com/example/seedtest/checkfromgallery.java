package com.example.seedtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class checkfromgallery extends AppCompatActivity {
    ImageView img;
    TextView answer;
    //View colorview;

    Bitmap bitmap;
    int total=0, count=0;
    int hundred= 100;
    float percent;
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkfromgallery);


        img=(ImageView) findViewById(R.id.imageView);
        answer=(TextView) findViewById(R.id.show);



                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_DENIED)){
                        //permission not granted then request it
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    //system OS is earlier than marshmallow
                    pickImageFromGallery();
                }

            }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
    //handle result at runtime permission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission granted
                    pickImageFromGallery();
                }
                else{
                    //permission not granted
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK && requestCode==IMAGE_PICK_CODE){
            img.setImageURI(data.getData());
            BitmapDrawable abmp = (BitmapDrawable)img.getDrawable();
            bitmap= abmp.getBitmap();
            bitmap=extract(bitmap);
            img.setImageBitmap(bitmap);
        }
    }
    public Bitmap extract(Bitmap bi) {
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(bi.getWidth(), bi.getHeight(), bi.getConfig());
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int p = bi.getPixel(i, j);
                int A = Color.alpha(p);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                total=total+1;
                //answer.setText("RGB: "+r +","+g+","+b);
                r = g = b = (int)(GS_RED * r + GS_GREEN * g + GS_BLUE * b);
                // set new pixel color to output bitmap
                bmOut.setPixel(i, j, Color.argb(A, r, g, b));

                if ((r >= 50 && r <= 100) && (g >=50 && g<=100) && (b >= 50 && b <= 100)) {
                    //answer.setText("Bad seed");
                    count=count+1;
                    //colorview.setBackgroundColor(Color.rgb(r, g, b));

                }

            }


        }
        percent=(float) count/total;
        answer.setText("Percentage of badness: " +(percent*100));
        return bmOut;
    }
}