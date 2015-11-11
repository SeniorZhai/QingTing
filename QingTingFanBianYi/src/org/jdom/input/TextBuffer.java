package org.jdom.input;

import org.jdom.Verifier;

class TextBuffer
{
  private static final String CVS_ID = "@(#) $RCSfile: TextBuffer.java,v $ $Revision: 1.10 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
  private char[] array = new char[4096];
  private int arraySize = 0;
  private String prefixString;

  private void ensureCapacity(int paramInt)
  {
    int j = this.array.length;
    if (paramInt > j)
    {
      char[] arrayOfChar = this.array;
      int i = j;
      while (paramInt > i)
        i += j / 2;
      this.array = new char[i];
      System.arraycopy(arrayOfChar, 0, this.array, 0, this.arraySize);
    }
  }

  void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.prefixString == null)
    {
      this.prefixString = new String(paramArrayOfChar, paramInt1, paramInt2);
      return;
    }
    ensureCapacity(this.arraySize + paramInt2);
    System.arraycopy(paramArrayOfChar, paramInt1, this.array, this.arraySize, paramInt2);
    this.arraySize += paramInt2;
  }

  void clear()
  {
    this.arraySize = 0;
    this.prefixString = null;
  }

  boolean isAllWhitespace()
  {
    boolean bool2 = false;
    boolean bool1;
    if ((this.prefixString == null) || (this.prefixString.length() == 0))
    {
      bool1 = true;
      return bool1;
    }
    int j = this.prefixString.length();
    int i = 0;
    while (true)
    {
      if (i >= j)
        break label63;
      bool1 = bool2;
      if (!Verifier.isXMLWhitespace(this.prefixString.charAt(i)))
        break;
      i += 1;
    }
    label63: i = 0;
    while (true)
    {
      if (i >= this.arraySize)
        break label95;
      bool1 = bool2;
      if (!Verifier.isXMLWhitespace(this.array[i]))
        break;
      i += 1;
    }
    label95: return true;
  }

  int size()
  {
    if (this.prefixString == null)
      return 0;
    return this.prefixString.length() + this.arraySize;
  }

  public String toString()
  {
    if (this.prefixString == null)
      return "";
    if (this.arraySize == 0)
      return this.prefixString;
    return new StringBuffer(this.prefixString.length() + this.arraySize).append(this.prefixString).append(this.array, 0, this.arraySize).toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.input.TextBuffer
 * JD-Core Version:    0.6.2
 */