package com.comp3350_group10.bookstore.persistence.hsqldb;

import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.comp3350_group10.bookstore.R;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ImageReferences {
    private static Dictionary<Integer, Integer> coverImages;

    public static void FillDictionary()
    {
        coverImages = new Hashtable<>();
        coverImages.put(700132, R.drawable.philosophers_stone);
        coverImages.put(700069, R.drawable.harry_potter_and_the_chamber_of_secrets);
        coverImages.put(700104, R.drawable.the_da_vinci_code);
        coverImages.put(700153, R.drawable.angels_demons);
        coverImages.put(700031, R.drawable.diary_of_wimpy_kid_the_getaway);
        coverImages.put(700003, R.drawable.diary_of_wimpy_kid_double_down);
        coverImages.put(700160, R.drawable.prisoner_of_azkaban);
        coverImages.put(700064, R.drawable.harry_potter_and_the_goblet_fire);
        coverImages.put(700007, R.drawable.harry_potter_and_the_order_of_the_phoenix);
        coverImages.put(700043, R.drawable.harry_potter_and_the_half_blood_prince);
        coverImages.put(700105, R.drawable.harry_potter_and_the_deathly_hallows);
        coverImages.put(700113, R.drawable.harry_potter_and_the_cursed_child);
        coverImages.put(700141, R.drawable.twilight);
        coverImages.put(700116, R.drawable.eclipse);
        coverImages.put(700056, R.drawable.new_moon);
        coverImages.put(700033, R.drawable.breaking_dawn);
        coverImages.put(700062, R.drawable.midnightsun);
        coverImages.put(700077, R.drawable.lotr);
        coverImages.put(700079, R.drawable.the_two_towers);
        coverImages.put(700130, R.drawable.the_book_of_lost_tails);
        coverImages.put(700127, R.drawable.the_children_of_hurin);
    }

    public static int Get(int key)
    {
        return coverImages.get(key);
    }
}