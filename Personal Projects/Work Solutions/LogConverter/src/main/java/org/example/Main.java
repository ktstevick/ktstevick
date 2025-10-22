package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Page and HTML constants
    private static final int COLUMNS_PER_PAGE = 30;
    private static final int ROWS_PER_PAGE = 58;
    private static final String PAGE_START_SIGNAL = "Delivery ";
    private static final String NOBR_STRING = "<nobr";

    // Column constants
    private static final int MATERIAL = 3;
    private static final int DESCRIPTION = 4;
    private static final int NET_WEIGHT = 8;
    private static final int BATCH = 10;
    private static final int MANU_DATE = 11;
    private static final int BB_DATE = 12;
    private static final int SOLD_TO = 17;
    private static final int GI_DATE = 21;
    private static final int ORDER_NO = 28;

    // Array declarations
    private static final ArrayList<String> material = new ArrayList<>();
    private static final ArrayList<String> description = new ArrayList<>();
    private static final ArrayList<String> netWeight = new ArrayList<>();
    private static final ArrayList<String> batch = new ArrayList<>();
    private static final ArrayList<String> manuDate = new ArrayList<>();
    private static final ArrayList<String> bbDate = new ArrayList<>();
    private static final ArrayList<String> soldTo = new ArrayList<>();
    private static final ArrayList<String> giDate = new ArrayList<>();
    private static final ArrayList<String> orderNo = new ArrayList<>();

    // File generation
    private static final String OUTPUT_FILE_PATH_BASE = "C:\\Users\\ktste\\Desktop\\ShippingLog ";

    public static void main(String[] args) {
        System.out.println("Hello FCI! Cheesed to meet you :)");

        // No Scanner, report naming is consistent so the file path is a constant for now
        String filePath = "C:\\Users\\ktste\\Desktop\\FCI\\Job OM_D_E40_100_7630_QUALITY_12PM, Step 1.htm";
        File logFile = new File(filePath);

        // Debug
        int tagCount = 0;
        int nobrCount = 0;
        String debugString = "";

        // Array organization
        boolean reportStart = false;
        int columnCounter = 0;
        int rowCounter = 0;

        try (Scanner fileInput = new Scanner(logFile)) {
            boolean readingTag = true;
            boolean readingText = false;

            // Parsing HTML
            int tagOpens = 0;
            int tagCloses = 0;
            String currentTag = "";
            String currentText = "";

            while (fileInput.hasNext()) {
                // Read the next line of 'lineOfText'
                String lineOfText = fileInput.nextLine();

                for (int i = 0; i < lineOfText.length() - 2; i++) {
                    // Identifying HTML tags
                    if (readingTag) {
                        if (lineOfText.charAt(i) == '<') {
                            tagOpens = i;

                            tagCount++; // Debug
                        }
                        if (lineOfText.charAt(i) == '>') {
                            tagCloses = i;

                            // Print tag
                            currentTag = lineOfText.substring(tagOpens, tagCloses + 1);

                            // Check for NOBR_STRING
                            if (currentTag.length() > 5 && currentTag.charAt(1) != '/') {
                                currentTag = currentTag.substring(0, 5);

                                if (currentTag.equals(NOBR_STRING)) {
                                    // Switch to text reading
                                    readingTag = false;
                                    readingText = true;

                                    // Debug
                                    nobrCount++;
                                }
                            }
                        }
                    }

                    // Actual text reading and manipulation
                    if (readingText) {
                        currentText = currentText + lineOfText.charAt(i + 1);

                        if (lineOfText.charAt(i + 2) == '<') {
                            // Filter aesthetics
                            currentText = currentText.replace("&nbsp;", " ");
                            currentText = currentText.replace("&#x2f;", "/");
                            currentText = currentText.replace("&#x27;", "'");
                            currentText = currentText.replace("&amp;", "&");

                            // Point at which currentText is exactly the information we want
                            if (reportStart) {
                                // ArrayList assignment (and filter), in order of presentation
                                if (columnCounter == GI_DATE && currentText.charAt(0) == '2') {
                                    giDate.add(currentText);
                                }
                                if (columnCounter == ORDER_NO && currentText.charAt(0) == '6') {
                                    orderNo.add(currentText);
                                }
                                if (columnCounter == SOLD_TO && !currentText.substring(0, 4).equals("Sold")) {
                                    soldTo.add(currentText);
                                }
                                if (columnCounter == MATERIAL && currentText.charAt(0) != 'M') {
                                    material.add(currentText);
                                }
                                if (columnCounter == DESCRIPTION && !currentText.substring(0, 4).equals("Desc")) {
                                    description.add(currentText);
                                }
                                if (columnCounter == BATCH && currentText.charAt(0) != 'B') {
                                    batch.add(currentText);
                                }
                                if (columnCounter == NET_WEIGHT && currentText.charAt(0) != 'N') {
                                    netWeight.add(currentText);
                                }
                                if (columnCounter == MANU_DATE && currentText.charAt(0) != 'M') {
                                    manuDate.add(currentText);
                                }
                                if (columnCounter == BB_DATE && currentText.charAt(0) != 'S') {
                                    bbDate.add(currentText);
                                }

                                // Left to right counter adjustment
                                if (columnCounter < COLUMNS_PER_PAGE) {
                                    columnCounter++;

                                } else {
                                    columnCounter = 0;
                                    rowCounter++;
                                }

                                // Reset rowCounter
                                if (rowCounter == ROWS_PER_PAGE) {
                                    rowCounter = 0;
                                }
                            }

                            // Report flag
                            if (currentText.equals(PAGE_START_SIGNAL)) {
                                reportStart = true;

                                columnCounter = 0;
                                columnCounter++;
                            }

                            // Housekeeping
                            currentText = "";
                            readingText = false;
                            readingTag = true;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR! The file was not found: " + logFile.getAbsolutePath());
        }

        // Data arrangement/display
        for (int i = 0; i < batch.size(); i++) {
            String currentOrderNo = "";
            String currentSoldTo = "";
            String currentMaterial = "";
            String currentDescription = "";

            // Repeat check
            if(i > 0) {
                if(checkRepeat(orderNo.get(i), orderNo.get(i - 1))) {
                    currentOrderNo = "           "; // 12 spaces for formatting
                } else {
                    currentOrderNo = orderNo.get(i);
                }

                if(checkRepeat(soldTo.get(i), soldTo.get(i - 1))) {
                    currentSoldTo = "                               "; // 34 spaces
                } else {
                    currentSoldTo = soldTo.get(i);
                }

                if(checkRepeat(material.get(i), material.get(i - 1))) {
                    currentMaterial = "             "; // 16 spaces
                } else {
                    currentMaterial = material.get(i);
                }

                if(checkRepeat(description.get(i), description.get(i - 1))) {
                    currentDescription = "                                "; // 35 spaces
                } else {
                    currentDescription = description.get(i);
                }
            } else {
                currentOrderNo = orderNo.get(i);
                currentSoldTo = soldTo.get(i);
                currentMaterial = material.get(i);
                currentDescription = description.get(i);
            }

            debugString = currentOrderNo + " " + currentSoldTo + " " + currentMaterial + " " + currentDescription
                    + " " + batch.get(i) + " - " + netWeight.get(i) + " - " + manuDate.get(i) + " " + bbDate.get(i);

            System.out.println(debugString);

            // File generation, would love to check and overwrite existing file but daylight is limited
            try {
                FileWriter myWriter = new FileWriter((OUTPUT_FILE_PATH_BASE + LocalDate.now() + ".txt"), true);
                myWriter.write("\n" + debugString + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
    }

    private static boolean checkRepeat(String string1, String string2) {
        boolean repeatStatus = false;

        if(string1.equals(string2)) {
            repeatStatus = true;
        }

        return repeatStatus;
    }
}