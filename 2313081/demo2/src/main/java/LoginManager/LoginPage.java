package LoginManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.demo2.ItemManager.makeFileReadOnly;
import static com.example.demo2.ItemManager.makeFileWritable;

public class LoginPage implements Serializable{

    @FXML
    protected TextField Password;

    @FXML
    private StackPane LoginPane;

    @FXML
    protected TextField UserName;

    @FXML
    private AnchorPane signIn;

    @FXML
    private AnchorPane signUp;

    @FXML
    private CheckBox showPassword;

    @FXML
    private PasswordField hidePassword;

    private double xPosition; // This tracks the x Position of the Scene

    private double yPosition; // This tracks the y Position of the scene

    @FXML
    private PasswordField SignUpConfirmPassword;

    @FXML
    private PasswordField SignUpPassword;

    @FXML
    private TextField SignUpUserName;

    @FXML
    private Label SignUpMassage;

    public static ArrayList<String[]> Accounts; // List that stores account details

    @FXML
    private Label SignInMassage;

    @FXML
    private Button LoginMinimise;

    // This method make the Signup page visible
    @FXML
    void CreateAccount(MouseEvent event) {
        signIn.setVisible(false); // Disable sign-in page
        signUp.setVisible(true);  // Enable sign-up page
    }


    // This method handles the login process
    @FXML
    void Login(MouseEvent event) throws IOException {
        String UserGivenName = UserName.getText().trim();  // This will temporarily store the username
        String UserGivenPassword; // This will temporarily store the password

        // This checks whether the checkbox is selected or not to get the password
        if (!showPassword.isSelected()) {
            // if it is not selected assign the hide password value to UserGiven Password
            UserGivenPassword = hidePassword.getText();
        } else {
            // If it is selected , assign the text field Password text to the UserGiven Password
            UserGivenPassword = Password.getText();
        }

        // This checks whether user given username and password are correct
        for (String[] index : Accounts) {
            if (index[0].equals(UserGivenName) && index[1].equals(UserGivenPassword)) {

                // If it is correct, then stage will call the inventoryMainController.fxml
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/demo2/InventoryMainController.fxml"));
                Parent root = loader.load();  // This will load the stage

                // This takes the control of the InventoryMainController
                com.example.demo2.InventoryMainController inventoryMainController = loader.getController();

                // This changes the Username that displays in the main page to the Username of the login account
                inventoryMainController.UserName.setText(UserGivenName);

                // This will align the text centered
                inventoryMainController.UserName.setStyle("-fx-alignment: center;");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Initializing the dimensional size of the scene
                Scene scene = new Scene(root, 1100, 600);

                stage.setScene(scene);
                // Locate the scene on center of the screen

                stage.centerOnScreen();
                stage.show(); // show the stage
                return;
            }
        }

        // If Login failed, display the below massage and center it on the screen
        SignInMassage.setText("Please input a valid username\n\t\tand password!");
        SignInMassage.setStyle("-fx-alignment: center");
    }

    // This method make the Signup page visible
    @FXML
    void GoToLoginAccount(MouseEvent event) throws IOException {
        signUp.setVisible(false); // Disable the sign-up page
        signIn.setVisible(true);  // Enable the sign-in page
    }

    // The method handles showing the password field
    @FXML
    private void showPasswordField(ActionEvent event) {
        if (showPassword.isSelected()) {
            // if check box is selected disable the password field
            hidePassword.setVisible(false);
            // Enable the password text-field
            Password.setVisible(true);
            // get the text from password field and set it on the enabled text-field
            Password.setText(hidePassword.getText());
        } else {
            // If the checkbox is not selected disable the password text field
            Password.setVisible(false);
            hidePassword.setVisible(true);
            // get the text from text-field and set it on the enabled password field
            hidePassword.setText(Password.getText());
        }
    }

    // This enables dragging the login page on the screen
    @FXML
    void LoginPaneDragDetected(MouseEvent event) {
        Stage stage = (Stage) LoginPane.getScene().getWindow(); // get the current stage
        stage.setX(event.getScreenX() - xPosition); // set the x position to user dragged position
        stage.setY(event.getScreenY() - yPosition); // set the y position to user dragged position
    }

    /* This method detects the current x and y dimensions of the login page,
    when user click on the page
     */
    @FXML
    void LoginPanePressed(MouseEvent event) {
        xPosition = event.getSceneX(); // Takes the current x position
        yPosition = event.getSceneY(); // Takes the current y position
    }

