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
package snake.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to initialize the LeaderBoard view
 * @author huextrat <extrat.h@gmail.com>
 */
public class InitLeader extends DBInfo{
    public String playerName = NAMEPLAYER.getValue();
    
    public ObservableList<String> gameMode = FXCollections.observableArrayList("Lent","Moyen","Rapide","Extreme","Sans Bord");
    public ObservableList<String> speedSlow = FXCollections.observableArrayList();
    public ObservableList<String> speedMid = FXCollections.observableArrayList();
    public ObservableList<String> speedFast = FXCollections.observableArrayList();
    public ObservableList<String> speedExtreme = FXCollections.observableArrayList();
    public ObservableList<String> speedNoField = FXCollections.observableArrayList();
}
