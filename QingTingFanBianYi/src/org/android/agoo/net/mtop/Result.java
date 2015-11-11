package org.android.agoo.net.mtop;

public class Result
{
  private volatile boolean a;
  private volatile String b;
  private volatile String c;
  private volatile String d;

  public String getData()
  {
    return this.b;
  }

  public String getRetCode()
  {
    return this.d;
  }

  public String getRetDesc()
  {
    return this.c;
  }

  public boolean isSuccess()
  {
    return this.a;
  }

  public void setData(String paramString)
  {
    this.b = paramString;
  }

  public void setRetCode(String paramString)
  {
    this.d = paramString;
  }

  public void setRetDesc(String paramString)
  {
    this.c = paramString;
  }

  public void setSuccess(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public String toString()
  {
    return "Result [isSuccess=" + this.a + ", data=" + this.b + ", retDesc=" + this.c + ", retCode=" + this.d + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.Result
 * JD-Core Version:    0.6.2
 */