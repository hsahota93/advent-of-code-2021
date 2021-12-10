package com.sahota;

import com.sahota.utility.DataLoader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataLoader dataLoader = new DataLoader();
        List<Integer> day1Data = dataLoader.getInputFile("day-1-input.txt");

        day1Part1(day1Data);
        day1part2(day1Data, 3);
    }

    private static void day1part2(List<Integer> data, Integer window) {

        int numIncreases = 0;

        for (int i = window; i < data.size(); i++) {

            int currentDepth = data.get(i);
            int previousDepth = data.get(i - window);

            if (currentDepth > previousDepth) {

                numIncreases++;
            }
        }

        System.out.println("D1P2 depth increased [" + numIncreases + "] times.");
    }

    public static void day1Part1(List<Integer> data) {

        int numIncreases = 0;

        for (int i = 1; i < data.size(); i++) {

            int currentDepth = data.get(i);
            int previousDepth = data.get(i - 1);

            if (currentDepth > previousDepth) {

                numIncreases++;
            }
        }

        System.out.println("D1P1 depth increased [" + numIncreases + "] times.");
    }
}
