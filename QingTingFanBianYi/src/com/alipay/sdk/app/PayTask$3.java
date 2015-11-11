package com.alipay.sdk.app;

import android.os.Handler;
import com.alipay.sdk.util.FileDownloader.IDownloadProgress;
import com.alipay.sdk.widget.Loading;

class PayTask$3
  implements FileDownloader.IDownloadProgress
{
  PayTask$3(PayTask paramPayTask, Loading paramLoading)
  {
  }

  public final void a()
  {
    this.a.c();
    PayTask.e(this.b).removeCallbacks(PayTask.d(this.b));
    PayTask.f(this.b);
  }

  public final void b()
  {
  }

  public final void c()
  {
    this.a.c();
    PayTask.e(this.b).post(PayTask.d(this.b));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.PayTask.3
 * JD-Core Version:    0.6.2
 */