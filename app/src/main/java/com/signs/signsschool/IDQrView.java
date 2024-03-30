package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class IDQrView extends AppCompatActivity {

    String url;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idqr_view);

        intent = getIntent();
        url = intent.getStringExtra("id_url");

        Button close = findViewById(R.id.closeQRID);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(IDQrView.this, Home.class));
            }
        });
        Button manuell = findViewById(R.id.openIDManButton);
        manuell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        ImageView qrCode = findViewById(R.id.imageCode);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix matrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 400,400);

            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(matrix);//creating bitmap of code
            qrCode.setImageBitmap(mBitmap);


        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}