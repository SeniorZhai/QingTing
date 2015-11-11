package weibo4android;

import java.io.Serializable;

public class Paging
  implements Serializable
{
  private static final long serialVersionUID = -3285857427993796670L;
  private int count = -1;
  private long maxId = -1L;
  private int page = -1;
  private long sinceId = -1L;

  public Paging()
  {
  }

  public Paging(int paramInt)
  {
    setPage(paramInt);
  }

  public Paging(int paramInt1, int paramInt2)
  {
    this(paramInt1);
    setCount(paramInt2);
  }

  public Paging(int paramInt1, int paramInt2, long paramLong)
  {
    this(paramInt1, paramInt2);
    setSinceId(paramLong);
  }

  public Paging(int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    this(paramInt1, paramInt2, paramLong1);
    setMaxId(paramLong2);
  }

  public Paging(int paramInt, long paramLong)
  {
    this(paramInt);
    setSinceId(paramLong);
  }

  public Paging(long paramLong)
  {
    setSinceId(paramLong);
  }

  public Paging count(int paramInt)
  {
    setCount(paramInt);
    return this;
  }

  public int getCount()
  {
    return this.count;
  }

  public long getMaxId()
  {
    return this.maxId;
  }

  public int getPage()
  {
    return this.page;
  }

  public long getSinceId()
  {
    return this.sinceId;
  }

  public Paging maxId(long paramLong)
  {
    setMaxId(paramLong);
    return this;
  }

  public void setCount(int paramInt)
  {
    if (paramInt < 1)
      throw new IllegalArgumentException("count should be positive integer. passed:" + paramInt);
    this.count = paramInt;
  }

  public void setMaxId(long paramLong)
  {
    if (paramLong < 1L)
      throw new IllegalArgumentException("max_id should be positive integer. passed:" + paramLong);
    this.maxId = paramLong;
  }

  public void setPage(int paramInt)
  {
    if (paramInt < 1)
      throw new IllegalArgumentException("page should be positive integer. passed:" + paramInt);
    this.page = paramInt;
  }

  public void setSinceId(int paramInt)
  {
    if (paramInt < 1)
      throw new IllegalArgumentException("since_id should be positive integer. passed:" + paramInt);
    this.sinceId = paramInt;
  }

  public void setSinceId(long paramLong)
  {
    if (paramLong < 1L)
      throw new IllegalArgumentException("since_id should be positive integer. passed:" + paramLong);
    this.sinceId = paramLong;
  }

  public Paging sinceId(int paramInt)
  {
    setSinceId(paramInt);
    return this;
  }

  public Paging sinceId(long paramLong)
  {
    setSinceId(paramLong);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Paging
 * JD-Core Version:    0.6.2
 */