package com.example.demo2;

import java.util.*;

// This class stores all the dealer related details
public class DealerDetails extends SwitchSceneManager {

    // Fields that store dealer personal details
    private String DealerName;         // Name of the dealer
    private String ContactNumber;      // Contact Number of the dealer
    private String DealerLocation;     // Location of the dealer

    // Fields that store dealer item details
    private String DealerItemName;     // Name of the dealer item
    private String DealerItemBrand;    // Name of the dealer item brand
    private String DealerItemPrice;    // Dealer item price
    private String DealerItemQuantity; // Dealer item quantity

    // This ArrayList stores details of the dealers after reading the file
    protected static ArrayList<String> DealersNameList = new ArrayList<>();

    // This array stores the randomly generated dealers
    protected static String[] GeneratedRandomDealersList = new String[4];

    // This 2D array stores the personal details of the randomly generated dealers
    static String[][] SelectedAllDealersPersonalDetails = new String[4][3];

    // This ArrayList stores all item details of the selected dealers
    static ArrayList<ArrayList<String[]>> SelectedDealersItemDetails = new ArrayList<>();

    // Constructor that is used to deal with Dealer personal details
    public DealerDetails(String dealerName, String dealerContactNo, String dealerLocation) {
        setDealerName(dealerName);
        setContactNumber(dealerContactNo);
        setDealerLocation(dealerLocation);
    }

    // Constructor that is used to deal with Dealer item details
    public DealerDetails(String DealerItemName, String DealerItemBrand, String DealerItemPrice,
                         String DealerItemQuantity) {
        setDealerItemName(DealerItemName);
        setDealerItemBrand(DealerItemBrand);
        setDealerItemPrice(DealerItemPrice);
        setDealerItemQuantity(DealerItemQuantity);
    }

    // Default constructor
    public DealerDetails() {
    }

    // get dealer name method
    public String getDealerName() {
        return DealerName;
    }

    // set dealer name method
    public void setDealerName(String dealerName) {
        DealerName = dealerName;
    }

    // get contact number method
    public String getContactNumber() {
        return ContactNumber;
    }

    // set contact number method
    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    // get dealer location method
    public String getDealerLocation() {
        return DealerLocation;
    }

    // set dealer location method
    public void setDealerLocation(String dealerLocation) {
        DealerLocation = dealerLocation;
    }

    // get dealer item name method
    public String getDealerItemName() {
        return DealerItemName;
    }

    // set dealer item name method
    public void setDealerItemName(String dealerItemName) {
        DealerItemName = dealerItemName;
    }

    // get dealer item brand method
    public String getDealerItemBrand() {
        return DealerItemBrand;
    }

    // set dealer item brand method
    public void setDealerItemBrand(String dealerItemBrand) {
        DealerItemBrand = dealerItemBrand;
    }

    // get dealer item price method
    public String getDealerItemPrice() {
        return DealerItemPrice;
    }

    // set dealer item price method
    public void setDealerItemPrice(String dealerItemPrice) {
        DealerItemPrice = dealerItemPrice;
    }

    // get dealer item quantity method
    public String getDealerItemQuantity() {
        return DealerItemQuantity;
    }

    // set dealer item quantity method
    public void setDealerItemQuantity(String dealerItemQuantity) {
        DealerItemQuantity = dealerItemQuantity;
    }
}
