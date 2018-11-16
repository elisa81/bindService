package com.example.edu.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.media.MediaPlayer;

public class BackgroundMusicWithBindService extends Service {

    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }



    public class MyBinder extends Binder {
        public BackgroundMusicWithBindService getService() {
            return BackgroundMusicWithBindService.this;
            }
        }

        public boolean play() {
            mediaPlayer = MediaPlayer.create(this, R.raw.trivia);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100, 100);
            mediaPlayer.start();
            return false;
        }

    public void stop() {
        mediaPlayer.stop();
    }

}
