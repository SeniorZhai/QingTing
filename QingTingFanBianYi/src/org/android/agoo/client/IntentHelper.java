package org.android.agoo.client;

import android.content.Context;
import android.content.Intent;

public class IntentHelper
{
  private static final String a = ".intent.action.COMMAND";
  private static final String b = ".intent.action.START";
  private static final String c = ".intent.action.COCKROACH";

  public static Intent createComandIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction(getAgooCommand(paramContext));
    localIntent.putExtra("command", paramString);
    return localIntent;
  }

  public static String getAgooCockroach(Context paramContext)
  {
    if (paramContext != null)
      return paramContext.getPackageName() + ".intent.action.COCKROACH";
    return "org.agoo.android.intent.action.COCKROACH";
  }

  public static String getAgooCommand(Context paramContext)
  {
    if (paramContext != null)
      return paramContext.getPackageName() + ".intent.action.COMMAND";
    return "org.agoo.android.intent.action.COMMAND";
  }

  public static String getAgooStart(Context paramContext)
  {
    if (paramContext != null)
      return paramContext.getPackageName() + ".intent.action.START";
    return "org.agoo.android.intent.action.START";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.IntentHelper
 * JD-Core Version:    0.6.2
 */