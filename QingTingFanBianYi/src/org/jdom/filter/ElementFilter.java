package org.jdom.filter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.jdom.Element;
import org.jdom.Namespace;

public class ElementFilter extends AbstractFilter
{
  private static final String CVS_ID = "@(#) $RCSfile: ElementFilter.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
  private String name;
  private transient Namespace namespace;

  public ElementFilter()
  {
  }

  public ElementFilter(String paramString)
  {
    this.name = paramString;
  }

  public ElementFilter(String paramString, Namespace paramNamespace)
  {
    this.name = paramString;
    this.namespace = paramNamespace;
  }

  public ElementFilter(Namespace paramNamespace)
  {
    this.namespace = paramNamespace;
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    Object localObject = paramObjectInputStream.readObject();
    paramObjectInputStream = paramObjectInputStream.readObject();
    if (localObject != null)
      this.namespace = Namespace.getNamespace((String)localObject, (String)paramObjectInputStream);
  }

  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    if (this.namespace != null)
    {
      paramObjectOutputStream.writeObject(this.namespace.getPrefix());
      paramObjectOutputStream.writeObject(this.namespace.getURI());
      return;
    }
    paramObjectOutputStream.writeObject(null);
    paramObjectOutputStream.writeObject(null);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof ElementFilter))
        return false;
      paramObject = (ElementFilter)paramObject;
      if (this.name != null)
      {
        if (this.name.equals(paramObject.name));
      }
      else
        while (paramObject.name != null)
          return false;
      if (this.namespace == null)
        break;
    }
    while (this.namespace.equals(paramObject.namespace));
    while (true)
    {
      return false;
      if (paramObject.namespace == null)
        break;
    }
  }

  public int hashCode()
  {
    int j = 0;
    if (this.name != null);
    for (int i = this.name.hashCode(); ; i = 0)
    {
      if (this.namespace != null)
        j = this.namespace.hashCode();
      return i * 29 + j;
    }
  }

  public boolean matches(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Element))
    {
      paramObject = (Element)paramObject;
      if (this.name != null)
      {
        bool1 = bool2;
        if (!this.name.equals(paramObject.getName()));
      }
      else if (this.namespace != null)
      {
        bool1 = bool2;
        if (!this.namespace.equals(paramObject.getNamespace()));
      }
      else
      {
        bool1 = true;
      }
    }
    return bool1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.filter.ElementFilter
 * JD-Core Version:    0.6.2
 */