package cn.com.iresearch.mapptracker.fm.a.d;

import java.util.LinkedList;

public final class a
{
  private String a;
  private LinkedList<Object> b;

  public final String a()
  {
    return this.a;
  }

  public final void a(Object paramObject)
  {
    if (this.b == null)
      this.b = new LinkedList();
    this.b.add(paramObject);
  }

  public final void a(String paramString)
  {
    this.a = paramString;
  }

  public final Object[] b()
  {
    if (this.b != null)
      return this.b.toArray();
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.d.a
 * JD-Core Version:    0.6.2
 */