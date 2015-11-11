package org.jdom;

public class IllegalTargetException extends IllegalArgumentException
{
  private static final String CVS_ID = "@(#) $RCSfile: IllegalTargetException.java,v $ $Revision: 1.15 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";

  public IllegalTargetException(String paramString)
  {
    super(paramString);
  }

  IllegalTargetException(String paramString1, String paramString2)
  {
    super("The target \"" + paramString1 + "\" is not legal for JDOM/XML Processing Instructions: " + paramString2 + ".");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.IllegalTargetException
 * JD-Core Version:    0.6.2
 */