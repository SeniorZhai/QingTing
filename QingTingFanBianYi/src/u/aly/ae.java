package u.aly;

import org.json.JSONArray;

public class ae extends ay
{
  public ae()
  {
  }

  public ae(String paramString)
    throws Exception
  {
    a(new JSONArray(paramString));
  }

  public ae(JSONArray paramJSONArray)
    throws Exception
  {
    a(paramJSONArray);
  }

  private void a(JSONArray paramJSONArray)
    throws Exception
  {
    a(paramJSONArray.getString(0));
    a(paramJSONArray.getInt(1));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ae
 * JD-Core Version:    0.6.2
 */