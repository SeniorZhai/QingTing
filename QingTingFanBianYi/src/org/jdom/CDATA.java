package org.jdom;

public class CDATA extends Text
{
  private static final String CVS_ID = "@(#) $RCSfile: CDATA.java,v $ $Revision: 1.32 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";

  protected CDATA()
  {
  }

  public CDATA(String paramString)
  {
    setText(paramString);
  }

  public void append(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString)))
      return;
    if (this.value == "");
    for (String str1 = paramString; ; str1 = this.value + paramString)
    {
      String str2 = Verifier.checkCDATASection(str1);
      if (str2 == null)
        break;
      throw new IllegalDataException(paramString, "CDATA section", str2);
    }
    this.value = str1;
  }

  public void append(Text paramText)
  {
    if (paramText == null)
      return;
    append(paramText.getText());
  }

  public Text setText(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString)))
    {
      this.value = "";
      return this;
    }
    String str = Verifier.checkCDATASection(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "CDATA section", str);
    this.value = paramString;
    return this;
  }

  public String toString()
  {
    return 64 + "[CDATA: " + getText() + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.CDATA
 * JD-Core Version:    0.6.2
 */