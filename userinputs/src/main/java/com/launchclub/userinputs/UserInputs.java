package com.launchclub.userinputs;

import java.util.Scanner;

public class UserInputs {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static UserInputs userInputs;

    private UserInputs() {

    }

    public static UserInputs getInstance()
    {
        if (userInputs == null)
        {
            userInputs = new UserInputs();
        }
        return userInputs;
    }

    public static String getInputs(String input) {
        System.out.println(input);
        return SCANNER.next().trim();
    }
}
