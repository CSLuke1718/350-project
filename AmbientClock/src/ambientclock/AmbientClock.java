
package ambientclock;

import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Bryan Banuelos
 */
public class AmbientClock extends Application {
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        javafx.geometry.Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
 
        //set Stage boundaries to the lower right corner of the visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 400);
        primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 300);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
 
        Button btn = new Button();
        btn.setText("Log In!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                userLogin(primaryStage);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Log In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    
    public void userLogin(Stage primaryStage) {
        GridPane grid1 = new GridPane();
        //put 10 pixel padding around window
        grid1.setPadding(new Insets(10, 10, 10, 10));
        //add padding for each cell
        grid1.setVgap(8); //vertical padding
        grid1.setHgap(8); //horizontal padding
        
        //Log In Text
        Label logInText = new Label("LOG IN");
        GridPane.setConstraints(logInText, 0, 0);
        
        //username label
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 1);
        
        //Token label
        Label tokenLabel = new Label("Auth Token:");
        GridPane.setConstraints(tokenLabel, 0, 2);
        
        //username input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Username");
        GridPane.setConstraints(nameInput, 1, 1);
        
        //Token input
        TextField tokenInput = new TextField();
        tokenInput.setPromptText("Authentication Token");
        GridPane.setConstraints(tokenInput, 1, 2);
        
        Button loginButton = new Button("LOG IN");
        GridPane.setConstraints(loginButton, 0, 5);
        loginButton.setOnAction( e -> {
            confirmLogIn(primaryStage, nameInput.getText(), tokenInput.getText());
        }
        );
        
        grid1.getChildren().addAll(logInText, nameLabel, nameInput, tokenLabel, tokenInput, loginButton);
        Scene scene = new Scene(grid1, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    
    
    
    /**
     *  confirm log in method for Uname and Token
    */
    public void confirmLogIn(Stage primaryStage, String uname, String token){
        try {
            ArrayList goalInfo = GetData.UserInfo(uname, token);
            Updater(primaryStage, goalInfo);
            
        } catch (IOException ex) {
            Logger.getLogger(AmbientClock.class.getName()).log(Level.SEVERE, null, ex);
            
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "INVALID USERNAME OR TOKEN!\n"
                    + "Please check to make sure you have the correct Username and Authentication Token."
                    + "\nYou can get your Authentication Token from BeeMinder.com.");
            alert1.show();
            
            userLogin(primaryStage);
        }
    }
    
    
    
    /**
     * 
     * @param primaryStage
     * @param openGoals
     */
    public void Updater(Stage primaryStage, ArrayList<GoalData> openGoals) {
System.out.println("got to timer view");
  
        ClockStage[] window = new ClockStage[openGoals.size()];
        for(int i = 0; i < openGoals.size(); i++) {
            window[i] = new ClockStage(openGoals.get(i));
        }

        
        
        //from here, do the countdown and get new data every five minutes
        
        
/* we don't need primary stage anymore
        
        //button to return back to Log In screen
        primaryStage.setOnCloseRequest(e -> {
        });

        primaryStage.setTitle("Timer Countdown");
        primaryStage.setResizable(false);
        primaryStage.show();
        
        */
    }
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
