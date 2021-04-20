package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.objects.IBook;
import java.util.ArrayList;
import java.util.List;

public class Notify extends AppCompatActivity {
    private static final List<IBook> lowStockNotified = new ArrayList<>();

    public static List<IBook> getLowStockNotified() {
        return lowStockNotified;
    }

    //have to create notification channel for devices with Oreo version or higher,
    // can be ignored for versions lower than Oreo
    public void checkAndroidVersion(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //create a low stock notification
    public void lowStockNotification(Context context){
        IBook currentBook = BookDataHandler.currentBook;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"Notification");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        //Notified when stock is less than 10, but more than 0
        if(!lowStockNotified.contains(currentBook) && currentBook.getStock()>0 && currentBook.getStock()<10){
            builder.setContentTitle("Low Stock Alert");
            builder.setContentText("Stock is les than 10");
            builder.setAutoCancel(true);
            displayNotification(context,builder);
            lowStockNotified.add(BookDataHandler.currentBook);
        }
        //Notified when stock finishes i.e, stock is 0
        else if(lowStockNotified.contains(currentBook) && currentBook.getStock()==0){
            builder.setContentTitle("LOW STOCK ALERT");
            builder.setContentText("Available stock is 0");
            builder.setAutoCancel(true);
            displayNotification(context,builder);
        }

    }

    //display low stock notification
    private void displayNotification(Context context, NotificationCompat.Builder builder){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1,builder.build());
    }

    //remove from the low stock books list,
    // once the available stock is more than 10
    public void removeFromLowStockList(IBook book){
        if(lowStockNotified.contains(book) && book.getStock()>=10){
            lowStockNotified.remove(book);
        }
    }

}
