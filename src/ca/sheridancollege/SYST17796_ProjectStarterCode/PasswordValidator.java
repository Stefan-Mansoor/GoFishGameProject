package ca.sheridancollege.SYST17796_ProjectStarterCode;

/**
 * This class models password validation.
 *
 * @author Stefan Mansoor April, 2021
 * @author Jinling Cai April 17, 2021
 */
public class PasswordValidator {

    private static final int Num_Chars = 8;
    private static final int Num_Special_Chars = 1;

    public static String getValidationString() {

        String msg1 = String.format("Password must at least have 8 characters", Num_Chars);
        String msg2 = String.format("Password must at least have one special characters", Num_Special_Chars);
        return String.format(msg1, msg2);

    }

    public static boolean validatePassword(String password) {

        int specialCharCount = 0;
        boolean validPassword = false;

        for (int i = 0; i < password.length(); i++) {
            if (!(Character.isLetterOrDigit(password.charAt(i)))) {
                specialCharCount++;
            }
        }
        if (specialCharCount >= Num_Special_Chars && password.length() > Num_Chars) {
            validPassword = true;
        }
        return validPassword;

    }
}
