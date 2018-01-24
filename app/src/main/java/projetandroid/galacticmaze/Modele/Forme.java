package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public abstract class Forme {
    private Point2D coordonnees;
    private boolean plein;

    public Point2D getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Point2D coordonnees) {
        this.coordonnees = coordonnees;
    }

    public boolean isPlein() {
        return plein;
    }

    public void setPlein(boolean plein) {
        this.plein = plein;
    }
}
