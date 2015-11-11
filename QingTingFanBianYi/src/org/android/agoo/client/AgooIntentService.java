package org.android.agoo.client;

import android.app.IntentService;
import android.os.Process;

public abstract class AgooIntentService extends IntentService
{
  public AgooIntentService(String paramString)
  {
    super(paramString);
  }

  public void onDestroy()
  {
    super.onDestroy();
    Process.sendSignal(Process.myPid(), 3);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.AgooIntentService
 * JD-Core Version:    0.6.2
 */