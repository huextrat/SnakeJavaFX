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

import snake.view.menu.MenuController;

/**
 * Abstract class to set database infos
 * @author huextrat <extrat.h@gmail.com>
 */
public abstract class DBInfo extends MenuController {
    private final String MYDRIVER = "com.mysql.jdbc.Driver";
    public String getMyDriver(){return MYDRIVER;}
    private final String MYURL = "jdbc:mysql://";
    public String getMyUrl(){return MYURL;}
    private final String DBUSER = "";
    public String getDBUser(){return DBUSER;}
    private final String DBPASS = "";
    public String getDBPass(){return DBPASS;}
}
