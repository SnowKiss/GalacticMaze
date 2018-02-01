package projetandroid.galacticmaze.Controleur;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import projetandroid.galacticmaze.R;

/**
 * Created by John on 01/02/2018.
 */

public class SoundManager extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        player = MediaPlayer.create(this, R.raw.spaceidea);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        player.stop();
    }
}
