package com.example.edu.bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MediaPlayer extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    Button play, stop;

    private BackgroundMusicWithBindService mServiceBinder;

    private ServiceConnection myConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder binder) {
            mServiceBinder = ((BackgroundMusicWithBindService.MyBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mServiceBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        play = findViewById(R.id.buttonPlay);
        stop = findViewById(R.id.buttonStop);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);

        Intent intent = new Intent(this, BackgroundMusicWithBindService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                    mServiceBinder.play();
                break;

            case R.id.buttonStop:
                mServiceBinder.stop();
                break;

        }
    }

}
