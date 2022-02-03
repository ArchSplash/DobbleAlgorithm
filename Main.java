package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] symbols = importSymbols("Symbols.txt");

        final int symbolsPerCard = 8;
        final int arraySize = symbolsPerCard - 1;
        final int possibleCards = (int) Math.pow(arraySize, 2) + symbolsPerCard;
        final int maxCards = 55;

        // Fill with slopes from 1 to 8
        int[] startNodes = new int[symbolsPerCard];
        for (int i = 0; i < symbolsPerCard; i++) {
            startNodes[i] = i + 1;
        }

        // Cards
        int[][] cards = new int[possibleCards][symbolsPerCard];

        int currentCard = 0;
        for (int slope : startNodes) {
            for (int x = 0; x < arraySize; x++) {
                int[] card = new int[symbolsPerCard];
                card[0] = slope;

                int sym = 1;
                for (int y = 0; y < arraySize; y++) {
                    card[sym++] = (symbolsPerCard + arraySize * y + ((slope * y + x) % arraySize)) + 1;
                }
                cards[currentCard++] = card;
            }
        }

        // Print output
        for (int i = 0; i < maxCards; i++) {
            System.out.print(i + " ");
            for (int sym : cards[i]) {
                System.out.print(symbols[sym - 1] + ", ");
            }
            System.out.println();
        }
    }

    public static String[] importSymbols(String fileName) throws IOException {
        LinkedList<String> symbols = new LinkedList<>();

        File file = new File(fileName);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                symbols.add(line);
            }
        }

        return symbols.toArray(String[]::new);
    }
}
