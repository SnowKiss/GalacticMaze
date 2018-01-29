package projetandroid.galacticmaze.Modele;

import android.util.Log;

/**
 * Created by John on 24/01/2018.
 */

public class Calcul {

    public static float distance2Points(Point2D A, Point2D B) {
        return (float) Math.sqrt((B.getX()-A.getX())*(B.getX()-A.getX())+(B.getY()-A.getY())*(B.getY()-A.getY()));
    }

    public static Vecteur imageVecteur(Vecteur vecteur, Droite mur)
    {
        float x = vecteur.getFin().getX()-((2*mur.getA())*(mur.getA()*vecteur.getFin().getX()+mur.getB()*vecteur.getFin().getY()+mur.getC())/(mur.getA()*mur.getA()+mur.getB()*mur.getB()))-vecteur.getOrigine().getX()+((2*mur.getA())*(mur.getA()*vecteur.getOrigine().getX()+mur.getB()*vecteur.getOrigine().getY()+mur.getC())/(mur.getA()*mur.getA()+mur.getB()*mur.getB()));
        float y = vecteur.getFin().getY()-((2*mur.getB())*(mur.getA()*vecteur.getFin().getX()+mur.getB()*vecteur.getFin().getY()+mur.getC())/(mur.getA()*mur.getA()+mur.getB()*mur.getB()))-vecteur.getOrigine().getY()+((2*mur.getB())*(mur.getA()*vecteur.getOrigine().getX()+mur.getB()*vecteur.getOrigine().getY()+mur.getC())/(mur.getA()*mur.getA()+mur.getB()*mur.getB()));
        float coefRebond = 0.8f;
        Vecteur v = new Vecteur(
                new Point2D(
                        0,
                        0),
                new Point2D(
                        x*coefRebond,
                        y*coefRebond)
                );
        //Log.i("TEST","ORIGINE");
        //Log.i(Float.toString(vecteur.getOrigine().getX()),Float.toString(vecteur.getOrigine().getY()));
        //Log.i(Float.toString(vecteur.getFin().getX()),Float.toString(vecteur.getFin().getY()));

        //Log.i("TEST","IMAGE");
        //Log.i(Float.toString(v.getOrigine().getX()),Float.toString(v.getOrigine().getY()));
        //Log.i(Float.toString(v.getFin().getX()),Float.toString(v.getFin().getY()));
        return v;
        //return new Vecteur(new Point2D(0,0), new Point2D(-vecteur.getFin().getX(),-vecteur.getFin().getY()));
    }
    public static float distanceDroitePoint(Droite d, Point2D p)
    {
        return (float)((Math.abs(d.getA()*p.getX()+d.getB()*p.getY()+d.getC()))/(Math.sqrt(d.getA()*d.getA()+d.getB()*d.getB())));
    }

}
