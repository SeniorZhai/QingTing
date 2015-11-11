package org.jdom.xpath;

import java.util.List;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.SimpleVariableContext;
import org.jaxen.VariableContext;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

class JaxenXPath extends XPath
{
  private static final String CVS_ID = "@(#) $RCSfile: JaxenXPath.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
  private Object currentContext;
  private transient JDOMXPath xPath;

  public JaxenXPath(String paramString)
    throws JDOMException
  {
    setXPath(paramString);
  }

  private void setXPath(String paramString)
    throws JDOMException
  {
    try
    {
      this.xPath = new JDOMXPath(paramString);
      this.xPath.setNamespaceContext(new NSContext());
      return;
    }
    catch (Exception localException)
    {
      throw new JDOMException("Invalid XPath expression: \"" + paramString + "\"", localException);
    }
  }

  public void addNamespace(Namespace paramNamespace)
  {
    try
    {
      this.xPath.addNamespace(paramNamespace.getPrefix(), paramNamespace.getURI());
      return;
    }
    catch (JaxenException paramNamespace)
    {
    }
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof JaxenXPath))
    {
      JaxenXPath localJaxenXPath = (JaxenXPath)paramObject;
      bool1 = bool2;
      if (super.equals(paramObject))
      {
        bool1 = bool2;
        if (this.xPath.toString().equals(localJaxenXPath.xPath.toString()))
          bool1 = true;
      }
    }
    return bool1;
  }

  public String getXPath()
  {
    return this.xPath.toString();
  }

  public int hashCode()
  {
    return this.xPath.hashCode();
  }

  public Number numberValueOf(Object paramObject)
    throws JDOMException
  {
    try
    {
      this.currentContext = paramObject;
      paramObject = this.xPath.numberValueOf(paramObject);
      return paramObject;
    }
    catch (JaxenException paramObject)
    {
      throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + paramObject.getMessage(), paramObject);
    }
    finally
    {
      this.currentContext = null;
    }
    throw paramObject;
  }

  public List selectNodes(Object paramObject)
    throws JDOMException
  {
    try
    {
      this.currentContext = paramObject;
      paramObject = this.xPath.selectNodes(paramObject);
      return paramObject;
    }
    catch (JaxenException paramObject)
    {
      throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + paramObject.getMessage(), paramObject);
    }
    finally
    {
      this.currentContext = null;
    }
    throw paramObject;
  }

  public Object selectSingleNode(Object paramObject)
    throws JDOMException
  {
    try
    {
      this.currentContext = paramObject;
      paramObject = this.xPath.selectSingleNode(paramObject);
      return paramObject;
    }
    catch (JaxenException paramObject)
    {
      throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + paramObject.getMessage(), paramObject);
    }
    finally
    {
      this.currentContext = null;
    }
    throw paramObject;
  }

  public void setVariable(String paramString, Object paramObject)
    throws IllegalArgumentException
  {
    VariableContext localVariableContext = this.xPath.getVariableContext();
    if ((localVariableContext instanceof SimpleVariableContext))
      ((SimpleVariableContext)localVariableContext).setVariableValue(null, paramString, paramObject);
  }

  public String toString()
  {
    return this.xPath.toString();
  }

  public String valueOf(Object paramObject)
    throws JDOMException
  {
    try
    {
      this.currentContext = paramObject;
      paramObject = this.xPath.stringValueOf(paramObject);
      return paramObject;
    }
    catch (JaxenException paramObject)
    {
      throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + paramObject.getMessage(), paramObject);
    }
    finally
    {
      this.currentContext = null;
    }
    throw paramObject;
  }

  private class NSContext extends SimpleNamespaceContext
  {
    public NSContext()
    {
    }

    public String translateNamespacePrefixToUri(String paramString)
    {
      Object localObject1;
      if ((paramString == null) || (paramString.length() == 0))
        localObject1 = null;
      String str;
      Object localObject2;
      do
      {
        do
        {
          return localObject1;
          str = super.translateNamespacePrefixToUri(paramString);
          localObject1 = str;
        }
        while (str != null);
        localObject2 = JaxenXPath.this.currentContext;
        localObject1 = str;
      }
      while (localObject2 == null);
      Element localElement = null;
      if ((localObject2 instanceof Element))
        localElement = (Element)localObject2;
      while (true)
      {
        localObject1 = str;
        if (localElement == null)
          break;
        paramString = localElement.getNamespace(paramString);
        localObject1 = str;
        if (paramString == null)
          break;
        return paramString.getURI();
        if ((localObject2 instanceof Attribute))
          localElement = ((Attribute)localObject2).getParent();
        else if ((localObject2 instanceof Content))
          localElement = ((Content)localObject2).getParentElement();
        else if ((localObject2 instanceof Document))
          localElement = ((Document)localObject2).getRootElement();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.xpath.JaxenXPath
 * JD-Core Version:    0.6.2
 */