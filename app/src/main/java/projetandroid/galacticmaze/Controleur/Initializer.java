package projetandroid.galacticmaze.Controleur;

import android.app.Activity;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Labyrinthe;
import projetandroid.galacticmaze.Modele.Partie;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.Rectangle;
import projetandroid.galacticmaze.Modele.SpawnZone;
import projetandroid.galacticmaze.Modele.WinZone;

/**
 * Created by John on 24/01/2018.
 */

public class Initializer {

    private Activity gameActivity;
    private Dessinateur dessinateur;
    private CollisionManager collisionManager;
    private Mouvement mouvement;

    public Initializer(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public void startGame()
    {
        Labyrinthe labyrinthe = initLabyrinthe();
        Bille player = initBille(labyrinthe.getSpawnZone());
        Partie partie = new Partie(player, labyrinthe, dessinateur, collisionManager, mouvement);
        // Game loop
        partie.start();
    }

    private Bille initBille(SpawnZone spawnZone) {
        Bille bille = new Bille(spawnZone.getCoordonnees(),20);
        return bille;
    }

    private Labyrinthe initLabyrinthe() {
        // On ajoute la zone de spawn et la zone à atteindre
        Labyrinthe labyrinthe = new Labyrinthe(
                                        new SpawnZone(new Point2D(150,150)),
                                        new WinZone(new Point2D(800,800)));

        // On ajoute les murs par extrusion
        labyrinthe.ajouterZone(new Rectangle(new Point2D(0,0), 727, 1280, 0, true));

        return labyrinthe;
    }

    public Activity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }
}
