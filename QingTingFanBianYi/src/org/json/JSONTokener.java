package org.json;

public class JSONTokener
{
  private int myIndex = 0;
  private String mySource;

  public JSONTokener(String paramString)
  {
    this.mySource = paramString;
  }

  public static int dehexchar(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return paramChar - '7';
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return paramChar - 'W';
    return -1;
  }

  public void back()
  {
    if (this.myIndex > 0)
      this.myIndex -= 1;
  }

  public boolean more()
  {
    return this.myIndex < this.mySource.length();
  }

  public char next()
  {
    if (more())
    {
      char c = this.mySource.charAt(this.myIndex);
      this.myIndex += 1;
      return c;
    }
    return '\000';
  }

  public char next(char paramChar)
    throws JSONException
  {
    char c = next();
    if (c != paramChar)
      throw syntaxError("Expected '" + paramChar + "' and instead saw '" + c + "'");
    return c;
  }

  public String next(int paramInt)
    throws JSONException
  {
    int i = this.myIndex;
    int j = i + paramInt;
    if (j >= this.mySource.length())
      throw syntaxError("Substring bounds error");
    this.myIndex += paramInt;
    return this.mySource.substring(i, j);
  }

  public char nextClean()
    throws JSONException
  {
    char c;
    do
      while (true)
      {
        c = next();
        int i;
        if (c == '/')
        {
          switch (next())
          {
          default:
            back();
            return '/';
          case '/':
            do
            {
              i = next();
              if ((i == 10) || (i == 13))
                break;
            }
            while (i != 0);
            break;
          case '*':
          }
          do
          {
            back();
            do
            {
              i = next();
              if (i == 0)
                throw syntaxError("Unclosed comment");
            }
            while (i != 42);
          }
          while (next() != '/');
        }
        else
        {
          if (c != '#')
            break;
          do
          {
            i = next();
            if ((i == 10) || (i == 13))
              break;
          }
          while (i != 0);
        }
      }
    while ((c != 0) && (c <= ' '));
    return c;
  }

  public String nextString(char paramChar)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      char c = next();
      switch (c)
      {
      default:
        if (c == paramChar)
          return localStringBuffer.toString();
        break;
      case '\000':
      case '\n':
      case '\r':
        throw syntaxError("Unterminated string");
      case '\\':
        c = next();
        switch (c)
        {
        default:
          localStringBuffer.append(c);
          break;
        case 'b':
          localStringBuffer.append('\b');
          break;
        case 't':
          localStringBuffer.append('\t');
          break;
        case 'n':
          localStringBuffer.append('\n');
          break;
        case 'f':
          localStringBuffer.append('\f');
          break;
        case 'r':
          localStringBuffer.append('\r');
          break;
        case 'u':
          localStringBuffer.append((char)Integer.parseInt(next(4), 16));
          break;
        case 'x':
          localStringBuffer.append((char)Integer.parseInt(next(2), 16));
        }
        break;
      }
      localStringBuffer.append(c);
    }
  }

  public String nextTo(char paramChar)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      char c = next();
      if ((c == paramChar) || (c == 0) || (c == '\n') || (c == '\r'))
      {
        if (c != 0)
          back();
        return localStringBuffer.toString().trim();
      }
      localStringBuffer.append(c);
    }
  }

  public String nextTo(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      char c = next();
      if ((paramString.indexOf(c) >= 0) || (c == 0) || (c == '\n') || (c == '\r'))
      {
        if (c != 0)
          back();
        return localStringBuffer.toString().trim();
      }
      localStringBuffer.append(c);
    }
  }

  public Object nextValue()
    throws JSONException
  {
    char c2 = nextClean();
    char c1;
    switch (c2)
    {
    default:
      localObject = new StringBuffer();
      c1 = c2;
    case '"':
    case '\'':
    case '{':
    case '[':
    }
    while ((c1 >= ' ') && (",:]}/\\\"[{;=#".indexOf(c1) < 0))
    {
      ((StringBuffer)localObject).append(c1);
      c1 = next();
      continue;
      return nextString(c2);
      back();
      return new JSONObject(this);
      back();
      return new JSONArray(this);
    }
    back();
    Object localObject = ((StringBuffer)localObject).toString().trim();
    if (((String)localObject).equals(""))
      throw syntaxError("Missing value");
    if (((String)localObject).equalsIgnoreCase("true"))
      return Boolean.TRUE;
    if (((String)localObject).equalsIgnoreCase("false"))
      return Boolean.FALSE;
    if (((String)localObject).equalsIgnoreCase("null"))
      return JSONObject.NULL;
    if (((c2 >= '0') && (c2 <= '9')) || (c2 == '.') || (c2 == '-') || (c2 == '+'))
    {
      if (c2 == '0')
      {
        if ((((String)localObject).length() <= 2) || ((((String)localObject).charAt(1) != 'x') && (((String)localObject).charAt(1) != 'X')))
          break label321;
        try
        {
          Integer localInteger1 = new Integer(Integer.parseInt(((String)localObject).substring(2), 16));
          return localInteger1;
        }
        catch (Exception localException1)
        {
        }
      }
      while (true)
      {
        try
        {
          Integer localInteger2 = new Integer((String)localObject);
          return localInteger2;
        }
        catch (Exception localException2)
        {
          try
          {
            Long localLong = new Long((String)localObject);
            return localLong;
          }
          catch (Exception localException3)
          {
            try
            {
              Double localDouble = new Double((String)localObject);
              return localDouble;
            }
            catch (Exception localException4)
            {
              return localObject;
            }
          }
        }
        try
        {
          label321: Integer localInteger3 = new Integer(Integer.parseInt((String)localObject, 8));
          return localInteger3;
        }
        catch (Exception localException5)
        {
        }
      }
    }
    return localObject;
  }

  public boolean skipPast(String paramString)
  {
    this.myIndex = this.mySource.indexOf(paramString, this.myIndex);
    if (this.myIndex < 0)
    {
      this.myIndex = this.mySource.length();
      return false;
    }
    this.myIndex += paramString.length();
    return true;
  }

  public char skipTo(char paramChar)
  {
    int i = this.myIndex;
    char c;
    do
    {
      c = next();
      if (c == 0)
      {
        this.myIndex = i;
        return c;
      }
    }
    while (c != paramChar);
    back();
    return c;
  }

  public JSONException syntaxError(String paramString)
  {
    return new JSONException(paramString + toString());
  }

  public String toString()
  {
    return " at character " + this.myIndex + " of " + this.mySource;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.JSONTokener
 * JD-Core Version:    0.6.2
 */