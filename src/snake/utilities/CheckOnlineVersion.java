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

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The class to detect the number of online version
 * @author huextrat <extrat.h@gmail.com>
 */
public class CheckOnlineVersion {
    private final StringProperty onlineVersion = new SimpleStringProperty();
    /**
     * The method to find online version XML parser
     * @return StringProperty
     */
    public StringProperty checkOnlineVersion() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                boolean bfversion = false;
                @Override
                public void startElement(String uri, String localName,String qName,Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("numero")) {
			bfversion = true;
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bfversion) {
                        onlineVersion.setValue(new String(ch, start, length));
			bfversion = false;
                    }
                }
            };
            saxParser.parse("https://hugoextrat.com/assets/download/version.xml", handler);
            return onlineVersion;
        } catch (IOException | ParserConfigurationException | SAXException e) {
        }
        return null;
   }

}