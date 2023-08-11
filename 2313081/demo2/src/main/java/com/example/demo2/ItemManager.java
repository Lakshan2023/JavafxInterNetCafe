package com.example.demo2;
import javafx.scene.control.Alert;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

// This class manages all the item related details
public class ItemManager extends ItemDetails implements ItemManagerHandler, Serializable {

   // This ArrayList stores the sorted item details in ascending order according to the item code
   public static ArrayList<ArrayList<Object>> SortedList;
   public boolean InputCorrectStatus = false;
   public static boolean ItemCodeStatus = false; // This checks whether Item Code is valid
   public static boolean ItemNameStatus = false; // This checks whether Item Name is valid
   public static boolean ItemBrandStatus = false; // This checks whether Item Brand is valid
   public static boolean ItemDateStatus = false; // This checks whether Item Purchased date is valid
   public static boolean ItemCategoryStatus = false; // This checks whether Item Category is valid
   public static boolean ItemPriceStatus = false; // This checks whether Item price is valid
   public static boolean ItemQuantityStatus = false; // This checks whether Item Quantity is valid

   static String PreviousItemCode; // This temporarily stores the previous item code when Updating details

   public boolean futureDateStat = false; // This checks whether the item purchased date is a future date

   // Default constructor of the Item Manager
   public ItemManager(){

   }

   // Constructor that is used to validate item data and assign it to the item details
   public ItemManager(String ItemCode, String ItemName, String ItemBrand, String ItemPrice,
                      String ItemQuantity, String ItemCategory, LocalDate PurchasedDate, String ItemImage) {

      if (validateItemCode(ItemCode) &&
              validateItemName(ItemName) &&
              validateItemBrand(ItemBrand) &&
              validateItemPrice(ItemPrice) &&
              validateItemQuantity(ItemQuantity) &&
              validateItemCategory(ItemCategory) &&
              validatePurchasedDate(PurchasedDate) &&
              validateImage()) {

         // If all the above given item details are valid, set the item details to Item Details Class
         setItemCode(Long.parseLong(ItemCode)); // Set the Item Code
         setItemName(ItemName); // Set the Item Name
         setItemBrand(ItemBrand); // Set the Item Brand
         setItemPrice(Double.parseDouble(ItemPrice)); // Set the Item Price
         setItemQuantity(Integer.parseInt(ItemQuantity)); // Set the Item Quantity
         setItemCategory(ItemCategory); // Set the Item Category
         setPurchasedDate(PurchasedDate); // Set the Item Purchased date
         setImageView(ItemImage); // Set Image View

         // This method will initialize the item details and store in the Item Details List
         InitializeItemDetails(ItemCode, ItemName, ItemBrand, ItemPrice, ItemQuantity, ItemCategory, PurchasedDate,
                 ItemImage);

         // Validate package is again called to clear the Error fields
         validationPackage(ItemCode, ItemName, ItemBrand, ItemPrice, ItemQuantity, ItemCategory, PurchasedDate);

         // After adding the data, the details will be sorted according to the ascending order
         SortedList = SortItemDetailsList(ItemDetailsList);

         InputCorrectStatus = true; // If Adding data successful Make input correct status true

      } else {
         // If Item details are not valid display below alerts by checking the conditions
         displayFutureDateAlert(); // Check whether user entered date is a future date
         displayImageAlert();    // Check whether user has entered the item image
         displayErrorAlert(); // Check whether there are any other issues in user input
         InputCorrectStatus = false;  // If adding data not successful Make input correct status false
         // Perform the validation package
         validationPackage(ItemCode, ItemName, ItemBrand, ItemPrice, ItemQuantity, ItemCategory, PurchasedDate);
      }
   }

