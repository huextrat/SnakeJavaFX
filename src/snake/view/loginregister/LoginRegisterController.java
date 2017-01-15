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
package snake.view.loginregister;

import java.io.IOException;
import static snake.view.loginregister.SHA256.sha256;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import snake.utilities.DBInfo;

/**
 * FXML Controller class
 * 
 * The controller class for the Login view
 * @author huextrat <extrat.h@gmail.com>
 */
public class LoginRegisterController extends DBInfo implements Initializable{
    @FXML private TextField logUsername;
    @FXML private PasswordField logPassword;
    @FXML private TextField registerUsername;
    @FXML private PasswordField registerPassword;
    @FXML private PasswordField registerConfirm;
    @FXML private TextField registerMail;
    @FXML private Label logLabel;
    @FXML private Label registerLabel;
    
    @FXML
    public void registerButton() throws NoSuchAlgorithmException{
        String user = registerUsername.getText();
        String pass = sha256(registerPassword.getText());
        String confirm = sha256(registerConfirm.getText());
        String mail = registerMail.getText();
        
        boolean isValidMail = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail);
        
        if(user.equals("")){
            registerLabel.setText("Nom d'utilisateur incorrect.");
        } else if(!pass.equals(confirm)){
            registerLabel.setText("Confirmation du mot de passe incorrect.");
            registerPassword.clear();
            registerConfirm.clear();
        } else if(!isValidMail){
            registerLabel.setText("Adresse mail incorrect.");
            registerMail.clear();
        } else {
            try {
                Class.forName(getMyDriver());
                try (Connection conn = DriverManager.getConnection(getMyUrl(), getDBUser(), getDBPass())) {
                    String query2 = "select * FROM Users";
                    PreparedStatement stmt = conn.prepareStatement(query2);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while(rs.next()){
                            if(rs.getString("name").equals(user)){
                                registerLabel.setText("Nom d'utilisateur déjà existant !");
                                return;
                            }
                        }
                    }
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                
                    String query = "insert into Users (name, password, email, date_created) values (?, ?, ?, ?)";
                
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, user);
                    preparedStmt.setString(2, pass);
                    preparedStmt.setString(3, mail);
                    preparedStmt.setString(4, dateFormat.format(date));
                
                    preparedStmt.execute();
                     
                }
            }
            catch (ClassNotFoundException | SQLException e)
            {
            }
            registerLabel.setText("Compte crée avec succès !");
            registerUsername.clear();
            registerPassword.clear();
            registerConfirm.clear();
            registerMail.clear();
        }
    }
    
    @FXML
    public void connectButton() throws NoSuchAlgorithmException{
        String user = logUsername.getText();
        String pass = sha256(logPassword.getText());
        try {
            Class.forName(getMyDriver());
            Connection conn = DriverManager.getConnection(getMyUrl(), getDBUser(), getDBPass());
            String query = "select * FROM Users WHERE name = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user);
            stmt.setString(2, pass);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    if(rs.getString("name").equals(user) && rs.getString("password").equals(pass)){
                        NAMEPLAYER.setValue(rs.getString("name"));
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setController(snake.view.menu.MenuController.class);
                        Parent root;
                        Stage primaryStage = (Stage) logUsername.getScene().getWindow();
                        try {
                            root = FXMLLoader.load(this.getClass().getResource("/view/menu/Menu.fxml"));
                            primaryStage.setTitle("SnakeGame");
                            primaryStage.setScene(new Scene(root, 550, 500));
                            primaryStage.show();
                        } catch (IOException e) {
                            System.exit(0);
                        } 
                    }
                }
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
        }
        logLabel.setText("Erreur de login ou de mot de passe !");
        logUsername.clear();
        logPassword.clear();
    }
    
    @FXML
    public void noNetwork(){
        NAMEPLAYER.setValue("local");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(snake.view.menu.MenuController.class);
        Parent root;
        Stage primaryStage = (Stage) logUsername.getScene().getWindow();
        try {
            root = FXMLLoader.load(this.getClass().getResource("/view/menu/Menu.fxml"));
            primaryStage.setTitle("SnakeGame");
            primaryStage.setScene(new Scene(root, 550, 500));
            primaryStage.show();
        } catch (IOException e) {
            System.exit(0);
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logPassword.setOnKeyPressed((KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                try {
                    connectButton();
                } catch (NoSuchAlgorithmException ex) {
                }
            }
        });
        registerMail.setOnKeyPressed((KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER)  {
                try {
                    registerButton();
                } catch (NoSuchAlgorithmException ex) {
                }
            }
        });
    }
}
