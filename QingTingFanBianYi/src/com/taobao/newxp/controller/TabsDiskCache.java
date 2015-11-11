package com.taobao.newxp.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.newxp.UFPResType;
import com.taobao.newxp.b;
import com.taobao.newxp.common.Category;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.b.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TabsDiskCache
{
  public static final Category a = new Category("", "热卖", "", UFPResType.TB_ITEM, b.b);
  public static final Category b = new Category("", ExchangeConstants.DEFAULT_HANDLE_APP_WALL_TITLE, "", UFPResType.APP, b.a);
  private static final String e = TabsDiskCache.class.getName();
  private static final String f = "UMENG_TABS_CACHE";
  private static final String g = "Tabs";
  String c;
  int d = 0;
  private final Context h;

  private TabsDiskCache(Context paramContext)
  {
    this.h = paramContext;
  }

  public static TabsDiskCache a(Context paramContext, String paramString)
  {
    paramContext = new TabsDiskCache(paramContext.getApplicationContext());
    paramContext.c = paramString;
    Log.i(e, "Initailized TabsDiskCahce [" + paramContext.c + "]");
    return paramContext;
  }

  private List<Category> b(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    try
    {
      while (i < paramJSONArray.length())
      {
        Object localObject3 = (JSONObject)paramJSONArray.get(i);
        Object localObject1 = ((JSONObject)localObject3).optString("title");
        String str = ((JSONObject)localObject3).optString("url_params");
        Object localObject4 = ((JSONObject)localObject3).optString("resource_type");
        Object localObject2 = ((JSONObject)localObject3).optString("template");
        localObject3 = ((JSONObject)localObject3).optString("tabid", "");
        this.d += 1;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          UFPResType localUFPResType = UFPResType.fromString((String)localObject4);
          localObject4 = new HashSet();
          localObject2 = b.a((String)localObject2, (Set)localObject4);
          localObject1 = new Category(e.a(str), (String)localObject1, (String)localObject3, localUFPResType, (b)localObject2);
          ((Category)localObject1).index = i;
          ((Category)localObject1).addAttributes((Collection)localObject4);
          localArrayList.add(localObject1);
        }
        i += 1;
      }
    }
    catch (JSONException paramJSONArray)
    {
      Log.w(e, "", paramJSONArray);
    }
    return localArrayList;
  }

  private String c()
  {
    return "Tabs_" + this.c;
  }

  public List<Category> a(Category[] paramArrayOfCategory)
  {
    Object localObject = a();
    if ((localObject != null) && (((JSONArray)localObject).length() > 0))
    {
      List localList = b((JSONArray)localObject);
      if (localList != null)
      {
        localObject = localList;
        if (localList.size() != 0);
      }
      else
      {
        if (paramArrayOfCategory == null)
          break label48;
        localObject = Arrays.asList(paramArrayOfCategory);
      }
      return localObject;
      label48: return new ArrayList();
    }
    if (paramArrayOfCategory != null)
      return Arrays.asList(paramArrayOfCategory);
    return new ArrayList();
  }

  public JSONArray a()
  {
    if (this.h == null)
    {
      Log.e(e, "TabDiskCache is not initialized.");
      return null;
    }
    try
    {
      JSONArray localJSONArray = new JSONArray(this.h.getSharedPreferences("UMENG_TABS_CACHE", 1).getString(c(), ""));
      Log.i(e, "get Data from TabsDiskCahce [" + this.c + "] " + localJSONArray.toString());
      return localJSONArray;
    }
    catch (Exception localException)
    {
      Log.w(e, "", localException);
    }
    return null;
  }

  public boolean a(JSONArray paramJSONArray)
  {
    if (this.h == null)
      Log.e(e, "TabDiskCache is not initialized.");
    while (true)
    {
      return false;
      if (paramJSONArray != null)
        try
        {
          if (paramJSONArray.length() > 0)
          {
            SharedPreferences localSharedPreferences = this.h.getSharedPreferences("UMENG_TABS_CACHE", 2);
            SharedPreferences.Editor localEditor = localSharedPreferences.edit();
            try
            {
              localEditor.putString(c(), paramJSONArray.toString());
              localEditor.commit();
              Log.i(e, "update TabsDiskCahce [" + this.c + "] " + paramJSONArray.toString());
              return true;
            }
            finally
            {
            }
          }
        }
        catch (Exception paramJSONArray)
        {
          Log.w(e, "", paramJSONArray);
        }
    }
    return false;
  }

  public boolean b()
  {
    try
    {
      SharedPreferences.Editor localEditor = this.h.getSharedPreferences("UMENG_TABS_CACHE", 2).edit();
      localEditor.clear();
      localEditor.commit();
      return true;
    }
    catch (Exception localException)
    {
      Log.w(e, "", localException);
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.TabsDiskCache
 * JD-Core Version:    0.6.2
 */