package com.alibaba.fastjson.asm;

public class Label
{
  static final int RESOLVED = 2;
  public Object info;
  int inputStackTop;
  int line;
  Label next;
  int outputStackMax;
  int position;
  private int referenceCount;
  private int[] srcAndRefPositions;
  int status;
  Label successor;

  private void addReference(int paramInt1, int paramInt2)
  {
    if (this.srcAndRefPositions == null)
      this.srcAndRefPositions = new int[6];
    if (this.referenceCount >= this.srcAndRefPositions.length)
    {
      arrayOfInt = new int[this.srcAndRefPositions.length + 6];
      System.arraycopy(this.srcAndRefPositions, 0, arrayOfInt, 0, this.srcAndRefPositions.length);
      this.srcAndRefPositions = arrayOfInt;
    }
    int[] arrayOfInt = this.srcAndRefPositions;
    int i = this.referenceCount;
    this.referenceCount = (i + 1);
    arrayOfInt[i] = paramInt1;
    arrayOfInt = this.srcAndRefPositions;
    paramInt1 = this.referenceCount;
    this.referenceCount = (paramInt1 + 1);
    arrayOfInt[paramInt1] = paramInt2;
  }

  void put(MethodWriter paramMethodWriter, ByteVector paramByteVector, int paramInt)
  {
    if ((this.status & 0x2) == 0)
    {
      addReference(paramInt, paramByteVector.length);
      paramByteVector.putShort(-1);
      return;
    }
    paramByteVector.putShort(this.position - paramInt);
  }

  void resolve(MethodWriter paramMethodWriter, int paramInt, byte[] paramArrayOfByte)
  {
    this.status |= 2;
    this.position = paramInt;
    int i = 0;
    while (i < this.referenceCount)
    {
      paramMethodWriter = this.srcAndRefPositions;
      int j = i + 1;
      int k = paramMethodWriter[i];
      paramMethodWriter = this.srcAndRefPositions;
      i = j + 1;
      j = paramMethodWriter[j];
      k = paramInt - k;
      paramArrayOfByte[j] = ((byte)(k >>> 8));
      paramArrayOfByte[(j + 1)] = ((byte)k);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.asm.Label
 * JD-Core Version:    0.6.2
 */