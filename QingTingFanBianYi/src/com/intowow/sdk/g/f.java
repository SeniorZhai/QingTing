package com.intowow.sdk.g;

import android.os.Bundle;
import android.os.Message;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.b.k;
import com.intowow.sdk.b.k.b;
import com.intowow.sdk.h.i;
import com.intowow.sdk.j.h;
import org.json.JSONObject;

public class f
  implements k.b
{
  private k a = null;

  public f(k paramk)
  {
    this.a = paramk;
  }

  private void a(Bundle paramBundle)
  {
    try
    {
      String str = paramBundle.getString("event_type");
      paramBundle = new JSONObject(paramBundle.getString("event_props"));
      this.a.j().a(str, paramBundle);
      return;
    }
    catch (Exception paramBundle)
    {
    }
  }

  public void a(Message paramMessage)
  {
    try
    {
      paramMessage = paramMessage.getData();
      h.b localb = h.b.values()[paramMessage.getInt("type")];
      switch (a()[localb.ordinal()])
      {
      case 22:
        a(paramMessage);
        return;
      }
    }
    catch (Exception paramMessage)
    {
      h.a(paramMessage);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.g.f
 * JD-Core Version:    0.6.2
 */