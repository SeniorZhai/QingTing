package com.taobao.newxp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.a.e;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.common.b.d.b;
import com.taobao.newxp.common.b.d.c;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.XpListenersCenter.ExchangeDataRequestListener;
import com.taobao.newxp.controller.b;
import com.taobao.newxp.net.MMEntity;
import com.taobao.newxp.net.h;
import com.taobao.newxp.net.s;
import com.taobao.newxp.net.s.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UMDetail extends Activity
{
  public static final String ENTITY_KEY = "entity";
  public static final String PROMOTER_KEY = "promoter";
  private static final String k = UMDetail.class.getName();
  HorizontalStrip a;
  View b;
  MMEntity c;
  Context d;
  Promoter e;
  int f;
  TextView g;
  volatile Map<String, Drawable> h = new HashMap();
  List<f.a> i;
  ExchangeDataService j;

  private void a(Promoter paramPromoter, int paramInt, ExchangeDataService paramExchangeDataService)
  {
    paramPromoter = Uri.parse(paramPromoter.url);
    if (!AlimmContext.getAliContext().getAppUtils().e("android.permission.CALL_PHONE"))
    {
      Toast.makeText(this.d, "This App has no call_phone permission!", 0).show();
      return;
    }
    paramPromoter = paramPromoter.getAuthority();
    paramPromoter = new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramPromoter));
    this.d.startActivity(paramPromoter);
  }

  private void b()
  {
    int m = ExchangeConstants.definePageLevel(this.c.layoutType);
    s.a locala = new s.a(this.c).a(3).b(this.f).c(m).a(new Promoter[] { this.e });
    new h(this.d, this.e.url).a(locala).d(this.e.title).a();
  }

  private void c()
  {
    if (this.j != null)
    {
      this.j.setFilterInstalledApp(false);
      this.j.getEntity().filterPromoter = this.e.promoter;
      final View localView = findViewById(com.taobao.newxp.a.c.ag(this.d));
      localView.setVisibility(8);
      this.j.requestDataAsyn(this.d, new XpListenersCenter.ExchangeDataRequestListener()
      {
        public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
        {
          if ((paramAnonymousList != null) && (paramAnonymousList.size() >= 4))
          {
            paramAnonymousInt = 0;
            if (paramAnonymousInt < 4)
            {
              final Promoter localPromoter = (Promoter)paramAnonymousList.get(paramAnonymousInt);
              Object localObject;
              ImageView localImageView;
              switch (paramAnonymousInt)
              {
              default:
                localObject = null;
                localImageView = null;
              case 0:
              case 1:
              case 2:
              case 3:
              }
              while (true)
              {
                if (localImageView != null)
                  localImageView.setOnClickListener(new View.OnClickListener()
                  {
                    public void onClick(View paramAnonymous2View)
                    {
                      b.a(localPromoter, UMDetail.this.d, UMDetail.this.j, false);
                    }
                  });
                if (localObject != null)
                  ((TextView)localObject).setText(localPromoter.title);
                localObject = AnimationUtils.loadAnimation(UMDetail.this.d, com.taobao.newxp.a.a.n(UMDetail.this.d));
                com.taobao.newxp.common.b.d.a(UMDetail.this.d, localImageView, localPromoter.icon, false, null, (Animation)localObject, true);
                localView.setVisibility(0);
                paramAnonymousInt += 1;
                break;
                localImageView = (ImageView)localView.findViewById(com.taobao.newxp.a.c.ah(UMDetail.this.d));
                localObject = (TextView)localView.findViewById(com.taobao.newxp.a.c.ai(UMDetail.this.d));
                continue;
                localImageView = (ImageView)localView.findViewById(com.taobao.newxp.a.c.aj(UMDetail.this.d));
                localObject = (TextView)localView.findViewById(com.taobao.newxp.a.c.ak(UMDetail.this.d));
                continue;
                localImageView = (ImageView)localView.findViewById(com.taobao.newxp.a.c.al(UMDetail.this.d));
                localObject = (TextView)localView.findViewById(com.taobao.newxp.a.c.am(UMDetail.this.d));
                continue;
                localImageView = (ImageView)localView.findViewById(com.taobao.newxp.a.c.an(UMDetail.this.d));
                localObject = (TextView)localView.findViewById(com.taobao.newxp.a.c.ao(UMDetail.this.d));
              }
            }
            new s.a(UMDetail.this.j.getEntity()).a(0).b(0).a((Promoter[])paramAnonymousList.toArray(new Promoter[paramAnonymousList.size()])).a().a();
          }
        }
      }
      , true);
    }
  }

  private void d()
  {
    int n = 0;
    Object localObject = k;
    final StringBuilder localStringBuilder = new StringBuilder().append("Start load imgs. [imgs.length");
    if (this.e.imgs == null);
    for (int m = 0; ; m = this.e.imgs.length)
    {
      Log.a((String)localObject, m + "]");
      if ((this.e.imgs == null) || (this.e.imgs.length <= 0))
        break;
      localObject = this.e.imgs;
      int i1 = localObject.length;
      m = n;
      while (m < i1)
      {
        localStringBuilder = localObject[m];
        com.taobao.newxp.common.b.d.a(this.d, localStringBuilder, new d.c()
        {
          public void a(Drawable paramAnonymousDrawable)
          {
            UMDetail.this.h.put(localStringBuilder, paramAnonymousDrawable);
            Log.a(UMDetail.a(), "Loaded drawable[" + localStringBuilder + "]");
            if (UMDetail.this.h.size() == UMDetail.this.e.imgs.length)
            {
              UMDetail.this.i = UMDetail.this.filterBadIMG();
              UMDetail.this.showStrip(UMDetail.this.i);
            }
          }

          public void a(d.b paramAnonymousb)
          {
          }
        });
        m += 1;
      }
    }
    findViewById(com.taobao.newxp.a.c.ar(this.d)).setVisibility(8);
  }

  protected List<f.a> filterBadIMG()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = this.e.imgs;
    int n = arrayOfString.length;
    int m = 0;
    while (m < n)
    {
      Object localObject2 = arrayOfString[m];
      Object localObject1 = (Drawable)this.h.get(localObject2);
      if ((localObject1 != null) && (((Drawable)localObject1).getIntrinsicHeight() > 0) && (((Drawable)localObject1).getIntrinsicWidth() > 0))
      {
        localArrayList.add(new f.a((String)localObject2, (Drawable)localObject1));
        m += 1;
      }
      else
      {
        String str = k;
        localObject2 = new StringBuilder().append("filter bad image [").append((String)localObject2).append("]").append("   ");
        if (localObject1 == null);
        for (localObject1 = "null"; ; localObject1 = "Exist")
        {
          Log.a(str, (String)localObject1);
          break;
        }
      }
    }
    return localArrayList;
  }

  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.d = this;
    setContentView(com.taobao.newxp.a.d.h(this.d));
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      this.e = ((Promoter)paramBundle.getParcelable("promoter"));
      this.c = ((MMEntity)paramBundle.getParcelable("entity"));
      this.f = paramBundle.getInt("action_index");
    }
    paramBundle = new MMEntity();
    paramBundle.psid = this.c.psid;
    paramBundle.slotId = this.c.slotId;
    paramBundle.appkey = this.c.appkey;
    paramBundle.layoutType = 16;
    this.j = new ExchangeDataService()
    {
      protected void a()
      {
        super.a();
        UMDetail.this.j.getEntity().psid = UMDetail.this.c.psid;
      }
    };
    this.j.setEntity(paramBundle);
    ((TextView)findViewById(com.taobao.newxp.a.c.aa(this.d))).setText(this.e.title);
    if ((TextView)findViewById(com.taobao.newxp.a.c.V(this.d)) != null);
    ((TextView)findViewById(com.taobao.newxp.a.c.ab(this.d))).setText(this.e.provider);
    this.g = ((TextView)findViewById(com.taobao.newxp.a.c.ac(this.d)));
    this.g.setText(this.e.description);
    if (this.e.description.length() < 75)
      findViewById(com.taobao.newxp.a.c.as(this.d)).setVisibility(8);
    this.g.setMaxLines(3);
    paramBundle = (TextView)findViewById(com.taobao.newxp.a.c.as(this.d));
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      int a;
      int b = 3;
      boolean c = false;

      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = UMDetail.this.g.getLayoutParams();
        if (this.a == 0)
          this.a = paramAnonymousView.height;
        if (3 <= UMDetail.this.g.getLineCount())
        {
          if (!this.c)
            break label137;
          UMDetail.this.g.setMaxLines(this.b);
          this.c = false;
          paramBundle.setText(UMDetail.this.d.getText(e.o(UMDetail.this.d)));
        }
        while (true)
        {
          Log.a(UMDetail.a(), "descript text view has changed height.[" + paramAnonymousView.height + "]");
          UMDetail.this.g.requestLayout();
          return;
          label137: UMDetail.this.g.setMaxLines(2147483647);
          this.c = true;
          paramBundle.setText(UMDetail.this.d.getText(e.p(UMDetail.this.d)));
        }
      }
    });
    paramBundle = (ImageView)findViewById(com.taobao.newxp.a.c.ad(this.d));
    Animation localAnimation = AnimationUtils.loadAnimation(this.d, com.taobao.newxp.a.a.n(this.d));
    com.taobao.newxp.common.b.d.a(this.d, paramBundle, this.e.icon, false, null, localAnimation, true);
    this.a = ((HorizontalStrip)findViewById(com.taobao.newxp.a.c.ae(this.d)));
    this.b = findViewById(com.taobao.newxp.a.c.af(this.d));
    findViewById(com.taobao.newxp.a.c.at(this.d)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UMDetail.a(UMDetail.this);
      }
    });
    findViewById(com.taobao.newxp.a.c.au(this.d)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UMDetail.this.finish();
      }
    });
    c();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.a.setAdapter(null);
    Iterator localIterator = this.h.values().iterator();
    while (localIterator.hasNext())
    {
      Drawable localDrawable = (Drawable)localIterator.next();
      if (localDrawable != null)
        localDrawable.setCallback(null);
    }
    this.h.clear();
  }

  protected void onResume()
  {
    super.onResume();
    d();
  }

  protected void showStrip(List<f.a> paramList)
  {
    if (paramList.size() > 0)
    {
      paramList = f.a(paramList);
      this.a.setAdapter(paramList);
      this.b.setVisibility(8);
      this.a.setVisibility(0);
      return;
    }
    findViewById(com.taobao.newxp.a.c.ar(this.d)).setVisibility(8);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.UMDetail
 * JD-Core Version:    0.6.2
 */