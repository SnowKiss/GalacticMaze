package projetandroid.galacticmaze.Controleur;

import android.app.Activity;
import android.widget.RelativeLayout;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Canon;
import projetandroid.galacticmaze.Modele.Cercle;
import projetandroid.galacticmaze.Modele.Labyrinthe;
import projetandroid.galacticmaze.Modele.Partie;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.Rectangle;
import projetandroid.galacticmaze.Modele.SpawnZone;
import projetandroid.galacticmaze.Modele.Trou;
import projetandroid.galacticmaze.Modele.WinZone;
import projetandroid.galacticmaze.R;
import projetandroid.galacticmaze.Vue.GameOverDialog;
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
    private GameOverDialog gameOverDialog;
    private int level;

    public Initializer(Activity gameActivity, int level) {
        this.gameActivity = gameActivity;
        this.inputManager = new InputManager(gameActivity);
        this.dessinateur = new Dessinateur((RelativeLayout) gameActivity.findViewById(R.id.gamelayout), gameActivity);
        this.collisionManager = new CollisionManager(gameActivity);
        this.gameOverDialog = new GameOverDialog(gameActivity);
        this.level = level;
    }

    public void startGame()
    {
        Labyrinthe labyrinthe = initLabyrinthe(this.level);
        Bille player = initBille(labyrinthe.getSpawnZone());
        this.mouvement = new Mouvement(inputManager, labyrinthe);
        dessinateur.initVue(player,labyrinthe);
        Partie partie = new Partie(player, labyrinthe, dessinateur, collisionManager, mouvement, gameOverDialog, gameActivity);
        // Game loop
        partie.start();
    }

    private Bille initBille(SpawnZone spawnZone) {
        float rayonBille = 40f;
        Bille bille = new Bille(new Point2D(spawnZone.getCoordonnees().getX()+spawnZone.getRayon()-rayonBille,spawnZone.getCoordonnees().getY()+spawnZone.getRayon()-rayonBille),rayonBille);
        return bille;
    }

    private Labyrinthe initLabyrinthe(int level) {

        if (level==1)
        {
            // On ajoute la zone de spawn et la zone à atteindre
            Labyrinthe labyrinthe = new Labyrinthe(
                    new SpawnZone(new Point2D(150,150), 60),
                    new WinZone(new Point2D(1000,150), 60));

            // On ajoute les murs par extrusion
            labyrinthe.ajouterZone(new Rectangle(new Point2D(0,0), 1280, 727, 0, true));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(50,50), 1180, 627, 0, false));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(350,50), 580, 427, 0, true));

            labyrinthe.ajouterTrou(new Trou(new Point2D(550,550)));

            return labyrinthe;
        }
        if (level==2)
        {
            // On ajoute la zone de spawn et la zone à atteindre
            Labyrinthe labyrinthe = new Labyrinthe(
                    new SpawnZone(new Point2D(150,150), 60),
                    new WinZone(new Point2D(900,150), 60));

            // On ajoute les murs par extrusion
            labyrinthe.ajouterZone(new Rectangle(new Point2D(0,0), 1280, 727, 0, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(800,360), 300, false));
            labyrinthe.ajouterZone(new Cercle(new Point2D(800,360), 150, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(360,360), 300, false));
            labyrinthe.ajouterZone(new Cercle(new Point2D(360,360), 150, true));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(350,0), 450, 360, 0, true));

            labyrinthe.ajouterTrou(new Trou(new Point2D(540,495)));

            return labyrinthe;
        }
        if (level==3)
        {
            // On ajoute la zone de spawn et la zone à atteindre
            Labyrinthe labyrinthe = new Labyrinthe(
                    new SpawnZone(new Point2D(70,80), 60),
                    new WinZone(new Point2D(1100,555), 60));

            // On ajoute les murs par extrusion
            /*labyrinthe.ajouterZone(new Rectangle(new Point2D(0,0), 1280, 727, 0, true));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(50,50), 1180, 627, 0, false));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(200,50), 600, 500, 0, true));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(350,550), 500, 627, 0, true));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(500,200), 600, 350, 0, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(430,500), 200, false));
            labyrinthe.ajouterZone(new Cercle(new Point2D(480,550), 150, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(700,220), 200, false));
            labyrinthe.ajouterZone(new Cercle(new Point2D(650,170), 150, true));*/
            labyrinthe.ajouterZone(new Rectangle(new Point2D(0,0), 1280, 727, 0, true));
            labyrinthe.ajouterZone(new Rectangle(new Point2D(50,50), 1180, 627, 0, false));
            labyrinthe.ajouterZone(new Cercle(new Point2D(800,360), 200, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(360,360), 150, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(360,100), 120, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(580,860), 300, true));
            labyrinthe.ajouterZone(new Cercle(new Point2D(0,700), 250, true));

            labyrinthe.ajouterTrou(new Trou(new Point2D(530,140)));
            labyrinthe.ajouterTrou(new Trou(new Point2D(1050,200)));

            labyrinthe.ajouterCanon(new Canon(new Point2D(900,600),labyrinthe.getSpawnZone().getCentre(),5000));

            return labyrinthe;
        }
        return null;
    }

    public Activity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

}
