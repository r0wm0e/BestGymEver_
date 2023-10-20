package Best_Gym_Ever;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Main(){

        List<Customer> customers = new ArrayList<>();

        try(Scanner scan = new Scanner(new File("src/Best_Gym_Ever/All_Members"))) {
            String name;
            String personalNumber;
            LocalDate joinDate = null;


            while(scan.hasNextLine()) {
                String fileInput = scan.nextLine();

                personalNumber = fileInput.split(",")[0].trim();

                name = fileInput.split(",")[1].trim();



                if(scan.hasNextLine()) {
                    String dateString = scan.nextLine();
                    joinDate = LocalDate.parse(dateString);
                }


                customers.add(new Customer(name, personalNumber, joinDate));
            }


        } catch (FileNotFoundException e) {
            System.out.println("Du fick ett: FileNotFoundException");
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            System.out.println("Du har ett felformulerat datum");
            e.printStackTrace();

        }

        String input = JOptionPane.showInputDialog("Skriv in namn eller personnummer");

        Customer person = null;
        for (Customer customer:customers) {
            if (customer.getName().equalsIgnoreCase(input) || customer.getPersonalNumber().equalsIgnoreCase(input)) {
                person = customer;
                break;
            }
        }
        if(person != null) {
            switch (person.getStatus()) {
                case MEMBER -> {
                    writeToLog(person);
                    JOptionPane.showMessageDialog(null,"Hej! " + person.getName() + " välkommen in");
                }
                case EX_MEMBER ->
                        JOptionPane.showMessageDialog(null,"Hej! " + person.getName() + " ditt medlemskap har gått ut");

            }
        } else {
            JOptionPane.showMessageDialog(null,"Du är inte medlem.");
        }
    }


    public static void writeToLog(Customer customer){
        String path = "src/Best_Gym_Ever/stats.txt";

        try(FileWriter fw = new FileWriter(path,true)) {
            fw.append(customer.getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +"\n");

        }
        catch (IOException e) {
            System.out.println("Du fick ett IOException");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}