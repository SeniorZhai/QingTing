package fm.qingting.async;

import fm.qingting.async.callback.DataCallback;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class PushParser
{
  static Hashtable<Class, Method> mTable;
  private ArrayList<Object> mArgs = new ArrayList();
  private TapCallback mCallback;
  DataEmitter mEmitter;
  int mNeeded = 0;
  DataEmitterReader mReader;
  private LinkedList<Object> mWaiting = new LinkedList();
  ByteOrder order = ByteOrder.BIG_ENDIAN;

  static
  {
    if (!PushParser.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      mTable = new Hashtable();
      return;
    }
  }

  public PushParser(DataEmitter paramDataEmitter)
  {
    this.mEmitter = paramDataEmitter;
    this.mReader = new DataEmitterReader();
    this.mEmitter.setDataCallback(this.mReader);
  }

  static Method getTap(TapCallback paramTapCallback)
  {
    Object localObject1 = (Method)mTable.get(paramTapCallback.getClass());
    if (localObject1 != null)
      return localObject1;
    localObject1 = paramTapCallback.getClass().getMethods();
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = localObject1[i];
      if ("tap".equals(localObject2.getName()))
      {
        mTable.put(paramTapCallback.getClass(), localObject2);
        return localObject2;
      }
      i += 1;
    }
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public PushParser noop()
  {
    this.mWaiting.add(Object.class);
    return this;
  }

  public PushParser order(ByteOrder paramByteOrder)
  {
    this.order = paramByteOrder;
    return this;
  }

  public ByteOrder order()
  {
    return this.order;
  }

  public PushParser readBuffer(int paramInt)
  {
    if (paramInt != -1)
      this.mNeeded += paramInt;
    BufferWaiter localBufferWaiter = new BufferWaiter();
    localBufferWaiter.length = paramInt;
    this.mWaiting.add(localBufferWaiter);
    return this;
  }

  public PushParser readByte()
  {
    this.mNeeded += 1;
    this.mWaiting.add(Byte.TYPE);
    return this;
  }

  public PushParser readInt()
  {
    this.mNeeded += 4;
    this.mWaiting.add(Integer.TYPE);
    return this;
  }

  public PushParser readLenBuffer()
  {
    readInt();
    BufferWaiter localBufferWaiter = new BufferWaiter();
    localBufferWaiter.length = -1;
    this.mWaiting.add(localBufferWaiter);
    return this;
  }

  public PushParser readLong()
  {
    this.mNeeded += 8;
    this.mWaiting.add(Long.TYPE);
    return this;
  }

  public PushParser readShort()
  {
    this.mNeeded += 2;
    this.mWaiting.add(Short.TYPE);
    return this;
  }

  public PushParser readString()
  {
    readInt();
    StringWaiter localStringWaiter = new StringWaiter();
    localStringWaiter.length = -1;
    this.mWaiting.add(localStringWaiter);
    return this;
  }

  Exception stack()
  {
    try
    {
      throw new Exception();
    }
    catch (Exception localException)
    {
      return localException;
    }
  }

  public void tap(TapCallback paramTapCallback)
  {
    assert (this.mCallback == null);
    assert (this.mWaiting.size() > 0);
    this.mCallback = paramTapCallback;
    new DataCallback()
    {
      static
      {
        if (!PushParser.class.desiredAssertionStatus());
        for (boolean bool = true; ; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }

      // ERROR //
      public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
      {
        // Byte code:
        //   0: aload_2
        //   1: ifnull +15 -> 16
        //   4: aload_2
        //   5: aload_0
        //   6: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   9: getfield 46	fm/qingting/async/PushParser:order	Ljava/nio/ByteOrder;
        //   12: invokevirtual 51	fm/qingting/async/ByteBufferList:order	(Ljava/nio/ByteOrder;)Lfm/qingting/async/ByteBufferList;
        //   15: pop
        //   16: aload_0
        //   17: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   20: invokestatic 55	fm/qingting/async/PushParser:access$000	(Lfm/qingting/async/PushParser;)Ljava/util/LinkedList;
        //   23: invokevirtual 61	java/util/LinkedList:size	()I
        //   26: ifle +20 -> 46
        //   29: aload_0
        //   30: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   33: invokestatic 55	fm/qingting/async/PushParser:access$000	(Lfm/qingting/async/PushParser;)Ljava/util/LinkedList;
        //   36: invokevirtual 65	java/util/LinkedList:peek	()Ljava/lang/Object;
        //   39: astore 4
        //   41: aload 4
        //   43: ifnonnull +52 -> 95
        //   46: aload_0
        //   47: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   50: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   53: invokevirtual 75	java/util/ArrayList:toArray	()[Ljava/lang/Object;
        //   56: astore_1
        //   57: aload_0
        //   58: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   61: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   64: invokevirtual 78	java/util/ArrayList:clear	()V
        //   67: aload_0
        //   68: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   71: invokestatic 82	fm/qingting/async/PushParser:access$200	(Lfm/qingting/async/PushParser;)Lfm/qingting/async/TapCallback;
        //   74: astore_2
        //   75: aload_0
        //   76: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   79: aconst_null
        //   80: invokestatic 86	fm/qingting/async/PushParser:access$202	(Lfm/qingting/async/PushParser;Lfm/qingting/async/TapCallback;)Lfm/qingting/async/TapCallback;
        //   83: pop
        //   84: aload_2
        //   85: invokestatic 90	fm/qingting/async/PushParser:getTap	(Lfm/qingting/async/TapCallback;)Ljava/lang/reflect/Method;
        //   88: aload_2
        //   89: aload_1
        //   90: invokevirtual 96	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   93: pop
        //   94: return
        //   95: aload 4
        //   97: getstatic 102	java/lang/Integer:TYPE	Ljava/lang/Class;
        //   100: if_acmpne +75 -> 175
        //   103: aload_0
        //   104: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   107: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   110: aload_2
        //   111: invokevirtual 105	fm/qingting/async/ByteBufferList:getInt	()I
        //   114: invokestatic 109	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   117: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   120: pop
        //   121: aload_0
        //   122: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   125: astore_3
        //   126: aload_3
        //   127: aload_3
        //   128: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   131: iconst_4
        //   132: isub
        //   133: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   136: aload_0
        //   137: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   140: invokestatic 55	fm/qingting/async/PushParser:access$000	(Lfm/qingting/async/PushParser;)Ljava/util/LinkedList;
        //   143: invokevirtual 120	java/util/LinkedList:remove	()Ljava/lang/Object;
        //   146: pop
        //   147: goto -131 -> 16
        //   150: astore_1
        //   151: getstatic 25	fm/qingting/async/PushParser$1:$assertionsDisabled	Z
        //   154: ifne +571 -> 725
        //   157: aload_0
        //   158: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   161: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   164: ifne +561 -> 725
        //   167: new 122	java/lang/AssertionError
        //   170: dup
        //   171: invokespecial 123	java/lang/AssertionError:<init>	()V
        //   174: athrow
        //   175: aload 4
        //   177: getstatic 126	java/lang/Short:TYPE	Ljava/lang/Class;
        //   180: if_acmpne +39 -> 219
        //   183: aload_0
        //   184: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   187: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   190: aload_2
        //   191: invokevirtual 129	fm/qingting/async/ByteBufferList:getShort	()I
        //   194: invokestatic 109	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   197: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   200: pop
        //   201: aload_0
        //   202: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   205: astore_3
        //   206: aload_3
        //   207: aload_3
        //   208: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   211: iconst_2
        //   212: isub
        //   213: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   216: goto -80 -> 136
        //   219: aload 4
        //   221: getstatic 132	java/lang/Byte:TYPE	Ljava/lang/Class;
        //   224: if_acmpne +39 -> 263
        //   227: aload_0
        //   228: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   231: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   234: aload_2
        //   235: invokevirtual 136	fm/qingting/async/ByteBufferList:get	()B
        //   238: invokestatic 139	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
        //   241: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   244: pop
        //   245: aload_0
        //   246: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   249: astore_3
        //   250: aload_3
        //   251: aload_3
        //   252: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   255: iconst_1
        //   256: isub
        //   257: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   260: goto -124 -> 136
        //   263: aload 4
        //   265: getstatic 142	java/lang/Long:TYPE	Ljava/lang/Class;
        //   268: if_acmpne +40 -> 308
        //   271: aload_0
        //   272: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   275: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   278: aload_2
        //   279: invokevirtual 146	fm/qingting/async/ByteBufferList:getLong	()J
        //   282: invokestatic 149	java/lang/Long:valueOf	(J)Ljava/lang/Long;
        //   285: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   288: pop
        //   289: aload_0
        //   290: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   293: astore_3
        //   294: aload_3
        //   295: aload_3
        //   296: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   299: bipush 8
        //   301: isub
        //   302: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   305: goto -169 -> 136
        //   308: aload 4
        //   310: ldc 4
        //   312: if_acmpne +18 -> 330
        //   315: aload_0
        //   316: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   319: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   322: aconst_null
        //   323: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   326: pop
        //   327: goto -191 -> 136
        //   330: aload 4
        //   332: instanceof 151
        //   335: ifeq +167 -> 502
        //   338: aload 4
        //   340: checkcast 151	fm/qingting/async/PushParser$UntilWaiter
        //   343: astore_3
        //   344: new 48	fm/qingting/async/ByteBufferList
        //   347: dup
        //   348: invokespecial 152	fm/qingting/async/ByteBufferList:<init>	()V
        //   351: astore 4
        //   353: iconst_1
        //   354: istore 6
        //   356: iload 6
        //   358: istore 7
        //   360: aload_2
        //   361: invokevirtual 153	fm/qingting/async/ByteBufferList:size	()I
        //   364: ifle +74 -> 438
        //   367: aload_2
        //   368: invokevirtual 156	fm/qingting/async/ByteBufferList:remove	()Ljava/nio/ByteBuffer;
        //   371: astore 5
        //   373: aload 5
        //   375: invokevirtual 162	java/nio/ByteBuffer:mark	()Ljava/nio/Buffer;
        //   378: pop
        //   379: iconst_0
        //   380: istore 8
        //   382: iload 6
        //   384: istore 7
        //   386: aload 5
        //   388: invokevirtual 165	java/nio/ByteBuffer:remaining	()I
        //   391: ifle +21 -> 412
        //   394: aload 5
        //   396: invokevirtual 166	java/nio/ByteBuffer:get	()B
        //   399: aload_3
        //   400: getfield 170	fm/qingting/async/PushParser$UntilWaiter:value	B
        //   403: if_icmpeq +384 -> 787
        //   406: iconst_1
        //   407: istore 6
        //   409: goto +360 -> 769
        //   412: aload 5
        //   414: invokevirtual 173	java/nio/ByteBuffer:reset	()Ljava/nio/Buffer;
        //   417: pop
        //   418: iload 7
        //   420: ifne +60 -> 480
        //   423: aload_2
        //   424: iconst_0
        //   425: aload 5
        //   427: invokevirtual 176	fm/qingting/async/ByteBufferList:add	(ILjava/nio/ByteBuffer;)V
        //   430: aload_2
        //   431: aload 4
        //   433: iload 8
        //   435: invokevirtual 179	fm/qingting/async/ByteBufferList:get	(Lfm/qingting/async/ByteBufferList;I)V
        //   438: aload_3
        //   439: getfield 183	fm/qingting/async/PushParser$UntilWaiter:callback	Lfm/qingting/async/callback/DataCallback;
        //   442: ifnull +15 -> 457
        //   445: aload_3
        //   446: getfield 183	fm/qingting/async/PushParser$UntilWaiter:callback	Lfm/qingting/async/callback/DataCallback;
        //   449: aload_1
        //   450: aload 4
        //   452: invokeinterface 184 3 0
        //   457: iload 7
        //   459: ifne +35 -> 494
        //   462: aload_0
        //   463: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   466: astore_3
        //   467: aload_3
        //   468: aload_3
        //   469: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   472: iconst_1
        //   473: isub
        //   474: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   477: goto -341 -> 136
        //   480: aload 4
        //   482: aload 5
        //   484: invokevirtual 187	fm/qingting/async/ByteBufferList:add	(Ljava/nio/ByteBuffer;)V
        //   487: iload 7
        //   489: istore 6
        //   491: goto -135 -> 356
        //   494: new 42	java/lang/Exception
        //   497: dup
        //   498: invokespecial 188	java/lang/Exception:<init>	()V
        //   501: athrow
        //   502: aload 4
        //   504: instanceof 190
        //   507: ifne +11 -> 518
        //   510: aload 4
        //   512: instanceof 192
        //   515: ifeq +196 -> 711
        //   518: aload 4
        //   520: checkcast 190	fm/qingting/async/PushParser$BufferWaiter
        //   523: astore_3
        //   524: aload_3
        //   525: getfield 195	fm/qingting/async/PushParser$BufferWaiter:length	I
        //   528: istore 7
        //   530: iload 7
        //   532: istore 6
        //   534: iload 7
        //   536: iconst_m1
        //   537: if_icmpne +78 -> 615
        //   540: aload_0
        //   541: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   544: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   547: aload_0
        //   548: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   551: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   554: invokevirtual 196	java/util/ArrayList:size	()I
        //   557: iconst_1
        //   558: isub
        //   559: invokevirtual 199	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   562: checkcast 98	java/lang/Integer
        //   565: invokevirtual 202	java/lang/Integer:intValue	()I
        //   568: istore 6
        //   570: aload_0
        //   571: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   574: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   577: aload_0
        //   578: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   581: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   584: invokevirtual 196	java/util/ArrayList:size	()I
        //   587: iconst_1
        //   588: isub
        //   589: invokevirtual 204	java/util/ArrayList:remove	(I)Ljava/lang/Object;
        //   592: pop
        //   593: aload_3
        //   594: iload 6
        //   596: putfield 195	fm/qingting/async/PushParser$BufferWaiter:length	I
        //   599: aload_0
        //   600: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   603: astore_3
        //   604: aload_3
        //   605: aload_3
        //   606: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   609: iload 6
        //   611: iadd
        //   612: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   615: aload_2
        //   616: invokevirtual 205	fm/qingting/async/ByteBufferList:remaining	()I
        //   619: iload 6
        //   621: if_icmpge +11 -> 632
        //   624: new 42	java/lang/Exception
        //   627: dup
        //   628: invokespecial 188	java/lang/Exception:<init>	()V
        //   631: athrow
        //   632: iload 6
        //   634: ifle +130 -> 764
        //   637: iload 6
        //   639: newarray byte
        //   641: astore_3
        //   642: aload_2
        //   643: aload_3
        //   644: invokevirtual 208	fm/qingting/async/ByteBufferList:get	([B)V
        //   647: aload_0
        //   648: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   651: astore 5
        //   653: aload 5
        //   655: aload 5
        //   657: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   660: iload 6
        //   662: isub
        //   663: putfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   666: aload 4
        //   668: instanceof 192
        //   671: ifeq +25 -> 696
        //   674: aload_0
        //   675: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   678: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   681: new 210	java/lang/String
        //   684: dup
        //   685: aload_3
        //   686: invokespecial 212	java/lang/String:<init>	([B)V
        //   689: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   692: pop
        //   693: goto -557 -> 136
        //   696: aload_0
        //   697: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   700: invokestatic 69	fm/qingting/async/PushParser:access$100	(Lfm/qingting/async/PushParser;)Ljava/util/ArrayList;
        //   703: aload_3
        //   704: invokevirtual 113	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   707: pop
        //   708: goto -572 -> 136
        //   711: getstatic 25	fm/qingting/async/PushParser$1:$assertionsDisabled	Z
        //   714: ifne -578 -> 136
        //   717: new 122	java/lang/AssertionError
        //   720: dup
        //   721: invokespecial 123	java/lang/AssertionError:<init>	()V
        //   724: athrow
        //   725: aload_0
        //   726: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   729: getfield 216	fm/qingting/async/PushParser:mReader	Lfm/qingting/async/DataEmitterReader;
        //   732: aload_0
        //   733: getfield 30	fm/qingting/async/PushParser$1:this$0	Lfm/qingting/async/PushParser;
        //   736: getfield 117	fm/qingting/async/PushParser:mNeeded	I
        //   739: aload_0
        //   740: invokevirtual 222	fm/qingting/async/DataEmitterReader:read	(ILfm/qingting/async/callback/DataCallback;)V
        //   743: return
        //   744: astore_1
        //   745: getstatic 25	fm/qingting/async/PushParser$1:$assertionsDisabled	Z
        //   748: ifne +11 -> 759
        //   751: new 122	java/lang/AssertionError
        //   754: dup
        //   755: invokespecial 123	java/lang/AssertionError:<init>	()V
        //   758: athrow
        //   759: aload_1
        //   760: invokevirtual 225	java/lang/Exception:printStackTrace	()V
        //   763: return
        //   764: aconst_null
        //   765: astore_3
        //   766: goto -119 -> 647
        //   769: iload 6
        //   771: istore 7
        //   773: iload 6
        //   775: ifeq -363 -> 412
        //   778: iload 8
        //   780: iconst_1
        //   781: iadd
        //   782: istore 8
        //   784: goto -402 -> 382
        //   787: iconst_0
        //   788: istore 6
        //   790: goto -21 -> 769
        //
        // Exception table:
        //   from	to	target	type
        //   4	16	150	java/lang/Exception
        //   16	41	150	java/lang/Exception
        //   95	136	150	java/lang/Exception
        //   136	147	150	java/lang/Exception
        //   175	216	150	java/lang/Exception
        //   219	260	150	java/lang/Exception
        //   263	305	150	java/lang/Exception
        //   315	327	150	java/lang/Exception
        //   330	353	150	java/lang/Exception
        //   360	379	150	java/lang/Exception
        //   386	406	150	java/lang/Exception
        //   412	418	150	java/lang/Exception
        //   423	438	150	java/lang/Exception
        //   438	457	150	java/lang/Exception
        //   462	477	150	java/lang/Exception
        //   480	487	150	java/lang/Exception
        //   494	502	150	java/lang/Exception
        //   502	518	150	java/lang/Exception
        //   518	530	150	java/lang/Exception
        //   540	615	150	java/lang/Exception
        //   615	632	150	java/lang/Exception
        //   637	647	150	java/lang/Exception
        //   647	693	150	java/lang/Exception
        //   696	708	150	java/lang/Exception
        //   711	725	150	java/lang/Exception
        //   46	94	744	java/lang/Exception
      }
    };
  }

  public PushParser until(byte paramByte, DataCallback paramDataCallback)
  {
    UntilWaiter localUntilWaiter = new UntilWaiter();
    localUntilWaiter.value = paramByte;
    localUntilWaiter.callback = paramDataCallback;
    this.mWaiting.add(localUntilWaiter);
    this.mNeeded += 1;
    return this;
  }

  static class BufferWaiter
  {
    int length;
  }

  static class StringWaiter extends PushParser.BufferWaiter
  {
  }

  static class UntilWaiter
  {
    DataCallback callback;
    byte value;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.PushParser
 * JD-Core Version:    0.6.2
 */