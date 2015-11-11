package com.talkingdata.pingan.sdk;

import android.content.Context;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CRC32;

public class n
{
  private static final long k = 1048576L;
  private static final String l = "td-cache";
  Context a;
  File b;
  RandomAccessFile c;
  String d;
  CRC32 e = new CRC32();
  Lock f = new ReentrantLock();
  long g = 0L;
  long h = 0L;
  long i = -1L;
  List j = new ArrayList(16);

  public n(Context paramContext, String paramString)
  {
    try
    {
      this.a = paramContext;
      this.d = paramString;
      this.b = paramContext.getDir("td-cache", 0);
      e();
      try
      {
        f();
        label85: if (this.c.length() > 1048576L)
          d();
        return;
      }
      catch (IOException paramContext)
      {
        break label85;
      }
    }
    catch (IOException paramContext)
    {
    }
    catch (FileNotFoundException paramContext)
    {
    }
  }

  private void a(long paramLong)
  {
    try
    {
      this.f.lock();
      this.c.seek(this.c.length());
      this.c.writeByte(46);
      this.c.writeInt((int)paramLong);
      this.c.writeByte(46);
      return;
    }
    finally
    {
      this.f.unlock();
    }
  }

  private byte[] a(long paramLong, boolean paramBoolean)
  {
    try
    {
      this.f.lock();
      try
      {
        this.c.seek(paramLong);
        int m = this.c.readByte();
        int n;
        if (m == 31)
        {
          m = this.c.readInt();
          n = this.c.readShort();
          if ((n >= 0) && (this.c.getFilePointer() + n <= this.c.length()))
          {
            byte[] arrayOfByte = new byte[n];
            this.c.readFully(arrayOfByte);
            if (this.c.readByte() == 31)
            {
              this.e.reset();
              this.e.update(arrayOfByte);
              if (m == (int)this.e.getValue())
              {
                this.h = this.c.getFilePointer();
                return arrayOfByte;
              }
            }
          }
        }
        else if (m == 46)
        {
          m = this.c.readInt();
          n = this.c.readByte();
          if ((m >= 0) && (m < this.c.length()) && (n == 46))
          {
            this.h = this.c.getFilePointer();
            if (paramBoolean)
              this.g = m;
            return null;
          }
        }
      }
      catch (Exception localException)
      {
        this.h = (1L + paramLong);
        return null;
      }
    }
    finally
    {
      this.f.unlock();
    }
  }

  private void b(byte[] paramArrayOfByte)
  {
    try
    {
      this.f.lock();
      this.c.seek(this.c.length());
      this.c.writeByte(31);
      this.e.reset();
      this.e.update(paramArrayOfByte);
      this.c.writeInt((int)this.e.getValue());
      this.c.writeShort(paramArrayOfByte.length);
      this.c.write(paramArrayOfByte);
      this.c.writeByte(31);
      return;
    }
    finally
    {
      this.f.unlock();
    }
    throw paramArrayOfByte;
  }

  private void d()
  {
    if (this.g < this.i);
    for (long l1 = this.i; ; l1 = this.g)
    {
      this.h = l1;
      File localFile = new File(this.b, this.d + ".tmp");
      localObject2 = new FileOutputStream(localFile);
      try
      {
        while (this.h < this.c.length())
        {
          byte[] arrayOfByte = a(this.h, false);
          if (arrayOfByte != null)
            ((FileOutputStream)localObject2).write(arrayOfByte);
        }
      }
      finally
      {
        ((FileOutputStream)localObject2).flush();
        ((FileOutputStream)localObject2).close();
        this.c.close();
      }
    }
    ((FileOutputStream)localObject2).flush();
    ((FileOutputStream)localObject2).close();
    this.c.close();
    Object localObject2 = new File(this.b, this.d);
    ((File)localObject2).delete();
    localObject1.renameTo((File)localObject2);
    e();
    this.g = 0L;
    this.h = 0L;
  }

  private void e()
  {
    this.c = new RandomAccessFile(new File(this.b, this.d), "rw");
  }

  private void f()
  {
    for (int m = 0; this.h < this.c.length(); m = 1)
    {
      label2: if ((this.i == -1L) && (this.c.length() - this.h < 1048576L))
        this.i = this.h;
      long l1 = this.h;
      if ((a(l1, true) == null) || (m != 0))
        break label2;
      if (this.g == 0L)
        this.g = l1;
    }
  }

  public List a(int paramInt)
  {
    this.j.clear();
    try
    {
      this.h = this.g;
      this.c.seek(this.h);
      int m;
      do
      {
        if (this.h >= this.c.length())
          break;
        byte[] arrayOfByte = a(this.h, false);
        if (arrayOfByte != null)
          this.j.add(arrayOfByte);
        m = this.j.size();
      }
      while (m < paramInt);
      label83: if (this.j.size() == 0)
        this.g = this.h;
      return this.j;
    }
    catch (IOException localIOException)
    {
      break label83;
    }
  }

  public void a()
  {
    a(this.h);
    this.g = this.h;
  }

  public void a(byte[] paramArrayOfByte)
  {
    b(paramArrayOfByte);
  }

  public void b()
  {
    this.c.getFD().sync();
  }

  public void c()
  {
    b();
    this.c.close();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.n
 * JD-Core Version:    0.6.2
 */