package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public abstract class Zone {
    private Point2D coordonnees;
    private float rayon;

    public Zone(Point2D coordonnees) {
        this.coordonnees = coordonnees;
        this.rayon = 50;
    }

    public Zone(Point2D coordonnees, float rayon) {
        this.coordonnees = coordonnees;
        this.rayon = rayon;
    }

    public Point2D getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Point2D coordonnees) {
        this.coordonnees = coordonnees;
    }

    public float getRayon() {
        return rayon;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }

    public Point2D getCentre() {
        return new Point2D(coordonnees.getX()+rayon,coordonnees.getY()+rayon);
    }
}
