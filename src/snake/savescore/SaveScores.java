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
package snake.savescore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import snake.utilities.DBInfo;
/**
 * The class to save the score
 * @author huextrat
 */
public class SaveScores extends DBInfo {
    String playerMode;
    int scorePlayer;
    String namePlayer;
            
    /**
     * The method allows to take player information and to call the type of saving
     * @param playerMode
     * @param scorePlayer
     * @param namePlayer
     * @throws ClassNotFoundException 
     */
    public void saveScores(String playerMode, int scorePlayer, String namePlayer) throws ClassNotFoundException{
        this.playerMode = playerMode;
        this.scorePlayer = scorePlayer;
        this.namePlayer = namePlayer;
        saveScoreDB();
    }
    
    /**
     * The method to save player score into database
     * @throws ClassNotFoundException 
     */
    public void saveScoreDB() throws ClassNotFoundException {
        if(!namePlayer.equals("local") && scorePlayer > 0){
            Class.forName(getMyDriver());
            try (Connection conn = DriverManager.getConnection(getMyUrl(), getDBUser(), getDBPass())) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
            
                String query = "insert into Score (mode, name, score, date_realized) values (?, ?, ?, ?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, playerMode);
                preparedStmt.setString(2, namePlayer);
                preparedStmt.setInt(3, scorePlayer);
                preparedStmt.setString(4, dateFormat.format(date));
                preparedStmt.execute();
            }
            catch (SQLException e)
            {
            }
        }
    }
}
