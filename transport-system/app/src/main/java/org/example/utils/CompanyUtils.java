package org.example.utils;

import java.security.SecureRandom;

public class CompanyUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String VAT_PREFIX = "VAT";
    private static final int VAT_LENGTH = 11; // VAT prefix + 8 random characters
    private static final int EMAIL_LENGTH = 6;
    private static final String EMAIL_DOMAIN = "@gmail.com";

    public static String generateRandomVAT() {
        SecureRandom random = new SecureRandom();
        StringBuilder vat = new StringBuilder(VAT_PREFIX);

        for (int i = 0; i < VAT_LENGTH - VAT_PREFIX.length(); i++) {
            int index = random.nextInt(CHARACTERS.length());
            vat.append(CHARACTERS.charAt(index));
        }

        return vat.toString();
    }

    public static String generateRandomEmail() {
        SecureRandom random = new SecureRandom();
        StringBuilder email = new StringBuilder();

        for (int i = 0; i < EMAIL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            email.append(CHARACTERS.charAt(index));
        }

        email.append(EMAIL_DOMAIN);
        return email.toString();
    }
}