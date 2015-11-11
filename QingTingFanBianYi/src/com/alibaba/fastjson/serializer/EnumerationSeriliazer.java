package com.alibaba.fastjson.serializer;

public class EnumerationSeriliazer
  implements ObjectSerializer
{
  public static EnumerationSeriliazer instance = new EnumerationSeriliazer();

  // ERROR //
  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, java.lang.reflect.Type paramType)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 27	com/alibaba/fastjson/serializer/JSONSerializer:getWriter	()Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   4: astore 7
    //   6: aload_2
    //   7: ifnonnull +28 -> 35
    //   10: aload 7
    //   12: getstatic 33	com/alibaba/fastjson/serializer/SerializerFeature:WriteNullListAsEmpty	Lcom/alibaba/fastjson/serializer/SerializerFeature;
    //   15: invokevirtual 39	com/alibaba/fastjson/serializer/SerializeWriter:isEnabled	(Lcom/alibaba/fastjson/serializer/SerializerFeature;)Z
    //   18: ifeq +11 -> 29
    //   21: aload 7
    //   23: ldc 41
    //   25: invokevirtual 44	com/alibaba/fastjson/serializer/SerializeWriter:write	(Ljava/lang/String;)V
    //   28: return
    //   29: aload 7
    //   31: invokevirtual 47	com/alibaba/fastjson/serializer/SerializeWriter:writeNull	()V
    //   34: return
    //   35: aconst_null
    //   36: astore 6
    //   38: aload 6
    //   40: astore 5
    //   42: aload_1
    //   43: getstatic 50	com/alibaba/fastjson/serializer/SerializerFeature:WriteClassName	Lcom/alibaba/fastjson/serializer/SerializerFeature;
    //   46: invokevirtual 51	com/alibaba/fastjson/serializer/JSONSerializer:isEnabled	(Lcom/alibaba/fastjson/serializer/SerializerFeature;)Z
    //   49: ifeq +29 -> 78
    //   52: aload 6
    //   54: astore 5
    //   56: aload 4
    //   58: instanceof 53
    //   61: ifeq +17 -> 78
    //   64: aload 4
    //   66: checkcast 53	java/lang/reflect/ParameterizedType
    //   69: invokeinterface 57 1 0
    //   74: iconst_0
    //   75: aaload
    //   76: astore 5
    //   78: aload_2
    //   79: checkcast 59	java/util/Enumeration
    //   82: astore 6
    //   84: aload_1
    //   85: invokevirtual 63	com/alibaba/fastjson/serializer/JSONSerializer:getContext	()Lcom/alibaba/fastjson/serializer/SerialContext;
    //   88: astore 4
    //   90: aload_1
    //   91: aload 4
    //   93: aload_2
    //   94: aload_3
    //   95: invokevirtual 67	com/alibaba/fastjson/serializer/JSONSerializer:setContext	(Lcom/alibaba/fastjson/serializer/SerialContext;Ljava/lang/Object;Ljava/lang/Object;)V
    //   98: aload 7
    //   100: bipush 91
    //   102: invokevirtual 71	com/alibaba/fastjson/serializer/SerializeWriter:append	(C)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   105: pop
    //   106: iconst_0
    //   107: istore 8
    //   109: aload 6
    //   111: invokeinterface 75 1 0
    //   116: ifeq +77 -> 193
    //   119: aload 6
    //   121: invokeinterface 79 1 0
    //   126: astore_2
    //   127: iload 8
    //   129: iconst_1
    //   130: iadd
    //   131: istore 9
    //   133: iload 8
    //   135: ifeq +11 -> 146
    //   138: aload 7
    //   140: bipush 44
    //   142: invokevirtual 71	com/alibaba/fastjson/serializer/SerializeWriter:append	(C)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   145: pop
    //   146: aload_2
    //   147: ifnonnull +15 -> 162
    //   150: aload 7
    //   152: invokevirtual 47	com/alibaba/fastjson/serializer/SerializeWriter:writeNull	()V
    //   155: iload 9
    //   157: istore 8
    //   159: goto -50 -> 109
    //   162: aload_1
    //   163: aload_2
    //   164: invokevirtual 83	java/lang/Object:getClass	()Ljava/lang/Class;
    //   167: invokevirtual 87	com/alibaba/fastjson/serializer/JSONSerializer:getObjectWriter	(Ljava/lang/Class;)Lcom/alibaba/fastjson/serializer/ObjectSerializer;
    //   170: aload_1
    //   171: aload_2
    //   172: iload 9
    //   174: iconst_1
    //   175: isub
    //   176: invokestatic 93	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   179: aload 5
    //   181: invokeinterface 95 5 0
    //   186: iload 9
    //   188: istore 8
    //   190: goto -81 -> 109
    //   193: aload 7
    //   195: bipush 93
    //   197: invokevirtual 71	com/alibaba/fastjson/serializer/SerializeWriter:append	(C)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   200: pop
    //   201: aload_1
    //   202: aload 4
    //   204: invokevirtual 98	com/alibaba/fastjson/serializer/JSONSerializer:setContext	(Lcom/alibaba/fastjson/serializer/SerialContext;)V
    //   207: return
    //   208: astore_2
    //   209: aload_1
    //   210: aload 4
    //   212: invokevirtual 98	com/alibaba/fastjson/serializer/JSONSerializer:setContext	(Lcom/alibaba/fastjson/serializer/SerialContext;)V
    //   215: aload_2
    //   216: athrow
    //   217: astore_2
    //   218: goto -9 -> 209
    //
    // Exception table:
    //   from	to	target	type
    //   98	106	208	finally
    //   138	146	208	finally
    //   150	155	208	finally
    //   162	186	208	finally
    //   109	127	217	finally
    //   193	201	217	finally
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.EnumerationSeriliazer
 * JD-Core Version:    0.6.2
 */