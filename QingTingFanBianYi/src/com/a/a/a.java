package com.a.a;

import java.util.ArrayList;

public abstract class a
  implements Cloneable
{
  ArrayList<a> a = null;

  public void a()
  {
  }

  public void a(a parama)
  {
    if (this.a == null)
      this.a = new ArrayList();
    this.a.add(parama);
  }

  public void b()
  {
  }

  public a c()
  {
    int j;
    int i;
    do
      try
      {
        a locala = (a)super.clone();
        if (this.a == null)
          break;
        ArrayList localArrayList = this.a;
        locala.a = new ArrayList();
        j = localArrayList.size();
        i = 0;
        continue;
        locala.a.add((a)localArrayList.get(i));
        i += 1;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        throw new AssertionError();
      }
    while (i < j);
    return localCloneNotSupportedException;
  }

  public static abstract interface a
  {
    public abstract void onAnimationCancel(a parama);

    public abstract void onAnimationEnd(a parama);

    public abstract void onAnimationRepeat(a parama);

    public abstract void onAnimationStart(a parama);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.a.a
 * JD-Core Version:    0.6.2
 */