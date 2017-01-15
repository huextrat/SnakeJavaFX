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

import java.util.Observable;
import snake.utilities.Direction;

/**
 * Class to notify Observer when the direction changed
 * @author huextrat <extrat.h@gmail.com>
 */
public class InputDevice extends Observable {

    /**
     * The method to notify Observer
     * @param snakeDirection 
     */
    public void changeDirection(Direction snakeDirection){
        setChanged();
        notifyObservers(snakeDirection);
    }
    
    /**
     * The method allowing to move the snake with keyboard keys
     */
    public void choiceKeyboard(){
        KeyboardHandler keyboard = new KeyboardHandler();
        keyboard.changeDirection();  
    }
    
    /**
     * The method allowing to move the snake with mouse
     */
    public void choiceMouse(){
        MouseHandler mouse = new MouseHandler();
        mouse.changeDirection();
    }
    
    /**
     * the method allowing to move the snake with LeapMotion
     */
    public void choiceLeapMotion(){
        LeapMotion leap = new LeapMotion();
        leap.changeDirection();
    }
}
