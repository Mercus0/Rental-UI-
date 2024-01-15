package com.example.rental_ui_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button login,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView video=findViewById(R.id.videoView);
        login=findViewById(R.id.loginButton);
        signUp=findViewById(R.id.idRegisterButton);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.loginvideo);

        video.setVideoURI(uri);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t1=new Intent(MainActivity.this,LoginPage.class);
                startActivity(t1);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t2=new Intent(MainActivity.this,RegisterPage.class);
                startActivity(t2);
            }
        });
    }
}