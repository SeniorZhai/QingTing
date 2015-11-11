package fm.qingting.qtradio.jd.data;

import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestParam
{
  private int mAdHeight = 200;
  private int mAdWidth = 200;
  private String mDeviceId = "111";
  private String mGender = "M";
  private String mId = "123";
  private String mParamString = "";

  private String getUserTag()
  {
    ArrayList localArrayList = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
    if (localArrayList != null)
    {
      Object localObject1 = "";
      int i = 0;
      while (true)
      {
        localObject2 = localObject1;
        if (i >= localArrayList.size())
          break;
        localObject2 = localObject1;
        if (i >= 3)
          break;
        localObject2 = (String)localObject1 + localArrayList.get(i);
        localObject1 = localObject2;
        if (i < 2)
          localObject1 = (String)localObject2 + "_";
        i += 1;
      }
    }
    Object localObject2 = "";
    return localObject2;
  }

  public void setDeviceId(String paramString)
  {
    this.mDeviceId = paramString;
    this.mId = (paramString + System.currentTimeMillis());
    this.mParamString = "";
  }

  public void setGender(String paramString)
  {
    this.mGender = paramString;
    this.mParamString = "";
  }

  public String toString()
  {
    JSONObject localJSONObject1;
    if (this.mParamString.equalsIgnoreCase(""))
      localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("id", this.mId);
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("did", this.mDeviceId);
      localJSONObject1.put("device", localJSONObject2);
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("gender", this.mGender);
      localJSONObject2.put("usertag", getUserTag());
      localJSONObject1.put("user", localJSONObject2);
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("w", this.mAdWidth);
      localJSONObject2.put("h", this.mAdHeight);
      localJSONObject1.put("ad", localJSONObject2);
      this.mParamString = localJSONObject1.toString();
      return this.mParamString;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        this.mParamString = "";
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.jd.data.RequestParam
 * JD-Core Version:    0.6.2
 */