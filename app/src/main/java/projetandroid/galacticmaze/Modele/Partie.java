package projetandroid.galacticmaze.Modele;

import android.os.Handler;
import android.util.Log;

import projetandroid.galacticmaze.Controleur.CollisionManager;
import projetandroid.galacticmaze.Controleur.Dessinateur;
import projetandroid.galacticmaze.Controleur.Mouvement;

/**
 * Created by John on 23/01/2018.
 */

public class Partie {
    private Bille player;
    private Labyrinthe labyrinthe;
    private boolean gameover;
    private Dessinateur dessinateur;
    private CollisionManager collisionManager;
    private Mouvement mouvement;

    public Partie(Bille player, Labyrinthe labyrinthe, Dessinateur dessinateur, CollisionManager collisionManager, Mouvement mouvement) {
        this.player = player;
        this.labyrinthe = labyrinthe;
        this.dessinateur = dessinateur;
        this.collisionManager = collisionManager;
        this.mouvement = mouvement;
        this.gameover = false;
    }

    public Bille getPlayer() {
        return player;
    }

    public void setPlayer(Bille player) {
        this.player = player;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

    public void setLabyrinthe(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    public void start() {
        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
            // on met à jour les accelerations en fonction de l'acceleromètre
            // on vérifie les collisions et on applique ses effets
            // on applique les déplacements
            mouvement.applyDeltaTime(player);

            // on met à jour la vue
            dessinateur.updateVue(player, labyrinthe);

            // on vérifie la condition de défaite
            for (Trou trou : labyrinthe.getTrous()) {
                if(collisionManager.billeDansZone(player,trou))
                {
                    gameover = true;
                    Log.i("GAMEOVER","DEFAITE");
                }
            }

            // on vérifie la condition de victoire
            if(collisionManager.billeDansZone(player,labyrinthe.getWinZone()))
            {
                gameover = true;
                Log.i("GAMEOVER","VICTOIRE");
            }

            // si la partie est finie, on sort de la boucle
            if(gameover)
                handler.removeCallbacks(this);

            handler.postDelayed(this,1); // set time here to refresh textView
            }
        });
    }
}
