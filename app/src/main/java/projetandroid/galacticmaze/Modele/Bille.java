package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public class Bille {
    private Point2D coordonnees;
    private Vecteur vitesse;
    private Vecteur acceleration;
    private float rayon;

    public Bille(Point2D coordonnees, float rayon) {
        this.coordonnees = coordonnees;
        this.vitesse = new Vecteur(new Point2D(0,0),new Point2D(0,0));
        this.acceleration = new Vecteur(new Point2D(0,0),new Point2D(0,0));
        this.rayon = rayon;
    }

    public Bille(Point2D coordonnees, Vecteur vitesse, float rayon) {
        this.coordonnees = coordonnees;
        this.vitesse = vitesse;
        this.acceleration = new Vecteur(new Point2D(0,0),new Point2D(0,0));
        this.rayon = rayon;
    }

    public Point2D getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Point2D coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Vecteur getVitesse() {
        return vitesse;
    }

    public void setVitesse(Vecteur vitesse) {
        this.vitesse = vitesse;
    }

    public Vecteur getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vecteur acceleration) {
        this.acceleration = acceleration;
    }

    public float getRayon() {
        return rayon;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }
}
