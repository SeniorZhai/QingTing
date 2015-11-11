package com.ta.utdid2.core.persistent;

import java.util.Map;

public abstract interface MySharedPreferences
{
  public abstract boolean checkFile();

  public abstract boolean contains(String paramString);

  public abstract MyEditor edit();

  public abstract Map<String, ?> getAll();

  public abstract boolean getBoolean(String paramString, boolean paramBoolean);

  public abstract float getFloat(String paramString, float paramFloat);

  public abstract int getInt(String paramString, int paramInt);

  public abstract long getLong(String paramString, long paramLong);

  public abstract String getString(String paramString1, String paramString2);

  public abstract void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener);

  public abstract void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener);

  public static abstract interface MyEditor
  {
    public abstract MyEditor clear();

    public abstract boolean commit();

    public abstract MyEditor putBoolean(String paramString, boolean paramBoolean);

    public abstract MyEditor putFloat(String paramString, float paramFloat);

    public abstract MyEditor putInt(String paramString, int paramInt);

    public abstract MyEditor putLong(String paramString, long paramLong);

    public abstract MyEditor putString(String paramString1, String paramString2);

    public abstract MyEditor remove(String paramString);
  }

  public static abstract interface OnSharedPreferenceChangeListener
  {
    public abstract void onSharedPreferenceChanged(MySharedPreferences paramMySharedPreferences, String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.MySharedPreferences
 * JD-Core Version:    0.6.2
 */