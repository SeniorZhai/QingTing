package com.alipay.sdk.util;

class FileDownloader$1
  implements Runnable
{
  FileDownloader$1(FileDownloader paramFileDownloader, FileDownloader.ProgressOutput paramProgressOutput)
  {
  }

  public void run()
  {
    FileDownloader.a(this.b, new FileFetch(FileDownloader.a(this.b), FileDownloader.b(this.b), this.b));
    long l1 = -1L;
    if (this.b.a)
    {
      long l2 = FileDownloader.c(this.b);
      l1 = l2;
      if (l2 <= 0L)
        this.a.sendEmptyMessage(0);
    }
    else
    {
      FileDownloader.d(this.b);
    }
    if (this.b.a)
    {
      FileDownloader.e(this.b);
      if (FileDownloader.f(this.b).b() != l1)
      {
        FileDownloader.d(this.b);
        FileDownloader.f(this.b).a(0L);
        FileDownloader.f(this.b).b(l1);
      }
    }
    new Thread(FileDownloader.f(this.b)).start();
    FileDownloader.ProgressOutput.a(this.a);
    while (true)
      if (!FileDownloader.f(this.b).c())
        try
        {
          Thread.sleep(1500L);
          this.a.sendEmptyMessage(0);
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            localInterruptedException.printStackTrace();
        }
    this.a.sendEmptyMessage(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.FileDownloader.1
 * JD-Core Version:    0.6.2
 */