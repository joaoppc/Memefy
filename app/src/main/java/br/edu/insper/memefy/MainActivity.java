package br.edu.insper.memefy;

import android.content.ActivityNotFoundException;
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
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    final int PIC_CROP = 2;
    File newfile;
    String file;

    //Uri outputFileUri;
    ArrayList<ImageView> listMemes;

    ImageView iw1;
    ImageView iw2;
    ImageView iw3;
    ImageView iw4;
    ImageView iw5;
    ImageView idply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        iw1=(ImageView) findViewById(R.id.meme1);
        iw2=(ImageView) findViewById(R.id.meme2);
        iw3=(ImageView) findViewById(R.id.meme3);
        iw4=(ImageView) findViewById(R.id.meme4);
        iw5=(ImageView) findViewById(R.id.meme5);
        idply=(ImageView) findViewById(R.id.memeDisplay);


        Button capture = (Button) findViewById(R.id.captureBtn);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                file = dir+count+".jpg";
                newfile = new File(file);
                try {
                    newfile.createNewFile();
               }
                    catch (IOException e)
               {
               }

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,outputFileUri );

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });

        final ImageView imageBtn1 = (ImageView) findViewById(R.id.meme1);
        imageBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("image clicked...");
                idply.setImageDrawable(iw1.getDrawable());
            }
        });
        final ImageView imageBtn2 = (ImageView) findViewById(R.id.meme2);
        imageBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("image clicked...");
                idply.setImageDrawable(iw2.getDrawable());
            }
        });
        final ImageView imageBtn3 = (ImageView) findViewById(R.id.meme3);
        imageBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("image clicked...");
                idply.setImageDrawable(iw3.getDrawable());
            }
        });
        final ImageView imageBtn4 = (ImageView) findViewById(R.id.meme4);
        imageBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("image clicked...");
                idply.setImageDrawable(iw4.getDrawable());
            }
        });
        final ImageView imageBtn5 = (ImageView) findViewById(R.id.meme5);
        imageBtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("image clicked...");
                idply.setImageDrawable(iw5.getDrawable());
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
            performCrop();
        }

        else if(requestCode == PIC_CROP && resultCode == RESULT_OK){
            Log.d("CameraCrop",("Pic Croped"));
        }


    }

    public void performCrop(){
        try {
            final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
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


            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(outputFileUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("return-data", true);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(cropIntent, PIC_CROP);

        }
        catch(ActivityNotFoundException anfe){
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
