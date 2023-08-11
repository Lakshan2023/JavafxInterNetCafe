package LoginManager;

import com.example.demo2.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class WelcomePage {
    @FXML
    private Button LoginMinimise;

    private double xPosition; // Tracks the x position of the login page

    private double yPosition; // Tracks the y position of the login page

    @FXML
    private StackPane WelcomePane;

    // This method handles the "Exit" program stage operations
    @FXML
    void ExitProgram(MouseEvent event) {
        // This alert box will  make sure whether user wants to exit the program
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.setTitle("One Net Cafe");
        exitAlert.setHeaderText("Do you really want to exit the program ?");

        // This shows the alert and wait showing the massage until user's response
        Optional<ButtonType> resultExit = exitAlert.showAndWait();
        if(resultExit.get() ==  ButtonType.OK){
            //If user clicks ok, exit the program
            Platform.exit();
        }

        //If the user clicks cancel, then it will remain in the program
    }

    // This method handles to minimise stage operations
    @FXML
    void MinimiseTheProgram(MouseEvent event) {
        // Taking the current stage and minimise it
        Stage stage = (Stage) LoginMinimise.getScene().getWindow();
        stage.setIconified(true);
    }

    // This method sets the loginPage.fxml to the stage
    @FXML
    void GetStart(MouseEvent event) throws IOException {
        // Load the LoginPage.fxml using FXML Loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("/com/example/demo2/LoginPage.fxml"));
        Parent root = loader.load();

        // Get the current stage and setting the scene as LoginPage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Setting the dimension size as 428 as width and 691 as width
        Scene scene = new Scene(root, 428, 691);
        stage.setScene(scene);
        stage.centerOnScreen();

        // Show the stage
        stage.show();
    }

    // This method handles the dragging the WelcomePage on the screen
    @FXML
    void WelcomePaneDragged(MouseEvent event) {
        // Take the current stage
        Stage stage = (Stage) WelcomePane.getScene().getWindow();

        // set the x and y dimensions to the dragged positions
        stage.setX(event.getScreenX() - xPosition);
        stage.setY(event.getScreenY() - yPosition);
    }

    // This method handles detecting whether user has pressed on the stage
    @FXML
    void WelcomePanePressed(MouseEvent event) {
        // When the Pane pressed, taking the initial X and Y positions
        xPosition = event.getSceneX();
        yPosition = event.getSceneY();
    }


}
