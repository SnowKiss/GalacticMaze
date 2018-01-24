package projetandroid.galacticmaze.Controleur;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.Vecteur;
import projetandroid.galacticmaze.Vue.InputManager;

/**
 * Created by John on 23/01/2018.
 */

public class Mouvement {

    private Bille bille;
    private InputManager inputManager;

    public Mouvement(Bille bille, InputManager inputManager) {
        this.bille = bille;
        this.inputManager = inputManager;
    }

    public void applyDeltaTime()
    {
        // on met à jour l'acceleration
        bille.setAcceleration(
                new Vecteur(
                        new Point2D(0,0),
                        new Point2D(inputManager.getDeltaX(),inputManager.getDeltaY())));

        // on met à jour la vitesse
        bille.setVitesse(
                new Vecteur(
                        new Point2D(0,0),
                        new Point2D(
                                bille.getVitesse().getFin().getX()+bille.getAcceleration().getFin().getX(),
                                bille.getVitesse().getFin().getY()+bille.getAcceleration().getFin().getY())));

        // on verifie les collisions
        // TODO gestion des collisions

        // on met à jour la position
        bille.setCoordonnees(
                new Point2D(
                        bille.getCoordonnees().getX()+bille.getVitesse().getFin().getX(),
                        bille.getCoordonnees().getY()+bille.getVitesse().getFin().getY()));
    }

    public Bille getBille() {
        return bille;
    }

    public void setBille(Bille bille) {
        this.bille = bille;
    }
}
