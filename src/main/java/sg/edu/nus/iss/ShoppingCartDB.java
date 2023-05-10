package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.naming.directory.SchemaViolationException;

public class ShoppingCartDB {
    //
    public static void saveCart(File userFile, List<String> cartList) throws Exception {

        FileWriter fw = new FileWriter(userFile, false);
        BufferedWriter bw = new BufferedWriter(fw);

        int i = 1;
        for (String f : cartList) {
            bw.write(i + ". " + f + " \n");
            i++;

        }
        System.out.println("Your cart has been saved");
        bw.flush();
        bw.close();
        fw.close();
    }

    public static void loadCart(File userFile) throws Exception {
            FileReader fr = new FileReader(userFile);
            BufferedReader br = new BufferedReader(fr);

            String items = "";
            System.out.println(userFile.getName().replaceAll(".txt", "") + ", your cart contains the following items");
            while ((items = br.readLine()) != null) {
                System.out.println(items);

            }
        
            br.close();
            fr.close();
        }
    }


