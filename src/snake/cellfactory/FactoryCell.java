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
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Abstract class to create shapes
 * @author huextrat <extrat.h@gmail.com>
 */
public abstract class FactoryCell {
    public static ColorPicker snakeColor = new ColorPicker();
    public ColorPicker bombColor = new ColorPicker(Color.RED);
    public ColorPicker foodColor = new ColorPicker(Color.web("#3498db"));

    /**
     * The method for the selection of the shape
     * @param myShape
     * @return FactoryCell
     */
    public static FactoryCell shape(String myShape)
    {
        switch(myShape){
            case "Rectangle":
                return(new RectangleShape());
            case "Circle":
                return(new CircleShape());
            default:
                return(new CircleShape());
        }
    }
    
    public abstract Shape createFood();
    public abstract Shape createBomb();
    public abstract Shape transFood();
    public abstract Shape transBomb();
    public abstract Shape createSnake();
    public abstract Shape addQueue(double queueX, double queueY);
}
