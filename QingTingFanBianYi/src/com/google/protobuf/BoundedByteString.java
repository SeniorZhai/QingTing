package com.google.protobuf;

import java.util.NoSuchElementException;

class BoundedByteString extends LiteralByteString
{
  private final int bytesLength;
  private final int bytesOffset;

  BoundedByteString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramArrayOfByte);
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Offset too small: " + paramInt1);
    if (paramInt2 < 0)
      throw new IllegalArgumentException("Length too small: " + paramInt1);
    if (paramInt1 + paramInt2 > paramArrayOfByte.length)
      throw new IllegalArgumentException("Offset+Length too large: " + paramInt1 + "+" + paramInt2);
    this.bytesOffset = paramInt1;
    this.bytesLength = paramInt2;
  }

  public byte byteAt(int paramInt)
  {
    if (paramInt < 0)
      throw new ArrayIndexOutOfBoundsException("Index too small: " + paramInt);
    if (paramInt >= size())
      throw new ArrayIndexOutOfBoundsException("Index too large: " + paramInt + ", " + size());
    return this.bytes[(this.bytesOffset + paramInt)];
  }

  protected void copyToInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.bytes, getOffsetIntoBytes() + paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }

  protected int getOffsetIntoBytes()
  {
    return this.bytesOffset;
  }

  public ByteString.ByteIterator iterator()
  {
    return new BoundedByteIterator(null);
  }

  public int size()
  {
    return this.bytesLength;
  }

  private class BoundedByteIterator
    implements ByteString.ByteIterator
  {
    private final int limit = this.position + BoundedByteString.this.size();
    private int position = BoundedByteString.this.getOffsetIntoBytes();

    private BoundedByteIterator()
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
      if (this.position >= this.limit)
        throw new NoSuchElementException();
      byte[] arrayOfByte = BoundedByteString.this.bytes;
      int i = this.position;
      this.position = (i + 1);
      return arrayOfByte[i];
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.BoundedByteString
 * JD-Core Version:    0.6.2
 */