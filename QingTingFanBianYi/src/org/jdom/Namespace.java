package org.jdom;

import java.util.HashMap;

public final class Namespace
{
  private static final String CVS_ID = "@(#) $RCSfile: Namespace.java,v $ $Revision: 1.44 $ $Date: 2008/12/17 23:22:48 $ $Name: jdom_1_1_1 $";
  public static final Namespace NO_NAMESPACE = new Namespace("", "");
  public static final Namespace XML_NAMESPACE = new Namespace("xml", "http://www.w3.org/XML/1998/namespace");
  private static HashMap namespaces = new HashMap(16);
  private String prefix;
  private String uri;

  static
  {
    namespaces.put(new NamespaceKey(NO_NAMESPACE), NO_NAMESPACE);
    namespaces.put(new NamespaceKey(XML_NAMESPACE), XML_NAMESPACE);
  }

  private Namespace(String paramString1, String paramString2)
  {
    this.prefix = paramString1;
    this.uri = paramString2;
  }

  public static Namespace getNamespace(String paramString)
  {
    return getNamespace("", paramString);
  }

  public static Namespace getNamespace(String arg0, String paramString2)
  {
    if ((??? == null) || (???.trim().equals("")))
      if ((paramString2 == null) || (paramString2.trim().equals("")))
        ??? = NO_NAMESPACE;
    String str1;
    String str2;
    label43: NamespaceKey localNamespaceKey;
    while (true)
    {
      return ???;
      str1 = "";
      str2 = paramString2;
      localNamespaceKey = new NamespaceKey(str1, str2);
      synchronized (namespaces)
      {
        paramString2 = (Namespace)namespaces.get(localNamespaceKey);
        ??? = paramString2;
        if (paramString2 == null)
        {
          ??? = Verifier.checkNamespacePrefix(str1);
          if (??? != null)
          {
            throw new IllegalNameException(str1, "Namespace prefix", ???);
            if (paramString2 != null)
            {
              str1 = ???;
              str2 = paramString2;
              if (!paramString2.trim().equals(""))
                break label43;
            }
            str2 = "";
            str1 = ???;
          }
        }
      }
    }
    ??? = Verifier.checkNamespaceURI(str2);
    if (??? != null)
      throw new IllegalNameException(str2, "Namespace URI", ???);
    if ((!str1.equals("")) && (str2.equals("")))
      throw new IllegalNameException("", "namespace", "Namespace URIs must be non-null and non-empty Strings");
    if (str1.equals("xml"))
      throw new IllegalNameException(str1, "Namespace prefix", "The xml prefix can only be bound to http://www.w3.org/XML/1998/namespace");
    if (str2.equals("http://www.w3.org/XML/1998/namespace"))
      throw new IllegalNameException(str2, "Namespace URI", "The http://www.w3.org/XML/1998/namespace must be bound to the xml prefix.");
    paramString2 = new Namespace(str1, str2);
    synchronized (namespaces)
    {
      namespaces.put(localNamespaceKey, paramString2);
      return paramString2;
    }
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof Namespace))
      return this.uri.equals(((Namespace)paramObject).uri);
    return false;
  }

  public String getPrefix()
  {
    return this.prefix;
  }

  public String getURI()
  {
    return this.uri;
  }

  public int hashCode()
  {
    return this.uri.hashCode();
  }

  public String toString()
  {
    return "[Namespace: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Namespace
 * JD-Core Version:    0.6.2
 */