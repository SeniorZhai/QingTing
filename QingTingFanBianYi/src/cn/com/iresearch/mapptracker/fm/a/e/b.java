package cn.com.iresearch.mapptracker.fm.a.e;

import java.text.SimpleDateFormat;

public final class b
{
  private static SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private String a;
  private Object b;

  public b()
  {
  }

  public b(String paramString, Object paramObject)
  {
    this.a = paramString;
    this.b = paramObject;
  }

  public final String a()
  {
    return this.a;
  }

  public final Object b()
  {
    if (((this.b instanceof java.util.Date)) || ((this.b instanceof java.sql.Date)))
      return c.format(this.b);
    return this.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.e.b
 * JD-Core Version:    0.6.2
 */