package fm.qingting.track.bean;

public class SelectSubCat extends UserAction
{
  public String catName;
  public String id;

  public SelectSubCat(String paramString1, String paramString2)
  {
    super(4, "in_sub_category");
    this.id = paramString1;
    this.catName = paramString2;
  }

  public String toString()
  {
    return addQuotes(this.name) + "," + addQuotes(new StringBuilder().append(this.catName).append("---").append(this.id).toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.track.bean.SelectSubCat
 * JD-Core Version:    0.6.2
 */