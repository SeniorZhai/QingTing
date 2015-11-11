package org.jdom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Attribute
  implements Serializable, Cloneable
{
  public static final int CDATA_TYPE = 1;
  private static final String CVS_ID = "@(#) $RCSfile: Attribute.java,v $ $Revision: 1.56 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
  public static final int ENTITIES_TYPE = 6;
  public static final int ENTITY_TYPE = 5;
  public static final int ENUMERATED_TYPE = 10;
  public static final int IDREFS_TYPE = 4;
  public static final int IDREF_TYPE = 3;
  public static final int ID_TYPE = 2;
  public static final int NMTOKENS_TYPE = 8;
  public static final int NMTOKEN_TYPE = 7;
  public static final int NOTATION_TYPE = 9;
  public static final int UNDECLARED_TYPE = 0;
  protected String name;
  protected transient Namespace namespace;
  protected Element parent;
  protected int type = 0;
  protected String value;

  protected Attribute()
  {
  }

  public Attribute(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, 0, Namespace.NO_NAMESPACE);
  }

  public Attribute(String paramString1, String paramString2, int paramInt)
  {
    this(paramString1, paramString2, paramInt, Namespace.NO_NAMESPACE);
  }

  public Attribute(String paramString1, String paramString2, int paramInt, Namespace paramNamespace)
  {
    setName(paramString1);
    setValue(paramString2);
    setAttributeType(paramInt);
    setNamespace(paramNamespace);
  }

  public Attribute(String paramString1, String paramString2, Namespace paramNamespace)
  {
    this(paramString1, paramString2, 0, paramNamespace);
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.namespace = Namespace.getNamespace((String)paramObjectInputStream.readObject(), (String)paramObjectInputStream.readObject());
  }

  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeObject(this.namespace.getPrefix());
    paramObjectOutputStream.writeObject(this.namespace.getURI());
  }

  public Object clone()
  {
    Object localObject = null;
    try
    {
      Attribute localAttribute = (Attribute)super.clone();
      localObject = localAttribute;
      label12: localObject.parent = null;
      return localObject;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      break label12;
    }
  }

  public Attribute detach()
  {
    Element localElement = getParent();
    if (localElement != null)
      localElement.removeAttribute(getName(), getNamespace());
    return this;
  }

  public final boolean equals(Object paramObject)
  {
    return paramObject == this;
  }

  public int getAttributeType()
  {
    return this.type;
  }

  public boolean getBooleanValue()
    throws DataConversionException
  {
    String str = this.value.trim();
    if ((str.equalsIgnoreCase("true")) || (str.equalsIgnoreCase("on")) || (str.equalsIgnoreCase("1")) || (str.equalsIgnoreCase("yes")))
      return true;
    if ((str.equalsIgnoreCase("false")) || (str.equalsIgnoreCase("off")) || (str.equalsIgnoreCase("0")) || (str.equalsIgnoreCase("no")))
      return false;
    throw new DataConversionException(this.name, "boolean");
  }

  public Document getDocument()
  {
    Element localElement = getParent();
    if (localElement != null)
      return localElement.getDocument();
    return null;
  }

  public double getDoubleValue()
    throws DataConversionException
  {
    try
    {
      double d = Double.valueOf(this.value.trim()).doubleValue();
      return d;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      String str = this.value.trim();
      if ("INF".equals(str))
        return (1.0D / 0.0D);
      if ("-INF".equals(str))
        return (-1.0D / 0.0D);
    }
    throw new DataConversionException(this.name, "double");
  }

  public float getFloatValue()
    throws DataConversionException
  {
    try
    {
      float f = Float.valueOf(this.value.trim()).floatValue();
      return f;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new DataConversionException(this.name, "float");
  }

  public int getIntValue()
    throws DataConversionException
  {
    try
    {
      int i = Integer.parseInt(this.value.trim());
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new DataConversionException(this.name, "int");
  }

  public long getLongValue()
    throws DataConversionException
  {
    try
    {
      long l = Long.parseLong(this.value.trim());
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new DataConversionException(this.name, "long");
  }

  public String getName()
  {
    return this.name;
  }

  public Namespace getNamespace()
  {
    return this.namespace;
  }

  public String getNamespacePrefix()
  {
    return this.namespace.getPrefix();
  }

  public String getNamespaceURI()
  {
    return this.namespace.getURI();
  }

  public Element getParent()
  {
    return this.parent;
  }

  public String getQualifiedName()
  {
    String str = this.namespace.getPrefix();
    if ((str == null) || ("".equals(str)))
      return getName();
    return str + ':' + getName();
  }

  public String getValue()
  {
    return this.value;
  }

  public final int hashCode()
  {
    return super.hashCode();
  }

  public Attribute setAttributeType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 10))
      throw new IllegalDataException(String.valueOf(paramInt), "attribute", "Illegal attribute type");
    this.type = paramInt;
    return this;
  }

  public Attribute setName(String paramString)
  {
    String str = Verifier.checkAttributeName(paramString);
    if (str != null)
      throw new IllegalNameException(paramString, "attribute", str);
    this.name = paramString;
    return this;
  }

  public Attribute setNamespace(Namespace paramNamespace)
  {
    Namespace localNamespace = paramNamespace;
    if (paramNamespace == null)
      localNamespace = Namespace.NO_NAMESPACE;
    if ((localNamespace != Namespace.NO_NAMESPACE) && ("".equals(localNamespace.getPrefix())))
      throw new IllegalNameException("", "attribute namespace", "An attribute namespace without a prefix can only be the NO_NAMESPACE namespace");
    this.namespace = localNamespace;
    return this;
  }

  protected Attribute setParent(Element paramElement)
  {
    this.parent = paramElement;
    return this;
  }

  public Attribute setValue(String paramString)
  {
    String str = Verifier.checkCharacterData(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "attribute", str);
    this.value = paramString;
    return this;
  }

  public String toString()
  {
    return "[Attribute: " + getQualifiedName() + "=\"" + this.value + "\"" + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Attribute
 * JD-Core Version:    0.6.2
 */