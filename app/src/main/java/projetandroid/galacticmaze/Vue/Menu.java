package projetandroid.galacticmaze.Vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import projetandroid.galacticmaze.R;

public class Menu extends Activity implements View.OnClickListener{

    Button btn_lvl1;
    Button btn_lvl2;
    Button btn_lvl3;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_lvl1 = (Button) findViewById(R.id.btn_lvl1);
        btn_lvl1.setOnClickListener(this);
    }

    /*
     * La méthode onClick(View) provient de l'interface View.OnClickListener.
     */
    @Override
    public void onClick(View v) {
        if (v == btn_lvl1) {
            Intent intent = new Intent(Menu.this, Jeu.class);
            startActivityForResult(intent,REQUEST_CODE);
        }
    }


    public void closeApp(View v)
    {
        onDestroy();
    }

    public void onDestroy() {
        super.onDestroy();
        System.exit(1);
    }


}
