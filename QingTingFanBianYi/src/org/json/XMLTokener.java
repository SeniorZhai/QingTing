package org.json;

import java.util.HashMap;

public class XMLTokener extends JSONTokener
{
  public static final HashMap entity = new HashMap(8);

  static
  {
    entity.put("amp", XML.AMP);
    entity.put("apos", XML.APOS);
    entity.put("gt", XML.GT);
    entity.put("lt", XML.LT);
    entity.put("quot", XML.QUOT);
  }

  public XMLTokener(String paramString)
  {
    super(paramString);
  }

  public String nextCDATA()
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i;
    do
    {
      char c = next();
      if (c == 0)
        throw syntaxError("Unclosed CDATA");
      localStringBuffer.append(c);
      i = localStringBuffer.length() - 3;
    }
    while ((i < 0) || (localStringBuffer.charAt(i) != ']') || (localStringBuffer.charAt(i + 1) != ']') || (localStringBuffer.charAt(i + 2) != '>'));
    localStringBuffer.setLength(i);
    return localStringBuffer.toString();
  }

  public Object nextContent()
    throws JSONException
  {
    char c;
    do
      c = next();
    while (Character.isWhitespace(c));
    if (c == 0)
      return null;
    if (c == '<')
      return XML.LT;
    StringBuffer localStringBuffer = new StringBuffer();
    if ((c == '<') || (c == 0))
    {
      back();
      return localStringBuffer.toString().trim();
    }
    if (c == '&')
      localStringBuffer.append(nextEntity(c));
    while (true)
    {
      c = next();
      break;
      localStringBuffer.append(c);
    }
  }

  public Object nextEntity(char paramChar)
    throws JSONException
  {
    Object localObject1 = new StringBuffer();
    char c;
    while (true)
    {
      c = next();
      if ((!Character.isLetterOrDigit(c)) && (c != '#'))
        break;
      ((StringBuffer)localObject1).append(Character.toLowerCase(c));
    }
    if (c == ';')
    {
      localObject1 = ((StringBuffer)localObject1).toString();
      Object localObject2 = entity.get(localObject1);
      if (localObject2 != null)
        return localObject2;
    }
    else
    {
      throw syntaxError("Missing ';' in XML entity: &" + localObject1);
    }
    return paramChar + (String)localObject1 + ";";
  }

  public Object nextMeta()
    throws JSONException
  {
    char c1;
    do
      c1 = next();
    while (Character.isWhitespace(c1));
    switch (c1)
    {
    default:
    case '\000':
    case '<':
    case '>':
    case '/':
    case '=':
    case '!':
    case '?':
    case '"':
    case '\'':
    }
    while (true)
    {
      c1 = next();
      if (Character.isWhitespace(c1))
      {
        return Boolean.TRUE;
        throw syntaxError("Misshaped meta tag");
        return XML.LT;
        return XML.GT;
        return XML.SLASH;
        return XML.EQ;
        return XML.BANG;
        return XML.QUEST;
        char c2;
        do
        {
          c2 = next();
          if (c2 == 0)
            throw syntaxError("Unterminated string");
        }
        while (c2 != c1);
        return Boolean.TRUE;
      }
      switch (c1)
      {
      default:
      case '\000':
      case '!':
      case '"':
      case '\'':
      case '/':
      case '<':
      case '=':
      case '>':
      case '?':
      }
    }
    back();
    return Boolean.TRUE;
  }

  public Object nextToken()
    throws JSONException
  {
    char c1;
    do
      c1 = next();
    while (Character.isWhitespace(c1));
    StringBuffer localStringBuffer;
    switch (c1)
    {
    default:
      localStringBuffer = new StringBuffer();
    case '\000':
    case '<':
    case '>':
    case '/':
    case '=':
    case '!':
    case '?':
    case '"':
    case '\'':
    }
    while (true)
    {
      localStringBuffer.append(c1);
      c1 = next();
      if (Character.isWhitespace(c1))
      {
        return localStringBuffer.toString();
        throw syntaxError("Misshaped element");
        throw syntaxError("Misplaced '<'");
        return XML.GT;
        return XML.SLASH;
        return XML.EQ;
        return XML.BANG;
        return XML.QUEST;
        localStringBuffer = new StringBuffer();
        while (true)
        {
          char c2 = next();
          if (c2 == 0)
            throw syntaxError("Unterminated string");
          if (c2 == c1)
            return localStringBuffer.toString();
          if (c2 == '&')
            localStringBuffer.append(nextEntity(c2));
          else
            localStringBuffer.append(c2);
        }
      }
      switch (c1)
      {
      default:
      case '\000':
      case '!':
      case '/':
      case '=':
      case '>':
      case '?':
      case '[':
      case ']':
      case '"':
      case '\'':
      case '<':
      }
    }
    back();
    return localStringBuffer.toString();
    throw syntaxError("Bad character in a name");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.XMLTokener
 * JD-Core Version:    0.6.2
 */