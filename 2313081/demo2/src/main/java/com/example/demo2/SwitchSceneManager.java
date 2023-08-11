package com.example.demo2;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;


import static com.example.demo2.ItemManager.SortedList;

// This class is the controller of the InventoryMainController.fxml

public class SwitchSceneManager extends InventoryMainController{

    private double xPosition = 0; // detects the x coordinates of the Main Pane

    private double yPosition = 0; // detects the y coordinates of the Main Pane

    final FileChooser fileChooser = new FileChooser();

    public static String imagePath;

    protected static boolean randomGeneratedStatus = false;


    // Method that handles exiting the page
    @Override
    protected void ExitTheProgram(MouseEvent event) {
        // Alert makes sure, whether user wants to exit the program
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.setTitle("One Net Cafe");
        exitAlert.setHeaderText("Do you really want to exit the program ?");

        // Show the alert and wait for user's response
        Optional<ButtonType> resultExit = exitAlert.showAndWait();
        if(resultExit.get() ==  ButtonType.OK){
            // If the user conforms Ok,exit the program
            Platform.exit();
        }

    }

    // Method that handles go to generate dealers tab
    @Override
    void GoTOGenerateDealers(MouseEvent event) {
        DisableAllPanes();  // Disable all Panes
        GenerateRandomDealerSidePane.setVisible(true); // set visible GenerateRandomDealerSidePane
        RandomDealerSelectionPane.setVisible(true);  // set visible RandomDealerSelectionPane
    }

    // Method that handles go to add item details tab
    @Override
    void GoToAddItems(MouseEvent event) {
        DisableAllPanes(); // Disable all Panes
        ItemInputSidePane.setVisible(true); // set visible ItemInputSidePane
        AddingWhitePane.setVisible(true);  // set visible AddingWhitPane
        InnerPane.setVisible(true);  // set visible Inner Pane
    }

    // Method that handles go to delete item details tab
    @Override
    void GoToDeleteItems(MouseEvent event) {
        DisableAllPanes(); // Disable all Panes
        ItemDeleteSidePane1.setVisible(true); // set visible ItemDeleteSidePane1
        AddingWhitePane.setVisible(true);  // set visible AddingWhitePane
        ItemDeletePane.setVisible(true);  // set visible ItemDeletePane
        SetTableView(); // populate the tables with saved item details
    }


    // Method that handles go to home page tab
    @Override
    void GoToHomePage(ActionEvent event) {
        DisableAllPanes(); // Disable all Panes
        SidePane.setVisible(true); // set visible SidePane
        mainPane.setVisible(true);  // set visible mainPane
    }


    // Method that handles items saving operations
    @Override
    void GoToSaveItems(MouseEvent event) {
        try{
            ItemManager.SaveItemDetails();  // call save details method in ItemManager class
            DisplayItemsSavedMassage(); // show the alert of saving items
        } catch (IOException e) {
            DisplayFileWritingError(); // If there is an issue writing the file, display the error
        }
    }

    // Method that handles go to selected dealer tab
    @Override
    void GoToSelectedDealer(MouseEvent event) {
        DisableAllPanes(); // Disable all Panes
        SelectedIndividualDealerSidePane.setVisible(true); // set visible the individualDealersSidePane
        SelectedIndividualDealerPane.setVisible(true); // set visible the SelectedIndividualDealerPane
        setNotRandomGeneratedMassage(); // if the dealers are not generated show the alert
    }

    // Method that handles go to update Items tab
    @Override
    void GoToUpdateItems(MouseEvent event) {
        DisableAllPanes(); // Disable all Panes
        AddingWhitePane.setVisible(true); // set visible the AddingWhitePane
        ItemUpdateSidePane.setVisible(true); // set visible the ItemUpdateSidePane
        ItemUpdatePane.setVisible(true); // set visible the ItemUpdatePane
        UpdateButton.setDisable(true); // set disable the UpdateButton
        SetTableView(); // populate the tables with saved item details
        disableUpdateFields(); // disable all the update text fields
    }

    // Method that handles go to view dealer details tab
    @Override
    void GoToViewDealerDetails(MouseEvent event) {
        DisableAllPanes(); // Disable all Panes
        SelectedRandomDealersPane.setVisible(true); // set visible the SelectedRandomDealersPane
        SeeSelectedDealersSidePane.setVisible(true); // set visible the SeeSelectedDealersSidePane
        setNotRandomGeneratedMassage(); // if the dealers are not generated yet, show the alert massage
    }

    // Method that handles go to view items tab
    @Override
    void GoToViewItems(MouseEvent event) {
        DisableAllPanes(); // Disable all Panes
        ItemSidePane.setVisible(true); // set visible the ItemSidePane
        ItemViewPane.setVisible(true); // set visible the ItemViewPane
        SetTableView(); // populate the tables
    }

    // Method that handles dragging the main pane
    @Override
    public void StackPaneDragDetected(MouseEvent event) {
        // Take the current stage
        Stage stage = (Stage) MainStackPane.getScene().getWindow();

        // get the x coordinate of the given pane
        yPosition = event.getScreenY();

        // get the y coordinate of the given pane
        xPosition = event.getScreenX();

        stage.setY(yPosition); // set the y position to dragged coordinate
        stage.setX(xPosition);// set the x position to dragged coordinate
    }

