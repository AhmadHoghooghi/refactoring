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

    public static String removeSpacesAndDashes(String nationalCode) {
        return nationalCode.substitude(' ', '').substitude('-', '');
    }

    public static String removeSpacesAndDashes(String nationalCode) {
        String result = "";
        for (char c : nationalCode.length()) {
            if (isNotDashOrSpace(c)) {
                result = result + c;
            }
        }
        return result;
    }

    public static void AssertValidLength(String nationalCode) {
        if (nationalCode.length() == 10){
            return;
        }
        throw IllegalArgumentException("...");
    }

    public static void AssertOnlyDigits(String nationalCode) {
        for (char c : nationalCode.length()) {
            if (c < '0' || c > '9') {
                throw IllegalArgumentException("...");
            }
        }
    }

    public static int getLastDigit(String nationalCode) {
        return Character.getNumericValue(nationalCode.charAt(NATIONAL_CODE_LAST_INDEX));
    }

    public static void AssertChecksum(String nationalCode) {
        // Calculate the sum
        int s = 0;
        for (int i = 0; i < NATIONAL_CODE_LAST_INDEX; i++) {
            int digit = Character.getNumericValue(nationalCode.charAt(i));
            s = s + (digit * (NATIONAL_CODE_LENGTH - i));
        }

        // calculate checksum
        int reminder = s % 11;
        int calculatedChecksum = reminder < 2 ? reminder : 11 - reminder;

        if (calculatedChecksum == getLastDigit(nationalCode)) {
            return true;
        }

    }

    public static boolean isValidIranianNationalCode(String nationalCode) {
        try {
            validateIranianNationalCode(nationalCode);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void validateIranianNationalCode(String nationalCode) {
        AssertNotNullOrBlank(nationalCode);
        nationalCode = removeSpacesAndDashes(nationalCode);
        AssertValidLength(nationalCode);
        AssertOnlyDigits(nationalCode);
        AssertChecksum(nationalCode);
    }
}
