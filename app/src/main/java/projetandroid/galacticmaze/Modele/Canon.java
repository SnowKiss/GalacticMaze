package projetandroid.galacticmaze.Modele;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by John on 23/01/2018.
 */

public class Canon {
    private Point2D coordonnees;
    private ArrayList<Bille> projectiles;
    private Point2D cible;
    private int cadence; // nombre de frames entre deux tirs

    public Canon(final Point2D coordonnees, final Point2D cible, final int cadence) {
        this.coordonnees = coordonnees;
        this.cible = cible;
        this.projectiles = new ArrayList<Bille>();
        this.cadence = cadence;
        start();
    }

    private void start() {
        final Handler handler=new Handler();

        handler.post(new Runnable(){
            @Override
            public void run() {
                try
                {
                    Bille tmpBille = new Bille(coordonnees, (new Vecteur(new Point2D(0,0),new Point2D(cible.getX()-coordonnees.getX()-20,cible.getY()-coordonnees.getY()-20))).scaled(0.02f),20);
                    //Log.i("X : ",Float.toString(coordonnees.getX()));
                    //Log.i("Y : ",Float.toString(coordonnees.getY()));
                    ajouterProjectile(tmpBille);
                    //Log.i("Coord X :", Float.toString(coordonnees.getX()));
                    //Log.i("Coord Y :", Float.toString(coordonnees.getY()));
                    //Log.i(" "," ");
                    //Log.i("Cible X :", Float.toString(cible.getX()));
                    //Log.i("Cible Y :", Float.toString(cible.getY()));
                    handler.postDelayed(this,cadence);
                }
                catch (Exception e)
                {

                }

            }
        });
    }

    private void ajouterProjectile(Bille bille) {
        projectiles.add(bille);
    }

    public Point2D getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Point2D coordonnees) {
        this.coordonnees = coordonnees;
    }

    public ArrayList<Bille> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Bille> projectiles) {
        this.projectiles = projectiles;
    }

    public Point2D getCible() {
        return cible;
    }

    public void setCible(Point2D cible) {
        this.cible = cible;
    }
}
