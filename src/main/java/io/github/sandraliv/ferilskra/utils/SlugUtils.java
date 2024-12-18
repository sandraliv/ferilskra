package io.github.sandraliv.ferilskra.utils;

import java.text.Normalizer;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class SlugUtils {

    private static final Map<String, String> CUSTOM_REPLACEMENTS = new HashMap<>();

    static {
        // Custom replacements
        CUSTOM_REPLACEMENTS.put("æ", "ae");
        CUSTOM_REPLACEMENTS.put("Æ", "ae");
        CUSTOM_REPLACEMENTS.put("ö", "o");
        CUSTOM_REPLACEMENTS.put("Ö", "o");
        CUSTOM_REPLACEMENTS.put("ð", "d");
        CUSTOM_REPLACEMENTS.put("Ð", "d");
    }

    public static String generateSlug(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        // Normalize string (remove accents and special characters)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Apply custom replacements
        for (Map.Entry<String, String> entry : CUSTOM_REPLACEMENTS.entrySet()) {
            normalized = normalized.replace(entry.getKey(), entry.getValue());
        }

        // Replace spaces and special characters with hyphens
        String slug = normalized.replaceAll("[^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();

        return slug;
    }
}
