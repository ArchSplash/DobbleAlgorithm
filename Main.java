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
        final int cardsAmount = 55;

        int[][] cards = new int[cardsAmount][symbolsPerCard];
        int currentCard = 0;

        // Fill int array
        for (int i = 0; i < symbolsPerCard; i++) {
            int slope = i + 1;

            // Assign to cards[0]
            cards[0][i] = slope;

            for (int x = 0; x < arraySize; x++) {
                if (currentCard + 1 < cardsAmount) {
                    cards[++currentCard][0] = slope;
                    int sym = 1;

                    for (int y = 0; y < arraySize; y++) {
                        int value = (symbolsPerCard + arraySize * y + ((slope * y + x) % arraySize)) + 1;
                        cards[currentCard][sym++] = value;
                    }
                }
            }
        }

        for (int card = 0; card < cardsAmount; card++) {
            System.out.print("[" + String.valueOf(card + 1) + "] -");
            for (int i = 0; i < symbolsPerCard; i++) {
                System.out.print(" " + symbols[cards[card][i] - 1] + " |");
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
