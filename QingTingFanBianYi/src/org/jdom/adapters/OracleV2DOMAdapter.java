package org.jdom.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jdom.JDOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class OracleV2DOMAdapter extends AbstractDOMAdapter
{
  private static final String CVS_ID = "@(#) $RCSfile: OracleV2DOMAdapter.java,v $ $Revision: 1.19 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  static Class class$org$xml$sax$InputSource;

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  public Document createDocument()
    throws JDOMException
  {
    try
    {
      Document localDocument = (Document)Class.forName("oracle.xml.parser.v2.XMLDocument").newInstance();
      return localDocument;
    }
    catch (Exception localException)
    {
      throw new JDOMException(localException.getClass().getName() + ": " + localException.getMessage() + " when creating document", localException);
    }
  }

  public Document getDocument(InputStream paramInputStream, boolean paramBoolean)
    throws IOException, JDOMException
  {
    try
    {
      Class localClass2 = Class.forName("oracle.xml.parser.v2.DOMParser");
      Object localObject = localClass2.newInstance();
      Class localClass1;
      if (class$org$xml$sax$InputSource == null)
      {
        localClass1 = class$("org.xml.sax.InputSource");
        class$org$xml$sax$InputSource = localClass1;
      }
      while (true)
      {
        localClass2.getMethod("parse", new Class[] { localClass1 }).invoke(localObject, new Object[] { new InputSource(paramInputStream) });
        return (Document)localClass2.getMethod("getDocument", null).invoke(localObject, null);
        localClass1 = class$org$xml$sax$InputSource;
      }
    }
    catch (InvocationTargetException paramInputStream)
    {
      paramInputStream = paramInputStream.getTargetException();
      if ((paramInputStream instanceof SAXParseException))
      {
        paramInputStream = (SAXParseException)paramInputStream;
        throw new JDOMException("Error on line " + paramInputStream.getLineNumber() + " of XML document: " + paramInputStream.getMessage(), paramInputStream);
      }
      if ((paramInputStream instanceof IOException))
        throw ((IOException)paramInputStream);
      throw new JDOMException(paramInputStream.getMessage(), paramInputStream);
    }
    catch (Exception paramInputStream)
    {
    }
    throw new JDOMException(paramInputStream.getClass().getName() + ": " + paramInputStream.getMessage(), paramInputStream);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.adapters.OracleV2DOMAdapter
 * JD-Core Version:    0.6.2
 */