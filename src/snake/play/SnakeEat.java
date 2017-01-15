/*
 * Copyright (C) 2017 huextrat <extrat.h@gmail.com>.
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

import java.util.ListIterator;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import static snake.play.Snake.BOARD;

/**
 * The class to find if snake eat food
 * @author huextrat <extrat.h@gmail.com>
 */
public class SnakeEat {
    /**
     * The method to detect if snake eat and if he has eaten we increment the score
     * @param queueX
     * @param queueY 
     */
    public void snakeEat(double queueX, double queueY){
        if (BOARD.queue.getTranslateX() == BOARD.food.getTranslateX() && BOARD.queue.getTranslateY() == BOARD.food.getTranslateY()){
            ListIterator<Node> snakeIt = BOARD.snake.listIterator();
            while(snakeIt.hasNext()){
                Node posSnake = snakeIt.next();
                if(posSnake.getTranslateX() == BOARD.food.getTranslateX() && posSnake.getTranslateY() == BOARD.food.getTranslateY() || posSnake.getTranslateX() == BOARD.bomb.getTranslateX() && posSnake.getTranslateY() == BOARD.bomb.getTranslateY()){
                    BOARD.food = BOARD.CELLFACT.transFood();
                    BOARD.bomb = BOARD.CELLFACT.transBomb();
                    while(snakeIt.hasPrevious()){
                        snakeIt.previous();
                    }
                }
            }
            Shape cir = BOARD.CELLFACT.addQueue(queueX,queueY);
            BOARD.getScores().setValue(BOARD.getScores().getValue()+10);
            BOARD.snake.add(cir);
        }
    }
}
