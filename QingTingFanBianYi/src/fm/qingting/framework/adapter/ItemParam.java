package fm.qingting.framework.adapter;

public class ItemParam
{
  public Object param;
  public int position;
  public Object target;
  public String type = "";

  public ItemParam(String paramString, int paramInt, Object paramObject1, Object paramObject2)
  {
    this.type = paramString;
    this.position = paramInt;
    this.target = paramObject1;
    this.param = paramObject2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.adapter.ItemParam
 * JD-Core Version:    0.6.2
 */