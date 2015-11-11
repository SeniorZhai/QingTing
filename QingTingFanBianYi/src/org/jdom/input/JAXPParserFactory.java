package org.jdom.input;

import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

class JAXPParserFactory
{
  private static final String CVS_ID = "@(#) $RCSfile: JAXPParserFactory.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
  private static final String JAXP_SCHEMA_LANGUAGE_PROPERTY = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  private static final String JAXP_SCHEMA_LOCATION_PROPERTY = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  public static XMLReader createParser(boolean paramBoolean, Map paramMap1, Map paramMap2)
    throws JDOMException
  {
    try
    {
      paramMap1 = SAXParserFactory.newInstance();
      paramMap1.setValidating(paramBoolean);
      paramMap1.setNamespaceAware(true);
      try
      {
        paramMap1 = paramMap1.newSAXParser();
        setProperty(paramMap1, paramMap2, "http://java.sun.com/xml/jaxp/properties/schemaLanguage");
        setProperty(paramMap1, paramMap2, "http://java.sun.com/xml/jaxp/properties/schemaSource");
        return paramMap1.getXMLReader();
      }
      catch (ParserConfigurationException paramMap1)
      {
        throw new JDOMException("Could not allocate JAXP SAX Parser", paramMap1);
      }
    }
    catch (SAXException paramMap1)
    {
    }
    throw new JDOMException("Could not allocate JAXP SAX Parser", paramMap1);
  }

  private static void setProperty(SAXParser paramSAXParser, Map paramMap, String paramString)
    throws JDOMException
  {
    try
    {
      if (paramMap.containsKey(paramString))
        paramSAXParser.setProperty(paramString, paramMap.get(paramString));
      return;
    }
    catch (SAXNotSupportedException paramMap)
    {
      throw new JDOMException(paramString + " property not supported for JAXP parser " + paramSAXParser.getClass().getName());
    }
    catch (SAXNotRecognizedException paramMap)
    {
    }
    throw new JDOMException(paramString + " property not recognized for JAXP parser " + paramSAXParser.getClass().getName());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.input.JAXPParserFactory
 * JD-Core Version:    0.6.2
 */