package com.taobao.newxp.controller;

import com.taobao.newxp.Promoter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  implements Iterator<Promoter>
{
  private ArrayList<Promoter> a = new ArrayList();
  private int b = 0;

  public a(List<Promoter> paramList)
  {
    this.a.addAll(paramList);
  }

  public int a()
  {
    return this.b;
  }

  public List<a> a(int paramInt)
  {
    Object localObject;
    if ((b() == 0) || (paramInt <= 0))
    {
      localObject = null;
      return localObject;
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      localObject = localArrayList;
      if (i >= paramInt)
        break;
      localArrayList.add(e());
      i += 1;
    }
  }

  public void a(List<Promoter> paramList)
  {
    if (this.a == null)
      this.a = new ArrayList();
    this.a.addAll(paramList);
  }

  public int b()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
  }

  public Promoter b(int paramInt)
  {
    if (this.a != null)
      return (Promoter)this.a.get(paramInt);
    return null;
  }

  public Promoter c()
  {
    if (hasNext())
    {
      ArrayList localArrayList = this.a;
      int i = this.b;
      this.b = (i + 1);
      return (Promoter)localArrayList.get(i);
    }
    return null;
  }

  public Promoter d()
  {
    if (b() == 0)
      return null;
    this.b %= b();
    ArrayList localArrayList = this.a;
    int i = this.b;
    this.b = (i + 1);
    return (Promoter)localArrayList.get(i);
  }

  public a e()
  {
    if (b() == 0)
      return null;
    this.b %= b();
    a locala = new a((Promoter)this.a.get(this.b), this.b);
    this.b += 1;
    return locala;
  }

  public Promoter f()
  {
    if ((this.b > 0) && (this.b <= b()))
      return (Promoter)this.a.get(this.b - 1);
    return null;
  }

  public boolean hasNext()
  {
    return (this.a != null) && (this.b >= 0) && (this.b < this.a.size());
  }

  public void remove()
  {
    if ((this.b > 0) && (this.b <= b()))
      this.a.remove(this.b - 1);
  }

  public static class a
  {
    public Promoter a;
    public int b = -1;

    public a(Promoter paramPromoter, int paramInt)
    {
      this.a = paramPromoter;
      this.b = paramInt;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.a
 * JD-Core Version:    0.6.2
 */