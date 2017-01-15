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
package snake.view.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import snake.savescore.SaveScores;
import static snake.play.Snake.BOARD;
import static snake.play.Snake.TIMELINE;

/**
 * FXML Controller class
 * 
 * The controller class for game view
 * @author huextrat <extrat.h@gmail.com>
 */
public class ControllerSnake extends SaveScores implements Initializable {
    private int playerScore;
    private String playerName;
    private String playerMode;
    @FXML private Button newGame;
    @FXML private Label playerLab;
    
    @FXML
    public void newGame() throws ClassNotFoundException{
        save();
        Stage stage = (Stage) newGame.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/menu/Menu.fxml"));
        Parent root1;
        try {
            root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            Image icon = new Image("snakeicon.png");
            stage1.getIcons().add(icon);
            stage1.setTitle("SnakeGame");
            stage1.setScene(new Scene(root1,550,500)); 
            stage1.show();
            TIMELINE.stop();
        } catch (IOException e) {
            System.exit(0);
        }
        
    }
    
    @FXML
    public void leaderBoard(){
        Stage leaderStage;
        Parent root;
        try {
            leaderStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/view/leaderboard/Leaderboard.fxml"));
            Image icon = new Image("snakeicon.png");
            leaderStage.getIcons().add(icon);
            leaderStage.setScene(new Scene(root));
            leaderStage.setTitle("Tableau des scores");
            leaderStage.show();
        } catch (IOException e) {
            System.exit(0);
        } 
    }

    @FXML
    public void popupAbout(){
        Stage popupStage;
        Parent root;
        try {
            popupStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/view/popup/PopupAbout.fxml"));
            Image icon = new Image("snakeicon.png");
            popupStage.getIcons().add(icon);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("A propos !");
            popupStage.show();
        } catch (IOException e) {
            System.exit(0);
        } 
    }

    @FXML
    public void exit() throws ClassNotFoundException {
        save();
        System.exit(0);
    }
    
    @FXML
    public void feedback(){
        Stage popupStage;
        Parent root;
        try {
            popupStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/view/popup/Feedback.fxml"));
            Image icon = new Image("snakeicon.png");
            popupStage.getIcons().add(icon);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Feedback !");
            popupStage.show();
        } catch (IOException e) {
            System.exit(0);
        } 
    }
    
    public void save() throws ClassNotFoundException{
        playerName = NAMEPLAYER.getValue();
        playerScore = BOARD.getBestScore().getValue();
        playerMode = speedText.getValue();
        
        saveScores(playerMode, playerScore, playerName);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        playerLab.textProperty().bindBidirectional(NAMEPLAYER);
        newGame.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) newScene.getWindow();
            stage.setOnCloseRequest(e -> {
                try {
                    save();
                } catch (ClassNotFoundException ex) {
                }
                Platform.exit();
                System.exit(0);
            });
        });
    });
    }
}
