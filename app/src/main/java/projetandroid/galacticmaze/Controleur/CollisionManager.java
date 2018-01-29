package projetandroid.galacticmaze.Controleur;
import android.util.Log;

import java.util.ArrayList;

import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Calcul;
import projetandroid.galacticmaze.Modele.Cercle;
import projetandroid.galacticmaze.Modele.Droite;
import projetandroid.galacticmaze.Modele.Forme;
import projetandroid.galacticmaze.Modele.Labyrinthe;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.Rectangle;
import projetandroid.galacticmaze.Modele.Trou;
import projetandroid.galacticmaze.Modele.Vecteur;
import projetandroid.galacticmaze.Modele.WinZone;
import projetandroid.galacticmaze.Modele.Zone;

/**
 * Created by John on 23/01/2018.
 */

public class CollisionManager {
    public boolean billeDansZone(Bille player, Zone zone) {

        return Calcul.distance2Points(
                // centre de la bille
                new Point2D(
                        player.getCoordonnees().getX()+player.getRayon(),
                        player.getCoordonnees().getY()+player.getRayon()),
                // centre de la zone
                new Point2D(
                        zone.getCoordonnees().getX()+zone.getRayon(),
                        zone.getCoordonnees().getY()+zone.getRayon()
                )) < (zone.getRayon()-player.getRayon());

    }

    public static boolean billeDansCercle(Bille player, Cercle cercle) {

        return Calcul.distance2Points(
                // centre de la bille
                new Point2D(
                        player.getCoordonnees().getX()+player.getRayon(),
                        player.getCoordonnees().getY()+player.getRayon()),
                // centre du cercle
                new Point2D(
                        cercle.getCoordonnees().getX(),
                        cercle.getCoordonnees().getY()
                )) < (cercle.getRayon()-player.getRayon());

    }

    public static boolean billeDansRectangle(Bille player, Rectangle rectangle)
    {

        return
                billeDansLargeur(player, rectangle) &&
                billeDansLongueur(player, rectangle);

    }

    public static boolean billeContreDroite(Bille player, Droite d)
    {
        return (Calcul.distanceDroitePoint(d,player.getCentre())<player.getRayon());
    }

    public static void applyCollision(Bille player, Bille bille, Labyrinthe maze) {

        // COLLISION AVEC LES MURS

        // On récupère l'index du premier conteneur vide
        int index = indexConteneur(bille,maze.getMurs());
        //Log.i("Conteneur",Integer.toString(index));

        int i;
        for(i = index+1; i<maze.getMurs().size();i++)
        {
            // On verifie la collision avec les objets à partir du conteneur (inclus)
            try
            {
                Rectangle r = (Rectangle)maze.getMurs().get(i);
                if(r.isPlein()||i==index+1)
                {
                    if(billeDansLongueur2(bille, r) && billeDansLargeur2(bille, r))
                    {
                        if(
                                (Calcul.distance2Points(bille.getCentre(),r.pointHG())<bille.getRayon())||
                                (Calcul.distance2Points(bille.getCentre(),r.pointBG())<bille.getRayon()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteGauche()));
                        }
                        else if(
                                (Calcul.distance2Points(bille.getCentre(),r.pointHD())<bille.getRayon())||
                                (Calcul.distance2Points(bille.getCentre(),r.pointBD())<bille.getRayon()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteDroite()));
                        }
                    }
                    if((billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteGauche())))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteGauche()));
                    }
                    else if(billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteDroite()))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteDroite()));
                    }
                    else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteHaut()))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteHaut()));
                    }
                    else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteBas()))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteBas()));
                    }
                }
            }
            catch(ClassCastException e)
            {
                Cercle c = (Cercle)maze.getMurs().get(i);
                if(i==index)
                {
                    if(Calcul.distance2Points(bille.getCentre(),c.getCoordonnees())<c.getRayon()-bille.getRayon())
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),c.getTangente(player.getCoordonnees())));
                    }
                }
                if(c.isPlein())
                {
                    if(Calcul.distance2Points(bille.getCentre(),c.getCoordonnees())<bille.getRayon()+c.getRayon())
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),c.getTangente(player.getCoordonnees())));
                    }
                }
            }
        }

        // ZONE D'ATTRACTIONS

        for(Trou trou:maze.getTrous())
        {
            if(Calcul.distance2Points(player.getCentre(),trou.getCentre())<player.getRayon()+trou.getRayon()+trou.getZoneAttraction())
            {
                Vecteur attraction = new Vecteur(player.getCentre(),trou.getCentre());
                player.setVitesse(
                        new Vecteur(
                                new Point2D(0,0),
                                new Point2D(
                                        player.getVitesse().getFin().getX()+(attraction.getFin().getX()-attraction.getOrigine().getX())/1000,
                                        player.getVitesse().getFin().getY()+(attraction.getFin().getY()-attraction.getOrigine().getY())/1000
                                )
                        )
                );
            }
        }

    }

    private static boolean billeDansLargeur(Bille bille, Rectangle r)
    {
        return
            // le joueur est sous la paroi haute
            (bille.getCoordonnees().getY()>r.getCoordonnees().getY())&&

            // le joueur est au dessus de la paroi basse
            (bille.getCoordonnees().getY()+2*bille.getRayon()<r.getCoordonnees().getY()+r.getLargeur());
    }

    private static boolean billeDansLongueur(Bille bille, Rectangle r)
    {
        return
            // le joueur est à droite de la paroi gauche
            (bille.getCoordonnees().getX()>r.getCoordonnees().getX())&&

            // le joueur est à gauche de la paroi droite
            (bille.getCoordonnees().getX()+2*bille.getRayon()<r.getCoordonnees().getX()+r.getLongueur());
    }

    private static boolean billeDansLargeur2(Bille bille, Rectangle r)
    {
        return
                // le joueur est sous la paroi haute
                (bille.getCoordonnees().getY()+2*bille.getRayon()>r.getCoordonnees().getY())&&

                // le joueur est au dessus de la paroi basse
                (bille.getCoordonnees().getY()<r.getCoordonnees().getY()+r.getLargeur());
    }

    private static boolean billeDansLongueur2(Bille bille, Rectangle r)
    {
        return
                // le joueur est à droite de la paroi gauche
                (bille.getCoordonnees().getX()+2*bille.getRayon()>r.getCoordonnees().getX())&&

                // le joueur est à gauche de la paroi droite
                (bille.getCoordonnees().getX()<r.getCoordonnees().getX()+r.getLongueur());
    }

    private static int indexConteneur(Bille bille, ArrayList<Forme> murs) {
        int i;
        for(i = murs.size()-1; i>0; i--)
        {
            if(!murs.get(i).isPlein())
            {
                try
                {
                    if(billeDansRectangle(bille, (Rectangle) murs.get(i)))
                        return i;
                }
                catch (Error e)
                {
                    if(billeDansCercle(bille, (Cercle) murs.get(i)))
                        return i;
                }
            }

        }
        return 0;
    }
}
