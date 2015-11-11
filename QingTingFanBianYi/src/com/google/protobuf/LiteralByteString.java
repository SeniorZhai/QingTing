package com.google.protobuf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class LiteralByteString extends ByteString
{
  protected final byte[] bytes;
  private int hash = 0;

  LiteralByteString(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
  }

  public ByteBuffer asReadOnlyByteBuffer()
  {
    return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
  }

  public List<ByteBuffer> asReadOnlyByteBufferList()
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(asReadOnlyByteBuffer());
    return localArrayList;
  }

  public byte byteAt(int paramInt)
  {
    return this.bytes[paramInt];
  }

  public void copyTo(ByteBuffer paramByteBuffer)
  {
    paramByteBuffer.put(this.bytes, getOffsetIntoBytes(), size());
  }

  protected void copyToInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.bytes, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof ByteString))
      return false;
    if (size() != ((ByteString)paramObject).size())
      return false;
    if (size() == 0)
      return true;
    if ((paramObject instanceof LiteralByteString))
      return equalsRange((LiteralByteString)paramObject, 0, size());
    if ((paramObject instanceof RopeByteString))
      return paramObject.equals(this);
    throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + paramObject.getClass());
  }

  boolean equalsRange(LiteralByteString paramLiteralByteString, int paramInt1, int paramInt2)
  {
    if (paramInt2 > paramLiteralByteString.size())
      throw new IllegalArgumentException("Length too large: " + paramInt2 + size());
    if (paramInt1 + paramInt2 > paramLiteralByteString.size())
      throw new IllegalArgumentException("Ran off end of other: " + paramInt1 + ", " + paramInt2 + ", " + paramLiteralByteString.size());
    byte[] arrayOfByte1 = this.bytes;
    byte[] arrayOfByte2 = paramLiteralByteString.bytes;
    int j = getOffsetIntoBytes();
    int i = getOffsetIntoBytes();
    paramInt1 = paramLiteralByteString.getOffsetIntoBytes() + paramInt1;
    while (i < j + paramInt2)
    {
      if (arrayOfByte1[i] != arrayOfByte2[paramInt1])
        return false;
      i += 1;
      paramInt1 += 1;
    }
    return true;
  }

  protected int getOffsetIntoBytes()
  {
    return 0;
  }

  protected int getTreeDepth()
  {
    return 0;
  }

  public int hashCode()
  {
    int j = this.hash;
    int i = j;
    if (j == 0)
    {
      i = size();
      j = partialHash(i, 0, i);
      i = j;
      if (j == 0)
        i = 1;
      this.hash = i;
    }
    return i;
  }

  protected boolean isBalanced()
  {
    return true;
  }

  public boolean isValidUtf8()
  {
    int i = getOffsetIntoBytes();
    return Utf8.isValidUtf8(this.bytes, i, size() + i);
  }

  public ByteString.ByteIterator iterator()
  {
    return new LiteralByteIterator(null);
  }

  public CodedInputStream newCodedInput()
  {
    return CodedInputStream.newInstance(this.bytes, getOffsetIntoBytes(), size());
  }

  public InputStream newInput()
  {
    return new ByteArrayInputStream(this.bytes, getOffsetIntoBytes(), size());
  }

  protected int partialHash(int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = this.bytes;
    int i = getOffsetIntoBytes() + paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = i;
    while (paramInt1 < i + paramInt3)
    {
      paramInt2 = paramInt2 * 31 + arrayOfByte[paramInt1];
      paramInt1 += 1;
    }
    return paramInt2;
  }

  protected int partialIsValidUtf8(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt2 = getOffsetIntoBytes() + paramInt2;
    return Utf8.partialIsValidUtf8(paramInt1, this.bytes, paramInt2, paramInt2 + paramInt3);
  }

  protected int peekCachedHashCode()
  {
    return this.hash;
  }

  public int size()
  {
    return this.bytes.length;
  }

  public ByteString substring(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException("Beginning index: " + paramInt1 + " < 0");
    if (paramInt2 > size())
      throw new IndexOutOfBoundsException("End index: " + paramInt2 + " > " + size());
    int i = paramInt2 - paramInt1;
    if (i < 0)
      throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + paramInt1 + ", " + paramInt2);
    if (i == 0)
      return ByteString.EMPTY;
    return new BoundedByteString(this.bytes, getOffsetIntoBytes() + paramInt1, i);
  }

  public String toString(String paramString)
    throws UnsupportedEncodingException
  {
    return new String(this.bytes, getOffsetIntoBytes(), size(), paramString);
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(toByteArray());
  }

  private class LiteralByteIterator
    implements ByteString.ByteIterator
  {
    private final int limit = LiteralByteString.this.size();
    private int position = 0;

    private LiteralByteIterator()
    {
    }

    public boolean hasNext()
    {
      return this.position < this.limit;
    }

    public Byte next()
    {
      return Byte.valueOf(nextByte());
    }

    public byte nextByte()
    {
      try
      {
        byte[] arrayOfByte = LiteralByteString.this.bytes;
        int i = this.position;
        this.position = (i + 1);
        byte b = arrayOfByte[i];
        return b;
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        throw new NoSuchElementException(localArrayIndexOutOfBoundsException.getMessage());
      }
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.LiteralByteString
 * JD-Core Version:    0.6.2
 */