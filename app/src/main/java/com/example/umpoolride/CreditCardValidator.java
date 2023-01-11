package com.example.umpoolride;

import android.widget.EditText;

public class CreditCardValidator {

    private static final int[] CARD_NUMBER_LENGTHS = {16, 15};
    private static final String CARD_NUMBER_REGEX = "^[0-9]{15,16}$";

    private static final int[] CVV_LENGTHS = {3, 4};
    private static final String CVV_REGEX = "^[0-9]{3,4}$";

    private static final String EXPIRATION_DATE_REGEX = "^(0[1-9]|1[0-2])\\/(\\d{2})$";

    public static boolean isValidCardNumber(EditText cardNumberEditText) {
        String cardNumber = cardNumberEditText.getText().toString().trim();
        return isValidCardNumber(cardNumber);
    }

    public static boolean isValidCardNumber(String cardNumber) {
        for (int length : CARD_NUMBER_LENGTHS) {
            if (cardNumber.length() == length && cardNumber.matches(CARD_NUMBER_REGEX)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidCvv(EditText cvvEditText) {
        String cvv = cvvEditText.getText().toString().trim();
        return isValidCvv(cvv);
    }

    public static boolean isValidCvv(String cvv) {
        for (int length : CVV_LENGTHS) {
            if (cvv.length() == length && cvv.matches(CVV_REGEX)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidExpirationDate(EditText expirationDateEditText) {
        String expirationDate = expirationDateEditText.getText().toString().trim();
        return isValidExpirationDate(expirationDate);
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        return expirationDate.matches(EXPIRATION_DATE_REGEX);
    }
}
