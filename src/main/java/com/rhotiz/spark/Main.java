package com.rhotiz.spark;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Boolean> nationalCodeToValidity = new HashMap<>();
        nationalCodeToValidity.put("0076228635", false);
        nationalCodeToValidity.put("1234567891", true);
        nationalCodeToValidity.put("0499370899", true);
        nationalCodeToValidity.put("0499 3708 99", true);
        nationalCodeToValidity.put("0499-3708-99", true);
        nationalCodeToValidity.put("0790419904", true);
        nationalCodeToValidity.put("0084575948", true);
        nationalCodeToValidity.put("0067749826", false);
        nationalCodeToValidity.put("0067749828", true);
        nationalCodeToValidity.put("123", false);
        nationalCodeToValidity.put("123456789012", false);

        for (Map.Entry<String, Boolean> entry : nationalCodeToValidity.entrySet()) {
            boolean value = isValidIranianNationalCode(entry.getKey());
            if (value != entry.getValue()) {
                throw new RuntimeException(entry.toString());
            }
        }

    }


    public static boolean isValidIranianNationalCode(String nc) {

        if (nc != null && !nc.trim().isEmpty()) { // Check if the input is null or empty

            // Remove any non-digit characters
            String newNc = "";
            for (int i = 0; i < nc.length(); i++) {
                char c = nc.charAt(i);
                if (c != '-' && c != ' ') {
                    newNc = newNc + c;
                }
            }
            nc = newNc;


            if (nc.length() == 10) { // Check length (should be 10 digits)
                for (int i = 0; i < nc.length(); i++) {
                    char c = nc.charAt(i);
                    if (c < '0' || c > '9') {
                        System.out.println("National code can only contain digits");
                        return false;
                    }
                }

                // Calculate the sum
                int s = 0;
                for (int i = 0; i < 9; i++) {
                    int digit = Character.getNumericValue(nc.charAt(i));
                    s = s + (digit * (10 - i));
                }

                // calculate checksum
                int remainder = s % 11;
                int calculatedChecksum;

                if (remainder < 2)
                    calculatedChecksum = remainder;
                else
                    calculatedChecksum = 11 - remainder;

                if (calculatedChecksum == Character.getNumericValue(nc.charAt(9))) {
                    return true;
                }
                System.out.println("Invalid National code");
                return false;
            } else {
                System.out.println("Only 10 digit national codes are valid.");
                return false;
            }

        } else {
            System.out.println("Entered national code is empty.");
            return false;
        }
    }
}