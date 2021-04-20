package com.comp3350_group10.bookstore.application;

import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import junit.framework.TestCase;

import java.io.File;

public class ServiceTest extends TestCase {
    private File tempDB;
    public void setUp() throws Exception {
        super.setUp();
        this.tempDB = TestUtils.copyDB();
    }

    public void tearDown() throws Exception {
        this.tempDB.delete();
    }

    public void testSetupBookDatabase() {
        IBookDatabase db = Service.setupBookDatabase();
        assertNotNull("This db should not be null",db);
    }

    public void testSetupUserDatabase() {
        IUserDatabase db = Service.setupUserDatabase();
        assertNotNull("This db should not be null",db);
    }
}