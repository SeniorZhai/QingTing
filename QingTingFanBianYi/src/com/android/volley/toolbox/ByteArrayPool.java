package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ByteArrayPool
{
  protected static final Comparator<byte[]> BUF_COMPARATOR = new Comparator()
  {
    public int compare(byte[] paramAnonymousArrayOfByte1, byte[] paramAnonymousArrayOfByte2)
    {
      return paramAnonymousArrayOfByte1.length - paramAnonymousArrayOfByte2.length;
    }
  };
  private List<byte[]> mBuffersByLastUse = new LinkedList();
  private List<byte[]> mBuffersBySize = new ArrayList(64);
  private int mCurrentSize = 0;
  private final int mSizeLimit;

  public ByteArrayPool(int paramInt)
  {
    this.mSizeLimit = paramInt;
  }

  private void trim()
  {
    try
    {
      while (true)
      {
        int i = this.mCurrentSize;
        int j = this.mSizeLimit;
        if (i <= j)
          return;
        byte[] arrayOfByte = (byte[])this.mBuffersByLastUse.remove(0);
        this.mBuffersBySize.remove(arrayOfByte);
        this.mCurrentSize -= arrayOfByte.length;
      }
    }
    finally
    {
    }
  }

  public byte[] getBuf(int paramInt)
  {
    int i = 0;
    while (true)
    {
      try
      {
        if (i >= this.mBuffersBySize.size())
        {
          arrayOfByte = new byte[paramInt];
          return arrayOfByte;
        }
        byte[] arrayOfByte = (byte[])this.mBuffersBySize.get(i);
        if (arrayOfByte.length >= paramInt)
        {
          this.mCurrentSize -= arrayOfByte.length;
          this.mBuffersBySize.remove(i);
          this.mBuffersByLastUse.remove(arrayOfByte);
          continue;
        }
      }
      finally
      {
      }
      i += 1;
    }
  }

  public void returnBuf(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null);
    try
    {
      int i = paramArrayOfByte.length;
      int j = this.mSizeLimit;
      if (i > j);
      while (true)
      {
        return;
        this.mBuffersByLastUse.add(paramArrayOfByte);
        j = Collections.binarySearch(this.mBuffersBySize, paramArrayOfByte, BUF_COMPARATOR);
        i = j;
        if (j < 0)
          i = -j - 1;
        this.mBuffersBySize.add(i, paramArrayOfByte);
        this.mCurrentSize += paramArrayOfByte.length;
        trim();
      }
    }
    finally
    {
    }
    throw paramArrayOfByte;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.ByteArrayPool
 * JD-Core Version:    0.6.2
 */