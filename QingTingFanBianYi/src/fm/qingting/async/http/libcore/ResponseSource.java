package fm.qingting.async.http.libcore;

public enum ResponseSource
{
  CACHE, CONDITIONAL_CACHE, NETWORK;

  public boolean requiresConnection()
  {
    return (this == CONDITIONAL_CACHE) || (this == NETWORK);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.ResponseSource
 * JD-Core Version:    0.6.2
 */