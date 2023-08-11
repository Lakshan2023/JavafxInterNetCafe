package com.example.demo2;

import java.time.LocalDate;

public interface ItemDetailsHandler {
    boolean DuplicationItemSolver(String itemCode);

    void InitializeItemDetails(String ItemCode, String ItemName, String ItemBrand, String ItemPrice,
                               String ItemQuantity, String ItemCategory, LocalDate PurchasedDate, String ItemImage);
}
