package com.sahota;

import com.sahota.utility.DataLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        DataLoader dataLoader = new DataLoader();
        List<Integer> day1Data = dataLoader.getDay1Input("day-1-input.txt");
        List<String> day2Data = dataLoader.getDay2Input("day-2-input.txt");

        day1Part1(day1Data);
        day1part2(day1Data, 3);
        day3Part1(day2Data);
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

    private static void day3Part1(List<String> data) {

        AtomicInteger horizontalDirection = new AtomicInteger();
        AtomicInteger verticalDirection = new AtomicInteger();

        ArrayList<String> horizontalModifiers = new ArrayList<>();
        horizontalModifiers.add("forward");

        ArrayList<String> verticalModifiers = new ArrayList<>();
        verticalModifiers.add("up");
        verticalModifiers.add("down");

        data.forEach(directionalInstruction -> {

            // determine if it's a horizontal or vertical direction
            // get the directional unit (how much to move)
            String[] directionAndUnit = directionalInstruction.split(" ");
            String direction = directionAndUnit[0];
            int directionalUnit = Integer.parseInt(directionAndUnit[1]);

            // moving "up" reduces depth, and therefore decreases the vertical position
            if (direction.equalsIgnoreCase("up")) {

                verticalDirection.getAndAdd(-directionalUnit);

                // moving "down" increases depth, and therefore increases the vertical position
            } else if (direction.equalsIgnoreCase("down")) {

                verticalDirection.getAndAdd(directionalUnit);

                // moving forward increases the horizontal position
            } else if (direction.equalsIgnoreCase("forward")) {

                horizontalDirection.getAndAdd(directionalUnit);
            } else {

                System.out.println("Shit broke");
            }
        });

        int d2p1Answer = Math.multiplyExact(horizontalDirection.get(), verticalDirection.get());

        System.out.println("D2P1: [" + d2p1Answer + "].");
    }
}
