package com.taobao.munion.base.utdid.b.a;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import org.xmlpull.v1.XmlSerializer;

class a
  implements XmlSerializer
{
  private static final String[] a = { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&quot;", null, null, null, "&amp;", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&lt;", null, "&gt;", null };
  private static final int b = 8192;
  private final char[] c = new char[8192];
  private int d;
  private Writer e;
  private OutputStream f;
  private CharsetEncoder g;
  private ByteBuffer h = ByteBuffer.allocate(8192);
  private boolean i;

  private void a()
    throws IOException
  {
    int j = this.h.position();
    if (j > 0)
    {
      this.h.flip();
      this.f.write(this.h.array(), 0, j);
      this.h.clear();
    }
  }

  private void a(char paramChar)
    throws IOException
  {
    int k = this.d;
    int j = k;
    if (k >= 8191)
    {
      flush();
      j = this.d;
    }
    this.c[j] = paramChar;
    this.d = (j + 1);
  }

  private void a(String paramString)
    throws IOException
  {
    a(paramString, 0, paramString.length());
  }

  private void a(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int k;
    int j;
    if (paramInt2 > 8192)
    {
      k = paramInt1 + paramInt2;
      if (paramInt1 < k)
      {
        j = paramInt1 + 8192;
        if (j < k);
        for (paramInt2 = 8192; ; paramInt2 = k - paramInt1)
        {
          a(paramString, paramInt1, paramInt2);
          paramInt1 = j;
          break;
        }
      }
    }
    else
    {
      k = this.d;
      j = k;
      if (k + paramInt2 > 8192)
      {
        flush();
        j = this.d;
      }
      paramString.getChars(paramInt1, paramInt1 + paramInt2, this.c, j);
      this.d = (j + paramInt2);
    }
  }

  private void a(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int k;
    int j;
    if (paramInt2 > 8192)
    {
      k = paramInt1 + paramInt2;
      if (paramInt1 < k)
      {
        j = paramInt1 + 8192;
        if (j < k);
        for (paramInt2 = 8192; ; paramInt2 = k - paramInt1)
        {
          a(paramArrayOfChar, paramInt1, paramInt2);
          paramInt1 = j;
          break;
        }
      }
    }
    else
    {
      k = this.d;
      j = k;
      if (k + paramInt2 > 8192)
      {
        flush();
        j = this.d;
      }
      System.arraycopy(paramArrayOfChar, paramInt1, this.c, j, paramInt2);
      this.d = (j + paramInt2);
    }
  }

  private void b(String paramString)
    throws IOException
  {
    int k = 0;
    int n = paramString.length();
    int i1 = (char)a.length;
    String[] arrayOfString = a;
    int j = 0;
    if (j < n)
    {
      int m = paramString.charAt(j);
      if (m >= i1)
        m = k;
      while (true)
      {
        j += 1;
        k = m;
        break;
        String str = arrayOfString[m];
        m = k;
        if (str != null)
        {
          if (k < j)
            a(paramString, k, j - k);
          m = j + 1;
          a(str);
        }
      }
    }
    if (k < j)
      a(paramString, k, j - k);
  }

  private void b(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int n = (char)a.length;
    String[] arrayOfString = a;
    int k = paramInt1;
    int j = paramInt1;
    int m = j;
    if (m < paramInt1 + paramInt2)
    {
      j = paramArrayOfChar[m];
      if (j >= n)
        j = k;
      while (true)
      {
        m += 1;
        k = j;
        j = m;
        break;
        String str = arrayOfString[j];
        j = k;
        if (str != null)
        {
          if (k < m)
            a(paramArrayOfChar, k, m - k);
          j = m + 1;
          a(str);
        }
      }
    }
    if (k < m)
      a(paramArrayOfChar, k, m - k);
  }

  public XmlSerializer attribute(String paramString1, String paramString2, String paramString3)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    a(' ');
    if (paramString1 != null)
    {
      a(paramString1);
      a(':');
    }
    a(paramString2);
    a("=\"");
    b(paramString3);
    a('"');
    return this;
  }

  public void cdsect(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void comment(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void docdecl(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void endDocument()
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    flush();
  }

  public XmlSerializer endTag(String paramString1, String paramString2)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.i)
      a(" />\n");
    while (true)
    {
      this.i = false;
      return this;
      a("</");
      if (paramString1 != null)
      {
        a(paramString1);
        a(':');
      }
      a(paramString2);
      a(">\n");
    }
  }

  public void entityRef(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void flush()
    throws IOException
  {
    if (this.d > 0)
    {
      if (this.f == null)
        break label105;
      CharBuffer localCharBuffer = CharBuffer.wrap(this.c, 0, this.d);
      for (CoderResult localCoderResult = this.g.encode(localCharBuffer, this.h, true); ; localCoderResult = this.g.encode(localCharBuffer, this.h, true))
      {
        if (localCoderResult.isError())
          throw new IOException(localCoderResult.toString());
        if (!localCoderResult.isOverflow())
          break;
        a();
      }
      a();
      this.f.flush();
    }
    while (true)
    {
      this.d = 0;
      return;
      label105: this.e.write(this.c, 0, this.d);
      this.e.flush();
    }
  }

  public int getDepth()
  {
    throw new UnsupportedOperationException();
  }

  public boolean getFeature(String paramString)
  {
    throw new UnsupportedOperationException();
  }

  public String getName()
  {
    throw new UnsupportedOperationException();
  }

  public String getNamespace()
  {
    throw new UnsupportedOperationException();
  }

  public String getPrefix(String paramString, boolean paramBoolean)
    throws IllegalArgumentException
  {
    throw new UnsupportedOperationException();
  }

  public Object getProperty(String paramString)
  {
    throw new UnsupportedOperationException();
  }

  public void ignorableWhitespace(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void processingInstruction(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void setFeature(String paramString, boolean paramBoolean)
    throws IllegalArgumentException, IllegalStateException
  {
    if (paramString.equals("http://xmlpull.org/v1/doc/features.html#indent-output"))
      return;
    throw new UnsupportedOperationException();
  }

  public void setOutput(OutputStream paramOutputStream, String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException();
    try
    {
      this.g = Charset.forName(paramString).newEncoder();
      this.f = paramOutputStream;
      return;
    }
    catch (IllegalCharsetNameException paramOutputStream)
    {
      throw ((UnsupportedEncodingException)new UnsupportedEncodingException(paramString).initCause(paramOutputStream));
    }
    catch (UnsupportedCharsetException paramOutputStream)
    {
    }
    throw ((UnsupportedEncodingException)new UnsupportedEncodingException(paramString).initCause(paramOutputStream));
  }

  public void setOutput(Writer paramWriter)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    this.e = paramWriter;
  }

  public void setPrefix(String paramString1, String paramString2)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void setProperty(String paramString, Object paramObject)
    throws IllegalArgumentException, IllegalStateException
  {
    throw new UnsupportedOperationException();
  }

  public void startDocument(String paramString, Boolean paramBoolean)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    StringBuilder localStringBuilder = new StringBuilder().append("<?xml version='1.0' encoding='utf-8' standalone='");
    if (paramBoolean.booleanValue());
    for (paramString = "yes"; ; paramString = "no")
    {
      a(paramString + "' ?>\n");
      return;
    }
  }

  public XmlSerializer startTag(String paramString1, String paramString2)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.i)
      a(">\n");
    a('<');
    if (paramString1 != null)
    {
      a(paramString1);
      a(':');
    }
    a(paramString2);
    this.i = true;
    return this;
  }

  public XmlSerializer text(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.i)
    {
      a(">");
      this.i = false;
    }
    b(paramString);
    return this;
  }

  public XmlSerializer text(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.i)
    {
      a(">");
      this.i = false;
    }
    b(paramArrayOfChar, paramInt1, paramInt2);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.utdid.b.a.a
 * JD-Core Version:    0.6.2
 */