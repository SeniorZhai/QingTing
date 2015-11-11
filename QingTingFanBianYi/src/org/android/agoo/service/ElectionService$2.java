package org.android.agoo.service;

import android.text.TextUtils;
import com.umeng.message.proguard.Q;
import java.util.Map;
import org.android.agoo.net.mtop.MtopResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;

class ElectionService$2 extends MtopResponseHandler
{
  ElectionService$2(ElectionService paramElectionService)
  {
  }

  public void onFailure(String paramString1, String paramString2)
  {
    Q.d("ElectionService", "errCode[" + paramString1 + "]errDesc[" + paramString2 + "]");
    ElectionService.b(this.a);
  }

  public void onSuccess(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      Q.c("ElectionService", "remote content==null");
      ElectionService.b(this.a);
      return;
    }
    Q.c("ElectionService", "remote election result[" + paramString + "] ");
    try
    {
      paramString = new JSONObject(paramString);
      ElectionService.a(this.a, Long.parseLong(paramString.getString("time_out")));
      paramString = paramString.getJSONArray("vote_list");
      if (paramString == null)
      {
        Q.c("ElectionService", "remote vote_list==null");
        ElectionService.b(this.a);
        return;
      }
    }
    catch (Throwable paramString)
    {
      Q.d("ElectionService", "remote--JSONException", paramString);
      ElectionService.b(this.a);
      return;
    }
    int k = paramString.length();
    if (k <= 0)
    {
      Q.c("ElectionService", "remote vote_list.length==0");
      ElectionService.b(this.a);
      return;
    }
    while (true)
    {
      int i;
      Object localObject;
      String str1;
      int m;
      if (i < k)
      {
        localObject = paramString.getJSONObject(i);
        if (localObject == null)
        {
          ElectionService.b(this.a);
          return;
        }
        str1 = ((JSONObject)localObject).getString("package_name");
        if (TextUtils.isEmpty(str1))
        {
          Q.c("ElectionService", "sudoPack==null");
          ElectionService.b(this.a);
          return;
        }
        if (ElectionService.c(this.a).get(str1) == null)
        {
          Q.c("ElectionService", "elctionResults not found[" + str1 + "]");
          ElectionService.b(this.a);
          return;
        }
        localObject = ((JSONObject)localObject).getJSONArray("package_name_list");
        if (localObject == null)
        {
          Q.c("ElectionService", "remote package_name_list==null");
          ElectionService.b(this.a);
          return;
        }
        m = ((JSONArray)localObject).length();
        if (m > 0)
          break label362;
        Q.c("ElectionService", "remote package_name_list.length==0");
        ElectionService.b(this.a);
      }
      while (true)
      {
        if (j >= m)
          break label368;
        String str2 = ((JSONArray)localObject).getString(j);
        ElectionService.d(this.a).put(str2, str1);
        j += 1;
        continue;
        ElectionService.a(this.a, "remote");
        return;
        i = 0;
        break;
        label362: int j = 0;
      }
      label368: i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.ElectionService.2
 * JD-Core Version:    0.6.2
 */