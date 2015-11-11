package com.taobao.newxp.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.AbsListView.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.XpListenersCenter.ExchangeDataRequestListener;
import com.taobao.newxp.controller.e;
import java.util.List;

public class ExHeader
{
  private static final String a = ExHeader.class.getName();
  private ExchangeDataService b;
  private int c;
  private FrameLayout d;

  public ExHeader(ExchangeDataService paramExchangeDataService, int paramInt)
  {
    this.b = paramExchangeDataService;
    this.c = paramInt;
  }

  public final boolean attachToList(Context paramContext, ListView paramListView)
  {
    try
    {
      this.d = new FrameLayout(paramContext);
      AbsListView.LayoutParams localLayoutParams = new AbsListView.LayoutParams(-1, (int)(paramContext.getResources().getDisplayMetrics().density * this.c + 0.5F));
      this.d.setLayoutParams(localLayoutParams);
      paramContext = new ExchangeViewManager(paramContext, this.b);
      onAttchContent(this.d, paramContext);
      paramListView.addHeaderView(this.d);
      show();
      return true;
    }
    catch (Exception paramContext)
    {
      Log.w(a, "attch header failed.", paramContext);
    }
    return false;
  }

  public ExchangeDataService getExDataService()
  {
    return this.b;
  }

  public void hide()
  {
    if (this.d != null)
      this.d.setVisibility(8);
  }

  public boolean onAttchContent(FrameLayout paramFrameLayout, ExchangeViewManager paramExchangeViewManager)
    throws Exception
  {
    boolean bool = false;
    if (paramExchangeViewManager != null)
    {
      paramExchangeViewManager.addView(paramFrameLayout, 43, new String[0]);
      bool = true;
    }
    return bool;
  }

  public void preload(final Context paramContext, final XpListenersCenter.ExchangeDataRequestListener paramExchangeDataRequestListener)
  {
    e locale = this.b.getPreloadData();
    if ((locale != null) && (locale.b()))
    {
      if (paramExchangeDataRequestListener != null)
        paramExchangeDataRequestListener.dataReceived(1, locale.a());
      Log.w(a, "the ExchagneDataService has exist preload data.");
      return;
    }
    this.b.requestDataAsyn(paramContext, new XpListenersCenter.ExchangeDataRequestListener()
    {
      public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
      {
        Object localObject = ExHeader.a();
        StringBuilder localStringBuilder = new StringBuilder().append("the ExHeader has preload data.[");
        if (paramAnonymousList == null);
        for (int i = 0; ; i = paramAnonymousList.size())
        {
          Log.i((String)localObject, i + "]");
          localObject = new e(paramContext, ExHeader.a(ExHeader.this), null);
          ExHeader.a(ExHeader.this).setPreloadData((e)localObject);
          ((e)localObject).a(paramAnonymousList);
          if (paramExchangeDataRequestListener != null)
            paramExchangeDataRequestListener.dataReceived(paramAnonymousInt, paramAnonymousList);
          return;
        }
      }
    }
    , true);
  }

  public void show()
  {
    if (this.d != null)
      this.d.setVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.ExHeader
 * JD-Core Version:    0.6.2
 */