package org.jdom;

public class IllegalNameException extends IllegalArgumentException
{
  private static final String CVS_ID = "@(#) $RCSfile: IllegalNameException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";

  public IllegalNameException(String paramString)
  {
    super(paramString);
  }

  IllegalNameException(String paramString1, String paramString2)
  {
    super("The name \"" + paramString1 + "\" is not legal for JDOM/XML " + paramString2 + "s.");
  }

  IllegalNameException(String paramString1, String paramString2, String paramString3)
  {
    super("The name \"" + paramString1 + "\" is not legal for JDOM/XML " + paramString2 + "s: " + paramString3 + ".");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.IllegalNameException
 * JD-Core Version:    0.6.2
 */