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
package snake.view.feedback;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import snake.utilities.DBInfo;

/**
 * FXML Controller class
 * 
 * The controller class for the feedback view
 * @author huextrat <extrat.h@gmail.com>
 */
public class FeedbackController extends DBInfo implements Initializable {

    @FXML private TextField mailField;
    @FXML private ChoiceBox choiceBox;
    @FXML private TextArea textArea;
    @FXML private Label label;
    
    private final ObservableList<String> catFeed = FXCollections.observableArrayList("Bug","Contact","Amélioration");
    
    @FXML
    public void sent() throws ClassNotFoundException{
        boolean isValidMail = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mailField.getText());
        if(!isValidMail){
            label.setText("Adresse mail incorrect.");
            mailField.clear();
        } else if(textArea.getText().equals("")){
            label.setText("Aucun message !");
        } else {
            Class.forName(getMyDriver());
            try (Connection conn = DriverManager.getConnection(getMyUrl(), getDBUser(), getDBPass())) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
            
                String query = "insert into Feedback (name, mail, cat, message, date_created) values (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, NAMEPLAYER.getValue());
                preparedStmt.setString(2, mailField.getText());
                preparedStmt.setString(3, choiceBox.getValue().toString());
                preparedStmt.setString(4, textArea.getText());
                preparedStmt.setString(5, dateFormat.format(date));
                preparedStmt.execute();
                label.setText("Feedback envoyé avec succès !");
            }
            catch (SQLException e)
            {
            }
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        nameField.setText(NAMEPLAYER.getValue());
        nameField.setDisable(true);
        choiceBox.setItems(catFeed);
        choiceBox.setValue("Bug");
    }    
    
}
