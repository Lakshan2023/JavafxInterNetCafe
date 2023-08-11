package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDate;

abstract public class InventoryMainController implements Initializable {

    @FXML
    public AnchorPane AddingWhitePane;
    @FXML
    protected Button DeleteButton;
    @FXML
    public TextField DeleteSearchBar;

    @FXML
    public Button DeleteSearchButton;

    @FXML
    public AnchorPane GenerateRandomDealerSidePane;

    @FXML
    public Button ImageInputButton;

    @FXML
    public AnchorPane InnerPane;

    @FXML
    public AnchorPane ItemDeletePane;

    @FXML
    public AnchorPane ItemDeleteSidePane1;

    @FXML
    public AnchorPane ItemInputSidePane;

    @FXML
    AnchorPane ItemSidePane;

    @FXML
    public AnchorPane ItemUpdatePane;

    @FXML
    public AnchorPane ItemUpdateSidePane;

    @FXML
    public AnchorPane ItemViewPane;

    @FXML
    public Button MinimiseButton;

    @FXML
    public AnchorPane RandomDealerSelectionPane;

    @FXML
    public TextField SearchBarPaneDealers;

    @FXML
    public Button SearchDealerButton;

    @FXML
    public AnchorPane SeeSelectedDealersSidePane;
    @FXML
    public AnchorPane SelectedIndividualDealerPane;

    @FXML
    public AnchorPane SelectedIndividualDealerSidePane;

    @FXML
    public AnchorPane SelectedRandomDealersPane;

    @FXML
    public AnchorPane SidePane;
    @FXML
    public Button UpdateButton;
    @FXML
    public TextField UpdateSearchBar;

    @FXML
    public Button UpdateSearchButton;

    @FXML
    public Label UserName;

    @FXML
    public Button ViewImageFilterButton;

    @FXML
    public TextField ViewItemSearchBar;

    @FXML
    public Button ViewUpdateButton;
    @FXML
    public AnchorPane mainPane;
    @FXML
    public Label GreetingMassage;

    @FXML
    public StackPane MainStackPane;

    @FXML
    public  TextField ItemCodeInput;

    @FXML
    public  TextField ItemNameInput;

    @FXML
    public  TextField ItemBrandInput;

    @FXML
    public TextField ItemPriceInput;

    @FXML
    public TextField ItemQuantityInput;

    @FXML
    public TextField ItemCategoryInput;

    @FXML
    public DatePicker DateInput;

    @FXML
    public ImageView ItemImageInput;

    @FXML
    public TableColumn<ItemDetails, String> ItemViewBrand;

    @FXML
    public TableColumn<ItemDetails, String> ItemViewCategory;

    @FXML
    public TableColumn<ItemManager, String> ItemCode;

    @FXML
    public TableColumn<ItemDetails, Image> ItemViewImage;

    @FXML
    public TableColumn<ItemDetails, String> ItemViewName;

    @FXML
    public TableColumn<ItemDetails, Double> ItemViewPrice;

    @FXML
    public TableColumn<ItemDetails, LocalDate> ItemViewPurchasedDate;

    @FXML
    public TableColumn<ItemDetails, Integer> ItemViewQuantity;

    @FXML
    public TableView<ItemDetails> ItemViewTable;

    @FXML
    public Label ViewItemTotalCount;

    @FXML
    public Label ViewItemTotalPrice;


    @FXML
    public TableColumn<ItemDetails, String> UpdateItemBrand;

    @FXML
    public TableColumn<ItemDetails, String> UpdateItemCategory;

    @FXML
    public TableColumn<ItemManager, String> UpdateItemCode;

    @FXML
    public TableColumn<ItemDetails, Image> UpdateItemImage;

    @FXML
    public TableColumn<ItemDetails, String> UpdateItemName;

    @FXML
    public TableColumn<ItemDetails, Double> UpdateItemPrice;

    @FXML
    public TableColumn<ItemDetails, LocalDate> UpdateItemPurchasedDate;

    @FXML
    public TableColumn<ItemDetails, Integer> UpdateItemQuantity;

    @FXML
    public TableView<ItemDetails> UpdateViewTable;

