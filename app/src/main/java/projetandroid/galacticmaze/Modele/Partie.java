package projetandroid.galacticmaze.Modele;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

import projetandroid.galacticmaze.Controleur.CollisionManager;
import projetandroid.galacticmaze.Controleur.Dessinateur;
import projetandroid.galacticmaze.Controleur.Mouvement;
import projetandroid.galacticmaze.R;
import projetandroid.galacticmaze.Vue.GameOverDialog;

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
    private GameOverDialog gameOverDialog;
    private static MediaPlayer gameOverMusic;
    private static MediaPlayer winMusic;

    public Partie(Bille player, Labyrinthe labyrinthe, Dessinateur dessinateur, CollisionManager collisionManager, Mouvement mouvement, GameOverDialog gameOverDialog, Activity gameActivity) {
        this.player = player;
        this.labyrinthe = labyrinthe;
        this.dessinateur = dessinateur;
        this.collisionManager = collisionManager;
        this.mouvement = mouvement;
        this.gameover = false;
        this.gameOverDialog = gameOverDialog;
        this.gameOverMusic = MediaPlayer.create(gameActivity, R.raw.loose);
        this.winMusic = MediaPlayer.create(gameActivity, R.raw.win);
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
                try
                {
                    // on met à jour les accelerations en fonction de l'acceleromètre
                    // on vérifie les collisions et on applique ses effets
                    // on applique les déplacements sur le joueur
                    mouvement.applyDeltaTime(player, true);

                    // on applique les déplacements sur les projectiles
                    if(labyrinthe.getCanon()!=null)
                    {
                        labyrinthe.getCanon().setCible(player.getCentre());
                        for(Bille projectile:labyrinthe.getCanon().getProjectiles())
                        {
                            mouvement.applyDeltaTime(projectile, false);
                        }
                    }


                    // on met à jour la vue
                    dessinateur.updateVue(player, labyrinthe);

                    // on vérifie la condition de défaite
                    for (Trou trou : labyrinthe.getTrous()) {
                        if(collisionManager.billeDansZone(player,trou))
                        {
                            gameover = true;
                            playGameOver();
                            gameOverDialog.afficherResultat("Perdu !", "Vous avez été absorbé par un trou noir !");
                        }
                    }

                    // on vérifie la condition de victoire
                    if(collisionManager.billeDansZone(player,labyrinthe.getWinZone()))
                    {
                        gameover = true;
                        playWin();
                        gameOverDialog.afficherResultat("Bravo !", "Vous avez atteint le point d'extraction !");
                    }

                    // si la partie est finie, on sort de la boucle
                    if(gameover)
                        throw new Exception();

                    handler.postDelayed(this,1); // set time here to refresh textView
                }
                catch (Exception e)
                {

                }

            }

            private void playWin() {
                winMusic.seekTo(0);
                winMusic.start();
            }

            private void playGameOver() {
                gameOverMusic.seekTo(0);
                gameOverMusic.start();
            }
        });
    }
}
