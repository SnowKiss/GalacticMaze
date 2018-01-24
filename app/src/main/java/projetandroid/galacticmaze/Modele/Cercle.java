package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public class Cercle extends Forme {
    private float rayon;

    public Cercle(Point2D coordonnees, float rayon, boolean plein) {
        this.setCoordonnees(coordonnees);
        this.rayon = rayon;
        this.setPlein(plein);
    }

    public float getRayon() {
        return rayon;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }
}
