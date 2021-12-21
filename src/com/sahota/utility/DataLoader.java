package com.sahota.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The intent of this glass is to easily pull in data provided by the advent of code
// daily challenges. Usually some sort of file
public class DataLoader {

    public List<Integer> getDay1Input(String fileName) {

        ArrayList<Integer> data = new ArrayList<>();

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                Integer integer = Integer.valueOf(myReader.nextLine());
                data.add(integer);
            }

            myReader.close();
        } catch (FileNotFoundException e) {

            System.out.println("Couldn't find the file...");
            e.printStackTrace();
        }

        return data;
    }

    public List<String> getDay2Input(String fileName) {

        ArrayList<String> data = new ArrayList<>();

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String directions = myReader.nextLine();
                data.add(directions);
            }

            myReader.close();
        } catch (FileNotFoundException e) {

            System.out.println("Couldn't find the file...");
            e.printStackTrace();
        }

        return data;
    }

    public String getDay3Input(String fileName) {

        StringBuilder data = new StringBuilder();

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String binaryNumbers = myReader.nextLine();
                data.append(binaryNumbers);
            }

            myReader.close();
        } catch (FileNotFoundException e) {

            System.out.println("Couldn't find the file...");
            e.printStackTrace();
        }

        return data.toString().replace("\n", "");
    }
}
