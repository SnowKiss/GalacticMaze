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

    public Droite getTangente(Bille bille) {

        // Chercher le point H sur la demie droite [cercle.centre,point) tel que distance = cercle.rayon
        float d = Calcul.distance2Points(getCoordonnees(),bille.getCentre());
        float facteur = (d+bille.getRayon())/d;
        Vecteur v = new Vecteur(new Point2D(0,0), new Point2D(bille.getCoordonnees().getX()-getCoordonnees().getX(), bille.getCoordonnees().getY()-getCoordonnees().getY()));
        v = v.scaled(facteur);
        Point2D H = new Point2D(getCoordonnees().getX()+v.getFin().getX(),getCoordonnees().getY()+v.getFin().getY());

        // renvoie la perpendiculaire à la droite (cercle.centre,point) passant par H
        Droite section = new Droite(getCoordonnees(),bille.getCentre());
        return section.perpendiculaire(H);
    }
}