    // Method that takes x and y coordinates when clicked on the main pane
    @Override
    public void StackPanePressed(MouseEvent event) {
        xPosition = event.getSceneX(); // get the x position when pressed
        yPosition = event.getSceneY(); //
    }

    // Method that handles minimising the pane
    @Override
    void MinimiseThePane(MouseEvent event) {
        // Take the current stage
        Stage stage = (Stage) MinimiseButton.getScene().getWindow();
        // Minimise the Pane
        stage.setIconified(true);
    }

    // Method that is used to disable all the panes
    public void DisableAllPanes(){
        // This pane array stores all the panes that should be disabled
        Pane[] mainPanes = {
                SidePane,ItemSidePane,ItemDeleteSidePane1,
                ItemUpdateSidePane,GenerateRandomDealerSidePane,
                SeeSelectedDealersSidePane, SelectedIndividualDealerSidePane,
                ItemInputSidePane, mainPane, ItemViewPane,
                AddingWhitePane, InnerPane, ItemUpdatePane,
                ItemDeletePane, RandomDealerSelectionPane,
                SelectedRandomDealersPane, SelectedIndividualDealerPane
        };

        // Take each pane one by one and disable it
        for (Pane pane : mainPanes) {
            pane.setVisible(false);
        }

    }

    // This method is used to clear all the text fields when updating and deleting
    public void ClearAllFields(MouseEvent event) {
        ClearAllFields();
    }

    // This method is used to clear all the fields
    public void ClearAllFields() {
        ItemCodeInput.setText("");
        ItemNameInput.setText("");
        ItemBrandInput.setText("");
        ItemPriceInput.setText("");
        ItemQuantityInput.setText("");
        ItemCategoryInput.setText("");
        DateInput.setValue(null);
        ItemImageInput.setImage(null);
        imagePath = null;
        massageInputItemCode.setText("");
        massageInputItemBrand.setText("");
        massageInputItemName.setText("");
        massageInputItemDate.setText("");
        massageInputItemQuantity.setText("");
        massageInputItemCategory.setText("");
        massageInputItemPrice.setText("");
        ItemManager.ItemCodeStatus = false;
        ItemManager.ItemCategoryStatus = false;
        ItemManager.ItemNameStatus = false;
        ItemManager.ItemPriceStatus = false;
        ItemManager.ItemQuantityStatus = false;
        ItemManager.ItemDateStatus = false;
        ItemManager.ItemBrandStatus = false;
    }


    // This method handles adding item details to the system
    public void AddDetails(MouseEvent event){

        // Create an ItemManager object with the inputs provided by user
        ItemManager addingData = new ItemManager(ItemCodeInput.getText(), ItemNameInput.getText(),
                ItemBrandInput.getText(), ItemPriceInput.getText(),ItemQuantityInput.getText(),
                ItemCategoryInput.getText(), DateInput.getValue(), imagePath);

        // Populate the view item details table
        SetViewTable();

        // Populate the update item details table
        SetUpdateTable();

        // Populate the delete item details table
        SetDeleteTable();

        // Call enable error label to display errors if inputs are not valid
        EnableErrorLabel();

        // Check whether the input details are valid and not duplicated

        if(addingData.InputCorrectStatus && !addingData.ItemDuplicationStatus){
            ClearAllFields(); // Clear all the text fields after successfully adding the item

            // Show the alert of items successfully saved
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item Added Massage");
            alert.setHeaderText("Item has successfully added !!!");
            alert.show();

            SavedDetailsCaller(); // Save details in the text file

        }
    }

