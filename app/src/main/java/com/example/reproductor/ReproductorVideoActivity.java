package com.example.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class ReproductorVideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getSupportActionBar().hide();

        videoView = findViewById(R.id.video_view);

        //Obtenemos la ruta del video seleccionado
        String ruta = getIntent().getStringExtra("ruta");


        if (ruta != null) {
            //mediaController nos proporciona controles como pause, play, next, previous
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);//agregamos los controles nuestra vista Video
            videoView.setVideoPath(ruta);//Establecemos la ruta del video
            videoView.start();//Inciamos el Video.
        }

    }

}