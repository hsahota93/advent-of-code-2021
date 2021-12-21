package com.sahota;

import com.sahota.utility.DataLoader;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        DataLoader dataLoader = new DataLoader();
        List<Integer> day1Data = dataLoader.getDay1Input("day-1-input.txt");
        List<String> day2Data = dataLoader.getDay2Input("day-2-input.txt");
        String day3Data = dataLoader.getDay3Input("day-3-input.txt");

        day1Part1(day1Data);
        day1part2(day1Data, 3);
        day2Part1(day2Data);
        day2Part2(day2Data);
        day3Part1(day3Data);
    }

    private static void day1Part1(List<Integer> data) {

        int numIncreases = 0;

        for (int i = 1; i < data.size(); i++) {

            int currentDepth = data.get(i);
            int previousDepth = data.get(i - 1);

            if (currentDepth > previousDepth) {

                numIncreases++;
            }
        }

        // Answer was: 1462
        System.out.println("D1P1 depth increased [" + numIncreases + "] times.");
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

    private static void day2Part1(List<String> data) {

        AtomicInteger horizontalDirection = new AtomicInteger();
        AtomicInteger verticalDirection = new AtomicInteger();

        data.forEach(directionalInstruction -> {

            // determine if it's a horizontal or vertical direction
            // get the directional unit (how much to move)
            String[] directionAndUnit = directionalInstruction.split(" ");
            String direction = directionAndUnit[0];
            int directionalUnit = Integer.parseInt(directionAndUnit[1]);

            switch (direction) {
                case "up" ->
                        // moving "up" reduces depth, and therefore decreases the vertical position
                        verticalDirection.getAndAdd(-directionalUnit);
                case "down" ->
                        // moving "down" increases depth, and therefore increases the vertical position
                        verticalDirection.getAndAdd(directionalUnit);
                case "forward" ->
                        // moving forward increases the horizontal position
                        horizontalDirection.getAndAdd(directionalUnit);
                default -> System.out.println("Unknown direction detected...skipping");
            }
        });

        int d2p1Answer = Math.multiplyExact(horizontalDirection.get(), verticalDirection.get());

        // Answer was: 1936494
        System.out.println("D2P1: [" + d2p1Answer + "].");
    }

    /*
    - down X increases your aim by X units.
    - up X decreases your aim by X units.
    - forward X does two things:
        - It increases your horizontal position by X units.
        - It increases your depth by your aim multiplied by X.
     */
    private static void day2Part2(List<String> data) {

        AtomicInteger horizontalDirection = new AtomicInteger();
        AtomicInteger verticalDirection = new AtomicInteger();
        AtomicInteger aim = new AtomicInteger();

        data.forEach(directionalInstruction -> {

            // determine if it's a horizontal or vertical direction
            // get the directional unit (how much to move)
            String[] directionAndUnit = directionalInstruction.split(" ");
            String direction = directionAndUnit[0];
            int directionalUnit = Integer.parseInt(directionAndUnit[1]);

            switch (direction) {
                case "up" ->
                        // moving "up" reduces depth, and therefore decreases the vertical position
                        aim.getAndAdd(-directionalUnit);
                case "down" ->
                        // moving "down" increases depth, and therefore increases the vertical position
                        aim.getAndAdd(directionalUnit);
                case "forward" -> {
                    // moving forward increases the horizontal position
                    horizontalDirection.getAndAdd(directionalUnit);
                    int newVerticalDirection = Math.multiplyExact(aim.get(), directionalUnit);
                    verticalDirection.getAndAdd(newVerticalDirection);
                }
                default -> System.out.println("Unknown direction detected...skipping");
            }
        });

        int d2p2Answer = Math.multiplyExact(horizontalDirection.get(), verticalDirection.get());

        // Answer was: 1997106066
        System.out.println("D2P2: [" + d2p2Answer + "].");
    }

    private static void day3Part1(String data) {

        /*
            1. Have the input be one long string (remove new lines)
            2. Go through the string one character at a time
            3. Create a counter that resets at 5
            4. Use the counter to determine which number to add to
            5. If the current char is 1, add 1 to the counter
            6. Once done, get the total number of records by string.length() / 5
            7. If the counter at that specific column is greater than 50%, it's a 1
         */
        HashMap<Integer, Integer> occurrenceCount = new HashMap<>();
        occurrenceCount.put(1, 0);
        occurrenceCount.put(2, 0);
        occurrenceCount.put(3, 0);
        occurrenceCount.put(4, 0);
        occurrenceCount.put(5, 0);
        occurrenceCount.put(6, 0);
        occurrenceCount.put(7, 0);
        occurrenceCount.put(8, 0);
        occurrenceCount.put(9, 0);
        occurrenceCount.put(10, 0);
        occurrenceCount.put(11, 0);
        occurrenceCount.put(12, 0);

        int counter = 1;

        char[] charArray = data.toCharArray();

        for (int i = 0; i < data.length(); i++) {

            if (charArray[i] == '1') {

                int currentOccurrenceCount = occurrenceCount.get(counter);
                currentOccurrenceCount++;
                occurrenceCount.put(counter, currentOccurrenceCount);
            }

            counter++;

            if (counter == 13) {
                counter = 1;
            }
        }

        StringBuilder gammaRateBinary = new StringBuilder();
        StringBuilder epsilonRateBinary = new StringBuilder();
        int halfTotalRecords = data.length() / 12 / 2;

        occurrenceCount.forEach((key, value) -> {

            if (value > halfTotalRecords) {

                gammaRateBinary.append("1");
                epsilonRateBinary.append("0");
            } else {

                gammaRateBinary.append("0");
                epsilonRateBinary.append("1");
            }
        });

        //converting to decimal numbers
        int gammaRateDecimal = Integer.parseInt(gammaRateBinary.toString(), 2);
        int epsilonRateDecimal = Integer.parseInt(epsilonRateBinary.toString(), 2);

        int d3p1Answer = Math.multiplyExact(gammaRateDecimal, epsilonRateDecimal);

        System.out.println("D3P1: [" + d3p1Answer + "].");
    }
}
