package com.intowow.sdk.model;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class g
{
  private String a = null;
  private d b = null;
  private h c = null;
  private List<f> d = null;

  public g(d paramd, h paramh, String paramString)
  {
    this.b = paramd;
    this.c = paramh;
    this.a = paramString;
  }

  public static g a(JSONObject paramJSONObject)
  {
    while (true)
    {
      Object localObject2;
      Object localObject1;
      int j;
      int i;
      try
      {
        localObject2 = paramJSONObject.getString("name");
        Object localObject3 = paramJSONObject.getString("type");
        if (!paramJSONObject.has("group_type"))
          break label204;
        localObject1 = paramJSONObject.getString("group_type");
        localObject3 = d.a((String)localObject3);
        localObject1 = h.a((String)localObject1);
        if ((localObject1 == h.a) && (localObject3 != null))
          switch (d()[localObject3.ordinal()])
          {
          case 2:
            localObject2 = new g((d)localObject3, (h)localObject1, (String)localObject2);
            paramJSONObject = paramJSONObject.getJSONArray("placements");
            j = paramJSONObject.length();
            i = 0;
            break label195;
            localObject1 = h.b;
            break;
          case 4:
            localObject1 = h.c;
            break;
          case 5:
            localObject1 = h.d;
            break;
          case 6:
            localObject1 = h.f;
            break;
          case 1:
          case 3:
            localObject1 = h.a;
            continue;
            ((g)localObject2).a(new f((d)localObject3, (h)localObject1, paramJSONObject.getString(i)));
            i += 1;
          }
      }
      catch (Exception paramJSONObject)
      {
        return null;
      }
      continue;
      label195: if (i >= j)
      {
        return localObject2;
        label204: localObject1 = null;
      }
    }
  }

  public String a()
  {
    return this.a;
  }

  public void a(f paramf)
  {
    this.d.add(paramf);
  }

  public h b()
  {
    return this.c;
  }

  public List<f> c()
  {
    return this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.model.g
 * JD-Core Version:    0.6.2
 */