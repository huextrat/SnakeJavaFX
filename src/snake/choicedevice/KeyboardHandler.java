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
package snake.choicedevice;

import static snake.play.Snake.BOARD;
import static snake.play.Snake.TIMELINE;
import static snake.play.StartSnake.device;
import static snake.play.StartSnake.scene;
import snake.utilities.Direction;

/**
 * Class to move the snake with keyboard
 * @author huextrat <extrat.h@gmail.com>
 */
public class KeyboardHandler extends InputDevice {
    /**
     * Listener method to detect keyboard events
     */
    public void changeDirection(){
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                case Z:
                    if(BOARD.getDirection() != Direction.DOWN){
                        device.changeDirection(Direction.UP);
                        BOARD.setDirection(Direction.UP);
                    }
                    break;
                case DOWN:
                case S:
                    if(BOARD.getDirection() != Direction.UP){
                        device.changeDirection(Direction.DOWN);
                        BOARD.setDirection(Direction.DOWN);
                    }
                    break;
                case LEFT:
                case Q:
                    if(BOARD.getDirection() != Direction.RIGHT){
                        device.changeDirection(Direction.LEFT);
                        BOARD.setDirection(Direction.LEFT);
                    }
                    break;
                case RIGHT:
                case D:
                    if(BOARD.getDirection() != Direction.LEFT){
                        device.changeDirection(Direction.RIGHT);
                        BOARD.setDirection(Direction.RIGHT);
                    }
                    break;
                case SPACE:
                    TIMELINE.pause();
                    BOARD.getPauseLab().setVisible(true);
                    scene.setOnKeyPressed(event1 -> {
                        switch(event1.getCode()){
                            case SPACE:
                                BOARD.getWinLab().setVisible(false);
                                BOARD.getPauseLab().setVisible(false);
                                TIMELINE.play();
                                changeDirection();
                                break;
                        }
                    });
            }
        });     
    }
}
