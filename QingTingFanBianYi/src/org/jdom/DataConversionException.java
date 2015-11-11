package org.jdom;

public class DataConversionException extends JDOMException
{
  private static final String CVS_ID = "@(#) $RCSfile: DataConversionException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";

  public DataConversionException(String paramString1, String paramString2)
  {
    super("The XML construct " + paramString1 + " could not be converted to a " + paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.DataConversionException
 * JD-Core Version:    0.6.2
 */