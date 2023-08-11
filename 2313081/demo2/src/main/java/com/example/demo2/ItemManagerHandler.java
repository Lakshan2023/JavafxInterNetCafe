package com.example.demo2;

import java.time.LocalDate;

public interface ItemManagerHandler {
    boolean validateItemCode(String ItemCode);

    boolean validateItemName(String ItemName);

    boolean validateItemBrand(String ItemBrand);

    boolean validateItemPrice(String ItemPrice);

    boolean validateItemQuantity(String ItemQuantity);

    boolean validateItemCategory(String ItemCategory);

    boolean validatePurchasedDate(LocalDate PurchasedDate);

    boolean validateImage();

    boolean UpdateItemSolver(String ItemCode, String ItemName, String ItemBrand, String ItemPrice,
                             String ItemQuantity, String ItemCategory, LocalDate PurchasedDate, String ImageUrl);

    void DeleteItemSolver(String ItemCode);
}
