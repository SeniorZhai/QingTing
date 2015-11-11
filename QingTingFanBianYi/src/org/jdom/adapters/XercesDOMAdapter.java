package org.jdom.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jdom.JDOMException;
import org.jdom.input.BuilderErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class XercesDOMAdapter extends AbstractDOMAdapter
{
  private static final String CVS_ID = "@(#) $RCSfile: XercesDOMAdapter.java,v $ $Revision: 1.19 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  static Class class$java$lang$String;
  static Class class$org$xml$sax$ErrorHandler;
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
      Document localDocument = (Document)Class.forName("org.apache.xerces.dom.DocumentImpl").newInstance();
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
      Class localClass = Class.forName("org.apache.xerces.parsers.DOMParser");
      Object localObject2 = localClass.newInstance();
      if (class$java$lang$String == null)
      {
        localObject1 = class$("java.lang.String");
        class$java$lang$String = (Class)localObject1;
        localObject1 = localClass.getMethod("setFeature", new Class[] { localObject1, Boolean.TYPE });
        ((Method)localObject1).invoke(localObject2, new Object[] { "http://xml.org/sax/features/validation", new Boolean(paramBoolean) });
        ((Method)localObject1).invoke(localObject2, new Object[] { "http://xml.org/sax/features/namespaces", new Boolean(true) });
        if (paramBoolean)
        {
          if (class$org$xml$sax$ErrorHandler != null)
            break label238;
          localObject1 = class$("org.xml.sax.ErrorHandler");
          class$org$xml$sax$ErrorHandler = (Class)localObject1;
          label126: localClass.getMethod("setErrorHandler", new Class[] { localObject1 }).invoke(localObject2, new Object[] { new BuilderErrorHandler() });
        }
        if (class$org$xml$sax$InputSource != null)
          break label245;
        localObject1 = class$("org.xml.sax.InputSource");
        class$org$xml$sax$InputSource = (Class)localObject1;
      }
      while (true)
      {
        localClass.getMethod("parse", new Class[] { localObject1 }).invoke(localObject2, new Object[] { new InputSource(paramInputStream) });
        return (Document)localClass.getMethod("getDocument", null).invoke(localObject2, null);
        localObject1 = class$java$lang$String;
        break;
        label238: localObject1 = class$org$xml$sax$ErrorHandler;
        break label126;
        label245: localObject1 = class$org$xml$sax$InputSource;
      }
    }
    catch (InvocationTargetException paramInputStream)
    {
      Object localObject1 = paramInputStream.getTargetException();
      if ((localObject1 instanceof SAXParseException))
      {
        localObject1 = (SAXParseException)localObject1;
        throw new JDOMException("Error on line " + ((SAXParseException)localObject1).getLineNumber() + " of XML document: " + ((SAXParseException)localObject1).getMessage(), paramInputStream);
      }
      if ((localObject1 instanceof IOException))
        throw ((IOException)localObject1);
      throw new JDOMException(((Throwable)localObject1).getMessage(), paramInputStream);
    }
    catch (Exception paramInputStream)
    {
    }
    throw new JDOMException(paramInputStream.getClass().getName() + ": " + paramInputStream.getMessage(), paramInputStream);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.adapters.XercesDOMAdapter
 * JD-Core Version:    0.6.2
 */