package com.taobao.newxp.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager.BadTokenException;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.Promoter.a;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.Category;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.a.a.f.a;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.TabsDiskCache;
import com.taobao.newxp.controller.XpListenersCenter.AdapterListener;
import com.taobao.newxp.controller.XpListenersCenter.EntryOnClickListener;
import com.taobao.newxp.controller.XpListenersCenter.ExchangeDataRequestListener;
import com.taobao.newxp.controller.XpListenersCenter.FloatDialogListener;
import com.taobao.newxp.controller.XpListenersCenter.NTipsChangedListener;
import com.taobao.newxp.controller.XpListenersCenter.WelcomeAdsListener;
import com.taobao.newxp.controller.XpListenersCenter.onHandleVisListener;
import com.taobao.newxp.net.MMEntity;
import com.taobao.newxp.net.s;
import com.taobao.newxp.net.s.a;
import com.taobao.newxp.net.s.b;
import com.taobao.newxp.view.common.UMBrowser;
import com.taobao.newxp.view.container.GridTemplate;
import com.taobao.newxp.view.container.GridTemplateConfig;
import com.taobao.newxp.view.handler.UMHandleRelativeLayout;
import com.taobao.newxp.view.handler.umwall.AlimamaWall;
import com.taobao.newxp.view.largeimage.LargeGallery;
import com.taobao.newxp.view.largeimage.LargeGallery.a;
import com.taobao.newxp.view.largeimage.LargeGalleryConfig;
import com.taobao.newxp.view.welcome.UMWelcomeDialog;
import com.taobao.newxp.view.welcome.UMWelcomePromoter;
import com.taobao.newxp.view.welcome.WelcomeView;
import com.taobao.newxp.view.widget.SwipeViewPointer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeViewManager
{
  Context a;
  int b;
  XpListenersCenter.EntryOnClickListener c;
  private final String d = ExchangeViewManager.class.getName();
  private ExHeader e = null;
  private final Map<String, d> f = new HashMap();
  private ExchangeDataService g;
  private UMBrowser h;
  private ImageView i;
  private View j;
  private View k;
  private TextView l;
  private TextView m;
  private ImageView n;
  private volatile boolean o = false;

  public ExchangeViewManager(Context paramContext)
  {
    this(paramContext, new ExchangeDataService());
  }

  public ExchangeViewManager(Context paramContext, ExchangeDataService paramExchangeDataService)
  {
    this.a = paramContext;
    if (paramExchangeDataService == null);
    for (this.g = new ExchangeDataService(); ; this.g = paramExchangeDataService)
    {
      this.g.getEntity().layoutType = 7;
      return;
    }
  }

  private <T extends d> T getFeatureConfig(Class<T> paramClass)
  {
    paramClass = paramClass.getSimpleName();
    if (this.f.containsKey(paramClass))
    {
      com.taobao.newxp.common.Log.a(this.d, "has exist config " + paramClass);
      return (d)this.f.get(paramClass);
    }
    return null;
  }

  private void matchHandlerList(final XpListenersCenter.NTipsChangedListener paramNTipsChangedListener, final XpListenersCenter.onHandleVisListener paramonHandleVisListener, final Drawable paramDrawable)
  {
    // Byte code:
    //   0: aload_3
    //   1: ifnonnull +58 -> 59
    //   4: aload_0
    //   5: getfield 130	com/taobao/newxp/view/ExchangeViewManager:k	Landroid/view/View;
    //   8: iconst_4
    //   9: invokevirtual 188	android/view/View:setVisibility	(I)V
    //   12: aload_0
    //   13: getfield 130	com/taobao/newxp/view/ExchangeViewManager:k	Landroid/view/View;
    //   16: iconst_0
    //   17: invokevirtual 192	android/view/View:setClickable	(Z)V
    //   20: aload_0
    //   21: getfield 95	com/taobao/newxp/view/ExchangeViewManager:g	Lcom/taobao/newxp/controller/ExchangeDataService;
    //   24: invokevirtual 99	com/taobao/newxp/controller/ExchangeDataService:getEntity	()Lcom/taobao/newxp/net/MMEntity;
    //   27: astore_3
    //   28: aload_0
    //   29: getfield 171	com/taobao/newxp/view/ExchangeViewManager:i	Landroid/widget/ImageView;
    //   32: aload_0
    //   33: getfield 124	com/taobao/newxp/view/ExchangeViewManager:n	Landroid/widget/ImageView;
    //   36: aload_0
    //   37: getfield 93	com/taobao/newxp/view/ExchangeViewManager:a	Landroid/content/Context;
    //   40: aload_0
    //   41: getfield 95	com/taobao/newxp/view/ExchangeViewManager:g	Lcom/taobao/newxp/controller/ExchangeDataService;
    //   44: new 22	com/taobao/newxp/view/ExchangeViewManager$6
    //   47: dup
    //   48: aload_0
    //   49: aload_3
    //   50: aload_2
    //   51: aload_1
    //   52: invokespecial 195	com/taobao/newxp/view/ExchangeViewManager$6:<init>	(Lcom/taobao/newxp/view/ExchangeViewManager;Lcom/taobao/newxp/net/MMEntity;Lcom/taobao/newxp/controller/XpListenersCenter$onHandleVisListener;Lcom/taobao/newxp/controller/XpListenersCenter$NTipsChangedListener;)V
    //   55: invokestatic 200	com/taobao/newxp/common/b/e:a	(Landroid/widget/ImageView;Landroid/widget/ImageView;Landroid/content/Context;Lcom/taobao/newxp/controller/ExchangeDataService;Lcom/taobao/newxp/common/b/e$a;)V
    //   58: return
    //   59: aload_0
    //   60: getfield 171	com/taobao/newxp/view/ExchangeViewManager:i	Landroid/widget/ImageView;
    //   63: aload_3
    //   64: invokevirtual 206	android/widget/ImageView:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   67: new 28	com/taobao/newxp/view/ExchangeViewManager$7
    //   70: dup
    //   71: aload_0
    //   72: invokespecial 208	com/taobao/newxp/view/ExchangeViewManager$7:<init>	(Lcom/taobao/newxp/view/ExchangeViewManager;)V
    //   75: astore_1
    //   76: aload_0
    //   77: getfield 130	com/taobao/newxp/view/ExchangeViewManager:k	Landroid/view/View;
    //   80: instanceof 210
    //   83: ifeq +15 -> 98
    //   86: aload_0
    //   87: getfield 130	com/taobao/newxp/view/ExchangeViewManager:k	Landroid/view/View;
    //   90: checkcast 210	com/taobao/newxp/view/handler/UMHandleRelativeLayout
    //   93: aload_1
    //   94: invokevirtual 214	com/taobao/newxp/view/handler/UMHandleRelativeLayout:setClickRunnable	(Ljava/lang/Runnable;)V
    //   97: return
    //   98: aload_0
    //   99: getfield 130	com/taobao/newxp/view/ExchangeViewManager:k	Landroid/view/View;
    //   102: new 30	com/taobao/newxp/view/ExchangeViewManager$8
    //   105: dup
    //   106: aload_0
    //   107: aload_1
    //   108: invokespecial 217	com/taobao/newxp/view/ExchangeViewManager$8:<init>	(Lcom/taobao/newxp/view/ExchangeViewManager;Ljava/lang/Runnable;)V
    //   111: invokevirtual 221	android/view/View:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   114: return
  }

  private void pushMsgDialog(final Promoter paramPromoter, final XpListenersCenter.FloatDialogListener paramFloatDialogListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
    localBuilder.setMessage(paramPromoter.title);
    localBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        String str = Uri.parse(paramPromoter.url).getScheme();
        paramAnonymousDialogInterface.dismiss();
        if ((paramFloatDialogListener != null) && (str.equalsIgnoreCase(Promoter.a.b.toString())))
        {
          paramFloatDialogListener.onConfirmClickWithCallBackUrl(paramPromoter.url);
          new s.a(ExchangeViewManager.f(ExchangeViewManager.this).getEntity()).a(2).b(0).c(3).a(new Promoter[] { paramPromoter }).a().a();
          return;
        }
        com.taobao.newxp.controller.b.a(paramPromoter, ExchangeViewManager.this.a, ExchangeViewManager.f(ExchangeViewManager.this), false);
      }
    });
    localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    try
    {
      localBuilder.create().show();
      new s.a(this.g.getEntity()).a(0).b(0).c(0).a(new Promoter[] { paramPromoter }).a().a();
      return;
    }
    catch (WindowManager.BadTokenException paramPromoter)
    {
      com.taobao.newxp.common.Log.e(this.d, "Can`t show dialog ", paramPromoter);
    }
  }

  private void reportAclick()
  {
    if (7 == this.g.getEntity().layoutType)
      new s.b(this.g.getEntity()).a(15).b(0).c(3).a().a();
  }

  private void reportApv()
  {
    new s.b(this.g.getEntity()).a(14).b(0).c(3).a().a();
  }

  private void showFirst(UMWelcomePromoter paramUMWelcomePromoter, final XpListenersCenter.WelcomeAdsListener paramWelcomeAdsListener)
  {
    final UMWelcomeDialog localUMWelcomeDialog = new UMWelcomeDialog(this.a);
    WelcomeView local4 = new WelcomeView(this.a)
    {
      public void onCountdown(int paramAnonymousInt, View paramAnonymousView1, View paramAnonymousView2)
      {
        super.onCountdown(paramAnonymousInt, paramAnonymousView1, paramAnonymousView2);
        if (paramWelcomeAdsListener != null)
          paramWelcomeAdsListener.onCountdown(paramAnonymousInt);
      }

      public void onError(String paramAnonymousString)
      {
        super.onError(paramAnonymousString);
        try
        {
          if (localUMWelcomeDialog.isShowing())
            localUMWelcomeDialog.dismiss();
          if (paramWelcomeAdsListener != null)
          {
            paramWelcomeAdsListener.onError(paramAnonymousString);
            paramWelcomeAdsListener.onFinish();
          }
          return;
        }
        catch (WindowManager.BadTokenException localBadTokenException)
        {
          while (true)
            android.util.Log.e(ExchangeViewManager.d(ExchangeViewManager.this), "can`t open welcome ads,the parent activity has finished.", localBadTokenException);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          while (true)
            android.util.Log.e(ExchangeViewManager.d(ExchangeViewManager.this), "can`t open welcome ads,the parent activity has finished.", localIllegalArgumentException);
        }
      }

      public void onFinish(WelcomeView paramAnonymousWelcomeView)
      {
        try
        {
          if (localUMWelcomeDialog.isShowing())
            localUMWelcomeDialog.dismiss();
          super.onFinish(paramAnonymousWelcomeView);
          if (paramWelcomeAdsListener != null)
            paramWelcomeAdsListener.onFinish();
          return;
        }
        catch (WindowManager.BadTokenException localBadTokenException)
        {
          while (true)
            android.util.Log.e(ExchangeViewManager.d(ExchangeViewManager.this), "can`t open welcome ads,the parent activity has finished.", localBadTokenException);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          while (true)
            android.util.Log.e(ExchangeViewManager.d(ExchangeViewManager.this), "can`t open welcome ads,the parent activity has finished.", localIllegalArgumentException);
        }
      }
    };
    local4.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    if (local4.loaded(paramUMWelcomePromoter))
    {
      localUMWelcomeDialog.setContentView(local4);
      try
      {
        localUMWelcomeDialog.show();
        this.g.reportImpression(new Promoter[] { paramUMWelcomePromoter });
        if (paramWelcomeAdsListener != null)
          paramWelcomeAdsListener.onShow(local4);
        return;
      }
      catch (WindowManager.BadTokenException localBadTokenException)
      {
        while (true)
          android.util.Log.e(this.d, "can`t open welcome ads,the parent activity has finished.", localBadTokenException);
      }
    }
    paramWelcomeAdsListener.onError("the promoter is failed");
    paramWelcomeAdsListener.onFinish();
  }

  private void welcomeDataReceived(final XpListenersCenter.WelcomeAdsListener paramWelcomeAdsListener, long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean, final List<Promoter> paramList)
  {
    Handler localHandler = new Handler();
    this.o = true;
    paramLong3 = System.currentTimeMillis() - paramLong3;
    if ((paramList == null) || (paramList.size() < 1))
      if (paramWelcomeAdsListener != null)
      {
        if (paramLong3 < paramLong1)
          localHandler.postDelayed(new Runnable()
          {
            public void run()
            {
              paramWelcomeAdsListener.onDataReviced(null);
              paramWelcomeAdsListener.onFinish();
            }
          }
          , paramLong1 - paramLong3);
      }
      else
        android.util.Log.w(ExchangeConstants.LOG_TAG, "unshow welcome dialog,there is no promoter data.");
    do
    {
      return;
      paramWelcomeAdsListener.onDataReviced(null);
      paramWelcomeAdsListener.onFinish();
      break;
      paramList = (UMWelcomePromoter)paramList.get(0);
      if (paramWelcomeAdsListener != null)
        paramWelcomeAdsListener.onDataReviced(paramList);
      if (paramBoolean)
      {
        showFirst(paramList, paramWelcomeAdsListener);
        return;
      }
      if (paramLong3 < paramLong1)
      {
        localHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            ExchangeViewManager.a(ExchangeViewManager.this, paramList, paramWelcomeAdsListener);
          }
        }
        , paramLong1 - paramLong3);
        return;
      }
      if (paramLong3 < paramLong2)
      {
        showFirst(paramList, paramWelcomeAdsListener);
        return;
      }
      android.util.Log.w(ExchangeConstants.LOG_TAG, "Load outdated..");
    }
    while (paramWelcomeAdsListener == null);
    paramWelcomeAdsListener.onError("Load outdated..");
    paramWelcomeAdsListener.onFinish();
  }

  public void addView(int paramInt, View paramView, Object[] paramArrayOfObject)
  {
    Object localObject2 = null;
    if ((7 == paramInt) && (!AlimmContext.getAliContext().getAppUtils().a(AlimamaWall.class)))
    {
      Toast.makeText(AlimmContext.getAliContext().getAppContext(), "请确认是否在Manifest文件中已添加 ‘com.taobao.newxp.view.handler.umwall.UMWall’。", 1).show();
      return;
    }
    while (true)
    {
      int i1;
      try
      {
        this.g.getEntity().layoutType = paramInt;
        int i2 = paramArrayOfObject.length;
        i1 = 0;
        localObject1 = null;
        if (i1 < i2)
        {
          localObject3 = paramArrayOfObject[i1];
          if ((localObject3 instanceof Drawable))
          {
            localObject3 = (Drawable)localObject3;
            localObject1 = localObject2;
            localObject2 = localObject3;
            break label489;
          }
          if (!(localObject3 instanceof XpListenersCenter.onHandleVisListener))
            break label477;
          localObject3 = (XpListenersCenter.onHandleVisListener)localObject3;
          localObject2 = localObject1;
          localObject1 = localObject3;
          break label489;
        }
        if (paramView != null)
          break label265;
        paramView = AlimmContext.getAliContext().getAppUtils();
        if ((paramView.e("android.permission.ACCESS_NETWORK_STATE")) && (!paramView.c()))
        {
          Toast.makeText(this.a, this.a.getResources().getString(com.taobao.newxp.a.e.a(this.a)), 1).show();
          return;
        }
      }
      catch (Exception paramView)
      {
        android.util.Log.e(ExchangeConstants.LOG_TAG, "添加推广样式失败！", paramView);
        return;
      }
      switch (paramInt)
      {
      default:
        return;
      case 7:
      }
      this.g.getEntity().template = com.taobao.newxp.b.a;
      paramView = this.g.getEntity();
      paramArrayOfObject = this.g.getPreloadData();
      com.taobao.newxp.view.handler.b.a(this.a, paramView, paramArrayOfObject, null);
      return;
      label265: if ((paramView instanceof ImageView))
      {
        this.i = ((ImageView)paramView);
        this.k = paramView;
      }
      while (true)
      {
        paramView = new XpListenersCenter.NTipsChangedListener()
        {
          public void onChanged(int paramAnonymousInt)
          {
            if (paramAnonymousInt == 0);
            try
            {
              ExchangeViewManager.a(ExchangeViewManager.this).setVisibility(0);
              ExchangeViewManager.b(ExchangeViewManager.this).setImageResource(com.taobao.newxp.a.b.c(ExchangeViewManager.this.a));
              ExchangeViewManager.c(ExchangeViewManager.this).setText("");
              ExchangeViewManager.c(ExchangeViewManager.this).setBackgroundDrawable(null);
              return;
              if (paramAnonymousInt > 0)
              {
                ExchangeViewManager.b(ExchangeViewManager.this).setImageDrawable(null);
                ExchangeViewManager.c(ExchangeViewManager.this).setBackgroundResource(com.taobao.newxp.a.b.d(ExchangeViewManager.this.a));
                ExchangeViewManager.c(ExchangeViewManager.this).setText("" + paramAnonymousInt);
                ExchangeViewManager.a(ExchangeViewManager.this).setVisibility(0);
                return;
              }
            }
            catch (Exception localException)
            {
              com.taobao.newxp.common.Log.e(ExchangeViewManager.d(ExchangeViewManager.this), "", localException);
              return;
            }
            ExchangeViewManager.a(ExchangeViewManager.this).setVisibility(4);
          }
        };
        this.b = paramInt;
        switch (paramInt)
        {
        case 7:
          matchHandlerList(paramView, localObject2, localObject1);
          return;
          if ((paramView instanceof RelativeLayout))
          {
            this.k = paramView;
            this.i = ((ImageView)this.k.findViewById(com.taobao.newxp.common.b.c.a(this.a).b("imageview")));
            this.j = this.k.findViewById(com.taobao.newxp.common.b.c.a(this.a).b("newtip_area"));
            this.l = ((TextView)this.k.findViewById(com.taobao.newxp.common.b.c.a(this.a).b("newtip_tv")));
            this.n = ((ImageView)this.k.findViewById(com.taobao.newxp.common.b.c.a(this.a).b("newtip_iv")));
            this.m = ((TextView)this.k.findViewById(com.taobao.newxp.common.b.c.a(this.a).b("textview")));
          }
          break;
        }
      }
      label477: Object localObject3 = localObject2;
      localObject2 = localObject1;
      Object localObject1 = localObject3;
      label489: i1 += 1;
      localObject3 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject3;
    }
  }

  public void addView(ViewGroup paramViewGroup, int paramInt, String[] paramArrayOfString)
  {
    this.g.getEntity().layoutType = paramInt;
    this.b = paramInt;
    if (paramArrayOfString != null);
    try
    {
      if ((paramArrayOfString.length > 0) && (paramArrayOfString.length >= 1))
        ExchangeConstants.CHANNEL = paramArrayOfString[0];
      if ((ExchangeConstants.ONLY_CHINESE) && (!AlimmContext.getAliContext().getAppUtils().e()))
      {
        com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, "Only chinese language os can show ads");
        return;
      }
      if (this.b == 8)
      {
        paramArrayOfString = (GridTemplateConfig)getFeatureConfig(GridTemplateConfig.class);
        paramViewGroup.addView(new GridTemplate(null, this.g, this.a, paramArrayOfString).contentView);
        return;
      }
    }
    catch (Exception paramViewGroup)
    {
      com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, "add view error " + paramViewGroup.getMessage(), paramViewGroup);
      return;
    }
    switch (this.b)
    {
    case 43:
      com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, String.format("Paramter type  %1$s cannot be handled. It may be deprecated.", new Object[] { Integer.valueOf(this.b) }));
      return;
      paramArrayOfString = (LargeGalleryConfig)getFeatureConfig(LargeGalleryConfig.class);
      if (paramArrayOfString == null)
        paramArrayOfString = new LargeGalleryConfig();
      break;
    case 13:
    }
    while (true)
    {
      Object localObject = (LayoutInflater)this.a.getSystemService("layout_inflater");
      com.taobao.newxp.common.b.c localc = com.taobao.newxp.common.b.c.a(this.a);
      localObject = (ViewGroup)((LayoutInflater)localObject).inflate(com.taobao.newxp.a.d.e(this.a), null);
      paramArrayOfString.setParent((View)localObject);
      LargeGallery localLargeGallery = (LargeGallery)((ViewGroup)localObject).findViewById(localc.b("taobao_xp_gallery"));
      SwipeViewPointer localSwipeViewPointer = (SwipeViewPointer)((ViewGroup)localObject).findViewById(localc.b("taobao_xp_gallery_pointer"));
      localLargeGallery.setLoadListener(new LargeGallery.a()
      {
        public void a()
        {
          this.a.setVisibility(4);
          this.b.setVisibility(0);
          this.c.setVisibility(8);
        }

        public void b()
        {
          this.a.setVisibility(4);
          this.b.setVisibility(4);
          this.c.setVisibility(0);
        }

        public void c()
        {
          this.a.setVisibility(0);
          this.b.setVisibility(8);
          this.c.setVisibility(8);
        }
      });
      localLargeGallery.work(this.g, paramArrayOfString);
      localLargeGallery.setForefathers(paramViewGroup);
      paramViewGroup.addView((View)localObject, -1, -1);
      localLargeGallery.setPageControl(localSwipeViewPointer);
      return;
      new com.taobao.newxp.view.text.b(this.a, paramViewGroup, 0, this.g);
      return;
      break;
    }
  }

  public void addView(ViewGroup paramViewGroup, ListView paramListView)
  {
    addView(paramViewGroup, paramListView, null);
  }

  public void addView(ViewGroup paramViewGroup, ListView paramListView, XpListenersCenter.AdapterListener paramAdapterListener)
  {
    this.g.getEntity().layoutType = 8;
    GridTemplateConfig localGridTemplateConfig = (GridTemplateConfig)getFeatureConfig(GridTemplateConfig.class);
    new com.taobao.newxp.view.container.a(this.a, paramViewGroup, paramListView, this.g, paramAdapterListener, null, localGridTemplateConfig, this.e);
  }

  public void addWelcomeAds(String paramString, final XpListenersCenter.WelcomeAdsListener paramWelcomeAdsListener, final long paramLong1, long paramLong2)
  {
    final long l1 = System.currentTimeMillis();
    this.o = false;
    this.g = new ExchangeDataService(paramString);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        int i = 0;
        List localList;
        String str;
        StringBuilder localStringBuilder;
        if (!ExchangeViewManager.l(ExchangeViewManager.this))
        {
          ExchangeViewManager.a(ExchangeViewManager.this, true);
          localList = ExchangeViewManager.f(ExchangeViewManager.this).requestCache(true, false);
          str = ExchangeViewManager.d(ExchangeViewManager.this);
          localStringBuilder = new StringBuilder().append("timeout,request data from cache.");
          if (localList != null)
            break label99;
        }
        while (true)
        {
          com.taobao.newxp.common.Log.a(str, i);
          ExchangeViewManager.a(ExchangeViewManager.this, paramWelcomeAdsListener, paramLong1, l1, this.d, true, localList);
          return;
          label99: i = localList.size();
        }
      }
    }
    , paramLong2);
    this.g.cacheLiveData = true;
    this.g.setSpecificPromoterClz(UMWelcomePromoter.class);
    this.g.requestRichImageDataAsyn(this.a, 9, new XpListenersCenter.ExchangeDataRequestListener()
    {
      public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
      {
        if (ExchangeViewManager.l(ExchangeViewManager.this));
        while (1 != paramAnonymousInt)
          return;
        String str = ExchangeViewManager.d(ExchangeViewManager.this);
        StringBuilder localStringBuilder = new StringBuilder().append("request data from network.");
        if (paramAnonymousList == null);
        for (paramAnonymousInt = 0; ; paramAnonymousInt = paramAnonymousList.size())
        {
          com.taobao.newxp.common.Log.a(str, paramAnonymousInt);
          ExchangeViewManager.a(ExchangeViewManager.this, paramWelcomeAdsListener, paramLong1, l1, this.d, false, paramAnonymousList);
          return;
        }
      }
    }
    , true);
  }

  public void destroy()
  {
    if ((this.h != null) && (this.h.isShowing()))
    {
      this.h.dismiss();
      this.h = null;
    }
  }

  public ExchangeViewManager setEntryOnClickListener(XpListenersCenter.EntryOnClickListener paramEntryOnClickListener)
  {
    if ((this.c != null) && (this.c != paramEntryOnClickListener))
    {
      com.taobao.newxp.common.Log.e(this.d, "EntryOnClickListener is exist, and make old listener invalid...");
      this.c = paramEntryOnClickListener;
    }
    while (this.c != null)
      return this;
    com.taobao.newxp.common.Log.a(this.d, "EntryOnClickListener set up...");
    this.c = paramEntryOnClickListener;
    return this;
  }

  public void setFeatureConfig(d paramd)
  {
    String str = paramd.getClass().getSimpleName();
    if (this.f.containsKey(str))
      com.taobao.newxp.common.Log.e(this.d, "replace exchange feature config [" + str + "]");
    this.f.put(str, paramd);
  }

  public void setListHeader(int paramInt, ExchangeDataService paramExchangeDataService)
  {
    if (paramExchangeDataService.getEntity().layoutType == -1)
      paramExchangeDataService.getEntity().layoutType = 43;
    this.e = new ExHeader(paramExchangeDataService, paramInt);
  }

  public void setListHeader(ExHeader paramExHeader)
  {
    this.e = paramExHeader;
  }

  public void setLoopInterval(int paramInt)
  {
    if (paramInt > 3000)
    {
      ExchangeConstants.REFRESH_INTERVAL = paramInt;
      ExchangeConstants.IGNORE_SERVER_INTERVAL = true;
    }
  }

  class a
    implements Runnable
  {
    a()
    {
    }

    public void run()
    {
      ExchangeViewManager.k(ExchangeViewManager.this);
      if (ExchangeViewManager.this.c != null)
        ExchangeViewManager.this.c.onClick(ExchangeViewManager.h(ExchangeViewManager.this));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.ExchangeViewManager
 * JD-Core Version:    0.6.2
 */