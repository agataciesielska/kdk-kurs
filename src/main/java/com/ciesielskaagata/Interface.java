package com.ciesielskaagata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interface {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void printOut(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cat cat = new Cat();
        CatDAO catDAO = new CatDAO();

        printOut("Hello. Please fill in the cat's name below.");
        cat.setName(scanner.nextLine());

        printOut("Please fill the keeper's name.");
        cat.setKeeperName(scanner.nextLine());

        do {
            printOut("Please fill the cat's weight.");
            String givenWeight = scanner.nextLine();
            Pattern weightPattern = Pattern.compile("[0-9]{1,3}\\.*[0-9]{0,2}");
            Matcher matcher = weightPattern.matcher(givenWeight);
            if (matcher.matches()) {
                cat.setWeight(Float.valueOf(givenWeight));
            } else {
                System.out.print("Wrong weight format. Please use dot as separator.");
            }
        }
        while (cat.getWeight() == null);

        do {
            printOut("Please fill the birthday(YYYY-MM-DD).");
            String givenBirthday = scanner.nextLine();

            Pattern birthdayPattern = Pattern.compile("[1-2][09][0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])");
            Matcher matcher = birthdayPattern.matcher(givenBirthday);

            if (matcher.matches()) {
                try {
                    cat.setBirthday(formatter.parse(givenBirthday));
                } catch (ParseException e) {
                    System.out.print("Wrong date format. Please use YYYY-MM-DD format.");
                }
            }
        }
        while (cat.getBirthday() == null);
        catDAO.addCat(cat);
        cat.introduce();
    }
}