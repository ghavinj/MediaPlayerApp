package com.ghavinj.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.holdyahead);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                String mDuration = String.valueOf(duration/1000);

                Toast.makeText(MainActivity.this, mDuration, Toast.LENGTH_SHORT).show();
            }
        });

        playButton = (Button)findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    //stop and give option to start again.
                    pauseMusic();

                }else{
                    startMusic();
                }
            }
        });


    }

    public void pauseMusic(){
        if (mediaPlayer != null){
            mediaPlayer.pause();
            playButton.setText("Play");
        }
    }

    public void startMusic(){
        if (mediaPlayer != null){
            mediaPlayer.start();
            playButton.setText("Pause");
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();

    }
}
