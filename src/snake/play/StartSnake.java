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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import snake.choicedevice.InputDevice;
import static snake.play.Snake.BOARD;
import static snake.play.Snake.TIMELINE;
import snake.utilities.Direction;

/**
 * The first class called when player press play button
 * @author huextrat <extrat.h@gmail.com>
 */
public class StartSnake {
    public Snake snake;
    public static InputDevice device;
    public static Scene scene;
    
    /**
     * The method instantiate Snake, InputDevice, Scene and we start the game
     * @param snakeStage 
     */
    public void startSnake(Stage snakeStage) {
        snake = new Snake();
        scene = new Scene(snake.snakePlayer(),550, 443);
        device = new InputDevice();
        device.choiceKeyboard();
        device.addObserver(snake);
        snakeStage.setResizable(false);
        snakeStage.setScene(scene);
        snakeStage.show();
        startGame();
    }
    
    /**
     * The method to start game and automatic pause at start
     */
    public void startGame() {
        try {
            Robot robot;
            robot = new Robot();
            BOARD.setDirection(Direction.RIGHT);
            BOARD.head = BOARD.CELLFACT.createSnake();
            BOARD.snake.add(BOARD.head);
            BOARD.getPauseLab().setVisible(false);
            TIMELINE.play();
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
        } catch (AWTException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
