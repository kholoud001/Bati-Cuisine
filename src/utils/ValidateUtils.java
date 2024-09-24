package utils;

import java.util.regex.Pattern;

public class ValidateUtils {

    public static boolean isValidPhone(String phone) {
        String phoneRegex = "(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}";
        boolean valid = Pattern.matches(phoneRegex, phone);

        if (!valid) {
            System.out.println("Format de numéro de téléphone invalide. Cela devrait être exactement comme ça : +212 Y XX XX XX XX.");
        }
        return valid;
    }


    public static String validateNom(String nom) {
        nom = nom.trim();

        if (nom.isEmpty()) {
            System.out.println("Le nom ne peut pas être vide.");
            return null;
        } else if (!nom.matches("[a-zA-ZÀ-ÿ ]+")) {
            System.out.println("Le nom ne peut contenir que des lettres et des espaces.");
            return null;
        }
        return nom;
    }

    
    public static boolean isNotNullOrEmpty(String str) {
        boolean chaine= str != null && !str.trim().isEmpty();
        if(!chaine) {
            System.out.println("ce champ ne peut pas être vide.");
        }
        return chaine;
    }


}
