package com.taobao.munion.base.caches;

public class m
{
  public int a = 0;
  private boolean b = true;

  public void a()
  {
    try
    {
      while (true)
      {
        boolean bool = this.b;
        if (!bool)
          break;
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException)
        {
        }
      }
      return;
    }
    finally
    {
    }
  }

  public void b()
  {
    try
    {
      if (this.b)
      {
        this.b = false;
        notify();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.m
 * JD-Core Version:    0.6.2
 */