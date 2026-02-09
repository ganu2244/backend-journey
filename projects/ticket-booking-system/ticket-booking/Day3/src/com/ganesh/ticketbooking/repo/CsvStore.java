package com.ganesh.ticketbooking.repo;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CsvStore {

    public static List<String> readAllLines(String path) {
        try {
            Path p = Paths.get(path);
            if (!Files.exists(p)) return new ArrayList<>();
            return Files.readAllLines(p);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }

    public static void writeAllLines(String path, List<String> lines) {
        try {
            Path p = Paths.get(path);
            Files.createDirectories(p.getParent());
            Files.write(p, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + path, e);
        }
    }
}
