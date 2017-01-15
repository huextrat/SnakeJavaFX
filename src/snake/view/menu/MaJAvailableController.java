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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import snake.utilities.CheckOnlineVersion;

/**
 * FXML Controller class
 * 
 * The controller class for the MAJ view
 * @author huextrat <extrat.h@gmail.com>
 */
public class MaJAvailableController implements Initializable {
    @FXML private Button button;
    @FXML private Label labelVersion;
    private final CheckOnlineVersion checkVersion = new CheckOnlineVersion();
    private StringProperty onlineVersion = new SimpleStringProperty();
    
    @FXML
    public void exit(){
        ((Stage)button.getScene().getWindow()).close();
    }
    
    @FXML
    public void downloadNew() throws URISyntaxException, IOException{
        Desktop.getDesktop().browse(new URL("https://hugoextrat.com/assets/download/snakegame.exe").toURI());
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onlineVersion = checkVersion.checkOnlineVersion();
        labelVersion.textProperty().bind(onlineVersion);
    }    
    
}
