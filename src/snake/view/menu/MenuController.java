/* 
 * Copyright (C) 2016 huextrat <extrat.h@gmail.com>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package snake.view.menu;

import snake.utilities.CheckOnlineVersion;
import snake.utilities.Speed;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static snake.Main.actualVersion;
import snake.play.Snake;
import snake.play.StartSnake;
import static snake.cellfactory.FactoryCell.snakeColor;

/**
 * FXML Controller class
 * 
 * The controller class for the menu view
 * @author huextrat <extrat.h@gmail.com>
 */
public class MenuController implements Initializable{
    @FXML private RadioButton rbLent;
    @FXML private RadioButton rbMoyen;
    @FXML private RadioButton rbRapide;
    @FXML private RadioButton rbExtreme;
    @FXML private RadioButton rbNoField;
    @FXML private ToggleGroup radioSpeed;
    @FXML public TextField nameField;
    @FXML private Button play;
    @FXML private ColorPicker colorPicker;
    
    private StringProperty onlineVersion = new SimpleStringProperty();
    
    private Snake snake;
    private Stage popupStage;
        
    public static final StringProperty NAMEPLAYER = new SimpleStringProperty();    
    public static StringProperty speedText = new SimpleStringProperty();
    
    private static double speed;
    public static double getSpeed(){ return speed; }
    
    @FXML
    public void radioSelect(){
        if(rbLent.isSelected()){
            speedText.setValue("Lent");
            speed = Speed.SLOW.getValue();
        }
        if(rbMoyen.isSelected()){
            speedText.setValue("Moyen");
            speed = Speed.MID.getValue();
        }
        if(rbNoField.isSelected()){
            speedText.setValue("SansBord");
            speed = Speed.NOFIELD.getValue();
        }
        if(rbRapide.isSelected()){
            speedText.setValue("Rapide");
            speed = Speed.FAST.getValue();
        }
        if(rbExtreme.isSelected()){
            speedText.setValue("Extreme");
            speed = Speed.EXTREME.getValue();
        }
        verifToPlay();
    }
    
    public void verifToPlay(){
        if(radioSpeed.getSelectedToggle().isSelected()){
            play.setDisable(false);
        }
    }

    @FXML
    public void play() { 
        snake = new Snake();
        new StartSnake().startSnake((Stage) play.getScene().getWindow());
    }
    
    @FXML
    public void labelHow(){
        Parent root;
        try {
            popupStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/view/popup/HowPlay.fxml"));
            Image icon = new Image("snakeicon.png");
            popupStage.getIcons().add(icon);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Comment jouer ?");
            popupStage.show();
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @FXML
    public void quit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!NAMEPLAYER.getValue().equals("local")){
            CheckOnlineVersion checkOnline = new CheckOnlineVersion();
            onlineVersion = checkOnline.checkOnlineVersion();
        }
        nameField.textProperty().bindBidirectional(NAMEPLAYER);
        nameField.setDisable(true);
        radioSpeed.selectToggle(null);
        snakeColor.setValue(Color.web("#2ecc71"));
        colorPicker.setValue(Color.web("#2ecc71"));
        colorPicker.setOnAction((ActionEvent event) -> {
            snakeColor = colorPicker;
        });
        play.setDisable(true);
        nameField.setOnKeyPressed((KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                if(!play.isDisabled()){
                    play();
                }
            }
        });
        if(!NAMEPLAYER.getValue().equals("local") && !actualVersion.equals(onlineVersion.getValue())){
            newVersionAvailable();
        }
    }

    public void newVersionAvailable(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(snake.view.menu.MaJAvailableController.class);
        Parent root1;
        Stage newStage;
        try {
            newStage = new Stage();
            root1 = FXMLLoader.load(this.getClass().getResource("/view/popup/MaJAvailable.fxml"));
            Image icon = new Image("snakeicon.png");
            newStage.getIcons().add(icon);
            newStage.setTitle("SnakeGame");
            newStage.setScene(new Scene(root1));
            newStage.show();
            newStage.setAlwaysOnTop(true);
        } catch (IOException e) {
            System.exit(0);
        } 
    }
}