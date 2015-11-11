package com.taobao.munion.base.volley;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import java.util.concurrent.BlockingQueue;

public class g extends Thread
{
  private final BlockingQueue<l<?>> a;
  private final f b;
  private final b c;
  private final o d;
  private volatile boolean e = false;

  public g(BlockingQueue<l<?>> paramBlockingQueue, f paramf, b paramb, o paramo)
  {
    this.a = paramBlockingQueue;
    this.b = paramf;
    this.c = paramb;
    this.d = paramo;
  }

  private void a(l<?> paraml)
  {
    if (Build.VERSION.SDK_INT >= 14)
      TrafficStats.setThreadStatsTag(paraml.d());
  }

  private void a(l<?> paraml, s params)
  {
    params = paraml.a(params);
    this.d.a(paraml, params);
  }

  public void a()
  {
    this.e = true;
    interrupt();
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 76	android/os/Process:setThreadPriority	(I)V
    //   5: aload_0
    //   6: getfield 24	com/taobao/munion/base/volley/g:a	Ljava/util/concurrent/BlockingQueue;
    //   9: invokeinterface 82 1 0
    //   14: checkcast 42	com/taobao/munion/base/volley/l
    //   17: astore_1
    //   18: aload_1
    //   19: ldc 84
    //   21: invokevirtual 87	com/taobao/munion/base/volley/l:a	(Ljava/lang/String;)V
    //   24: aload_1
    //   25: invokevirtual 91	com/taobao/munion/base/volley/l:j	()Z
    //   28: ifeq +31 -> 59
    //   31: aload_1
    //   32: ldc 93
    //   34: invokevirtual 95	com/taobao/munion/base/volley/l:b	(Ljava/lang/String;)V
    //   37: goto -32 -> 5
    //   40: astore_2
    //   41: aload_0
    //   42: aload_1
    //   43: aload_2
    //   44: invokespecial 96	com/taobao/munion/base/volley/g:a	(Lcom/taobao/munion/base/volley/l;Lcom/taobao/munion/base/volley/s;)V
    //   47: goto -42 -> 5
    //   50: astore_1
    //   51: aload_0
    //   52: getfield 22	com/taobao/munion/base/volley/g:e	Z
    //   55: ifeq -50 -> 5
    //   58: return
    //   59: aload_0
    //   60: aload_1
    //   61: invokespecial 98	com/taobao/munion/base/volley/g:a	(Lcom/taobao/munion/base/volley/l;)V
    //   64: aload_0
    //   65: getfield 26	com/taobao/munion/base/volley/g:b	Lcom/taobao/munion/base/volley/f;
    //   68: aload_1
    //   69: invokeinterface 103 2 0
    //   74: astore_2
    //   75: aload_1
    //   76: ldc 105
    //   78: invokevirtual 87	com/taobao/munion/base/volley/l:a	(Ljava/lang/String;)V
    //   81: aload_2
    //   82: getfield 109	com/taobao/munion/base/volley/i:d	Z
    //   85: ifeq +58 -> 143
    //   88: aload_1
    //   89: invokevirtual 112	com/taobao/munion/base/volley/l:y	()Z
    //   92: ifeq +51 -> 143
    //   95: aload_1
    //   96: ldc 114
    //   98: invokevirtual 95	com/taobao/munion/base/volley/l:b	(Ljava/lang/String;)V
    //   101: goto -96 -> 5
    //   104: astore_2
    //   105: aload_2
    //   106: ldc 116
    //   108: iconst_1
    //   109: anewarray 118	java/lang/Object
    //   112: dup
    //   113: iconst_0
    //   114: aload_2
    //   115: invokevirtual 122	java/lang/Exception:toString	()Ljava/lang/String;
    //   118: aastore
    //   119: invokestatic 127	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   122: aload_0
    //   123: getfield 30	com/taobao/munion/base/volley/g:d	Lcom/taobao/munion/base/volley/o;
    //   126: aload_1
    //   127: new 69	com/taobao/munion/base/volley/s
    //   130: dup
    //   131: aload_2
    //   132: invokespecial 130	com/taobao/munion/base/volley/s:<init>	(Ljava/lang/Throwable;)V
    //   135: invokeinterface 60 3 0
    //   140: goto -135 -> 5
    //   143: aload_1
    //   144: aload_2
    //   145: invokevirtual 133	com/taobao/munion/base/volley/l:a	(Lcom/taobao/munion/base/volley/i;)Lcom/taobao/munion/base/volley/n;
    //   148: astore_2
    //   149: aload_1
    //   150: ldc 135
    //   152: invokevirtual 87	com/taobao/munion/base/volley/l:a	(Ljava/lang/String;)V
    //   155: aload_1
    //   156: invokevirtual 138	com/taobao/munion/base/volley/l:t	()Z
    //   159: ifeq +33 -> 192
    //   162: aload_2
    //   163: getfield 143	com/taobao/munion/base/volley/n:b	Lcom/taobao/munion/base/volley/b$a;
    //   166: ifnull +26 -> 192
    //   169: aload_0
    //   170: getfield 28	com/taobao/munion/base/volley/g:c	Lcom/taobao/munion/base/volley/b;
    //   173: aload_1
    //   174: invokevirtual 146	com/taobao/munion/base/volley/l:g	()Ljava/lang/String;
    //   177: aload_2
    //   178: getfield 143	com/taobao/munion/base/volley/n:b	Lcom/taobao/munion/base/volley/b$a;
    //   181: invokeinterface 151 3 0
    //   186: aload_1
    //   187: ldc 153
    //   189: invokevirtual 87	com/taobao/munion/base/volley/l:a	(Ljava/lang/String;)V
    //   192: aload_1
    //   193: invokevirtual 156	com/taobao/munion/base/volley/l:x	()V
    //   196: aload_0
    //   197: getfield 30	com/taobao/munion/base/volley/g:d	Lcom/taobao/munion/base/volley/o;
    //   200: aload_1
    //   201: aload_2
    //   202: invokeinterface 159 3 0
    //   207: goto -202 -> 5
    //
    // Exception table:
    //   from	to	target	type
    //   18	37	40	com/taobao/munion/base/volley/s
    //   59	101	40	com/taobao/munion/base/volley/s
    //   143	192	40	com/taobao/munion/base/volley/s
    //   192	207	40	com/taobao/munion/base/volley/s
    //   5	18	50	java/lang/InterruptedException
    //   18	37	104	java/lang/Exception
    //   59	101	104	java/lang/Exception
    //   143	192	104	java/lang/Exception
    //   192	207	104	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.g
 * JD-Core Version:    0.6.2
 */