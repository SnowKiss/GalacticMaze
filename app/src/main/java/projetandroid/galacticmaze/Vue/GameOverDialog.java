package projetandroid.galacticmaze.Vue;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by John on 25/01/2018.
 */

public class GameOverDialog {
    private Activity gameActivity;

    public GameOverDialog(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public void afficherResultat(String titre, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(gameActivity).create();
        alertDialog.setTitle(titre);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        gameActivity.finish();
                    }
                });
        alertDialog.show();
    }
}
