/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambientclock;

import java.text.ParseException;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * 
 * 
 * This class was inspired by code found on stackexchange
 * https://codereview.stackexchange.com/questions/256836/pomodoro-javafx-mvc-pattern-countdown-timer
 * 
 * @author CSLuk
 */
public class ClockStage {
    private Label dayTime;
    private Label hourTime;
    private Label minTime;
    private Label secTime;
    private Label amtDue;
    private Stage s;
    private Scene scene;
    
    public ClockStage(GoalData goal) {
        VBox Layer1VB;      
        HBox Layer2HB;      
        VBox Layer3Days;    
        VBox Layer3Hours;   
        VBox Layer3Mins;    
        VBox Layer3Secs;
        
        int[] time = TimeConverter.Convert(goal);
        
        dayTime = new Label(Integer.toString(time[0]));
        hourTime = new Label(Integer.toString(time[1]));
        minTime = new Label(Integer.toString(time[2]));
        secTime = new Label(Integer.toString(time[3]));
        
        Label day = new Label("Days:");
        Layer3Days = new VBox(day,dayTime);
        
        Label hour = new Label("Hours:");
        Layer3Hours = new VBox(hour,hourTime);
        
        Label mins = new Label("Mins:");
        Layer3Mins = new VBox(mins, minTime);
        
        Label secs = new Label("Secs:");
        Layer3Secs = new VBox(secs,secTime);
        
        Layer2HB = new HBox(Layer3Days, Layer3Hours, Layer3Mins, Layer3Secs);
        
        Label titleLabel = new Label(goal.getSlug());
        amtDue = new Label(goal.getSafetyText());
        Layer1VB = new VBox(titleLabel, Layer2HB, amtDue);
        
        Layer1VB.setAlignment(Pos.CENTER);
        Layer2HB.setAlignment(Pos.CENTER);
        Layer3Days.setAlignment(Pos.CENTER);
        Layer3Hours.setAlignment(Pos.CENTER);
        Layer3Mins.setAlignment(Pos.CENTER);
        Layer3Secs.setAlignment(Pos.CENTER);
        
        

        //Scene scene = new Scene(vbox, 400, 400);
        scene = new Scene(Layer1VB, 300, 250);
        s = new Stage();
        
        s.setTitle(goal.getSlug());
        s.setScene(scene);
        s.show();

    }

    public Stage getStage() {
        return s;
    }
    
    public Scene getScene() {
        return scene;
    }

    public Label getDayTime() {
        return dayTime;
    }

    public Label getHourTime() {
        return hourTime;
    }

    public Label getMinTime() {
        return minTime;
    }

    public Label getSecTime() {
        return secTime;
    }

}