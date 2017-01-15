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

import static snake.play.Snake.BOARD;
import static snake.utilities.Size.HEIGHT;
import static snake.utilities.Size.PIXEL;
import static snake.utilities.Size.WIDTH;
import static snake.view.menu.MenuController.speedText;

/**
 * Class to detect collision
 * @author huextrat <extrat.h@gmail.com>
 */
public class CollisionManager {
    private final SnakeCollider snakeCollider = new SnakeCollider();
    
    /**
     * The method to detect collision
     */
    public void snakeCollision() {
        if(testSnakeCollision()){
            snakeCollider.snakeCollider();
        }
        if(speedText.getValue().equals("Extreme")){
            bombFoodCollision();
            if(testBombCollision()){
                snakeCollider.snakeCollider();
            }
        }
        if(!speedText.getValue().equals("SansBord")){
            if(testWallCollision()){
                snakeCollider.snakeCollider();
            }
        }
        if(testWin()){
            snakeCollider.snakeCollider();
        }
    }
    
    /**
     * The method to detect collisions with himself
     * @return 
     */
    private boolean testSnakeCollision(){
        return BOARD.snake.stream().anyMatch((cir) -> (cir != BOARD.queue && BOARD.queue.getTranslateX() == cir.getTranslateX() && BOARD.queue.getTranslateY() == cir.getTranslateY()));
    }
    
    /**
     * The method to detect bomb collisions
     * @return 
     */
    private boolean testBombCollision(){
        return BOARD.queue.getTranslateX() == BOARD.bomb.getTranslateX() && BOARD.queue.getTranslateY() == BOARD.bomb.getTranslateY();
    }
    
    /**
     * The method to detect wall collisions
     * @return 
     */
    private boolean testWallCollision(){
        return BOARD.queue.getTranslateX() < 0 || BOARD.queue.getTranslateX() >= WIDTH || BOARD.queue.getTranslateY() < 0 || BOARD.queue.getTranslateY() >= HEIGHT;
    }
    
    /**
     * The method to detect if player won the game
     * @return 
     */
    private boolean testWin(){
        if(BOARD.getScores().getValue()==PIXEL*PIXEL){
            BOARD.getWinLab().setVisible(true);
            return true;
        }
        return false;
    }
    
    /**
     * The method to detect if bomb pop into food or vice versa
     */
    private void bombFoodCollision(){
        if(BOARD.bomb.getTranslateX() == BOARD.food.getTranslateX() && BOARD.bomb.getTranslateY() == BOARD.food.getTranslateY()){
            BOARD.food = BOARD.CELLFACT.transFood();
            BOARD.bomb = BOARD.CELLFACT.transBomb();
        }   
    }
}
