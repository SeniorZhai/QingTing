package fm.qingting.async;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ConnectCallback;
import fm.qingting.async.callback.ListenCallback;
import fm.qingting.async.future.Cancellable;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.SimpleFuture;
import fm.qingting.async.future.TransformFuture;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AsyncServer
{
  public static final String LOGTAG = "NIO";
  private static final long QUEUE_EMPTY = 9223372036854775807L;
  static AsyncServer mInstance;
  static WeakHashMap<Thread, AsyncServer> mServers;
  private static WeakHashMap<Thread, ThreadQueue> mThreadQueues;
  Thread mAffinity;
  private boolean mAutoStart = false;
  LinkedList<Scheduled> mQueue = new LinkedList();
  private Selector mSelector;
  ExecutorService synchronousWorkers = Executors.newFixedThreadPool(4);

  static
  {
    boolean bool;
    if (!AsyncServer.class.desiredAssertionStatus())
      bool = true;
    while (true)
    {
      $assertionsDisabled = bool;
      mThreadQueues = new WeakHashMap();
      try
      {
        if (Build.VERSION.SDK_INT <= 8)
        {
          System.setProperty("java.net.preferIPv4Stack", "true");
          System.setProperty("java.net.preferIPv6Addresses", "false");
        }
        label48: mInstance = new AsyncServer()
        {
        };
        mServers = new WeakHashMap();
        return;
        bool = false;
      }
      catch (Throwable localThrowable)
      {
        break label48;
      }
    }
  }

  private boolean addMe()
  {
    synchronized (mServers)
    {
      if ((AsyncServer)mServers.get(Thread.currentThread()) != null)
        return false;
      mServers.put(this.mAffinity, this);
      return true;
    }
  }

  private ConnectFuture connectResolvedInetSocketAddress(final InetSocketAddress paramInetSocketAddress, final ConnectCallback paramConnectCallback)
  {
    final ConnectFuture localConnectFuture = new ConnectFuture(null);
    assert (!paramInetSocketAddress.isUnresolved());
    post(new Runnable()
    {
      public void run()
      {
        Object localObject3 = null;
        Object localObject4 = null;
        if (localConnectFuture.isCancelled())
          return;
        localConnectFuture.callback = paramConnectCallback;
        try
        {
          Object localObject1 = localConnectFuture;
          SocketChannel localSocketChannel = SocketChannel.open();
          ((AsyncServer.ConnectFuture)localObject1).socket = localSocketChannel;
          localObject1 = localObject4;
          try
          {
            localSocketChannel.configureBlocking(false);
            localObject1 = localObject4;
            localObject3 = localSocketChannel.register(AsyncServer.this.mSelector, 8);
            localObject1 = localObject3;
            ((SelectionKey)localObject3).attach(localConnectFuture);
            localObject1 = localObject3;
            localSocketChannel.connect(paramInetSocketAddress);
            return;
          }
          catch (Exception localException3)
          {
            localObject3 = localObject1;
            localObject1 = localException3;
          }
          if (localObject3 != null)
            ((SelectionKey)localObject3).cancel();
          if (localSocketChannel != null);
          try
          {
            localSocketChannel.close();
            label113: localConnectFuture.setComplete((Exception)localObject1);
            return;
          }
          catch (Exception localException2)
          {
            break label113;
          }
        }
        catch (Exception localException1)
        {
          while (true)
            Object localObject2 = null;
        }
      }
    });
    return localConnectFuture;
  }

  public static AsyncServer getCurrentThreadServer()
  {
    return (AsyncServer)mServers.get(Thread.currentThread());
  }

  public static AsyncServer getDefault()
  {
    return mInstance;
  }

  static ThreadQueue getOrCreateThreadQueue(Thread paramThread)
  {
    synchronized (mThreadQueues)
    {
      ThreadQueue localThreadQueue2 = (ThreadQueue)mThreadQueues.get(paramThread);
      ThreadQueue localThreadQueue1 = localThreadQueue2;
      if (localThreadQueue2 == null)
      {
        localThreadQueue1 = new ThreadQueue();
        mThreadQueues.put(paramThread, localThreadQueue1);
      }
      return localThreadQueue1;
    }
  }

  private void handleSocket(AsyncNetworkSocket paramAsyncNetworkSocket)
    throws ClosedChannelException
  {
    SelectionKey localSelectionKey = paramAsyncNetworkSocket.getChannel().register(this.mSelector);
    localSelectionKey.attach(paramAsyncNetworkSocket);
    paramAsyncNetworkSocket.setup(this, localSelectionKey);
  }

  private static long lockAndRunQueue(AsyncServer paramAsyncServer, LinkedList<Scheduled> paramLinkedList)
  {
    long l1 = 9223372036854775807L;
    while (true)
    {
      try
      {
        long l2 = System.currentTimeMillis();
        Object localObject = null;
        if (paramLinkedList.size() <= 0)
          break label113;
        localScheduled = (Scheduled)paramLinkedList.remove();
        if (localScheduled.time <= l2)
        {
          if (localObject != null)
            paramLinkedList.addAll((Collection)localObject);
          if (localScheduled == null)
            return l1;
        }
        else
        {
          l1 = Math.min(l1, localScheduled.time - l2);
          if (localObject != null)
            break label110;
          localObject = new LinkedList();
          ((LinkedList)localObject).add(localScheduled);
          continue;
        }
      }
      finally
      {
      }
      localScheduled.runnable.run();
      break;
      label110: continue;
      label113: Scheduled localScheduled = null;
    }
  }

  public static void post(Handler paramHandler, Runnable paramRunnable)
  {
    RunnableWrapper localRunnableWrapper = new RunnableWrapper(null);
    ThreadQueue localThreadQueue = getOrCreateThreadQueue(paramHandler.getLooper().getThread());
    localRunnableWrapper.threadQueue = localThreadQueue;
    localRunnableWrapper.handler = paramHandler;
    localRunnableWrapper.runnable = paramRunnable;
    localThreadQueue.add(localRunnableWrapper);
    paramHandler.post(localRunnableWrapper);
    localThreadQueue.queueSemaphore.release();
  }

  private static void run(AsyncServer arg0, Selector paramSelector, LinkedList<Scheduled> paramLinkedList, boolean paramBoolean)
  {
    try
    {
      while (true)
      {
        runLoop(???, paramSelector, paramLinkedList, paramBoolean);
        try
        {
          label7: if ((paramSelector.isOpen()) && ((paramSelector.keys().size() > 0) || (paramBoolean) || (paramLinkedList.size() > 0)));
        }
        finally
        {
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("NIO", "exception?", localException);
      shutdownEverything(paramSelector);
      if (???.mSelector == paramSelector)
      {
        ???.mQueue = new LinkedList();
        ???.mSelector = null;
        ???.mAffinity = null;
      }
      synchronized (mServers)
      {
        mServers.remove(Thread.currentThread());
        return;
      }
    }
    catch (ClosedSelectorException localClosedSelectorException)
    {
      break label7;
    }
  }

  private static void runLoop(AsyncServer paramAsyncServer, Selector paramSelector, LinkedList<Scheduled> paramLinkedList, boolean paramBoolean)
    throws IOException
  {
    int j = 1;
    long l2 = lockAndRunQueue(paramAsyncServer, paramLinkedList);
    label473: label480: 
    while (true)
    {
      int i;
      Object localObject2;
      Object localObject3;
      AsyncNetworkSocket localAsyncNetworkSocket;
      try
      {
        if (paramSelector.selectNow() != 0)
          break label480;
        i = j;
        if (paramSelector.keys().size() == 0)
        {
          i = j;
          if (!paramBoolean)
          {
            i = j;
            if (l2 == 9223372036854775807L)
              return;
          }
        }
        if (i != 0)
        {
          long l1 = l2;
          if (l2 == 9223372036854775807L)
            l1 = 100L;
          paramSelector.select(l1);
        }
        paramLinkedList = paramSelector.selectedKeys();
        Iterator localIterator = paramLinkedList.iterator();
        if (!localIterator.hasNext())
          break label473;
        Object localObject1 = (SelectionKey)localIterator.next();
        try
        {
          if (!((SelectionKey)localObject1).isAcceptable())
            break label254;
          localObject2 = ((ServerSocketChannel)((SelectionKey)localObject1).channel()).accept();
          if (localObject2 == null)
            continue;
          ((SocketChannel)localObject2).configureBlocking(false);
          localObject3 = ((SocketChannel)localObject2).register(paramSelector, 1);
          localObject1 = (ListenCallback)((SelectionKey)localObject1).attachment();
          localAsyncNetworkSocket = new AsyncNetworkSocket();
          localAsyncNetworkSocket.attach((SocketChannel)localObject2, (InetSocketAddress)((SocketChannel)localObject2).socket().getRemoteSocketAddress());
          localAsyncNetworkSocket.setup(paramAsyncServer, (SelectionKey)localObject3);
          ((SelectionKey)localObject3).attach(localAsyncNetworkSocket);
          ((ListenCallback)localObject1).onAccepted(localAsyncNetworkSocket);
        }
        catch (Exception localException1)
        {
          Log.e("NIO", "inner loop exception", localException1);
        }
        continue;
      }
      finally
      {
      }
      label254: if (localException1.isReadable())
      {
        paramAsyncServer.onDataTransmitted(((AsyncNetworkSocket)localException1.attachment()).onReadable());
      }
      else if (localException1.isWritable())
      {
        ((AsyncNetworkSocket)localException1.attachment()).onDataWritable();
      }
      else if (localException1.isConnectable())
      {
        localObject2 = (ConnectFuture)localException1.attachment();
        localObject3 = (SocketChannel)localException1.channel();
        localException1.interestOps(1);
        try
        {
          ((SocketChannel)localObject3).finishConnect();
          localAsyncNetworkSocket = new AsyncNetworkSocket();
          localAsyncNetworkSocket.setup(paramAsyncServer, localException1);
          localAsyncNetworkSocket.attach((SocketChannel)localObject3, (InetSocketAddress)((SocketChannel)localObject3).socket().getRemoteSocketAddress());
          localException1.attach(localAsyncNetworkSocket);
          if (!((ConnectFuture)localObject2).setComplete(localAsyncNetworkSocket))
            continue;
          ((ConnectFuture)localObject2).callback.onConnectCompleted(null, localAsyncNetworkSocket);
        }
        catch (Exception localException2)
        {
          localException1.cancel();
          ((SocketChannel)localObject3).close();
        }
        if (((ConnectFuture)localObject2).setComplete(localException2))
          ((ConnectFuture)localObject2).callback.onConnectCompleted(localException2, null);
      }
      else
      {
        Log.i("NIO", "wtf");
        if (!$assertionsDisabled)
        {
          throw new AssertionError();
          paramLinkedList.clear();
          return;
          i = 0;
        }
      }
    }
  }

  private static void shutdownEverything(Selector paramSelector)
  {
    try
    {
      Iterator localIterator = paramSelector.keys().iterator();
      if (localIterator.hasNext())
        localSelectionKey = (SelectionKey)localIterator.next();
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          SelectionKey localSelectionKey;
          localSelectionKey.channel().close();
          try
          {
            label36: localSelectionKey.cancel();
          }
          catch (Exception localException2)
          {
          }
        }
        localException1 = localException1;
        try
        {
          paramSelector.close();
          return;
        }
        catch (Exception paramSelector)
        {
        }
      }
      catch (Exception localException3)
      {
        break label36;
      }
    }
  }

  public AsyncDatagramSocket connectDatagram(final String paramString, final int paramInt)
    throws IOException
  {
    final DatagramChannel localDatagramChannel = DatagramChannel.open();
    final AsyncDatagramSocket localAsyncDatagramSocket = new AsyncDatagramSocket();
    localAsyncDatagramSocket.attach(localDatagramChannel);
    run(new Runnable()
    {
      public void run()
      {
        try
        {
          InetSocketAddress localInetSocketAddress = new InetSocketAddress(paramString, paramInt);
          AsyncServer.this.handleSocket(localAsyncDatagramSocket);
          localDatagramChannel.connect(localInetSocketAddress);
          return;
        }
        catch (Exception localException)
        {
          Log.e("NIO", "Datagram error", localException);
        }
      }
    });
    return localAsyncDatagramSocket;
  }

  public AsyncDatagramSocket connectDatagram(final SocketAddress paramSocketAddress)
    throws IOException
  {
    final DatagramChannel localDatagramChannel = DatagramChannel.open();
    final AsyncDatagramSocket localAsyncDatagramSocket = new AsyncDatagramSocket();
    localAsyncDatagramSocket.attach(localDatagramChannel);
    run(new Runnable()
    {
      public void run()
      {
        try
        {
          AsyncServer.this.handleSocket(localAsyncDatagramSocket);
          localDatagramChannel.connect(paramSocketAddress);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    });
    return localAsyncDatagramSocket;
  }

  public Cancellable connectSocket(String paramString, int paramInt, ConnectCallback paramConnectCallback)
  {
    return connectSocket(InetSocketAddress.createUnresolved(paramString, paramInt), paramConnectCallback);
  }

  public Cancellable connectSocket(final InetSocketAddress paramInetSocketAddress, final ConnectCallback paramConnectCallback)
  {
    if (!paramInetSocketAddress.isUnresolved())
      return connectResolvedInetSocketAddress(paramInetSocketAddress, paramConnectCallback);
    return new TransformFuture()
    {
      protected void transform(InetAddress paramAnonymousInetAddress)
        throws Exception
      {
        setParent(AsyncServer.this.connectResolvedInetSocketAddress(new InetSocketAddress(paramInetSocketAddress.getHostName(), paramInetSocketAddress.getPort()), paramConnectCallback));
      }
    }
    .from(getByName(paramInetSocketAddress.getHostName()));
  }

  public void dump()
  {
    post(new Runnable()
    {
      public void run()
      {
        if (AsyncServer.this.mSelector == null)
          Log.i("NIO", "Server dump not possible. No selector?");
        while (true)
        {
          return;
          Log.i("NIO", "Key Count: " + AsyncServer.this.mSelector.keys().size());
          Iterator localIterator = AsyncServer.this.mSelector.keys().iterator();
          while (localIterator.hasNext())
          {
            SelectionKey localSelectionKey = (SelectionKey)localIterator.next();
            Log.i("NIO", "Key: " + localSelectionKey);
          }
        }
      }
    });
  }

  public Thread getAffinity()
  {
    return this.mAffinity;
  }

  public Future<InetAddress[]> getAllByName(final String paramString)
  {
    final SimpleFuture localSimpleFuture = new SimpleFuture();
    this.synchronousWorkers.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(paramString);
          if ((arrayOfInetAddress == null) || (arrayOfInetAddress.length == 0))
            throw new Exception("no addresses for host");
        }
        catch (Exception localException)
        {
          AsyncServer.this.post(new Runnable()
          {
            public void run()
            {
              AsyncServer.8.this.val$ret.setComplete(localException, null);
            }
          });
          return;
        }
        AsyncServer.this.post(new Runnable()
        {
          public void run()
          {
            AsyncServer.8.this.val$ret.setComplete(null, localException);
          }
        });
      }
    });
    return localSimpleFuture;
  }

  public boolean getAutoStart()
  {
    return this.mAutoStart;
  }

  public Future<InetAddress> getByName(String paramString)
  {
    return new TransformFuture()
    {
      protected void transform(InetAddress[] paramAnonymousArrayOfInetAddress)
        throws Exception
      {
        setComplete(paramAnonymousArrayOfInetAddress[0]);
      }
    }
    .from(getAllByName(paramString));
  }

  public ExecutorService getExecutorService()
  {
    return this.synchronousWorkers;
  }

  public boolean isAffinityThread()
  {
    return this.mAffinity == Thread.currentThread();
  }

  public boolean isRunning()
  {
    return this.mSelector != null;
  }

  public void listen(final InetAddress paramInetAddress, final int paramInt, final ListenCallback paramListenCallback)
  {
    run(new Runnable()
    {
      public void run()
      {
        try
        {
          final ServerSocketChannel localServerSocketChannel = ServerSocketChannel.open();
          ServerSocketChannelWrapper localServerSocketChannelWrapper = new ServerSocketChannelWrapper(localServerSocketChannel);
          if (paramInetAddress == null);
          for (Object localObject = new InetSocketAddress(paramInt); ; localObject = new InetSocketAddress(paramInetAddress, paramInt))
          {
            localServerSocketChannel.socket().bind((SocketAddress)localObject);
            localObject = localServerSocketChannelWrapper.register(AsyncServer.this.mSelector);
            ((SelectionKey)localObject).attach(paramListenCallback);
            paramListenCallback.onListening(new AsyncServerSocket()
            {
              public void stop()
              {
                try
                {
                  localServerSocketChannel.close();
                  try
                  {
                    label7: this.val$key.cancel();
                    return;
                  }
                  catch (Exception localException1)
                  {
                  }
                }
                catch (Exception localException2)
                {
                  break label7;
                }
              }
            });
            return;
          }
        }
        catch (Exception localException)
        {
          paramListenCallback.onCompleted(localException);
        }
      }
    });
  }

  protected void onDataTransmitted(int paramInt)
  {
  }

  public AsyncDatagramSocket openDatagram()
    throws IOException
  {
    final DatagramChannel localDatagramChannel = DatagramChannel.open();
    final AsyncDatagramSocket localAsyncDatagramSocket = new AsyncDatagramSocket();
    localAsyncDatagramSocket.attach(localDatagramChannel);
    run(new Runnable()
    {
      public void run()
      {
        try
        {
          localDatagramChannel.socket().bind(null);
          AsyncServer.this.handleSocket(localAsyncDatagramSocket);
          return;
        }
        catch (Exception localException)
        {
          Log.e("NIO", "Datagram error", localException);
        }
      }
    });
    return localAsyncDatagramSocket;
  }

  public Object post(final CompletedCallback paramCompletedCallback, final Exception paramException)
  {
    return post(new Runnable()
    {
      public void run()
      {
        paramCompletedCallback.onCompleted(paramException);
      }
    });
  }

  public Object post(Runnable paramRunnable)
  {
    return postDelayed(paramRunnable, 0L);
  }

  public Object postDelayed(Runnable paramRunnable, long paramLong)
  {
    long l = paramLong;
    if (paramLong != 0L);
    try
    {
      l = paramLong + System.currentTimeMillis();
      LinkedList localLinkedList = this.mQueue;
      paramRunnable = new Scheduled(paramRunnable, l);
      localLinkedList.add(paramRunnable);
      if (this.mSelector == null)
        run(false, true);
      if ((Thread.currentThread() != this.mAffinity) && (this.mSelector != null))
        this.mSelector.wakeup();
      return paramRunnable;
    }
    finally
    {
    }
    throw paramRunnable;
  }

  public void removeAllCallbacks(Object paramObject)
  {
    try
    {
      this.mQueue.remove(paramObject);
      return;
    }
    finally
    {
    }
    throw paramObject;
  }

  public void run()
  {
    run(false, false);
  }

  public void run(final Runnable paramRunnable)
  {
    if (Thread.currentThread() == this.mAffinity)
    {
      post(paramRunnable);
      lockAndRunQueue(this, this.mQueue);
      return;
    }
    final Semaphore localSemaphore = new Semaphore(0);
    post(new Runnable()
    {
      public void run()
      {
        paramRunnable.run();
        localSemaphore.release();
      }
    });
    try
    {
      localSemaphore.acquire();
      return;
    }
    catch (InterruptedException paramRunnable)
    {
      Log.e("NIO", "run", paramRunnable);
    }
  }

  // ERROR //
  public void run(final boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   6: ifnull +71 -> 77
    //   9: ldc 62
    //   11: ldc_w 605
    //   14: invokestatic 477	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: getstatic 95	fm/qingting/async/AsyncServer:$assertionsDisabled	Z
    //   21: ifne +26 -> 47
    //   24: invokestatic 182	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   27: aload_0
    //   28: getfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   31: if_acmpeq +16 -> 47
    //   34: new 202	java/lang/AssertionError
    //   37: dup
    //   38: invokespecial 203	java/lang/AssertionError:<init>	()V
    //   41: athrow
    //   42: astore_3
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_3
    //   46: athrow
    //   47: iconst_1
    //   48: istore 5
    //   50: aload_0
    //   51: getfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   54: astore_3
    //   55: aload_0
    //   56: getfield 136	fm/qingting/async/AsyncServer:mQueue	Ljava/util/LinkedList;
    //   59: astore 4
    //   61: aload_0
    //   62: monitorexit
    //   63: iload 5
    //   65: ifeq +124 -> 189
    //   68: aload_0
    //   69: aload_3
    //   70: aload 4
    //   72: iconst_0
    //   73: invokestatic 325	fm/qingting/async/AsyncServer:runLoop	(Lfm/qingting/async/AsyncServer;Ljava/nio/channels/Selector;Ljava/util/LinkedList;Z)V
    //   76: return
    //   77: invokestatic 611	java/nio/channels/spi/SelectorProvider:provider	()Ljava/nio/channels/spi/SelectorProvider;
    //   80: invokevirtual 615	java/nio/channels/spi/SelectorProvider:openSelector	()Ljava/nio/channels/spi/AbstractSelector;
    //   83: astore_3
    //   84: aload_0
    //   85: aload_3
    //   86: putfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   89: aload_0
    //   90: getfield 136	fm/qingting/async/AsyncServer:mQueue	Ljava/util/LinkedList;
    //   93: astore 4
    //   95: iload_2
    //   96: ifeq +57 -> 153
    //   99: aload_0
    //   100: new 14	fm/qingting/async/AsyncServer$13
    //   103: dup
    //   104: aload_0
    //   105: ldc_w 617
    //   108: aload_3
    //   109: aload 4
    //   111: iload_1
    //   112: invokespecial 620	fm/qingting/async/AsyncServer$13:<init>	(Lfm/qingting/async/AsyncServer;Ljava/lang/String;Ljava/nio/channels/Selector;Ljava/util/LinkedList;Z)V
    //   115: putfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   118: aload_0
    //   119: invokespecial 622	fm/qingting/async/AsyncServer:addMe	()Z
    //   122: istore 6
    //   124: iload 6
    //   126: ifne +37 -> 163
    //   129: aload_0
    //   130: getfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   133: invokevirtual 484	java/nio/channels/Selector:close	()V
    //   136: aload_0
    //   137: aconst_null
    //   138: putfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   141: aload_0
    //   142: aconst_null
    //   143: putfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   146: aload_0
    //   147: monitorexit
    //   148: return
    //   149: astore_3
    //   150: aload_0
    //   151: monitorexit
    //   152: return
    //   153: aload_0
    //   154: invokestatic 182	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   157: putfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   160: goto -42 -> 118
    //   163: iload_2
    //   164: ifeq +38 -> 202
    //   167: aload_0
    //   168: getfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   171: invokevirtual 625	java/lang/Thread:start	()V
    //   174: aload_0
    //   175: monitorexit
    //   176: return
    //   177: astore_3
    //   178: ldc 62
    //   180: ldc_w 339
    //   183: aload_3
    //   184: invokestatic 345	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   187: pop
    //   188: return
    //   189: aload_0
    //   190: aload_3
    //   191: aload 4
    //   193: iload_1
    //   194: invokestatic 175	fm/qingting/async/AsyncServer:run	(Lfm/qingting/async/AsyncServer;Ljava/nio/channels/Selector;Ljava/util/LinkedList;Z)V
    //   197: return
    //   198: astore_3
    //   199: goto -63 -> 136
    //   202: iconst_0
    //   203: istore 5
    //   205: goto -144 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   2	42	42	finally
    //   43	45	42	finally
    //   50	61	42	finally
    //   61	63	42	finally
    //   77	95	42	finally
    //   99	118	42	finally
    //   118	124	42	finally
    //   129	136	42	finally
    //   136	148	42	finally
    //   150	152	42	finally
    //   153	160	42	finally
    //   167	176	42	finally
    //   77	95	149	java/io/IOException
    //   68	76	177	java/lang/Exception
    //   129	136	198	java/lang/Exception
  }

  public void setAutostart(boolean paramBoolean)
  {
    this.mAutoStart = paramBoolean;
  }

  // ERROR //
  public void stop()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   6: ifnonnull +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: new 22	fm/qingting/async/AsyncServer$4
    //   16: dup
    //   17: aload_0
    //   18: aload_0
    //   19: getfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   22: invokespecial 631	fm/qingting/async/AsyncServer$4:<init>	(Lfm/qingting/async/AsyncServer;Ljava/nio/channels/Selector;)V
    //   25: invokevirtual 210	fm/qingting/async/AsyncServer:post	(Ljava/lang/Runnable;)Ljava/lang/Object;
    //   28: pop
    //   29: getstatic 127	fm/qingting/async/AsyncServer:mServers	Ljava/util/WeakHashMap;
    //   32: astore_1
    //   33: aload_1
    //   34: monitorenter
    //   35: getstatic 127	fm/qingting/async/AsyncServer:mServers	Ljava/util/WeakHashMap;
    //   38: aload_0
    //   39: getfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   42: invokevirtual 347	java/util/WeakHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   45: pop
    //   46: aload_1
    //   47: monitorexit
    //   48: aload_0
    //   49: new 133	java/util/LinkedList
    //   52: dup
    //   53: invokespecial 134	java/util/LinkedList:<init>	()V
    //   56: putfield 136	fm/qingting/async/AsyncServer:mQueue	Ljava/util/LinkedList;
    //   59: aload_0
    //   60: aconst_null
    //   61: putfield 155	fm/qingting/async/AsyncServer:mSelector	Ljava/nio/channels/Selector;
    //   64: aload_0
    //   65: aconst_null
    //   66: putfield 188	fm/qingting/async/AsyncServer:mAffinity	Ljava/lang/Thread;
    //   69: aload_0
    //   70: monitorexit
    //   71: return
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    //   77: astore_2
    //   78: aload_1
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	11	72	finally
    //   12	35	72	finally
    //   48	71	72	finally
    //   73	75	72	finally
    //   80	82	72	finally
    //   35	48	77	finally
    //   78	80	77	finally
  }

  public static class AsyncSemaphore
  {
    Semaphore semaphore = new Semaphore(0);

    public void acquire()
      throws InterruptedException
    {
      AsyncServer.ThreadQueue localThreadQueue = AsyncServer.getOrCreateThreadQueue(Thread.currentThread());
      AsyncSemaphore localAsyncSemaphore = localThreadQueue.waiter;
      localThreadQueue.waiter = this;
      Semaphore localSemaphore = localThreadQueue.queueSemaphore;
      try
      {
        boolean bool = this.semaphore.tryAcquire();
        Runnable localRunnable;
        if (bool)
        {
          return;
          localRunnable.run();
        }
        do
        {
          localRunnable = localThreadQueue.remove();
          if (localRunnable != null)
            break;
          localSemaphore.acquire(Math.max(1, localSemaphore.availablePermits()));
          bool = this.semaphore.tryAcquire();
        }
        while (!bool);
        return;
      }
      finally
      {
        localThreadQueue.waiter = localAsyncSemaphore;
      }
    }

    public void release()
    {
      this.semaphore.release();
      synchronized (AsyncServer.mThreadQueues)
      {
        Iterator localIterator = AsyncServer.mThreadQueues.values().iterator();
        while (localIterator.hasNext())
        {
          AsyncServer.ThreadQueue localThreadQueue = (AsyncServer.ThreadQueue)localIterator.next();
          if (localThreadQueue.waiter == this)
            localThreadQueue.queueSemaphore.release();
        }
      }
    }

    public boolean tryAcquire(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      paramLong = TimeUnit.MILLISECONDS.convert(paramLong, paramTimeUnit);
      paramTimeUnit = AsyncServer.getOrCreateThreadQueue(Thread.currentThread());
      AsyncSemaphore localAsyncSemaphore = paramTimeUnit.waiter;
      paramTimeUnit.waiter = this;
      Semaphore localSemaphore = paramTimeUnit.queueSemaphore;
      long l1;
      long l2;
      do
      {
        try
        {
          bool = this.semaphore.tryAcquire();
          if (bool)
            return true;
          l1 = System.currentTimeMillis();
          while (true)
          {
            Runnable localRunnable = paramTimeUnit.remove();
            if (localRunnable == null)
            {
              bool = localSemaphore.tryAcquire(Math.max(1, localSemaphore.availablePermits()), paramLong, TimeUnit.MILLISECONDS);
              if (bool)
                break;
              return false;
            }
            localRunnable.run();
          }
        }
        finally
        {
          paramTimeUnit.waiter = localAsyncSemaphore;
        }
        boolean bool = this.semaphore.tryAcquire();
        if (bool)
        {
          paramTimeUnit.waiter = localAsyncSemaphore;
          return true;
        }
        l2 = System.currentTimeMillis();
      }
      while (l2 - l1 < paramLong);
      paramTimeUnit.waiter = localAsyncSemaphore;
      return false;
    }
  }

  private class ConnectFuture extends SimpleFuture<AsyncNetworkSocket>
  {
    ConnectCallback callback;
    SocketChannel socket;

    private ConnectFuture()
    {
    }

    protected void cancelCleanup()
    {
      super.cancelCleanup();
      try
      {
        if (this.socket != null)
          this.socket.close();
        return;
      }
      catch (IOException localIOException)
      {
      }
    }
  }

  static class Reclaimer
    implements Comparator<ByteBuffer>
  {
    public int compare(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2)
    {
      if (paramByteBuffer1.capacity() > paramByteBuffer2.capacity())
        return 1;
      return -1;
    }
  }

  private static class RunnableWrapper
    implements Runnable
  {
    Handler handler;
    boolean hasRun;
    Runnable runnable;
    AsyncServer.ThreadQueue threadQueue;

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 27	fm/qingting/async/AsyncServer$RunnableWrapper:hasRun	Z
      //   6: ifeq +6 -> 12
      //   9: aload_0
      //   10: monitorexit
      //   11: return
      //   12: aload_0
      //   13: iconst_1
      //   14: putfield 27	fm/qingting/async/AsyncServer$RunnableWrapper:hasRun	Z
      //   17: aload_0
      //   18: monitorexit
      //   19: aload_0
      //   20: getfield 29	fm/qingting/async/AsyncServer$RunnableWrapper:runnable	Ljava/lang/Runnable;
      //   23: ifnull +12 -> 35
      //   26: aload_0
      //   27: getfield 29	fm/qingting/async/AsyncServer$RunnableWrapper:runnable	Ljava/lang/Runnable;
      //   30: invokeinterface 31 1 0
      //   35: aload_0
      //   36: getfield 33	fm/qingting/async/AsyncServer$RunnableWrapper:threadQueue	Lfm/qingting/async/AsyncServer$ThreadQueue;
      //   39: aload_0
      //   40: invokevirtual 39	fm/qingting/async/AsyncServer$ThreadQueue:remove	(Ljava/lang/Object;)Z
      //   43: pop
      //   44: aload_0
      //   45: getfield 41	fm/qingting/async/AsyncServer$RunnableWrapper:handler	Landroid/os/Handler;
      //   48: aload_0
      //   49: invokevirtual 47	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
      //   52: aload_0
      //   53: aconst_null
      //   54: putfield 33	fm/qingting/async/AsyncServer$RunnableWrapper:threadQueue	Lfm/qingting/async/AsyncServer$ThreadQueue;
      //   57: aload_0
      //   58: aconst_null
      //   59: putfield 41	fm/qingting/async/AsyncServer$RunnableWrapper:handler	Landroid/os/Handler;
      //   62: aload_0
      //   63: aconst_null
      //   64: putfield 29	fm/qingting/async/AsyncServer$RunnableWrapper:runnable	Ljava/lang/Runnable;
      //   67: return
      //   68: astore_1
      //   69: aload_0
      //   70: monitorexit
      //   71: aload_1
      //   72: athrow
      //   73: astore_1
      //   74: aload_0
      //   75: getfield 33	fm/qingting/async/AsyncServer$RunnableWrapper:threadQueue	Lfm/qingting/async/AsyncServer$ThreadQueue;
      //   78: aload_0
      //   79: invokevirtual 39	fm/qingting/async/AsyncServer$ThreadQueue:remove	(Ljava/lang/Object;)Z
      //   82: pop
      //   83: aload_0
      //   84: getfield 41	fm/qingting/async/AsyncServer$RunnableWrapper:handler	Landroid/os/Handler;
      //   87: aload_0
      //   88: invokevirtual 47	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
      //   91: aload_0
      //   92: aconst_null
      //   93: putfield 33	fm/qingting/async/AsyncServer$RunnableWrapper:threadQueue	Lfm/qingting/async/AsyncServer$ThreadQueue;
      //   96: aload_0
      //   97: aconst_null
      //   98: putfield 41	fm/qingting/async/AsyncServer$RunnableWrapper:handler	Landroid/os/Handler;
      //   101: aload_0
      //   102: aconst_null
      //   103: putfield 29	fm/qingting/async/AsyncServer$RunnableWrapper:runnable	Ljava/lang/Runnable;
      //   106: aload_1
      //   107: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   2	11	68	finally
      //   12	19	68	finally
      //   69	71	68	finally
      //   19	35	73	finally
    }
  }

  private static class Scheduled
  {
    public Runnable runnable;
    public long time;

    public Scheduled(Runnable paramRunnable, long paramLong)
    {
      this.runnable = paramRunnable;
      this.time = paramLong;
    }
  }

  public static class ThreadQueue extends LinkedList<Runnable>
  {
    Semaphore queueSemaphore = new Semaphore(0);
    AsyncServer.AsyncSemaphore waiter;

    public boolean add(Runnable paramRunnable)
    {
      try
      {
        boolean bool = super.add(paramRunnable);
        return bool;
      }
      finally
      {
      }
      throw paramRunnable;
    }

    public Runnable remove()
    {
      try
      {
        if (isEmpty())
          return null;
        Runnable localRunnable = (Runnable)super.remove();
        return localRunnable;
      }
      finally
      {
      }
    }

    public boolean remove(Object paramObject)
    {
      try
      {
        boolean bool = super.remove(paramObject);
        return bool;
      }
      finally
      {
      }
      throw paramObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.AsyncServer
 * JD-Core Version:    0.6.2
 */