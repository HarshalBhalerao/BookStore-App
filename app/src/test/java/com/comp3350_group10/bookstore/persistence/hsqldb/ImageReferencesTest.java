package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.R;

import junit.framework.TestCase;

public class ImageReferencesTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testFillDictionary() {
        //ImageReferences collection = new ImageReferences();
        ImageReferences.FillDictionary();
        assertEquals("They key should be different than 0", R.drawable.philosophers_stone,ImageReferences.Get(700132));
    }
}