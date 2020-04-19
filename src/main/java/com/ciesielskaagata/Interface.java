package com.ciesielskaagata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cat cat = new Cat();

        System.out.println("Hello. Please fill in the cat's name below.");
        cat.setName(scanner.nextLine());

        System.out.println("Please fill the keeper's name.");
        cat.setKeeperName(scanner.nextLine());

        do {
            System.out.println("Please fill the cat's weight.");
            try {
                cat.setWeight(scanner.nextFloat());
            }
            catch (InputMismatchException e) {
                System.out.println("Wrong weight format. Please use dot as a separator.");
                scanner.nextLine();
            }
        }
        while (cat.getWeight() == null);

        scanner.nextLine();

        do {
            System.out.println("Please fill the birthday(YYYY-MM-DD).");
            try {
                cat.setBirthday(formatter.parse(scanner.nextLine()));
            }
            catch (ParseException e) {
                System.out.println("Wrong date format. Please use YYYY-MM-DD format.");
            }
        }
        while (cat.getBirthday() == null);

        cat.introduce();
    }
}
