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
package snake;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class
 * @author huextrat <extrat.h@gmail.com>
 */
public class Main extends Application{
    public static String actualVersion = "2.1";
    
    /**
     * The method launches the game with login page
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(snake.view.loginregister.LoginRegisterController.class);
        Parent root;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/view/loginregister/LoginRegister.fxml"));
            Image icon = new Image("snakeicon.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("SnakeGame");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnCloseRequest(e -> Platform.exit());
        } catch (IOException e) {
            System.exit(0);
        } 
    }

    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }


}