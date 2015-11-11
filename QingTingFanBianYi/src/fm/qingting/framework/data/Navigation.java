package fm.qingting.framework.data;

public class Navigation
{
  public int count = 0;
  public int page = 1;
  public int size = 10;

  public Navigation()
  {
  }

  public Navigation(int paramInt)
  {
    this.page = 1;
    this.count = paramInt;
    this.size = paramInt;
  }

  public Navigation(int paramInt1, int paramInt2, int paramInt3)
  {
    this.page = paramInt1;
    this.size = paramInt2;
    this.count = paramInt3;
  }

  public boolean HasNext()
  {
    return this.page < Math.ceil(this.count / this.size);
  }

  public void reset()
  {
    this.page = 1;
    this.size = 10;
    this.count = 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.Navigation
 * JD-Core Version:    0.6.2
 */