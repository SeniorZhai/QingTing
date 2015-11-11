package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.Base64;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public final class JSONScanner extends JSONLexerBase
{
  protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
  public final int ISO8601_LEN_0 = "0000-00-00".length();
  public final int ISO8601_LEN_1 = "0000-00-00T00:00:00".length();
  public final int ISO8601_LEN_2 = "0000-00-00T00:00:00.000".length();
  private final String text;

  public JSONScanner(String paramString)
  {
    this(paramString, JSON.DEFAULT_PARSER_FEATURE);
  }

  public JSONScanner(String paramString, int paramInt)
  {
    this.features = paramInt;
    this.text = paramString;
    this.bp = -1;
    next();
    if (this.ch == 65279)
      next();
  }

  public JSONScanner(char[] paramArrayOfChar, int paramInt)
  {
    this(paramArrayOfChar, paramInt, JSON.DEFAULT_PARSER_FEATURE);
  }

  public JSONScanner(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this(new String(paramArrayOfChar, 0, paramInt1), paramInt2);
  }

  static final boolean charArrayCompare(String paramString, int paramInt, char[] paramArrayOfChar)
  {
    int j = paramArrayOfChar.length;
    if (j + paramInt > paramString.length())
      return false;
    int i = 0;
    while (true)
    {
      if (i >= j)
        break label45;
      if (paramArrayOfChar[i] != paramString.charAt(paramInt + i))
        break;
      i += 1;
    }
    label45: return true;
  }

  static boolean checkDate(char paramChar1, char paramChar2, char paramChar3, char paramChar4, char paramChar5, char paramChar6, int paramInt1, int paramInt2)
  {
    if ((paramChar1 != '1') && (paramChar1 != '2'));
    label71: 
    do
    {
      do
      {
        do
          return false;
        while ((paramChar2 < '0') || (paramChar2 > '9') || (paramChar3 < '0') || (paramChar3 > '9') || (paramChar4 < '0') || (paramChar4 > '9'));
        if (paramChar5 != '0')
          break;
      }
      while ((paramChar6 < '1') || (paramChar6 > '9'));
      if (paramInt1 != 48)
        break label124;
    }
    while ((paramInt2 < 49) || (paramInt2 > 57));
    label124: 
    do
    {
      do
      {
        return true;
        if (paramChar5 != '1')
          break;
        if ((paramChar6 == '0') || (paramChar6 == '1') || (paramChar6 == '2'))
          break label71;
        return false;
        if ((paramInt1 != 49) && (paramInt1 != 50))
          break label154;
        if (paramInt2 < 48)
          break;
      }
      while (paramInt2 <= 57);
      return false;
      if (paramInt1 != 51)
        break;
    }
    while ((paramInt2 == 48) || (paramInt2 == 49));
    label154: return false;
  }

  private boolean checkTime(char paramChar1, char paramChar2, char paramChar3, char paramChar4, char paramChar5, char paramChar6)
  {
    if (paramChar1 == '0')
      if ((paramChar2 >= '0') && (paramChar2 <= '9'))
        break label38;
    label38: label64: 
    do
    {
      do
      {
        do
        {
          return false;
          if (paramChar1 != '1')
            break;
        }
        while ((paramChar2 < '0') || (paramChar2 > '9'));
        if ((paramChar3 < '0') || (paramChar3 > '5'))
          break label114;
      }
      while ((paramChar4 < '0') || (paramChar4 > '9'));
      if ((paramChar5 < '0') || (paramChar5 > '5'))
        break label129;
    }
    while ((paramChar6 < '0') || (paramChar6 > '9'));
    label114: 
    do
    {
      return true;
      if ((paramChar1 != '2') || (paramChar2 < '0'))
        break;
      if (paramChar2 <= '4')
        break label38;
      return false;
      if (paramChar3 != '6')
        break;
      if (paramChar4 == '0')
        break label64;
      return false;
      if (paramChar5 != '6')
        break;
    }
    while (paramChar6 == '0');
    label129: return false;
  }

  private void setCalendar(char paramChar1, char paramChar2, char paramChar3, char paramChar4, char paramChar5, char paramChar6, char paramChar7, char paramChar8)
  {
    Locale localLocale = Locale.getDefault();
    this.calendar = Calendar.getInstance(TimeZone.getDefault(), localLocale);
    paramChar1 = digits[paramChar1];
    paramChar2 = digits[paramChar2];
    paramChar3 = digits[paramChar3];
    paramChar4 = digits[paramChar4];
    paramChar5 = digits[paramChar5];
    paramChar6 = digits[paramChar6];
    paramChar7 = digits[paramChar7];
    paramChar8 = digits[paramChar8];
    this.calendar.set(1, paramChar1 * 'Ϩ' + paramChar2 * 'd' + paramChar3 * '\n' + paramChar4);
    this.calendar.set(2, paramChar5 * '\n' + paramChar6 - 1);
    this.calendar.set(5, paramChar7 * '\n' + paramChar8);
  }

  public final String addSymbol(int paramInt1, int paramInt2, int paramInt3, SymbolTable paramSymbolTable)
  {
    return paramSymbolTable.addSymbol(this.text, paramInt1, paramInt2, paramInt3);
  }

  protected final void arrayCopy(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    this.text.getChars(paramInt1, paramInt1 + paramInt3, paramArrayOfChar, paramInt2);
  }

  public byte[] bytesValue()
  {
    return Base64.decodeFast(this.text, this.np + 1, this.sp);
  }

  public final boolean charArrayCompare(char[] paramArrayOfChar)
  {
    return charArrayCompare(this.text, this.bp, paramArrayOfChar);
  }

  public final char charAt(int paramInt)
  {
    if (paramInt >= this.text.length())
      return '\032';
    return this.text.charAt(paramInt);
  }

  protected final void copyTo(int paramInt1, int paramInt2, char[] paramArrayOfChar)
  {
    this.text.getChars(paramInt1, paramInt1 + paramInt2, paramArrayOfChar, 0);
  }

  public final int indexOf(char paramChar, int paramInt)
  {
    return this.text.indexOf(paramChar, paramInt);
  }

  public boolean isEOF()
  {
    return (this.bp == this.text.length()) || ((this.ch == '\032') && (this.bp + 1 == this.text.length()));
  }

  public final char next()
  {
    int i = this.bp + 1;
    this.bp = i;
    char c = charAt(i);
    this.ch = c;
    return c;
  }

  public final String numberString()
  {
    int k = charAt(this.np + this.sp - 1);
    int j = this.sp;
    int i;
    if ((k != 76) && (k != 83) && (k != 66) && (k != 70))
    {
      i = j;
      if (k != 68);
    }
    else
    {
      i = j - 1;
    }
    return this.text.substring(this.np, this.np + i);
  }

  public boolean scanFieldBoolean(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(this.text, this.bp, paramArrayOfChar))
    {
      this.matchStat = -2;
      return false;
    }
    int j = this.bp + paramArrayOfChar.length;
    int i = j + 1;
    j = charAt(j);
    boolean bool;
    if (j == 116)
    {
      j = i + 1;
      if (charAt(i) != 'r')
      {
        this.matchStat = -1;
        return false;
      }
      i = j + 1;
      if (charAt(j) != 'u')
      {
        this.matchStat = -1;
        return false;
      }
      if (charAt(i) != 'e')
      {
        this.matchStat = -1;
        return false;
      }
      this.bp = (i + 1);
      i = charAt(this.bp);
      bool = true;
    }
    while (i == 44)
    {
      i = this.bp + 1;
      this.bp = i;
      this.ch = charAt(i);
      this.matchStat = 3;
      this.token = 16;
      return bool;
      if (j == 102)
      {
        j = i + 1;
        if (charAt(i) != 'a')
        {
          this.matchStat = -1;
          return false;
        }
        i = j + 1;
        if (charAt(j) != 'l')
        {
          this.matchStat = -1;
          return false;
        }
        j = i + 1;
        if (charAt(i) != 's')
        {
          this.matchStat = -1;
          return false;
        }
        if (charAt(j) != 'e')
        {
          this.matchStat = -1;
          return false;
        }
        this.bp = (j + 1);
        i = charAt(this.bp);
        bool = false;
      }
      else
      {
        this.matchStat = -1;
        return false;
      }
    }
    if (i == 125)
    {
      i = this.bp + 1;
      this.bp = i;
      i = charAt(i);
      if (i == 44)
      {
        this.token = 16;
        i = this.bp + 1;
        this.bp = i;
        this.ch = charAt(i);
      }
      while (true)
      {
        this.matchStat = 4;
        return bool;
        if (i == 93)
        {
          this.token = 15;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        else if (i == 125)
        {
          this.token = 13;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        else
        {
          if (i != 26)
            break;
          this.token = 20;
        }
      }
      this.matchStat = -1;
      return false;
    }
    this.matchStat = -1;
    return false;
  }

  public int scanFieldInt(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    int m = this.bp;
    char c = this.ch;
    if (!charArrayCompare(this.text, this.bp, paramArrayOfChar))
    {
      this.matchStat = -2;
      j = 0;
    }
    int i;
    int n;
    do
    {
      return j;
      j = this.bp + paramArrayOfChar.length;
      i = charAt(j);
      if ((i >= 48) && (i <= 57))
      {
        i = digits[i];
        j += 1;
        int k;
        while (true)
        {
          k = j + 1;
          n = charAt(j);
          if ((n < 48) || (n > 57))
            break;
          i = i * 10 + digits[n];
          j = k;
        }
        if (n == 46)
        {
          this.matchStat = -1;
          return 0;
        }
        this.bp = (k - 1);
        if (i < 0)
        {
          this.matchStat = -1;
          return 0;
        }
      }
      else
      {
        this.matchStat = -1;
        return 0;
      }
      if (n == 44)
      {
        j = this.bp + 1;
        this.bp = j;
        this.ch = charAt(j);
        this.matchStat = 3;
        this.token = 16;
        return i;
      }
      j = i;
    }
    while (n != 125);
    int j = this.bp + 1;
    this.bp = j;
    j = charAt(j);
    if (j == 44)
    {
      this.token = 16;
      j = this.bp + 1;
      this.bp = j;
      this.ch = charAt(j);
    }
    while (true)
    {
      this.matchStat = 4;
      return i;
      if (j == 93)
      {
        this.token = 15;
        j = this.bp + 1;
        this.bp = j;
        this.ch = charAt(j);
      }
      else if (j == 125)
      {
        this.token = 13;
        j = this.bp + 1;
        this.bp = j;
        this.ch = charAt(j);
      }
      else
      {
        if (j != 26)
          break;
        this.token = 20;
      }
    }
    this.bp = m;
    this.ch = c;
    this.matchStat = -1;
    return 0;
  }

  public long scanFieldLong(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    int k = this.bp;
    char c = this.ch;
    if (!charArrayCompare(this.text, this.bp, paramArrayOfChar))
    {
      this.matchStat = -2;
      return 0L;
    }
    int i = this.bp + paramArrayOfChar.length;
    int j = charAt(i);
    long l;
    if ((j >= 48) && (j <= 57))
    {
      l = digits[j];
      i += 1;
      while (true)
      {
        j = i + 1;
        i = charAt(i);
        if ((i < 48) || (i > 57))
          break;
        l = 10L * l + digits[i];
        i = j;
      }
      if (i == 46)
      {
        this.matchStat = -1;
        return 0L;
      }
      this.bp = (j - 1);
      if (l < 0L)
      {
        this.bp = k;
        this.ch = c;
        this.matchStat = -1;
        return 0L;
      }
    }
    else
    {
      this.bp = k;
      this.ch = c;
      this.matchStat = -1;
      return 0L;
    }
    if (i == 44)
    {
      i = this.bp + 1;
      this.bp = i;
      charAt(i);
      this.matchStat = 3;
      this.token = 16;
      return l;
    }
    if (i == 125)
    {
      i = this.bp + 1;
      this.bp = i;
      i = charAt(i);
      if (i == 44)
      {
        this.token = 16;
        i = this.bp + 1;
        this.bp = i;
        this.ch = charAt(i);
      }
      while (true)
      {
        this.matchStat = 4;
        return l;
        if (i == 93)
        {
          this.token = 15;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        else if (i == 125)
        {
          this.token = 13;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        else
        {
          if (i != 26)
            break;
          this.token = 20;
        }
      }
      this.bp = k;
      this.ch = c;
      this.matchStat = -1;
      return 0L;
    }
    this.matchStat = -1;
    return 0L;
  }

  public String scanFieldString(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    int m = this.bp;
    char c1 = this.ch;
    if (!charArrayCompare(this.text, this.bp, paramArrayOfChar))
    {
      this.matchStat = -2;
      return stringDefaultValue();
    }
    int j = this.bp + paramArrayOfChar.length;
    int i = j + 1;
    if (charAt(j) != '"')
    {
      this.matchStat = -1;
      return stringDefaultValue();
    }
    int k = 0;
    int n = this.text.indexOf('"', i);
    if (n == -1)
      throw new JSONException("unclosed str");
    paramArrayOfChar = subString(i, n - i);
    i = 0;
    while (true)
    {
      j = k;
      if (i < paramArrayOfChar.length())
      {
        if (paramArrayOfChar.charAt(i) == '\\')
          j = 1;
      }
      else
      {
        if (j == 0)
          break;
        this.matchStat = -1;
        return stringDefaultValue();
      }
      i += 1;
    }
    this.bp = (n + 1);
    char c2 = charAt(this.bp);
    this.ch = c2;
    if (c2 == ',')
    {
      i = this.bp + 1;
      this.bp = i;
      this.ch = charAt(i);
      this.matchStat = 3;
      return paramArrayOfChar;
    }
    if (c2 == '}')
    {
      i = this.bp + 1;
      this.bp = i;
      i = charAt(i);
      if (i == 44)
      {
        this.token = 16;
        i = this.bp + 1;
        this.bp = i;
        this.ch = charAt(i);
      }
      while (true)
      {
        this.matchStat = 4;
        return paramArrayOfChar;
        if (i == 93)
        {
          this.token = 15;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        else if (i == 125)
        {
          this.token = 13;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        else
        {
          if (i != 26)
            break;
          this.token = 20;
        }
      }
      this.bp = m;
      this.ch = c1;
      this.matchStat = -1;
      return stringDefaultValue();
    }
    this.matchStat = -1;
    return stringDefaultValue();
  }

  public Collection<String> scanFieldStringArray(char[] paramArrayOfChar, Class<?> paramClass)
  {
    this.matchStat = 0;
    if (!charArrayCompare(this.text, this.bp, paramArrayOfChar))
    {
      this.matchStat = -2;
      return null;
    }
    if (paramClass.isAssignableFrom(HashSet.class))
      paramClass = new HashSet();
    while (true)
    {
      i = this.bp + paramArrayOfChar.length;
      j = i + 1;
      if (charAt(i) != '[')
      {
        this.matchStat = -1;
        return null;
        if (paramClass.isAssignableFrom(ArrayList.class))
        {
          paramClass = new ArrayList();
          continue;
        }
        try
        {
          paramClass = (Collection)paramClass.newInstance();
        }
        catch (Exception paramArrayOfChar)
        {
          throw new JSONException(paramArrayOfChar.getMessage(), paramArrayOfChar);
        }
      }
    }
    int i = j + 1;
    int j = charAt(j);
    if (j != 34)
    {
      this.matchStat = -1;
      return null;
    }
    j = i;
    while (true)
    {
      int k = j;
      j = k + 1;
      k = charAt(k);
      if (k == 34)
      {
        paramClass.add(this.text.substring(i, j - 1));
        i = j + 1;
        j = charAt(j);
        if (j != 44)
          break label252;
        j = charAt(i);
        i += 1;
        break;
      }
      if (k == 92)
      {
        this.matchStat = -1;
        return null;
        label252: if (j == 93)
        {
          j = charAt(i);
          this.bp = (i + 1);
          if (j == 44)
          {
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return paramClass;
          }
        }
        else
        {
          this.matchStat = -1;
          return null;
        }
        if (j == 125)
        {
          char c = charAt(this.bp);
          if (c == ',')
          {
            this.token = 16;
            i = this.bp + 1;
            this.bp = i;
            this.ch = charAt(i);
          }
          while (true)
          {
            this.matchStat = 4;
            return paramClass;
            if (c == ']')
            {
              this.token = 15;
              i = this.bp + 1;
              this.bp = i;
              this.ch = charAt(i);
            }
            else if (c == '}')
            {
              this.token = 13;
              i = this.bp + 1;
              this.bp = i;
              this.ch = charAt(i);
            }
            else
            {
              if (c != '\032')
                break;
              this.token = 20;
              this.ch = c;
            }
          }
          this.matchStat = -1;
          return null;
        }
        this.matchStat = -1;
        return null;
      }
    }
  }

  public String scanFieldSymbol(char[] paramArrayOfChar, SymbolTable paramSymbolTable)
  {
    this.matchStat = 0;
    if (!charArrayCompare(this.text, this.bp, paramArrayOfChar))
    {
      this.matchStat = -2;
      return null;
    }
    int i = this.bp + paramArrayOfChar.length;
    int k = i + 1;
    if (charAt(i) != '"')
    {
      this.matchStat = -1;
      return null;
    }
    i = 0;
    int m;
    for (int j = k; ; j = m)
    {
      m = j + 1;
      j = charAt(j);
      char c;
      if (j == 34)
      {
        this.bp = m;
        c = charAt(this.bp);
        this.ch = c;
        paramArrayOfChar = paramSymbolTable.addSymbol(this.text, k, m - k - 1, i);
        if (c == ',')
        {
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
          this.matchStat = 3;
          return paramArrayOfChar;
        }
      }
      else
      {
        i = i * 31 + j;
        if (j != 92)
          continue;
        this.matchStat = -1;
        return null;
      }
      if (c == '}')
      {
        i = this.bp + 1;
        this.bp = i;
        i = charAt(i);
        if (i == 44)
        {
          this.token = 16;
          i = this.bp + 1;
          this.bp = i;
          this.ch = charAt(i);
        }
        while (true)
        {
          this.matchStat = 4;
          return paramArrayOfChar;
          if (i == 93)
          {
            this.token = 15;
            i = this.bp + 1;
            this.bp = i;
            this.ch = charAt(i);
          }
          else if (i == 125)
          {
            this.token = 13;
            i = this.bp + 1;
            this.bp = i;
            this.ch = charAt(i);
          }
          else
          {
            if (i != 26)
              break;
            this.token = 20;
          }
        }
        this.matchStat = -1;
        return null;
      }
      this.matchStat = -1;
      return null;
    }
  }

  public boolean scanISO8601DateIfMatch()
  {
    return scanISO8601DateIfMatch(true);
  }

  public boolean scanISO8601DateIfMatch(boolean paramBoolean)
  {
    int m = this.text.length() - this.bp;
    if ((!paramBoolean) && (m > 13))
    {
      i = charAt(this.bp);
      j = charAt(this.bp + 1);
      k = charAt(this.bp + 2);
      n = charAt(this.bp + 3);
      i1 = charAt(this.bp + 4);
      i2 = charAt(this.bp + 5);
      int i3 = charAt(this.bp + m - 1);
      int i4 = charAt(this.bp + m - 2);
      if ((i == 47) && (j == 68) && (k == 97) && (n == 116) && (i1 == 101) && (i2 == 40) && (i3 == 47) && (i4 == 41))
      {
        j = -1;
        i = 6;
        if (i < m)
        {
          n = charAt(this.bp + i);
          if (n == 43)
            k = i;
          do
          {
            i += 1;
            j = k;
            break;
            if (n < 48)
              break label250;
            k = j;
          }
          while (n <= 57);
        }
        label250: if (j == -1)
          return false;
        i = this.bp + 6;
        long l = Long.parseLong(subString(i, j - i));
        Locale localLocale = Locale.getDefault();
        this.calendar = Calendar.getInstance(TimeZone.getDefault(), localLocale);
        this.calendar.setTimeInMillis(l);
        this.token = 5;
        return true;
      }
    }
    if ((m == 8) || (m == 14) || (m == 17))
    {
      if (paramBoolean)
        return false;
      c1 = charAt(this.bp);
      c2 = charAt(this.bp + 1);
      c3 = charAt(this.bp + 2);
      c4 = charAt(this.bp + 3);
      c5 = charAt(this.bp + 4);
      c6 = charAt(this.bp + 5);
      c7 = charAt(this.bp + 6);
      c8 = charAt(this.bp + 7);
      if (!checkDate(c1, c2, c3, c4, c5, c6, c7, c8))
        return false;
      setCalendar(c1, c2, c3, c4, c5, c6, c7, c8);
      if (m != 8)
      {
        c1 = charAt(this.bp + 8);
        c2 = charAt(this.bp + 9);
        c3 = charAt(this.bp + 10);
        c4 = charAt(this.bp + 11);
        c5 = charAt(this.bp + 12);
        c6 = charAt(this.bp + 13);
        if (!checkTime(c1, c2, c3, c4, c5, c6))
          return false;
        if (m == 17)
        {
          i = charAt(this.bp + 14);
          j = charAt(this.bp + 15);
          k = charAt(this.bp + 16);
          if ((i < 48) || (i > 57))
            return false;
          if ((j < 48) || (j > 57))
            return false;
          if ((k < 48) || (k > 57))
            return false;
          i = digits[i] * 100 + digits[j] * 10 + digits[k];
          n = digits[c1] * 10 + digits[c2];
          k = digits[c3] * 10 + digits[c4];
          m = digits[c5] * 10 + digits[c6];
          j = i;
          i = n;
        }
      }
      while (true)
      {
        this.calendar.set(11, i);
        this.calendar.set(12, k);
        this.calendar.set(13, m);
        this.calendar.set(14, j);
        this.token = 5;
        return true;
        i = 0;
        break;
        i = 0;
        k = 0;
        m = 0;
        j = 0;
      }
    }
    if (m < this.ISO8601_LEN_0)
      return false;
    if (charAt(this.bp + 4) != '-')
      return false;
    if (charAt(this.bp + 7) != '-')
      return false;
    char c1 = charAt(this.bp);
    char c2 = charAt(this.bp + 1);
    char c3 = charAt(this.bp + 2);
    char c4 = charAt(this.bp + 3);
    char c5 = charAt(this.bp + 5);
    char c6 = charAt(this.bp + 6);
    char c7 = charAt(this.bp + 8);
    char c8 = charAt(this.bp + 9);
    if (!checkDate(c1, c2, c3, c4, c5, c6, c7, c8))
      return false;
    setCalendar(c1, c2, c3, c4, c5, c6, c7, c8);
    int i = charAt(this.bp + 10);
    if ((i == 84) || ((i == 32) && (!paramBoolean)))
    {
      if (m < this.ISO8601_LEN_1)
        return false;
    }
    else
    {
      if ((i == 34) || (i == 26))
      {
        this.calendar.set(11, 0);
        this.calendar.set(12, 0);
        this.calendar.set(13, 0);
        this.calendar.set(14, 0);
        i = this.bp + 10;
        this.bp = i;
        this.ch = charAt(i);
        this.token = 5;
        return true;
      }
      return false;
    }
    if (charAt(this.bp + 13) != ':')
      return false;
    if (charAt(this.bp + 16) != ':')
      return false;
    c1 = charAt(this.bp + 11);
    c2 = charAt(this.bp + 12);
    c3 = charAt(this.bp + 14);
    c4 = charAt(this.bp + 15);
    c5 = charAt(this.bp + 17);
    c6 = charAt(this.bp + 18);
    if (!checkTime(c1, c2, c3, c4, c5, c6))
      return false;
    i = digits[c1];
    int j = digits[c2];
    int k = digits[c3];
    int n = digits[c4];
    int i1 = digits[c5];
    int i2 = digits[c6];
    this.calendar.set(11, i * 10 + j);
    this.calendar.set(12, k * 10 + n);
    this.calendar.set(13, i1 * 10 + i2);
    if (charAt(this.bp + 19) == '.')
    {
      if (m < this.ISO8601_LEN_2)
        return false;
    }
    else
    {
      this.calendar.set(14, 0);
      i = this.bp + 19;
      this.bp = i;
      this.ch = charAt(i);
      this.token = 5;
      return true;
    }
    j = charAt(this.bp + 20);
    k = charAt(this.bp + 21);
    i = charAt(this.bp + 22);
    if ((j < 48) || (j > 57))
      return false;
    if ((k < 48) || (k > 57))
      return false;
    if ((i < 48) || (i > 57))
      return false;
    j = digits[j];
    k = digits[k];
    i = digits[i];
    this.calendar.set(14, j * 100 + k * 10 + i);
    i = this.bp + 23;
    this.bp = i;
    this.ch = charAt(i);
    this.token = 5;
    return true;
  }

  public final int scanType(String paramString)
  {
    int k = -1;
    this.matchStat = 0;
    if (!charArrayCompare(this.text, this.bp, typeFieldName))
      i = -2;
    int j;
    label81: 
    do
    {
      return i;
      int m = this.bp + typeFieldName.length;
      int n = paramString.length();
      j = 0;
      while (true)
      {
        if (j >= n)
          break label81;
        i = k;
        if (paramString.charAt(j) != charAt(m + j))
          break;
        j += 1;
      }
      j = m + n;
      i = k;
    }
    while (charAt(j) != '"');
    j += 1;
    this.ch = charAt(j);
    if (this.ch == ',')
    {
      i = j + 1;
      this.ch = charAt(i);
      this.bp = i;
      this.token = 16;
      return 3;
    }
    int i = j;
    if (this.ch == '}')
    {
      j += 1;
      this.ch = charAt(j);
      if (this.ch != ',')
        break label215;
      this.token = 16;
      i = j + 1;
      this.ch = charAt(i);
    }
    while (true)
    {
      this.matchStat = 4;
      this.bp = i;
      return this.matchStat;
      label215: if (this.ch == ']')
      {
        this.token = 15;
        i = j + 1;
        this.ch = charAt(i);
      }
      else if (this.ch == '}')
      {
        this.token = 13;
        i = j + 1;
        this.ch = charAt(i);
      }
      else
      {
        i = k;
        if (this.ch != '\032')
          break;
        this.token = 20;
        i = j;
      }
    }
  }

  public final String stringVal()
  {
    if (!this.hasSpecial)
      return this.text.substring(this.np + 1, this.np + 1 + this.sp);
    return new String(this.sbuf, 0, this.sp);
  }

  public final String subString(int paramInt1, int paramInt2)
  {
    return this.text.substring(paramInt1, paramInt1 + paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.JSONScanner
 * JD-Core Version:    0.6.2
 */