package com.intowow.sdk.j;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.intowow.sdk.a.i;
import com.intowow.sdk.a.i.a;
import com.intowow.sdk.a.i.b;
import com.intowow.sdk.a.i.c;
import com.intowow.sdk.b.k;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.b.k.a.a;
import com.intowow.sdk.d.a;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.c.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class e
{
  public static List<ADProfile.c.a> a(i parami, int paramInt)
  {
    if (parami == null)
      return null;
    Map localMap = parami.h;
    if (paramInt == 2);
    for (parami = i.b.b.toString(); ; parami = i.b.a.toString())
    {
      parami = (i.c)localMap.get(parami);
      if (parami == null)
        break;
      return parami.b;
    }
    return null;
  }

  public static boolean a(k.a.a parama, k paramk, i parami)
  {
    if ((parama == k.a.a.b) || (parami == null))
      return false;
    parama = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    parama = paramk.b().registerReceiver(null, parama);
    Iterator localIterator = parami.d.iterator();
    label263: 
    while (true)
    {
      if (!localIterator.hasNext())
        return false;
      i.a locala = (i.a)localIterator.next();
      switch (a()[locala.ordinal()])
      {
      case 3:
      default:
        break;
      case 1:
        if ((com.intowow.sdk.a.e.a) && (paramk.m() != null))
          paramk.m().a("Disable background for constraint : DISABLED");
        return true;
      case 2:
        if (paramk.c().a() != 2)
        {
          if ((com.intowow.sdk.a.e.a) && (paramk.m() != null))
            paramk.m().a("Disable background for constraint : WIFI_ONLY");
          return true;
        }
        break;
      case 4:
        int i = parama.getIntExtra("status", -1);
        if ((i != 2) && (i != 5));
        for (i = 0; ; i = 1)
        {
          if (i != 0)
            break label263;
          i = parama.getIntExtra("level", -1);
          int j = parama.getIntExtra("scale", -1);
          if ((int)Math.floor(i * 100.0F / j) >= parami.e)
            break;
          if ((com.intowow.sdk.a.e.a) && (paramk.m() != null))
            paramk.m().a("Disable background for constraint : HIGH_BATTERY_LEVEL");
          return true;
        }
      }
    }
  }

  public static boolean a(List<ADProfile.c.a> paramList, ADProfile paramADProfile)
  {
    if (paramList != null)
    {
      if (paramList.size() == 0)
        return true;
      return b.a(paramADProfile.p(), paramList);
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.e
 * JD-Core Version:    0.6.2
 */