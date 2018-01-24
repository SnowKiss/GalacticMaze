package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 24/01/2018.
 */

public class Calcul {
    public static float distance2Points(Point2D A, Point2D B) {
        return (float) Math.sqrt((B.getX()-A.getX())*(B.getX()-A.getX())+(B.getY()-A.getY())*(B.getY()-A.getY()));
    }
}
