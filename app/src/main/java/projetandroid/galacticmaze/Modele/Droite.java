package projetandroid.galacticmaze.Modele;

/**
 * Created by John on 25/01/2018.
 */

public class Droite {

    // aX + bY + c= 0
    private float a;
    private float b;
    private float c;

    public Droite(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Droite(Point2D A, Point2D B)
    {
        setB(A.getX()-B.getX());
        setA(B.getY()-A.getY());
        setC(-(A.getX()*a+A.getY()*b));
    }

    public Droite perpendiculaire(Point2D p)
    {
        // TODO perpendiculaire en un point
        return (new Droite(getB(),-getA(),getC()));
    }

    public boolean estPerpendiculaire(Droite d)
    {
        return (this.b * d.getB())+(this.a * d.getA()) == 0;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }
}
