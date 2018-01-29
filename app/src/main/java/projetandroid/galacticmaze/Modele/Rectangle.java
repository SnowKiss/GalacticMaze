package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public class Rectangle extends Forme {
    private float longueur;
    private float largeur;
    private float angle;

    public Rectangle(Point2D coordonnees, float longueur, float largeur, float angle, boolean plein) {
        this.setCoordonnees(coordonnees);
        this.longueur = longueur;
        this.largeur = largeur;
        this.angle = angle;
        this.setPlein(plein);
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Droite coteGauche()
    {
        return new Droite(
                getCoordonnees(),
                new Point2D(getCoordonnees().getX(),getCoordonnees().getY()+largeur));
    }

    public Droite coteDroite()
    {
        return new Droite(
                new Point2D(getCoordonnees().getX()+longueur,getCoordonnees().getY()),
                new Point2D(getCoordonnees().getX()+longueur,getCoordonnees().getY()+largeur));
    }

    public Droite coteHaut()
    {
        return new Droite(
                getCoordonnees(),
                new Point2D(getCoordonnees().getX()+longueur,getCoordonnees().getY()));
    }

    public Droite coteBas()
    {
        return new Droite(
                new Point2D(getCoordonnees().getX(),getCoordonnees().getY()+largeur),
                new Point2D(getCoordonnees().getX()+longueur,getCoordonnees().getY()+largeur));
    }

    public Point2D pointHG() {
        return new Point2D(
                getCoordonnees().getX(),
                getCoordonnees().getY()
        );
    }

    public Point2D pointHD() {
        return new Point2D(
                getCoordonnees().getX()+longueur,
                getCoordonnees().getY()
        );
    }

    public Point2D pointBG() {
        return new Point2D(
                getCoordonnees().getX(),
                getCoordonnees().getY()+largeur
        );
    }

    public Point2D pointBD() {
        return new Point2D(
                getCoordonnees().getX()+longueur,
                getCoordonnees().getY()+largeur
        );
    }
}
