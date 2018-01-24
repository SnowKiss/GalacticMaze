package projetandroid.galacticmaze.Controleur;

import android.app.Activity;
import android.widget.RelativeLayout;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Labyrinthe;
import projetandroid.galacticmaze.Modele.Partie;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.Rectangle;
import projetandroid.galacticmaze.Modele.SpawnZone;
import projetandroid.galacticmaze.Modele.Trou;
import projetandroid.galacticmaze.Modele.WinZone;
import projetandroid.galacticmaze.R;
import projetandroid.galacticmaze.Vue.InputManager;

/**
 * Created by John on 24/01/2018.
 */

public class Initializer {

    private Activity gameActivity;
    private Dessinateur dessinateur;
    private CollisionManager collisionManager;
    private Mouvement mouvement;
    private InputManager inputManager;

    public Initializer(Activity gameActivity) {
        this.gameActivity = gameActivity;
        this.inputManager = new InputManager(gameActivity);
        this.dessinateur = new Dessinateur((RelativeLayout) gameActivity.findViewById(R.id.gamelayout), gameActivity);
        this.collisionManager = new CollisionManager();
        this.mouvement = new Mouvement(inputManager);
    }

    public void startGame()
    {
        Labyrinthe labyrinthe = initLabyrinthe();
        Bille player = initBille(labyrinthe.getSpawnZone());
        dessinateur.initVue(player,labyrinthe);
        Partie partie = new Partie(player, labyrinthe, dessinateur, collisionManager, mouvement);
        // Game loop
        partie.start();
    }

    private Bille initBille(SpawnZone spawnZone) {
        float rayonBille = 40f;
        Bille bille = new Bille(new Point2D(spawnZone.getCoordonnees().getX()+spawnZone.getRayon()-rayonBille,spawnZone.getCoordonnees().getY()+spawnZone.getRayon()-rayonBille),rayonBille);
        return bille;
    }

    private Labyrinthe initLabyrinthe() {
        // On ajoute la zone de spawn et la zone à atteindre
        Labyrinthe labyrinthe = new Labyrinthe(
                                        new SpawnZone(new Point2D(150,150), 60),
                                        new WinZone(new Point2D(1000,150), 60));

        // On ajoute les murs par extrusion
        labyrinthe.ajouterZone(new Rectangle(new Point2D(0,0), 1280, 727, 0, true));
        labyrinthe.ajouterZone(new Rectangle(new Point2D(50,50), 1180, 627, 0, false));
        labyrinthe.ajouterZone(new Rectangle(new Point2D(350,50), 580, 427, 0, true));

        labyrinthe.ajouterTrou(new Trou(new Point2D(110,400), 50));

        return labyrinthe;
    }

    public Activity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

}
