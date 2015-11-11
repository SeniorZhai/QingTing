package org.jdom;

public class Text extends Content
{
  private static final String CVS_ID = "@(#) $RCSfile: Text.java,v $ $Revision: 1.25 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  static final String EMPTY_STRING = "";
  protected String value;

  protected Text()
  {
  }

  public Text(String paramString)
  {
    setText(paramString);
  }

  public static String normalizeString(String paramString)
  {
    if (paramString == null)
      return "";
    paramString = paramString.toCharArray();
    char[] arrayOfChar = new char[paramString.length];
    int n = 1;
    int i = 0;
    int m = 0;
    if (m < paramString.length)
    {
      int k;
      if (" \t\n\r".indexOf(paramString[m]) != -1)
      {
        j = i;
        k = n;
        if (n == 0)
        {
          arrayOfChar[i] = ' ';
          k = 1;
        }
      }
      for (j = i + 1; ; j = i + 1)
      {
        m += 1;
        i = j;
        n = k;
        break;
        arrayOfChar[i] = paramString[m];
        k = 0;
      }
    }
    int j = i;
    if (n != 0)
    {
      j = i;
      if (i > 0)
        j = i - 1;
    }
    return new String(arrayOfChar, 0, j);
  }

  public void append(String paramString)
  {
    if (paramString == null)
      return;
    String str = Verifier.checkCharacterData(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "character content", str);
    if (paramString == "")
    {
      this.value = paramString;
      return;
    }
    this.value += paramString;
  }

  public void append(Text paramText)
  {
    if (paramText == null)
      return;
    this.value += paramText.getText();
  }

  public Object clone()
  {
    Text localText = (Text)super.clone();
    localText.value = this.value;
    return localText;
  }

  public String getText()
  {
    return this.value;
  }

  public String getTextNormalize()
  {
    return normalizeString(getText());
  }

  public String getTextTrim()
  {
    return getText().trim();
  }

  public String getValue()
  {
    return this.value;
  }

  public Text setText(String paramString)
  {
    if (paramString == null)
    {
      this.value = "";
      return this;
    }
    String str = Verifier.checkCharacterData(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "character content", str);
    this.value = paramString;
    return this;
  }

  public String toString()
  {
    return 64 + "[Text: " + getText() + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Text
 * JD-Core Version:    0.6.2
 */