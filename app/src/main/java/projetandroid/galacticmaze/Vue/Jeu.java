package projetandroid.galacticmaze.Vue;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import projetandroid.galacticmaze.Controleur.Initializer;
import projetandroid.galacticmaze.R;

public class Jeu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        Initializer initializer = new Initializer(this);
        initializer.startGame();
    }
}
