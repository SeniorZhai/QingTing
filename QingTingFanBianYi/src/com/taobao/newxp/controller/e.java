package com.taobao.newxp.controller;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.net.MMEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class e
{
  private static final String b = e.class.getName();
  public ExchangeDataService a;
  private List<Promoter> c = new ArrayList();
  private Context d;
  private MMEntity e;
  private a f = a.b;
  private int g = -1;
  private XpListenersCenter.NTipsChangedListener h;
  private final boolean i = false;
  private boolean j = false;
  private int k;
  private int l;

  public e(Context paramContext, ExchangeDataService paramExchangeDataService, XpListenersCenter.NTipsChangedListener paramNTipsChangedListener)
  {
    this.d = paramContext;
    this.a = paramExchangeDataService;
    this.h = paramNTipsChangedListener;
    if (ExchangeConstants.PRELOAD_REPEAT_COUNT > 0);
    for (int m = ExchangeConstants.PRELOAD_REPEAT_COUNT; ; m = 1)
    {
      this.k = m;
      return;
    }
  }

  private void a(List<Promoter> paramList, boolean paramBoolean, int paramInt)
  {
    Log.a(b, "PreloadData setPromoters " + paramList.size());
    if (paramBoolean)
    {
      this.c.clear();
      this.c.addAll(paramList);
    }
    while (true)
    {
      this.l = paramInt;
      try
      {
        this.e = ((MMEntity)this.a.getEntity().clone());
        this.f = a.a;
        return;
        this.c = paramList;
      }
      catch (CloneNotSupportedException paramList)
      {
        while (true)
          Log.b(b, "", paramList);
      }
    }
  }

  public int a(String paramString1, String paramString2)
    throws Throwable
  {
    int n;
    if (a.b != this.f)
    {
      paramString1 = Promoter.class.getDeclaredField(paramString1);
      paramString1.setAccessible(true);
      Iterator localIterator = this.c.iterator();
      int m = 0;
      n = m;
      if (!localIterator.hasNext())
        break label99;
      Object localObject = paramString1.get((Promoter)localIterator.next());
      if ((localObject == null) || (TextUtils.isEmpty(paramString2)) || (!paramString2.equals(localObject.toString())))
        break label102;
      m += 1;
    }
    label99: label102: 
    while (true)
    {
      break;
      n = 0;
      return n;
    }
  }

  public List<Promoter> a()
  {
    Log.c(b, "PreloadData use preload data.. (used=" + this.l + ")");
    if ((this.f == a.a) || ((this.f == a.c) && ((this.l <= 0) || (this.l < this.k))))
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(this.c);
      this.l += 1;
      this.f = a.c;
      if ((this.h == null) || (this.g == -1));
      while (true)
      {
        Log.c(b, "  EXIST preload data  " + localArrayList.size());
        try
        {
          MMEntity localMMEntity = (MMEntity)this.e.clone();
          localMMEntity.extendFields(this.a.getEntity());
          this.a.setEntity(localMMEntity);
          return localArrayList;
          this.g = -1;
          this.h.onChanged(this.g);
          this.j = true;
          Log.c(b, "call new tips changed " + this.g);
        }
        catch (CloneNotSupportedException localCloneNotSupportedException)
        {
          while (true)
            Log.b(b, "", localCloneNotSupportedException);
        }
      }
    }
    return null;
  }

  protected void a(int paramInt)
  {
    Log.c(b, "PreloadData set tips " + paramInt);
    int m = this.g;
    this.g = paramInt;
    if ((this.h == null) || (this.j) || (this.g == m))
      return;
    this.h.onChanged(this.g);
    Log.c(b, "call new tips changed " + this.g);
  }

  public void a(XpListenersCenter.NTipsChangedListener paramNTipsChangedListener)
  {
    Log.a(b, " PreloadData reset ");
    this.h = paramNTipsChangedListener;
    this.f = a.b;
    this.c.clear();
    this.l = 0;
  }

  public void a(Class<? extends Promoter> paramClass)
  {
    try
    {
      Log.a(b, "PreloadData do init without data.");
      this.a.getEntity().clear();
      if (paramClass != null)
        this.a.setSpecificPromoterClz(paramClass);
      this.a.a(this.d, new XpListenersCenter.ExchangeDataRequestListener()
      {
        public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
        {
          String str = e.f();
          StringBuilder localStringBuilder = new StringBuilder().append("PreloadData do init without data end..");
          if (paramAnonymousList == null);
          for (paramAnonymousInt = 0; ; paramAnonymousInt = paramAnonymousList.size())
          {
            Log.a(str, paramAnonymousInt);
            if ((paramAnonymousList != null) && (paramAnonymousList.size() >= 1))
              break;
            e.a(e.this, e.a.b);
            return;
          }
          e.this.a(e.this.a.getEntity().newTips);
          e.a(e.this, paramAnonymousList, true, 0);
        }
      });
      return;
    }
    finally
    {
    }
    throw paramClass;
  }

  public void a(Collection<Promoter> paramCollection)
  {
    if ((paramCollection != null) && (paramCollection.size() > 0))
      this.c.addAll(paramCollection);
  }

  public void a(List<Promoter> paramList)
  {
    try
    {
      a(paramList, 0);
      return;
    }
    finally
    {
      paramList = finally;
    }
    throw paramList;
  }

  public void a(List<Promoter> paramList, int paramInt)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder().append("PreloadData do init with data.");
      int m;
      if (paramList == null)
      {
        m = 0;
        Log.a(m);
        if ((paramList != null) && (paramList.size() >= 1))
          break label69;
        this.f = a.b;
      }
      while (true)
      {
        return;
        m = paramList.size();
        break;
        label69: this.g = -1;
        a(paramList, true, paramInt);
        a(this.a.getEntity().newTips);
        Log.c(b, "init preload data with promoters...");
      }
    }
    finally
    {
    }
    throw paramList;
  }

  public void b(int paramInt)
  {
    if (b())
    {
      this.k += paramInt;
      return;
    }
    this.l = this.k;
    this.k += paramInt;
  }

  public boolean b()
  {
    return (a.b != this.f) && ((this.l <= 0) || (this.l < this.k));
  }

  public void c()
  {
    this.l = this.k;
  }

  public void c(int paramInt)
  {
    if (paramInt > 0);
    while (true)
    {
      this.k = paramInt;
      return;
      paramInt = 1;
    }
  }

  public e d()
  {
    this.c.clear();
    if (this.e != null)
      this.e.clear();
    this.f = a.b;
    this.g = -1;
    this.l = 0;
    if (ExchangeConstants.PRELOAD_REPEAT_COUNT > 0);
    for (int m = ExchangeConstants.PRELOAD_REPEAT_COUNT; ; m = 1)
    {
      this.k = m;
      return this;
    }
  }

  public int e()
  {
    return this.k;
  }

  public String toString()
  {
    Object localObject = new StringBuilder().append("PreloadData [cacheData=");
    int m;
    StringBuilder localStringBuilder;
    if (this.c == null)
    {
      m = 0;
      localStringBuilder = ((StringBuilder)localObject).append(m).append(", status=").append(this.f).append(", used=").append(this.l).append(", repeatcount=").append(this.k).append(" ,NTipsChangedListener=");
      if (this.h != null)
        break label113;
    }
    label113: for (localObject = "null"; ; localObject = "existed")
    {
      return (String)localObject + "]";
      m = this.c.size();
      break;
    }
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.e
 * JD-Core Version:    0.6.2
 */