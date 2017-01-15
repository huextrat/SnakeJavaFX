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

/**
 * Class to define what to do after a collision
 * @author huextrat <extrat.h@gmail.com>
 */
public class SnakeCollider {
    private final StartSnake startSnake = new StartSnake();
    /**
     * The method who is called when we got a collision
     */
    public void snakeCollider(){
        if(BOARD.getScores().getValue() >= BOARD.getBestScore().getValue()){
            BOARD.getBestScore().setValue(BOARD.getScores().getValue());
        }
        BOARD.getScores().setValue(0);
        BOARD.food = BOARD.CELLFACT.transFood();
        BOARD.bomb = BOARD.CELLFACT.transBomb();
        BOARD.snake.clear();
        BOARD.setDirection(Direction.RIGHT);
        startSnake.startGame();
    }
}
