package com.taobao.newxp.view;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.Promoter.a;
import com.taobao.newxp.a.c;
import com.taobao.newxp.a.e;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.common.b.d;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.XpListenersCenter.AdapterListener;
import com.taobao.newxp.controller.XpListenersCenter.FitType;
import com.taobao.newxp.controller.XpListenersCenter.ListClickListener;
import java.util.List;

public class a extends ArrayAdapter<Promoter>
{
  public XpListenersCenter.ListClickListener a = null;
  boolean b = false;
  private Context c;
  private int d;
  private int e;
  private XpListenersCenter.AdapterListener f = null;
  private ExchangeDataService g;

  public a(Context paramContext, int paramInt1, List<Promoter> paramList, int paramInt2, int paramInt3, ExchangeDataService paramExchangeDataService)
  {
    super(paramContext, paramInt1, paramList);
    this.c = paramContext;
    this.d = paramInt2;
    this.e = paramInt3;
    this.g = paramExchangeDataService;
  }

  public void a(int paramInt)
  {
  }

  public void a(XpListenersCenter.AdapterListener paramAdapterListener)
  {
    this.f = paramAdapterListener;
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject1 = (LayoutInflater)this.c.getSystemService("layout_inflater");
    if ((paramView == null) || (paramView.getTag() == null))
    {
      localObject1 = ((LayoutInflater)localObject1).inflate(this.d, paramViewGroup, false);
      paramViewGroup = new a();
      paramViewGroup.a = ((ImageView)((View)localObject1).findViewById(c.I(this.c)));
      paramViewGroup.b = ((TextView)((View)localObject1).findViewById(c.l(this.c)));
      paramViewGroup.c = ((TextView)((View)localObject1).findViewById(c.G(this.c)));
      paramViewGroup.d = ((TextView)((View)localObject1).findViewById(c.H(this.c)));
      paramViewGroup.e = ((TextView)((View)localObject1).findViewById(c.u(this.c)));
      paramViewGroup.f = ((Button)((View)localObject1).findViewById(c.M(this.c)));
    }
    label775: label794: 
    while (true)
    {
      try
      {
        paramViewGroup.g = ((ImageView)((View)localObject1).findViewById(c.s(this.c)));
        Object localObject2;
        if (Log.LOG)
        {
          localObject2 = ExchangeConstants.LOG_TAG;
          if ("New tip Imageview is " + paramViewGroup.g == null)
          {
            paramView = "null";
            Log.c((String)localObject2, paramView);
          }
        }
        else
        {
          ((View)localObject1).setTag(paramViewGroup);
          paramView = (View)localObject1;
          localObject1 = (Promoter)getItem(paramInt);
          paramViewGroup.a.setImageDrawable(this.c.getResources().getDrawable(com.taobao.newxp.a.b.b(this.c)));
          if (paramViewGroup.a != null)
          {
            if (!ExchangeConstants.ROUND_ICON)
              break label694;
            d.a(this.c, paramViewGroup.a, ((Promoter)localObject1).icon, false, null, null, true);
          }
          if (paramViewGroup.b != null)
          {
            if (!AlimmContext.getAliContext().getAppUtils().c(((Promoter)localObject1).app_package_name))
              break label714;
            paramViewGroup.b.setText(e.b(this.c));
            if ((!AlimmContext.getAliContext().getAppUtils().c(((Promoter)localObject1).app_package_name)) && (!TextUtils.isEmpty(((Promoter)localObject1).price)))
              paramViewGroup.b.setText(((Promoter)localObject1).price);
          }
          if (paramViewGroup.c != null)
            paramViewGroup.c.setText(((Promoter)localObject1).title);
          if (paramViewGroup.d != null)
            paramViewGroup.d.setText(((Promoter)localObject1).ad_words);
          if (!ExchangeConstants.show_size)
            break label775;
          if (paramViewGroup.e != null)
            paramViewGroup.e.setText(com.taobao.newxp.common.b.b.a(this.c, ((Promoter)localObject1).size));
          if (paramViewGroup.f != null)
            paramViewGroup.d.setText(((Promoter)localObject1).ad_words);
          localObject2 = new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              com.taobao.newxp.controller.b.a(new com.taobao.newxp.controller.a.a(this.a, paramInt), a.a(a.this), a.b(a.this), true, paramInt);
            }
          };
          if (paramViewGroup.f != null)
          {
            if (!AlimmContext.getAliContext().getAppUtils().c(((Promoter)localObject1).app_package_name))
              break label794;
            paramViewGroup.f.setText(e.b(this.c));
            if (this.f != null)
              this.f.onFitType(paramView, (Promoter)localObject1, XpListenersCenter.FitType.OPEN);
            if (((Promoter)localObject1).new_tip != 1)
              break label982;
            if (this.f != null)
              this.f.onFitType(paramView, (Promoter)localObject1, XpListenersCenter.FitType.NEW);
            if (paramViewGroup.g != null)
              paramViewGroup.g.setVisibility(0);
            paramViewGroup.f.setOnClickListener((View.OnClickListener)localObject2);
          }
          ExchangeConstants.definePageLevel(this.e);
          paramView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              if (a.this.a != null)
              {
                a.this.a.click(this.a);
                return;
              }
              com.taobao.newxp.controller.b.a(new com.taobao.newxp.controller.a.a(this.a, paramInt), a.a(a.this), a.b(a.this), false, paramInt);
            }
          });
          if (paramInt == getCount() - 1)
          {
            Log.c(ExchangeConstants.LOG_TAG, "get last position data " + paramInt);
            a(paramInt);
          }
          return paramView;
        }
      }
      catch (Exception paramView)
      {
        paramViewGroup.g = null;
        continue;
        paramView = "not null";
        continue;
      }
      paramViewGroup = (a)paramView.getTag();
      continue;
      label694: d.a(this.c, paramViewGroup.a, ((Promoter)localObject1).icon, false);
      continue;
      label714: if ((((Promoter)localObject1).landing_type == 3) || (((Promoter)localObject1).landing_type == 2) || (((Promoter)localObject1).landing_type == 4))
      {
        paramViewGroup.b.setText(e.c(this.c));
      }
      else
      {
        paramViewGroup.b.setText(e.e(this.c));
        continue;
        if (paramViewGroup.e != null)
        {
          paramViewGroup.e.setVisibility(8);
          continue;
          if ((((Promoter)localObject1).landing_type == 3) || (((Promoter)localObject1).landing_type == 2) || (((Promoter)localObject1).landing_type == 4))
          {
            int i = e.c(this.c);
            String str = Uri.parse(((Promoter)localObject1).url).getScheme();
            if ((str != null) && (str.equalsIgnoreCase(Promoter.a.a.toString())))
            {
              paramViewGroup.f.setText(e.f(this.c));
              e.f(this.c);
              if (this.f != null)
                this.f.onFitType(paramView, (Promoter)localObject1, XpListenersCenter.FitType.PHONE);
            }
            else
            {
              paramViewGroup.f.setText(i);
              if (this.f != null)
                this.f.onFitType(paramView, (Promoter)localObject1, XpListenersCenter.FitType.BROWSE);
            }
          }
          else
          {
            paramViewGroup.f.setText(e.e(this.c));
            if (this.f != null)
            {
              this.f.onFitType(paramView, (Promoter)localObject1, XpListenersCenter.FitType.DOWNLOAD);
              continue;
              label982: if (paramViewGroup.g != null)
                paramViewGroup.g.setVisibility(8);
            }
          }
        }
      }
    }
  }

  static class a
  {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    Button f;
    ImageView g;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.a
 * JD-Core Version:    0.6.2
 */