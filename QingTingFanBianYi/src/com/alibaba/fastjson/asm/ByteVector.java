package com.alibaba.fastjson.asm;

public class ByteVector
{
  byte[] data;
  int length;

  public ByteVector()
  {
    this.data = new byte[64];
  }

  public ByteVector(int paramInt)
  {
    this.data = new byte[paramInt];
  }

  private void enlarge(int paramInt)
  {
    int i = this.data.length * 2;
    paramInt = this.length + paramInt;
    if (i > paramInt)
      paramInt = i;
    while (true)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.data, 0, arrayOfByte, 0, this.length);
      this.data = arrayOfByte;
      return;
    }
  }

  ByteVector put11(int paramInt1, int paramInt2)
  {
    int i = this.length;
    if (i + 2 > this.data.length)
      enlarge(2);
    byte[] arrayOfByte = this.data;
    int j = i + 1;
    arrayOfByte[i] = ((byte)paramInt1);
    arrayOfByte[j] = ((byte)paramInt2);
    this.length = (j + 1);
    return this;
  }

  ByteVector put12(int paramInt1, int paramInt2)
  {
    int j = this.length;
    if (j + 3 > this.data.length)
      enlarge(3);
    byte[] arrayOfByte = this.data;
    int i = j + 1;
    arrayOfByte[j] = ((byte)paramInt1);
    paramInt1 = i + 1;
    arrayOfByte[i] = ((byte)(paramInt2 >>> 8));
    arrayOfByte[paramInt1] = ((byte)paramInt2);
    this.length = (paramInt1 + 1);
    return this;
  }

  public ByteVector putByte(int paramInt)
  {
    int i = this.length;
    if (i + 1 > this.data.length)
      enlarge(1);
    this.data[i] = ((byte)paramInt);
    this.length = (i + 1);
    return this;
  }

  public ByteVector putByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.length + paramInt2 > this.data.length)
      enlarge(paramInt2);
    if (paramArrayOfByte != null)
      System.arraycopy(paramArrayOfByte, paramInt1, this.data, this.length, paramInt2);
    this.length += paramInt2;
    return this;
  }

  public ByteVector putInt(int paramInt)
  {
    int j = this.length;
    if (j + 4 > this.data.length)
      enlarge(4);
    byte[] arrayOfByte = this.data;
    int i = j + 1;
    arrayOfByte[j] = ((byte)(paramInt >>> 24));
    j = i + 1;
    arrayOfByte[i] = ((byte)(paramInt >>> 16));
    i = j + 1;
    arrayOfByte[j] = ((byte)(paramInt >>> 8));
    arrayOfByte[i] = ((byte)paramInt);
    this.length = (i + 1);
    return this;
  }

  public ByteVector putShort(int paramInt)
  {
    int i = this.length;
    if (i + 2 > this.data.length)
      enlarge(2);
    byte[] arrayOfByte = this.data;
    int j = i + 1;
    arrayOfByte[i] = ((byte)(paramInt >>> 8));
    arrayOfByte[j] = ((byte)paramInt);
    this.length = (j + 1);
    return this;
  }

  public ByteVector putUTF8(String paramString)
  {
    int k = paramString.length();
    int j = this.length;
    if (j + 2 + k > this.data.length)
      enlarge(k + 2);
    byte[] arrayOfByte = this.data;
    int i = j + 1;
    arrayOfByte[j] = ((byte)(k >>> 8));
    arrayOfByte[i] = ((byte)k);
    j = 0;
    i += 1;
    while (j < k)
    {
      int m = paramString.charAt(j);
      if ((m >= 1) && (m <= 127))
      {
        arrayOfByte[i] = ((byte)m);
        j += 1;
        i += 1;
      }
      else
      {
        throw new UnsupportedOperationException();
      }
    }
    this.length = i;
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.asm.ByteVector
 * JD-Core Version:    0.6.2
 */