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

public class XML4JDOMAdapter extends AbstractDOMAdapter
{
  private static final String CVS_ID = "@(#) $RCSfile: XML4JDOMAdapter.java,v $ $Revision: 1.18 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
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
      throw new JDOMException(localException.getClass().getName() + ": " + localException.getMessage() + " while creating document", localException);
    }
  }

  public Document getDocument(InputStream paramInputStream, boolean paramBoolean)
    throws IOException, JDOMException
  {
    try
    {
      Class localClass = Class.forName("org.apache.xerces.parsers.DOMParser");
      Object localObject2 = localClass.newInstance();
      Object localObject1;
      if (class$java$lang$String == null)
      {
        localObject1 = class$("java.lang.String");
        class$java$lang$String = (Class)localObject1;
        localObject1 = localClass.getMethod("setFeature", new Class[] { localObject1, Boolean.TYPE });
        ((Method)localObject1).invoke(localObject2, new Object[] { "http://xml.org/sax/features/validation", new Boolean(paramBoolean) });
        ((Method)localObject1).invoke(localObject2, new Object[] { "http://xml.org/sax/features/namespaces", new Boolean(false) });
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
 * Qualified Name:     org.jdom.adapters.XML4JDOMAdapter
 * JD-Core Version:    0.6.2
 */