    // This method handles the signup process when user click on sign up
    @FXML
    void SignUp(ActionEvent event) throws IOException {
        String userName = SignUpUserName.getText(); // get the user typed sign up name
        String userPassword = SignUpPassword.getText(); // get the user typed sign up password
        // get the user typed sign up conformation password
        String userConfirmPassword = SignUpConfirmPassword.getText();

        // Checks whether given username, password and password conformation is empty
        if (!userName.equals("") && !userPassword.equals("") && !userConfirmPassword.equals("")) {

            // first check whether user given username already exists in the system
            for (String[] index : Accounts) {
                if (index[0].equals(userName)) {
                    // If the username already exists display the below massage
                    SignUpMassage.setText("That account already exists !!!");
                    SignUpMassage.setStyle("-fx-text-alignment: center");
                    return;
                }
            }

            // Checks whether given username consist with more than ten characters
            if (userName.length() > 10) {
                // If it is consist more than ten chars then display the below massage
                SignUpMassage.setText("Username cannot exceed 10 characters!");
                SignUpMassage.setStyle("-fx-text-alignment: center");
                return;
            }

            // This checks the validity of the given password
            boolean passwordValidity = checkPasswordValid(userPassword);

            // If it is not valid display the below massage
            if (!passwordValidity) {
                SignUpMassage.setText("Password must contain at least 8 to 20characters,\n" +
                        "with at least one uppercase letter, one lowercase letter,\n" +
                        "and one digit.");
                return;
            }

            // Check whether password conformation is equals to user given password
            if (userPassword.equals(userConfirmPassword)) {
                String[] accDetails = new String[2]; // Make an Array to store the given username and password
                accDetails[0] = userName; // assign the username to the Array
                accDetails[1] = userPassword; // assign the password to the Array
                Accounts.add(accDetails); // Add that array to the Accounts array list
                SaveAccounts(); // write the Accounts array list in Account.txt

                // Then stage will call the inventoryMainController.fxml
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/demo2/InventoryMainController.fxml"));
                Parent root = loader.load(); // This will load the stage

                // This takes the control of the InventoryMainController
                com.example.demo2.InventoryMainController inventoryMainController = loader.getController();

                // This changes the Username that displays in the main page to the Username of the login account
                inventoryMainController.UserName.setText(userName);

                // This will align the text centered
                inventoryMainController.UserName.setStyle("-fx-alignment: center;");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Initializing the dimensional size of the scene as 1100 width and 600 height
                Scene scene = new Scene(root, 1100, 600);
                stage.setScene(scene); // Locate the scene on center of the screen
                stage.centerOnScreen();
                stage.show(); // show the stage

            } else {
                // If password confirmation failed, display the below massage and center it on the screen
                SignUpMassage.setText("Please Check your password conformation!!!");
                SignUpMassage.setStyle("-fx-text-alignment: center");
            }
        }
    }

    private boolean checkPasswordValid(String password) {
        /* Password must contain at least 8 characters, with at least one uppercase letter,
         one lowercase letter, and one digit. */
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$");
        // https://www.javatpoint.com/java-regex
        // https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
    }

    // This method stores account username and password using serializable text
    protected static void SaveAccounts() {
        String filePath = "Account.txt"; // file path that saves data

        FileOutputStream fileOutputStream = null; // Assigning stream for writing data to the file
        ObjectOutputStream objectOutputStream = null; // Assigning stream for writing objects to the file

        try {

            makeFileWritable(filePath); // Before saving the details to the file, make the file writable

            fileOutputStream = new FileOutputStream(filePath); // Create a fileOutputStream for the specified file path

            // Create the ObjectOutput stream to serialize the objects and write it to the file
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Accounts); // Writing Accounts array list to the file
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // After writing the data, if the objectOutput Stream is not null, close the ObjectOutputStream
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // After writing the data, if the fileOutput Stream is not null, close the FileOutputStream
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set the file as read-only after saving data to the text file
        makeFileReadOnly(filePath);
    }

    //Load the saved account details to the system using deserialization
    public static ArrayList<String[]> loadAccountDetails() {
        String filePath = "Account.txt"; //File path that saves the account details
        ArrayList<String[]> details = new ArrayList<>(); // List that stores, the loaded account details

        FileInputStream fileInputStream = null; // Stream that reads data from the file
        ObjectInputStream objectInputStream = null; // Stream that reads objects from the file

        try {
            // Create a FileInputStream to the Account.txt
            fileInputStream = new FileInputStream(filePath);

            // Create an ObjectInputStream to deserialize objects from the file
            objectInputStream = new ObjectInputStream(fileInputStream);

            // Reads the Object using Object InputStream and cast it to String Array List
            details = (ArrayList<String[]>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("Account file not found.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the ObjectInputStream after reading the file
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Close the fileInputStream if stream is not null
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return details;
    }

    // This method makes the ability to minimise the pane
    @FXML
    void MinimiseTheProgram(MouseEvent event) {
        // Get the current stage
        Stage stage = (Stage) LoginMinimise.getScene().getWindow();
        // Minimise the pane
        stage.setIconified(true);
    }

    // This method allows the user to exit the program
    @FXML
    void ExitProgram(MouseEvent event) {
        // Alert that asks user whether they need to exit the program
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.setTitle("One Net Cafe");
        exitAlert.setHeaderText("Do you really want to exit the program ?");

        // Until user confirms, show and wait the massage
        Optional<ButtonType> resultExit = exitAlert.showAndWait();
        if (resultExit.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

}

