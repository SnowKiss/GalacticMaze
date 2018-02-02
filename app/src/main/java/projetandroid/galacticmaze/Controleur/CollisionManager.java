package projetandroid.galacticmaze.Controleur;
import android.app.Activity;
import android.media.MediaPlayer;
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
import projetandroid.galacticmaze.R;

/**
 * Created by John on 23/01/2018.
 */

public class CollisionManager {
    private static Activity gameActivity;
    private static MediaPlayer wallCollision;
    private static MediaPlayer ballCollision;

    public CollisionManager(Activity gameActivity) {
        this.gameActivity = gameActivity;
        wallCollision = MediaPlayer.create(gameActivity, R.raw.wall);
        ballCollision = MediaPlayer.create(gameActivity, R.raw.bump);
    }

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

    public static void applyCollision(Bille player, Bille bille, Labyrinthe maze, boolean joueur) {

        // COLLISION AVEC LES MURS

        // On récupère l'index du premier conteneur vide
        int index = indexConteneur(bille,maze.getMurs());
        int nbConteneurs = countConteneur(bille, maze.getMurs());
        Log.i("Nombre de Conteneurs",Integer.toString(nbConteneurs));
        //Log.i("Index 1:",Integer.toString(index));
        //Log.i("Conteneur",Integer.toString(index));

        int i;
        for(i = index; i<maze.getMurs().size();i++)
        {
            // On verifie la collision avec les objets à partir du conteneur (inclus)
            try
            {
                Rectangle r = (Rectangle)maze.getMurs().get(i);
                if(r.isPlein())
                {
                    if(billeDansLongueur2(bille, r) && billeDansLargeur2(bille, r))
                    {
                        if(
                                (Calcul.distance2Points(bille.getCentre(),r.pointHG())<bille.getRayon())||
                                (Calcul.distance2Points(bille.getCentre(),r.pointBG())<bille.getRayon()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteGauche()));
                            playCollisionMur();
                        }
                        else if(
                                (Calcul.distance2Points(bille.getCentre(),r.pointHD())<bille.getRayon())||
                                (Calcul.distance2Points(bille.getCentre(),r.pointBD())<bille.getRayon()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteDroite()));
                            playCollisionMur();
                        }
                    }
                    if((billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteGauche())))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteGauche()));
                        playCollisionMur();
                    }
                    else if(billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteDroite()))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteDroite()));
                        playCollisionMur();
                    }
                    else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteHaut()))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteHaut()));
                        playCollisionMur();
                    }
                    else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteBas()))
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteBas()));
                        playCollisionMur();
                    }
                }
                else if(i==index+1)
                {

                    if(nbConteneurs<1)
                    {
                        if(billeDansLongueur2(bille, r) && billeDansLargeur2(bille, r))
                        {
                            if(
                                    (Calcul.distance2Points(bille.getCentre(),r.pointHG())<bille.getRayon())||
                                            (Calcul.distance2Points(bille.getCentre(),r.pointBG())<bille.getRayon()))
                            {
                                player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteGauche()));
                                playCollisionMur();
                            }
                            else if(
                                    (Calcul.distance2Points(bille.getCentre(),r.pointHD())<bille.getRayon())||
                                            (Calcul.distance2Points(bille.getCentre(),r.pointBD())<bille.getRayon()))
                            {
                                player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteDroite()));
                                playCollisionMur();
                            }
                        }
                        if((billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteGauche())))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteGauche()));
                            playCollisionMur();
                        }
                        else if(billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteDroite()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteDroite()));
                            playCollisionMur();
                        }
                        else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteHaut()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteHaut()));
                            playCollisionMur();
                        }
                        else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteBas()))
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),r.coteBas()));
                            playCollisionMur();
                        }
                    }
                }
            }
            catch(Exception e)
            {
                //Log.i("COUCOU", "JE SUIS UN CERCLE MDR");
                Cercle c = (Cercle)maze.getMurs().get(i);
                //Log.i(Integer.toString(i),Boolean.toString(c.isPlein()));
                if(i==index)
                {
                    if(Calcul.distance2Points(bille.getCentre(),c.getCoordonnees())+bille.getRayon()>c.getRayon())
                    {
                        if(nbConteneurs-1==0)
                        {
                            player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),c.getTangente(player)));
                            //Log.i("BIM !!!! :", Integer.toString(i));
                            playCollisionMur();
                        }

                    }
                }
                else if(c.isPlein())
                {
                    if(Calcul.distance2Points(bille.getCentre(),c.getCoordonnees())<bille.getRayon()+c.getRayon())
                    {
                        player.setVitesse(Calcul.imageVecteur(bille.getVitesse(),c.getTangente(player)));
                        playCollisionMur();
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

        // COLLISION AVEC LES AUTRES BILLES
        /*if(joueur)
        {
            for(Bille projectile:maze.getCanon().getProjectiles())
            {
                if(Calcul.distance2Points(bille.getCentre(),projectile.getCentre())<bille.getRayon()+projectile.getRayon())
                {
                    Vecteur v = player.getVitesse();
                    player.setVitesse(projectile.getVitesse());
                    projectile.setVitesse(v);
                }
            }
        }*/
        if(maze.getCanon()!=null)
        {
            for (Bille proj : maze.getCanon().getProjectiles())
            {
                //si c'est pas lui meme
                if(proj!=player)
                {
                    if(Calcul.distance2Points(bille.getCentre(),proj.getCentre())<bille.getRayon()+proj.getRayon())
                    {
                        Vecteur v = player.getVitesse();
                        player.setVitesse(proj.getVitesse());
                        proj.setVitesse(v);
                        playCollisionBille();
                    }
                }
            }
        }


    }

    private static int countConteneur(Bille bille, ArrayList<Forme> murs) {
        int count = 0;
        int index;
        for (index= murs.size()-1;index>=0;index--)
        {
            if(murs.get(index).isPlein())
            {
                // si la bille est dans la forme entierement ou partiellement (centre à l'interieur)
                    // return count
                try
                {
                    Rectangle r = (Rectangle) murs.get(index);
                    if(billeDansLongueur2(bille, r) && billeDansLargeur2(bille, r))
                    {
                        if(
                                (Calcul.distance2Points(bille.getCentre(),r.pointHG())<bille.getRayon())||
                                        (Calcul.distance2Points(bille.getCentre(),r.pointBG())<bille.getRayon()))
                        {
                            return count;
                        }
                        else if(
                                (Calcul.distance2Points(bille.getCentre(),r.pointHD())<bille.getRayon())||
                                        (Calcul.distance2Points(bille.getCentre(),r.pointBD())<bille.getRayon()))
                        {
                            return count;
                        }
                    }
                    if((billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteGauche())))
                    {
                        return count;
                    }
                    else if(billeDansLargeur(bille,r) && billeContreDroite(bille, r.coteDroite()))
                    {
                        return count;
                    }
                    else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteHaut()))
                    {
                        return count;
                    }
                    else if(billeDansLongueur(bille,r) && billeContreDroite(bille, r.coteBas()))
                    {
                        return count;
                    }

                }
                catch(Exception e)
                {
                    Cercle c = (Cercle) murs.get(index);
                    if(Calcul.distance2Points(bille.getCentre(),c.getCoordonnees())<bille.getRayon()+c.getRayon())
                    {
                        return count;
                    }
                }

            }
            else
            {
                // si la bille est dans la forme entierement ou partiellement (centre à l'interieur)
                    // +1
                try
                {
                    Rectangle r = (Rectangle) murs.get(index);
                    if(billeDansRectangle(bille,r))
                        count++;
                }
                catch(Exception e)
                {
                    Cercle c = (Cercle) murs.get(index);
                    if(Calcul.distance2Points(bille.getCentre(),c.getCoordonnees())<bille.getRayon()+c.getRayon())
                    {
                        count++;
                    }
                }
            }


        }
        return count;
    }

    private static void playCollisionBille() {
        ballCollision.seekTo(0);
        ballCollision.start();
    }

    private static void playCollisionMur() {
        ballCollision.seekTo(0);
        wallCollision.start();
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
                //Log.i("COUCOU", "JE SUIS VIDE");
                try
                {
                    Rectangle rect = (Rectangle) murs.get(i);
                    if(billeDansRectangle(bille, rect))
                        return i;
                }
                catch (Exception e)
                {
                    //Log.i("COUCOU", "JE SUIS UN CERCLE MDR");
                    Cercle cer = (Cercle) murs.get(i);
                    if(centreDansCercle(bille, cer))
                    {
                        //Log.i("COUCOU", "c mwa");
                        return i;
                    }
                }
            }

        }
        return 0;
    }

    private static boolean centreDansCercle(Bille bille, Cercle cer) {
        return (Calcul.distance2Points(bille.getCentre(),cer.getCoordonnees())<cer.getRayon());
    }
}
