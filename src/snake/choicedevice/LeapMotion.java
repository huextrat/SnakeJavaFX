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

import com.leapmotion.leap.*;
import java.util.Iterator;
import javafx.application.Platform;
import static snake.play.Snake.BOARD;
import static snake.play.StartSnake.device;
import snake.utilities.Direction;

/**
 * Class to move the snake with LeapMotion
 * @author huextrat <extrat.h@gmail.com>
 */
public class LeapMotion extends InputDevice {
    /**
     * The method to initialize LeapMotion
     * @param controller 
     */
    void onInit(final Controller controller){
        System.out.println("OK");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    /**
     * The method to detect swipe events
     * @param controller 
     */
    void onFrame(final Controller controller)
    {
        Frame frame = controller.frame();
        HandList handList = frame.hands();
        Iterator<Hand> handIterator = handList.iterator();
        while (handIterator.hasNext()) {
            Hand hand = handIterator.next();
            if (hand.isValid() && (hand.fingers().count() > 2)) {
                Vector palmPosition = hand.palmPosition();
                final float x = palmPosition.getX();
                final float y = palmPosition.getY();
                Platform.runLater(() -> {
                    if (Math.abs(x) > 10) {
                        if (BOARD.getDirection() != Direction.LEFT){
                            device.changeDirection(Direction.RIGHT);
                            BOARD.setDirection(Direction.RIGHT);
                        }
                    }
                    if (Math.abs(x) < 10) {
                        if (BOARD.getDirection() != Direction.RIGHT){
                            device.changeDirection(Direction.LEFT);
                            BOARD.setDirection(Direction.LEFT);
                        }
                    }
                    if (Math.abs(y) < 10) {
                        if (BOARD.getDirection() != Direction.UP){
                            device.changeDirection(Direction.DOWN);
                            BOARD.setDirection(Direction.DOWN);
                        }
                    }
                    if (Math.abs(y) > 10) {
                        if (BOARD.getDirection() != Direction.DOWN){
                            device.changeDirection(Direction.UP);
                            BOARD.setDirection(Direction.UP);
                        }
                    }
                });
            }
        }
    }
    
    public void changeDirection(){
        System.out.println("OkLeapMotion");
    }
}