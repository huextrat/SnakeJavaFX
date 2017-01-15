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
import static snake.utilities.Size.HEIGHT;
import static snake.utilities.Size.PIXEL;
import static snake.utilities.Size.WIDTH;

/**
 * The class to define what is NoField game
 * @author huextrat <extrat.h@gmail.com>
 */
public class SnakeNoField {
    /**
     * The method to define NoField level
     */
    public void noField(){
        if(BOARD.queue.getTranslateX() < 0)
            BOARD.queue.setTranslateX(WIDTH-PIXEL);
        if(BOARD.queue.getTranslateX() >= WIDTH)
            BOARD.queue.setTranslateX(0);
        if(BOARD.queue.getTranslateY() < 0)
            BOARD.queue.setTranslateY(HEIGHT-PIXEL);
        if(BOARD.queue.getTranslateY() >= HEIGHT)
            BOARD.queue.setTranslateY(0);
    }
}
