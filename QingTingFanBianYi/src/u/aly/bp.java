package u.aly;

import org.json.JSONObject;

public abstract class bp
{
  protected static String b = "POST";
  protected static String c = "GET";
  protected String d;

  public bp(String paramString)
  {
    this.d = paramString;
  }

  public abstract JSONObject a();

  public void a(String paramString)
  {
    this.d = paramString;
  }

  public abstract String b();

  protected String c()
  {
    return b;
  }

  public String d()
  {
    return this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bp
 * JD-Core Version:    0.6.2
 */