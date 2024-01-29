package soClassy;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Person {
    public Person(String id, String firstName, String lastName, String title, String birthdateString) {
    }

    public static void main(String[] args) {

            ArrayList<String> personList = new ArrayList<>();
            Scanner in = new Scanner(System.in);
            Scanner stringScanner = new Scanner(System.in);

            collectPersonData(in, stringScanner, personList);

            // Save person data to CSV file
            saveToCSV(personList, "PersonTestData.txt");

            //asking end user if they want to use file chooser
            System.out.println("Do you want to use the JFileChooser to read data?(Y/N) ");
            String input = in.nextLine();
            //if yes then program will open file chooser
            if (input.equalsIgnoreCase("Y")) {
                // reads and displays the data chosen using JFileChooser
                readAndDisplayPersonData();
            } else {
                System.out.println("Have a good day!");
            }

            in.close();
            stringScanner.close();
        }
            private static int calculateAge(LocalDate birthDate) {
                LocalDate currentDate = LocalDate.now();
                return currentDate.getYear() - birthDate.getYear();
            }


        private static void collectPersonData(Scanner in, Scanner stringScanner, ArrayList<String> personList) {
            boolean input;

            do {
                String ID = SafeInput.getNonZeroLenString(in, "What is your ID?");
                String firstName = SafeInput.getNonZeroLenString(in, "What is your first name?");
                String lastName = SafeInput.getNonZeroLenString(in, "What is your last name?");
                String title = SafeInput.getNonZeroLenString(in, "What is your title?");

                String birthdateString = SafeInput.getNonZeroLenString(in, "Enter your birthdate (YYYY-MM-DD) ");
                LocalDate birthdate = LocalDate.parse(birthdateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                int age = calculateAge(birthdate);

                personList.add(String.format("| %-15s | %-15s | %-10s | %-15s | %-4d |", ID, firstName, lastName, title, age));
                System.out.println(String.format("| %-15s | %-15s | %-10s | %-15s | %-4d |", ID, firstName, lastName, title, age));

                input = SafeInput.getYNConfirm(stringScanner,   "Do you have another person to input?");
            }

            while (input);

            if (!input) {
                System.out.println("Information for all persons: ");
                printTableHeader();
                for (String info : personList) {
                    System.out.println(info);
                }
                printTableFooter();
            }
        }

        private static void saveToCSV(ArrayList<String> personList, String fileName) {
            try (FileWriter writer = new FileWriter("PersonTestData.txt")) {
                for (String personInfo : personList) {
                    writer.write(personInfo + "\n");
                }
                System.out.println("Person data saved to " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void printTableHeader() {
            System.out.println("+-----------------+-----------------+------------+-----------------+------+");
            System.out.printf("| %-15s | %-15s | %-10s | %-15s | %-4s |\n","ID", "First Name", "Last Name", "Title", "Age");
            System.out.println("+-----------------+-----------------+------------+-----------------+------+");
        }

        private static void printTableFooter() {
            System.out.println("+-----------------+-----------------+------------+-----------------+------+");
        }


        private static void readAndDisplayPersonData() {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                readAndDisplayFile(selectedFile);
            } else {
                System.out.println("File selection incomplete..");
            }
        }
        private static void readAndDisplayFile(File file) {
            try (Scanner fileScanner = new Scanner(file)) {
                System.out.println("Person data from file: ");
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

