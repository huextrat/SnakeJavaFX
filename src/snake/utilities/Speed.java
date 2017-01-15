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
package snake.utilities;

/**
 * Enumeration to set the available speed level
 * @author huextrat <extrat.h@gmail.com>
 */
public enum Speed {
    SLOW(0.16), MID(0.12), FAST(0.08), EXTREME(0.05), NOFIELD(0.10);
    private final double value;

    /**
     * Enumeration constructor
     * @param value 
     */
    private Speed(double value) {
        this.value = value;
    }

    /**
     * The method to get speed value
     * @return double
     */
    public double getValue() {
        return value;
    }
}
