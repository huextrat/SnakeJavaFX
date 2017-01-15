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
package snake.view.leaderboard;

import snake.utilities.InitLeader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 * 
 * The controller class for the LeaderBoard view
 * @author huextrat <extrat.h@gmail.com>
 */
public class LeaderController implements Initializable {
    @FXML private ChoiceBox<String> choiceMode;
    @FXML private ListView<String> listView;
    @FXML private Label localLabel;
    private final InitLeader initLeader = new InitLeader();
    
    private void loadScoreDB(){        
        String query;
        int l=1,m=1,r=1,e=1,s=1;
        choiceMode.setItems(initLeader.gameMode);
        if(!initLeader.playerName.equals("local"))
            try {
                Class.forName(initLeader.getMyDriver());
                Connection conn = DriverManager.getConnection(initLeader.getMyUrl(), initLeader.getDBUser(), initLeader.getDBPass());
                query = "select mode,name,score FROM Score ORDER BY score DESC";
                PreparedStatement stmt = conn.prepareStatement(query);
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()){
                        switch(rs.getString("mode")){
                            case "Lent":
                                if(l<11){
                                    initLeader.speedSlow.add(l +" - "+rs.getString("name")+"     :       "+rs.getInt("score"));
                                    l=l+1;
                                }
                                break;
                            case "Moyen":
                                if(m<11){
                                    initLeader.speedMid.add(m +" - "+rs.getString("name")+"     :       "+rs.getInt("score"));
                                    m=m+1;
                                }
                                break;
                            case "Rapide":
                                if(r<11){
                                    initLeader.speedFast.add(r +" - "+rs.getString("name")+"     :       "+rs.getInt("score"));
                                    r=r+1;
                                }
                                break;
                            case "Extreme":
                                if(e<11){
                                    initLeader.speedExtreme.add(e +" - "+rs.getString("name")+"     :       "+rs.getInt("score"));
                                    e=e+1;
                                }
                                break;
                            case "SansBord":
                                if(s<11){
                                    initLeader.speedNoField.add(s +" - "+rs.getString("name")+"     :       "+rs.getInt("score"));
                                    s=s+1;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            catch (ClassNotFoundException | SQLException d)
            {
            }
        else { 
            listView.setDisable(true);
            localLabel.setText("Vous jouez sur un compte local !");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadScoreDB();
        choiceMode.setOnAction((event) -> {
            switch (choiceMode.getSelectionModel().getSelectedItem()) {
                case "Lent":
                    listView.setItems(initLeader.speedSlow);
                    break;
                case "Moyen":
                    listView.setItems(initLeader.speedMid);
                    break;
                case "Rapide":
                    listView.setItems(initLeader.speedFast);
                    break;
                case "Extreme":
                    listView.setItems(initLeader.speedExtreme);
                    break;
                case "Sans Bord":
                    listView.setItems(initLeader.speedNoField);
                    break;
                default:
                    break;
            }
        });
    }    

    
}
