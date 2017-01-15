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
package snake.play;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.shape.Shape;
import javafx.util.converter.NumberStringConverter;
import snake.cellfactory.FactoryCell;
import snake.utilities.Direction;
import static snake.view.menu.MenuController.getSpeed;
import static snake.view.menu.MenuController.speedText;

/**
 * Class to set the board
 * @author huextrat <extrat.h@gmail.com>
 */
public class Board {
    private Label pauseLab;
    public Label getPauseLab(){return pauseLab;}
    private Label winLab;
    public Label getWinLab(){return winLab;}
    private Label scoresLab;
    private Label bestLab;
    private Label speedLab;
    
    protected FactoryCell CELLFACT = FactoryCell.shape("Circle");
    protected Shape food = CELLFACT.createFood();
    protected Shape bomb = CELLFACT.createBomb();
    protected Shape head;
    protected Shape queue;
    
    private final IntegerProperty scores = new SimpleIntegerProperty();
    public IntegerProperty getScores(){return scores;}
    
    private final IntegerProperty bestScore = new SimpleIntegerProperty();
    public IntegerProperty getBestScore(){return bestScore;}
    
    protected final double speed = getSpeed();
    
    private Direction direction = Direction.RIGHT;
    public void setDirection(Direction direction){this.direction=direction;}
    public Direction getDirection(){return direction;}
    
    protected ObservableList<Node> snake;
    
    /**
     * The method to set graphical board
     * @param root1 
     */
    void setBoard(Parent root1) {
        food = CELLFACT.transFood();
        bomb = CELLFACT.transBomb();

        scoresLab = (Label) root1.lookup("#scoresLab");
        scores.setValue(0);
        scoresLab.textProperty().bindBidirectional(scores, new NumberStringConverter());
        bestLab = (Label) root1.lookup("#bestLab");
        bestScore.setValue(0);
        bestLab.textProperty().bindBidirectional(bestScore, new NumberStringConverter());
        speedLab = (Label) root1.lookup("#speedLab");
        speedLab.textProperty().bindBidirectional(speedText);
        pauseLab = (Label) root1.lookup("#pauseLab");
        winLab = (Label) root1.lookup("#winLab");
        winLab.setVisible(false);
    }
}
