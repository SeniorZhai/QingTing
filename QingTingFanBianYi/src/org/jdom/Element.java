package org.jdom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jdom.filter.ElementFilter;
import org.jdom.filter.Filter;

public class Element extends Content
  implements Parent
{
  private static final String CVS_ID = "@(#) $RCSfile: Element.java,v $ $Revision: 1.159 $ $Date: 2007/11/14 05:02:08 $ $Name: jdom_1_1_1 $";
  private static final int INITIAL_ARRAY_SIZE = 5;
  protected transient List additionalNamespaces;
  AttributeList attributes = new AttributeList(this);
  ContentList content = new ContentList(this);
  protected String name;
  protected transient Namespace namespace;

  protected Element()
  {
  }

  public Element(String paramString)
  {
    this(paramString, (Namespace)null);
  }

  public Element(String paramString1, String paramString2)
  {
    this(paramString1, Namespace.getNamespace("", paramString2));
  }

  public Element(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, Namespace.getNamespace(paramString2, paramString3));
  }

  public Element(String paramString, Namespace paramNamespace)
  {
    setName(paramString);
    setNamespace(paramNamespace);
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.namespace = Namespace.getNamespace((String)paramObjectInputStream.readObject(), (String)paramObjectInputStream.readObject());
    int j = paramObjectInputStream.read();
    if (j != 0)
    {
      this.additionalNamespaces = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        Namespace localNamespace = Namespace.getNamespace((String)paramObjectInputStream.readObject(), (String)paramObjectInputStream.readObject());
        this.additionalNamespaces.add(localNamespace);
        i += 1;
      }
    }
  }

  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeObject(this.namespace.getPrefix());
    paramObjectOutputStream.writeObject(this.namespace.getURI());
    if (this.additionalNamespaces == null)
      paramObjectOutputStream.write(0);
    while (true)
    {
      return;
      int j = this.additionalNamespaces.size();
      paramObjectOutputStream.write(j);
      int i = 0;
      while (i < j)
      {
        Namespace localNamespace = (Namespace)this.additionalNamespaces.get(i);
        paramObjectOutputStream.writeObject(localNamespace.getPrefix());
        paramObjectOutputStream.writeObject(localNamespace.getURI());
        i += 1;
      }
    }
  }

  public Element addContent(int paramInt, Collection paramCollection)
  {
    this.content.addAll(paramInt, paramCollection);
    return this;
  }

  public Element addContent(int paramInt, Content paramContent)
  {
    this.content.add(paramInt, paramContent);
    return this;
  }

  public Element addContent(String paramString)
  {
    return addContent(new Text(paramString));
  }

  public Element addContent(Collection paramCollection)
  {
    this.content.addAll(paramCollection);
    return this;
  }

  public Element addContent(Content paramContent)
  {
    this.content.add(paramContent);
    return this;
  }

  public void addNamespaceDeclaration(Namespace paramNamespace)
  {
    String str = Verifier.checkNamespaceCollision(paramNamespace, this);
    if (str != null)
      throw new IllegalAddException(this, paramNamespace, str);
    if (this.additionalNamespaces == null)
      this.additionalNamespaces = new ArrayList(5);
    this.additionalNamespaces.add(paramNamespace);
  }

  public Object clone()
  {
    Element localElement = (Element)super.clone();
    localElement.content = new ContentList(localElement);
    localElement.attributes = new AttributeList(localElement);
    int i;
    Object localObject;
    if (this.attributes != null)
    {
      i = 0;
      while (i < this.attributes.size())
      {
        localObject = (Attribute)this.attributes.get(i);
        localElement.attributes.add(((Attribute)localObject).clone());
        i += 1;
      }
    }
    if (this.additionalNamespaces != null)
      localElement.additionalNamespaces = new ArrayList(this.additionalNamespaces);
    if (this.content != null)
    {
      i = 0;
      while (i < this.content.size())
      {
        localObject = (Content)this.content.get(i);
        localElement.content.add(((Content)localObject).clone());
        i += 1;
      }
    }
    return localElement;
  }

  public List cloneContent()
  {
    int j = getContentSize();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(getContent(i).clone());
      i += 1;
    }
    return localArrayList;
  }

  public List getAdditionalNamespaces()
  {
    if (this.additionalNamespaces == null)
      return Collections.EMPTY_LIST;
    return Collections.unmodifiableList(this.additionalNamespaces);
  }

  public Attribute getAttribute(String paramString)
  {
    return getAttribute(paramString, Namespace.NO_NAMESPACE);
  }

  public Attribute getAttribute(String paramString, Namespace paramNamespace)
  {
    return (Attribute)this.attributes.get(paramString, paramNamespace);
  }

  public String getAttributeValue(String paramString)
  {
    return getAttributeValue(paramString, Namespace.NO_NAMESPACE);
  }

  public String getAttributeValue(String paramString1, String paramString2)
  {
    return getAttributeValue(paramString1, Namespace.NO_NAMESPACE, paramString2);
  }

  public String getAttributeValue(String paramString, Namespace paramNamespace)
  {
    return getAttributeValue(paramString, paramNamespace, null);
  }

  public String getAttributeValue(String paramString1, Namespace paramNamespace, String paramString2)
  {
    paramString1 = (Attribute)this.attributes.get(paramString1, paramNamespace);
    if (paramString1 == null)
      return paramString2;
    return paramString1.getValue();
  }

  public List getAttributes()
  {
    return this.attributes;
  }

  public Element getChild(String paramString)
  {
    return getChild(paramString, Namespace.NO_NAMESPACE);
  }

  public Element getChild(String paramString, Namespace paramNamespace)
  {
    paramString = this.content.getView(new ElementFilter(paramString, paramNamespace)).iterator();
    if (paramString.hasNext())
      return (Element)paramString.next();
    return null;
  }

  public String getChildText(String paramString)
  {
    paramString = getChild(paramString);
    if (paramString == null)
      return null;
    return paramString.getText();
  }

  public String getChildText(String paramString, Namespace paramNamespace)
  {
    paramString = getChild(paramString, paramNamespace);
    if (paramString == null)
      return null;
    return paramString.getText();
  }

  public String getChildTextNormalize(String paramString)
  {
    paramString = getChild(paramString);
    if (paramString == null)
      return null;
    return paramString.getTextNormalize();
  }

  public String getChildTextNormalize(String paramString, Namespace paramNamespace)
  {
    paramString = getChild(paramString, paramNamespace);
    if (paramString == null)
      return null;
    return paramString.getTextNormalize();
  }

  public String getChildTextTrim(String paramString)
  {
    paramString = getChild(paramString);
    if (paramString == null)
      return null;
    return paramString.getTextTrim();
  }

  public String getChildTextTrim(String paramString, Namespace paramNamespace)
  {
    paramString = getChild(paramString, paramNamespace);
    if (paramString == null)
      return null;
    return paramString.getTextTrim();
  }

  public List getChildren()
  {
    return this.content.getView(new ElementFilter());
  }

  public List getChildren(String paramString)
  {
    return getChildren(paramString, Namespace.NO_NAMESPACE);
  }

  public List getChildren(String paramString, Namespace paramNamespace)
  {
    return this.content.getView(new ElementFilter(paramString, paramNamespace));
  }

  public List getContent()
  {
    return this.content;
  }

  public List getContent(Filter paramFilter)
  {
    return this.content.getView(paramFilter);
  }

  public Content getContent(int paramInt)
  {
    return (Content)this.content.get(paramInt);
  }

  public int getContentSize()
  {
    return this.content.size();
  }

  public Iterator getDescendants()
  {
    return new DescendantIterator(this);
  }

  public Iterator getDescendants(Filter paramFilter)
  {
    return new FilterIterator(new DescendantIterator(this), paramFilter);
  }

  public String getName()
  {
    return this.name;
  }

  public Namespace getNamespace()
  {
    return this.namespace;
  }

  public Namespace getNamespace(String paramString)
  {
    Object localObject;
    if (paramString == null)
    {
      localObject = null;
      return localObject;
    }
    if ("xml".equals(paramString))
      return Namespace.XML_NAMESPACE;
    if (paramString.equals(getNamespacePrefix()))
      return getNamespace();
    if (this.additionalNamespaces != null)
    {
      int i = 0;
      while (true)
      {
        if (i >= this.additionalNamespaces.size())
          break label99;
        Namespace localNamespace = (Namespace)this.additionalNamespaces.get(i);
        localObject = localNamespace;
        if (paramString.equals(localNamespace.getPrefix()))
          break;
        i += 1;
      }
    }
    label99: if ((this.parent instanceof Element))
      return ((Element)this.parent).getNamespace(paramString);
    return null;
  }

  public String getNamespacePrefix()
  {
    return this.namespace.getPrefix();
  }

  public String getNamespaceURI()
  {
    return this.namespace.getURI();
  }

  public String getQualifiedName()
  {
    if ("".equals(this.namespace.getPrefix()))
      return getName();
    return this.namespace.getPrefix() + ':' + this.name;
  }

  public String getText()
  {
    if (this.content.size() == 0)
      return "";
    if (this.content.size() == 1)
    {
      localObject1 = this.content.get(0);
      if ((localObject1 instanceof Text))
        return ((Text)localObject1).getText();
      return "";
    }
    Object localObject1 = new StringBuffer();
    int j = 0;
    int i = 0;
    while (i < this.content.size())
    {
      Object localObject2 = this.content.get(i);
      if ((localObject2 instanceof Text))
      {
        ((StringBuffer)localObject1).append(((Text)localObject2).getText());
        j = 1;
      }
      i += 1;
    }
    if (j == 0)
      return "";
    return ((StringBuffer)localObject1).toString();
  }

  public String getTextNormalize()
  {
    return Text.normalizeString(getText());
  }

  public String getTextTrim()
  {
    return getText().trim();
  }

  public String getValue()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = getContent().iterator();
    while (localIterator.hasNext())
    {
      Content localContent = (Content)localIterator.next();
      if (((localContent instanceof Element)) || ((localContent instanceof Text)))
        localStringBuffer.append(localContent.getValue());
    }
    return localStringBuffer.toString();
  }

  public int indexOf(Content paramContent)
  {
    return this.content.indexOf(paramContent);
  }

  public boolean isAncestor(Element paramElement)
  {
    for (paramElement = paramElement.getParent(); (paramElement instanceof Element); paramElement = paramElement.getParent())
      if (paramElement == this)
        return true;
    return false;
  }

  public boolean isRootElement()
  {
    return this.parent instanceof Document;
  }

  public boolean removeAttribute(String paramString)
  {
    return removeAttribute(paramString, Namespace.NO_NAMESPACE);
  }

  public boolean removeAttribute(String paramString, Namespace paramNamespace)
  {
    return this.attributes.remove(paramString, paramNamespace);
  }

  public boolean removeAttribute(Attribute paramAttribute)
  {
    return this.attributes.remove(paramAttribute);
  }

  public boolean removeChild(String paramString)
  {
    return removeChild(paramString, Namespace.NO_NAMESPACE);
  }

  public boolean removeChild(String paramString, Namespace paramNamespace)
  {
    paramString = new ElementFilter(paramString, paramNamespace);
    paramString = this.content.getView(paramString).iterator();
    if (paramString.hasNext())
    {
      paramString.next();
      paramString.remove();
      return true;
    }
    return false;
  }

  public boolean removeChildren(String paramString)
  {
    return removeChildren(paramString, Namespace.NO_NAMESPACE);
  }

  public boolean removeChildren(String paramString, Namespace paramNamespace)
  {
    boolean bool = false;
    paramString = new ElementFilter(paramString, paramNamespace);
    paramString = this.content.getView(paramString).iterator();
    while (paramString.hasNext())
    {
      paramString.next();
      paramString.remove();
      bool = true;
    }
    return bool;
  }

  public List removeContent()
  {
    ArrayList localArrayList = new ArrayList(this.content);
    this.content.clear();
    return localArrayList;
  }

  public List removeContent(Filter paramFilter)
  {
    ArrayList localArrayList = new ArrayList();
    paramFilter = this.content.getView(paramFilter).iterator();
    while (paramFilter.hasNext())
    {
      localArrayList.add((Content)paramFilter.next());
      paramFilter.remove();
    }
    return localArrayList;
  }

  public Content removeContent(int paramInt)
  {
    return (Content)this.content.remove(paramInt);
  }

  public boolean removeContent(Content paramContent)
  {
    return this.content.remove(paramContent);
  }

  public void removeNamespaceDeclaration(Namespace paramNamespace)
  {
    if (this.additionalNamespaces == null)
      return;
    this.additionalNamespaces.remove(paramNamespace);
  }

  public Element setAttribute(String paramString1, String paramString2)
  {
    Attribute localAttribute = getAttribute(paramString1);
    if (localAttribute == null)
    {
      setAttribute(new Attribute(paramString1, paramString2));
      return this;
    }
    localAttribute.setValue(paramString2);
    return this;
  }

  public Element setAttribute(String paramString1, String paramString2, Namespace paramNamespace)
  {
    Attribute localAttribute = getAttribute(paramString1, paramNamespace);
    if (localAttribute == null)
    {
      setAttribute(new Attribute(paramString1, paramString2, paramNamespace));
      return this;
    }
    localAttribute.setValue(paramString2);
    return this;
  }

  public Element setAttribute(Attribute paramAttribute)
  {
    this.attributes.add(paramAttribute);
    return this;
  }

  public Element setAttributes(Collection paramCollection)
  {
    this.attributes.clearAndSet(paramCollection);
    return this;
  }

  public Element setAttributes(List paramList)
  {
    return setAttributes(paramList);
  }

  public Element setContent(int paramInt, Content paramContent)
  {
    this.content.set(paramInt, paramContent);
    return this;
  }

  public Element setContent(Collection paramCollection)
  {
    this.content.clearAndSet(paramCollection);
    return this;
  }

  public Element setContent(Content paramContent)
  {
    this.content.clear();
    this.content.add(paramContent);
    return this;
  }

  public Parent setContent(int paramInt, Collection paramCollection)
  {
    this.content.remove(paramInt);
    this.content.addAll(paramInt, paramCollection);
    return this;
  }

  public Element setName(String paramString)
  {
    String str = Verifier.checkElementName(paramString);
    if (str != null)
      throw new IllegalNameException(paramString, "element", str);
    this.name = paramString;
    return this;
  }

  public Element setNamespace(Namespace paramNamespace)
  {
    Namespace localNamespace = paramNamespace;
    if (paramNamespace == null)
      localNamespace = Namespace.NO_NAMESPACE;
    this.namespace = localNamespace;
    return this;
  }

  public Element setText(String paramString)
  {
    this.content.clear();
    if (paramString != null)
      addContent(new Text(paramString));
    return this;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(64).append("[Element: <").append(getQualifiedName());
    String str = getNamespaceURI();
    if (!"".equals(str))
      localStringBuffer.append(" [Namespace: ").append(str).append("]");
    localStringBuffer.append("/>]");
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Element
 * JD-Core Version:    0.6.2
 */