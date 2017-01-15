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

import static snake.play.Snake.BOARD;
import snake.utilities.Direction;
import static snake.utilities.Size.PIXEL;

/**
 * Class to modify the direction of the snake
 * @author huextrat <extrat.h@gmail.com>
 */
public class SnakeDirection {
    /**
     * The method to change the direction of the snake according to the new player direction
     * @param newDirection 
     */
    public void changeDirection(Direction newDirection){
        switch (newDirection){
            case UP:
                BOARD.queue.setTranslateX(BOARD.snake.get(0).getTranslateX());
                BOARD.queue.setTranslateY(BOARD.snake.get(0).getTranslateY() - PIXEL);
                break;
            case DOWN:
                BOARD.queue.setTranslateX(BOARD.snake.get(0).getTranslateX());
                BOARD.queue.setTranslateY(BOARD.snake.get(0).getTranslateY() + PIXEL);
                break;
            case LEFT:
                BOARD.queue.setTranslateX(BOARD.snake.get(0).getTranslateX() - PIXEL);
                BOARD.queue.setTranslateY(BOARD.snake.get(0).getTranslateY());
                break;
            case RIGHT:
                BOARD.queue.setTranslateX(BOARD.snake.get(0).getTranslateX() + PIXEL);
                BOARD.queue.setTranslateY(BOARD.snake.get(0).getTranslateY());
                break;
        }
    }
}
