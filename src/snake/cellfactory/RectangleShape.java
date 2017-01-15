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
package snake.cellfactory;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import static snake.utilities.Size.*;

/**
 * Class to create rectangles
 * @author huextrat <extrat.h@gmail.com>
 */
public class RectangleShape extends FactoryCell{
    private Rectangle food;
    private Rectangle bomb;
    private Rectangle head;
    private Rectangle queue;
    
    /**
     * The method to create rectangle food
     * @return Shape
     */
    @Override
    public Shape createFood(){
        food = new Rectangle(20,20);
        food.setFill(foodColor.getValue());
        food.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        food.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return food;
    }
    
    /**
     * The method to move rectangle food
     * @return Shape
     */
    @Override
    public Shape transFood(){
        food.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        food.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return food;
    }
    
    /**
     * The method to create rectangle bomb
     * @return Shape
     */
    @Override
    public Shape createBomb(){
        bomb = new Rectangle(20,20);
        bomb.setFill(bombColor.getValue());
        bomb.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        bomb.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return bomb;
    }
    
    /**
     * The method to move rectangle bomb
     * @return Shape
     */
    @Override
    public Shape transBomb(){
        bomb.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        bomb.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return bomb;
    }
    
    /**
     * The method to create rectangle snake
     * @return Shape
     */
    @Override
    public Shape createSnake(){
        head = new Rectangle(20,20);
        head.setFill(snakeColor.getValue());
        head.setTranslateX(160);
        head.setTranslateY(20);
        return head;
    }
    
    /**
     * The method to create rectangle queue
     * @param queueX
     * @param queueY
     * @return Shape
     */
    @Override
    public Shape addQueue(double queueX, double queueY){
        queue = new Rectangle(20,20);
        queue.setFill(snakeColor.getValue());
        queue.setTranslateX(queueX);
        queue.setTranslateY(queueY);
        return queue;
    }
}
