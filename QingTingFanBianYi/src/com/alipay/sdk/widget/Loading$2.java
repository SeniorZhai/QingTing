package com.alipay.sdk.widget;

class Loading$2
  implements Runnable
{
  Loading$2(Loading paramLoading)
  {
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/alipay/sdk/widget/Loading$2:a	Lcom/alipay/sdk/widget/Loading;
    //   4: invokestatic 24	com/alipay/sdk/widget/Loading:a	(Lcom/alipay/sdk/widget/Loading;)Landroid/app/ProgressDialog;
    //   7: ifnull +23 -> 30
    //   10: aload_0
    //   11: getfield 12	com/alipay/sdk/widget/Loading$2:a	Lcom/alipay/sdk/widget/Loading;
    //   14: invokevirtual 27	com/alipay/sdk/widget/Loading:a	()Z
    //   17: ifeq +13 -> 30
    //   20: aload_0
    //   21: getfield 12	com/alipay/sdk/widget/Loading$2:a	Lcom/alipay/sdk/widget/Loading;
    //   24: invokestatic 24	com/alipay/sdk/widget/Loading:a	(Lcom/alipay/sdk/widget/Loading;)Landroid/app/ProgressDialog;
    //   27: invokevirtual 32	android/app/ProgressDialog:dismiss	()V
    //   30: aload_0
    //   31: getfield 12	com/alipay/sdk/widget/Loading$2:a	Lcom/alipay/sdk/widget/Loading;
    //   34: aconst_null
    //   35: invokestatic 35	com/alipay/sdk/widget/Loading:a	(Lcom/alipay/sdk/widget/Loading;Landroid/app/ProgressDialog;)Landroid/app/ProgressDialog;
    //   38: pop
    //   39: return
    //   40: astore_1
    //   41: aload_0
    //   42: getfield 12	com/alipay/sdk/widget/Loading$2:a	Lcom/alipay/sdk/widget/Loading;
    //   45: aconst_null
    //   46: invokestatic 35	com/alipay/sdk/widget/Loading:a	(Lcom/alipay/sdk/widget/Loading;Landroid/app/ProgressDialog;)Landroid/app/ProgressDialog;
    //   49: pop
    //   50: return
    //   51: astore_1
    //   52: aload_0
    //   53: getfield 12	com/alipay/sdk/widget/Loading$2:a	Lcom/alipay/sdk/widget/Loading;
    //   56: aconst_null
    //   57: invokestatic 35	com/alipay/sdk/widget/Loading:a	(Lcom/alipay/sdk/widget/Loading;Landroid/app/ProgressDialog;)Landroid/app/ProgressDialog;
    //   60: pop
    //   61: aload_1
    //   62: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	30	40	java/lang/Exception
    //   0	30	51	finally
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.widget.Loading.2
 * JD-Core Version:    0.6.2
 */