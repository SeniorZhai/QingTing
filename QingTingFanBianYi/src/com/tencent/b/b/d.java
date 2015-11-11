package com.tencent.b.b;

import android.os.StatFs;
import java.io.File;

public class d
{
  private File a;
  private long b;
  private long c;

  public static d b(File paramFile)
  {
    d locald = new d();
    locald.a(paramFile);
    paramFile = new StatFs(paramFile.getAbsolutePath());
    long l1 = paramFile.getBlockSize();
    long l2 = paramFile.getBlockCount();
    long l3 = paramFile.getAvailableBlocks();
    locald.a(l2 * l1);
    locald.b(l3 * l1);
    return locald;
  }

  public File a()
  {
    return this.a;
  }

  public void a(long paramLong)
  {
    this.b = paramLong;
  }

  public void a(File paramFile)
  {
    this.a = paramFile;
  }

  public long b()
  {
    return this.b;
  }

  public void b(long paramLong)
  {
    this.c = paramLong;
  }

  public long c()
  {
    return this.c;
  }

  public String toString()
  {
    return String.format("[%s : %d / %d]", new Object[] { a().getAbsolutePath(), Long.valueOf(c()), Long.valueOf(b()) });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.b.d
 * JD-Core Version:    0.6.2
 */