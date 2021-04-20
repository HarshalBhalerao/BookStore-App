/**
 * This class provides methods to show popup messages on GUI
 */
package com.comp3350_group10.bookstore.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import com.comp3350_group10.bookstore.R;

import android.content.Context;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class Messages {
    //warning message
    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }

    //Regular popups
    public static void viewPopUp(String message, Context context){
        Toast.makeText(context, message, LENGTH_LONG).show();
    }
}
