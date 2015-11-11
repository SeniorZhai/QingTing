package org.jdom;

final class NamespaceKey
{
  private static final String CVS_ID = "@(#) $RCSfile: NamespaceKey.java,v $ $Revision: 1.2 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  private int hash;
  private String prefix;
  private String uri;

  public NamespaceKey(String paramString1, String paramString2)
  {
    this.prefix = paramString1;
    this.uri = paramString2;
    this.hash = paramString1.hashCode();
  }

  public NamespaceKey(Namespace paramNamespace)
  {
    this(paramNamespace.getPrefix(), paramNamespace.getURI());
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof NamespaceKey))
        break;
      paramObject = (NamespaceKey)paramObject;
    }
    while ((this.prefix.equals(paramObject.prefix)) && (this.uri.equals(paramObject.uri)));
    return false;
    return false;
  }

  public int hashCode()
  {
    return this.hash;
  }

  public String toString()
  {
    return "[NamespaceKey: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.NamespaceKey
 * JD-Core Version:    0.6.2
 */