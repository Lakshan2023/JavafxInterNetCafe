package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

// This class stores all Item related details
public class ItemDetails extends SwitchSceneManager implements ItemDetailsHandler, Serializable {

    // Following details will be stored for each item
    private String ItemCode; // This stores the Item Code
    private String ItemName; // This stores the Item Name
    private String ItemBrand; // This stores the Item Brand
    private double ItemPrice; // This stores the Item Price
    private int ItemQuantity; // This stores the Item Quantity
    private String ItemCategory; // This stores the Item Category
    private LocalDate PurchasedDate; // This stores the Item Purchase Date
    private String ImageView; // This stores Path (URL) of the image
    @FXML
    private ImageView absoluteImage;

    // It stores the Item details in the ArrayList
    public static ArrayList<ArrayList<Object>> ItemDetailsList = new ArrayList<>();

    // This ArrayList temporarily stores the item details of each item
    public ArrayList<Object> TemporaryDetailStorage = new ArrayList<>();
    boolean ItemDuplicationStatus; // This checks the Item duplication status
    public static int UpdateIndex; // This variable tracks the Item Update Index
    public static int DeleteIndex; // This variable tracks the Item Delete Index

    // Assigned default constructor
    public ItemDetails() {

    }

    // This constructor is used to assign the values to Item details
    public ItemDetails(String ItemCode, String ItemName, String ItemBrand, double ItemPrice, int ItemQuantity,
                       String ItemCategory, LocalDate PurchasedDate, String ImageView) {
        this.ItemCode = ItemCode; // Assign the item code
        this.ItemName = ItemName; // Assign the item name
        this.ItemBrand = ItemBrand; // Assign the item brand
        this.ItemPrice = ItemPrice; // Assign the item price
        this.ItemQuantity = ItemQuantity; // Assign the item quantity
        this.ItemCategory = ItemCategory; // Assign the item Category
        this.PurchasedDate = PurchasedDate; // Assign the item purchased date
        setAbsoluteImage(ImageView); // Sets the image using image path
    }

    // This method gets the Item Code
    public String getItemCode() {
        return ItemCode;
    }

    // This method sets the Item Code
    public void setItemCode(long itemCode) {
        ItemCode = String.valueOf(itemCode);
    }

    // This method gets the Item Name
    public String getItemName() {
        return ItemName;
    }

    // This method sets the Item Name
    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    // This method gets the Item Brand
    public String getItemBrand() {
        return ItemBrand;
    }

    // This method sets the Item Brand
    public void setItemBrand(String itemBrand) {
        ItemBrand = itemBrand;
    }

    // This method gets the Item Price
    public double getItemPrice() {
        return ItemPrice;
    }

    // This method sets the Item Price
    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }

    // This method gets the Item Quantity
    public int getItemQuantity() {
        return ItemQuantity;
    }

    // This method sets the Item Quantity
    public void setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    // This method gets the Item Category
    public String getItemCategory() {
        return ItemCategory;
    }

    // This method sets the Item Category
    public void setItemCategory(String itemCategory) {
        ItemCategory = itemCategory;
    }

    // This method gets the Item Purchase Date
    public LocalDate getPurchasedDate() {
        return PurchasedDate;
    }

    // This method sets the Item Purchased dates
    public void setPurchasedDate(LocalDate purchasedDate) {
        PurchasedDate = purchasedDate;
    }

    // This method handles the duplication of items
    @Override
    public boolean DuplicationItemSolver(String itemCode) {
        // This loop checks whether ItemDetailsList contains the itemCode
        for (ArrayList<Object> itemDetails : ItemDetailsList) {
            // Take the item code of the index in the ItemDetailsList
            String currentCode = (String) itemDetails.get(0);
            if (currentCode.equals(itemCode)) {
                // If item code exist, return true
                return true;
            }
        }
        // If item code does not exist in the ItemDetailsList, return false
        return false;
    }

    @Override
    public void InitializeItemDetails(String ItemCode, String ItemName, String ItemBrand, String ItemPrice,
                                      String ItemQuantity, String ItemCategory, LocalDate PurchasedDate, String ItemImage)
    {
        /* If item code, item name, item brand, item price, item quantity, item category and purchased date
        * is not null assign the values to the TemporaryDetailsStorage.*/

        if (ItemCode != null && ItemName != null && ItemBrand != null && ItemPrice != null && ItemQuantity != null &&
                ItemCategory != null && PurchasedDate != null) {

            // If item code is not duplicated add the details to the Temporary Detail Storage
            if (!DuplicationItemSolver(ItemCode)) {
                TemporaryDetailStorage.add(ItemCode);         // Add the Item code
                TemporaryDetailStorage.add(ItemName);         // Add the Item name
                TemporaryDetailStorage.add(ItemBrand);        // Add the Item brand
                TemporaryDetailStorage.add(ItemPrice);        // Add the Item name
                TemporaryDetailStorage.add(ItemQuantity);     // Add the Item quantity
                TemporaryDetailStorage.add(ItemCategory);     // Add the Item Category
                TemporaryDetailStorage.add(PurchasedDate);    // Add the Item Purchased Date
                TemporaryDetailStorage.add(ItemImage);        // Add the Item Image
                ItemDetailsList.add(TemporaryDetailStorage);  // Add the temporary details storage to Item details List
                ItemDuplicationStatus = false; // assign item duplication status as false
            } else {
                ItemCodeExistAlert(); // Call ItemCodeExistAlert method
                ItemDuplicationStatus = true; // assign item duplication status as true
            }
        }
    }

    // This method gets the Item Image View
    public String getImageView() {
        return ImageView;
    }

    // This method sets the Item Image View
    public void setImageView(String imageView) {
        ImageView = String.valueOf(imageView);
    }

    // This method gets the absolute image
    public ImageView getAbsoluteImage() {
        return absoluteImage;
    }

    public void setAbsoluteImage(String URL) {
        // This checks whether the image path is null
        if (URL != null) {
            double desiredWidth = 120; // Set the desired width
            double desiredHeight = 90; // Set the desired height

            // This sets the image path to the Image
            Image image = new Image(URL);

            // This assigns the Image View to the absolute image
            this.absoluteImage = new ImageView(image);

            // set the absolute image width to 120
            absoluteImage.setFitWidth(desiredWidth);

            // set the absolute image width to 90
            absoluteImage.setFitHeight(desiredHeight);
        } else {
            // If image path is null, set the absolute image as null
            this.absoluteImage = null;
        }

        //https://www.tutorialspoint.com/javafx/javafx_images.htm
    }

    // This method shows the alert that Item code exists
    public void ItemCodeExistAlert() {
        Alert alertDuplication = new Alert(Alert.AlertType.ERROR);
        alertDuplication.setTitle("Duplicate value");
        alertDuplication.setHeaderText("That Item already exists in the system !!!");
        alertDuplication.show(); // This shows the alert
    }
}
