package weibo4android.org.json;

public class HTTPTokener extends JSONTokener
{
  public HTTPTokener(String paramString)
  {
    super(paramString);
  }

  public String nextToken()
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    char c2;
    do
      c2 = next();
    while (Character.isWhitespace(c2));
    char c1;
    if (c2 != '"')
    {
      c1 = c2;
      if (c2 != '\'');
    }
    else
    {
      while (true)
      {
        c1 = next();
        if (c1 < ' ')
          throw syntaxError("Unterminated string.");
        if (c1 == c2)
          return localStringBuffer.toString();
        localStringBuffer.append(c1);
      }
    }
    while ((c1 != 0) && (!Character.isWhitespace(c1)))
    {
      localStringBuffer.append(c1);
      c1 = next();
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.org.json.HTTPTokener
 * JD-Core Version:    0.6.2
 */