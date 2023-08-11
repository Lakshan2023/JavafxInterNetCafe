package com.example.demo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealerManagerTest {
    private DealerManager dealerManager;

    @BeforeEach
    void setUp() {
        dealerManager = new DealerManager();
    }

    @Test
    void readDealersFile() {
        // Verify that the DealersNameList is not empty after reading the file
        assertFalse(DealerManager.DealersNameList.isEmpty());
    }

    // Method to validate it generates for unique random dealers
    @Test
    void generateRandomDealersSuccessShouldGenerateFourUniqueDealers() throws IOException {
        // Call the generateRandomDealers method
        dealerManager.generateRandomDealers();

        // Verify that the GeneratedRandomDealersList contains 4 elements
        assertEquals(4, DealerManager.GeneratedRandomDealersList.length);

        // Verify that there are no duplicate dealers in the GeneratedRandomDealersList
        for (int i = 0; i < DealerManager.GeneratedRandomDealersList.length - 1; i++) {
            for (int j = i + 1; j < DealerManager.GeneratedRandomDealersList.length; j++) {
                assertNotEquals(DealerManager.GeneratedRandomDealersList[i], DealerManager.GeneratedRandomDealersList[j]);
            }
        }
    }

    // Method to validate the code ignores duplicate dealers
    @Test
    void checkDealerSelectedNoDuplicateDealersReturnsFalseForNewDealer() {
        // Manually set some dealers in GeneratedRandomDealersList
        DealerManager.GeneratedRandomDealersList[0] = "Malik Peiris";
        DealerManager.GeneratedRandomDealersList[1] = "Dinesh Gomes";
        DealerManager.GeneratedRandomDealersList[2] = "James Murphy";

        // Verify that the checkDealerSelected method returns false for a new dealer
        assertFalse(dealerManager.checkDealerSelected("Simon Watson", 3));
    }

    // Method to validate sort selected dealers success considering location
    @Test
    void sortSelectedDealersSuccessShouldSortDealersBasedOnLocation() {
        // Set up test data
        String[][] selectedAllDealersPersonalDetails = {
                {"James Murphy", "Badulla", "300"},
                {"Malik Peiris", "Negambo", "100"},
                {"Dinesh Gomes", "Nugegoda", "200"}
        };

        ArrayList<ArrayList<String[]>> selectedDealersItemDetails = new ArrayList<>();
        selectedDealersItemDetails.add(new ArrayList<>());
        selectedDealersItemDetails.get(0).add(new String[]{"Network Switch", "10", "1000"});
        selectedDealersItemDetails.add(new ArrayList<>());
        selectedDealersItemDetails.get(1).add(new String[]{"Laptop", "20", "2000"});
        selectedDealersItemDetails.add(new ArrayList<>());
        selectedDealersItemDetails.get(2).add(new String[]{"Key Board", "30", "3000"});

        // Set the test data in the DealerManager
        DealerManager.SelectedAllDealersPersonalDetails = selectedAllDealersPersonalDetails;
        DealerManager.SelectedDealersItemDetails = selectedDealersItemDetails;

        // Call the sortSelectedDealers method
        dealerManager.sortSelectedDealers();

        // Verify that the dealers are sorted in ascending order based on location
        assertEquals("Malik Peiris", DealerManager.sortedPersonalArray[0][0]);
        assertEquals("Negambo", DealerManager.sortedPersonalArray[0][1]);
        assertEquals("100", DealerManager.sortedPersonalArray[0][2]);

        assertEquals("Dinesh Gomes", DealerManager.sortedPersonalArray[1][0]);
        assertEquals("Nugegoda", DealerManager.sortedPersonalArray[1][1]);
        assertEquals("200", DealerManager.sortedPersonalArray[1][2]);

        assertEquals("James Murphy", DealerManager.sortedPersonalArray[2][0]);
        assertEquals("Badulla", DealerManager.sortedPersonalArray[2][1]);
        assertEquals("300", DealerManager.sortedPersonalArray[2][2]);

        // Verify that the item details are also sorted accordingly
        assertEquals("Laptop", DealerManager.sortedDealersItemDetails.get(0).get(0)[0]);
        assertEquals("Key Board", DealerManager.sortedDealersItemDetails.get(1).get(0)[0]);
        assertEquals("Network Switch", DealerManager.sortedDealersItemDetails.get(2).get(0)[0]);
    }
}