package com.tencent.b.a;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class d
  implements Iterable<String>
{
  private ConcurrentLinkedQueue<String> a = null;
  private AtomicInteger b = null;

  public int a()
  {
    return this.b.get();
  }

  public int a(String paramString)
  {
    int i = paramString.length();
    this.a.add(paramString);
    return this.b.addAndGet(i);
  }

  public void a(Writer paramWriter, char[] paramArrayOfChar)
    throws IOException
  {
    if ((paramWriter == null) || (paramArrayOfChar == null) || (paramArrayOfChar.length == 0))
      return;
    int n = paramArrayOfChar.length;
    Iterator localIterator = iterator();
    int i2 = 0;
    int i1 = n;
    String str;
    int k;
    int m;
    int i;
    int j;
    if (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      k = str.length();
      m = 0;
      i = i1;
      j = i2;
    }
    label183: 
    while (true)
    {
      i2 = j;
      i1 = i;
      if (k <= 0)
        break;
      if (i > k);
      for (i1 = k; ; i1 = i)
      {
        str.getChars(m, m + i1, paramArrayOfChar, j);
        i -= i1;
        j += i1;
        k -= i1;
        m = i1 + m;
        if (i != 0)
          break label183;
        paramWriter.write(paramArrayOfChar, 0, n);
        j = 0;
        i = n;
        break;
      }
      if (i2 > 0)
        paramWriter.write(paramArrayOfChar, 0, i2);
      paramWriter.flush();
      return;
    }
  }

  public void b()
  {
    this.a.clear();
    this.b.set(0);
  }

  public Iterator<String> iterator()
  {
    return this.a.iterator();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.a.d
 * JD-Core Version:    0.6.2
 */