package br.edu.insper.memefy;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    ImageView iw1;
    ImageView iw2;
    ImageView iw3;
    ImageView iw4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        iw1=(ImageView) findViewById(R.id.meme1);

        Button capture = (Button) findViewById(R.id.captureBtn);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                count++;
                String file = dir+count+".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                }
                catch (IOException e)
                {
                }

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });

        final ImageView imageBtn = (ImageView) findViewById(R.id.meme1);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("image clicked...");
                iw1.setImageResource(R.drawable.meme3);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
        }
    }
}
