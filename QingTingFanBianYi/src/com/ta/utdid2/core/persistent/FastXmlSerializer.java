package com.ta.utdid2.core.persistent;

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

class FastXmlSerializer
  implements XmlSerializer
{
  private static final int BUFFER_LEN = 8192;
  private static final String[] ESCAPE_TABLE = { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&quot;", null, null, null, "&amp;", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&lt;", null, "&gt;", null };
  private ByteBuffer mBytes = ByteBuffer.allocate(8192);
  private CharsetEncoder mCharset;
  private boolean mInTag;
  private OutputStream mOutputStream;
  private int mPos;
  private final char[] mText = new char[8192];
  private Writer mWriter;

  private void append(char paramChar)
    throws IOException
  {
    int j = this.mPos;
    int i = j;
    if (j >= 8191)
    {
      flush();
      i = this.mPos;
    }
    this.mText[i] = paramChar;
    this.mPos = (i + 1);
  }

  private void append(String paramString)
    throws IOException
  {
    append(paramString, 0, paramString.length());
  }

  private void append(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int j;
    int i;
    if (paramInt2 > 8192)
    {
      j = paramInt1 + paramInt2;
      if (paramInt1 < j)
      {
        i = paramInt1 + 8192;
        if (i < j);
        for (paramInt2 = 8192; ; paramInt2 = j - paramInt1)
        {
          append(paramString, paramInt1, paramInt2);
          paramInt1 = i;
          break;
        }
      }
    }
    else
    {
      j = this.mPos;
      i = j;
      if (j + paramInt2 > 8192)
      {
        flush();
        i = this.mPos;
      }
      paramString.getChars(paramInt1, paramInt1 + paramInt2, this.mText, i);
      this.mPos = (i + paramInt2);
    }
  }

  private void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int j;
    int i;
    if (paramInt2 > 8192)
    {
      j = paramInt1 + paramInt2;
      if (paramInt1 < j)
      {
        i = paramInt1 + 8192;
        if (i < j);
        for (paramInt2 = 8192; ; paramInt2 = j - paramInt1)
        {
          append(paramArrayOfChar, paramInt1, paramInt2);
          paramInt1 = i;
          break;
        }
      }
    }
    else
    {
      j = this.mPos;
      i = j;
      if (j + paramInt2 > 8192)
      {
        flush();
        i = this.mPos;
      }
      System.arraycopy(paramArrayOfChar, paramInt1, this.mText, i, paramInt2);
      this.mPos = (i + paramInt2);
    }
  }

  private void escapeAndAppendString(String paramString)
    throws IOException
  {
    int m = paramString.length();
    int n = (char)ESCAPE_TABLE.length;
    String[] arrayOfString = ESCAPE_TABLE;
    int j = 0;
    int i = 0;
    if (i < m)
    {
      int k = paramString.charAt(i);
      if (k >= n)
        k = j;
      while (true)
      {
        i += 1;
        j = k;
        break;
        String str = arrayOfString[k];
        k = j;
        if (str != null)
        {
          if (j < i)
            append(paramString, j, i - j);
          k = i + 1;
          append(str);
        }
      }
    }
    if (j < i)
      append(paramString, j, i - j);
  }

  private void escapeAndAppendString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int m = (char)ESCAPE_TABLE.length;
    String[] arrayOfString = ESCAPE_TABLE;
    int j = paramInt1;
    int i = paramInt1;
    if (i < paramInt1 + paramInt2)
    {
      int k = paramArrayOfChar[i];
      if (k >= m)
        k = j;
      while (true)
      {
        i += 1;
        j = k;
        break;
        String str = arrayOfString[k];
        k = j;
        if (str != null)
        {
          if (j < i)
            append(paramArrayOfChar, j, i - j);
          k = i + 1;
          append(str);
        }
      }
    }
    if (j < i)
      append(paramArrayOfChar, j, i - j);
  }

  private void flushBytes()
    throws IOException
  {
    int i = this.mBytes.position();
    if (i > 0)
    {
      this.mBytes.flip();
      this.mOutputStream.write(this.mBytes.array(), 0, i);
      this.mBytes.clear();
    }
  }

  public XmlSerializer attribute(String paramString1, String paramString2, String paramString3)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    append(' ');
    if (paramString1 != null)
    {
      append(paramString1);
      append(':');
    }
    append(paramString2);
    append("=\"");
    escapeAndAppendString(paramString3);
    append('"');
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
    if (this.mInTag)
      append(" />\n");
    while (true)
    {
      this.mInTag = false;
      return this;
      append("</");
      if (paramString1 != null)
      {
        append(paramString1);
        append(':');
      }
      append(paramString2);
      append(">\n");
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
    if (this.mPos > 0)
    {
      if (this.mOutputStream == null)
        break label105;
      CharBuffer localCharBuffer = CharBuffer.wrap(this.mText, 0, this.mPos);
      for (CoderResult localCoderResult = this.mCharset.encode(localCharBuffer, this.mBytes, true); ; localCoderResult = this.mCharset.encode(localCharBuffer, this.mBytes, true))
      {
        if (localCoderResult.isError())
          throw new IOException(localCoderResult.toString());
        if (!localCoderResult.isOverflow())
          break;
        flushBytes();
      }
      flushBytes();
      this.mOutputStream.flush();
    }
    while (true)
    {
      this.mPos = 0;
      return;
      label105: this.mWriter.write(this.mText, 0, this.mPos);
      this.mWriter.flush();
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
      this.mCharset = Charset.forName(paramString).newEncoder();
      this.mOutputStream = paramOutputStream;
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
    this.mWriter = paramWriter;
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
      append(paramString + "' ?>\n");
      return;
    }
  }

  public XmlSerializer startTag(String paramString1, String paramString2)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.mInTag)
      append(">\n");
    append('<');
    if (paramString1 != null)
    {
      append(paramString1);
      append(':');
    }
    append(paramString2);
    this.mInTag = true;
    return this;
  }

  public XmlSerializer text(String paramString)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.mInTag)
    {
      append(">");
      this.mInTag = false;
    }
    escapeAndAppendString(paramString);
    return this;
  }

  public XmlSerializer text(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, IllegalArgumentException, IllegalStateException
  {
    if (this.mInTag)
    {
      append(">");
      this.mInTag = false;
    }
    escapeAndAppendString(paramArrayOfChar, paramInt1, paramInt2);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.FastXmlSerializer
 * JD-Core Version:    0.6.2
 */