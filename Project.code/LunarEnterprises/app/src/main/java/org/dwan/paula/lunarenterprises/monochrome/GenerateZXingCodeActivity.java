package org.dwan.paula.lunarenterprises.monochrome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.dwan.paula.lunarenterprises.R;
import org.dwan.paula.lunarenterprises.store.DisplayStoreSpecificInfoActivity;

public class GenerateZXingCodeActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "GenerateZXingCodeActivity";

    int points = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, ":\tonCreate ZXing code ...");

        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_generate_zxing_code);

        Intent intentZXing = getIntent();
        displayZxingCode(intentZXing);
    }

    private void displayZxingCode(Intent intentZXing) {
        Log.d(CLASS_NAME, ":\tdisplayZxingCode --> Bar-code or QR-code ...");

        String store = intentZXing.getStringExtra(DisplayStoreSpecificInfoActivity.STORE_NAME);
        TextView tvStore = (TextView) findViewById(R.id.textviewStoreName);
        tvStore.setText(store);

        String voucher = intentZXing.getStringExtra(DisplayStoreSpecificInfoActivity.STORE_VOUCHER);
        generateQRCode(voucher);
        generateBarCode(voucher);

    }

    private void generateQRCode(String data) {
        Log.d(CLASS_NAME, ":\tgenerateQRCode ...");

        int xRange = 150, yRange = 150;
        int colorBack = 0xFF000000, colorWhite = 0xFFFFFFFF;
        Writer qrWriter = new QRCodeWriter();
        String finalData = Uri.encode(data, "utf-8");
        try {
            BitMatrix bitMatrix = qrWriter.encode(finalData, BarcodeFormat.QR_CODE, xRange, yRange);
            Bitmap imageBitmap = Bitmap.createBitmap(xRange, yRange, Bitmap.Config.RGB_565);

            for (int x = 0; x < xRange; x++) {
                for (int y = 0; y < yRange; y++) {
                    imageBitmap.setPixel(x, y, bitMatrix.get(x, y) ? colorBack : colorWhite);
                }
            }
            ImageView imageview = (ImageView) findViewById(R.id.imageviewQRcode);
            if (imageBitmap != null) imageview.setImageBitmap(imageBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void generateBarCode(String data) {
        Log.d(CLASS_NAME, ":\tgenerateBarCode ...");

        int xRange = 180, yRange = 40;
        int colorBack = 0xFF000000, colorWhite = 0xFFFFFFFF;
        MultiFormatWriter barWriter = new MultiFormatWriter();
        String finalData = Uri.encode(data, "utf-8");
        try {
            BitMatrix bitMatrix = barWriter.encode(finalData, BarcodeFormat.CODE_128, xRange, yRange);
            Bitmap imageBitmap = Bitmap.createBitmap(xRange, yRange, Bitmap.Config.RGB_565);

            for (int x = 0; x < xRange; x++) {
                for (int y = 0; y < yRange; y++) {
                    imageBitmap.setPixel(x, y, bitMatrix.get(x, y) ? colorBack : colorWhite);
                }
            }
            ImageView imageview = (ImageView) findViewById(R.id.imageviewBarcode);
            if (imageBitmap != null) imageview.setImageBitmap(imageBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes ZXing bar-code or QR-code and returns to calling activity - Store Information.
     */
    protected void backToStoreInformation() {
        Log.d(CLASS_NAME, ":\tbackToStoreInformation ...");

        finish();
    }

}