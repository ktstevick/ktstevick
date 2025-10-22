package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookReader {
    // Good usage of constants for these
    static private final String BEGIN_MARKER = "*** START OF";
    static private final String END_MARKER = "*** END OF";
    public static void main(String[] args) {
        /*
         * This book-reader program opens a file that was downloaded from https://www.gutenberg.org/, reads
         * through the copyright information at the top until it finds the start of the book content, and
         * then displays the content to the user. It also counts the total lines of book content between the
         * start and the end markers.
         */

        /*
        Step 1: Prompt the user for a filename
         */
        // Create a scanner for user input
        Scanner userInput = new Scanner(System.in);
        // Prompt the user for a file path - path should look like "data/jekyll-and-hyde.txt"
        System.out.print("Enter path to the book file: ");
        String filePath = userInput.nextLine();

        /*
        Step 2: Step Two: Open the book file and handle errors
         */
        // Creating a File object using the path provided
        File bookFile = new File(filePath);

        // To track the total number of lines
        int lineCount = 0;

        boolean inBookText = false; // Are you reading between the start and end markers?

        try (Scanner fileInput = new Scanner(bookFile)) {
            // Loop until the end of the file is reached
            while (fileInput.hasNext()) {
                // Read the next line into 'lineOfText'
                String lineOfText = fileInput.nextLine();

                if (lineOfText.startsWith(BEGIN_MARKER)) {
                    inBookText = true;
                    continue;
                }

                // This code checks for the end marker and breaks the loop if it finds it
                if (lineOfText.startsWith(END_MARKER)) {
                    break;
                }

                if (inBookText) {
                    // Increment the line count
                    lineCount++;
                    // Print that line
                    System.out.println(lineCount + ": " + lineOfText);
                }
            }

        } catch (FileNotFoundException e) {
            // Could not find the file at the specified path
            System.out.println("The file was not found: " + bookFile.getAbsolutePath());
        }

        // Display total line count
        System.out.println("Found " + lineCount + " lines of text in " + filePath);
    }
}
