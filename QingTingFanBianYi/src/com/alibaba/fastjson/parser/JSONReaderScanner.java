package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.Base64;
import com.alibaba.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;

public final class JSONReaderScanner extends JSONLexerBase
{
  public static final int BUF_INIT_LEN = 8192;
  private static final ThreadLocal<SoftReference<char[]>> BUF_REF_LOCAL = new ThreadLocal();
  private char[] buf;
  private int bufLength;
  private Reader reader;

  public JSONReaderScanner(Reader paramReader)
  {
    this(paramReader, JSON.DEFAULT_PARSER_FEATURE);
  }

  public JSONReaderScanner(Reader paramReader, int paramInt)
  {
    this.reader = paramReader;
    this.features = paramInt;
    SoftReference localSoftReference = (SoftReference)BUF_REF_LOCAL.get();
    if (localSoftReference != null)
    {
      this.buf = ((char[])localSoftReference.get());
      BUF_REF_LOCAL.set(null);
    }
    if (this.buf == null)
      this.buf = new char[8192];
    try
    {
      this.bufLength = paramReader.read(this.buf);
      this.bp = -1;
      next();
      if (this.ch == 65279)
        next();
      return;
    }
    catch (IOException paramReader)
    {
    }
    throw new JSONException(paramReader.getMessage(), paramReader);
  }

  public JSONReaderScanner(String paramString)
  {
    this(paramString, JSON.DEFAULT_PARSER_FEATURE);
  }

  public JSONReaderScanner(String paramString, int paramInt)
  {
    this(new StringReader(paramString), paramInt);
  }

  public JSONReaderScanner(char[] paramArrayOfChar, int paramInt)
  {
    this(paramArrayOfChar, paramInt, JSON.DEFAULT_PARSER_FEATURE);
  }

  public JSONReaderScanner(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this(new CharArrayReader(paramArrayOfChar, 0, paramInt1), paramInt2);
  }

  public final String addSymbol(int paramInt1, int paramInt2, int paramInt3, SymbolTable paramSymbolTable)
  {
    return paramSymbolTable.addSymbol(this.buf, paramInt1, paramInt2, paramInt3);
  }

  protected final void arrayCopy(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.buf, paramInt1, paramArrayOfChar, paramInt2, paramInt3);
  }

  public byte[] bytesValue()
  {
    return Base64.decodeFast(this.buf, this.np + 1, this.sp);
  }

  public final boolean charArrayCompare(char[] paramArrayOfChar)
  {
    int i = 0;
    while (i < paramArrayOfChar.length)
    {
      if (charAt(this.bp + i) != paramArrayOfChar[i])
        return false;
      i += 1;
    }
    return true;
  }

  public final char charAt(int paramInt)
  {
    char c = '\032';
    int i = paramInt;
    if (paramInt >= this.bufLength)
    {
      if (this.bufLength == -1)
        if (paramInt < this.sp)
          c = this.buf[paramInt];
      do
      {
        return c;
        i = this.bufLength - this.bp;
        if (i > 0)
          System.arraycopy(this.buf, this.bp, this.buf, 0, i);
        try
        {
          this.bufLength = this.reader.read(this.buf, i, this.buf.length - i);
          if (this.bufLength == 0)
            throw new JSONException("illegal stat, textLength is zero");
        }
        catch (IOException localIOException)
        {
          throw new JSONException(localIOException.getMessage(), localIOException);
        }
      }
      while (this.bufLength == -1);
      this.bufLength += i;
      i = paramInt - this.bp;
      this.np -= this.bp;
      this.bp = 0;
    }
    return this.buf[i];
  }

  public void close()
  {
    super.close();
    BUF_REF_LOCAL.set(new SoftReference(this.buf));
    this.buf = null;
    IOUtils.close(this.reader);
  }

  protected final void copyTo(int paramInt1, int paramInt2, char[] paramArrayOfChar)
  {
    System.arraycopy(this.buf, paramInt1, paramArrayOfChar, 0, paramInt2);
  }

  public final int indexOf(char paramChar, int paramInt)
  {
    paramInt -= this.bp;
    while (true)
    {
      if (paramChar == charAt(this.bp + paramInt))
        return this.bp + paramInt;
      if (paramChar == '\032')
        return -1;
      paramInt += 1;
    }
  }

  public boolean isEOF()
  {
    return (this.bufLength == -1) || (this.bp == this.buf.length) || ((this.ch == '\032') && (this.bp + 1 == this.buf.length));
  }

  public final char next()
  {
    int j = this.bp + 1;
    this.bp = j;
    int i = j;
    if (j >= this.bufLength)
    {
      if (this.bufLength == -1)
        return '\032';
      if (this.sp > 0)
      {
        j = this.bufLength - this.sp;
        i = j;
        if (this.ch == '"')
          i = j - 1;
        System.arraycopy(this.buf, i, this.buf, 0, this.sp);
      }
      this.np = -1;
      i = this.sp;
      this.bp = i;
      try
      {
        j = this.bp;
        int k = this.buf.length;
        this.bufLength = this.reader.read(this.buf, this.bp, k - j);
        if (this.bufLength == 0)
          throw new JSONException("illegal stat, textLength is zero");
      }
      catch (IOException localIOException)
      {
        throw new JSONException(localIOException.getMessage(), localIOException);
      }
      if (this.bufLength == -1)
      {
        this.ch = '\032';
        return '\032';
      }
      this.bufLength += this.bp;
    }
    char c = this.buf[i];
    this.ch = c;
    return c;
  }

  public final String numberString()
  {
    int j = this.np;
    int i = j;
    if (j == -1)
      i = 0;
    int m = charAt(this.sp + i - 1);
    int k = this.sp;
    if ((m != 76) && (m != 83) && (m != 66) && (m != 70))
    {
      j = k;
      if (m != 68);
    }
    else
    {
      j = k - 1;
    }
    return new String(this.buf, i, j);
  }

  public final String stringVal()
  {
    if (!this.hasSpecial)
    {
      int i = this.np + 1;
      if (i < 0)
        throw new IllegalStateException();
      if (i > this.buf.length - this.sp)
        throw new IllegalStateException();
      return new String(this.buf, i, this.sp);
    }
    return new String(this.sbuf, 0, this.sp);
  }

  public final String subString(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0)
      throw new StringIndexOutOfBoundsException(paramInt2);
    return new String(this.buf, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.JSONReaderScanner
 * JD-Core Version:    0.6.2
 */