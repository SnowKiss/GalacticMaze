package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public class Vecteur {
    private Point2D origine;
    private Point2D fin;

    public Vecteur(Point2D origine, Point2D fin) {
        this.origine = origine;
        this.fin = fin;
    }

    public Point2D getOrigine() {
        return origine;
    }

    public void setOrigine(Point2D origine) {
        this.origine = origine;
    }

    public Point2D getFin() {
        return fin;
    }

    public void setFin(Point2D fin) {
        this.fin = fin;
    }

    public Vecteur scaled(float facteur) {
        return new Vecteur(new Point2D(origine.getX()*facteur,origine.getY()*facteur),new Point2D(fin.getX()*facteur,fin.getY()*facteur));
    }
}
