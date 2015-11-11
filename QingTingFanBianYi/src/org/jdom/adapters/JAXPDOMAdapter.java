package org.jdom.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jdom.JDOMException;
import org.jdom.input.BuilderErrorHandler;
import org.w3c.dom.Document;

public class JAXPDOMAdapter extends AbstractDOMAdapter
{
  private static final String CVS_ID = "@(#) $RCSfile: JAXPDOMAdapter.java,v $ $Revision: 1.13 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  static Class class$java$io$InputStream;
  static Class class$org$xml$sax$ErrorHandler;

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
      Class.forName("javax.xml.transform.Transformer");
      Object localObject1 = Class.forName("javax.xml.parsers.DocumentBuilderFactory");
      Object localObject2 = ((Class)localObject1).getMethod("newInstance", null).invoke(null, null);
      localObject1 = ((Class)localObject1).getMethod("newDocumentBuilder", null).invoke(localObject2, null);
      localObject1 = (Document)localObject1.getClass().getMethod("newDocument", null).invoke(localObject1, null);
      return localObject1;
    }
    catch (Exception localException)
    {
      throw new JDOMException("Reflection failed while creating new JAXP document", localException);
    }
  }

  public Document getDocument(InputStream paramInputStream, boolean paramBoolean)
    throws IOException, JDOMException
  {
    try
    {
      Class.forName("javax.xml.transform.Transformer");
      Class localClass1 = Class.forName("javax.xml.parsers.DocumentBuilderFactory");
      Object localObject = localClass1.getMethod("newInstance", null).invoke(null, null);
      localClass1.getMethod("setValidating", new Class[] { Boolean.TYPE }).invoke(localObject, new Object[] { new Boolean(paramBoolean) });
      localClass1.getMethod("setNamespaceAware", new Class[] { Boolean.TYPE }).invoke(localObject, new Object[] { Boolean.TRUE });
      localObject = localClass1.getMethod("newDocumentBuilder", null).invoke(localObject, null);
      Class localClass2 = localObject.getClass();
      if (class$org$xml$sax$ErrorHandler == null)
      {
        localClass1 = class$("org.xml.sax.ErrorHandler");
        class$org$xml$sax$ErrorHandler = localClass1;
        localClass2.getMethod("setErrorHandler", new Class[] { localClass1 }).invoke(localObject, new Object[] { new BuilderErrorHandler() });
        if (class$java$io$InputStream != null)
          break label223;
        localClass1 = class$("java.io.InputStream");
        class$java$io$InputStream = localClass1;
      }
      while (true)
      {
        return (Document)localClass2.getMethod("parse", new Class[] { localClass1 }).invoke(localObject, new Object[] { paramInputStream });
        localClass1 = class$org$xml$sax$ErrorHandler;
        break;
        label223: localClass1 = class$java$io$InputStream;
      }
    }
    catch (InvocationTargetException paramInputStream)
    {
      paramInputStream = paramInputStream.getTargetException();
      if ((paramInputStream instanceof IOException))
        throw ((IOException)paramInputStream);
      throw new JDOMException(paramInputStream.getMessage(), paramInputStream);
    }
    catch (Exception paramInputStream)
    {
    }
    throw new JDOMException("Reflection failed while parsing a document with JAXP", paramInputStream);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.adapters.JAXPDOMAdapter
 * JD-Core Version:    0.6.2
 */