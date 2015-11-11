package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader
  implements Closeable
{
  private static final String FALSE = "false";
  private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
  private static final String TRUE = "true";
  private final char[] buffer = new char[1024];
  private int bufferStartColumn = 1;
  private int bufferStartLine = 1;
  private final Reader in;
  private boolean lenient = false;
  private int limit = 0;
  private String name;
  private int pos = 0;
  private boolean skipping;
  private JsonScope[] stack = new JsonScope[32];
  private int stackSize = 0;
  private final StringPool stringPool = new StringPool();
  private JsonToken token;
  private String value;
  private int valueLength;
  private int valuePos;

  static
  {
    JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess()
    {
      public void promoteNameToValue(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if ((paramAnonymousJsonReader instanceof JsonTreeReader))
        {
          ((JsonTreeReader)paramAnonymousJsonReader).promoteNameToValue();
          return;
        }
        paramAnonymousJsonReader.peek();
        if (paramAnonymousJsonReader.token != JsonToken.NAME)
          throw new IllegalStateException("Expected a name but was " + paramAnonymousJsonReader.peek() + " " + " at line " + paramAnonymousJsonReader.getLineNumber() + " column " + paramAnonymousJsonReader.getColumnNumber());
        JsonReader.access$302(paramAnonymousJsonReader, paramAnonymousJsonReader.name);
        JsonReader.access$402(paramAnonymousJsonReader, null);
        JsonReader.access$002(paramAnonymousJsonReader, JsonToken.STRING);
      }
    };
  }

  public JsonReader(Reader paramReader)
  {
    push(JsonScope.EMPTY_DOCUMENT);
    this.skipping = false;
    if (paramReader == null)
      throw new NullPointerException("in == null");
    this.in = paramReader;
  }

  private JsonToken advance()
    throws IOException
  {
    peek();
    JsonToken localJsonToken = this.token;
    this.token = null;
    this.value = null;
    this.name = null;
    return localJsonToken;
  }

  private void checkLenient()
    throws IOException
  {
    if (!this.lenient)
      throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
  }

  private void consumeNonExecutePrefix()
    throws IOException
  {
    nextNonWhitespace(true);
    this.pos -= 1;
    if ((this.pos + NON_EXECUTE_PREFIX.length > this.limit) && (!fillBuffer(NON_EXECUTE_PREFIX.length)))
      return;
    int i = 0;
    while (true)
    {
      if (i >= NON_EXECUTE_PREFIX.length)
        break label80;
      if (this.buffer[(this.pos + i)] != NON_EXECUTE_PREFIX[i])
        break;
      i += 1;
    }
    label80: this.pos += NON_EXECUTE_PREFIX.length;
  }

  private JsonToken decodeLiteral()
    throws IOException
  {
    if (this.valuePos == -1)
      return JsonToken.STRING;
    if ((this.valueLength == 4) && (('n' == this.buffer[this.valuePos]) || ('N' == this.buffer[this.valuePos])) && (('u' == this.buffer[(this.valuePos + 1)]) || ('U' == this.buffer[(this.valuePos + 1)])) && (('l' == this.buffer[(this.valuePos + 2)]) || ('L' == this.buffer[(this.valuePos + 2)])) && (('l' == this.buffer[(this.valuePos + 3)]) || ('L' == this.buffer[(this.valuePos + 3)])))
    {
      this.value = "null";
      return JsonToken.NULL;
    }
    if ((this.valueLength == 4) && (('t' == this.buffer[this.valuePos]) || ('T' == this.buffer[this.valuePos])) && (('r' == this.buffer[(this.valuePos + 1)]) || ('R' == this.buffer[(this.valuePos + 1)])) && (('u' == this.buffer[(this.valuePos + 2)]) || ('U' == this.buffer[(this.valuePos + 2)])) && (('e' == this.buffer[(this.valuePos + 3)]) || ('E' == this.buffer[(this.valuePos + 3)])))
    {
      this.value = "true";
      return JsonToken.BOOLEAN;
    }
    if ((this.valueLength == 5) && (('f' == this.buffer[this.valuePos]) || ('F' == this.buffer[this.valuePos])) && (('a' == this.buffer[(this.valuePos + 1)]) || ('A' == this.buffer[(this.valuePos + 1)])) && (('l' == this.buffer[(this.valuePos + 2)]) || ('L' == this.buffer[(this.valuePos + 2)])) && (('s' == this.buffer[(this.valuePos + 3)]) || ('S' == this.buffer[(this.valuePos + 3)])) && (('e' == this.buffer[(this.valuePos + 4)]) || ('E' == this.buffer[(this.valuePos + 4)])))
    {
      this.value = "false";
      return JsonToken.BOOLEAN;
    }
    this.value = this.stringPool.get(this.buffer, this.valuePos, this.valueLength);
    return decodeNumber(this.buffer, this.valuePos, this.valueLength);
  }

  private JsonToken decodeNumber(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int j = paramInt1;
    int m = paramArrayOfChar[j];
    int k = m;
    int i = j;
    if (m == 45)
    {
      i = j + 1;
      k = paramArrayOfChar[i];
    }
    if (k == 48)
    {
      i += 1;
      k = paramArrayOfChar[i];
      m = k;
      j = i;
      if (k == 46)
      {
        k = i + 1;
        for (i = paramArrayOfChar[k]; ; i = paramArrayOfChar[k])
        {
          m = i;
          j = k;
          if (i < 48)
            break;
          m = i;
          j = k;
          if (i > 57)
            break;
          k += 1;
        }
      }
    }
    else
    {
      if ((k >= 49) && (k <= 57))
      {
        m = i + 1;
        for (j = paramArrayOfChar[m]; ; j = paramArrayOfChar[m])
        {
          k = j;
          i = m;
          if (j < 48)
            break;
          k = j;
          i = m;
          if (j > 57)
            break;
          m += 1;
        }
      }
      return JsonToken.STRING;
    }
    if (m != 101)
    {
      k = j;
      if (m != 69);
    }
    else
    {
      k = j + 1;
      m = paramArrayOfChar[k];
      if (m != 43)
      {
        j = m;
        i = k;
        if (m != 45);
      }
      else
      {
        i = k + 1;
        j = paramArrayOfChar[i];
      }
      if ((j >= 48) && (j <= 57))
      {
        i += 1;
        for (j = paramArrayOfChar[i]; ; j = paramArrayOfChar[i])
        {
          k = i;
          if (j < 48)
            break;
          k = i;
          if (j > 57)
            break;
          i += 1;
        }
      }
      return JsonToken.STRING;
    }
    if (k == paramInt1 + paramInt2)
      return JsonToken.NUMBER;
    return JsonToken.STRING;
  }

  private void expect(JsonToken paramJsonToken)
    throws IOException
  {
    peek();
    if (this.token != paramJsonToken)
      throw new IllegalStateException("Expected " + paramJsonToken + " but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
  }

  private boolean fillBuffer(int paramInt)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int k = this.bufferStartLine;
    int i = this.bufferStartColumn;
    int j = 0;
    int m = this.pos;
    if (j < m)
    {
      if (arrayOfChar[j] == '\n')
      {
        k += 1;
        i = 1;
      }
      while (true)
      {
        j += 1;
        break;
        i += 1;
      }
    }
    this.bufferStartLine = k;
    this.bufferStartColumn = i;
    if (this.limit != this.pos)
    {
      this.limit -= this.pos;
      System.arraycopy(arrayOfChar, this.pos, arrayOfChar, 0, this.limit);
    }
    while (true)
    {
      this.pos = 0;
      do
      {
        i = this.in.read(arrayOfChar, this.limit, arrayOfChar.length - this.limit);
        if (i == -1)
          break;
        this.limit += i;
        if ((this.bufferStartLine == 1) && (this.bufferStartColumn == 1) && (this.limit > 0) && (arrayOfChar[0] == 65279))
        {
          this.pos += 1;
          this.bufferStartColumn -= 1;
        }
      }
      while (this.limit < paramInt);
      return true;
      this.limit = 0;
    }
    return false;
  }

  private int getColumnNumber()
  {
    int i = this.bufferStartColumn;
    int j = 0;
    if (j < this.pos)
    {
      if (this.buffer[j] == '\n')
        i = 1;
      while (true)
      {
        j += 1;
        break;
        i += 1;
      }
    }
    return i;
  }

  private int getLineNumber()
  {
    int j = this.bufferStartLine;
    int i = 0;
    while (i < this.pos)
    {
      int k = j;
      if (this.buffer[i] == '\n')
        k = j + 1;
      i += 1;
      j = k;
    }
    return j;
  }

  private CharSequence getSnippet()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = Math.min(this.pos, 20);
    localStringBuilder.append(this.buffer, this.pos - i, i);
    i = Math.min(this.limit - this.pos, 20);
    localStringBuilder.append(this.buffer, this.pos, i);
    return localStringBuilder;
  }

  private JsonToken nextInArray(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      this.stack[(this.stackSize - 1)] = JsonScope.NONEMPTY_ARRAY;
    while (true)
      switch (nextNonWhitespace(true))
      {
      default:
        this.pos -= 1;
        return nextValue();
        switch (nextNonWhitespace(true))
        {
        case 44:
        default:
          throw syntaxError("Unterminated array");
        case 93:
          this.stackSize -= 1;
          localJsonToken = JsonToken.END_ARRAY;
          this.token = localJsonToken;
          return localJsonToken;
        case 59:
        }
        checkLenient();
      case 93:
      case 44:
      case 59:
      }
    if (paramBoolean)
    {
      this.stackSize -= 1;
      localJsonToken = JsonToken.END_ARRAY;
      this.token = localJsonToken;
      return localJsonToken;
    }
    checkLenient();
    this.pos -= 1;
    this.value = "null";
    JsonToken localJsonToken = JsonToken.NULL;
    this.token = localJsonToken;
    return localJsonToken;
  }

  private JsonToken nextInObject(boolean paramBoolean)
    throws IOException
  {
    int i;
    if (paramBoolean)
    {
      switch (nextNonWhitespace(true))
      {
      default:
        this.pos -= 1;
        i = nextNonWhitespace(true);
        switch (i)
        {
        default:
          checkLenient();
          this.pos -= 1;
          this.name = nextLiteral(false);
          if (this.name.length() != 0)
            break label215;
          throw syntaxError("Expected name");
        case 39:
        case 34:
        }
      case 125:
        this.stackSize -= 1;
        localJsonToken = JsonToken.END_OBJECT;
        this.token = localJsonToken;
        return localJsonToken;
      }
    }
    else
    {
      switch (nextNonWhitespace(true))
      {
      case 44:
      case 59:
      default:
        throw syntaxError("Unterminated object");
      case 125:
      }
      this.stackSize -= 1;
      localJsonToken = JsonToken.END_OBJECT;
      this.token = localJsonToken;
      return localJsonToken;
      checkLenient();
      this.name = nextString((char)i);
    }
    label215: this.stack[(this.stackSize - 1)] = JsonScope.DANGLING_NAME;
    JsonToken localJsonToken = JsonToken.NAME;
    this.token = localJsonToken;
    return localJsonToken;
  }

  private String nextLiteral(boolean paramBoolean)
    throws IOException
  {
    Object localObject1 = null;
    this.valuePos = -1;
    this.valueLength = 0;
    int i = 0;
    Object localObject2;
    int j;
    while (this.pos + i < this.limit)
    {
      localObject2 = localObject1;
      j = i;
      switch (this.buffer[(this.pos + i)])
      {
      default:
        i += 1;
        break;
      case '#':
      case '/':
      case ';':
      case '=':
      case '\\':
        checkLenient();
        j = i;
        localObject2 = localObject1;
      case '\t':
      case '\n':
      case '\f':
      case '\r':
      case ' ':
      case ',':
      case ':':
      case '[':
      case ']':
      case '{':
      case '}':
        label203: if ((!paramBoolean) || (localObject2 != null))
          break label355;
        this.valuePos = this.pos;
        localObject1 = null;
      }
    }
    while (true)
    {
      this.valueLength += j;
      this.pos += j;
      return localObject1;
      if (i < this.buffer.length)
      {
        if (fillBuffer(i + 1))
          break;
        this.buffer[this.limit] = '\000';
        localObject2 = localObject1;
        j = i;
        break label203;
      }
      localObject2 = localObject1;
      if (localObject1 == null)
        localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(this.buffer, this.pos, i);
      this.valueLength += i;
      this.pos += i;
      j = 0;
      i = 0;
      localObject1 = localObject2;
      if (fillBuffer(1))
        break;
      break label203;
      label355: if (this.skipping)
      {
        localObject1 = "skipped!";
      }
      else if (localObject2 == null)
      {
        localObject1 = this.stringPool.get(this.buffer, this.pos, j);
      }
      else
      {
        ((StringBuilder)localObject2).append(this.buffer, this.pos, j);
        localObject1 = ((StringBuilder)localObject2).toString();
      }
    }
  }

  private int nextNonWhitespace(boolean paramBoolean)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    int j = this.limit;
    while (true)
    {
      int k = j;
      int m = i;
      if (i == j)
      {
        this.pos = i;
        if (!fillBuffer(1))
        {
          if (!paramBoolean)
            break;
          throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
        }
        m = this.pos;
        k = this.limit;
      }
      i = m + 1;
      j = arrayOfChar[m];
      switch (j)
      {
      default:
        this.pos = i;
        return j;
      case 9:
      case 10:
      case 13:
      case 32:
        j = k;
        break;
      case 47:
        this.pos = i;
        if ((i == k) && (!fillBuffer(1)))
          return j;
        checkLenient();
        switch (arrayOfChar[this.pos])
        {
        default:
          return j;
        case '*':
          this.pos += 1;
          if (!skipTo("*/"))
            throw syntaxError("Unterminated comment");
          i = this.pos + 2;
          j = this.limit;
          break;
        case '/':
          this.pos += 1;
          skipToEndOfLine();
          i = this.pos;
          j = this.limit;
        }
        break;
      case 35:
        this.pos = i;
        checkLenient();
        skipToEndOfLine();
        i = this.pos;
        j = this.limit;
      }
    }
    return -1;
  }

  private String nextString(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    Object localObject1 = null;
    do
    {
      int m = this.pos;
      int i = this.limit;
      int j;
      for (int k = m; m < i; k = j)
      {
        int n = m + 1;
        char c = arrayOfChar[m];
        if (c == paramChar)
        {
          this.pos = n;
          if (this.skipping)
            return "skipped!";
          if (localObject1 == null)
            return this.stringPool.get(arrayOfChar, k, n - k - 1);
          localObject1.append(arrayOfChar, k, n - k - 1);
          return localObject1.toString();
        }
        localObject2 = localObject1;
        m = i;
        i = n;
        j = k;
        if (c == '\\')
        {
          this.pos = n;
          localObject2 = localObject1;
          if (localObject1 == null)
            localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(arrayOfChar, k, n - k - 1);
          ((StringBuilder)localObject2).append(readEscapeCharacter());
          i = this.pos;
          m = this.limit;
          j = i;
        }
        k = i;
        localObject1 = localObject2;
        i = m;
        m = k;
      }
      Object localObject2 = localObject1;
      if (localObject1 == null)
        localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(arrayOfChar, k, m - k);
      this.pos = m;
      localObject1 = localObject2;
    }
    while (fillBuffer(1));
    throw syntaxError("Unterminated string");
  }

  private JsonToken nextValue()
    throws IOException
  {
    int i = nextNonWhitespace(true);
    switch (i)
    {
    default:
      this.pos -= 1;
      return readLiteral();
    case 123:
      push(JsonScope.EMPTY_OBJECT);
      localJsonToken = JsonToken.BEGIN_OBJECT;
      this.token = localJsonToken;
      return localJsonToken;
    case 91:
      push(JsonScope.EMPTY_ARRAY);
      localJsonToken = JsonToken.BEGIN_ARRAY;
      this.token = localJsonToken;
      return localJsonToken;
    case 39:
      checkLenient();
    case 34:
    }
    this.value = nextString((char)i);
    JsonToken localJsonToken = JsonToken.STRING;
    this.token = localJsonToken;
    return localJsonToken;
  }

  private JsonToken objectValue()
    throws IOException
  {
    switch (nextNonWhitespace(true))
    {
    case 59:
    case 60:
    default:
      throw syntaxError("Expected ':'");
    case 61:
      checkLenient();
      if (((this.pos < this.limit) || (fillBuffer(1))) && (this.buffer[this.pos] == '>'))
        this.pos += 1;
      break;
    case 58:
    }
    this.stack[(this.stackSize - 1)] = JsonScope.NONEMPTY_OBJECT;
    return nextValue();
  }

  private void push(JsonScope paramJsonScope)
  {
    if (this.stackSize == this.stack.length)
    {
      arrayOfJsonScope = new JsonScope[this.stackSize * 2];
      System.arraycopy(this.stack, 0, arrayOfJsonScope, 0, this.stackSize);
      this.stack = arrayOfJsonScope;
    }
    JsonScope[] arrayOfJsonScope = this.stack;
    int i = this.stackSize;
    this.stackSize = (i + 1);
    arrayOfJsonScope[i] = paramJsonScope;
  }

  private char readEscapeCharacter()
    throws IOException
  {
    if ((this.pos == this.limit) && (!fillBuffer(1)))
      throw syntaxError("Unterminated escape sequence");
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    this.pos = (i + 1);
    char c = arrayOfChar[i];
    switch (c)
    {
    default:
      return c;
    case 'u':
      if ((this.pos + 4 > this.limit) && (!fillBuffer(4)))
        throw syntaxError("Unterminated escape sequence");
      c = '\000';
      int j = this.pos;
      i = j;
      if (i < j + 4)
      {
        int k = this.buffer[i];
        int m = (char)(c << '\004');
        if ((k >= 48) && (k <= 57))
          c = (char)(k - 48 + m);
        while (true)
        {
          i += 1;
          break;
          if ((k >= 97) && (k <= 102))
          {
            c = (char)(k - 97 + 10 + m);
          }
          else
          {
            if ((k < 65) || (k > 70))
              break label263;
            c = (char)(k - 65 + 10 + m);
          }
        }
        throw new NumberFormatException("\\u" + this.stringPool.get(this.buffer, this.pos, 4));
      }
      this.pos += 4;
      return c;
    case 't':
      return '\t';
    case 'b':
      return '\b';
    case 'n':
      return '\n';
    case 'r':
      label263: return '\r';
    case 'f':
    }
    return '\f';
  }

  private JsonToken readLiteral()
    throws IOException
  {
    this.value = nextLiteral(true);
    if (this.valueLength == 0)
      throw syntaxError("Expected literal value");
    this.token = decodeLiteral();
    if (this.token == JsonToken.STRING)
      checkLenient();
    return this.token;
  }

  private boolean skipTo(String paramString)
    throws IOException
  {
    if ((this.pos + paramString.length() <= this.limit) || (fillBuffer(paramString.length())))
    {
      int i = 0;
      while (true)
      {
        if (i >= paramString.length())
          break label76;
        if (this.buffer[(this.pos + i)] != paramString.charAt(i))
        {
          this.pos += 1;
          break;
        }
        i += 1;
      }
      label76: return true;
    }
    return false;
  }

  private void skipToEndOfLine()
    throws IOException
  {
    int i;
    do
    {
      if ((this.pos >= this.limit) && (!fillBuffer(1)))
        break;
      char[] arrayOfChar = this.buffer;
      i = this.pos;
      this.pos = (i + 1);
      i = arrayOfChar[i];
    }
    while ((i != 13) && (i != 10));
  }

  private IOException syntaxError(String paramString)
    throws IOException
  {
    throw new MalformedJsonException(paramString + " at line " + getLineNumber() + " column " + getColumnNumber());
  }

  public void beginArray()
    throws IOException
  {
    expect(JsonToken.BEGIN_ARRAY);
  }

  public void beginObject()
    throws IOException
  {
    expect(JsonToken.BEGIN_OBJECT);
  }

  public void close()
    throws IOException
  {
    this.value = null;
    this.token = null;
    this.stack[0] = JsonScope.CLOSED;
    this.stackSize = 1;
    this.in.close();
  }

  public void endArray()
    throws IOException
  {
    expect(JsonToken.END_ARRAY);
  }

  public void endObject()
    throws IOException
  {
    expect(JsonToken.END_OBJECT);
  }

  public boolean hasNext()
    throws IOException
  {
    peek();
    return (this.token != JsonToken.END_OBJECT) && (this.token != JsonToken.END_ARRAY);
  }

  public final boolean isLenient()
  {
    return this.lenient;
  }

  public boolean nextBoolean()
    throws IOException
  {
    peek();
    if (this.token != JsonToken.BOOLEAN)
      throw new IllegalStateException("Expected a boolean but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    if (this.value == "true");
    for (boolean bool = true; ; bool = false)
    {
      advance();
      return bool;
    }
  }

  public double nextDouble()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected a double but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    double d = Double.parseDouble(this.value);
    if ((d >= 1.0D) && (this.value.startsWith("0")))
      throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    if ((!this.lenient) && ((Double.isNaN(d)) || (Double.isInfinite(d))))
      throw new MalformedJsonException("JSON forbids NaN and infinities: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
    return d;
  }

  public int nextInt()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected an int but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    int i;
    try
    {
      i = Integer.parseInt(this.value);
      if ((i >= 1L) && (this.value.startsWith("0")))
        throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      double d;
      int j;
      do
      {
        d = Double.parseDouble(this.value);
        j = (int)d;
        i = j;
      }
      while (j == d);
      throw new NumberFormatException("Expected an int but was " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    }
    advance();
    return i;
  }

  public long nextLong()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected a long but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    long l1;
    try
    {
      l1 = Long.parseLong(this.value);
      if ((l1 >= 1L) && (this.value.startsWith("0")))
        throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      double d;
      long l2;
      do
      {
        d = Double.parseDouble(this.value);
        l2 = ()d;
        l1 = l2;
      }
      while (l2 == d);
      throw new NumberFormatException("Expected a long but was " + this.value + " at line " + getLineNumber() + " column " + getColumnNumber());
    }
    advance();
    return l1;
  }

  public String nextName()
    throws IOException
  {
    peek();
    if (this.token != JsonToken.NAME)
      throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    String str = this.name;
    advance();
    return str;
  }

  public void nextNull()
    throws IOException
  {
    peek();
    if (this.token != JsonToken.NULL)
      throw new IllegalStateException("Expected null but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    advance();
  }

  public String nextString()
    throws IOException
  {
    peek();
    if ((this.token != JsonToken.STRING) && (this.token != JsonToken.NUMBER))
      throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
    String str = this.value;
    advance();
    return str;
  }

  public JsonToken peek()
    throws IOException
  {
    Object localObject;
    if (this.token != null)
      localObject = this.token;
    do
    {
      JsonToken localJsonToken;
      do
      {
        do
        {
          return localObject;
          switch (2.$SwitchMap$com$google$gson$stream$JsonScope[this.stack[(this.stackSize - 1)].ordinal()])
          {
          default:
            throw new AssertionError();
          case 1:
            if (this.lenient)
              consumeNonExecutePrefix();
            this.stack[(this.stackSize - 1)] = JsonScope.NONEMPTY_DOCUMENT;
            localJsonToken = nextValue();
            localObject = localJsonToken;
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          case 7:
          case 8:
          }
        }
        while (this.lenient);
        localObject = localJsonToken;
      }
      while (this.token == JsonToken.BEGIN_ARRAY);
      localObject = localJsonToken;
    }
    while (this.token == JsonToken.BEGIN_OBJECT);
    throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.token + " at line " + getLineNumber() + " column " + getColumnNumber());
    return nextInArray(true);
    return nextInArray(false);
    return nextInObject(true);
    return objectValue();
    return nextInObject(false);
    if (nextNonWhitespace(false) == -1)
      return JsonToken.END_DOCUMENT;
    this.pos -= 1;
    if (!this.lenient)
      throw syntaxError("Expected EOF");
    return nextValue();
    throw new IllegalStateException("JsonReader is closed");
  }

  public final void setLenient(boolean paramBoolean)
  {
    this.lenient = paramBoolean;
  }

  public void skipValue()
    throws IOException
  {
    this.skipping = true;
    int j = 0;
    try
    {
      JsonToken localJsonToken1 = advance();
      JsonToken localJsonToken2;
      int i;
      if (localJsonToken1 != JsonToken.BEGIN_ARRAY)
      {
        localJsonToken2 = JsonToken.BEGIN_OBJECT;
        if (localJsonToken1 != localJsonToken2);
      }
      else
      {
        i = j + 1;
      }
      while (true)
      {
        j = i;
        if (i != 0)
          break;
        return;
        if (localJsonToken1 != JsonToken.END_ARRAY)
        {
          localJsonToken2 = JsonToken.END_OBJECT;
          i = j;
          if (localJsonToken1 != localJsonToken2);
        }
        else
        {
          i = j - 1;
        }
      }
    }
    finally
    {
      this.skipping = false;
    }
  }

  public String toString()
  {
    return getClass().getSimpleName() + " near " + getSnippet();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.stream.JsonReader
 * JD-Core Version:    0.6.2
 */