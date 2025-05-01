package com.example.videoplay_demo;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    
    //step 01
    private VideoView videoView; 
    private VideoView uservideoView; 
    private Button button;
    private Button btn_userVideo;

    private VideoView videoViewWebURL;

    private Button buttonWebURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
   //step 02
        videoView= findViewById(R.id.videoView);
        button=findViewById(R.id.uploadedvideo_btn);
    
        uservideoView= findViewById(R.id.uservideoView);
        btn_userVideo = findViewById((R.id.button2));

        videoViewWebURL= findViewById(R.id.videoViewWeb);
        buttonWebURL = findViewById((R.id.button3));
  
        // resource folder video loading button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.demo;
                Uri uri = Uri.parse(videoPath);
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });



        ActivityResultLauncher<String> fileChooser= registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>(){
                    @Override
                    public void onActivityResult(Uri result) {
                        uservideoView.setVideoURI(result);
                        uservideoView.start();
                    }
        }

        );

        //user video loading button
        btn_userVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser.launch("video*/");
            }
        });


        //web video loading button
        buttonWebURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String videoPath = "https://www.youtube.com/watch?v=I3OJUwILelU";
               Uri uri = Uri.parse(videoPath);
                videoViewWebURL.setVideoURI(uri);
                videoViewWebURL.start();


            }
        });


     //Add media controller
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setMediaPlayer(videoView);
        videoView.setMediaController(mediacontroller);

        mediacontroller.setMediaPlayer(uservideoView);
        uservideoView.setMediaController(mediacontroller);

        mediacontroller.setMediaPlayer(videoViewWebURL);
        videoViewWebURL.setMediaController(mediacontroller);

        }

    }
