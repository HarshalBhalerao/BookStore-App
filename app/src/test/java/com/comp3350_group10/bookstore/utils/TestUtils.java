package com.comp3350_group10.bookstore.utils;

import java.nio.file.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

import com.comp3350_group10.bookstore.application.Main;



public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/BS.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        //copy from DB_SRC to Target
        Files.copy(DB_SRC.toPath(),target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Main.setDBPath(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
