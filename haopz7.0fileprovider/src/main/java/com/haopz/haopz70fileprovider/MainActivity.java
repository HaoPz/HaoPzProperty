package com.haopz.haopz70fileprovider;

import android.Manifest;
import android.app.usage.ExternalStorageStats;
import android.content.Intent;
import android.content.pm.PermissionInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.haopz.haopzpermissionutils.PermissionUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int PHOTO_GRAPH = 1;
    private ImageView viewById;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(" toggle click ->", isChecked + "");
                toggleButton.setChecked(isChecked);
            }
        });


        // viewById = findViewById(R.id.pic);
        findViewById(R.id.takePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.getInstance().setRequestPermissionCode(100).setRequestPermission(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.CAMERA},
                        MainActivity.this
                );
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionUtils.OnRequestPermissionResult() {
            @Override
            public void onRequestPerSuccess() {
                takePhoto();
            }

            @Override
            public void onRequestPerFail() {

            }
        });
    }

    private void takePhoto() {
        File file1 = new File(getCacheDir() + "/images/" + "linshi.png");

        if (file1.exists()) {
            file1.delete();
        }

        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdir();
        }
        Intent intentShoot = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentShoot.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file1));
//        intentShoot.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        Uri uriForFile = FileProvider.getUriForFile(MainActivity.this, "com.haopz.haopz70fileprovider.fileprovider", file1);
        intentShoot.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        startActivityForResult(intentShoot, PHOTO_GRAPH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_GRAPH:
                // Log.i("-->" , file.getAbsolutePath());  /data/user/0/com.haopz.haopz70fileprovider/files/linshi.png
                File file = new File(getCacheDir() + "/images/" + "linshi.png");
                Log.i("-->", file.getAbsolutePath() + " --> " + file.length());
                if (file.length() >= 0) {
                    // viewById.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                }
                break;
        }
    }
}
