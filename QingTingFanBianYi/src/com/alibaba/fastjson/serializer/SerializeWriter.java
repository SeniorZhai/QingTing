package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.CharTypes;
import com.alibaba.fastjson.util.Base64;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.nio.charset.Charset;

public final class SerializeWriter extends Writer
{
  private static final ThreadLocal<SoftReference<char[]>> bufLocal = new ThreadLocal();
  protected char[] buf;
  protected int count;
  private int features;
  private final Writer writer;

  public SerializeWriter()
  {
    this((Writer)null);
  }

  public SerializeWriter(int paramInt)
  {
    this(null, paramInt);
  }

  public SerializeWriter(Writer paramWriter)
  {
    this.writer = paramWriter;
    this.features = JSON.DEFAULT_GENERATE_FEATURE;
    paramWriter = (SoftReference)bufLocal.get();
    if (paramWriter != null)
    {
      this.buf = ((char[])paramWriter.get());
      bufLocal.set(null);
    }
    if (this.buf == null)
      this.buf = new char[1024];
  }

  public SerializeWriter(Writer paramWriter, int paramInt)
  {
    this.writer = paramWriter;
    if (paramInt <= 0)
      throw new IllegalArgumentException("Negative initial size: " + paramInt);
    this.buf = new char[paramInt];
  }

  public SerializeWriter(Writer paramWriter, SerializerFeature[] paramArrayOfSerializerFeature)
  {
    this.writer = paramWriter;
    paramWriter = (SoftReference)bufLocal.get();
    if (paramWriter != null)
    {
      this.buf = ((char[])paramWriter.get());
      bufLocal.set(null);
    }
    if (this.buf == null)
      this.buf = new char[1024];
    int j = 0;
    int k = paramArrayOfSerializerFeature.length;
    int i = 0;
    while (i < k)
    {
      j |= paramArrayOfSerializerFeature[i].getMask();
      i += 1;
    }
    this.features = j;
  }

  public SerializeWriter(SerializerFeature[] paramArrayOfSerializerFeature)
  {
    this(null, paramArrayOfSerializerFeature);
  }

  static final boolean isSpecial(char paramChar, int paramInt)
  {
    if (paramChar == ' ');
    do
    {
      return false;
      if ((paramChar == '/') && (SerializerFeature.isEnabled(paramInt, SerializerFeature.WriteSlashAsSpecial)))
        return true;
    }
    while (((paramChar > '#') && (paramChar != '\\')) || ((paramChar > '\r') && (paramChar != '\\') && (paramChar != '"')));
    return true;
  }