   // This validation package performs validating all the properties of the item details
   public void validationPackage(String ItemCode, String ItemName, String ItemBrand, String ItemPrice,
                                 String ItemQuantity, String ItemCategory, LocalDate PurchasedDate){
      validateItemCode(ItemCode); // Check if the Item code is valid
      validateItemName(ItemName); // Check if the Item Name is valid
      validateItemBrand(ItemBrand); // Check if the Item Brand is valid
      validateItemPrice(ItemPrice); // Check if the Item Price is valid
      validateItemQuantity(ItemQuantity); // Check if the Item Quantity is valid
      validateItemCategory(ItemCategory); // Check if the Item Category is valid
      validatePurchasedDate(PurchasedDate); // Check if the Item Purchased date is valid
   }

   // This method checks the validity of the Item code when Updating and Adding
   @Override
   public boolean validateItemCode(String ItemCode) {
      // Check whether Item code text field is empty
      if (ItemCode.isEmpty() || ItemCode.contains(" ")) {
         ItemCodeStatus = true; // Update item code invalid status as true
         return false;
      }
      try {
         // Check whether Item code consist with only integers
         long itemCode = Long.parseLong(ItemCode);
         // Check whether Item code is greater or equals to 0
         if(itemCode < 0){
            ItemCodeStatus = true; // Update item code invalid status as true
            return false;
         }

         // If Item code contains letters and any characters show the exception
      } catch (NumberFormatException e) {
         ItemCodeStatus = true; // Update item code invalid status as true
         return false;
      }

      // Update item code invalid status as false, if Item code is valid
      ItemCodeStatus = false;
      return true;

   }

   // This method checks the validity of the Item name when Updating and Adding
   @Override
   public boolean validateItemName(String ItemName){
      // Check whether Item name text field is empty
      if(ItemName.isEmpty()){
         ItemNameStatus = true; // Update item name invalid status as true
         return false;
      }

      // Update item name invalid status as false, if Item name is valid
      ItemNameStatus = false;
      return true;
   }

   // This method checks the validity of the Item brand when Updating and Adding
   @Override
   public boolean validateItemBrand(String ItemBrand){
      // Check whether Item brand text field is empty
      if(ItemBrand.isEmpty()){
         ItemBrandStatus = true; // Update item brand invalid status as true
         return false;
      }

      // Update item brand invalid status as false, if Item brand is valid
      ItemBrandStatus = false;
      return true;
   }

   // This method checks the validity of the Item price when Updating and Adding
   @Override
   public boolean validateItemPrice(String ItemPrice){
      // Check whether Item price text field is empty
      if (ItemPrice.isEmpty() || ItemPrice.contains(" ")) {
         ItemPriceStatus = true; // Update item price invalid status as true
         return false;
      }
      try {
         // Check whether Item price consist with only integers or decimals
         double price = Double.parseDouble(ItemPrice);

         // Check whether Item price is greater or equals to 0
         if(price <  0){
            // If Item price is less than 0, update item price invalid status as true
            ItemPriceStatus = true;
            return false;
         }

         // If Item price contains letters and any characters show the exception
      } catch (NumberFormatException e) {
         ItemPriceStatus = true; // Update item price invalid status as true
         return false;
      }

      // Update item price invalid status as false, if Item brand is valid
      ItemPriceStatus = false;
      return true;
   }

   // This method checks the validity of the Item quantity when Updating and Adding
   @Override
   public boolean validateItemQuantity(String ItemQuantity){
      // Check whether Item quantity text field is empty
      if (ItemQuantity.isEmpty() || ItemQuantity.contains(" ")) {
         ItemQuantityStatus = true; // Update item quantity invalid status as true
         return false;
      }
      try {
         // Check whether Item quantity consist with only integers
        int quantity =  Integer.parseInt(ItemQuantity);

         // Check whether Item quantity is greater or equals to 0
        if(quantity < 0){
           // If Item quantity is less than 0, update item price invalid status as true
           ItemQuantityStatus = true;
           return false;
        }

         // If Item quantity contains letters and any characters show the exception
      } catch (NumberFormatException e) {
         ItemQuantityStatus = true;
         return false;
      }

      // Update item quantity invalid status as false, if Item quantity is valid
      ItemQuantityStatus = false;
      return true;

   }

