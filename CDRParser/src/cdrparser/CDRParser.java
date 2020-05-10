/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdrparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author MohabOmar
 */
public class CDRParser {

    File cdr = null;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        while (true) {
            File folder = new File("D:\\ITI\\Billing\\CDRParser\\CDRs");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    try (BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
                        String row;
                        while ((row = csvReader.readLine()) != null) {
                            String[] data = row.split(",");
                            CDR cdr = new CDR(Long.parseLong(data[0]),
                                    data[1],
                                    Integer.parseInt(data[2]),
                                    Integer.parseInt(data[3]),
                                    data[4],
                                    Time.valueOf(data[5]),
                                    Float.parseFloat(data[6]),
                                    0,
                                    false,
                                    false);
                            Database database = new Database();
                            database.addCdr(cdr);
                            System.out.println("####inserted in database####");
                        }
                    }
                }
                if (file.renameTo(new File("D:\\ITI\\Billing\\CDRParser\\CDRs archived\\archived" + file.getName()))) {
                    file.delete();
                    System.out.println("File moved successfully");
                }
            }

        }
    }

}
