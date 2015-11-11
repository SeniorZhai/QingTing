package org.jdom;

import java.io.Serializable;

public abstract class Content
  implements Cloneable, Serializable
{
  protected Parent parent = null;

  public Object clone()
  {
    try
    {
      Content localContent = (Content)super.clone();
      localContent.parent = null;
      return localContent;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    return null;
  }

  public Content detach()
  {
    if (this.parent != null)
      this.parent.removeContent(this);
    return this;
  }

  public final boolean equals(Object paramObject)
  {
    return paramObject == this;
  }

  public Document getDocument()
  {
    if (this.parent == null)
      return null;
    return this.parent.getDocument();
  }

  public Parent getParent()
  {
    return this.parent;
  }

  public Element getParentElement()
  {
    Parent localParent = getParent();
    if ((localParent instanceof Element));
    while (true)
    {
      return (Element)localParent;
      localParent = null;
    }
  }

  public abstract String getValue();

  public final int hashCode()
  {
    return super.hashCode();
  }

  protected Content setParent(Parent paramParent)
  {
    this.parent = paramParent;
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Content
 * JD-Core Version:    0.6.2
 */