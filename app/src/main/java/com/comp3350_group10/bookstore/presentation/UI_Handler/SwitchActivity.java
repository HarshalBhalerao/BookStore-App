/**
 * This class provides method to switch activity
 */

package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.app.Activity;
import android.content.Intent;

public class SwitchActivity
{
    public static void SwitchTo(Class destinationClass, Activity CurrentActivity)
    {
        Intent intent = new Intent(CurrentActivity.getBaseContext(), destinationClass);
        CurrentActivity.startActivity(intent);
    }
}