    @FXML
    public TableColumn<ItemDetails, String> DeleteViewItemBrand;

    @FXML
    public TableColumn<ItemDetails, String> DeleteViewItemCategory;

    @FXML
    public TableColumn<ItemManager, String> DeleteViewItemCode;

    @FXML
    public TableColumn<ItemDetails, Image> DeleteViewItemImage;

    @FXML
    public TableColumn<ItemDetails, String> DeleteViewItemName;

    @FXML
    public TableColumn<ItemDetails, Double> DeleteViewItemPrice;

    @FXML
    public TableColumn<ItemDetails, LocalDate> DeleteViewItemPurchasedDate;

    @FXML
    public TableColumn<ItemDetails, Integer> DeleteViewItemQuantity;

    @FXML
    public TableView<ItemDetails> ItemDeleteTable;

    @FXML
    public ImageView UpdateImageInput;

    @FXML
    protected TextField UpdateItemCategoryInput;

    @FXML
    protected TextField UpdateItemCodeInput;

    @FXML
    protected TextField UpdateItemNameInput;

    @FXML
    protected TextField UpdateItemPriceInput;

    @FXML
    protected TextField UpdateItemQuantityInput;

    @FXML
    protected TextField UpdateItemBrandInput;

    @FXML
    protected DatePicker UpdatePurchasedDateInput;

    @FXML
    protected Label massageInputItemBrand;

    @FXML
    protected Label massageInputItemCategory;

    @FXML
    protected Label massageInputItemCode;

    @FXML
    protected Label massageInputItemCode1;

    @FXML
    protected Label massageInputItemDate;

    @FXML
    protected Label massageInputItemName;

    @FXML
    protected Label massageInputItemPrice;

    @FXML
    protected Label massageInputItemQuantity;

    @FXML
    public Label massageItemUpdateBrand;

    @FXML
    public Label massageItemUpdateCategory;

    @FXML
    public Label massageItemUpdateCode;

    @FXML
    public Label massageItemUpdateDate;

    @FXML
    public Label massageItemUpdateName;

    @FXML
    public Label massageItemUpdateQuantity;

    @FXML
    public Label massageUpdateItemPrice;

    @FXML
    public TableColumn<DealerDetails, String> GeneratedViewContactNumber;

    @FXML
    public TableColumn<DealerDetails, String> GeneratedViewDealerLocation;

    @FXML
    public TableColumn<DealerDetails, String> GeneratedViewDealerName;

    @FXML
    public TableView<DealerDetails> GeneratedViewDealerTable;

    @FXML
    public TableColumn<DealerDetails, String> SortedSelectedDealerContactNumber;

    @FXML
    public TableColumn<DealerDetails, String> SortedSelectedDealerName;

    @FXML
    public TableView<DealerDetails> SortedSelectedDealersTable;

    @FXML
    public TableColumn<DealerDetails, String> SortedSelectedDealerLocation;
    @FXML
    public TableView<DealerDetails> SelectedDealer1Table;

    @FXML
    public TableView<DealerDetails> SelectedDealer2Table;

    @FXML
    public TableView<DealerDetails> SelectedDealer3Table;

    @FXML
    public TableView<DealerDetails> SelectedDealer4Table;

    @FXML
    public TableColumn<DealerDetails, String> Dealer1ItemBrand;

    @FXML
    public TableColumn<DealerDetails, String> Dealer1ItemName;

    @FXML
    public TableColumn<DealerDetails, String> Dealer1ItemPrice;

    @FXML
    public TableColumn<DealerDetails, String> Dealer1ItemQuantity;

    @FXML
    public TableColumn<DealerDetails, String> Dealer2ItemBrand;

    @FXML
    public TableColumn<DealerDetails, String> Dealer2ItemName;

    @FXML
    public TableColumn<DealerDetails, String> Dealer2ItemPrice;

    @FXML
    public TableColumn<DealerDetails, String> Dealer2ItemQuantity;

    @FXML
    public TableColumn<DealerDetails, String> Dealer3ItemBrand;

    @FXML
    public TableColumn<DealerDetails, String> Dealer3ItemName;

    @FXML
    public TableColumn<DealerDetails, String> Dealer3ItemPrice;

