package fm.qingting.qtradio.config;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class SharedConfig
{
  private static SharedConfig mInstance;
  private SharedPreferences.Editor mEditor;
  private SharedPreferences mSharedPref;

  public static SharedConfig getInstance()
  {
    if (mInstance == null)
      mInstance = new SharedConfig();
    return mInstance;
  }

  public boolean getBoolean(String paramString)
  {
    return this.mSharedPref.getBoolean(paramString, true);
  }

  public float getFloat(String paramString)
  {
    return this.mSharedPref.getFloat(paramString, 0.0F);
  }

  public int getInt(String paramString)
  {
    return this.mSharedPref.getInt(paramString, 0);
  }

  public long getLong(String paramString)
  {
    return this.mSharedPref.getLong(paramString, 0L);
  }

  public String getString(String paramString)
  {
    return this.mSharedPref.getString(paramString, "");
  }

  public Set<String> getStringSet(String paramString)
  {
    return this.mSharedPref.getStringSet(paramString, null);
  }

  public void init(Activity paramActivity)
  {
    this.mSharedPref = paramActivity.getPreferences(0);
    this.mEditor = this.mSharedPref.edit();
  }

  public void putInt(String paramString, int paramInt)
  {
    this.mEditor.putInt(paramString, paramInt);
    this.mEditor.commit();
  }

  public void putIntPreferences(HashMap<String, Integer> paramHashMap)
  {
    putPreferences(paramHashMap, null, null, null, null, null);
  }

  public void putPreferences(HashMap<String, Integer> paramHashMap, HashMap<String, Long> paramHashMap1, HashMap<String, Boolean> paramHashMap2, HashMap<String, Float> paramHashMap3, HashMap<String, String> paramHashMap4, HashMap<String, Set<String>> paramHashMap5)
  {
    if (paramHashMap != null)
    {
      paramHashMap = paramHashMap.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramHashMap.next();
        this.mEditor.putInt((String)localEntry.getKey(), ((Integer)localEntry.getValue()).intValue());
      }
    }
    if (paramHashMap1 != null)
    {
      paramHashMap = paramHashMap1.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        paramHashMap1 = (Map.Entry)paramHashMap.next();
        this.mEditor.putLong((String)paramHashMap1.getKey(), ((Long)paramHashMap1.getValue()).longValue());
      }
    }
    if (paramHashMap2 != null)
    {
      paramHashMap = paramHashMap2.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        paramHashMap1 = (Map.Entry)paramHashMap.next();
        this.mEditor.putBoolean((String)paramHashMap1.getKey(), ((Boolean)paramHashMap1.getValue()).booleanValue());
      }
    }
    if (paramHashMap3 != null)
    {
      paramHashMap = paramHashMap3.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        paramHashMap1 = (Map.Entry)paramHashMap.next();
        this.mEditor.putFloat((String)paramHashMap1.getKey(), ((Float)paramHashMap1.getValue()).floatValue());
      }
    }
    if (paramHashMap4 != null)
    {
      paramHashMap = paramHashMap4.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        paramHashMap1 = (Map.Entry)paramHashMap.next();
        this.mEditor.putString((String)paramHashMap1.getKey(), (String)paramHashMap1.getValue());
      }
    }
    if (paramHashMap5 != null)
    {
      paramHashMap = paramHashMap5.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        paramHashMap1 = (Map.Entry)paramHashMap.next();
        this.mEditor.putStringSet((String)paramHashMap1.getKey(), (Set)paramHashMap1.getValue());
      }
    }
    this.mEditor.commit();
  }

  public void putStringPreferences(HashMap<String, String> paramHashMap)
  {
    putPreferences(null, null, null, null, paramHashMap, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.config.SharedConfig
 * JD-Core Version:    0.6.2
 */