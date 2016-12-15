package hu.penzestamas.gomoku.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by penzes.tamas on 2016.10.19..
 */

public class Config {


    public static final int NULLSOR = 7;
    public static final int O_SOR = 35;
    public static final int OO_SOR = 800;
    public static final int OOO_SOR = 15000;
    public static final int OOOO_SOR = 800000;
    public static final int X_SOR = 15;
    public static final int XX_SOR = 400;
    public static final int XXX_SOR = 1800;
    public static final int XXXX_SOR = 100000;

    public static final int[][] AI_VALUES = {   {NULLSOR,  X_SOR,    XX_SOR,   XXX_SOR,  XXXX_SOR,         0},
                                                {O_SOR,    0,          0,          0,          0,          0},
                                                {OO_SOR,   0,          0,          0,          0,          0},
                                                {OOO_SOR,  0,          0,          0,          0,          0},
                                                {OOOO_SOR, 0,          0,          0,          0,          0},
                                                {0,    0,          0,          0,          0,           0}};

    public static final int[][] PLAYER_VALUES = {   {NULLSOR,  O_SOR,    OO_SOR,   OOO_SOR,  OOOO_SOR,         0},
                                                    {X_SOR,    0,          0,          0,          0,          0},
                                                    {XX_SOR,   0,          0,          0,          0,          0},
                                                    {XXX_SOR,  0,          0,          0,          0,          0},
                                                    {XXXX_SOR, 0,          0,          0,          0,          0},
                                                    {0,    0,          0,          0,          0,           0}};


    public static void alertPopUp(Context context, String title, String message){
        AlertDialog.Builder newdialog = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        newdialog.setMessage(message).setTitle(title).setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(true);

        newdialog.setPositiveButton("Rendben", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();


            }
        });

        newdialog.create().show();
    }
}
