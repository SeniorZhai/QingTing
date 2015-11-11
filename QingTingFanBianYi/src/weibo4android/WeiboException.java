package weibo4android;

public class WeiboException extends Exception
{
  private static final long serialVersionUID = -2623309261327598087L;
  private int statusCode = -1;

  public WeiboException(Exception paramException)
  {
    super(paramException);
  }

  public WeiboException(String paramString)
  {
    super(paramString);
  }

  public WeiboException(String paramString, int paramInt)
  {
    super(paramString);
    this.statusCode = paramInt;
  }

  public WeiboException(String paramString, Exception paramException)
  {
    super(paramString, paramException);
  }

  public WeiboException(String paramString, Exception paramException, int paramInt)
  {
    super(paramString, paramException);
    this.statusCode = paramInt;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.WeiboException
 * JD-Core Version:    0.6.2
 */