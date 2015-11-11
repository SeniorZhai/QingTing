package com.taobao.newxp.view.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.taobao.munion.base.a;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.a.e;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.b.b;
import com.taobao.newxp.net.MMEntity;
import com.taobao.newxp.net.h;
import com.taobao.newxp.net.s.a;

public class DownloadDialog extends Dialog
{
  private Context a;
  private int b;
  private a c;

  public DownloadDialog(Context paramContext, final Promoter paramPromoter, final int paramInt1, final MMEntity paramMMEntity, int paramInt2, a parama)
  {
    super(paramContext, paramInt2);
    this.a = paramContext;
    this.b = paramMMEntity.layoutType;
    this.c = parama;
    setContentView(com.taobao.newxp.a.d.d(paramContext));
    ((TextView)findViewById(com.taobao.newxp.a.c.x(this.a))).setText(" 开发:" + paramPromoter.provider);
    ((TextView)findViewById(com.taobao.newxp.a.c.y(this.a))).setText(" 名称:" + paramPromoter.title);
    paramContext = (TextView)findViewById(com.taobao.newxp.a.c.u(this.a));
    if (ExchangeConstants.show_size)
    {
      paramContext.setText(b.a(this.a, paramPromoter.size));
      paramContext = (TextView)findViewById(com.taobao.newxp.a.c.A(this.a));
      paramContext.setText(paramPromoter.description);
      paramContext.setMovementMethod(ScrollingMovementMethod.getInstance());
      paramContext = (ImageView)findViewById(com.taobao.newxp.a.c.r(this.a));
      com.taobao.newxp.common.b.d.a(this.a, paramContext, paramPromoter.icon, false);
      paramContext = (Button)findViewById(com.taobao.newxp.a.c.p(this.a));
      switch (3.a[parama.ordinal()])
      {
      default:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      paramContext.setOnClickListener(new View.OnClickListener()
      {
        private void a(Promoter paramAnonymousPromoter)
        {
          paramAnonymousPromoter = Uri.parse(paramAnonymousPromoter.url);
          if (!AlimmContext.getAliContext().getAppUtils().e("android.permission.CALL_PHONE"))
          {
            Toast.makeText(DownloadDialog.b(DownloadDialog.this), "This App has no call_phone permission!", 0).show();
            return;
          }
          paramAnonymousPromoter = paramAnonymousPromoter.getAuthority();
          paramAnonymousPromoter = new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramAnonymousPromoter));
          DownloadDialog.b(DownloadDialog.this).startActivity(paramAnonymousPromoter);
        }

        private void a(Promoter paramAnonymousPromoter, int paramAnonymousInt, MMEntity paramAnonymousMMEntity)
        {
          int i = ExchangeConstants.definePageLevel(DownloadDialog.c(DownloadDialog.this));
          paramAnonymousMMEntity = new s.a(paramAnonymousMMEntity).a(3).b(paramAnonymousInt).c(i);
          new h(DownloadDialog.b(DownloadDialog.this), paramAnonymousPromoter.url).a(paramAnonymousMMEntity).d(paramAnonymousPromoter.title).a();
        }

        public void onClick(View paramAnonymousView)
        {
          DownloadDialog.this.dismiss();
          if (DownloadDialog.a(DownloadDialog.this) == DownloadDialog.a.b)
          {
            a(paramPromoter);
            return;
          }
          a(paramPromoter, paramInt1, paramMMEntity);
        }
      });
      ((Button)findViewById(com.taobao.newxp.a.c.o(this.a))).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DownloadDialog.this.cancel();
        }
      });
      return;
      paramContext.setVisibility(8);
      break;
      paramContext.setText(e.m(this.a));
      continue;
      paramContext.setText(e.n(this.a));
    }
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.DownloadDialog
 * JD-Core Version:    0.6.2
 */