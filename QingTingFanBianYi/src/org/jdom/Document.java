package org.jdom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.filter.Filter;

public class Document
  implements Parent
{
  private static final String CVS_ID = "@(#) $RCSfile: Document.java,v $ $Revision: 1.85 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
  protected String baseURI = null;
  ContentList content = new ContentList(this);
  private HashMap propertyMap = null;

  public Document()
  {
  }

  public Document(List paramList)
  {
    setContent(paramList);
  }

  public Document(Element paramElement)
  {
    this(paramElement, null, null);
  }

  public Document(Element paramElement, DocType paramDocType)
  {
    this(paramElement, paramDocType, null);
  }

  public Document(Element paramElement, DocType paramDocType, String paramString)
  {
    if (paramElement != null)
      setRootElement(paramElement);
    if (paramDocType != null)
      setDocType(paramDocType);
    if (paramString != null)
      setBaseURI(paramString);
  }

  public Document addContent(int paramInt, Collection paramCollection)
  {
    this.content.addAll(paramInt, paramCollection);
    return this;
  }

  public Document addContent(int paramInt, Content paramContent)
  {
    this.content.add(paramInt, paramContent);
    return this;
  }

  public Document addContent(Collection paramCollection)
  {
    this.content.addAll(paramCollection);
    return this;
  }

  public Document addContent(Content paramContent)
  {
    this.content.add(paramContent);
    return this;
  }

  public Object clone()
  {
    Object localObject1 = null;
    try
    {
      Object localObject2 = (Document)super.clone();
      localObject1 = localObject2;
      label12: localObject1.content = new ContentList(localObject1);
      int i = 0;
      if (i < this.content.size())
      {
        localObject2 = this.content.get(i);
        if ((localObject2 instanceof Element))
        {
          localObject2 = (Element)((Element)localObject2).clone();
          localObject1.content.add(localObject2);
        }
        while (true)
        {
          i += 1;
          break;
          if ((localObject2 instanceof Comment))
          {
            localObject2 = (Comment)((Comment)localObject2).clone();
            localObject1.content.add(localObject2);
          }
          else if ((localObject2 instanceof ProcessingInstruction))
          {
            localObject2 = (ProcessingInstruction)((ProcessingInstruction)localObject2).clone();
            localObject1.content.add(localObject2);
          }
          else if ((localObject2 instanceof DocType))
          {
            localObject2 = (DocType)((DocType)localObject2).clone();
            localObject1.content.add(localObject2);
          }
        }
      }
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      break label12;
    }
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

  public Element detachRootElement()
  {
    int i = this.content.indexOfFirstElement();
    if (i < 0)
      return null;
    return (Element)removeContent(i);
  }

  public final boolean equals(Object paramObject)
  {
    return paramObject == this;
  }

  public final String getBaseURI()
  {
    return this.baseURI;
  }

  public List getContent()
  {
    if (!hasRootElement())
      throw new IllegalStateException("Root element not set");
    return this.content;
  }

  public List getContent(Filter paramFilter)
  {
    if (!hasRootElement())
      throw new IllegalStateException("Root element not set");
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

  public DocType getDocType()
  {
    int i = this.content.indexOfDocType();
    if (i < 0)
      return null;
    return (DocType)this.content.get(i);
  }

  public Document getDocument()
  {
    return this;
  }

  public Parent getParent()
  {
    return null;
  }

  public Object getProperty(String paramString)
  {
    if (this.propertyMap == null)
      return null;
    return this.propertyMap.get(paramString);
  }

  public Element getRootElement()
  {
    int i = this.content.indexOfFirstElement();
    if (i < 0)
      throw new IllegalStateException("Root element not set");
    return (Element)this.content.get(i);
  }

  public boolean hasRootElement()
  {
    return this.content.indexOfFirstElement() >= 0;
  }

  public final int hashCode()
  {
    return super.hashCode();
  }

  public int indexOf(Content paramContent)
  {
    return this.content.indexOf(paramContent);
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

  public final void setBaseURI(String paramString)
  {
    this.baseURI = paramString;
  }

  public Document setContent(int paramInt, Collection paramCollection)
  {
    this.content.remove(paramInt);
    this.content.addAll(paramInt, paramCollection);
    return this;
  }

  public Document setContent(int paramInt, Content paramContent)
  {
    this.content.set(paramInt, paramContent);
    return this;
  }

  public Document setContent(Collection paramCollection)
  {
    this.content.clearAndSet(paramCollection);
    return this;
  }

  public Document setContent(Content paramContent)
  {
    this.content.clear();
    this.content.add(paramContent);
    return this;
  }

  public Document setDocType(DocType paramDocType)
  {
    if (paramDocType == null)
    {
      i = this.content.indexOfDocType();
      if (i >= 0)
        this.content.remove(i);
      return this;
    }
    if (paramDocType.getParent() != null)
      throw new IllegalAddException(paramDocType, "The DocType already is attached to a document");
    int i = this.content.indexOfDocType();
    if (i < 0)
    {
      this.content.add(0, paramDocType);
      return this;
    }
    this.content.set(i, paramDocType);
    return this;
  }

  public void setProperty(String paramString, Object paramObject)
  {
    if (this.propertyMap == null)
      this.propertyMap = new HashMap();
    this.propertyMap.put(paramString, paramObject);
  }

  public Document setRootElement(Element paramElement)
  {
    int i = this.content.indexOfFirstElement();
    if (i < 0)
    {
      this.content.add(paramElement);
      return this;
    }
    this.content.set(i, paramElement);
    return this;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer().append("[Document: ");
    Object localObject = getDocType();
    if (localObject != null)
    {
      localStringBuffer.append(((DocType)localObject).toString()).append(", ");
      localObject = getRootElement();
      if (localObject == null)
        break label81;
      localStringBuffer.append("Root is ").append(((Element)localObject).toString());
    }
    while (true)
    {
      localStringBuffer.append("]");
      return localStringBuffer.toString();
      localStringBuffer.append(" No DOCTYPE declaration, ");
      break;
      label81: localStringBuffer.append(" No root element");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Document
 * JD-Core Version:    0.6.2
 */