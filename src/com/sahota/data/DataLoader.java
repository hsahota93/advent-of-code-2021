package com.sahota.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

// The intent of this glass is to easily pull in data provided by the advent of code
// daily challenges. Usually some sort of file
public class DataLoader {

    public List<Integer> getInputFile(String fileName) {

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
}
