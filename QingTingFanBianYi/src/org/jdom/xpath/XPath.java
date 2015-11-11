package org.jdom.xpath;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import org.jdom.JDOMException;
import org.jdom.Namespace;

public abstract class XPath
  implements Serializable
{
  private static final String CVS_ID = "@(#) $RCSfile: XPath.java,v $ $Revision: 1.17 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
  private static final String DEFAULT_XPATH_CLASS = "org.jdom.xpath.JaxenXPath";
  public static final String JDOM_OBJECT_MODEL_URI = "http://jdom.org/jaxp/xpath/jdom";
  private static final String XPATH_CLASS_PROPERTY = "org.jdom.xpath.class";
  static Class class$java$lang$String;
  static Class class$org$jdom$xpath$XPath;
  private static Constructor constructor = null;

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

  public static XPath newInstance(String paramString)
    throws JDOMException
  {
    try
    {
      Object localObject = constructor;
      if (localObject == null);
      try
      {
        localObject = System.getProperty("org.jdom.xpath.class", "org.jdom.xpath.JaxenXPath");
        setXPathClass(Class.forName((String)localObject));
        return (XPath)constructor.newInstance(new Object[] { paramString });
      }
      catch (SecurityException localSecurityException)
      {
        while (true)
          String str = "org.jdom.xpath.JaxenXPath";
      }
    }
    catch (JDOMException paramString)
    {
      throw paramString;
    }
    catch (InvocationTargetException paramString)
    {
      paramString = paramString.getTargetException();
      if ((paramString instanceof JDOMException));
      for (paramString = (JDOMException)paramString; ; paramString = new JDOMException(paramString.toString(), paramString))
        throw paramString;
    }
    catch (Exception paramString)
    {
    }
    throw new JDOMException(paramString.toString(), paramString);
  }

  public static List selectNodes(Object paramObject, String paramString)
    throws JDOMException
  {
    return newInstance(paramString).selectNodes(paramObject);
  }

  public static Object selectSingleNode(Object paramObject, String paramString)
    throws JDOMException
  {
    return newInstance(paramString).selectSingleNode(paramObject);
  }

  public static void setXPathClass(Class paramClass)
    throws JDOMException
  {
    if (paramClass == null)
      throw new IllegalArgumentException("aClass");
    try
    {
      Class localClass;
      if (class$org$jdom$xpath$XPath == null)
      {
        localClass = class$("org.jdom.xpath.XPath");
        class$org$jdom$xpath$XPath = localClass;
        if ((!localClass.isAssignableFrom(paramClass)) || (Modifier.isAbstract(paramClass.getModifiers())))
          break label94;
        if (class$java$lang$String != null)
          break label87;
        localClass = class$("java.lang.String");
        class$java$lang$String = localClass;
      }
      while (true)
      {
        constructor = paramClass.getConstructor(new Class[] { localClass });
        return;
        localClass = class$org$jdom$xpath$XPath;
        break;
        label87: localClass = class$java$lang$String;
      }
      label94: throw new JDOMException(paramClass.getName() + " is not a concrete JDOM XPath implementation");
    }
    catch (JDOMException paramClass)
    {
      throw paramClass;
    }
    catch (Exception paramClass)
    {
    }
    throw new JDOMException(paramClass.toString(), paramClass);
  }

  public void addNamespace(String paramString1, String paramString2)
  {
    addNamespace(Namespace.getNamespace(paramString1, paramString2));
  }

  public abstract void addNamespace(Namespace paramNamespace);

  public abstract String getXPath();

  public abstract Number numberValueOf(Object paramObject)
    throws JDOMException;

  public abstract List selectNodes(Object paramObject)
    throws JDOMException;

  public abstract Object selectSingleNode(Object paramObject)
    throws JDOMException;

  public abstract void setVariable(String paramString, Object paramObject);

  public abstract String valueOf(Object paramObject)
    throws JDOMException;

  protected final Object writeReplace()
    throws ObjectStreamException
  {
    return new XPathString(getXPath());
  }

  private static final class XPathString
    implements Serializable
  {
    private String xPath = null;

    public XPathString(String paramString)
    {
      this.xPath = paramString;
    }

    private Object readResolve()
      throws ObjectStreamException
    {
      try
      {
        XPath localXPath = XPath.newInstance(this.xPath);
        return localXPath;
      }
      catch (JDOMException localJDOMException)
      {
        throw new InvalidObjectException("Can't create XPath object for expression \"" + this.xPath + "\": " + localJDOMException.toString());
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.xpath.XPath
 * JD-Core Version:    0.6.2
 */