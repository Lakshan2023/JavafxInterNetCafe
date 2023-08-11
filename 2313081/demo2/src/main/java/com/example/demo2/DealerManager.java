package com.example.demo2;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

// This class manages all the dealer details related work
public class DealerManager extends DealerDetails implements DealerManagerHandler {

    // 2d array that stores the sorted personal details of the dealers
    protected static String[][] sortedPersonalArray = new String[4][3];

    // ArrayList that stores the sorted item details of the dealer
    protected static ArrayList<ArrayList<String[]>> sortedDealersItemDetails = new ArrayList<>();

    // default constructor that is used to read dealer details from the Dealers.txt
    public DealerManager() {
        readDealers();
    }

    // This method reads the dealer details from Dealers.txt file
    @Override
    public void readDealers() {
        // Use buffered reader to read the Dealers.txt file
        try (BufferedReader readDealers = new BufferedReader(new FileReader("Dealers.txt"))) {
            String readLine;

            // Until the cursor finds a null line it will read the file
            while ((readLine = readDealers.readLine()) != null) {
                // All the readiness are added to DealersNameList ArrayList
                DealersNameList.add(readLine.trim());
            }
        } catch (IOException e) {
            // Throw Runtime Exception if it is unable to read the file
            throw new RuntimeException(e);
        }
    }

    // This method randomly selects four unique dealers
    @Override
    public void generateRandomDealers() throws IOException {
        SwitchSceneManager.randomGeneratedStatus = true;
        Random random = new Random();

        for (int count = 0; count < 4; count++) {
            int selectedNumber; // Randomly generated number
            String selectedDealer; // Selected dealer name

            // This ensures that the same dealer is not selected more than once
            do {
                selectedNumber = random.nextInt(DealersNameList.size());
                selectedDealer = DealersNameList.get(selectedNumber);
                // calling the method checks duplication
            } while (checkDealerSelected(selectedDealer, count));

            // This will assign the selected dealers name to Generated Random Dealers list
            GeneratedRandomDealersList[count] = selectedDealer;
        }

        // Take both personal and item details of the selected dealer
        TakeSelectedDealersDetails();

        // Sort the selected dealers details according the ascending order of the location
        sortSelectedDealers();
    }

    // This method validates the duplication of Random dealer selection
    @Override
    public boolean checkDealerSelected(String dealer, int count) {
        // Check whether the generated dealer name already exists in GeneratedRandomDealersList
        for (int i = 0; i < count; i++) {
            if (GeneratedRandomDealersList[i].equals(dealer)) {
                // If dealer name exists return true
                return true;
            }
        }
        // If dealer name does not exist, return false
        return false;
    }

    // This method takes both selected dealers personal details and item details from their files
    @Override
    public void TakeSelectedDealersDetails() throws IOException {
        for (int index = 0; index < GeneratedRandomDealersList.length; index++) {
            // Take each dealer's text file path
            String selectedDealerTextName = GeneratedRandomDealersList[index] + ".txt";

            // Use buffered reader to read each selected dealer's file
            BufferedReader selectedDealerChecker = new BufferedReader(new FileReader(selectedDealerTextName));

            // Get the dealer's personal details and item details based on the line count
            int lineCount;

            // This tempArray temporarily stores read data of each line of personal details
            String[] tempArray;

            for (lineCount = 0; lineCount < 3; lineCount++) {
                try {
                    // When a line is read, split the words in the line based on ":" and add the words into temp array
                    tempArray = selectedDealerChecker.readLine().trim().split(":");

                    /* First index of the line stores the value and assign that value to required position in
                    * SelectedAllDealersPersonalDetails 2d array*/
                    SelectedAllDealersPersonalDetails[index][lineCount] = tempArray[1];
                } catch (IOException e) {
                    // Throw a runtime exception if it is unable to read the details
                    throw new RuntimeException(e);
                }
            }

            // This arrayList tempList stores both dealer's item  and personal details
            ArrayList<String[]> tempList = new ArrayList<>();
            tempList.add(SelectedAllDealersPersonalDetails[index]);

            String readLine; // This stores each read line
            ArrayList<String> lines = new ArrayList<>();
            while ((readLine = selectedDealerChecker.readLine()) != null) {
                lines.add(readLine); // Add each readline to the lines array
            }
            selectedDealerChecker.close(); // Close the Buffered reader after reading data

            for (int i = 0; i < lines.size(); i += 4) {
                String[] tempItemDetails = new String[4]; // Assigning the tempDetails array to store the dealer items
                for (int count = 0; count < 4 && i + count < lines.size(); count++) {
                    // Trim and split the data in lines based on ":"
                    tempArray = lines.get(i + count).trim().split(":");
                    // Assign the split data in tempArray to the tempItemDetails Array
                    tempItemDetails[count] = tempArray[1];
                }

                tempList.add(tempItemDetails); // Add tempItemDetails array to tempList
            }

            // Add the temp list to selectedDealersItemDetails
            SelectedDealersItemDetails.add(tempList);
        }
    }

    // This method sorts the dealer's details according to the ascending order based on the location
    @Override
    public void sortSelectedDealers() {
        // Take the length of the selected dealer's personal details array
        int dealerListLength = SelectedAllDealersPersonalDetails.length;

        for (int index = 0; index < dealerListLength - 1; index++) {
            int lowestIndex = index; // Make the lowest index as the current index of the loop


            for (int count = index + 1; count < dealerListLength; count++) {
                // Assign the currentValue as the index [count] [2] in SelectedAllDealersPersonalDetails
                String currentValue = SelectedAllDealersPersonalDetails[count][2];

                // Assign the lowest value as the index [the lowest index][2] in SelectedAllDealersPersonalDetails
                String lowestValue = SelectedAllDealersPersonalDetails[lowestIndex][2];

                /* This checks whether the current value is not null and lowest value is not null and
                * current value is smaller than the lowest value */
                if (currentValue != null && lowestValue != null && currentValue.compareTo(lowestValue) < 0) {
                    // If the current value is smaller than the lowest value, assign it as the lowest index
                    lowestIndex = count;
                }
            }

            // Check whether the lowest index is not equals to the index
            if (lowestIndex != index) {
                // Assign the temp value SelectedAllDealersPersonalDetails[index]
                String[] temp = SelectedAllDealersPersonalDetails[index];
                // Assign the lowest value to that index
                SelectedAllDealersPersonalDetails[index] = SelectedAllDealersPersonalDetails[lowestIndex];
                // Assign the temp value to the lowest value index
                SelectedAllDealersPersonalDetails[lowestIndex] = temp;

                //Then assign the values as above to the SelectedDealersItemDetails table
                ArrayList<String[]> tempArrayList = SelectedDealersItemDetails.get(index);
                SelectedDealersItemDetails.set(index, SelectedDealersItemDetails.get(lowestIndex));
                SelectedDealersItemDetails.set(lowestIndex, tempArrayList);
            }
        }

        // Assign the SelectedAllDealersPersonalDetails sorted array to sortedPersonalArray
        sortedPersonalArray = SelectedAllDealersPersonalDetails;

        // Assign the SelectedDealersItemDetails sorted array to sortedDealersItemDetails
        sortedDealersItemDetails = SelectedDealersItemDetails;
    }
}
