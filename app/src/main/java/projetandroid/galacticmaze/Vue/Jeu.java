package projetandroid.galacticmaze.Vue;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import projetandroid.galacticmaze.Controleur.Initializer;
import projetandroid.galacticmaze.R;

public class Jeu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        Bundle extras = getIntent().getExtras();
        int level = extras.getInt("level");
        Log.i("Level",Integer.toString(level));
        Initializer initializer = new Initializer(this, level);
        initializer.startGame();
    }
}
