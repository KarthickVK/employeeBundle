package com.launchclub;

import java.util.Scanner;

public class UserInputs {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getInputs(String input) {
        System.out.println(input);
        return SCANNER.next().trim();
    }
}
