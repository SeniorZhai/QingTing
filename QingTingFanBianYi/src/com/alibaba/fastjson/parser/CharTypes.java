package com.alibaba.fastjson.parser;

public final class CharTypes
{
  public static final char[] ASCII_CHARS = { 48, 48, 48, 49, 48, 50, 48, 51, 48, 52, 48, 53, 48, 54, 48, 55, 48, 56, 48, 57, 48, 65, 48, 66, 48, 67, 48, 68, 48, 69, 48, 70, 49, 48, 49, 49, 49, 50, 49, 51, 49, 52, 49, 53, 49, 54, 49, 55, 49, 56, 49, 57, 49, 65, 49, 66, 49, 67, 49, 68, 49, 69, 49, 70, 50, 48, 50, 49, 50, 50, 50, 51, 50, 52, 50, 53, 50, 54, 50, 55, 50, 56, 50, 57, 50, 65, 50, 66, 50, 67, 50, 68, 50, 69, 50, 70 };
  public static final char[] digits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  public static final boolean[] firstIdentifierFlags = new boolean[256];
  public static final boolean[] identifierFlags;
  public static final char[] replaceChars;
  public static final boolean[] specicalFlags_doubleQuotes;
  public static final boolean[] specicalFlags_singleQuotes;

  static
  {
    int i = 0;
    if (i < firstIdentifierFlags.length)
    {
      if ((i >= 65) && (i <= 90))
        firstIdentifierFlags[i] = true;
      while (true)
      {
        i = (char)(i + 1);
        break;
        if ((i >= 97) && (i <= 122))
          firstIdentifierFlags[i] = true;
        else if (i == 95)
          firstIdentifierFlags[i] = true;
      }
    }
    identifierFlags = new boolean[256];
    i = 0;
    if (i < identifierFlags.length)
    {
      if ((i >= 65) && (i <= 90))
        identifierFlags[i] = true;
      while (true)
      {
        i = (char)(i + 1);
        break;
        if ((i >= 97) && (i <= 122))
          identifierFlags[i] = true;
        else if (i == 95)
          identifierFlags[i] = true;
        else if ((i >= 48) && (i <= 57))
          identifierFlags[i] = true;
      }
    }
    specicalFlags_doubleQuotes = new boolean[''];
    specicalFlags_singleQuotes = new boolean[''];
    replaceChars = new char[''];
    specicalFlags_doubleQuotes[0] = true;
    specicalFlags_doubleQuotes[1] = true;
    specicalFlags_doubleQuotes[2] = true;
    specicalFlags_doubleQuotes[3] = true;
    specicalFlags_doubleQuotes[4] = true;
    specicalFlags_doubleQuotes[5] = true;
    specicalFlags_doubleQuotes[6] = true;
    specicalFlags_doubleQuotes[7] = true;
    specicalFlags_doubleQuotes[8] = true;
    specicalFlags_doubleQuotes[9] = true;
    specicalFlags_doubleQuotes[10] = true;
    specicalFlags_doubleQuotes[11] = true;
    specicalFlags_doubleQuotes[12] = true;
    specicalFlags_doubleQuotes[13] = true;
    specicalFlags_doubleQuotes[34] = true;
    specicalFlags_doubleQuotes[92] = true;
    specicalFlags_singleQuotes[0] = true;
    specicalFlags_singleQuotes[1] = true;
    specicalFlags_singleQuotes[2] = true;
    specicalFlags_singleQuotes[3] = true;
    specicalFlags_singleQuotes[4] = true;
    specicalFlags_singleQuotes[5] = true;
    specicalFlags_singleQuotes[6] = true;
    specicalFlags_singleQuotes[7] = true;
    specicalFlags_singleQuotes[8] = true;
    specicalFlags_singleQuotes[9] = true;
    specicalFlags_singleQuotes[10] = true;
    specicalFlags_singleQuotes[11] = true;
    specicalFlags_singleQuotes[12] = true;
    specicalFlags_singleQuotes[13] = true;
    specicalFlags_singleQuotes[39] = true;
    specicalFlags_singleQuotes[92] = true;
    replaceChars[0] = '0';
    replaceChars[1] = '1';
    replaceChars[2] = '2';
    replaceChars[3] = '3';
    replaceChars[4] = '4';
    replaceChars[5] = '5';
    replaceChars[6] = '6';
    replaceChars[7] = '7';
    replaceChars[8] = 'b';
    replaceChars[9] = 't';
    replaceChars[10] = 'n';
    replaceChars[11] = 'v';
    replaceChars[12] = 'f';
    replaceChars[13] = 'r';
    replaceChars[34] = '"';
    replaceChars[39] = '\'';
    replaceChars[47] = '/';
    replaceChars[92] = '\\';
  }

  public static boolean isSpecial_doubleQuotes(char paramChar)
  {
    return (paramChar < specicalFlags_doubleQuotes.length) && (specicalFlags_doubleQuotes[paramChar] != 0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.CharTypes
 * JD-Core Version:    0.6.2
 */