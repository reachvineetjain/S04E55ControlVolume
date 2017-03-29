package com.nehvin.s04e55controlvolume;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;
    SeekBar volumeControl = null;
    SeekBar fileProgress = null;
    AudioManager audioManager = null;
//    int maxVol;
//    int curVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.chaiyachaiya);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        volumeControl = (SeekBar) findViewById(R.id.volumeControl);
        volumeControl.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeControl.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("Seek Bar progress" , Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fileProgress = (SeekBar)findViewById(R.id.fileProgress);
        fileProgress.setMax(mediaPlayer.getDuration());
        fileProgress.setProgress(mediaPlayer.getCurrentPosition());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fileProgress.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,100);

        fileProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b)
                {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void playMedia (View view)
    {
        mediaPlayer.start();
    }

    public void pauseMedia (View view)
    {
        mediaPlayer.pause();
    }

}
