package com.weather.client.outfit.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;

public class ResourceUtils {

    public static String read(String filePath) {
        try {
            File file = org.springframework.util.ResourceUtils.getFile(filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new UncheckedIOException("Resource " + filePath + " not found.", e);
        }
    }

}
