package org.jdom;

public class IllegalDataException extends IllegalArgumentException
{
  private static final String CVS_ID = "@(#) $RCSfile: IllegalDataException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";

  public IllegalDataException(String paramString)
  {
    super(paramString);
  }

  IllegalDataException(String paramString1, String paramString2)
  {
    super("The data \"" + paramString1 + "\" is not legal for a JDOM " + paramString2 + ".");
  }

  IllegalDataException(String paramString1, String paramString2, String paramString3)
  {
    super("The data \"" + paramString1 + "\" is not legal for a JDOM " + paramString2 + ": " + paramString3 + ".");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.IllegalDataException
 * JD-Core Version:    0.6.2
 */