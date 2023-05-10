package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) throws Exception {

        File directory = null;
        if (args.length == 0) {
            directory = new File("db");
            directory.mkdir();

        } else {
            directory = new File(args[0]);
            directory.mkdir();
        }

        List<String> cartList = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        String loginUser = "";
        File userFile = null;

        System.out.println("Welcome to your shopping cart");

        while (!input.equals("quit")) {
            System.out.print("> ");
            input = scanner.nextLine();

            if (input.startsWith("add")) {
                input = input.replace(',', ' ');
                Scanner scanner2 = new Scanner(input.substring(4));

                while (scanner2.hasNext()) {
                    String fruit = scanner2.next();
                    if (cartList.contains(fruit)) {
                        System.out.println(input.substring(4) + " already in cart");
                    } else {
                        cartList.add(fruit);

                        System.out.println(fruit + " added to cart");

                    }
                    
                }

            }

            if (input.startsWith("delete")) {
                Scanner scanner3 = new Scanner(input.substring(7));
                int index = scanner3.nextInt();
                index--;

                if (index < cartList.size()) {
                    System.out.println(cartList.get(index) + " removed from cart");
                    cartList.remove(index);

                } else {
                    System.out.println("Incorrect item index");
                }
            }

            if (input.equals("list")) {
                if (cartList.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else {
                    int i = 0;
                    for (String fruits : cartList) {
                        i++;
                        String fruitList = fruits.substring(0, 1).toUpperCase() + fruits.substring(1);
                        // System.out.println(i + "." + fruitList);
                        System.out.printf("%d.%s\n", i, fruitList);
                    }
                }
            }

            if (input.equals("users")) {
                String[] user = directory.list();

                System.out.println("The following users are registered");
                
                int i = 1;
                for (String u : user) {
                   
                    System.out.println(i + ". " + u.replace(".txt", " "));
                    i++;

                }

            }

            if (input.startsWith("login")) {
                Scanner log = new Scanner(input.substring(6));
                loginUser = log.next();

                userFile = new File(directory + File.separator + loginUser + ".txt");
                
                cartList = new LinkedList<>();
                if (userFile.exists() && userFile.length() <= 0) {
                    System.out.println(loginUser + " already exists");
                    
                
                } else if ((userFile.exists() && userFile.length() != 0)) {
                    
                    ShoppingCartDB.loadCart(userFile);

                
                // } else if (userFile.exists() && userFile.length() != 0) {
                    
                //     FileReader fr = new FileReader(directory + File.separator + loginUser + ".txt");
                //     BufferedReader br = new BufferedReader (fr);
                    

                //     String items = ""; 
                //     System.out.println(loginUser + ", your cart contains the following items");
                //     while ((items = br.readLine()) != null) {
                //     System.out.println(items);
                    

                   
                    
                // }
                //     br.close();
                } else {
                    userFile.createNewFile();
                    System.out.println(loginUser + " your cart is empty");
                } 

            }

            if (input.equals("save")) {
               ShoppingCartDB.saveCart(userFile, cartList);
                // FileWriter fw = new FileWriter(directory + File.separator + loginUser + ".txt", false);
                // BufferedWriter bw = new BufferedWriter(fw);
            
                // int i = 1;
                // for (String f : cartList) {
                //     bw.write(i + ". " + f + " \n");
                //     i++;
                
                // }

                // System.out.println("Your cart has been saved");
                // bw.flush();
                // bw.close();
                // fw.close();
            }

        }

    }

}    



// hasNext: after scanning the first word, it will check if the is a next word
// available
// returns a boolean
// next: scanning and accepting the word
// nextLine: scanning and accept the line
// nextIntL scanning and accepting the integer

// a folder is a directory
// a file is a file
// a file can be .txt. .csv etc
// a directory holds file