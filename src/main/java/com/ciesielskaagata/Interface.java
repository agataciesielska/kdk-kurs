package com.ciesielskaagata;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Interface {
    static Scanner scanner = new Scanner(System.in);
    static CatDAO catDAO = new CatDAO();
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    static boolean wantToLeave = false;

    public static void displayMenu() {
        printOut("Hello. Choose what You want to do:\n1 - Add new cat.\n2 - Show cats.\nx - Close the program.");
    }

    public static void initialize() {
        Cat cat = new Cat();
        printOut("Hello. Please fill in the cat's name below.");
        cat.setName(getStringUserChoice());

        printOut("Please fill the keeper's name.");
        cat.setKeeperName(getStringUserChoice());

        do {
            printOut("Please fill the cat's weight.");
            String givenWeight = getStringUserChoice();
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
            String givenBirthday = getStringUserChoice();

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
        catDAO.saveCat(cat);
    }

    public static void introduce(Cat cat) {
        printOut("Cat named " + cat.getName() + " was born on " + cat.getFormatedBirthday() +
                " and right now his/her weight is " + cat.getWeight() + "kg. The keeper is " + cat.getKeeperName() + ".\n");
    }

    public static void printOut(String text) {
        System.out.println(text);
    }

    public static void printCatsNames() {
        int catAmount = catDAO.cats.size();
        if (catAmount > 0) {
            printOut("Here are all saved cats:");
            for (int i = 1; i < catDAO.cats.size() + 1; i++) {
                printOut(i + " - " + catDAO.cats.get(i - 1).getName());
            }
            printOut("Choose the cat You would like to know better:");
            introduce(catDAO.cats.get(getIntUserChoice() - 1));
            getStringUserChoice();
        } else {
            printOut("There are no cats saved yet.\n");
        }
    }

    public static String getStringUserChoice() {
        return scanner.nextLine().trim();
    }

    public static Integer getIntUserChoice() {
        return scanner.nextInt();
    }

    public static void choiceAction() {
        String choice = getStringUserChoice();
        if (choice.equals("1")) {
            initialize();
        } else if (choice.equals("2")) {
            printCatsNames();
        } else if (choice.equals("x")) {
            wantToLeave = true;
        } else {
            printOut("Wrong answer.");
        }
    }

    public static void main(String[] args) {
        while (!wantToLeave) {
            displayMenu();
            choiceAction();
        }
    }
}