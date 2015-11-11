package org.jdom.output;

import java.util.Stack;
import org.jdom.Namespace;

class NamespaceStack
{
  private static final String CVS_ID = "@(#) $RCSfile: NamespaceStack.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
  private Stack prefixes = new Stack();
  private Stack uris = new Stack();

  public String getURI(String paramString)
  {
    int i = this.prefixes.lastIndexOf(paramString);
    if (i == -1)
      return null;
    return (String)this.uris.elementAt(i);
  }

  public String pop()
  {
    String str = (String)this.prefixes.pop();
    this.uris.pop();
    return str;
  }

  public void push(Namespace paramNamespace)
  {
    this.prefixes.push(paramNamespace.getPrefix());
    this.uris.push(paramNamespace.getURI());
  }

  public int size()
  {
    return this.prefixes.size();
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str = System.getProperty("line.separator");
    localStringBuffer.append("Stack: " + this.prefixes.size() + str);
    int i = 0;
    while (i < this.prefixes.size())
    {
      localStringBuffer.append(this.prefixes.elementAt(i) + "&" + this.uris.elementAt(i) + str);
      i += 1;
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.output.NamespaceStack
 * JD-Core Version:    0.6.2
 */