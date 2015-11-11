package com.motorola.android.fmradio;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class Preferences
{
  private static final int DEFAULT_SENSITIVITY = 12;
  private static final int DEFAULT_VOLUME = 10;
  private static final String KEY_IGNORE_AIRPLANE_MODE = "ignore_airplane_mode";
  private static final String KEY_IGNORE_NO_HEADSET = "ignore_no_headset";
  private static final String KEY_MEDIA_BUTTON_BEHAVIOUR = "media_button_behaviour";
  private static final String KEY_SCANNED = "scanned";
  private static final String KEY_SEEK_SENSITIVITY = "seek_sensitivity";
  private static final String KEY_VOLUME = "volume";

  private static SharedPreferences getPrefs(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext);
  }

  public static int getSeekSensitivityThreshold(Context paramContext)
  {
    paramContext = getPrefs(paramContext).getString("seek_sensitivity", null);
    if (paramContext == null)
      return 12;
    return Integer.parseInt(paramContext);
  }

  public static int getVolume(Context paramContext)
  {
    return getPrefs(paramContext).getInt("volume", 10);
  }

  public static boolean isAirplaneModeIgnored(Context paramContext)
  {
    return getPrefs(paramContext).getBoolean("ignore_airplane_mode", false);
  }

  public static boolean isHeadsetRequired(Context paramContext)
  {
    return !getPrefs(paramContext).getBoolean("ignore_no_headset", false);
  }

  public static boolean isScanned(Context paramContext)
  {
    return getPrefs(paramContext).getBoolean("scanned", false);
  }

  public static boolean mediaButtonPrevNextSwitchesPresets(Context paramContext)
  {
    return !TextUtils.equals(getPrefs(paramContext).getString("media_button_behaviour", null), "seek");
  }

  public static void setScanned(Context paramContext, boolean paramBoolean)
  {
    getPrefs(paramContext).edit().putBoolean("scanned", paramBoolean).commit();
  }

  public static void setVolume(Context paramContext, int paramInt)
  {
    getPrefs(paramContext).edit().putInt("volume", paramInt).commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.motorola.android.fmradio.Preferences
 * JD-Core Version:    0.6.2
 */