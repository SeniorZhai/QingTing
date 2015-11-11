package com.taobao.newxp.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.taobao.munion.base.f;
import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.s;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.net.MMEntity;
import com.taobao.newxp.net.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class c extends f
{
  ExchangeDataService a;
  private final XpListenersCenter.ExchangeDataRequestListener b;
  private final int c;
  private final boolean d;

  public c(ExchangeDataService paramExchangeDataService, XpListenersCenter.ExchangeDataRequestListener paramExchangeDataRequestListener, int paramInt, boolean paramBoolean)
  {
    this.a = paramExchangeDataService;
    this.b = paramExchangeDataRequestListener;
    this.c = paramInt;
    this.d = paramBoolean;
  }

  public void a()
  {
    a(false);
  }

  public void a(s params)
  {
    if (this.b != null)
      this.b.dataReceived(0, new ArrayList());
  }

  public void a(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    MMEntity localMMEntity = this.a.getEntity();
    boolean bool;
    if (TextUtils.isEmpty(localMMEntity.sid))
      bool = true;
    while (true)
    {
      int j = this.a.a.a(localMMEntity, localArrayList, this.a.mSpecificPromoterClz, paramJSONObject);
      Object localObject = AlimmContext.getAliContext().getAppContext();
      int i = paramJSONObject.optInt("preload", 0);
      localObject = ((Context)localObject).getSharedPreferences(this.a.e, 0);
      if (((SharedPreferences)localObject).getInt(this.a.f, 0) != i)
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).putInt(this.a.f, i);
        ((SharedPreferences.Editor)localObject).commit();
      }
      if (localArrayList != null);
      try
      {
        if (localArrayList.size() <= 0)
          if (bool)
            this.a.removeCache();
        while (true)
        {
          if (this.b != null)
          {
            if (localMMEntity.filterInstalledApp)
            {
              i = this.a.a(localArrayList);
              if ((i > 0) && (localMMEntity.newTips > 0))
              {
                int k = localMMEntity.newTips;
                i = localMMEntity.newTips - i;
                if (i <= 0)
                  break label312;
                localMMEntity.newTips = i;
                Log.c(ExchangeConstants.LOG_TAG, "new tips has changed " + k + " ===> " + localMMEntity.newTips);
              }
            }
            this.b.dataReceived(j, localArrayList);
          }
          return;
          bool = false;
          break;
          if ((this.d) && (j == 1))
            this.a.a(bool, paramJSONObject);
        }
      }
      catch (Exception paramJSONObject)
      {
        while (true)
        {
          continue;
          label312: i = -1;
        }
      }
    }
  }

  public void a(boolean paramBoolean)
  {
    int j = 0;
    TextUtils.isEmpty(this.a.b.sid);
    int i = this.c;
    Object localObject = this.a;
    if (i == 1)
    {
      localObject = this.a;
      if (TextUtils.isEmpty(this.a.b.sid));
      for (paramBoolean = true; ; paramBoolean = false)
      {
        localObject = ((ExchangeDataService)localObject).requestCache(paramBoolean, true);
        i = j;
        if (localObject != null)
        {
          i = j;
          if (((List)localObject).size() > 0)
            i = 1;
        }
        if (this.b != null)
          this.b.dataReceived(i, (List)localObject);
        Log.a(ExchangeConstants.LOG_TAG, "get data from cache.");
        return;
      }
    }
    localObject = this.a.a.a(this.a.b, paramBoolean, this);
    AlimmContext.getAliContext().getQueryQueue().a((l)localObject);
    Log.a(ExchangeConstants.LOG_TAG, "get data from live.");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.c
 * JD-Core Version:    0.6.2
 */