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


    public static boolean isValidIranianNationalCode(String nationalCode) {
        // Check if the input is null or empty
        if (nationalCode == null || nationalCode.trim().isEmpty()) {
            System.out.println("Entered national code is empty.");
            return false;
        }

        // Remove any non-digit characters
        StringBuilder dashAndSpaceRemoved = new StringBuilder();
        for (int i = 0; i < nationalCode.length(); i++) {
            char c = nationalCode.charAt(i);
            if (c != '-' && c != ' ') {
                dashAndSpaceRemoved.append(c);
            }
        }
        nationalCode = dashAndSpaceRemoved.toString();

        // Check length (should be 10 digits)
        if (nationalCode.length() != 10) {
            System.out.println("Only 10 digit national codes are valid.");
            return false;
        }

        for (int i = 0; i < nationalCode.length(); i++) {
            char c = nationalCode.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println("National code can only contain digits");
                return false;
            }
        }

        // Calculate the checksum
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(nationalCode.charAt(i));
            int nextSum = digit * (10 - i);
            sum += nextSum;
        }

        int remainder = sum % 11;


        int calculatedValidDigit;

        if (remainder < 2) {
            calculatedValidDigit = remainder;
        } else {
            calculatedValidDigit = (11 - remainder);
        }

        if (calculatedValidDigit != Character.getNumericValue(nationalCode.charAt(9))) {
            System.out.println("Invalid National code");
            return false;
        }
        return true;

    }
}