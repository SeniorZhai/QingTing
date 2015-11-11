package org.jdom.filter;

import java.io.Serializable;

public abstract interface Filter extends Serializable
{
  public abstract boolean matches(Object paramObject);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.filter.Filter
 * JD-Core Version:    0.6.2
 */