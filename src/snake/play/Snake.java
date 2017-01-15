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

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import snake.utilities.Direction;
import static snake.utilities.Size.*;

/**
 * Class to manage the time and update the direction of the snake
 * @author huextrat <extrat.h@gmail.com>
 */
public class Snake implements Observer {
    private SnakeNoField noField;
    private SnakeDirection snakeDirection;
    private final SnakeEat snakeEat = new SnakeEat();
    public static Timeline TIMELINE;
    public static Board BOARD;
    private final CollisionManager collision = new CollisionManager();
    
    /**
     * The method to manage the time and to execute tests
     * @return Pane
     */
    protected Pane snakePlayer() {
        try {
            snakeDirection = new SnakeDirection();
            TIMELINE = new Timeline();
            BOARD = new Board();
            if(speedText.getValue().equals("SansBord")){
                noField = new SnakeNoField();
            }
            
            Parent root1;
            root1 = FXMLLoader.load(getClass().getResource("/view/game/Snake.fxml"));
            Pane root2 = (Pane) root1.lookup("#pane");
            Pane root = (Pane) root2.lookup("#paneSnake");
            root.setPrefSize(WIDTH, HEIGHT);
            Group snakeBody = new Group();
            
            BOARD.setBoard(root1);
            BOARD.snake = snakeBody.getChildren();

            TIMELINE.pause();
            KeyFrame frame = new KeyFrame(Duration.seconds(BOARD.speed), event -> {
                boolean bigSnake = BOARD.snake.size() > 1;
                if(!bigSnake){
                    BOARD.queue = (Shape)BOARD.snake.get(0);
                }
                else { 
                    BOARD.queue = (Shape)BOARD.snake.remove(BOARD.snake.size() - 1); 
                }
                
                double queueX = BOARD.queue.getTranslateX();
                double queueY = BOARD.queue.getTranslateY();
                
                snakeDirection.changeDirection(BOARD.getDirection());
                
                if (bigSnake){
                    BOARD.snake.add(0, BOARD.queue);
                }
                
                snakeEat.snakeEat(queueX, queueY);
                if(speedText.getValue().equals("SansBord")){
                    noField.noField();
                }
                collision.snakeCollision();
            });
            TIMELINE.getKeyFrames().add(frame);
            TIMELINE.setCycleCount(Timeline.INDEFINITE);

            if(speedText.getValue().equals("Extreme")){
                root.getChildren().addAll(BOARD.food, BOARD.bomb, snakeBody);
            }
            else { root.getChildren().addAll(BOARD.food, snakeBody); }
        
            return  root2;
        } catch (IOException e) {
            System.exit(0);
        }
        return new Pane();
    }
    
    /**
     * The method to update direction
     * @param o
     * @param o1 
     */
    @Override
    public void update(Observable o, Object o1) {
        BOARD.setDirection((Direction)o1);
    }
}

