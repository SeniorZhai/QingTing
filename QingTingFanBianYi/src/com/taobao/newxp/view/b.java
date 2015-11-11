package com.taobao.newxp.view;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.XpListenersCenter.AdapterListener;
import com.taobao.newxp.controller.XpListenersCenter.ExchangeDataRequestListener;
import com.taobao.newxp.controller.XpListenersCenter.ListClickListener;
import com.taobao.newxp.controller.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b
{
  private int a;
  private a b;
  private ListView c;
  private List<Promoter> d;
  private ExchangeDataService e;

  public b(ListView paramListView, final Context paramContext, int paramInt1, final ExHeader paramExHeader, List<Promoter> paramList, int paramInt2, ExchangeDataService paramExchangeDataService)
  {
    this.a = paramInt2;
    this.c = paramListView;
    this.e = paramExchangeDataService;
    this.d = new ArrayList();
    this.d.addAll(paramList);
    this.b = new a(paramContext, 17367045, this.d, paramInt1, this.a, this.e)
    {
      public void a(int paramAnonymousInt)
      {
        super.a(paramAnonymousInt);
        b.this.a(paramAnonymousInt);
      }
    };
    if (paramExHeader != null)
    {
      paramListView = paramExHeader.getExDataService().getPreloadData();
      if ((paramListView != null) && (paramListView.b()))
      {
        a(paramContext, paramExHeader, paramListView);
        a();
        return;
      }
      paramExHeader.preload(paramContext, new XpListenersCenter.ExchangeDataRequestListener()
      {
        public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
        {
          paramAnonymousList = paramExHeader.getExDataService().getPreloadData();
          if (paramAnonymousList != null)
            b.a(b.this, paramContext, paramExHeader, paramAnonymousList);
          b.this.a();
        }
      });
      return;
    }
    a();
  }

  private void a(Context paramContext, ExHeader paramExHeader, e parame)
  {
    try
    {
      if (parame.a("display_type", "1") > 0)
        paramExHeader.attachToList(paramContext, this.c);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w(ExchangeConstants.LOG_TAG, "can`t attach header,no match promoter.");
    }
  }

  public void a()
  {
    this.c.setAdapter(this.b);
    this.c.setItemsCanFocus(true);
  }

  public void a(int paramInt)
  {
  }

  public void a(XpListenersCenter.AdapterListener paramAdapterListener)
  {
    if (this.b != null)
      this.b.a(paramAdapterListener);
  }

  public void a(XpListenersCenter.ListClickListener paramListClickListener)
  {
    this.b.a = paramListClickListener;
  }

  public void a(List<Promoter> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Promoter localPromoter = (Promoter)paramList.next();
      if (localPromoter.display_type != 1)
      {
        this.d.add(localPromoter);
        this.b.notifyDataSetChanged();
      }
    }
  }

  public a b()
  {
    return this.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.b
 * JD-Core Version:    0.6.2
 */