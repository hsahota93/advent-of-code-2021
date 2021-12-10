package com.sahota;

import com.sahota.data.DataLoader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataLoader dataLoader = new DataLoader();
        List<Integer> day1Data = dataLoader.getInputFile("day-1-input.txt");

        int numIncreases = 0;

        for (int i = 1; i < day1Data.size(); i++) {

            int currentDepth = day1Data.get(i);
            int previousDepth = day1Data.get(i-1);

            if (currentDepth > previousDepth) {

                numIncreases++;
            }
        }

        System.out.println("The depth increased [" + numIncreases + "] times.");
    }
}