    @FXML
    public TableColumn<DealerDetails, String> Dealer3ItemQuantity;

    @FXML
    public TableColumn<DealerDetails, String> Dealer4ItemName;

    @FXML
    public TableColumn<DealerDetails, String> Dealer4ItemPrice;

    @FXML
    public TableColumn<DealerDetails, String> Dealer4ItemQuantity;

    @FXML
    public TableColumn<DealerDetails, String> Dealer4ItemBrand;

    @FXML
    public Label Dealer1Name;
    @FXML
    public Label Dealer2Name;
    @FXML
    public Label Dealer3Name;
    @FXML
    public Label Dealer4Name;

    @FXML
    public TableColumn<DealerDetails, String> SelectedDealerViewContactNumber;

    @FXML
    public TableColumn<DealerDetails, String> SelectedDealerViewDealerName;

    @FXML
    public TableColumn<DealerDetails, String> SelectedDealerViewLocation;

    @FXML
    public TableView<DealerDetails> SelectedDealerViewTable;

    @FXML
    public TableColumn<DealerDetails, String> SingleDealerItemBrand;

    @FXML
    public TableColumn<DealerDetails, String> SingleDealerItemName;

    @FXML
    public TableColumn<DealerDetails, String> SingleDealerItemPrice;

    @FXML
    public TableColumn<DealerDetails, String> SingleDealerItemQuantity;

    @FXML
    public TableView<DealerDetails> SingleDealerViewTable;

    @FXML
    protected abstract void ExitTheProgram(MouseEvent event); // Method that handles exiting the program

    @FXML
    abstract void GoTOGenerateDealers(MouseEvent event); // Method that handles go to generate dealers tab

    @FXML
    abstract void GoToAddItems(MouseEvent event); // Method that handles go to add item details tab

    @FXML
    abstract void GoToDeleteItems(MouseEvent event); // Method that handles go to delete item details tab

    @FXML
    abstract void GoToHomePage(ActionEvent event); // Method that handles go to home page tab

    @FXML
    abstract void GoToSaveItems(MouseEvent event); // Method that handles items saving operations

    @FXML
    abstract void GoToSelectedDealer(MouseEvent event); // Method that handles go to selected dealer tab

    @FXML
    abstract void GoToUpdateItems(MouseEvent event); // Method that handles go to update Items tab

    @FXML
    abstract void GoToViewDealerDetails(MouseEvent event); // Method that handles go to view dealer details tab

    @FXML
    abstract void GoToViewItems(MouseEvent event); // Method that handles go to view items tab

    @FXML
    abstract void StackPaneDragDetected(MouseEvent event); // Method that handles dragging the main pane

    // Method that takes x and y coordinates when clicked on the main pane
    @FXML
    abstract void StackPanePressed(MouseEvent event);

    @FXML
    abstract void MinimiseThePane(MouseEvent event); // Method that handles minimising the pane

    @FXML
    abstract void OpenImageHandler(ActionEvent event); // This method enables to select an image from PC

    @FXML
    abstract void UpdateRowSelection(MouseEvent event); // This method enables to select the updating rows

    @FXML
    abstract void UpdateItems(ActionEvent event); // This method handles updating items

    @FXML
    abstract void DeleteDetails(MouseEvent event); // This method handles deleting item details

    @FXML
    abstract void GenerateDealers(MouseEvent event); // This method handles generating random dealers

    @FXML
    abstract void SearchUpdateItems(MouseEvent event); // This method handles searching items to update

    @FXML
    abstract void SelectDealerDetails(MouseEvent event); // This method handles selecting dealer details

    @FXML
    abstract void ViewItemByItemCode(MouseEvent event);// This method handles searching items by item code

    @FXML
    abstract void DeleteItemByItemCode(MouseEvent event);// This method handles searching items to delete

    @FXML
    abstract void TypeTextChange(KeyEvent event); // This method enables detecting when key release (typing)

    @FXML
    abstract void textUpdateErrorDisplay(KeyEvent event); // This method enables showing textUpdate errors

    @FXML
    abstract void LogOut(MouseEvent event) throws IOException; //This method handle logout from the system
}
