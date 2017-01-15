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

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static snake.utilities.Size.*;

/**
 * Class to create circles
 * @author huextrat <extrat.h@gmail.com>
 */
public class CircleShape extends FactoryCell{
    private Circle food;
    private Circle bomb;
    private Circle head;
    private Circle queue;

    /**
     * The method to create circle food
     * @return Shape
     */
    @Override
    public Shape createFood(){
        food = new Circle(10,10,8);
        food.setFill(foodColor.getValue());
        food.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        food.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return food;
    }
    
    /**
     * The method to move circle food
     * @return Shape
     */
    @Override
    public Shape transFood(){
        food.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        food.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return food;
    }
    
    /**
     * The method to create circle bomb
     * @return Shape
     */
    @Override
    public Shape createBomb(){
        bomb = new Circle(10,10,8);
        bomb.setFill(bombColor.getValue());
        bomb.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        bomb.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return bomb;
    }
    
    /**
     * The method to move circle bomb
     * @return Shape
     */
    @Override
    public Shape transBomb(){
        bomb.setTranslateX((int) (Math.random() * (WIDTH - PIXEL)) / PIXEL * PIXEL);
        bomb.setTranslateY((int) (Math.random() * (HEIGHT - PIXEL)) / PIXEL * PIXEL);
        return bomb;
    }
    
    /**
     * The method to create circle snake
     * @return Shape
     */
    @Override
    public Shape createSnake(){
        head = new Circle(10,10,8);
        head.setFill(snakeColor.getValue());
        head.setTranslateX(160);
        head.setTranslateY(20);
        return head;
    }
    
    /**
     * The method to create circle queue
     * @param queueX
     * @param queueY
     * @return Shape
     */
    @Override
    public Shape addQueue(double queueX, double queueY){
        queue = new Circle(10,10,8);
        queue.setFill(snakeColor.getValue());
        queue.setTranslateX(queueX);
        queue.setTranslateY(queueY);
        return queue;
    }
}
