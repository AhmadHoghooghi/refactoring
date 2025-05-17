package com.rhotiz.spark;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        verifyNationalCode("1234567891");
    }


    public static void verifyNationalCode(String nationalCode) {
        verifyIsNotEmpty(nationalCode);
        String refinedNationalCode = removeDashAndSpace(nationalCode);
        verifyLengthIs10(refinedNationalCode);
        verifyOnlyContainsDigits(refinedNationalCode);
        verifyChecksum(refinedNationalCode);
    }

    private static void verifyIsNotEmpty(String nationalCode) {
        if (nationalCode == null || nationalCode.trim().isEmpty()) {
            throw new InvalidNationalCodeException("Entered national code is empty.", nationalCode);
        }
    }

    private static String removeDashAndSpace(String nationalCode) {
        StringBuilder dashAndSpaceRemoved = new StringBuilder();
        for (int i = 0; i < nationalCode.length(); i++) {
            char c = nationalCode.charAt(i);
            if (c != '-' && c != ' ') {
                dashAndSpaceRemoved.append(c);
            }
        }
        return dashAndSpaceRemoved.toString();
    }

    private static int calculateChecksum(String nationalCode) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(nationalCode.charAt(i));
            sum += digit * (10 - i);
        }

        int remainder = sum % 11;
        if (remainder < 2) {
            return remainder;
        } else {
            return (11 - remainder);
        }
    }

    private static void verifyLengthIs10(String nationalCode) {
        if (nationalCode.length() != 10) {
            throw new InvalidNationalCodeException("Length of National code should be 10.", nationalCode);
        }
    }

    private static void verifyOnlyContainsDigits(String nationalCode) {
        for (int i = 0; i < nationalCode.length(); i++) {
            char c = nationalCode.charAt(i);
            if (c < '0' || c > '9') {
                throw new RuntimeException("National code can only contain digits");
            }
        }
    }

    private static void verifyChecksum(String refinedNationalCode) {
        int calculatedChecksum = calculateChecksum(refinedNationalCode);
        int enteredChecksum = Character.getNumericValue(refinedNationalCode.charAt(9));

        if (calculatedChecksum != enteredChecksum) {
            throw new InvalidNationalCodeException("National code is not valid", refinedNationalCode);
        }
    }
}