package soClassy;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    public static void main(String[] args) {
        ArrayList<String> productList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);

        collectProductData(in, stringScanner, productList);

        // Save product data to CSV file
        saveToCSV(productList, "ProductTestData.txt");

        //asking end user if they want to use file chooser
        System.out.println("Do you want to use the JFileChooser to read data?(Y/N) ");
        String input = in.nextLine();
        //if yes then program will open file chooser
        if (input.equalsIgnoreCase("Y")) {
            // reads and displays the data chosen using JFileChooser
            readAndDisplayProductData();
        } else {
            System.out.println("Have a good day!");
        }

        in.close();
        stringScanner.close();
    }



    private static void collectProductData(Scanner in, Scanner stringScanner, ArrayList<String> productList) {
        boolean input;

        do {
            String ID = SafeInput.getNonZeroLenString(in, "What is the product ID?");
            String name = SafeInput.getNonZeroLenString(in, "What is the product name?");
            String description = SafeInput.getNonZeroLenString(in, "What is the description of the product? ");

            double cost = SafeInput.getRangedInt(in, "Enter the cost", 0, 2000);

            String productInfo = String.format("| %-15s | %-15s | %-10s | %-15s |", ID, name, description, cost);
            productList.add(productInfo);
            System.out.println(productInfo);

            input = SafeInput.getYNConfirm(stringScanner, "Do you have another product to input?");
        }

        while (input);

        if (!input) {
            System.out.println("Information for all products: ");
            printTableHeader();
            for (String info : productList) {
                System.out.println(info);
            }
            printTableFooter();
        }
    }

    private static void saveToCSV(ArrayList<String> productList, String fileName) {
        try (FileWriter writer = new FileWriter("ProductTestData.txt")) {
            for (String productInfo : productList) {
                writer.write(productInfo + "\n");
            }
            System.out.println("Product data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTableHeader() {
        System.out.println("+-----------------+-----------------+------------+-----------------+");
        System.out.printf("| %-15s | %-15s | %-10s | %-15s | \n","ID", "Product Name", "Description", "Cost");
        System.out.println("+-----------------+-----------------+------------+-----------------+");
    }

    private static void printTableFooter() {
        System.out.println("+-----------------+-----------------+------------+-----------------+");
    }


    private static void readAndDisplayProductData() {
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
            System.out.println("Product data from file: ");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
