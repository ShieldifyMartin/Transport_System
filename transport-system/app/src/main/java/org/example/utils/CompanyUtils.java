package org.example.utils;

import java.security.SecureRandom;

public class CompanyUtils {
    private static final String VAT_PREFIX = "VAT";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int VAT_LENGTH = 11; // VAT prefix + 8 random characters

    public static String generateRandomVAT() {
        SecureRandom random = new SecureRandom();
        StringBuilder vat = new StringBuilder(VAT_PREFIX);

        for (int i = 0; i < VAT_LENGTH - VAT_PREFIX.length(); i++) {
            int index = random.nextInt(CHARACTERS.length());
            vat.append(CHARACTERS.charAt(index));
        }

        return vat.toString();
    }
}