package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

public abstract class JSONLexerBase
  implements JSONLexer, Closeable
{
  protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
  protected static final int INT_N_MULTMAX_RADIX_TEN = -214748364;
  protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
  protected static final long N_MULTMAX_RADIX_TEN = -922337203685477580L;
  private static final ThreadLocal<SoftReference<char[]>> SBUF_REF_LOCAL = new ThreadLocal();
  protected static final int[] digits;
  protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
  protected static boolean[] whitespaceFlags = new boolean[256];
  protected int bp;
  protected Calendar calendar = null;
  protected char ch;
  protected int eofPos;
  protected int features = JSON.DEFAULT_PARSER_FEATURE;
  protected boolean hasSpecial;
  protected Keywords keywods = Keywords.DEFAULT_KEYWORDS;
  public int matchStat = 0;
  protected int np;
  protected int pos;
  protected char[] sbuf;
  protected int sp;
  protected int token;

  static
  {
    whitespaceFlags[32] = true;
    whitespaceFlags[10] = true;
    whitespaceFlags[13] = true;
    whitespaceFlags[9] = true;
    whitespaceFlags[12] = true;
    whitespaceFlags[8] = true;
    digits = new int[103];
    int i = 48;
    while (i <= 57)
    {
      digits[i] = (i - 48);
      i += 1;
    }
    i = 97;
    while (i <= 102)
    {
      digits[i] = (i - 97 + 10);
      i += 1;
    }
    i = 65;
    while (i <= 70)
    {
      digits[i] = (i - 65 + 10);
      i += 1;
    }
  }

  public JSONLexerBase()
  {
    SoftReference localSoftReference = (SoftReference)SBUF_REF_LOCAL.get();
    if (localSoftReference != null)
    {
      this.sbuf = ((char[])localSoftReference.get());
      SBUF_REF_LOCAL.set(null);
    }
    if (this.sbuf == null)
      this.sbuf = new char[64];
  }

  public static final boolean isWhitespace(char paramChar)
  {
    return (paramChar == ' ') || (paramChar == '\n') || (paramChar == '\r') || (paramChar == '\t') || (paramChar == '\f') || (paramChar == '\b');
  }

  private final void scanStringSingleQuote()
  {
    this.np = this.bp;
    this.hasSpecial = false;
    while (true)
    {
      char c = next();
      if (c == '\'')
      {
        this.token = 4;
        next();
        return;
      }
      if (c == '\032')
        throw new JSONException("unclosed single-quote string");
      char[] arrayOfChar;
      int i;
      if (c == '\\')
      {
        if (!this.hasSpecial)
        {
          this.hasSpecial = true;
          if (this.sp > this.sbuf.length)
          {
            arrayOfChar = new char[this.sp * 2];
            System.arraycopy(this.sbuf, 0, arrayOfChar, 0, this.sbuf.length);
            this.sbuf = arrayOfChar;
          }
          copyTo(this.np + 1, this.sp, this.sbuf);
        }
        c = next();
        switch (c)
        {
        default:
          this.ch = c;
          throw new JSONException("unclosed single-quote string");
        case '0':
          putChar('\000');
          break;
        case '1':
          putChar('\001');
          break;
        case '2':
          putChar('\002');
          break;
        case '3':
          putChar('\003');
          break;
        case '4':
          putChar('\004');
          break;
        case '5':
          putChar('\005');
          break;
        case '6':
          putChar('\006');
          break;
        case '7':
          putChar('\007');
          break;
        case 'b':
          putChar('\b');
          break;
        case 't':
          putChar('\t');
          break;
        case 'n':
          putChar('\n');
          break;
        case 'v':
          putChar('\013');
          break;
        case 'F':
        case 'f':
          putChar('\f');
          break;
        case 'r':
          putChar('\r');
          break;
        case '"':
          putChar('"');
          break;
        case '\'':
          putChar('\'');
          break;
        case '/':
          putChar('/');
          break;
        case '\\':
          putChar('\\');
          break;
        case 'x':
          i = next();
          int j = next();
          putChar((char)(digits[i] * 16 + digits[j]));
          break;
        case 'u':
          putChar((char)Integer.parseInt(new String(new char[] { next(), next(), next(), next() }), 16));
          break;
        }
      }
      else if (!this.hasSpecial)
      {
        this.sp += 1;
      }
      else if (this.sp == this.sbuf.length)
      {
        putChar(c);
      }
      else
      {
        arrayOfChar = this.sbuf;
        i = this.sp;
        this.sp = (i + 1);
        arrayOfChar[i] = c;
      }
    }
  }

  public abstract String addSymbol(int paramInt1, int paramInt2, int paramInt3, SymbolTable paramSymbolTable);

  protected abstract void arrayCopy(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3);

  public abstract byte[] bytesValue();

  protected abstract boolean charArrayCompare(char[] paramArrayOfChar);

  public abstract char charAt(int paramInt);

  public void close()
  {
    if (this.sbuf.length <= 8192)
      SBUF_REF_LOCAL.set(new SoftReference(this.sbuf));
    this.sbuf = null;
  }

  public void config(Feature paramFeature, boolean paramBoolean)
  {
    this.features = Feature.config(this.features, paramFeature, paramBoolean);
  }

  protected abstract void copyTo(int paramInt1, int paramInt2, char[] paramArrayOfChar);

  public final Number decimalValue(boolean paramBoolean)
  {
    int i = charAt(this.np + this.sp - 1);
    if (i == 70)
      return Float.valueOf(Float.parseFloat(numberString()));
    if (i == 68)
      return Double.valueOf(Double.parseDouble(numberString()));
    if (paramBoolean)
      return decimalValue();
    return Double.valueOf(doubleValue());
  }

  public final BigDecimal decimalValue()
  {
    return new BigDecimal(numberString());
  }

  public double doubleValue()
  {
    return Double.parseDouble(numberString());
  }

  public float floatValue()
  {
    return Float.parseFloat(numberString());
  }

  public final int getBufferPosition()
  {
    return this.bp;
  }

  public Calendar getCalendar()
  {
    return this.calendar;
  }

  public final char getCurrent()
  {
    return this.ch;
  }

  public abstract int indexOf(char paramChar, int paramInt);

  public final int intValue()
  {
    int j = 0;
    int m = 0;
    int k = this.np;
    int i1 = this.np + this.sp;
    int n;
    int i;
    label73: int i2;
    if (charAt(this.np) == '-')
    {
      m = 1;
      n = -2147483648;
      k += 1;
      if (m != 0);
      i = k;
      if (k < i1)
      {
        j = -digits[charAt(k)];
        i = k + 1;
      }
      if (i >= i1)
        break label215;
      k = i + 1;
      i2 = charAt(i);
      i = k;
      if (i2 != 76)
      {
        i = k;
        if (i2 != 83)
        {
          if (i2 != 66)
            break label141;
          i = k;
        }
      }
    }
    label141: label215: 
    while (true)
    {
      if (m != 0)
      {
        if (i > this.np + 1)
        {
          return j;
          n = -2147483647;
          break;
          i = digits[i2];
          if (j < -214748364)
            throw new NumberFormatException(numberString());
          j *= 10;
          if (j < n + i)
            throw new NumberFormatException(numberString());
          j -= i;
          i = k;
          break label73;
        }
        throw new NumberFormatException(numberString());
      }
      return -j;
    }
  }

  public final Number integerValue()
    throws NumberFormatException
  {
    long l1 = 0L;
    int n = 0;
    if (this.np == -1)
      this.np = 0;
    int k = this.np;
    int i = this.np + this.sp;
    int j = 32;
    long l2;
    label105: int m;
    switch (charAt(i - 1))
    {
    default:
      if (charAt(this.np) == '-')
      {
        n = 1;
        l2 = -9223372036854775808L;
        k += 1;
        if (n != 0);
        m = k;
        if (k < i)
        {
          l1 = -digits[charAt(k)];
          m = k + 1;
        }
      }
      break;
    case 'L':
    case 'S':
    case 'B':
    }
    while (true)
    {
      if (m >= i)
        break label259;
      k = digits[charAt(m)];
      if (l1 < -922337203685477580L)
      {
        return new BigInteger(numberString());
        i -= 1;
        j = 76;
        break;
        i -= 1;
        j = 83;
        break;
        i -= 1;
        j = 66;
        break;
        l2 = -9223372036854775807L;
        break label105;
      }
      l1 *= 10L;
      if (l1 < k + l2)
        return new BigInteger(numberString());
      l1 -= k;
      m += 1;
    }
    label259: if (n != 0)
    {
      if (m > this.np + 1)
      {
        if ((l1 >= -2147483648L) && (j != 76))
          return Integer.valueOf((int)l1);
        return Long.valueOf(l1);
      }
      throw new NumberFormatException(numberString());
    }
    l1 = -l1;
    if ((l1 <= 2147483647L) && (j != 76))
    {
      if (j == 83)
        return Short.valueOf((short)(int)l1);
      if (j == 66)
        return Byte.valueOf((byte)(int)l1);
      return Integer.valueOf((int)l1);
    }
    return Long.valueOf(l1);
  }

  public final boolean isBlankInput()
  {
    int i = 0;
    while (true)
    {
      char c = charAt(i);
      if (c == '\032')
        return true;
      if (!isWhitespace(c))
        return false;
      i += 1;
    }
  }

  public abstract boolean isEOF();

  public final boolean isEnabled(Feature paramFeature)
  {
    return Feature.isEnabled(this.features, paramFeature);
  }

  public final boolean isRef()
  {
    if (this.sp != 4);
    while ((charAt(this.np + 1) != '$') || (charAt(this.np + 2) != 'r') || (charAt(this.np + 3) != 'e') || (charAt(this.np + 4) != 'f'))
      return false;
    return true;
  }

  protected void lexError(String paramString, Object[] paramArrayOfObject)
  {
    this.token = 1;
  }

  public final long longValue()
    throws NumberFormatException
  {
    long l1 = 0L;
    int k = 0;
    int j = this.np;
    int m = this.np + this.sp;
    long l2;
    int i;
    label74: int n;
    if (charAt(this.np) == '-')
    {
      k = 1;
      l2 = -9223372036854775808L;
      j += 1;
      if (k != 0);
      i = j;
      if (j < m)
      {
        l1 = -digits[charAt(j)];
        i = j + 1;
      }
      if (i >= m)
        break label230;
      j = i + 1;
      n = charAt(i);
      i = j;
      if (n != 76)
      {
        i = j;
        if (n != 83)
        {
          if (n != 66)
            break label143;
          i = j;
        }
      }
    }
    label143: label230: 
    while (true)
    {
      if (k != 0)
      {
        if (i > this.np + 1)
        {
          return l1;
          l2 = -9223372036854775807L;
          break;
          i = digits[n];
          if (l1 < -922337203685477580L)
            throw new NumberFormatException(numberString());
          l1 *= 10L;
          if (l1 < i + l2)
            throw new NumberFormatException(numberString());
          l1 -= i;
          i = j;
          break label74;
        }
        throw new NumberFormatException(numberString());
      }
      return -l1;
    }
  }

  public final boolean matchField(char[] paramArrayOfChar)
  {
    if (!charArrayCompare(paramArrayOfChar))
      return false;
    this.bp += paramArrayOfChar.length;
    this.ch = charAt(this.bp);
    if (this.ch == '{')
    {
      next();
      this.token = 12;
    }
    while (true)
    {
      return true;
      if (this.ch == '[')
      {
        next();
        this.token = 14;
      }
      else
      {
        nextToken();
      }
    }
  }

  public final int matchStat()
  {
    return this.matchStat;
  }

  public abstract char next();

  public final void nextIdent()
  {
    while (isWhitespace(this.ch))
      next();
    if ((this.ch == '_') || (Character.isLetter(this.ch)))
    {
      scanIdent();
      return;
    }
    nextToken();
  }

  public final void nextToken()
  {
    this.sp = 0;
    while (true)
    {
      this.pos = this.bp;
      if (this.ch == '"')
      {
        scanString();
        return;
      }
      if (this.ch == ',')
      {
        next();
        this.token = 16;
        return;
      }
      if ((this.ch >= '0') && (this.ch <= '9'))
      {
        scanNumber();
        return;
      }
      if (this.ch == '-')
      {
        scanNumber();
        return;
      }
      switch (this.ch)
      {
      default:
        if (!isEOF())
          break label444;
        if (this.token != 20)
          break;
        throw new JSONException("EOF error");
      case '\'':
        if (!isEnabled(Feature.AllowSingleQuotes))
          throw new JSONException("Feature.AllowSingleQuotes is false");
        scanStringSingleQuote();
        return;
      case '\b':
      case '\t':
      case '\n':
      case '\f':
      case '\r':
      case ' ':
        next();
      case 't':
      case 'T':
      case 'S':
      case 'f':
      case 'n':
      case '(':
      case ')':
      case '[':
      case ']':
      case '{':
      case '}':
      case ':':
      }
    }
    scanTrue();
    return;
    scanTreeSet();
    return;
    scanSet();
    return;
    scanFalse();
    return;
    scanNullOrNew();
    return;
    next();
    this.token = 10;
    return;
    next();
    this.token = 11;
    return;
    next();
    this.token = 14;
    return;
    next();
    this.token = 15;
    return;
    next();
    this.token = 12;
    return;
    next();
    this.token = 13;
    return;
    next();
    this.token = 17;
    return;
    this.token = 20;
    int i = this.eofPos;
    this.bp = i;
    this.pos = i;
    return;
    label444: lexError("illegal.char", new Object[] { String.valueOf(this.ch) });
    next();
  }

  public final void nextToken(int paramInt)
  {
    this.sp = 0;
    switch (paramInt)
    {
    case 3:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 13:
    case 17:
    case 19:
    default:
    case 12:
    case 16:
    case 2:
    case 4:
    case 14:
    case 15:
    case 20:
    case 18:
    }
    while (true)
      if ((this.ch == ' ') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\f') || (this.ch == '\b'))
      {
        next();
        break;
        if (this.ch == '{')
        {
          this.token = 12;
          next();
          return;
        }
        if (this.ch == '[')
        {
          this.token = 14;
          next();
          return;
          if (this.ch == ',')
          {
            this.token = 16;
            next();
            return;
          }
          if (this.ch == '}')
          {
            this.token = 13;
            next();
            return;
          }
          if (this.ch == ']')
          {
            this.token = 15;
            next();
            return;
          }
          if (this.ch == '\032')
          {
            this.token = 20;
            return;
            if ((this.ch >= '0') && (this.ch <= '9'))
            {
              this.pos = this.bp;
              scanNumber();
              return;
            }
            if (this.ch == '"')
            {
              this.pos = this.bp;
              scanString();
              return;
            }
            if (this.ch == '[')
            {
              this.token = 14;
              next();
              return;
            }
            if (this.ch == '{')
            {
              this.token = 12;
              next();
              return;
              if (this.ch == '"')
              {
                this.pos = this.bp;
                scanString();
                return;
              }
              if ((this.ch >= '0') && (this.ch <= '9'))
              {
                this.pos = this.bp;
                scanNumber();
                return;
              }
              if (this.ch == '[')
              {
                this.token = 14;
                next();
                return;
              }
              if (this.ch == '{')
              {
                this.token = 12;
                next();
                return;
                if (this.ch == '[')
                {
                  this.token = 14;
                  next();
                  return;
                }
                if (this.ch == '{')
                {
                  this.token = 12;
                  next();
                  return;
                  if (this.ch == ']')
                  {
                    this.token = 15;
                    next();
                    return;
                  }
                  if (this.ch == '\032')
                  {
                    this.token = 20;
                    return;
                    nextIdent();
                    return;
                  }
                }
              }
            }
          }
        }
      }
    nextToken();
  }

  public final void nextTokenWithChar(char paramChar)
  {
    this.sp = 0;
    while (true)
    {
      if (this.ch == paramChar)
      {
        next();
        nextToken();
        return;
      }
      if ((this.ch != ' ') && (this.ch != '\n') && (this.ch != '\r') && (this.ch != '\t') && (this.ch != '\f') && (this.ch != '\b'))
        break;
      next();
    }
    throw new JSONException("not match " + paramChar + " - " + this.ch);
  }

  public final void nextTokenWithChar(char paramChar, int paramInt)
  {
    this.sp = 0;
    if (this.ch == paramChar)
      next();
    while (true)
    {
      if (paramInt == 2)
      {
        if ((this.ch >= '0') && (this.ch <= '9'))
        {
          this.pos = this.bp;
          scanNumber();
          return;
          if (isWhitespace(this.ch))
          {
            next();
            break;
          }
          throw new JSONException("not match " + paramInt + " - " + this.ch);
        }
        if (this.ch != '"')
          break label289;
        this.pos = this.bp;
        scanString();
        return;
      }
      if (paramInt == 4)
      {
        if (this.ch == '"')
        {
          this.pos = this.bp;
          scanString();
          return;
        }
        if ((this.ch >= '0') && (this.ch <= '9'))
        {
          this.pos = this.bp;
          scanNumber();
        }
      }
      else if (paramInt == 12)
      {
        if (this.ch == '{')
        {
          this.token = 12;
          next();
          return;
        }
        if (this.ch == '[')
        {
          this.token = 14;
          next();
        }
      }
      else if (paramInt == 14)
      {
        if (this.ch == '[')
        {
          this.token = 14;
          next();
          return;
        }
        if (this.ch == '{')
        {
          this.token = 12;
          next();
          return;
        }
      }
      label289: if (!isWhitespace(this.ch))
        break label307;
      next();
    }
    label307: nextToken();
  }

  public final void nextTokenWithColon()
  {
    nextTokenWithChar(':');
  }

  public final void nextTokenWithColon(int paramInt)
  {
    nextTokenWithChar(':');
  }

  public final void nextTokenWithComma()
  {
    nextTokenWithChar(':');
  }

  public final void nextTokenWithComma(int paramInt)
  {
    nextTokenWithChar(',');
  }

  public abstract String numberString();

  public final Number numberValue()
  {
    int i = charAt(this.np + this.sp - 1);
    String str = numberString();
    switch (i)
    {
    case 69:
    default:
      return new BigDecimal(str);
    case 68:
      return Double.valueOf(Double.parseDouble(str));
    case 70:
    }
    return Float.valueOf(Float.parseFloat(str));
  }

  public final int pos()
  {
    return this.pos;
  }

  protected final void putChar(char paramChar)
  {
    if (this.sp == this.sbuf.length)
    {
      arrayOfChar = new char[this.sbuf.length * 2];
      System.arraycopy(this.sbuf, 0, arrayOfChar, 0, this.sbuf.length);
      this.sbuf = arrayOfChar;
    }
    char[] arrayOfChar = this.sbuf;
    int i = this.sp;
    this.sp = (i + 1);
    arrayOfChar[i] = paramChar;
  }

  public final void resetStringPosition()
  {
    this.sp = 0;
  }

  public boolean scanBoolean(char paramChar)
  {
    this.matchStat = 0;
    char c = this.bp;
    int i = 0 + 1;
    c = charAt(c + '\000');
    boolean bool = false;
    if (c == 't')
      if ((charAt(this.bp + 1) == 'r') && (charAt(this.bp + 1 + 1) == 'u') && (charAt(this.bp + 1 + 2) == 'e'))
      {
        c = this.bp;
        i = i + 3 + 1;
        c = charAt(c + '\004');
        bool = true;
      }
    while (true)
      if (c == paramChar)
      {
        this.bp += i - 1;
        next();
        this.matchStat = 3;
        return bool;
        this.matchStat = -1;
        return false;
        if (c == 'f')
          if ((charAt(this.bp + 1) == 'a') && (charAt(this.bp + 1 + 1) == 'l') && (charAt(this.bp + 1 + 2) == 's') && (charAt(this.bp + 1 + 3) == 'e'))
          {
            c = this.bp;
            i = i + 4 + 1;
            c = charAt(c + '\005');
            bool = false;
          }
          else
          {
            this.matchStat = -1;
            return false;
          }
      }
      else
      {
        this.matchStat = -1;
        return bool;
      }
  }

  public Enum<?> scanEnum(Class<?> paramClass, SymbolTable paramSymbolTable, char paramChar)
  {
    paramSymbolTable = scanSymbolWithSeperator(paramSymbolTable, paramChar);
    if (paramSymbolTable == null)
      return null;
    return Enum.valueOf(paramClass, paramSymbolTable);
  }

  public final void scanFalse()
  {
    if (this.ch != 'f')
      throw new JSONException("error parse false");
    next();
    if (this.ch != 'a')
      throw new JSONException("error parse false");
    next();
    if (this.ch != 'l')
      throw new JSONException("error parse false");
    next();
    if (this.ch != 's')
      throw new JSONException("error parse false");
    next();
    if (this.ch != 'e')
      throw new JSONException("error parse false");
    next();
    if ((this.ch == ' ') || (this.ch == ',') || (this.ch == '}') || (this.ch == ']') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\032') || (this.ch == '\f') || (this.ch == '\b'))
    {
      this.token = 7;
      return;
    }
    throw new JSONException("scan false error");
  }

  public boolean scanFieldBoolean(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return false;
    }
    int j = paramArrayOfChar.length;
    int k = this.bp;
    int i = j + 1;
    j = charAt(k + j);
    boolean bool;
    if (j == 116)
    {
      k = this.bp;
      j = i + 1;
      if (charAt(k + i) != 'r')
      {
        this.matchStat = -1;
        return false;
      }
      i = this.bp;
      k = j + 1;
      if (charAt(i + j) != 'u')
      {
        this.matchStat = -1;
        return false;
      }
      j = this.bp;
      i = k + 1;
      if (charAt(j + k) != 'e')
      {
        this.matchStat = -1;
        return false;
      }
      bool = true;
    }
    while (true)
    {
      k = this.bp;
      j = i + 1;
      i = charAt(k + i);
      if (i != 44)
        break label333;
      this.bp += j - 1;
      next();
      this.matchStat = 3;
      this.token = 16;
      return bool;
      if (j != 102)
        break;
      k = this.bp;
      j = i + 1;
      if (charAt(k + i) != 'a')
      {
        this.matchStat = -1;
        return false;
      }
      k = this.bp;
      i = j + 1;
      if (charAt(k + j) != 'l')
      {
        this.matchStat = -1;
        return false;
      }
      j = this.bp;
      k = i + 1;
      if (charAt(j + i) != 's')
      {
        this.matchStat = -1;
        return false;
      }
      if (charAt(this.bp + k) != 'e')
      {
        this.matchStat = -1;
        return false;
      }
      bool = false;
      i = k + 1;
    }
    this.matchStat = -1;
    return false;
    label333: if (i == 125)
    {
      k = this.bp;
      i = j + 1;
      j = charAt(k + j);
      if (j == 44)
      {
        this.token = 16;
        this.bp += i - 1;
        next();
      }
      while (true)
      {
        this.matchStat = 4;
        return bool;
        if (j == 93)
        {
          this.token = 15;
          this.bp += i - 1;
          next();
        }
        else if (j == 125)
        {
          this.token = 13;
          this.bp += i - 1;
          next();
        }
        else
        {
          if (j != 26)
            break;
          this.token = 20;
          this.bp += i - 1;
          this.ch = '\032';
        }
      }
      this.matchStat = -1;
      return false;
    }
    this.matchStat = -1;
    return false;
  }

  public final double scanFieldDouble(char paramChar)
  {
    this.matchStat = 0;
    char c1 = charAt(this.bp + 0);
    char c2;
    char c3;
    char c4;
    if ((c1 >= '0') && (c1 <= '9'))
    {
      for (c1 = 0 + 1; ; c1 = c3)
      {
        c2 = this.bp;
        c3 = c1 + '\001';
        c4 = charAt(c2 + c1);
        if ((c4 < '0') || (c4 > '9'))
          break;
      }
      c2 = c4;
      c1 = c3;
      if (c4 == '.')
      {
        c2 = this.bp;
        c1 = c3 + '\001';
        c2 = charAt(c2 + c3);
        if ((c2 >= '0') && (c2 <= '9'))
          while (true)
          {
            c2 = this.bp;
            c3 = c1 + '\001';
            c4 = charAt(c2 + c1);
            c2 = c4;
            c1 = c3;
            if (c4 < '0')
              break;
            c2 = c4;
            c1 = c3;
            if (c4 > '9')
              break;
            c1 = c3;
          }
        this.matchStat = -1;
        return 0.0D;
      }
      if (c2 != 'e')
      {
        c3 = c2;
        c4 = c1;
        if (c2 != 'E');
      }
      else
      {
        c3 = this.bp;
        c2 = c1 + '\001';
        c1 = charAt(c3 + c1);
        if ((c1 != '+') && (c1 != '-'))
          break label420;
        c3 = this.bp;
        c1 = c2 + '\001';
        c3 = charAt(c3 + c2);
        c2 = c1;
        c1 = c3;
      }
    }
    label420: 
    while (true)
    {
      c3 = c1;
      c4 = c2;
      if (c1 >= '0')
      {
        c3 = c1;
        c4 = c2;
        if (c1 <= '9')
        {
          c1 = charAt(this.bp + c2);
          c2 += '\001';
        }
      }
      else
      {
        c1 = this.bp;
        double d = Double.parseDouble(subString(c1, this.bp + c4 - c1 - 1));
        if (c3 == paramChar)
        {
          this.bp += c4 - '\001';
          next();
          this.matchStat = 3;
          this.token = 16;
          return d;
          this.matchStat = -1;
          return 0.0D;
        }
        this.matchStat = -1;
        return d;
      }
    }
  }

  public final double scanFieldDouble(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return 0.0D;
    }
    int i = paramArrayOfChar.length;
    int j = charAt(this.bp + i);
    int k;
    int m;
    if ((j >= 48) && (j <= 57))
    {
      i += 1;
      while (true)
      {
        j = this.bp;
        k = i + 1;
        m = charAt(j + i);
        if ((m < 48) || (m > 57))
          break;
        i = k;
      }
      j = m;
      i = k;
      if (m == 46)
      {
        j = this.bp;
        i = k + 1;
        j = charAt(j + k);
        if ((j >= 48) && (j <= 57))
          while (true)
          {
            j = this.bp;
            k = i + 1;
            m = charAt(j + i);
            j = m;
            i = k;
            if (m < 48)
              break;
            j = m;
            i = k;
            if (m > 57)
              break;
            i = k;
          }
        this.matchStat = -1;
        return 0.0D;
      }
      if (j != 101)
      {
        m = j;
        k = i;
        if (j != 69);
      }
      else
      {
        k = this.bp;
        j = i + 1;
        i = charAt(k + i);
        if ((i != 43) && (i != 45))
          break label624;
        k = this.bp;
        i = j + 1;
        k = charAt(k + j);
        j = i;
        i = k;
      }
    }
    label624: 
    while (true)
    {
      m = i;
      k = j;
      if (i >= 48)
      {
        m = i;
        k = j;
        if (i <= 57)
        {
          i = charAt(this.bp + j);
          j += 1;
        }
      }
      else
      {
        i = this.bp + paramArrayOfChar.length;
        double d = Double.parseDouble(subString(i, this.bp + k - i - 1));
        if (m == 44)
        {
          this.bp += k - 1;
          next();
          this.matchStat = 3;
          this.token = 16;
          return d;
          this.matchStat = -1;
          return 0.0D;
        }
        if (m == 125)
        {
          j = this.bp;
          i = k + 1;
          j = charAt(j + k);
          if (j == 44)
          {
            this.token = 16;
            this.bp += i - 1;
            next();
          }
          while (true)
          {
            this.matchStat = 4;
            return d;
            if (j == 93)
            {
              this.token = 15;
              this.bp += i - 1;
              next();
            }
            else if (j == 125)
            {
              this.token = 13;
              this.bp += i - 1;
              next();
            }
            else
            {
              if (j != 26)
                break;
              this.token = 20;
              this.bp += i - 1;
              this.ch = '\032';
            }
          }
          this.matchStat = -1;
          return 0.0D;
        }
        this.matchStat = -1;
        return 0.0D;
      }
    }
  }

  public final float scanFieldFloat(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return 0.0F;
    }
    int i = paramArrayOfChar.length;
    int j = charAt(this.bp + i);
    int k;
    float f;
    if ((j >= 48) && (j <= 57))
    {
      i += 1;
      int m;
      while (true)
      {
        k = this.bp;
        j = i + 1;
        m = charAt(k + i);
        if ((m < 48) || (m > 57))
          break;
        i = j;
      }
      k = m;
      i = j;
      if (m == 46)
      {
        k = this.bp;
        i = j + 1;
        j = charAt(k + j);
        if ((j >= 48) && (j <= 57))
          while (true)
          {
            k = this.bp;
            j = i + 1;
            m = charAt(k + i);
            k = m;
            i = j;
            if (m < 48)
              break;
            k = m;
            i = j;
            if (m > 57)
              break;
            i = j;
          }
        this.matchStat = -1;
        return 0.0F;
      }
      j = this.bp + paramArrayOfChar.length;
      f = Float.parseFloat(subString(j, this.bp + i - j - 1));
      if (k == 44)
      {
        this.bp += i - 1;
        next();
        this.matchStat = 3;
        this.token = 16;
        return f;
      }
    }
    else
    {
      this.matchStat = -1;
      return 0.0F;
    }
    if (k == 125)
    {
      k = this.bp;
      j = i + 1;
      i = charAt(k + i);
      if (i == 44)
      {
        this.token = 16;
        this.bp += j - 1;
        next();
      }
      while (true)
      {
        this.matchStat = 4;
        return f;
        if (i == 93)
        {
          this.token = 15;
          this.bp += j - 1;
          next();
        }
        else if (i == 125)
        {
          this.token = 13;
          this.bp += j - 1;
          next();
        }
        else
        {
          if (i != 26)
            break;
          this.bp += j - 1;
          this.token = 20;
          this.ch = '\032';
        }
      }
      this.matchStat = -1;
      return 0.0F;
    }
    this.matchStat = -1;
    return 0.0F;
  }

  public int scanFieldInt(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return 0;
    }
    int j = paramArrayOfChar.length;
    int i = charAt(this.bp + j);
    int m;
    int k;
    if ((i >= 48) && (i <= 57))
    {
      i = digits[i];
      j += 1;
      while (true)
      {
        m = this.bp;
        k = j + 1;
        j = charAt(m + j);
        if ((j < 48) || (j > 57))
          break;
        i = i * 10 + digits[j];
        j = k;
      }
      if (j == 46)
      {
        this.matchStat = -1;
        return 0;
      }
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
    if (j == 44)
    {
      this.bp += k - 1;
      next();
      this.matchStat = 3;
      this.token = 16;
      return i;
    }
    if (j == 125)
    {
      m = this.bp;
      j = k + 1;
      k = charAt(m + k);
      if (k == 44)
      {
        this.token = 16;
        this.bp += j - 1;
        next();
      }
      while (true)
      {
        this.matchStat = 4;
        return i;
        if (k == 93)
        {
          this.token = 15;
          this.bp += j - 1;
          next();
        }
        else if (k == 125)
        {
          this.token = 13;
          this.bp += j - 1;
          next();
        }
        else
        {
          if (k != 26)
            break;
          this.token = 20;
          this.bp += j - 1;
          this.ch = '\032';
        }
      }
      this.matchStat = -1;
      return 0;
    }
    this.matchStat = -1;
    return 0;
  }

  public long scanFieldLong(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return 0L;
    }
    int i = paramArrayOfChar.length;
    int j = charAt(this.bp + i);
    long l;
    int k;
    if ((j >= 48) && (j <= 57))
    {
      l = digits[j];
      i += 1;
      while (true)
      {
        k = this.bp;
        j = i + 1;
        i = charAt(k + i);
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
      if (l < 0L)
      {
        this.matchStat = -1;
        return 0L;
      }
    }
    else
    {
      this.matchStat = -1;
      return 0L;
    }
    if (i == 44)
    {
      this.bp += j - 1;
      next();
      this.matchStat = 3;
      this.token = 16;
      return l;
    }
    if (i == 125)
    {
      k = this.bp;
      i = j + 1;
      j = charAt(k + j);
      if (j == 44)
      {
        this.token = 16;
        this.bp += i - 1;
        next();
      }
      while (true)
      {
        this.matchStat = 4;
        return l;
        if (j == 93)
        {
          this.token = 15;
          this.bp += i - 1;
          next();
        }
        else if (j == 125)
        {
          this.token = 13;
          this.bp += i - 1;
          next();
        }
        else
        {
          if (j != 26)
            break;
          this.token = 20;
          this.bp += i - 1;
          this.ch = '\032';
        }
      }
      this.matchStat = -1;
      return 0L;
    }
    this.matchStat = -1;
    return 0L;
  }

  public String scanFieldString(char[] paramArrayOfChar)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return stringDefaultValue();
    }
    int m = paramArrayOfChar.length;
    if (charAt(this.bp + m) != '"')
    {
      this.matchStat = -1;
      return stringDefaultValue();
    }
    int k = 0;
    int n = indexOf('"', this.bp + paramArrayOfChar.length + 1);
    if (n == -1)
      throw new JSONException("unclosed str");
    int i = this.bp + paramArrayOfChar.length + 1;
    String str = subString(i, n - i);
    i = this.bp + paramArrayOfChar.length + 1;
    while (true)
    {
      j = k;
      if (i < n)
      {
        if (charAt(i) == '\\')
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
    int j = m + 1 + (n - (this.bp + paramArrayOfChar.length + 1) + 1);
    k = this.bp;
    i = j + 1;
    j = charAt(k + j);
    if (j == 44)
    {
      this.bp += i - 1;
      next();
      this.matchStat = 3;
      return str;
    }
    if (j == 125)
    {
      k = this.bp;
      j = i + 1;
      i = charAt(k + i);
      if (i == 44)
      {
        this.token = 16;
        this.bp += j - 1;
        next();
      }
      while (true)
      {
        this.matchStat = 4;
        return str;
        if (i == 93)
        {
          this.token = 15;
          this.bp += j - 1;
          next();
        }
        else if (i == 125)
        {
          this.token = 13;
          this.bp += j - 1;
          next();
        }
        else
        {
          if (i != 26)
            break;
          this.token = 20;
          this.bp += j - 1;
          this.ch = '\032';
        }
      }
      this.matchStat = -1;
      return stringDefaultValue();
    }
    this.matchStat = -1;
    return stringDefaultValue();
  }

  public Collection<String> scanFieldStringArray(char[] paramArrayOfChar, Class<?> paramClass)
  {
    this.matchStat = 0;
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return null;
    }
    if (paramClass.isAssignableFrom(HashSet.class))
      paramClass = new HashSet();
    while (true)
    {
      i = paramArrayOfChar.length;
      k = this.bp;
      j = i + 1;
      if (charAt(k + i) != '[')
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
    int k = this.bp;
    int i = j + 1;
    int j = charAt(k + j);
    if (j != 34)
    {
      this.matchStat = -1;
      return null;
    }
    j = i;
    while (true)
    {
      k = j;
      int m = this.bp;
      j = k + 1;
      k = charAt(m + k);
      if (k == 34)
      {
        i = this.bp + i;
        paramClass.add(subString(i, this.bp + j - i - 1));
        k = this.bp;
        i = j + 1;
        j = charAt(k + j);
        if (j != 44)
          break label284;
        j = charAt(this.bp + i);
        i += 1;
        break;
      }
      if (k == 92)
      {
        this.matchStat = -1;
        return null;
        label284: if (j == 93)
        {
          k = this.bp;
          j = i + 1;
          i = charAt(k + i);
          if (i == 44)
          {
            this.bp += j - 1;
            next();
            this.matchStat = 3;
            return paramClass;
          }
        }
        else
        {
          this.matchStat = -1;
          return null;
        }
        if (i == 125)
        {
          k = this.bp;
          i = j + 1;
          j = charAt(k + j);
          if (j == 44)
          {
            this.token = 16;
            this.bp += i - 1;
            next();
          }
          while (true)
          {
            this.matchStat = 4;
            return paramClass;
            if (j == 93)
            {
              this.token = 15;
              this.bp += i - 1;
              next();
            }
            else if (j == 125)
            {
              this.token = 13;
              this.bp += i - 1;
              next();
            }
            else
            {
              if (j != 26)
                break;
              this.bp += i - 1;
              this.token = 20;
              this.ch = '\032';
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
    if (!charArrayCompare(paramArrayOfChar))
    {
      this.matchStat = -2;
      return null;
    }
    int j = paramArrayOfChar.length;
    if (charAt(this.bp + j) != '"')
    {
      this.matchStat = -1;
      return null;
    }
    int i = 0;
    j += 1;
    while (true)
    {
      int m = this.bp;
      int k = j + 1;
      j = charAt(m + j);
      if (j == 34)
      {
        j = this.bp + paramArrayOfChar.length + 1;
        paramArrayOfChar = addSymbol(j, this.bp + k - j - 1, i, paramSymbolTable);
        j = this.bp;
        i = k + 1;
        j = charAt(j + k);
        if (j == 44)
        {
          this.bp += i - 1;
          next();
          this.matchStat = 3;
          return paramArrayOfChar;
        }
      }
      else
      {
        i = i * 31 + j;
        if (j != 92)
          break label371;
        this.matchStat = -1;
        return null;
      }
      if (j == 125)
      {
        k = this.bp;
        j = i + 1;
        i = charAt(k + i);
        if (i == 44)
        {
          this.token = 16;
          this.bp += j - 1;
          next();
        }
        while (true)
        {
          this.matchStat = 4;
          return paramArrayOfChar;
          if (i == 93)
          {
            this.token = 15;
            this.bp += j - 1;
            next();
          }
          else if (i == 125)
          {
            this.token = 13;
            this.bp += j - 1;
            next();
          }
          else
          {
            if (i != 26)
              break;
            this.token = 20;
            this.bp += j - 1;
            this.ch = '\032';
          }
        }
        this.matchStat = -1;
        return null;
      }
      this.matchStat = -1;
      return null;
      label371: j = k;
    }
  }

  public final float scanFloat(char paramChar)
  {
    this.matchStat = 0;
    char c1 = charAt(this.bp + 0);
    float f;
    if ((c1 >= '0') && (c1 <= '9'))
    {
      char c4;
      for (char c2 = 0 + 1; ; c2 = c1)
      {
        c3 = this.bp;
        c1 = c2 + '\001';
        c4 = charAt(c3 + c2);
        if ((c4 < '0') || (c4 > '9'))
          break;
      }
      c2 = c4;
      char c3 = c1;
      if (c4 == '.')
      {
        c3 = this.bp;
        c2 = c1 + '\001';
        c1 = charAt(c3 + c1);
        if ((c1 >= '0') && (c1 <= '9'))
          while (true)
          {
            c3 = this.bp;
            c1 = c2 + '\001';
            c4 = charAt(c3 + c2);
            c2 = c4;
            c3 = c1;
            if (c4 < '0')
              break;
            c2 = c4;
            c3 = c1;
            if (c4 > '9')
              break;
            c2 = c1;
          }
        this.matchStat = -1;
        return 0.0F;
      }
      c1 = this.bp;
      f = Float.parseFloat(subString(c1, this.bp + c3 - c1 - 1));
      if (c2 == paramChar)
      {
        this.bp += c3 - '\001';
        next();
        this.matchStat = 3;
        this.token = 16;
        return f;
      }
    }
    else
    {
      this.matchStat = -1;
      return 0.0F;
    }
    this.matchStat = -1;
    return f;
  }

  public final void scanIdent()
  {
    this.np = (this.bp - 1);
    this.hasSpecial = false;
    do
    {
      this.sp += 1;
      next();
    }
    while (Character.isLetterOrDigit(this.ch));
    Object localObject = stringVal();
    localObject = this.keywods.getKeyword((String)localObject);
    if (localObject != null)
    {
      this.token = ((Integer)localObject).intValue();
      return;
    }
    this.token = 18;
  }

  public int scanInt(char paramChar)
  {
    this.matchStat = 0;
    int i = charAt(this.bp + 0);
    char c1;
    char c2;
    if ((i >= 48) && (i <= 57))
    {
      i = digits[i];
      for (c1 = 0 + 1; ; c1 = c2)
      {
        char c3 = this.bp;
        c2 = c1 + '\001';
        c1 = charAt(c3 + c1);
        if ((c1 < '0') || (c1 > '9'))
          break;
        i = i * 10 + digits[c1];
      }
      if (c1 == '.')
      {
        this.matchStat = -1;
        return 0;
      }
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
    if (c1 == paramChar)
    {
      this.bp += c2 - '\001';
      next();
      this.matchStat = 3;
      this.token = 16;
      return i;
    }
    this.matchStat = -1;
    return i;
  }

  public long scanLong(char paramChar)
  {
    this.matchStat = 0;
    char c1 = charAt(this.bp + 0);
    long l;
    char c2;
    if ((c1 >= '0') && (c1 <= '9'))
    {
      l = digits[c1];
      for (c1 = 0 + 1; ; c1 = c2)
      {
        char c3 = this.bp;
        c2 = c1 + '\001';
        c1 = charAt(c3 + c1);
        if ((c1 < '0') || (c1 > '9'))
          break;
        l = 10L * l + digits[c1];
      }
      if (c1 == '.')
      {
        this.matchStat = -1;
        return 0L;
      }
      if (l < 0L)
      {
        this.matchStat = -1;
        return 0L;
      }
    }
    else
    {
      this.matchStat = -1;
      return 0L;
    }
    if (c1 == paramChar)
    {
      this.bp += c2 - '\001';
      next();
      this.matchStat = 3;
      this.token = 16;
      return l;
    }
    this.matchStat = -1;
    return l;
  }

  public final void scanNullOrNew()
  {
    if (this.ch != 'n')
      throw new JSONException("error parse null or new");
    next();
    if (this.ch == 'u')
    {
      next();
      if (this.ch != 'l')
        throw new JSONException("error parse true");
      next();
      if (this.ch != 'l')
        throw new JSONException("error parse true");
      next();
      if ((this.ch == ' ') || (this.ch == ',') || (this.ch == '}') || (this.ch == ']') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\032') || (this.ch == '\f') || (this.ch == '\b'))
      {
        this.token = 8;
        return;
      }
      throw new JSONException("scan true error");
    }
    if (this.ch != 'e')
      throw new JSONException("error parse e");
    next();
    if (this.ch != 'w')
      throw new JSONException("error parse w");
    next();
    if ((this.ch == ' ') || (this.ch == ',') || (this.ch == '}') || (this.ch == ']') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\032') || (this.ch == '\f') || (this.ch == '\b'))
    {
      this.token = 9;
      return;
    }
    throw new JSONException("scan true error");
  }

  public final void scanNumber()
  {
    this.np = this.bp;
    if (this.ch == '-')
    {
      this.sp += 1;
      next();
    }
    while ((this.ch >= '0') && (this.ch <= '9'))
    {
      this.sp += 1;
      next();
    }
    int i = 0;
    if (this.ch == '.')
    {
      this.sp += 1;
      next();
      int j = 1;
      while (true)
      {
        i = j;
        if (this.ch < '0')
          break;
        i = j;
        if (this.ch > '9')
          break;
        this.sp += 1;
        next();
      }
    }
    if (this.ch == 'L')
    {
      this.sp += 1;
      next();
    }
    while (i != 0)
    {
      this.token = 3;
      return;
      if (this.ch == 'S')
      {
        this.sp += 1;
        next();
      }
      else if (this.ch == 'B')
      {
        this.sp += 1;
        next();
      }
      else if (this.ch == 'F')
      {
        this.sp += 1;
        next();
        i = 1;
      }
      else if (this.ch == 'D')
      {
        this.sp += 1;
        next();
        i = 1;
      }
      else if ((this.ch == 'e') || (this.ch == 'E'))
      {
        this.sp += 1;
        next();
        if ((this.ch == '+') || (this.ch == '-'))
        {
          this.sp += 1;
          next();
        }
        while ((this.ch >= '0') && (this.ch <= '9'))
        {
          this.sp += 1;
          next();
        }
        if ((this.ch == 'D') || (this.ch == 'F'))
        {
          this.sp += 1;
          next();
        }
        i = 1;
      }
    }
    this.token = 2;
  }

  public final void scanSet()
  {
    if (this.ch != 'S')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'e')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 't')
      throw new JSONException("error parse true");
    next();
    if ((this.ch == ' ') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\f') || (this.ch == '\b') || (this.ch == '[') || (this.ch == '('))
    {
      this.token = 21;
      return;
    }
    throw new JSONException("scan set error");
  }

  public String scanString(char paramChar)
  {
    this.matchStat = 0;
    int i = charAt(this.bp + 0);
    if (i == 110)
    {
      if ((charAt(this.bp + 1) == 'u') && (charAt(this.bp + 1 + 1) == 'l') && (charAt(this.bp + 1 + 2) == 'l'))
      {
        if (charAt(this.bp + 4) == paramChar)
        {
          this.bp += 4;
          next();
          this.matchStat = 3;
          return null;
        }
      }
      else
      {
        this.matchStat = -1;
        return null;
      }
      this.matchStat = -1;
      return null;
    }
    if (i != 34)
    {
      this.matchStat = -1;
      return stringDefaultValue();
    }
    int k = 0;
    i = this.bp + 1;
    int m = indexOf('"', i);
    if (m == -1)
      throw new JSONException("unclosed str");
    String str = subString(this.bp + 1, m - i);
    i = this.bp + 1;
    while (true)
    {
      int j = k;
      if (i < m)
      {
        if (charAt(i) == '\\')
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
    i = m - (this.bp + 1) + 1 + 1;
    if (charAt(this.bp + i) == paramChar)
    {
      this.bp += i + 1 - 1;
      next();
      this.matchStat = 3;
      return str;
    }
    this.matchStat = -1;
    return str;
  }

  public final void scanString()
  {
    this.np = this.bp;
    this.hasSpecial = false;
    while (true)
    {
      char c = next();
      if (c == '"')
      {
        this.token = 4;
        this.ch = next();
        return;
      }
      if (c == '\032')
        throw new JSONException("unclosed string : " + c);
      int i;
      char[] arrayOfChar;
      if (c == '\\')
      {
        int j;
        if (!this.hasSpecial)
        {
          this.hasSpecial = true;
          if (this.sp >= this.sbuf.length)
          {
            j = this.sbuf.length * 2;
            i = j;
            if (this.sp > j)
              i = this.sp;
            arrayOfChar = new char[i];
            System.arraycopy(this.sbuf, 0, arrayOfChar, 0, this.sbuf.length);
            this.sbuf = arrayOfChar;
          }
          copyTo(this.np + 1, this.sp, this.sbuf);
        }
        c = next();
        switch (c)
        {
        default:
          this.ch = c;
          throw new JSONException("unclosed string : " + c);
        case '0':
          putChar('\000');
          break;
        case '1':
          putChar('\001');
          break;
        case '2':
          putChar('\002');
          break;
        case '3':
          putChar('\003');
          break;
        case '4':
          putChar('\004');
          break;
        case '5':
          putChar('\005');
          break;
        case '6':
          putChar('\006');
          break;
        case '7':
          putChar('\007');
          break;
        case 'b':
          putChar('\b');
          break;
        case 't':
          putChar('\t');
          break;
        case 'n':
          putChar('\n');
          break;
        case 'v':
          putChar('\013');
          break;
        case 'F':
        case 'f':
          putChar('\f');
          break;
        case 'r':
          putChar('\r');
          break;
        case '"':
          putChar('"');
          break;
        case '\'':
          putChar('\'');
          break;
        case '/':
          putChar('/');
          break;
        case '\\':
          putChar('\\');
          break;
        case 'x':
          i = next();
          j = next();
          putChar((char)(digits[i] * 16 + digits[j]));
          break;
        case 'u':
          putChar((char)Integer.parseInt(new String(new char[] { next(), next(), next(), next() }), 16));
          break;
        }
      }
      else if (!this.hasSpecial)
      {
        this.sp += 1;
      }
      else if (this.sp == this.sbuf.length)
      {
        putChar(c);
      }
      else
      {
        arrayOfChar = this.sbuf;
        i = this.sp;
        this.sp = (i + 1);
        arrayOfChar[i] = c;
      }
    }
  }

  public Collection<String> scanStringArray(Class<?> paramClass, char paramChar)
  {
    this.matchStat = 0;
    if (paramClass.isAssignableFrom(HashSet.class))
      paramClass = new HashSet();
    int i;
    while (true)
    {
      j = this.bp;
      i = 0 + 1;
      j = charAt(j + 0);
      if (j != 110)
        break label195;
      if ((charAt(this.bp + 1) == 'u') && (charAt(this.bp + 1 + 1) == 'l') && (charAt(this.bp + 1 + 2) == 'l'))
      {
        if (charAt(this.bp + 4) != paramChar)
          break label188;
        this.bp += 4;
        next();
        this.matchStat = 3;
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
        catch (Exception paramClass)
        {
          throw new JSONException(paramClass.getMessage(), paramClass);
        }
      }
    }
    this.matchStat = -1;
    return null;
    label188: this.matchStat = -1;
    return null;
    label195: if (j != 91)
    {
      this.matchStat = -1;
      return null;
    }
    int j = this.bp;
    i += 1;
    j = charAt(j + 1);
    int k;
    while ((j == 110) && (charAt(this.bp + i) == 'u') && (charAt(this.bp + i + 1) == 'l') && (charAt(this.bp + i + 2) == 'l'))
    {
      j = i + 3;
      k = this.bp;
      i = j + 1;
      j = charAt(k + j);
      if (j != 44)
        break label468;
      j = this.bp;
      k = i + 1;
      j = charAt(j + i);
      i = k;
    }
    if (j != 34)
    {
      this.matchStat = -1;
      return null;
    }
    j = i;
    while (true)
    {
      k = j;
      int m = this.bp;
      j = k + 1;
      k = charAt(m + k);
      if (k == 34)
      {
        i = this.bp + i;
        paramClass.add(subString(i, this.bp + j - i - 1));
        i = charAt(this.bp + j);
        k = j + 1;
        j = i;
        i = k;
        break;
      }
      if (k == 92)
      {
        this.matchStat = -1;
        return null;
        label468: if (j == 93)
        {
          if (charAt(this.bp + i) == paramChar)
          {
            this.bp += i + 1 - 1;
            next();
            this.matchStat = 3;
            return paramClass;
          }
        }
        else
        {
          this.matchStat = -1;
          return null;
        }
        this.matchStat = -1;
        return paramClass;
      }
    }
  }

  public final String scanSymbol(SymbolTable paramSymbolTable)
  {
    skipWhitespace();
    if (this.ch == '"')
      return scanSymbol(paramSymbolTable, '"');
    if (this.ch == '\'')
    {
      if (!isEnabled(Feature.AllowSingleQuotes))
        throw new JSONException("syntax error");
      return scanSymbol(paramSymbolTable, '\'');
    }
    if (this.ch == '}')
    {
      next();
      this.token = 13;
      return null;
    }
    if (this.ch == ',')
    {
      next();
      this.token = 16;
      return null;
    }
    if (this.ch == '\032')
    {
      this.token = 20;
      return null;
    }
    if (!isEnabled(Feature.AllowUnQuotedFieldNames))
      throw new JSONException("syntax error");
    return scanSymbolUnQuoted(paramSymbolTable);
  }

  public final String scanSymbol(SymbolTable paramSymbolTable, char paramChar)
  {
    int i = 0;
    this.np = this.bp;
    this.sp = 0;
    int j = 0;
    char c1 = next();
    if (c1 == paramChar)
    {
      this.token = 4;
      if (j != 0)
        break label1011;
      if (this.np != -1)
        break label1001;
      paramChar = '\000';
    }
    label49: label1001: label1011: for (paramSymbolTable = addSymbol(paramChar, this.sp, i, paramSymbolTable); ; paramSymbolTable = paramSymbolTable.addSymbol(this.sbuf, 0, this.sp, i))
    {
      this.sp = 0;
      next();
      return paramSymbolTable;
      if (c1 == '\032')
        throw new JSONException("unclosed.str");
      if (c1 == '\\')
      {
        k = j;
        if (j == 0)
        {
          k = 1;
          if (this.sp >= this.sbuf.length)
          {
            int m = this.sbuf.length * 2;
            j = m;
            if (this.sp > m)
              j = this.sp;
            arrayOfChar = new char[j];
            System.arraycopy(this.sbuf, 0, arrayOfChar, 0, this.sbuf.length);
            this.sbuf = arrayOfChar;
          }
          arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
        }
        c1 = next();
        switch (c1)
        {
        default:
          this.ch = c1;
          throw new JSONException("unclosed.str.lit");
        case '0':
          i = i * 31 + c1;
          putChar('\000');
          j = k;
          break;
        case '1':
          i = i * 31 + c1;
          putChar('\001');
          j = k;
          break;
        case '2':
          i = i * 31 + c1;
          putChar('\002');
          j = k;
          break;
        case '3':
          i = i * 31 + c1;
          putChar('\003');
          j = k;
          break;
        case '4':
          i = i * 31 + c1;
          putChar('\004');
          j = k;
          break;
        case '5':
          i = i * 31 + c1;
          putChar('\005');
          j = k;
          break;
        case '6':
          i = i * 31 + c1;
          putChar('\006');
          j = k;
          break;
        case '7':
          i = i * 31 + c1;
          putChar('\007');
          j = k;
          break;
        case 'b':
          i = i * 31 + 8;
          putChar('\b');
          j = k;
          break;
        case 't':
          i = i * 31 + 9;
          putChar('\t');
          j = k;
          break;
        case 'n':
          i = i * 31 + 10;
          putChar('\n');
          j = k;
          break;
        case 'v':
          i = i * 31 + 11;
          putChar('\013');
          j = k;
          break;
        case 'F':
        case 'f':
          i = i * 31 + 12;
          putChar('\f');
          j = k;
          break;
        case 'r':
          i = i * 31 + 13;
          putChar('\r');
          j = k;
          break;
        case '"':
          i = i * 31 + 34;
          putChar('"');
          j = k;
          break;
        case '\'':
          i = i * 31 + 39;
          putChar('\'');
          j = k;
          break;
        case '/':
          i = i * 31 + 47;
          putChar('/');
          j = k;
          break;
        case '\\':
          i = i * 31 + 92;
          putChar('\\');
          j = k;
          break;
        case 'x':
          c1 = next();
          this.ch = c1;
          char c2 = next();
          this.ch = c2;
          c1 = (char)(digits[c1] * 16 + digits[c2]);
          i = i * 31 + c1;
          putChar(c1);
          j = k;
          break;
        case 'u':
          j = Integer.parseInt(new String(new char[] { next(), next(), next(), next() }), 16);
          i = i * 31 + j;
          putChar((char)j);
          j = k;
          break;
        }
      }
      i = i * 31 + c1;
      if (j == 0)
      {
        this.sp += 1;
        break;
      }
      if (this.sp == this.sbuf.length)
      {
        putChar(c1);
        break;
      }
      char[] arrayOfChar = this.sbuf;
      int k = this.sp;
      this.sp = (k + 1);
      arrayOfChar[k] = c1;
      break;
      paramChar = this.np + 1;
      break label49;
    }
  }

  public final String scanSymbolUnQuoted(SymbolTable paramSymbolTable)
  {
    boolean[] arrayOfBoolean = CharTypes.firstIdentifierFlags;
    int j = this.ch;
    if ((this.ch >= arrayOfBoolean.length) || (arrayOfBoolean[j] != 0));
    for (int i = 1; i == 0; i = 0)
      throw new JSONException("illegal identifier : " + this.ch);
    arrayOfBoolean = CharTypes.identifierFlags;
    i = j;
    this.np = this.bp;
    for (this.sp = 1; ; this.sp += 1)
    {
      j = next();
      if ((j < arrayOfBoolean.length) && (arrayOfBoolean[j] == 0))
      {
        this.ch = charAt(this.bp);
        this.token = 18;
        if ((this.sp != 4) || (i != 3392903) || (charAt(this.np) != 'n') || (charAt(this.np + 1) != 'u') || (charAt(this.np + 2) != 'l') || (charAt(this.np + 3) != 'l'))
          break;
        return null;
      }
      i = i * 31 + j;
    }
    return addSymbol(this.np, this.sp, i, paramSymbolTable);
  }

  public String scanSymbolWithSeperator(SymbolTable paramSymbolTable, char paramChar)
  {
    this.matchStat = 0;
    int i = this.bp;
    int j = 0 + 1;
    i = charAt(i + 0);
    if (i == 110)
    {
      if ((charAt(this.bp + 1) == 'u') && (charAt(this.bp + 1 + 1) == 'l') && (charAt(this.bp + 1 + 2) == 'l'))
      {
        if (charAt(this.bp + 4) == paramChar)
        {
          this.bp += 4;
          next();
          this.matchStat = 3;
          return null;
        }
      }
      else
      {
        this.matchStat = -1;
        return null;
      }
      this.matchStat = -1;
      return null;
    }
    if (i != 34)
    {
      this.matchStat = -1;
      return null;
    }
    i = 0;
    while (true)
    {
      int m = this.bp;
      int k = j + 1;
      j = charAt(m + j);
      if (j == 34)
      {
        j = this.bp + 0 + 1;
        paramSymbolTable = addSymbol(j, this.bp + k - j - 1, i, paramSymbolTable);
        if (charAt(this.bp + k) == paramChar)
        {
          this.bp += k + 1 - 1;
          next();
          this.matchStat = 3;
          return paramSymbolTable;
        }
      }
      else
      {
        i = i * 31 + j;
        if (j != 92)
          break label275;
        this.matchStat = -1;
        return null;
      }
      this.matchStat = -1;
      return paramSymbolTable;
      label275: j = k;
    }
  }

  public final void scanTreeSet()
  {
    if (this.ch != 'T')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'r')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'e')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'e')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'S')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'e')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 't')
      throw new JSONException("error parse true");
    next();
    if ((this.ch == ' ') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\f') || (this.ch == '\b') || (this.ch == '[') || (this.ch == '('))
    {
      this.token = 22;
      return;
    }
    throw new JSONException("scan set error");
  }

  public final void scanTrue()
  {
    if (this.ch != 't')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'r')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'u')
      throw new JSONException("error parse true");
    next();
    if (this.ch != 'e')
      throw new JSONException("error parse true");
    next();
    if ((this.ch == ' ') || (this.ch == ',') || (this.ch == '}') || (this.ch == ']') || (this.ch == '\n') || (this.ch == '\r') || (this.ch == '\t') || (this.ch == '\032') || (this.ch == '\f') || (this.ch == '\b'))
    {
      this.token = 6;
      return;
    }
    throw new JSONException("scan true error");
  }

  public int scanType(String paramString)
  {
    int k = -1;
    this.matchStat = 0;
    if (!charArrayCompare(typeFieldName))
      i = -2;
    int j;
    label74: 
    do
    {
      return i;
      int m = this.bp + typeFieldName.length;
      int n = paramString.length();
      j = 0;
      while (true)
      {
        if (j >= n)
          break label74;
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
        break label208;
      this.token = 16;
      i = j + 1;
      this.ch = charAt(i);
    }
    while (true)
    {
      this.matchStat = 4;
      this.bp = i;
      return this.matchStat;
      label208: if (this.ch == ']')
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

  public final void skipWhitespace()
  {
    while (whitespaceFlags[this.ch] != 0)
      next();
  }

  public final String stringDefaultValue()
  {
    if (isEnabled(Feature.InitStringFieldAsEmpty))
      return "";
    return null;
  }

  public abstract String stringVal();

  public abstract String subString(int paramInt1, int paramInt2);

  public final int token()
  {
    return this.token;
  }

  public final String tokenName()
  {
    return JSONToken.name(this.token);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.JSONLexerBase
 * JD-Core Version:    0.6.2
 */