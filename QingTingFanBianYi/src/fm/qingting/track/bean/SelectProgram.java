package fm.qingting.track.bean;

public class SelectProgram extends UserAction
{
  public int id;
  public String pname;

  public SelectProgram(int paramInt, String paramString)
  {
    super(0, "select_program");
    this.id = paramInt;
    this.pname = paramString;
  }

  public String toString()
  {
    return addQuotes(this.name) + "," + addQuotes(new StringBuilder().append(this.pname).append("---").append(this.id).toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.track.bean.SelectProgram
 * JD-Core Version:    0.6.2
 */