    // This method handles writing the item details in the text file,
    public void SavedDetailsCaller(){
        try{
            ItemManager.SaveItemDetails(); // Call SaveItemDetails method to save details
        }catch(Exception error){
            DisplayFileWritingError(); // If it is unable to save details, display an error alert
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set cell value factories for the columns of the ItemView Table
        ItemViewBrand.setCellValueFactory(new PropertyValueFactory<>("ItemBrand"));
        ItemViewCategory.setCellValueFactory(new PropertyValueFactory<>("ItemCategory"));
        ItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        ItemViewImage.setCellValueFactory(new PropertyValueFactory<>("absoluteImage"));
        ItemViewName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        ItemViewPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        ItemViewPurchasedDate.setCellValueFactory(new PropertyValueFactory<>("PurchasedDate"));
        ItemViewQuantity.setCellValueFactory(new PropertyValueFactory<>("ItemQuantity"));

        // Set cell value factories for the columns of the UpdateItem Table
        UpdateItemBrand.setCellValueFactory(new PropertyValueFactory<>("ItemBrand"));
        UpdateItemCategory.setCellValueFactory(new PropertyValueFactory<>("ItemCategory"));
        UpdateItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        UpdateItemImage.setCellValueFactory(new PropertyValueFactory<>("absoluteImage"));
        UpdateItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        UpdateItemPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        UpdateItemPurchasedDate.setCellValueFactory(new PropertyValueFactory<>("purchasedDate"));
        UpdateItemQuantity.setCellValueFactory(new PropertyValueFactory<>("ItemQuantity"));

        // Set cell value factories for the columns of the DeleteViewItem table
        DeleteViewItemBrand.setCellValueFactory(new PropertyValueFactory<>("ItemBrand"));
        DeleteViewItemCategory.setCellValueFactory(new PropertyValueFactory<>("ItemCategory"));
        DeleteViewItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        DeleteViewItemImage.setCellValueFactory(new PropertyValueFactory<>("absoluteImage"));
        DeleteViewItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        DeleteViewItemPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        DeleteViewItemPurchasedDate.setCellValueFactory(new PropertyValueFactory<>("purchasedDate"));
        DeleteViewItemQuantity.setCellValueFactory(new PropertyValueFactory<>("ItemQuantity"));

        // Set cell value factories for the columns of the GeneratedViewDealer Table
        GeneratedViewDealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        GeneratedViewContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        GeneratedViewDealerLocation.setCellValueFactory(new PropertyValueFactory<>("dealerLocation"));

        // Set cell value factories for the columns of the SortedSelectedDealer Table
        SortedSelectedDealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        SortedSelectedDealerContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        SortedSelectedDealerLocation.setCellValueFactory(new PropertyValueFactory<>("dealerLocation"));

        // Set cell value factories for the columns of the Dealer1Item table
        Dealer1ItemBrand.setCellValueFactory(new PropertyValueFactory<>("dealerItemBrand"));
        Dealer1ItemName.setCellValueFactory(new PropertyValueFactory<>("dealerItemName"));
        Dealer1ItemPrice.setCellValueFactory(new PropertyValueFactory<>("dealerItemPrice"));
        Dealer1ItemQuantity.setCellValueFactory(new PropertyValueFactory<>("dealerItemQuantity"));

        // Set cell value factories for the columns of the Dealer2Item table
        Dealer2ItemBrand.setCellValueFactory(new PropertyValueFactory<>("dealerItemBrand"));
        Dealer2ItemName.setCellValueFactory(new PropertyValueFactory<>("dealerItemName"));
        Dealer2ItemPrice.setCellValueFactory(new PropertyValueFactory<>("dealerItemPrice"));
        Dealer2ItemQuantity.setCellValueFactory(new PropertyValueFactory<>("dealerItemQuantity"));

        // Set cell value factories for the columns of the Dealer3Item table
        Dealer3ItemBrand.setCellValueFactory(new PropertyValueFactory<>("dealerItemBrand"));
        Dealer3ItemName.setCellValueFactory(new PropertyValueFactory<>("dealerItemName"));
        Dealer3ItemPrice.setCellValueFactory(new PropertyValueFactory<>("dealerItemPrice"));
        Dealer3ItemQuantity.setCellValueFactory(new PropertyValueFactory<>("dealerItemQuantity"));

        // Set cell value factories for the columns of the Dealer4Item table
        Dealer4ItemName.setCellValueFactory(new PropertyValueFactory<>("dealerItemName"));
        Dealer4ItemPrice.setCellValueFactory(new PropertyValueFactory<>("dealerItemPrice"));
        Dealer4ItemQuantity.setCellValueFactory(new PropertyValueFactory<>("dealerItemQuantity"));
        Dealer4ItemBrand.setCellValueFactory(new PropertyValueFactory<>("dealerItemBrand"));

        // Set cell value factories for the columns of the SelectedDealerView table
        SelectedDealerViewContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        SelectedDealerViewDealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        SelectedDealerViewLocation.setCellValueFactory(new PropertyValueFactory<>("dealerLocation"));

        // Set cell value factories for the columns of the SingleDealerItem table
        SingleDealerItemBrand.setCellValueFactory(new PropertyValueFactory<>("dealerItemBrand"));
        SingleDealerItemName.setCellValueFactory(new PropertyValueFactory<>("dealerItemName"));
        SingleDealerItemPrice.setCellValueFactory(new PropertyValueFactory<>("dealerItemPrice"));
        SingleDealerItemQuantity.setCellValueFactory(new PropertyValueFactory<>("dealerItemQuantity"));

    }

    // This method enables to select an image from PC
    @Override
    public void OpenImageHandler(ActionEvent event) {
        fileChooser.setTitle("File Chooser"); // Set the title of the file chooser dialog

        // Set the initial directory of the fileChooser to the user's  home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear(); // Clear all the existing extensions
        // Add a new filter for image files (ex: "*.png", "*.jpg", "*.gif" )
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.png", "*.jpg", "*.gif"));

        // Show the file chooser dialog box and wait for the user to select a file
        File file = fileChooser.showOpenDialog(null);

        // Check whether if a file is selected by the user
        if (file != null) {
            // get the button that handles the event
            Button clickedButton = (Button) event.getSource();

            // Take the FxId of the button
            String  FxId = clickedButton.getId();
            imagePath = file.getPath(); // get the selected Image path

            // Check whether image imported is from the update pane or from the adding pane
            if(FxId.equals("ImageInputButton")){
                // Set the Input Image view as the selected image
                ItemImageInput.setImage(new Image(String.valueOf(file.toURI())));
            }else if(FxId.equals("ImageUpdateImportButton")){
                // Set the Update Image view as the selected image
                UpdateImageInput.setImage(new Image(String.valueOf(file.toURI())));
            }

        } else {
            // Show the import image error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Import Image Error !!!");
            alert.setHeaderText("Image is not imported !!!");
            alert.show(); // Show the alert
        }
    }

    // This method enables to select the updating rows
    @Override
    public void UpdateRowSelection(MouseEvent event) {
       UpdateRowSelection(); // Call the overloading method
    }

    public void UpdateRowSelection(){
        try{
            // Enable the input fields for updating
            enableUpdateFields();

            // Set the input fields with the details of the selected item
            ItemDetails selectedTableValue =  UpdateViewTable.getSelectionModel().getSelectedItem();
            UpdateItemCodeInput.setText(String.valueOf(selectedTableValue.getItemCode()));
            checkUpdateValidity(UpdateItemCodeInput);
            UpdateItemNameInput.setText(String.valueOf(selectedTableValue.getItemName()));
            checkUpdateValidity(UpdateItemNameInput);
            UpdateItemBrandInput.setText(String.valueOf(selectedTableValue.getItemBrand()));
            checkUpdateValidity(UpdateItemBrandInput);
            UpdateItemPriceInput.setText(String.valueOf(selectedTableValue.getItemPrice()));
            checkUpdateValidity(UpdateItemPriceInput);
            UpdateItemQuantityInput.setText(String.valueOf(selectedTableValue.getItemQuantity()));
            checkUpdateValidity(UpdateItemQuantityInput);
            UpdateItemCategoryInput.setText(String.valueOf(selectedTableValue.getItemCategory()));
            checkUpdateValidity(UpdateItemCategoryInput);
            UpdatePurchasedDateInput.setValue(selectedTableValue.getPurchasedDate());
            UpdateImageInput.setImage(selectedTableValue.getAbsoluteImage().getImage());

            // Save the previous item code since, it can be updated during the Update value editing process
            ItemManager.PreviousItemCode = selectedTableValue.getItemCode();

            // Enable the UpdateButton for updating the item
            UpdateButton.setDisable(false);

            // Find the index of the item in the ItemManager for updating
            ItemManager.UpdateIndexFinder(UpdateItemCodeInput.getText());
        }catch(NullPointerException E){
            System.out.println(); // If update field is null, ignore it
        }

    }


    // This method handles updating items
    @Override
    void UpdateItems(ActionEvent event) {
        // Get the updated details from the input fields
        String updatedCode = UpdateItemCodeInput.getText();
        String UpdatedName = UpdateItemNameInput.getText();
        String UpdatedBrand = UpdateItemBrandInput.getText();
        String UpdatedPrice = UpdateItemPriceInput.getText();
        String UpdatedQuantity = UpdateItemQuantityInput.getText();
        String UpdatedCategory = UpdateItemCategoryInput.getText();
        LocalDate UpdatedDate = UpdatePurchasedDateInput.getValue();

        // Create an ItemManager Object to update the item Code
       ItemManager itemManager = new ItemManager();

       // Try to update the item and handle the result
       if(itemManager.UpdateItemSolver(updatedCode, UpdatedName, UpdatedBrand,
               UpdatedPrice, UpdatedQuantity, UpdatedCategory, UpdatedDate, imagePath)){
           // Clear the updated fields after successful
           clearUpdatedFields();

           // Update the table views
           SetViewTable();
           SetUpdateTable();
           SetDeleteTable();

           // Disable the UpdateButton and input fields after update
           UpdateButton.setDisable(true);

           // Call the SavedDetailsCaller method
           disableUpdateFields();
           SavedDetailsCaller();

       }else{
           // Show an error label if the update was not successful
           UpdateErrorLabel();
       }

    }

    // This method handles deleting item details
    @Override
    void DeleteDetails(MouseEvent event) {
        try{
            // Get the selected item from the ItemDeleteTable
            ItemDetails selectedDeleteValue = ItemDeleteTable.getSelectionModel().getSelectedItem();

            // Create an ItemManager object to delete the item
            ItemManager deleteItem = new ItemManager();

            // Delete the item and update the table views
            deleteItem.DeleteItemSolver(selectedDeleteValue.getItemCode());
            SetViewTable();
            SetUpdateTable();
            SetDeleteTable();
        }catch (NullPointerException error){
            // Show an error alert if no item is selected for deletion
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Table selection");
            alert.setHeaderText("Select an item from table to delete !!!");
            alert.show();
            return;
        }

        // Show a success alert after successful item deletion
        Alert deletedAlert = new Alert(Alert.AlertType.INFORMATION);
        deletedAlert.setHeaderText("Item Successfully Deleted !!!");
        deletedAlert.setTitle("Delete Item Massage");
        deletedAlert.show();

        // Save the updated item details
        try{
            ItemManager.SaveItemDetails();
        } catch (IOException e) {
            DisplayFileWritingError();
        }
    }

    // This method handles generating random dealers
    @Override
    void GenerateDealers(MouseEvent event) {
        // Create a DealerManager object to generate random dealers
      DealerManager dealerManager = new DealerManager();
        try {
            dealerManager.generateRandomDealers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Update the table view with the generated dealers
        SetGeneratedPersonalTable();
    }


    // This method handles searching items to update
    @Override
    void SearchUpdateItems(MouseEvent event) {
        // Get the item code to search for from the UpdateSearchBar
        String itemCode = UpdateSearchBar.getText();
        System.out.println(itemCode);

        // Search for the item code and handle non-existent item code
        ItemDetails foundItem = null;
        for (ItemDetails item : UpdateViewTable.getItems()) {
            if (item.getItemCode().equals(itemCode)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem != null) {
            // Select the row with the found item in the UpdateViewTable
            UpdateViewTable.getSelectionModel().select(foundItem);
            UpdateViewTable.scrollTo(foundItem);

            // Update the input fields with the selected item's details for updating
            UpdateRowSelection();
        } else {
            // Show alert for non-existent item code
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Item Not Found");
            alert.setHeaderText(null);
            alert.setContentText("The item with code " + itemCode + " does not exist.");
            alert.showAndWait();
        }
    }

    // This method handles selecting dealer details
    @Override
    void SelectDealerDetails(MouseEvent event) {
      // Clear the table view
      SingleDealerViewTable.getItems().clear();

      // Get the dealer name to search for from the SearchBarPaneDealers
      String searchingDealer = SearchBarPaneDealers.getText();

      // Search for the dealer and handle non-existent dealer
      for(int index = 0; index < DealerManager.sortedPersonalArray.length; index++){
          if (Objects.equals(searchingDealer, DealerManager.sortedPersonalArray[index][0])) {

              // Get the list of item details for the selected dealer
              ArrayList<String[]> itemDetailsList = DealerManager.sortedDealersItemDetails.get(index);

              // Add the item details to the SingleDealerViewTable
              ObservableList<DealerDetails> dealerDetails = SingleDealerViewTable.getItems();
              for (int count = 1; count < itemDetailsList.size(); count++) {
                  String[] itemDetails = itemDetailsList.get(count);
                  dealerDetails.add(new DealerDetails(itemDetails[0], itemDetails[1], itemDetails[2], itemDetails[3]));
              }

              // Set the items in SingleDealerViewTable
              SingleDealerViewTable.setItems(dealerDetails);
              return;
          }
      }
      // Show an alert if the dealer is not found
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Dealer found Status");
      alert.setHeaderText(null);
      alert.setContentText("Dealer not found !!!");
      alert.show(); // Show the alert
    }

    // This method handles searching items by item code
    @Override
    void ViewItemByItemCode(MouseEvent event) {
        // Get the Item code to search for from the ViewItemSearchBar
        String ItemCode = ViewItemSearchBar.getText();
        ItemDetails foundItem = null;

        // Search for the item code in the ItemViewTable and find the item if it exists
        for (ItemDetails item : ItemViewTable.getItems()) {
            if (item.getItemCode().equals(ItemCode)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem != null) {
            // Select the row with the found item in the ItemViewTable and scroll to it
            ItemViewTable.getSelectionModel().select(foundItem);
            ItemViewTable.scrollTo(foundItem);
        } else {
            // Show alert for non-existing item code
           showItemFoundAlert(ItemCode);
        }

    }

    // This method handles searching items to delete
    @Override
    void DeleteItemByItemCode(MouseEvent event) {
        // Get the item code to search for from the DeleteSearchBar
        String ItemCode = DeleteSearchBar.getText();
        ItemDetails foundItem = null;

        // Search for the item code in the ItemDeleteTable and find the item if it exists
        for (ItemDetails item : ItemDeleteTable.getItems()) {
            if (item.getItemCode().equals(ItemCode)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem != null) {
            // Select the row with the found item in the ItemDeleteTable and scroll to it
            ItemDeleteTable.getSelectionModel().select(foundItem);
            ItemDeleteTable.scrollTo(foundItem);
        } else {
            // Show alert for non-existing item code
            showItemFoundAlert(ItemCode);
        }

    }

    // This method enables detecting when key release (typing) in Adding Pane
    @Override
    void TypeTextChange(KeyEvent event) {
        // Get the source of the event(Text field)
        TextInputControl textField = (TextInputControl) event.getSource();

        // Call the check Input validity method to validate the text fields
        checkInputValidity(textField);
    }

    // This method enables showing textUpdate errors when key release in update pane
    @Override
    void textUpdateErrorDisplay(KeyEvent event) {
        // Get the source of the event(Text field)
        TextInputControl textField = (TextInputControl) event.getSource();

        // Call the check Input validity method to validate the text fields
        checkUpdateValidity(textField);
    }

    //This method handle logout from the system
    @Override
    void LogOut(MouseEvent event) throws IOException {

        // Show a confirmation alert before logging out
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.setTitle("One Net Cafe");
        exitAlert.setHeaderText("Do you really want to logout ?");

        // Show and wait the alert until user selects the choice
        Optional<ButtonType> resultExit = exitAlert.showAndWait();
        if(resultExit.get() ==  ButtonType.OK){

            // Load the LoginPage.fxml using FXML Loader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginPage.fxml"));
            Parent root = loader.load();

            // Get the current stage and setting the scene as LoginPage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Setting the dimension size as 428 as width and 691 as width
            Scene scene = new Scene(root, 421, 691);
            stage.setScene(scene);
            stage.centerOnScreen();

            // Show the stage
            stage.show();

            // https://jenkov.com/tutorials/javafx/stage.html
        }
    }

    // If the item not found when searching this method shows the alert
    public void showItemFoundAlert(String ItemCode){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Item Not Found");
        alert.setHeaderText(null);

        // Show the alert with user given item code
        alert.setContentText("The item with code " + ItemCode + " does not exist.");
        alert.showAndWait(); // show the alert
    }

    // This method sets the ItemViewTable with data
    public void SetViewTable(){
        // Clear the item view table if it is not null
        if(ItemViewTable != null){
            ItemViewTable.getItems().clear();
        }

        double TotalAmount = 0.0; // Initialize the total amount as 0.0
        int ItemCount = 0; // Initialize the total item count as 0

        // Check whether the sortedList is null and return if it is null
        if(SortedList == null){
            return;
        }

        //Add the item details to the view table using sorted list
        for(ArrayList<Object> item : SortedList) {

            if(SortedList == null){
                return;
            }

            // Create an Item details object with the item details
            ItemDetails itemDetails = new ItemDetails(String.valueOf(item.get(0)), String.valueOf(item.get(1)),
                    String.valueOf(item.get(2)), Double.parseDouble(String.valueOf(item.get(3))),
                    Integer.parseInt((String) item.get(4)),
                    String.valueOf(item.get(5)), (LocalDate) item.get(6), String.valueOf(item.get(7)));

            // Add the Item details to the item view table
            // https://openjfx.io/javadoc/17/javafx.base/javafx/collections/class-use/ObservableList.htm
            ObservableList<ItemDetails> viewDetails = ItemViewTable.getItems();
            viewDetails.add(itemDetails);
            ItemViewTable.setItems(viewDetails);

            // Calculate the total amount of the items
            TotalAmount = TotalAmount + (Double.parseDouble(String.valueOf(item.get(3))) *
                    Integer.parseInt(String.valueOf(item.get(4))));

            // Save the item count that is stored in the system
            ItemCount++;
        }

        // Use decimal format to display item price with two decimal points
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        // Display the saved item count
        ViewItemTotalCount.setText(String.valueOf(ItemCount));

        // Display the total price of the items
        ViewItemTotalPrice.setText("Rs." + decimalFormat.format(TotalAmount));

    }

    // Set the Item Update table by populating values
    public void SetUpdateTable(){
        // Check whether the sortedList is null and return the method, if it is null
        if(SortedList == null){
            return;
        }
        // Clear the UpdateViewTable
        UpdateViewTable.getItems().clear();

        // Add Item details to the UpdateView Table using Sorted List
        for(ArrayList<Object> item : SortedList) {

            // Create an Item details object with the item details
            ItemDetails itemDetails = new ItemDetails(String.valueOf(item.get(0)), String.valueOf(item.get(1)),
                    String.valueOf(item.get(2)), Double.parseDouble(String.valueOf(item.get(3))),
                    Integer.parseInt((String) item.get(4)),
                    String.valueOf(item.get(5)), (LocalDate) item.get(6), String.valueOf(item.get(7)));

            // Add the item details to the UpdateViewTable
            ObservableList<ItemDetails> viewUpdateDetails = UpdateViewTable.getItems();
            viewUpdateDetails.add(itemDetails);
            UpdateViewTable.setItems(viewUpdateDetails);
        }
    }

    // Set the Item Delete table by populating values
    public void SetDeleteTable(){
        // Check whether the sortedList is null and return the method, if it is null
        if(SortedList == null){
            return;
        }

        // Clear the ItemDeleteTable
        ItemDeleteTable.getItems().clear();

        // Add Item details to the ItemDelete Table using Sorted List
        for(ArrayList<Object> item : SortedList) {

            // Create an Item details object with the item details
            ItemDetails itemDetails = new ItemDetails(String.valueOf(item.get(0)), String.valueOf(item.get(1)),
                    String.valueOf(item.get(2)), Double.parseDouble(String.valueOf(item.get(3))),
                    Integer.parseInt((String) item.get(4)),
                    String.valueOf(item.get(5)), (LocalDate) item.get(6), String.valueOf(item.get(7)));

            // Add the item details to the ItemDeleteTable
            ObservableList<ItemDetails> viewDeleteDetails = ItemDeleteTable.getItems();
            viewDeleteDetails.add(itemDetails);
            ItemDeleteTable.setItems(viewDeleteDetails);
        }

    }


    // This method will populate all the selected dealers related tables
    public void SetGeneratedPersonalTable() {
        // Check whether the sorted array is null, if it is null return the method
        if (DealerManager.sortedPersonalArray == null) {
            return;
        }

        // Clear the existing data from all the selected dealers tables
        GeneratedViewDealerTable.getItems().clear();
        SortedSelectedDealersTable.getItems().clear();
        SelectedDealerViewTable.getItems().clear();
        SelectedDealer1Table.getItems().clear();
        SelectedDealer2Table.getItems().clear();
        SelectedDealer3Table.getItems().clear();
        SelectedDealer4Table.getItems().clear();

        for (int dealerIndex = 0; dealerIndex < 4; dealerIndex++) {
            String[] dealerArray = DealerManager.sortedPersonalArray[dealerIndex];

            // Set the data for the GeneratedViewDealerTable by using dealerArray
            ObservableList<DealerDetails> viewGeneratedPersonalDetails = GeneratedViewDealerTable.getItems();
            viewGeneratedPersonalDetails.add(new DealerDetails(dealerArray[0], dealerArray[1], dealerArray[2]));
            GeneratedViewDealerTable.setItems(viewGeneratedPersonalDetails);

            // Set the data for the SortedSelectedDealersTable by using dealerArray
            ObservableList<DealerDetails> SortedGeneratedPersonalDetails = SortedSelectedDealersTable.getItems();
            SortedGeneratedPersonalDetails.add(new DealerDetails(dealerArray[0], dealerArray[1], dealerArray[2]));
            SortedSelectedDealersTable.setItems(SortedGeneratedPersonalDetails);

            // Set the data for the SelectedDealerViewTable by using dealerArray
            ObservableList<DealerDetails> sortedGeneratedPersonaDetails = SelectedDealerViewTable.getItems();
            sortedGeneratedPersonaDetails.add(new DealerDetails(dealerArray[0], dealerArray[1], dealerArray[2]));
            SelectedDealerViewTable.setItems(sortedGeneratedPersonaDetails);

            TableView<DealerDetails> selectedDealerTable;
            // Populate the selected dealers table by based on the dealer index
            switch (dealerIndex) {
                // Takes the dealer in the 0th index and display the name
                case 0 -> {
                    selectedDealerTable = SelectedDealer1Table;
                    Dealer1Name.setText("Dealer " + dealerArray[0] + "'s Item details given below");
                }
                // Takes the dealer in the 1st index and display the name
                case 1 -> {
                    selectedDealerTable = SelectedDealer2Table;
                    Dealer2Name.setText("Dealer " + dealerArray[0] + "'s Item details given below");
                }
                // Takes the dealer in the 2nd index and display the name
                case 2 -> {
                    selectedDealerTable = SelectedDealer3Table;
                    Dealer3Name.setText("Dealer " + dealerArray[0] + "'s Item details given below");
                }
                // Takes the dealer in the 3rd index and display the name
                case 3 -> {
                    selectedDealerTable = SelectedDealer4Table;
                    Dealer4Name.setText("Dealer " + dealerArray[0] + "'s Item details given below");
                }
                default -> {
                    // If the dealerIndex is not within 0 to 3, continue to the next iteration.
                    continue;
                }
            }

            // Get the list of item details of the current dealer from SortedDealers item details
            ArrayList<String[]> itemDetailsList = DealerManager.sortedDealersItemDetails.get(dealerIndex);
            ObservableList<DealerDetails> sortedGeneratedItemDetails = selectedDealerTable.getItems();

            // Using the item details list, populate the data to its corresponding table view
            for (int index = 1; index < itemDetailsList.size(); index++) {
                String[] itemDetails = itemDetailsList.get(index);
                sortedGeneratedItemDetails.add(new DealerDetails(itemDetails[0], itemDetails[1], itemDetails[2], itemDetails[3]));
            }
            selectedDealerTable.setItems(sortedGeneratedItemDetails); // populate tables
        }
    }

    // Enable the input error labels  based on the Item detail status
    public void EnableErrorLabel() {
        massageInputItemCode.setText(ItemManager.ItemCodeStatus ? "Please input a correct Item Code !!!" : "");
        massageInputItemName.setText(ItemManager.ItemNameStatus ? "Please input a correct Item Name !!!" : "");
        massageInputItemBrand.setText(ItemManager.ItemBrandStatus ? "Please input a correct Item Brand !!!" : "");
        massageInputItemCategory.setText(ItemManager.ItemCategoryStatus ? "Please input a correct Item Category !!!" : "");
        massageInputItemPrice.setText(ItemManager.ItemPriceStatus ? "Please input a correct numbered amount !!!" : "");
        massageInputItemQuantity.setText(ItemManager.ItemQuantityStatus ? "Please input a correct numbered quantity" : "");
        massageInputItemDate.setText(ItemManager.ItemDateStatus ? "Please input a proper date !!!" : "");
    }

    // Enable the update error labels  based on the Item detail status
    public void UpdateErrorLabel() {
        massageItemUpdateCode.setText(ItemManager.ItemCodeStatus ? "Please input a correct Item Code !!!" : "");
        massageItemUpdateName.setText(ItemManager.ItemNameStatus ? "Please input a correct Item Name !!!" : "");
        massageItemUpdateBrand.setText(ItemManager.ItemBrandStatus ? "Please input a correct Item Brand !!!" : "");
        massageItemUpdateCategory.setText(ItemManager.ItemCategoryStatus ? "Please input a correct Item Category !!!" : "");
        massageUpdateItemPrice.setText(ItemManager.ItemPriceStatus ? "Please input a correct numbered amount !!!" : "");
        massageItemUpdateQuantity.setText(ItemManager.ItemQuantityStatus ? "Please input a correct numbered quantity" : "");
        massageItemUpdateDate.setText(ItemManager.ItemDateStatus ? "Please input a proper date !!!" : "");
    }

    // Clears all the labels and text-fields  in clearUpdatedFields method
    public void clearUpdatedFields(){
        UpdateItemCodeInput.setText("");
        UpdateItemNameInput.setText("");
        UpdateItemBrandInput.setText("");
        UpdateItemPriceInput.setText("");
        UpdateItemQuantityInput.setText("");
        UpdateItemCategoryInput.setText("");
        UpdatePurchasedDateInput.setValue(null);
        massageItemUpdateCode.setText("");
        massageItemUpdateQuantity.setText("");
        massageItemUpdateName.setText("");
        massageItemUpdateDate.setText("");
        massageItemUpdateCategory.setText("");
        massageUpdateItemPrice.setText("");
        massageItemUpdateBrand.setText("");
    }


    // This method shows alert when there is an error saving the data in a text file
    public static void DisplayFileWritingError(){
        Alert displayFileWritingError = new Alert(Alert.AlertType.CONFIRMATION);
        displayFileWritingError.setTitle("File Writing Error");
        displayFileWritingError.setHeaderText(null);
        displayFileWritingError.setContentText("There is an issue with writing the file");
        displayFileWritingError.show(); // Show the Alert
    }

    // This method displays the information alert of saved details
    public static void DisplayItemsSavedMassage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Items Saved Massage");
        alert.setHeaderText("Items Saved Successfully !!!");
        alert.show();
    }

    // This method sets all Item details related tables
    public void SetTableView(){
        SetViewTable();
        SetUpdateTable();
        SetDeleteTable();
    }

    // This method shows the alert if the dealers are not randomly generated when visiting dealer related panes
    public void setNotRandomGeneratedMassage(){
        // Check whether dealers are generated
        if(!randomGeneratedStatus){
            // If dealers are not generated, show the below alert
            Alert setNotRandomGeneratedMassage = new Alert(Alert.AlertType.INFORMATION);
            setNotRandomGeneratedMassage.setTitle("Generate Dealer's Massage");
            setNotRandomGeneratedMassage.setHeaderText(null);
            setNotRandomGeneratedMassage.setContentText("Go to generated Dealer's tab \nand generate dealers first " +
                    "to see their details");
            setNotRandomGeneratedMassage.show(); // Show the alert
        }
    }

    // This method is used to validate the adding item details when typing
    public void checkInputValidity(TextInputControl textField) {
        // Create an object of the itemManager
        ItemManager itemManager = new ItemManager();

        //Get the id of the text-field and remove its spaces
        String textFieldId = textField.getId().trim();

        // Use a switch statement to validate the item related details
        switch (textFieldId) {
            case "ItemCodeInput" -> itemManager.validateItemCode(textField.getText());
            case "ItemNameInput" -> itemManager.validateItemName(textField.getText());
            case "ItemBrandInput" -> itemManager.validateItemBrand(textField.getText());
            case "ItemPriceInput" -> itemManager.validateItemPrice(textField.getText());
            case "ItemQuantityInput" -> itemManager.validateItemQuantity(textField.getText());
            case "ItemCategoryInput" -> itemManager.validateItemCategory(textField.getText());
        }

        EnableErrorLabel(); // Call the enable error label method to display the errors
    }

    // This method is used to validate the adding item details when typing
    public void checkUpdateValidity(TextInputControl textField){
        // Create an object of the itemManager
        ItemManager updateManagerUpdate = new ItemManager();

        //Get the id of the text-field and remove its spaces
        String textFieldId = textField.getId().trim();

        //Get the id of the text-field and remove its spaces
        switch (textFieldId) {
            case "UpdateItemCodeInput" -> updateManagerUpdate.validateItemCode(textField.getText());
            case "UpdateItemNameInput" -> updateManagerUpdate.validateItemName(textField.getText());
            case "UpdateItemBrandInput" -> updateManagerUpdate.validateItemBrand(textField.getText());
            case "UpdateItemPriceInput" -> updateManagerUpdate.validateItemPrice(textField.getText());
            case "UpdateItemQuantityInput" -> updateManagerUpdate.validateItemQuantity(textField.getText());
            case "UpdateItemCategoryInput" -> updateManagerUpdate.validateItemCategory(textField.getText());
        }

        UpdateErrorLabel(); // Call the enable error label method to display the errors
    }

    // Disable all update Input text fields
    public void disableUpdateFields(){
        UpdateItemCodeInput.setDisable(true);
        UpdateItemNameInput.setDisable(true);
        UpdateItemBrandInput.setDisable(true);
        UpdateItemQuantityInput.setDisable(true);
        UpdateItemCategoryInput.setDisable(true);
        UpdateItemPriceInput.setDisable(true);
        UpdateItemPurchasedDate.setEditable(false);
        UpdateImageInput.setImage(null);
    }

    // Enable all update Input text fields
    public void enableUpdateFields(){
        UpdateItemCodeInput.setDisable(false);
        UpdateItemNameInput.setDisable(false);
        UpdateItemBrandInput.setDisable(false);
        UpdateItemQuantityInput.setDisable(false);
        UpdateItemCategoryInput.setDisable(false);
        UpdateItemPriceInput.setDisable(false);
        UpdateItemPurchasedDate.setEditable(true);
    }
}
