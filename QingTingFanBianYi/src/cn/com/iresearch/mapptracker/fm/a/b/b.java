package cn.com.iresearch.mapptracker.fm.a.b;

import java.io.PrintStream;

public final class b extends a
{
  private String a = null;

  public b()
  {
  }

  public b(String paramString)
  {
    this.a = paramString;
  }

  public final void printStackTrace()
  {
    if (this.a != null)
      System.err.println(this.a);
    super.printStackTrace();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.b.b
 * JD-Core Version:    0.6.2
 */