package com.taobao.newxp.controller;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.taobao.munion.base.a;
import com.taobao.munion.base.download.c;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.Promoter.a;
import com.taobao.newxp.a.d;
import com.taobao.newxp.a.e;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.Category;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.net.MMEntity;
import com.taobao.newxp.net.h;
import com.taobao.newxp.net.s;
import com.taobao.newxp.net.s.a;
import com.taobao.newxp.view.UMDetail;
import com.taobao.newxp.view.common.DownloadDialog;
import com.taobao.newxp.view.common.DownloadDialog.a;
import com.taobao.newxp.view.common.UMBrowser;
import java.util.ArrayList;
import java.util.List;

public class b
{
  private static final String a = b.class.getName();
  private static List<Category> b = new ArrayList();

  private static void a(Context paramContext, MMEntity paramMMEntity, Promoter paramPromoter)
  {
    if (!TextUtils.isEmpty(paramPromoter.url))
    {
      c(paramContext, paramMMEntity, paramPromoter, 0);
      return;
    }
    Toast.makeText(paramContext, "抱歉，开打页面失败了！", 0).show();
  }

  private static void a(Context paramContext, MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    paramMMEntity = new s.a(paramMMEntity).a(7).b(paramInt).c(3).a(new Promoter[] { paramPromoter });
    new h(paramContext, paramPromoter.url).a(paramMMEntity).d(paramPromoter.title).a();
  }

  public static void a(Promoter paramPromoter, Context paramContext, ExchangeDataService paramExchangeDataService, boolean paramBoolean)
  {
    a(new a.a(paramPromoter, 0), paramContext, paramExchangeDataService, paramBoolean, 0);
  }

  public static void a(a.a parama, Context paramContext, ExchangeDataService paramExchangeDataService, boolean paramBoolean, int paramInt)
  {
    Promoter localPromoter = parama.a;
    try
    {
      paramExchangeDataService = (MMEntity)paramExchangeDataService.b.clone();
      if (AlimmContext.getAliContext().getAppUtils().c(localPromoter.app_package_name))
      {
        d(paramContext, paramExchangeDataService, localPromoter, paramInt);
        return;
      }
    }
    catch (CloneNotSupportedException paramExchangeDataService)
    {
      while (true)
        paramExchangeDataService = null;
      String str = Uri.parse(localPromoter.url).getScheme();
      if ((str != null) && (str.equalsIgnoreCase(Promoter.a.a.toString())))
      {
        a(parama, paramContext, paramExchangeDataService, paramInt);
        return;
      }
      int j = localPromoter.landing_type;
      int i = j;
      if (paramBoolean)
      {
        i = j;
        if (j == 0)
          i = 1;
      }
      switch (i)
      {
      case 5:
      default:
        return;
      case 0:
      case 2:
      case 4:
      case 3:
      case 1:
      }
    }
    b(parama, paramContext, paramExchangeDataService, paramInt);
    return;
    c(paramContext, paramExchangeDataService, localPromoter, paramInt);
    return;
    b(paramContext, paramExchangeDataService, localPromoter, paramInt);
    return;
    a(paramContext, paramExchangeDataService, localPromoter, paramInt);
  }

  private static void a(a.a parama, Context paramContext, MMEntity paramMMEntity)
  {
    int i = paramContext.getResources().getIdentifier("taobao_xp_dialog_download_window", "style", paramContext.getPackageName());
    new DownloadDialog(paramContext, parama.a, parama.b, paramMMEntity, i, DownloadDialog.a.a).show();
  }

  private static void a(a.a parama, Context paramContext, MMEntity paramMMEntity, int paramInt)
  {
    new s.a(paramMMEntity).a(2).b(paramInt).c(3).a(new Promoter[] { parama.a }).a().a();
    paramInt = paramContext.getResources().getIdentifier("taobao_xp_dialog_download_window", "style", paramContext.getPackageName());
    new DownloadDialog(paramContext, parama.a, parama.b, paramMMEntity, paramInt, DownloadDialog.a.b).show();
  }

  private static void b(Context paramContext, MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    new s.a(paramMMEntity).a(2).b(paramInt).c(3).a(new Promoter[] { paramPromoter }).a().a();
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramPromoter.url)));
      return;
    }
    catch (ActivityNotFoundException paramMMEntity)
    {
      Log.b(ExchangeConstants.LOG_TAG, paramMMEntity.toString());
      Toast.makeText(paramContext, paramContext.getString(e.k(paramContext)), 0).show();
    }
  }

  private static void b(a.a parama, Context paramContext, MMEntity paramMMEntity, int paramInt)
  {
    Promoter localPromoter = parama.a;
    if (1 > d.d(paramContext))
    {
      Log.e(ExchangeConstants.LOG_TAG, "no resource open download dialog.");
      a(paramContext, paramMMEntity, localPromoter, paramInt);
      return;
    }
    int i = paramMMEntity.layoutType;
    new s.a(paramMMEntity).a(2).b(paramInt).c(3).a(new Promoter[] { localPromoter }).a().a();
    if (ExchangeConstants.DETAIL_PAGE)
      try
      {
        Intent localIntent = new Intent(paramContext, UMDetail.class);
        localIntent.putExtra("promoter", localPromoter);
        localIntent.setFlags(268435456);
        localIntent.putExtra("action_index", parama.b);
        localIntent.putExtra("entity", paramMMEntity);
        paramContext.startActivity(localIntent);
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.b(ExchangeConstants.LOG_TAG, "", localActivityNotFoundException);
        a(parama, paramContext, paramMMEntity);
        return;
      }
    a(parama, paramContext, paramMMEntity);
  }

  private static void c(Context paramContext, MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    if (1 > d.c(paramContext))
    {
      Log.e(ExchangeConstants.LOG_TAG, "no resource open native webview,and then open with system browser.");
      b(paramContext, paramMMEntity, paramPromoter, paramInt);
      return;
    }
    new s.a(paramMMEntity).a(2).b(paramInt).c(3).a(new Promoter[] { paramPromoter }).a().a();
    new UMBrowser(paramContext).setInterceptBack(ExchangeConstants.BROWSER_GRADUAL_BACK).loadAndShow(paramPromoter.url);
  }

  private static void d(Context paramContext, MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    new s.a(paramMMEntity).a(5).b(paramInt).c(0).a(new Promoter[] { paramPromoter }).a().a();
    if (!com.taobao.newxp.common.b.b.c(paramPromoter.url_in_app))
    {
      com.taobao.newxp.common.b.b.b(paramContext, paramPromoter.url_in_app);
      return;
    }
    com.taobao.newxp.common.b.b.a(paramContext, paramPromoter.app_package_name);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.b
 * JD-Core Version:    0.6.2
 */