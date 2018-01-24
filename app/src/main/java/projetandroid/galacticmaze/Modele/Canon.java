package projetandroid.galacticmaze.Modele;

import java.util.ArrayList;

/**
 * Created by John on 23/01/2018.
 */

class Canon {
    private Point2D coordonnees;
    private ArrayList<Bille> projectiles;
    private Point2D cible;

    public Canon(Point2D coordonnees, Point2D cible) {
        this.coordonnees = coordonnees;
        this.cible = cible;
        this.projectiles = new ArrayList<Bille>();
    }

    public Point2D getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Point2D coordonnees) {
        this.coordonnees = coordonnees;
    }

    public ArrayList<Bille> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Bille> projectiles) {
        this.projectiles = projectiles;
    }

    public Point2D getCible() {
        return cible;
    }

    public void setCible(Point2D cible) {
        this.cible = cible;
    }
}
