package master.flame.danmaku.danmaku.parser.android;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AcFunDanmakuParser extends BaseDanmakuParser
{
  private Danmakus _parse(JSONArray paramJSONArray, Danmakus paramDanmakus)
  {
    int i = 0;
    if (paramDanmakus == null)
    {
      paramDanmakus = new Danmakus();
      if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
        return paramDanmakus;
    }
    while (true)
    {
      if (i < paramJSONArray.length());
      try
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        Object localObject = localJSONObject.getString("c").split(",");
        if (localObject.length <= 0)
          break label240;
        j = Integer.parseInt(localObject[2]);
        if (j == 7)
          break label240;
        long l = ()(Float.parseFloat(localObject[0]) * 1000.0F);
        int k = Integer.parseInt(localObject[1]) | 0xFF000000;
        float f = Float.parseFloat(localObject[3]);
        localObject = DanmakuFactory.createDanmaku(j, this.mDisp);
        if (localObject == null)
          break label240;
        ((BaseDanmaku)localObject).time = l;
        ((BaseDanmaku)localObject).textSize = (f * (this.mDispDensity - 0.6F));
        ((BaseDanmaku)localObject).textColor = k;
        if (k <= -16777216)
        {
          j = -1;
          ((BaseDanmaku)localObject).textShadowColor = j;
          DanmakuFactory.fillText((BaseDanmaku)localObject, localJSONObject.optString("m", "...."));
          ((BaseDanmaku)localObject).index = i;
          ((BaseDanmaku)localObject).setTimer(this.mTimer);
          paramDanmakus.addItem((BaseDanmaku)localObject);
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          break;
          int j = -16777216;
        }
        return paramDanmakus;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
      break;
      label240: i += 1;
    }
  }

  private Danmakus doParse(JSONArray paramJSONArray)
  {
    Object localObject1 = new Danmakus();
    Object localObject2 = localObject1;
    if (paramJSONArray != null)
    {
      if (paramJSONArray.length() == 0)
        localObject2 = localObject1;
    }
    else
      return localObject2;
    int i = 0;
    while (true)
    {
      localObject2 = localObject1;
      if (i >= paramJSONArray.length())
        break;
      try
      {
        JSONArray localJSONArray = paramJSONArray.getJSONArray(i);
        localObject2 = localObject1;
        if (localJSONArray != null)
          localObject2 = _parse(localJSONArray, (Danmakus)localObject1);
        i += 1;
        localObject1 = localObject2;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          Object localObject3 = localObject1;
      }
    }
  }

  public Danmakus parse()
  {
    if ((this.mDataSource != null) && ((this.mDataSource instanceof JSONSource)))
      return doParse(((JSONSource)this.mDataSource).data());
    return new Danmakus();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.parser.android.AcFunDanmakuParser
 * JD-Core Version:    0.6.2
 */