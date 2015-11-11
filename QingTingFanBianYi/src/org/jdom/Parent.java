package org.jdom;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.jdom.filter.Filter;

public abstract interface Parent extends Cloneable, Serializable
{
  public abstract Object clone();

  public abstract List cloneContent();

  public abstract List getContent();

  public abstract List getContent(Filter paramFilter);

  public abstract Content getContent(int paramInt);

  public abstract int getContentSize();

  public abstract Iterator getDescendants();

  public abstract Iterator getDescendants(Filter paramFilter);

  public abstract Document getDocument();

  public abstract Parent getParent();

  public abstract int indexOf(Content paramContent);

  public abstract List removeContent();

  public abstract List removeContent(Filter paramFilter);

  public abstract Content removeContent(int paramInt);

  public abstract boolean removeContent(Content paramContent);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Parent
 * JD-Core Version:    0.6.2
 */