   // This method checks the validity of the Item category when Updating and Adding
   @Override
   public boolean validateItemCategory(String ItemCategory){
      // Check whether Item category text field is empty
      if(ItemCategory.isEmpty()){
         ItemCategoryStatus = true; // Update item quantity invalid status as true
         return false;
      }

      // Update item category invalid status as false, if Item category is valid
      ItemCategoryStatus = false;
      return true;
   }

   // This method checks the validity of the Item purchased date when Updating and Adding
   @Override
   public boolean validatePurchasedDate(LocalDate PurchasedDate){
      // Check whether Item purchased date is null
      if(PurchasedDate == null){
         ItemDateStatus = true; // If item date is null,Update item purchased date invalid status as true
         return false;
      }

      // Check whether Item purchased date is a future date
      if(PurchasedDate.isAfter(LocalDate.now())){
         ItemDateStatus = true; // If it is a future date, Update item purchased date invalid status as true
         futureDateStat = true; // Update future date stat as true since it is a future date
         return false;
      }else{
         futureDateStat = false; // Update future date stat as false, if it is a past or the current date
      }
      ItemDateStatus = false;
      return true;
   }

   // This method checks the validity of the Item Image when Updating and Adding
   @Override
   public boolean validateImage(){
      return SwitchSceneManager.imagePath != null; // Check whether image path is null
   }

   // This method sorts the Item code based on the string values in ascending order
   public static ArrayList<ArrayList<Object>> SortItemDetailsList(ArrayList<ArrayList<Object>> itemDetailsList) {
      int size = itemDetailsList.size(); // Take the length of the ArrayList Item details list
      for (int count = 0; count < size - 1; count++) {
         for (int time = 0; time < size - count - 1; time++) {
            // comparing data that is stored next to each other
            String currentItemCode = (String) itemDetailsList.get(time).get(0); // take the string value of an index

            //take the string value after that index
            String nextItemCode = (String) itemDetailsList.get(time + 1).get(0);

            /* Checks the first index String value(currentItemCode)
             is greater compared to the next index string value(nextItemCode)*/

            if (currentItemCode.compareTo(nextItemCode) > 0) {
               // If it is, then switch the values
               // Assign the temporary value as the value in first index
               ArrayList<Object> temp = itemDetailsList.get(time);

               // Assign the next index value to the first index
               itemDetailsList.set(time, itemDetailsList.get(time + 1));
               itemDetailsList.set(time + 1, temp); // Assign the temp value to the next index
            }
         }
      }
      return itemDetailsList; // This will return the sorted array
   }

   // This method finds the updating index of the user given item code
   public static void UpdateIndexFinder(String ItemCode){
      for(int index = 0; index< ItemDetailsList.size(); index++){
         // Check whether user given item code equals to the item code in the index in ItemDetailsList
         if(ItemDetailsList.get(index).get(0).equals(ItemCode)){
            UpdateIndex = index; // If it is, assign the index as the Update index
         }
      }
   }

