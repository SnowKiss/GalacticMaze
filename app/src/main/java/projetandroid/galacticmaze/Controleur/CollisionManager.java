package projetandroid.galacticmaze.Controleur;
import projetandroid.galacticmaze.Modele.Bille;
import projetandroid.galacticmaze.Modele.Calcul;
import projetandroid.galacticmaze.Modele.Point2D;
import projetandroid.galacticmaze.Modele.WinZone;
import projetandroid.galacticmaze.Modele.Zone;

/**
 * Created by John on 23/01/2018.
 */

public class CollisionManager {
    public boolean billeDansZone(Bille player, Zone zone) {
        return Calcul.distance2Points(
                new Point2D(
                        player.getCoordonnees().getX()+player.getRayon(),
                        player.getCoordonnees().getY()+player.getRayon()),
                new Point2D(
                        zone.getCoordonnees().getX()+zone.getRayon(),
                        zone.getCoordonnees().getY()+zone.getRayon()
                )) < (zone.getRayon()-player.getRayon());
    }
}
