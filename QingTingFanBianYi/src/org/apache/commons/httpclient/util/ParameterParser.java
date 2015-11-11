package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;

public class ParameterParser
{
  private char[] chars = null;
  private int i1 = 0;
  private int i2 = 0;
  private int len = 0;
  private int pos = 0;

  private String getToken(boolean paramBoolean)
  {
    if ((this.i1 >= this.i2) || (!Character.isWhitespace(this.chars[this.i1])));
    while (true)
    {
      if ((this.i2 <= this.i1) || (!Character.isWhitespace(this.chars[(this.i2 - 1)])))
      {
        if ((paramBoolean) && (this.i2 - this.i1 >= 2) && (this.chars[this.i1] == '"') && (this.chars[(this.i2 - 1)] == '"'))
        {
          this.i1 += 1;
          this.i2 -= 1;
        }
        String str = null;
        if (this.i2 >= this.i1)
          str = new String(this.chars, this.i1, this.i2 - this.i1);
        return str;
        this.i1 += 1;
        break;
      }
      this.i2 -= 1;
    }
  }

  private boolean hasChar()
  {
    return this.pos < this.len;
  }

  private boolean isOneOf(char paramChar, char[] paramArrayOfChar)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfChar.length)
        return false;
      if (paramChar == paramArrayOfChar[i])
        return true;
      i += 1;
    }
  }

  private String parseQuotedToken(char[] paramArrayOfChar)
  {
    this.i1 = this.pos;
    this.i2 = this.pos;
    int k = 0;
    int j = 0;
    if (!hasChar());
    char c;
    do
    {
      return getToken(true);
      c = this.chars[this.pos];
    }
    while ((k == 0) && (isOneOf(c, paramArrayOfChar)));
    int i = k;
    if (j == 0)
    {
      i = k;
      if (c == '"')
      {
        if (k != 0)
          break label123;
        i = 1;
      }
    }
    label83: if ((j == 0) && (c == '\\'));
    for (j = 1; ; j = 0)
    {
      this.i2 += 1;
      this.pos += 1;
      k = i;
      break;
      label123: i = 0;
      break label83;
    }
  }

  private String parseToken(char[] paramArrayOfChar)
  {
    this.i1 = this.pos;
    this.i2 = this.pos;
    while (true)
    {
      if (!hasChar());
      while (isOneOf(this.chars[this.pos], paramArrayOfChar))
        return getToken(false);
      this.i2 += 1;
      this.pos += 1;
    }
  }

  public List parse(String paramString, char paramChar)
  {
    if (paramString == null)
      return new ArrayList();
    return parse(paramString.toCharArray(), paramChar);
  }

  public List parse(char[] paramArrayOfChar, char paramChar)
  {
    if (paramArrayOfChar == null)
      return new ArrayList();
    return parse(paramArrayOfChar, 0, paramArrayOfChar.length, paramChar);
  }

  public List parse(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar)
  {
    Object localObject1;
    if (paramArrayOfChar == null)
    {
      localObject1 = new ArrayList();
      return localObject1;
    }
    ArrayList localArrayList = new ArrayList();
    this.chars = paramArrayOfChar;
    this.pos = paramInt1;
    this.len = paramInt2;
    while (true)
    {
      localObject1 = localArrayList;
      if (!hasChar())
        break;
      String str = parseToken(new char[] { '=', paramChar });
      Object localObject2 = null;
      localObject1 = localObject2;
      if (hasChar())
      {
        localObject1 = localObject2;
        if (paramArrayOfChar[this.pos] == '=')
        {
          this.pos += 1;
          localObject1 = parseQuotedToken(new char[] { paramChar });
        }
      }
      if ((hasChar()) && (paramArrayOfChar[this.pos] == paramChar))
        this.pos += 1;
      if ((str != null) && ((!str.equals("")) || (localObject1 != null)))
        localArrayList.add(new NameValuePair(str, (String)localObject1));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.ParameterParser
 * JD-Core Version:    0.6.2
 */