   // This method handles updating the existing values of the stored items
   @Override
   public boolean UpdateItemSolver(String ItemCode, String ItemName, String ItemBrand, String ItemPrice,
                                   String ItemQuantity, String ItemCategory, LocalDate PurchasedDate, String ImageUrl){

      // Check the validity of the item code, item name, brand, price, quantity, category and purchased date
      if((validateItemCode(ItemCode)) && validateItemName(ItemName) &&
              validateItemBrand(ItemBrand) &&
              validateItemPrice(ItemPrice) && validateItemQuantity(ItemQuantity) &&
              validateItemCategory(ItemCategory) && validatePurchasedDate(PurchasedDate))
      {

         // If all details are valid, check whether user updated item code already exists in the system
         for(ArrayList<Object> index : ItemDetailsList){
            if(index.get(0).equals(ItemCode)  && !ItemCode.equals(ItemManager.PreviousItemCode)){
               ItemCodeExistAlert(); // If it exists display the Item code exists alert and break the method
               return false;
            }
         }

         try{
            // If all the validations are successful, make the previous item code as the current item code
            ItemManager.PreviousItemCode = ItemCode;
            // Then assign the values to the temporary array list
            // get the index values from ItemDetailsList
            ArrayList<Object> item = ItemDetailsList.get(UpdateIndex);
            item.set(0, ItemCode);
            item.set(1, ItemName);
            item.set(2, ItemBrand);
            item.set(3, ItemPrice);
            item.set(4, ItemQuantity);
            item.set(5, ItemCategory);
            item.set(6, PurchasedDate);

            if(ImageUrl != null){
               item.set(7, ImageUrl); // If image url is not null set the image url
            }

            ItemDetailsList.set(UpdateIndex, item); // Update the given item details in item details list

            SortItemDetailsList(ItemDetailsList); // Then sort the item details in ascending order

         }catch (IndexOutOfBoundsException error){
            // If no any items found, display the below massage
            Alert noItemAlert = new Alert(Alert.AlertType.ERROR);
            noItemAlert.setHeaderText("No saved Items Massage");
            noItemAlert.setHeaderText("No saved Items in the System !!!");
            noItemAlert.show();
            return false;
         }

         // If item update process is successful, display the below massage
         Alert UpdateAlert = new Alert(Alert.AlertType.INFORMATION);
         UpdateAlert.setTitle("Update Item Status");
         UpdateAlert.setHeaderText("Item Updated Successfully !!!");
         UpdateAlert.show();

         // After updating details clear all the field based on validation package
         validationPackage(ItemCode, ItemName, ItemBrand, ItemPrice, ItemQuantity, ItemCategory, PurchasedDate);

         return true;
      }

      // If update details not successful use validation package to show error massages
      validationPackage(ItemCode, ItemName, ItemBrand, ItemPrice, ItemQuantity, ItemCategory, PurchasedDate);
      displayFutureDateAlert(); // Check whether the date entered is a future date
      displayErrorAlert();

      return false;

   }

   // This method displays the Error alert when input and update details are not valid
   public static void displayErrorAlert(){
      Alert InvalidInput = new Alert(Alert.AlertType.ERROR);
      InvalidInput.setTitle("Invalid Input");
      InvalidInput.setHeaderText("Please check whether you have filled everything correctly !!!");
      InvalidInput.show(); // show the alert
   }

   // This method displays the Image input error when image is not imported when adding item details
   public void displayImageAlert(){
      if(SwitchSceneManager.imagePath == null){
         Alert imageAlert= new Alert(Alert.AlertType.ERROR);
         imageAlert.setTitle("Item Image Error");
         imageAlert.setHeaderText("Please Input the Image of the item !!!");
         imageAlert.show(); // Show the alert
      }

   }

   // This method displays the future date alert, if user has selected a future date as the purchased date
   public void displayFutureDateAlert(){
      if(futureDateStat){
         Alert alertFutureDate = new Alert(Alert.AlertType.ERROR);
         alertFutureDate.setTitle("Item Purchase Date Error");
         alertFutureDate.setHeaderText("Future Dates are not allowed !!!, please input a past date!!!");
         alertFutureDate.show(); // show the alert
      }
   }

   // This method finds the index of the user selected deleting item
   public void DeleteIndexFinder(String ItemCode){
         for(int index = 0; index< ItemDetailsList.size(); index++){
            // Check whether user given item code equals to the item code in the index in ItemDetailsList
            if(ItemDetailsList.get(index).get(0).equals(ItemCode)){
               DeleteIndex = index; // If it is, assign the index as the Delete index
            }
         }
   }