  private void writeFieldValueStringWithDoubleQuote(char paramChar, String paramString1, String paramString2, boolean paramBoolean)
  {
    int k = paramString1.length();
    int i = this.count;
    int j;
    if (paramString2 == null)
    {
      j = 4;
      i += k + 8;
    }
    while (i > this.buf.length)
      if (this.writer != null)
      {
        write(paramChar);
        writeStringWithDoubleQuote(paramString1, ':', paramBoolean);
        writeStringWithDoubleQuote(paramString2, '\000', paramBoolean);
        return;
        j = paramString2.length();
        i += k + j + 6;
      }
      else
      {
        expandCapacity(i);
      }
    this.buf[this.count] = paramChar;
    int m = this.count + 2;
    int n = m + k;
    this.buf[(this.count + 1)] = '"';
    paramString1.getChars(0, k, this.buf, m);
    this.count = i;
    this.buf[n] = '"';
    m = n + 1;
    paramString1 = this.buf;
    k = m + 1;
    paramString1[m] = 58;
    if (paramString2 == null)
    {
      paramString1 = this.buf;
      i = k + 1;
      paramString1[k] = 110;
      paramString1 = this.buf;
      j = i + 1;
      paramString1[i] = 117;
      paramString1 = this.buf;
      i = j + 1;
      paramString1[j] = 108;
      this.buf[i] = 'l';
      return;
    }
    paramString1 = this.buf;
    int i5 = k + 1;
    paramString1[k] = 34;
    int i6 = i5 + j;
    paramString2.getChars(0, j, this.buf, i5);
    int i1;
    int i2;
    if ((paramBoolean) && (!isEnabled(SerializerFeature.DisableCheckSpecialChar)))
    {
      i1 = 0;
      i2 = -1;
      k = -1;
      m = 0;
      j = i5;
      int i4 = i;
      i = j;
      j = k;
      if (i < i6)
      {
        paramChar = this.buf[i];
        k = j;
        int i3 = i4;
        n = i1;
        int i7;
        if (paramChar == ' ')
        {
          i7 = i1 + 1;
          i1 = i;
          int i8 = paramChar;
          i4 += 4;
          k = j;
          m = i8;
          i2 = i1;
          i3 = i4;
          n = i7;
          if (j == -1)
          {
            k = i;
            n = i7;
            i3 = i4;
            i2 = i1;
            m = i8;
          }
        }
        if (paramChar >= ']')
        {
          i1 = n;
          j = k;
        }
        while (true)
        {
          i += 1;
          i4 = i3;
          break;
          j = k;
          i1 = n;
          if (isSpecial(paramChar, this.features))
          {
            i4 = n + 1;
            n = i;
            i7 = paramChar;
            j = k;
            m = i7;
            i2 = n;
            i1 = i4;
            if (k == -1)
            {
              j = i;
              m = i7;
              i2 = n;
              i1 = i4;
            }
          }
        }
      }
      if (i1 > 0)
      {
        i = i4 + i1;
        if (i > this.buf.length)
          expandCapacity(i);
        this.count = i;
        if (i1 != 1)
          break label760;
        if (m != 8232)
          break label707;
        System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 6, i6 - i2 - 1);
        this.buf[i2] = '\\';
        paramString1 = this.buf;
        i = i2 + 1;
        paramString1[i] = 117;
        paramString1 = this.buf;
        i += 1;
        paramString1[i] = 50;
        paramString1 = this.buf;
        i += 1;
        paramString1[i] = 48;
        paramString1 = this.buf;
        i += 1;
        paramString1[i] = 50;
        this.buf[(i + 1)] = '8';
      }
    }
    label707: label760: 
    while (i1 <= 1)
      while (true)
      {
        this.buf[(this.count - 1)] = '"';
        return;
        System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, i6 - i2 - 1);
        this.buf[i2] = '\\';
        this.buf[(i2 + 1)] = CharTypes.replaceChars[m];
      }
    i = j;
    j -= i5;
    k = i6;
    label781: if (j < paramString2.length())
    {
      paramChar = paramString2.charAt(j);
      if (((paramChar >= CharTypes.specicalFlags_doubleQuotes.length) || (CharTypes.specicalFlags_doubleQuotes[paramChar] == 0)) && ((paramChar != '/') || (!isEnabled(SerializerFeature.WriteSlashAsSpecial))))
        break label881;
      paramString1 = this.buf;
      m = i + 1;
      paramString1[i] = 92;
      paramString1 = this.buf;
      i = m + 1;
      paramString1[m] = CharTypes.replaceChars[paramChar];
      k += 1;
    }
    while (true)
    {
      j += 1;
      break label781;
      break;
      label881: this.buf[i] = paramChar;
      i += 1;
    }
  }

  private void writeKeyWithDoubleQuoteIfHasSpecial(String paramString)
  {
    boolean[] arrayOfBoolean = CharTypes.specicalFlags_doubleQuotes;
    int n = paramString.length();
    int i4 = this.count + n + 1;
    int m;
    int k;
    if (i4 > this.buf.length)
    {
      if (this.writer != null)
      {
        if (n == 0)
        {
          write('"');
          write('"');
          write(':');
          return;
        }
        m = 0;
        k = 0;
        j = m;
        label118: int i;
        if (k < n)
        {
          j = paramString.charAt(k);
          if ((j < arrayOfBoolean.length) && (arrayOfBoolean[j] != 0))
            j = 1;
        }
        else
        {
          if (j != 0)
            write('"');
          k = 0;
          if (k >= n)
            break label185;
          i = paramString.charAt(k);
          if ((i >= arrayOfBoolean.length) || (arrayOfBoolean[i] == 0))
            break label177;
          write('\\');
          write(CharTypes.replaceChars[i]);
        }
        while (true)
        {
          k += 1;
          break label118;
          k += 1;
          break;
          label177: write(i);
        }
        label185: if (j != 0)
          write('"');
        write(':');
        return;
      }
      expandCapacity(i4);
    }
    if (n == 0)
    {
      if (this.count + 3 > this.buf.length)
        expandCapacity(this.count + 3);
      paramString = this.buf;
      j = this.count;
      this.count = (j + 1);
      paramString[j] = 34;
      paramString = this.buf;
      j = this.count;
      this.count = (j + 1);
      paramString[j] = 34;
      paramString = this.buf;
      j = this.count;
      this.count = (j + 1);
      paramString[j] = 58;
      return;
    }
    int i5 = this.count;
    int i1 = i5 + n;
    paramString.getChars(0, n, this.buf, i5);
    this.count = i4;
    int i2 = 0;
    int j = i5;
    if (j < i1)
    {
      int i6 = this.buf[j];
      m = i1;
      int i3 = i2;
      n = j;
      k = i4;
      if (i6 < arrayOfBoolean.length)
      {
        m = i1;
        i3 = i2;
        n = j;
        k = i4;
        if (arrayOfBoolean[i6] != 0)
        {
          if (i2 != 0)
            break label579;
          k = i4 + 3;
          if (k > this.buf.length)
            expandCapacity(k);
          this.count = k;
          System.arraycopy(this.buf, j + 1, this.buf, j + 3, i1 - j - 1);
          System.arraycopy(this.buf, 0, this.buf, 1, j);
          this.buf[i5] = '"';
          paramString = this.buf;
          j += 1;
          paramString[j] = 92;
          paramString = this.buf;
          n = j + 1;
          paramString[n] = CharTypes.replaceChars[i6];
          m = i1 + 2;
          this.buf[(this.count - 2)] = '"';
        }
      }
      for (i3 = 1; ; i3 = i2)
      {
        j = n + 1;
        i1 = m;
        i2 = i3;
        i4 = k;
        break;
        label579: k = i4 + 1;
        if (k > this.buf.length)
          expandCapacity(k);
        this.count = k;
        System.arraycopy(this.buf, j + 1, this.buf, j + 2, i1 - j);
        this.buf[j] = '\\';
        paramString = this.buf;
        n = j + 1;
        paramString[n] = CharTypes.replaceChars[i6];
        m = i1 + 1;
      }
    }
    this.buf[(this.count - 1)] = ':';
  }

  private void writeKeyWithSingleQuoteIfHasSpecial(String paramString)
  {
    boolean[] arrayOfBoolean = CharTypes.specicalFlags_singleQuotes;
    int n = paramString.length();
    int i4 = this.count + n + 1;
    int m;
    int k;
    if (i4 > this.buf.length)
    {
      if (this.writer != null)
      {
        if (n == 0)
        {
          write('\'');
          write('\'');
          write(':');
          return;
        }
        m = 0;
        k = 0;
        j = m;
        label118: int i;
        if (k < n)
        {
          j = paramString.charAt(k);
          if ((j < arrayOfBoolean.length) && (arrayOfBoolean[j] != 0))
            j = 1;
        }
        else
        {
          if (j != 0)
            write('\'');
          k = 0;
          if (k >= n)
            break label185;
          i = paramString.charAt(k);
          if ((i >= arrayOfBoolean.length) || (arrayOfBoolean[i] == 0))
            break label177;
          write('\\');
          write(CharTypes.replaceChars[i]);
        }
        while (true)
        {
          k += 1;
          break label118;
          k += 1;
          break;
          label177: write(i);
        }
        label185: if (j != 0)
          write('\'');
        write(':');
        return;
      }
      expandCapacity(i4);
    }
    if (n == 0)
    {
      if (this.count + 3 > this.buf.length)
        expandCapacity(this.count + 3);
      paramString = this.buf;
      j = this.count;
      this.count = (j + 1);
      paramString[j] = 39;
      paramString = this.buf;
      j = this.count;
      this.count = (j + 1);
      paramString[j] = 39;
      paramString = this.buf;
      j = this.count;
      this.count = (j + 1);
      paramString[j] = 58;
      return;
    }
    int i5 = this.count;
    int i1 = i5 + n;
    paramString.getChars(0, n, this.buf, i5);
    this.count = i4;
    int i2 = 0;
    int j = i5;
    if (j < i1)
    {
      int i6 = this.buf[j];
      m = i1;
      int i3 = i2;
      n = j;
      k = i4;
      if (i6 < arrayOfBoolean.length)
      {
        m = i1;
        i3 = i2;
        n = j;
        k = i4;
        if (arrayOfBoolean[i6] != 0)
        {
          if (i2 != 0)
            break label579;
          k = i4 + 3;
          if (k > this.buf.length)
            expandCapacity(k);
          this.count = k;
          System.arraycopy(this.buf, j + 1, this.buf, j + 3, i1 - j - 1);
          System.arraycopy(this.buf, 0, this.buf, 1, j);
          this.buf[i5] = '\'';
          paramString = this.buf;
          j += 1;
          paramString[j] = 92;
          paramString = this.buf;
          n = j + 1;
          paramString[n] = CharTypes.replaceChars[i6];
          m = i1 + 2;
          this.buf[(this.count - 2)] = '\'';
        }
      }
      for (i3 = 1; ; i3 = i2)
      {
        j = n + 1;
        i1 = m;
        i2 = i3;
        i4 = k;
        break;
        label579: k = i4 + 1;
        if (k > this.buf.length)
          expandCapacity(k);
        this.count = k;
        System.arraycopy(this.buf, j + 1, this.buf, j + 2, i1 - j);
        this.buf[j] = '\\';
        paramString = this.buf;
        n = j + 1;
        paramString[n] = CharTypes.replaceChars[i6];
        m = i1 + 1;
      }
    }
    this.buf[(i4 - 1)] = ':';
  }

  private void writeStringWithDoubleQuote(String paramString, char paramChar)
  {
    writeStringWithDoubleQuote(paramString, paramChar, true);
  }

  private void writeStringWithDoubleQuote(String paramString, char paramChar, boolean paramBoolean)
  {
    if (paramString == null)
    {
      writeNull();
      if (paramChar != 0)
        write(paramChar);
    }
    int m;
    do
    {
      return;
      n = paramString.length();
      m = this.count + n + 2;
      k = m;
      if (paramChar != 0)
        k = m + 1;
      if (k <= this.buf.length)
        break label410;
      if (this.writer == null)
        break;
      write('"');
      k = 0;
      if (k < paramString.length())
      {
        int i = paramString.charAt(k);
        if (isEnabled(SerializerFeature.BrowserCompatible))
          if ((i == 8) || (i == 12) || (i == 10) || (i == 13) || (i == 9) || (i == 34) || (i == 47) || (i == 92))
          {
            write('\\');
            write(CharTypes.replaceChars[i]);
          }
        while (true)
        {
          k += 1;
          break;
          if (i < 32)
          {
            write('\\');
            write('u');
            write('0');
            write('0');
            write(CharTypes.ASCII_CHARS[(i * 2)]);
            write(CharTypes.ASCII_CHARS[(i * 2 + 1)]);
          }
          else if (i >= 127)
          {
            write('\\');
            write('u');
            write(CharTypes.digits[(i >>> 12 & 0xF)]);
            write(CharTypes.digits[(i >>> 8 & 0xF)]);
            write(CharTypes.digits[(i >>> 4 & 0xF)]);
            write(CharTypes.digits[(i & 0xF)]);
            continue;
            if (((i < CharTypes.specicalFlags_doubleQuotes.length) && (CharTypes.specicalFlags_doubleQuotes[i] != 0)) || ((i == 47) && (isEnabled(SerializerFeature.WriteSlashAsSpecial))))
            {
              write('\\');
              write(CharTypes.replaceChars[i]);
            }
          }
          else
          {
            write(i);
          }
        }
      }
      write('"');
    }
    while (paramChar == 0);
    write(paramChar);
    return;
    expandCapacity(k);
    label410: int i8 = this.count + 1;
    int i9 = i8 + n;
    this.buf[this.count] = '"';
    paramString.getChars(0, n, this.buf, i8);
    this.count = k;
    int j;
    if (isEnabled(SerializerFeature.BrowserCompatible))
    {
      m = -1;
      n = i8;
      i1 = k;
      k = n;
      if (k < i9)
      {
        i2 = this.buf[k];
        if ((i2 == 34) || (i2 == 47) || (i2 == 92))
        {
          m = k;
          n = i1 + 1;
        }
        while (true)
        {
          k += 1;
          i1 = n;
          break;
          if ((i2 == 8) || (i2 == 12) || (i2 == 10) || (i2 == 13) || (i2 == 9))
          {
            m = k;
            n = i1 + 1;
          }
          else if (i2 < 32)
          {
            m = k;
            n = i1 + 5;
          }
          else
          {
            n = i1;
            if (i2 >= 127)
            {
              m = k;
              n = i1 + 5;
            }
          }
        }
      }
      if (i1 > this.buf.length)
        expandCapacity(i1);
      this.count = i1;
      if (m >= i8)
      {
        j = this.buf[m];
        if ((j == 8) || (j == 12) || (j == 10) || (j == 13) || (j == 9))
        {
          System.arraycopy(this.buf, m + 1, this.buf, m + 2, i9 - m - 1);
          this.buf[m] = '\\';
          this.buf[(m + 1)] = CharTypes.replaceChars[j];
          k = i9 + 1;
        }
        while (true)
        {
          m -= 1;
          i9 = k;
          break;
          if ((j == 34) || (j == 47) || (j == 92))
          {
            System.arraycopy(this.buf, m + 1, this.buf, m + 2, i9 - m - 1);
            this.buf[m] = '\\';
            this.buf[(m + 1)] = j;
            k = i9 + 1;
          }
          else if (j < 32)
          {
            System.arraycopy(this.buf, m + 1, this.buf, m + 6, i9 - m - 1);
            this.buf[m] = '\\';
            this.buf[(m + 1)] = 'u';
            this.buf[(m + 2)] = '0';
            this.buf[(m + 3)] = '0';
            this.buf[(m + 4)] = CharTypes.ASCII_CHARS[(j * 2)];
            this.buf[(m + 5)] = CharTypes.ASCII_CHARS[(j * 2 + 1)];
            k = i9 + 5;
          }
          else
          {
            k = i9;
            if (j >= 127)
            {
              System.arraycopy(this.buf, m + 1, this.buf, m + 6, i9 - m - 1);
              this.buf[m] = '\\';
              this.buf[(m + 1)] = 'u';
              this.buf[(m + 2)] = CharTypes.digits[(j >>> 12 & 0xF)];
              this.buf[(m + 3)] = CharTypes.digits[(j >>> 8 & 0xF)];
              this.buf[(m + 4)] = CharTypes.digits[(j >>> 4 & 0xF)];
              this.buf[(m + 5)] = CharTypes.digits[(j & 0xF)];
              k = i9 + 5;
            }
          }
        }
      }
      if (paramChar != 0)
      {
        this.buf[(this.count - 2)] = '"';
        this.buf[(this.count - 1)] = paramChar;
        return;
      }
      this.buf[(this.count - 1)] = '"';
      return;
    }
    int i10 = 0;
    int i1 = 0;
    int i5 = -1;
    int n = -1;
    int i3 = 0;
    int i7 = 0;
    int i2 = n;
    int i4 = i5;
    int i6 = k;
    if (paramBoolean)
    {
      i2 = i8;
      m = k;
      k = i2;
      i2 = n;
      i3 = i7;
      i4 = i5;
      i6 = m;
      i10 = i1;
      if (k < i9)
      {
        int i11 = this.buf[k];
        if (i11 >= 93)
        {
          i6 = n;
          i2 = i7;
          i3 = i5;
          i10 = m;
          i4 = i1;
          if (i11 == 8232)
          {
            i5 = i1 + 1;
            i1 = k;
            m += 4;
            i6 = n;
            i2 = i11;
            i3 = i1;
            i10 = m;
            i4 = i5;
            if (n == -1)
            {
              i6 = k;
              i4 = i5;
              i10 = m;
              i3 = i1;
              i2 = i11;
            }
          }
        }
        while (true)
        {
          k += 1;
          n = i6;
          i7 = i2;
          i5 = i3;
          m = i10;
          i1 = i4;
          break;
          i6 = n;
          i2 = i7;
          i3 = i5;
          i10 = m;
          i4 = i1;
          if (i11 != 32)
            if (i11 >= 48)
            {
              i6 = n;
              i2 = i7;
              i3 = i5;
              i10 = m;
              i4 = i1;
              if (i11 != 92);
            }
            else if ((i11 >= CharTypes.specicalFlags_doubleQuotes.length) || (CharTypes.specicalFlags_doubleQuotes[i11] == 0))
            {
              i6 = n;
              i2 = i7;
              i3 = i5;
              i10 = m;
              i4 = i1;
              if (i11 == 47)
              {
                i6 = n;
                i2 = i7;
                i3 = i5;
                i10 = m;
                i4 = i1;
                if (!isEnabled(SerializerFeature.WriteSlashAsSpecial));
              }
            }
            else
            {
              i5 = i1 + 1;
              i1 = k;
              i6 = n;
              i2 = i11;
              i3 = i1;
              i10 = m;
              i4 = i5;
              if (n == -1)
              {
                i6 = k;
                i2 = i11;
                i3 = i1;
                i10 = m;
                i4 = i5;
              }
            }
        }
      }
    }
    int k = i6 + i10;
    if (k > this.buf.length)
      expandCapacity(k);
    this.count = k;
    if (i10 == 1)
      if (i3 == 8232)
      {
        System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 6, i9 - i4 - 1);
        this.buf[i4] = '\\';
        paramString = this.buf;
        k = i4 + 1;
        paramString[k] = 117;
        paramString = this.buf;
        k += 1;
        paramString[k] = 50;
        paramString = this.buf;
        k += 1;
        paramString[k] = 48;
        paramString = this.buf;
        k += 1;
        paramString[k] = 50;
        this.buf[(k + 1)] = '8';
      }
    while (paramChar != 0)
    {
      this.buf[(this.count - 2)] = '"';
      this.buf[(this.count - 1)] = paramChar;
      return;
      System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 2, i9 - i4 - 1);
      this.buf[i4] = '\\';
      this.buf[(i4 + 1)] = CharTypes.replaceChars[i3];
      continue;
      if (i10 > 1)
      {
        k = i2;
        m = i2 - i8;
        label1823: if (m < paramString.length())
        {
          j = paramString.charAt(m);
          if (((j >= CharTypes.specicalFlags_doubleQuotes.length) || (CharTypes.specicalFlags_doubleQuotes[j] == 0)) && ((j != 47) || (!isEnabled(SerializerFeature.WriteSlashAsSpecial))))
            break label1932;
          char[] arrayOfChar = this.buf;
          n = k + 1;
          arrayOfChar[k] = '\\';
          arrayOfChar = this.buf;
          k = n + 1;
          arrayOfChar[n] = CharTypes.replaceChars[j];
          i9 += 1;
        }
        while (true)
        {
          m += 1;
          break label1823;
          break;
          label1932: this.buf[k] = j;
          k += 1;
        }
      }
    }
    this.buf[(this.count - 1)] = '"';
  }

  private void writeStringWithSingleQuote(String paramString)
  {
    if (paramString == null)
    {
      i = this.count + 4;
      if (i > this.buf.length)
        expandCapacity(i);
      "null".getChars(0, 4, this.buf, this.count);
      this.count = i;
      return;
    }
    int i = paramString.length();
    int i6 = this.count + i + 2;
    if (i6 > this.buf.length)
    {
      if (this.writer != null)
      {
        write('\'');
        i = 0;
        if (i < paramString.length())
        {
          char c = paramString.charAt(i);
          if ((c <= '\r') || (c == '\\') || (c == '\'') || ((c == '/') && (isEnabled(SerializerFeature.WriteSlashAsSpecial))))
          {
            write('\\');
            write(CharTypes.replaceChars[c]);
          }
          while (true)
          {
            i += 1;
            break;
            write(c);
          }
        }
        write('\'');
        return;
      }
      expandCapacity(i6);
    }
    int i3 = this.count + 1;
    int i5 = i3 + i;
    this.buf[this.count] = '\'';
    paramString.getChars(0, i, this.buf, i3);
    this.count = i6;
    int j = 0;
    int m = -1;
    int k = 0;
    i = i3;
    while (i < i5)
    {
      int i4 = this.buf[i];
      int i2;
      int i1;
      int n;
      if ((i4 > 13) && (i4 != 92) && (i4 != 39))
      {
        i2 = k;
        i1 = m;
        n = j;
        if (i4 == 47)
        {
          i2 = k;
          i1 = m;
          n = j;
          if (!isEnabled(SerializerFeature.WriteSlashAsSpecial));
        }
      }
      else
      {
        n = j + 1;
        i1 = i;
        i2 = i4;
      }
      i += 1;
      k = i2;
      m = i1;
      j = n;
    }
    i = i6 + j;
    if (i > this.buf.length)
      expandCapacity(i);
    this.count = i;
    if (j == 1)
    {
      System.arraycopy(this.buf, m + 1, this.buf, m + 2, i5 - m - 1);
      this.buf[m] = '\\';
      this.buf[(m + 1)] = CharTypes.replaceChars[k];
    }
    while (true)
    {
      this.buf[(this.count - 1)] = '\'';
      return;
      if (j > 1)
      {
        System.arraycopy(this.buf, m + 1, this.buf, m + 2, i5 - m - 1);
        this.buf[m] = '\\';
        paramString = this.buf;
        i = m + 1;
        paramString[i] = CharTypes.replaceChars[k];
        j = i5 + 1;
        i -= 2;
        while (i >= i3)
        {
          m = this.buf[i];
          if ((m > 13) && (m != 92) && (m != 39))
          {
            k = j;
            if (m == 47)
            {
              k = j;
              if (!isEnabled(SerializerFeature.WriteSlashAsSpecial));
            }
          }
          else
          {
            System.arraycopy(this.buf, i + 1, this.buf, i + 2, j - i - 1);
            this.buf[i] = '\\';
            this.buf[(i + 1)] = CharTypes.replaceChars[m];
            k = j + 1;
          }
          i -= 1;
          j = k;
        }
      }
    }
  }

  public SerializeWriter append(char paramChar)
  {
    write(paramChar);
    return this;
  }

  public SerializeWriter append(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null);
    for (paramCharSequence = "null"; ; paramCharSequence = paramCharSequence.toString())
    {
      write(paramCharSequence, 0, paramCharSequence.length());
      return this;
    }
  }

  public SerializeWriter append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null)
      localObject = "null";
    paramCharSequence = ((CharSequence)localObject).subSequence(paramInt1, paramInt2).toString();
    write(paramCharSequence, 0, paramCharSequence.length());
    return this;
  }

  public void close()
  {
    if ((this.writer != null) && (this.count > 0))
      flush();
    if (this.buf.length <= 8192)
      bufLocal.set(new SoftReference(this.buf));
    this.buf = null;
  }

  public void config(SerializerFeature paramSerializerFeature, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.features |= paramSerializerFeature.getMask();
      return;
    }
    this.features &= (paramSerializerFeature.getMask() ^ 0xFFFFFFFF);
  }

  public void expandCapacity(int paramInt)
  {
    int j = this.buf.length * 3 / 2 + 1;
    int i = j;
    if (j < paramInt)
      i = paramInt;
    char[] arrayOfChar = new char[i];
    System.arraycopy(this.buf, 0, arrayOfChar, 0, this.count);
    this.buf = arrayOfChar;
  }

  public void flush()
  {
    if (this.writer == null)
      return;
    try
    {
      this.writer.write(this.buf, 0, this.count);
      this.writer.flush();
      this.count = 0;
      return;
    }
    catch (IOException localIOException)
    {
      throw new JSONException(localIOException.getMessage(), localIOException);
    }
  }

  public int getBufferLength()
  {
    return this.buf.length;
  }

  public boolean isEnabled(SerializerFeature paramSerializerFeature)
  {
    return SerializerFeature.isEnabled(this.features, paramSerializerFeature);
  }

  public void reset()
  {
    this.count = 0;
  }

  public int size()
  {
    return this.count;
  }

  public byte[] toBytes(String paramString)
  {
    if (this.writer != null)
      throw new UnsupportedOperationException("writer not null");
    String str = paramString;
    if (paramString == null)
      str = "UTF-8";
    return new SerialWriterStringEncoder(Charset.forName(str)).encode(this.buf, 0, this.count);
  }

  public char[] toCharArray()
  {
    if (this.writer != null)
      throw new UnsupportedOperationException("writer not null");
    char[] arrayOfChar = new char[this.count];
    System.arraycopy(this.buf, 0, arrayOfChar, 0, this.count);
    return arrayOfChar;
  }

  public String toString()
  {
    return new String(this.buf, 0, this.count);
  }

  public void write(char paramChar)
  {
    int j = this.count + 1;
    int i = j;
    if (j > this.buf.length)
    {
      if (this.writer != null)
        break label48;
      expandCapacity(j);
    }
    for (i = j; ; i = 1)
    {
      this.buf[this.count] = paramChar;
      this.count = i;
      return;
      label48: flush();
    }
  }

  public void write(int paramInt)
  {
    int j = this.count + 1;
    int i = j;
    if (j > this.buf.length)
    {
      if (this.writer != null)
        break label49;
      expandCapacity(j);
    }
    for (i = j; ; i = 1)
    {
      this.buf[this.count] = ((char)paramInt);
      this.count = i;
      return;
      label49: flush();
    }
  }

  public void write(String paramString)
  {
    if (paramString == null)
    {
      writeNull();
      return;
    }
    write(paramString, 0, paramString.length());
  }

  public void write(String paramString, int paramInt1, int paramInt2)
  {
    int m = this.count + paramInt2;
    int i = m;
    int j = paramInt1;
    int k = paramInt2;
    if (m > this.buf.length)
    {
      i = paramInt1;
      j = paramInt2;
      if (this.writer != null)
        break label83;
      expandCapacity(m);
      k = paramInt2;
      j = paramInt1;
      i = m;
    }
    while (true)
    {
      paramString.getChars(j, j + k, this.buf, this.count);
      this.count = i;
      return;
      label83: 
      do
      {
        paramInt2 = this.buf.length - this.count;
        paramString.getChars(i, i + paramInt2, this.buf, this.count);
        this.count = this.buf.length;
        flush();
        paramInt1 = j - paramInt2;
        paramInt2 = i + paramInt2;
        i = paramInt2;
        j = paramInt1;
      }
      while (paramInt1 > this.buf.length);
      i = paramInt1;
      j = paramInt2;
      k = paramInt1;
    }
  }

  public void write(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      write("true");
      return;
    }
    write("false");
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfChar.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length) || (paramInt1 + paramInt2 < 0))
      throw new IndexOutOfBoundsException();
    if (paramInt2 == 0)
      return;
    int m = this.count + paramInt2;
    int i = m;
    int j = paramInt1;
    int k = paramInt2;
    if (m > this.buf.length)
    {
      i = paramInt1;
      j = paramInt2;
      if (this.writer != null)
        break label121;
      expandCapacity(m);
      k = paramInt2;
      j = paramInt1;
      i = m;
    }
    while (true)
    {
      System.arraycopy(paramArrayOfChar, j, this.buf, this.count, k);
      this.count = i;
      return;
      label121: 
      do
      {
        paramInt2 = this.buf.length - this.count;
        System.arraycopy(paramArrayOfChar, i, this.buf, this.count, paramInt2);
        this.count = this.buf.length;
        flush();
        paramInt1 = j - paramInt2;
        paramInt2 = i + paramInt2;
        i = paramInt2;
        j = paramInt1;
      }
      while (paramInt1 > this.buf.length);
      i = paramInt1;
      j = paramInt2;
      k = paramInt1;
    }
  }

  public void writeBooleanAndChar(boolean paramBoolean, char paramChar)
  {
    if (paramBoolean)
    {
      if (paramChar == ',')
      {
        write("true,");
        return;
      }
      if (paramChar == ']')
      {
        write("true]");
        return;
      }
      write("true");
      write(paramChar);
      return;
    }
    if (paramChar == ',')
    {
      write("false,");
      return;
    }
    if (paramChar == ']')
    {
      write("false]");
      return;
    }
    write("false");
    write(paramChar);
  }

  public void writeByteArray(byte[] paramArrayOfByte)
  {
    int k = paramArrayOfByte.length;
    if (k == 0)
    {
      write("\"\"");
      return;
    }
    char[] arrayOfChar1 = Base64.CA;
    int m = k / 3 * 3;
    int j = (k - 1) / 3;
    int i = this.count;
    int n = this.count + (j + 1 << 2) + 2;
    if (n > this.buf.length)
    {
      if (this.writer != null)
      {
        write('"');
        i = 0;
        while (i < m)
        {
          j = i + 1;
          n = paramArrayOfByte[i];
          i = j + 1;
          j = (n & 0xFF) << 16 | (paramArrayOfByte[j] & 0xFF) << 8 | paramArrayOfByte[i] & 0xFF;
          write(arrayOfChar1[(j >>> 18 & 0x3F)]);
          write(arrayOfChar1[(j >>> 12 & 0x3F)]);
          write(arrayOfChar1[(j >>> 6 & 0x3F)]);
          write(arrayOfChar1[(j & 0x3F)]);
          i += 1;
        }
        j = k - m;
        if (j > 0)
        {
          m = paramArrayOfByte[m];
          if (j != 2)
            break label312;
          i = (paramArrayOfByte[(k - 1)] & 0xFF) << 2;
          i = (m & 0xFF) << 10 | i;
          write(arrayOfChar1[(i >> 12)]);
          write(arrayOfChar1[(i >>> 6 & 0x3F)]);
          if (j != 2)
            break label318;
        }
        label312: label318: for (c = arrayOfChar1[(i & 0x3F)]; ; c = '=')
        {
          write(c);
          write('=');
          write('"');
          return;
          i = 0;
          break;
        }
      }
      expandCapacity(n);
    }
    this.count = n;
    this.buf[i] = '"';
    i += 1;
    j = 0;
    while (j < m)
    {
      int i1 = j + 1;
      int i2 = paramArrayOfByte[j];
      j = i1 + 1;
      i1 = (i2 & 0xFF) << 16 | (paramArrayOfByte[i1] & 0xFF) << 8 | paramArrayOfByte[j] & 0xFF;
      char[] arrayOfChar2 = this.buf;
      i2 = i + 1;
      arrayOfChar2[i] = arrayOfChar1[(i1 >>> 18 & 0x3F)];
      arrayOfChar2 = this.buf;
      i = i2 + 1;
      arrayOfChar2[i2] = arrayOfChar1[(i1 >>> 12 & 0x3F)];
      arrayOfChar2 = this.buf;
      i2 = i + 1;
      arrayOfChar2[i] = arrayOfChar1[(i1 >>> 6 & 0x3F)];
      arrayOfChar2 = this.buf;
      i = i2 + 1;
      arrayOfChar2[i2] = arrayOfChar1[(i1 & 0x3F)];
      j += 1;
    }
    j = k - m;
    if (j > 0)
    {
      m = paramArrayOfByte[m];
      if (j != 2)
        break label661;
      i = (paramArrayOfByte[(k - 1)] & 0xFF) << 2;
      i = (m & 0xFF) << 10 | i;
      this.buf[(n - 5)] = arrayOfChar1[(i >> 12)];
      this.buf[(n - 4)] = arrayOfChar1[(i >>> 6 & 0x3F)];
      paramArrayOfByte = this.buf;
      if (j != 2)
        break label667;
    }
    label661: label667: for (char c = arrayOfChar1[(i & 0x3F)]; ; c = '=')
    {
      paramArrayOfByte[(n - 3)] = c;
      this.buf[(n - 2)] = '=';
      this.buf[(n - 1)] = '"';
      return;
      i = 0;
      break;
    }
  }

  public void writeCharacterAndChar(char paramChar1, char paramChar2)
  {
    writeString(Character.toString(paramChar1));
    write(paramChar2);
  }

  public void writeDoubleAndChar(double paramDouble, char paramChar)
  {
    String str2 = Double.toString(paramDouble);
    String str1 = str2;
    if (str2.endsWith(".0"))
      str1 = str2.substring(0, str2.length() - 2);
    write(str1);
    write(paramChar);
  }

  public void writeEnum(Enum<?> paramEnum, char paramChar)
  {
    if (paramEnum == null)
    {
      writeNull();
      write(',');
      return;
    }
    if (isEnabled(SerializerFeature.WriteEnumUsingToString))
    {
      if (isEnabled(SerializerFeature.UseSingleQuotes))
      {
        write('\'');
        write(paramEnum.name());
        write('\'');
        write(paramChar);
        return;
      }
      write('"');
      write(paramEnum.name());
      write('"');
      write(paramChar);
      return;
    }
    writeIntAndChar(paramEnum.ordinal(), paramChar);
  }

  public void writeFieldEmptyList(char paramChar, String paramString)
  {
    write(paramChar);
    writeFieldName(paramString);
    write("[]");
  }

  public void writeFieldName(String paramString)
  {
    writeFieldName(paramString, false);
  }

  public void writeFieldName(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
    {
      write("null:");
      return;
    }
    if (isEnabled(SerializerFeature.UseSingleQuotes))
    {
      if (isEnabled(SerializerFeature.QuoteFieldNames))
      {
        writeStringWithSingleQuote(paramString);
        write(':');
        return;
      }
      writeKeyWithSingleQuoteIfHasSpecial(paramString);
      return;
    }
    if (isEnabled(SerializerFeature.QuoteFieldNames))
    {
      writeStringWithDoubleQuote(paramString, ':', paramBoolean);
      return;
    }
    writeKeyWithDoubleQuoteIfHasSpecial(paramString);
  }

  public void writeFieldNull(char paramChar, String paramString)
  {
    write(paramChar);
    writeFieldName(paramString);
    writeNull();
  }

  public void writeFieldNullBoolean(char paramChar, String paramString)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (isEnabled(SerializerFeature.WriteNullBooleanAsFalse))
    {
      write("false");
      return;
    }
    writeNull();
  }

  public void writeFieldNullList(char paramChar, String paramString)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (isEnabled(SerializerFeature.WriteNullListAsEmpty))
    {
      write("[]");
      return;
    }
    writeNull();
  }

  public void writeFieldNullNumber(char paramChar, String paramString)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (isEnabled(SerializerFeature.WriteNullNumberAsZero))
    {
      write('0');
      return;
    }
    writeNull();
  }

  public void writeFieldNullString(char paramChar, String paramString)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (isEnabled(SerializerFeature.WriteNullStringAsEmpty))
    {
      writeString("");
      return;
    }
    writeNull();
  }

  public void writeFieldValue(char paramChar1, String paramString, char paramChar2)
  {
    write(paramChar1);
    writeFieldName(paramString);
    if (paramChar2 == 0)
    {
      writeString("");
      return;
    }
    writeString(Character.toString(paramChar2));
  }

  public void writeFieldValue(char paramChar, String paramString, double paramDouble)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (paramDouble == 0.0D)
    {
      write('0');
      return;
    }
    if (Double.isNaN(paramDouble))
    {
      writeNull();
      return;
    }
    if (Double.isInfinite(paramDouble))
    {
      writeNull();
      return;
    }
    String str = Double.toString(paramDouble);
    paramString = str;
    if (str.endsWith(".0"))
      paramString = str.substring(0, str.length() - 2);
    write(paramString);
  }

  public void writeFieldValue(char paramChar, String paramString, float paramFloat)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (paramFloat == 0.0F)
    {
      write('0');
      return;
    }
    if (Float.isNaN(paramFloat))
    {
      writeNull();
      return;
    }
    if (Float.isInfinite(paramFloat))
    {
      writeNull();
      return;
    }
    String str = Float.toString(paramFloat);
    paramString = str;
    if (str.endsWith(".0"))
      paramString = str.substring(0, str.length() - 2);
    write(paramString);
  }

  public void writeFieldValue(char paramChar, String paramString, int paramInt)
  {
    if ((paramInt == -2147483648) || (!isEnabled(SerializerFeature.QuoteFieldNames)))
    {
      writeFieldValue1(paramChar, paramString, paramInt);
      return;
    }
    int i;
    if (isEnabled(SerializerFeature.UseSingleQuotes))
    {
      i = 39;
      if (paramInt >= 0)
        break label104;
    }
    int k;
    label104: for (int j = IOUtils.stringSize(-paramInt) + 1; ; j = IOUtils.stringSize(paramInt))
    {
      k = paramString.length();
      m = this.count + k + 4 + j;
      if (m <= this.buf.length)
        break label119;
      if (this.writer == null)
        break label113;
      writeFieldValue1(paramChar, paramString, paramInt);
      return;
      i = 34;
      break;
    }
    label113: expandCapacity(m);
    label119: j = this.count;
    this.count = m;
    this.buf[j] = paramChar;
    int m = j + k + 1;
    this.buf[(j + 1)] = i;
    paramString.getChars(0, k, this.buf, j + 2);
    this.buf[(m + 1)] = i;
    this.buf[(m + 2)] = ':';
    IOUtils.getChars(paramInt, this.count, this.buf);
  }

  public void writeFieldValue(char paramChar, String paramString, long paramLong)
  {
    if ((paramLong == -9223372036854775808L) || (!isEnabled(SerializerFeature.QuoteFieldNames)))
    {
      writeFieldValue1(paramChar, paramString, paramLong);
      return;
    }
    int i;
    if (isEnabled(SerializerFeature.UseSingleQuotes))
    {
      i = 39;
      if (paramLong >= 0L)
        break label115;
    }
    int k;
    label115: for (int j = IOUtils.stringSize(-paramLong) + 1; ; j = IOUtils.stringSize(paramLong))
    {
      k = paramString.length();
      m = this.count + k + 4 + j;
      if (m <= this.buf.length)
        break label130;
      if (this.writer == null)
        break label124;
      write(paramChar);
      writeFieldName(paramString);
      writeLong(paramLong);
      return;
      i = 34;
      break;
    }
    label124: expandCapacity(m);
    label130: j = this.count;
    this.count = m;
    this.buf[j] = paramChar;
    int m = j + k + 1;
    this.buf[(j + 1)] = i;
    paramString.getChars(0, k, this.buf, j + 2);
    this.buf[(m + 1)] = i;
    this.buf[(m + 2)] = ':';
    IOUtils.getChars(paramLong, this.count, this.buf);
  }

  public void writeFieldValue(char paramChar, String paramString, Enum<?> paramEnum)
  {
    if (paramEnum == null)
    {
      write(paramChar);
      writeFieldName(paramString);
      writeNull();
      return;
    }
    if (isEnabled(SerializerFeature.WriteEnumUsingToString))
    {
      if (isEnabled(SerializerFeature.UseSingleQuotes))
      {
        writeFieldValue(paramChar, paramString, paramEnum.name());
        return;
      }
      writeFieldValueStringWithDoubleQuote(paramChar, paramString, paramEnum.name(), false);
      return;
    }
    writeFieldValue(paramChar, paramString, paramEnum.ordinal());
  }

  public void writeFieldValue(char paramChar, String paramString1, String paramString2)
  {
    if (isEnabled(SerializerFeature.QuoteFieldNames))
    {
      if (isEnabled(SerializerFeature.UseSingleQuotes))
      {
        write(paramChar);
        writeFieldName(paramString1);
        if (paramString2 == null)
        {
          writeNull();
          return;
        }
        writeString(paramString2);
        return;
      }
      if (isEnabled(SerializerFeature.BrowserCompatible))
      {
        write(paramChar);
        writeStringWithDoubleQuote(paramString1, ':');
        writeStringWithDoubleQuote(paramString2, '\000');
        return;
      }
      writeFieldValueStringWithDoubleQuote(paramChar, paramString1, paramString2, true);
      return;
    }
    write(paramChar);
    writeFieldName(paramString1);
    if (paramString2 == null)
    {
      writeNull();
      return;
    }
    writeString(paramString2);
  }

  public void writeFieldValue(char paramChar, String paramString, BigDecimal paramBigDecimal)
  {
    write(paramChar);
    writeFieldName(paramString);
    if (paramBigDecimal == null)
    {
      writeNull();
      return;
    }
    write(paramBigDecimal.toString());
  }

  public void writeFieldValue(char paramChar, String paramString, boolean paramBoolean)
  {
    int i;
    if (isEnabled(SerializerFeature.UseSingleQuotes))
    {
      i = 39;
      if (!paramBoolean)
        break label87;
    }
    int k;
    label87: for (int j = 4; ; j = 5)
    {
      k = paramString.length();
      m = this.count + k + 4 + j;
      if (m <= this.buf.length)
        break label99;
      if (this.writer == null)
        break label93;
      write(paramChar);
      writeString(paramString);
      write(':');
      write(paramBoolean);
      return;
      i = 34;
      break;
    }
    label93: expandCapacity(m);
    label99: j = this.count;
    this.count = m;
    this.buf[j] = paramChar;
    int m = j + k + 1;
    this.buf[(j + 1)] = i;
    paramString.getChars(0, k, this.buf, j + 2);
    this.buf[(m + 1)] = i;
    if (paramBoolean)
    {
      System.arraycopy(":true".toCharArray(), 0, this.buf, m + 2, 5);
      return;
    }
    System.arraycopy(":false".toCharArray(), 0, this.buf, m + 2, 6);
  }

  public void writeFieldValue1(char paramChar, String paramString, int paramInt)
  {
    write(paramChar);
    writeFieldName(paramString);
    writeInt(paramInt);
  }

  public void writeFieldValue1(char paramChar, String paramString, long paramLong)
  {
    write(paramChar);
    writeFieldName(paramString);
    writeLong(paramLong);
  }

  public void writeFloatAndChar(float paramFloat, char paramChar)
  {
    String str2 = Float.toString(paramFloat);
    String str1 = str2;
    if (str2.endsWith(".0"))
      str1 = str2.substring(0, str2.length() - 2);
    write(str1);
    write(paramChar);
  }

  public void writeInt(int paramInt)
  {
    if (paramInt == -2147483648)
    {
      write("-2147483648");
      return;
    }
    if (paramInt < 0);
    for (int i = IOUtils.stringSize(-paramInt) + 1; ; i = IOUtils.stringSize(paramInt))
    {
      int j = this.count + i;
      if (j > this.buf.length)
      {
        if (this.writer != null)
          break;
        expandCapacity(j);
      }
      IOUtils.getChars(paramInt, j, this.buf);
      this.count = j;
      return;
    }
    char[] arrayOfChar = new char[i];
    IOUtils.getChars(paramInt, i, arrayOfChar);
    write(arrayOfChar, 0, arrayOfChar.length);
  }

  public void writeIntAndChar(int paramInt, char paramChar)
  {
    if (paramInt == -2147483648)
    {
      write("-2147483648");
      write(paramChar);
      return;
    }
    if (paramInt < 0);
    int j;
    for (int i = IOUtils.stringSize(-paramInt) + 1; ; i = IOUtils.stringSize(paramInt))
    {
      i = this.count + i;
      j = i + 1;
      if (j <= this.buf.length)
        break label86;
      if (this.writer == null)
        break;
      writeInt(paramInt);
      write(paramChar);
      return;
    }
    expandCapacity(j);
    label86: IOUtils.getChars(paramInt, i, this.buf);
    this.buf[i] = paramChar;
    this.count = j;
  }

  public void writeLong(long paramLong)
  {
    if (paramLong == -9223372036854775808L)
    {
      write("-9223372036854775808");
      return;
    }
    if (paramLong < 0L);
    for (int i = IOUtils.stringSize(-paramLong) + 1; ; i = IOUtils.stringSize(paramLong))
    {
      int j = this.count + i;
      if (j > this.buf.length)
      {
        if (this.writer != null)
          break;
        expandCapacity(j);
      }
      IOUtils.getChars(paramLong, j, this.buf);
      this.count = j;
      return;
    }
    char[] arrayOfChar = new char[i];
    IOUtils.getChars(paramLong, i, arrayOfChar);
    write(arrayOfChar, 0, arrayOfChar.length);
  }

  public void writeLongAndChar(long paramLong, char paramChar)
    throws IOException
  {
    if (paramLong == -9223372036854775808L)
    {
      write("-9223372036854775808");
      write(paramChar);
      return;
    }
    if (paramLong < 0L);
    int j;
    for (int i = IOUtils.stringSize(-paramLong) + 1; ; i = IOUtils.stringSize(paramLong))
    {
      i = this.count + i;
      j = i + 1;
      if (j <= this.buf.length)
        break label94;
      if (this.writer == null)
        break;
      writeLong(paramLong);
      write(paramChar);
      return;
    }
    expandCapacity(j);
    label94: IOUtils.getChars(paramLong, i, this.buf);
    this.buf[i] = paramChar;
    this.count = j;
  }

  public void writeNull()
  {
    write("null");
  }

  public void writeString(String paramString)
  {
    if (isEnabled(SerializerFeature.UseSingleQuotes))
    {
      writeStringWithSingleQuote(paramString);
      return;
    }
    writeStringWithDoubleQuote(paramString, '\000');
  }

  public void writeString(String paramString, char paramChar)
  {
    if (isEnabled(SerializerFeature.UseSingleQuotes))
    {
      writeStringWithSingleQuote(paramString);
      write(paramChar);
      return;
    }
    writeStringWithDoubleQuote(paramString, paramChar);
  }

  public void writeTo(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    writeTo(paramOutputStream, Charset.forName(paramString));
  }

  public void writeTo(OutputStream paramOutputStream, Charset paramCharset)
    throws IOException
  {
    if (this.writer != null)
      throw new UnsupportedOperationException("writer not null");
    paramOutputStream.write(new String(this.buf, 0, this.count).getBytes(paramCharset));
  }

  public void writeTo(Writer paramWriter)
    throws IOException
  {
    if (this.writer != null)
      throw new UnsupportedOperationException("writer not null");
    paramWriter.write(this.buf, 0, this.count);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.SerializeWriter
 * JD-Core Version:    0.6.2
 */