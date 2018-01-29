package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 23/01/2018.
 */

public class Trou extends Zone {

    private float zoneAttraction;

    public Trou(Point2D coordonnees) {
        super(coordonnees);
        this.zoneAttraction = 20;
    }

    public Trou(Point2D coordonnees, float rayon) {
        super(coordonnees, rayon);
        this.zoneAttraction = 20;
    }

    public float getZoneAttraction() {
        return zoneAttraction;
    }

    public void setZoneAttraction(float zoneAttraction) {
        this.zoneAttraction = zoneAttraction;
    }

    public Point2D getCentre() {
        return new Point2D(
                getCoordonnees().getX()+getRayon(),
                getCoordonnees().getY()+getRayon()
        );
    }
}