   // This method handles deleting items from the system
   @Override
   public void DeleteItemSolver(String ItemCode){
      DeleteIndexFinder(ItemCode); // Calling DeleteIndexFinder  to find the deleting index
      ItemDetailsList.remove(DeleteIndex); //remove the deleting index from Item details list
      SortItemDetailsList(ItemDetailsList); // Sort the item details list
   }

   // This method handles saving item details in "SavedItems.txt" using object serialization
   protected static void SaveItemDetails() throws IOException {
      String filePath = "SavedItems.txt"; // take the file path that details has to be saved

      makeFileWritable(filePath); // Make the file writable before saving since, the file is only readable

      try (
              // Open the file output stream to write data to the file
              FileOutputStream fOutStream = new FileOutputStream(filePath, false);

              // Open the buffered output stream to improve the performance of  writing data to the file
              BufferedOutputStream bufferedOutStream = new BufferedOutputStream(fOutStream);

              // Open the object output stream to serialize and write the objects to the file
              ObjectOutputStream oOutStream = new ObjectOutputStream(bufferedOutStream)
      ) {
         // Check whether item details stored in the SortedList is null
         if (SortedList != null) {

            // If not, write the details to the text file using Object Output stream
            for (ArrayList<Object> sublist : SortedList) {
               oOutStream.writeObject(sublist); // Each sublist will be written in a single line from the Sorted List
               oOutStream.writeObject("\n");
            }
         }
      } catch (Exception e) {
         e.printStackTrace(); // If it fails to write on the file, handle the erro
      }

     // Set the file read only after writing on the file.
      makeFileReadOnly(filePath);

      //https://www.baeldung.com/java-serialization
   }

   // This method enables the selected file writable
   public static void makeFileWritable(String filePath) {
      File file = new File(filePath); // get the file path

      if (file.exists()) {
         file.setWritable(true, true); // enable the file as writable
      }
   }

   // This method makes the file read only
   public static void makeFileReadOnly(String filePath) {
      File file = new File(filePath); // get the file path

      if (file.exists()) {
         file.setReadOnly(); // makes the file read only
      }
   }

   // This method read the saved details in the system and assign the read data to item details list
   protected static void LoadItemDetails() {
      String filePath = "SavedItems.txt";  // take the file path that has to be read

      try (
              // Open the file input stream to read data from the file
              FileInputStream fInStream = new FileInputStream(filePath);

              // Open the buffered input stream to improve the performance of reading the file
              BufferedInputStream bufferedInStream = new BufferedInputStream(fInStream);

              // Open the Object input stream to deserialize and read the objects of the file
              ObjectInputStream oInStream = new ObjectInputStream(bufferedInStream)
      ) {
         Object obj;
         // read the objects until it comes to the end of file
         while ((obj = oInStream.readObject()) != null) {

            // Checks whether the reading objects are Arraylists
            if (obj.getClass().equals(ArrayList.class)) {

               // If it is, then cast the taken object to ArryList of objects
               ArrayList<Object> sublist = (ArrayList<Object>) obj;

               // Convert the 7th element to LocalDate if the 7 th element is a string
               Object dateElement = sublist.get(6);
               if (dateElement.getClass().equals(String.class)) {
                  String dateString = (String) dateElement;
                  LocalDate purchasedDate = LocalDate.parse(dateString);

                  // Replace the 7th element with LocalDate
                  sublist.set(6, purchasedDate);
               }

               // Add the sublist to the ItemDetailsList
               ItemDetailsList.add(sublist);
            }

            // Since it is already sorted before reading the file assign the ItemDetails list as the sorted list
            SortedList = ItemDetailsList;
         }
      } catch (EOFException e) {
         // End of file reached, continue with the program
      } catch (Exception e) {
         e.printStackTrace();
      }

      // https://howtodoinjava.com/java/serialization/how-deserialization-process-happen-in-java/
   }



}
