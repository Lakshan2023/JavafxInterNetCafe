package com.example.demo2;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemManagerTest {

    private ItemManager itemManager;

    @Before
    public void setUp() {
        itemManager = new ItemManager();
    }

    // Method to validate item valid name
    @Test
    public void testValidateItemNameValidName() {
        String validItemName = "Example Item";

        boolean result = itemManager.validateItemName(validItemName);

        assertTrue(result);
        assertFalse(ItemManager.ItemNameStatus);
    }

    // Method to validate empty name
    @Test
    public void testValidateItemNameInvalidNameEmpty() {
        String invalidItemName = "";

        boolean result = itemManager.validateItemName(invalidItemName);

        assertFalse(result);
        assertTrue(ItemManager.ItemNameStatus);
    }

    // Method to validate valid item brand
    @Test
    public void testValidateItemBrandValidBrand() {
        String validItemBrand = "Example Brand";

        boolean result = itemManager.validateItemBrand(validItemBrand);

        assertTrue(result);
        assertFalse(ItemManager.ItemBrandStatus);
    }

    // Method to validate empty Item Brand
    @Test
    public void testValidateItemBrandInvalidBrandEmpty() {
        String invalidItemBrand = "";

        boolean result = itemManager.validateItemBrand(invalidItemBrand);

        assertFalse(result);
        assertTrue(ItemManager.ItemBrandStatus);
    }

    // Method to validate valid Item Price
    @Test
    public void testValidateItemPriceValidPrice() {
        String validItemPrice = "49.99";

        boolean result = itemManager.validateItemPrice(validItemPrice);

        assertTrue(result);
        assertFalse(ItemManager.ItemPriceStatus);
    }

    // Method to validate invalid empty price
    @Test
    public void testValidateItemPriceInvalidPriceEmpty() {
        String invalidItemPrice = "";

        boolean result = itemManager.validateItemPrice(invalidItemPrice);

        assertFalse(result);
        assertTrue(ItemManager.ItemPriceStatus);
    }

    // Method to validate invalid price that contains spaces
    @Test
    public void testValidateItemPriceInvalidPriceContainsSpace() {
        String invalidItemPrice = "12 34.56";

        boolean result = itemManager.validateItemPrice(invalidItemPrice);

        assertFalse(result);
        assertTrue(ItemManager.ItemPriceStatus);
    }

    // Method to validate invalid price that contains negative values
    @Test
    public void testValidateItemPriceInvalidPriceNegativeValue() {
        String invalidItemPrice = "-29.99";

        boolean result = itemManager.validateItemPrice(invalidItemPrice);

        assertFalse(result);
        assertTrue(ItemManager.ItemPriceStatus);
    }

    // Method to validate invalid item price tha is not numeric
    @Test
    public void testValidateItemPriceInvalidPriceNonNumeric() {
        String invalidItemPrice = "abc";

        boolean result = itemManager.validateItemPrice(invalidItemPrice);

        assertFalse(result);
        assertTrue(ItemManager.ItemPriceStatus);
    }

    // Method validate validate valid item quantity
    @Test
    public void testValidateItemQuantityValidQuantity() {
        String validItemQuantity = "10";

        boolean result = itemManager.validateItemQuantity(validItemQuantity);

        assertTrue(result);
        assertFalse(ItemManager.ItemQuantityStatus);
    }

    // Method to validate invalid item quantity that is empty
    @Test
    public void testValidateItemQuantityInvalidQuantityEmpty() {
        String invalidItemQuantity = "";

        boolean result = itemManager.validateItemQuantity(invalidItemQuantity);

        assertFalse(result);
        assertTrue(ItemManager.ItemQuantityStatus);
    }

    // Method to validate invalid item quantity that contains spaces
    @Test
    public void testValidateItemQuantityInvalidQuantityContainsSpace() {
        String invalidItemQuantity = "5  ";

        boolean result = itemManager.validateItemQuantity(invalidItemQuantity);

        assertFalse(result);
        assertTrue(ItemManager.ItemQuantityStatus);
    }

    // Method to validate Invalid item quantity that contains negative values
    @Test
    public void testValidateItemQuantityInvalidQuantityNegativeValue() {
        String invalidItemQuantity = "-3";

        boolean result = itemManager.validateItemQuantity(invalidItemQuantity);

        assertFalse(result);
        assertTrue(ItemManager.ItemQuantityStatus);
    }

    // Method to validate invalid item quantity that contains letters and symbols
    @Test
    public void testValidateItemQuantityInvalidQuantityNonNumeric() {
        String invalidItemQuantity = "xyz@";

        boolean result = itemManager.validateItemQuantity(invalidItemQuantity);

        assertFalse(result);
        assertTrue(ItemManager.ItemQuantityStatus);
    }

    // Method to validate valid item category
    @Test
    public void testValidateItemCategoryValidCategory() {
        String validItemCategory = "Electronics";

        boolean result = itemManager.validateItemCategory(validItemCategory);

        assertTrue(result);
        assertFalse(ItemManager.ItemCategoryStatus);
    }

    // Method to validate invalid item category
    @Test
    public void testValidateItemCategoryInvalidCategoryEmpty() {
        String invalidItemCategory = "";

        boolean result = itemManager.validateItemCategory(invalidItemCategory);

        assertFalse(result);
        assertTrue(ItemManager.ItemCategoryStatus);
    }

    // Method to validate valid purchased date
    @Test
    public void testValidatePurchasedDateValidDate() {
        LocalDate validDate = LocalDate.now();

        boolean result = itemManager.validatePurchasedDate(validDate);

        assertTrue(result);
        assertFalse(ItemManager.ItemDateStatus);
    }

    // Method to validate future dates
    @Test
    public void testValidatePurchasedDateInvalidDateFutureDate() {
        LocalDate invalidDate = LocalDate.now().plusDays(1);

        boolean result = itemManager.validatePurchasedDate(invalidDate);

        assertFalse(result);
        assertTrue(ItemManager.ItemDateStatus);
    }

    //Method to validate purchased dates that are null
    @Test
    public void testValidatePurchasedDateInvalidDateNull() {
        LocalDate invalidDate = null;

        boolean result = itemManager.validatePurchasedDate(invalidDate);

        assertFalse(result);
        assertTrue(ItemManager.ItemDateStatus);
    }


    // Method to validate sort item details
    @Test
    public void testSortItemDetailsList() {
        ArrayList<ArrayList<Object>> unsortedList = new ArrayList<>();
        ArrayList<Object> item1 = new ArrayList<>();
        item1.add("0001");
        item1.add("Item 1");
        unsortedList.add(item1);

        ArrayList<Object> item2 = new ArrayList<>();
        item2.add("0002");
        item2.add("Item 2");
        unsortedList.add(item2);

        ArrayList<ArrayList<Object>> sortedList = ItemManager.SortItemDetailsList(unsortedList);

        // Verify that the items are sorted in ascending order based on item code
        assertEquals("0001", sortedList.get(0).get(0));
        assertEquals("0002", sortedList.get(1).get(0));
    }
}