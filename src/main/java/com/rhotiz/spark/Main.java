package com.rhotiz.spark;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String[] testCodes = {
                "0076228635",  // valid
                "1234567891",  // invalid
                "1111111111",  // invalid (all same digits)
                "0499370899",  // valid
                "0790419904",  // valid
                "0084575948",  // valid
                "0067749826",  // valid
                "0000000000",  // invalid
                "123",        // invalid (too short)
                "123456789012" // invalid (too long)
        };

        for (String code : testCodes) {
            boolean isValid = isValidIranianNationalCode(code);
            System.out.println(code + " is " + (isValid ? "valid" : "invalid"));
        }

    }


    public static boolean isValidIranianNationalCode(String nationalCode) {
        // Check if the input is null or empty
        if (nationalCode == null || nationalCode.trim().isEmpty()) {
            return false;
        }

        // Remove any non-digit characters
        nationalCode = nationalCode.replaceAll("[^0-9]", "");

        // Check length (should be 10 digits)
        if (nationalCode.length() != 10) {
            return false;
        }

        // Check for invalid patterns (all digits same)
        if (nationalCode.matches("(\\d)\\1{9}")) {
            return false;
        }

        // Calculate the checksum
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(nationalCode.charAt(i));
            sum += digit * (10 - i);
        }

        int remainder = sum % 11;
        int controlDigit = Character.getNumericValue(nationalCode.charAt(9));

        // Validate the control digit
        if (remainder < 2) {
            return controlDigit == remainder;
        } else {
            return controlDigit == (11 - remainder);
        }
    }
}