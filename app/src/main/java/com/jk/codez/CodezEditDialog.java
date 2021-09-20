package com.jk.codez;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CodezEditDialog {

    public CodezEditDialog(Activity a) {
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
// Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
// Set other dialog properties


// Create the AlertDialog
        AlertDialog dialog = builder.create();
    }

}
