package projetandroid.galacticmaze.Controleur;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Labyrinthe;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.Vecteur;
import projetandroid.galacticmaze.Vue.InputManager;

/**
 * Created by John on 23/01/2018.
 */

public class Mouvement {

    private InputManager inputManager;
    private Labyrinthe maze;

    public Mouvement(InputManager inputManager, Labyrinthe maze) {
        this.inputManager = inputManager;
        this.maze = maze;
    }

    public void applyDeltaTime(Bille bille, boolean joueur)
    {
        float seuil = 0.02f;
        float coefFrottement = 0.993f;

        // on met à jour l'acceleration
        bille.setAcceleration(
                new Vecteur(
                        new Point2D(0,0),
                        new Point2D(Math.abs(inputManager.getDeltaX())<seuil?0:inputManager.getDeltaX(),Math.abs(inputManager.getDeltaY())<seuil?0:inputManager.getDeltaY())));

        // on met à jour la vitesse
        bille.setVitesse(
                new Vecteur(
                        new Point2D(0,0),
                        new Point2D(
                                Math.abs((bille.getVitesse().getFin().getX()+bille.getAcceleration().getFin().getX())*coefFrottement)<seuil?0:(bille.getVitesse().getFin().getX()+bille.getAcceleration().getFin().getX())*coefFrottement,
                                Math.abs((bille.getVitesse().getFin().getY()+bille.getAcceleration().getFin().getY())*coefFrottement)<seuil?0:(bille.getVitesse().getFin().getY()+bille.getAcceleration().getFin().getY())*coefFrottement)));

        // on applique les collisions
        Bille billeDuFutur = bille.copy();
        billeDuFutur.setCoordonnees(
                new Point2D(
                        bille.getCoordonnees().getX()+(bille.getVitesse().getFin().getX()-bille.getVitesse().getOrigine().getX()),
                        bille.getCoordonnees().getY()+(bille.getVitesse().getFin().getY()-bille.getVitesse().getOrigine().getY())));
        CollisionManager.applyCollision(bille, billeDuFutur, maze, joueur);

        // on enlève le bruit
        /*bille.setVitesse(
                new Vecteur(
                        new Point2D(0,0),
                        new Point2D(
                                Math.abs(bille.getVitesse().getFin().getX())<seuil?0:bille.getVitesse().getFin().getX(),
                                Math.abs(bille.getVitesse().getFin().getY())<seuil?0:bille.getVitesse().getFin().getY())));
        */

        // on met à jour la position
        bille.setCoordonnees(
                new Point2D(
                        bille.getCoordonnees().getX()+(bille.getVitesse().getFin().getX()-bille.getVitesse().getOrigine().getX()),
                        bille.getCoordonnees().getY()+(bille.getVitesse().getFin().getY()-bille.getVitesse().getOrigine().getY())));
    }

}
