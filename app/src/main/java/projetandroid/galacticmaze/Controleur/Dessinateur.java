package projetandroid.galacticmaze.Controleur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.Console;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Cercle;
import projetandroid.galacticmaze.Modele.Forme;
import projetandroid.galacticmaze.Modele.Labyrinthe;
import projetandroid.galacticmaze.Modele.Rectangle;
import projetandroid.galacticmaze.Modele.Trou;
import projetandroid.galacticmaze.R;

/**
 * Created by John on 23/01/2018.
 */

public class Dessinateur {

    RelativeLayout gameLayout;
    Activity gameActivity;
    ImageView playerView;
    Canvas canvas;
    Bitmap canvasBM;

    public Dessinateur(RelativeLayout gameLayout, Activity gameActivity) {
        this.gameLayout = gameLayout;
        this.gameActivity = gameActivity;
    }

    public void initVue(Bille player, Labyrinthe labyrinthe)
    {

        // Affichage du labyrinthe (tempo)
        canvasBM = Bitmap.createBitmap(1280, 727, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBM);
        dessinerLabyrinthe(labyrinthe);

        // Affichage du joueur
        playerView = new ImageView(gameActivity);
        playerView.setImageBitmap(Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(gameActivity.getResources(), R.drawable.player),
                (int)player.getRayon()*2,
                (int)player.getRayon()*2,
                false));
        gameLayout.addView(playerView);
        playerView.setX(player.getCoordonnees().getX());
        playerView.setY(player.getCoordonnees().getY());
        dessinerPlayer(player);
    }
    
    public void updateVue(Bille player, Labyrinthe labyrinthe) {
        // on met à jour la vue du joueur
        dessinerPlayer(player);
        
        // on met à jour la vue du labyrinthe
        //dessinerLabyrinthe(labyrinthe);
    }

    private void dessinerLabyrinthe(Labyrinthe labyrinthe) {

        Paint myPaint = new Paint();

        // affichage des murs
        for (Forme forme:labyrinthe.getMurs()) {
            try
            {
                afficherRectangle((Rectangle) forme, myPaint);
            }
            catch(ClassCastException e)
            {
                afficherCercle((Cercle) forme, myPaint);
            }
        }

        // affichage de la zone de spawn
        myPaint.setColor(Color.YELLOW);
        canvas.drawCircle(
                labyrinthe.getSpawnZone().getCoordonnees().getX()+labyrinthe.getSpawnZone().getRayon(),
                labyrinthe.getSpawnZone().getCoordonnees().getY()+labyrinthe.getSpawnZone().getRayon(),
                labyrinthe.getSpawnZone().getRayon(),
                myPaint);

        // affichage de la zone d'arrivée
        myPaint.setColor(Color.GREEN);
        canvas.drawCircle(
                labyrinthe.getWinZone().getCoordonnees().getX()+labyrinthe.getWinZone().getRayon(),
                labyrinthe.getWinZone().getCoordonnees().getY()+labyrinthe.getWinZone().getRayon(),
                labyrinthe.getWinZone().getRayon(),
                myPaint);

        ImageView imgView = new ImageView(gameActivity);
        imgView.setImageBitmap(canvasBM);
        imgView.setImageAlpha(100);
        gameLayout.addView(imgView);

        // affichage des trous
        //myPaint.setColor(Color.BLACK);
        for (Trou trou:labyrinthe.getTrous())
        {
            /*canvas.drawCircle(
                    trou.getCoordonnees().getX()+trou.getRayon(),
                    trou.getCoordonnees().getY()+trou.getRayon(),
                    trou.getRayon(),
                    myPaint);*/
            ImageView trouView = new ImageView(gameActivity);
            trouView.setImageBitmap(Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(gameActivity.getResources(), R.drawable.blackhole),
                    (int)(trou.getRayon()*6),
                    (int)(trou.getRayon()*6),
                    false));
            trouView.setX(trou.getCoordonnees().getX()-100);
            trouView.setY(trou.getCoordonnees().getY()-100);

            //RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, trouView.getWidth()/2, Animation.RELATIVE_TO_SELF, trouView.getHeight());
            RotateAnimation rotate = new RotateAnimation(0, 720,trou.getCoordonnees().getX()+50,trou.getCoordonnees().getY()+50);

            rotate.setDuration(60000);
            rotate.setRepeatCount(Animation.INFINITE);
            rotate.setRepeatMode(Animation.REVERSE);
            trouView.setAnimation(rotate);

            gameLayout.addView(trouView);
        }
        /*ImageView imgView = new ImageView(gameActivity);
        imgView.setImageBitmap(canvasBM);
        imgView.setImageAlpha(100);
        gameLayout.addView(imgView);*/
        //TODO remplacer les zones par des assets
    }

    private void dessinerPlayer(Bille player) {
        //TODO Animation pour rendu smooth (problèmes liés à l'arrondi en int)
        playerView.setX(player.getCoordonnees().getX());
        playerView.setY(player.getCoordonnees().getY());
        /*TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 100.0f, 0.0f, 100.0f);
        translateAnimation.setDuration(1000); // 1 second
        playerView.startAnimation(translateAnimation);*/

    }

    private void afficherRectangle(Rectangle rectangle, Paint myPaint)
    {

        myPaint.setColor(rectangle.isPlein()? Color.BLACK:Color.GRAY);
        canvas.drawRect(
                rectangle.getCoordonnees().getX(),
                rectangle.getCoordonnees().getY(),
                rectangle.getCoordonnees().getX()+rectangle.getLongueur(),
                rectangle.getCoordonnees().getY()+rectangle.getLargeur(),
                myPaint);

    }

    private void afficherCercle(Cercle cercle, Paint myPaint)
    {
        //Log.i("ETAT","COUCOU");
        myPaint.setColor(cercle.isPlein()? Color.BLACK:Color.GRAY);
        canvas.drawCircle(cercle.getCoordonnees().getX(),cercle.getCoordonnees().getY(),cercle.getRayon(),myPaint);
    }
}
