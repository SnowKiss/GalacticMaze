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

    public Droite getTangente(Point2D p) {

        // TODO renvoyer la tangente

        // Chercher le point H sur la demie droite [cercle.centre,point) tel que distance = cercle.rayon

        // renvoie la perpendiculaire à la droite (cercle.centre,point) passant par H

        return null;
    }
}
