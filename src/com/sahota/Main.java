package com.sahota;

import com.sahota.utility.DataLoader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;

public class Main {

    public static void main(String[] args) {

        DataLoader dataLoader = new DataLoader();
        List<Integer> day1Data = dataLoader.getDay1Input("day-1-input.txt");
        List<String> day2Data = dataLoader.getDay2Input("day-2-input.txt");

        day1Part1(day1Data);
        day1part2(day1Data, 3);
        day2Part1(day2Data);
        day2Part2(day2Data);
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
}
