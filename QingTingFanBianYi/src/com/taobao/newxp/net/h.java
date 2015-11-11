package com.taobao.newxp.net;

import android.content.Context;
import com.taobao.munion.base.download.c;
import com.taobao.newxp.common.ExchangeConstants;

public class h extends c
{
  public h(Context paramContext, String paramString)
  {
    super(paramContext, "tb_munion", paramString);
    if (ExchangeConstants.RICH_NOTIFICATION)
    {
      a(true);
      return;
    }
    a(false);
  }

  public h a(s.a parama)
  {
    c(new String[] { parama.a(7).a().f() });
    a(new String[] { parama.a(1).a().f() });
    b(new String[] { parama.a(-2).a().f() });
    e(new String[] { parama.a(-2).a().f() });
    c(i.class.getName());
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.h
 * JD-Core Version:    0.6.2
 */