package projetandroid.galacticmaze.Modele;

import java.util.ArrayList;

/**
 * Created by John on 23/01/2018.
 */

public class Labyrinthe {
    private ArrayList<Forme> murs;
    private SpawnZone spawnZone;
    private WinZone winZone;
    private ArrayList<Trou> trous;
    private Canon canon;

    public Labyrinthe(SpawnZone spawnZone, WinZone winZone) {
        this.spawnZone = spawnZone;
        this.winZone = winZone;
        this.murs = new ArrayList<Forme>();
        this.trous = new ArrayList<Trou>();
    }

    public void ajouterZone(Forme nouvelleZone)
    {
        murs.add(nouvelleZone);
    }

    public ArrayList<Forme> getMurs() {
        return murs;
    }

    public void setMurs(ArrayList<Forme> murs) {
        this.murs = murs;
    }

    public SpawnZone getSpawnZone() {
        return spawnZone;
    }

    public void setSpawnZone(SpawnZone spawnZone) {
        this.spawnZone = spawnZone;
    }

    public WinZone getWinZone() {
        return winZone;
    }

    public void setWinZone(WinZone winZone) {
        this.winZone = winZone;
    }

    public ArrayList<Trou> getTrous() {
        return trous;
    }

    public void setTrous(ArrayList<Trou> trous) {
        this.trous = trous;
    }

    public Canon getCanon() {
        return canon;
    }

    public void setCanon(Canon canon) {
        this.canon = canon;
    }
}
