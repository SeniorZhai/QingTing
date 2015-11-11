package org.jdom.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jdom.JDOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

public class CrimsonDOMAdapter extends AbstractDOMAdapter
{
  private static final String CVS_ID = "@(#) $RCSfile: CrimsonDOMAdapter.java,v $ $Revision: 1.17 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";

  public Document createDocument()
    throws JDOMException
  {
    try
    {
      Document localDocument = (Document)Class.forName("org.apache.crimson.tree.XmlDocument").newInstance();
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
      Class localClass1 = Class.forName("java.io.InputStream");
      Class localClass2 = Boolean.TYPE;
      Boolean localBoolean = new Boolean(false);
      paramInputStream = (Document)Class.forName("org.apache.crimson.tree.XmlDocument").getMethod("createXmlDocument", new Class[] { localClass1, localClass2 }).invoke(null, new Object[] { paramInputStream, localBoolean });
      return paramInputStream;
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
 * Qualified Name:     org.jdom.adapters.CrimsonDOMAdapter
 * JD-Core Version:    0.6.2
 */