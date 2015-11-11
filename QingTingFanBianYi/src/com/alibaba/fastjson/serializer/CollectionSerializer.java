package com.alibaba.fastjson.serializer;

public class CollectionSerializer
  implements ObjectSerializer
{
  public static final CollectionSerializer instance = new CollectionSerializer();

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
    //   79: checkcast 59	java/util/Collection
    //   82: astore 6
    //   84: aload_1
    //   85: invokevirtual 63	com/alibaba/fastjson/serializer/JSONSerializer:getContext	()Lcom/alibaba/fastjson/serializer/SerialContext;
    //   88: astore 4
    //   90: aload_1
    //   91: aload 4
    //   93: aload_2
    //   94: aload_3
    //   95: invokevirtual 67	com/alibaba/fastjson/serializer/JSONSerializer:setContext	(Lcom/alibaba/fastjson/serializer/SerialContext;Ljava/lang/Object;Ljava/lang/Object;)V
    //   98: aload_1
    //   99: getstatic 50	com/alibaba/fastjson/serializer/SerializerFeature:WriteClassName	Lcom/alibaba/fastjson/serializer/SerializerFeature;
    //   102: invokevirtual 51	com/alibaba/fastjson/serializer/JSONSerializer:isEnabled	(Lcom/alibaba/fastjson/serializer/SerializerFeature;)Z
    //   105: ifeq +21 -> 126
    //   108: ldc 69
    //   110: aload 6
    //   112: invokevirtual 73	java/lang/Object:getClass	()Ljava/lang/Class;
    //   115: if_acmpne +81 -> 196
    //   118: aload 7
    //   120: ldc 75
    //   122: invokevirtual 79	com/alibaba/fastjson/serializer/SerializeWriter:append	(Ljava/lang/CharSequence;)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   125: pop
    //   126: aload 7
    //   128: bipush 91
    //   130: invokevirtual 82	com/alibaba/fastjson/serializer/SerializeWriter:append	(C)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   133: pop
    //   134: aload 6
    //   136: invokeinterface 86 1 0
    //   141: astore_2
    //   142: iconst_0
    //   143: istore 8
    //   145: aload_2
    //   146: invokeinterface 92 1 0
    //   151: ifeq +171 -> 322
    //   154: aload_2
    //   155: invokeinterface 96 1 0
    //   160: astore_3
    //   161: iload 8
    //   163: iconst_1
    //   164: iadd
    //   165: istore 9
    //   167: iload 8
    //   169: ifeq +11 -> 180
    //   172: aload 7
    //   174: bipush 44
    //   176: invokevirtual 82	com/alibaba/fastjson/serializer/SerializeWriter:append	(C)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   179: pop
    //   180: aload_3
    //   181: ifnonnull +36 -> 217
    //   184: aload 7
    //   186: invokevirtual 47	com/alibaba/fastjson/serializer/SerializeWriter:writeNull	()V
    //   189: iload 9
    //   191: istore 8
    //   193: goto -48 -> 145
    //   196: ldc 98
    //   198: aload 6
    //   200: invokevirtual 73	java/lang/Object:getClass	()Ljava/lang/Class;
    //   203: if_acmpne -77 -> 126
    //   206: aload 7
    //   208: ldc 100
    //   210: invokevirtual 79	com/alibaba/fastjson/serializer/SerializeWriter:append	(Ljava/lang/CharSequence;)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   213: pop
    //   214: goto -88 -> 126
    //   217: aload_3
    //   218: invokevirtual 73	java/lang/Object:getClass	()Ljava/lang/Class;
    //   221: astore 6
    //   223: aload 6
    //   225: ldc 102
    //   227: if_acmpne +22 -> 249
    //   230: aload 7
    //   232: aload_3
    //   233: checkcast 102	java/lang/Integer
    //   236: invokevirtual 106	java/lang/Integer:intValue	()I
    //   239: invokevirtual 110	com/alibaba/fastjson/serializer/SerializeWriter:writeInt	(I)V
    //   242: iload 9
    //   244: istore 8
    //   246: goto -101 -> 145
    //   249: aload 6
    //   251: ldc 112
    //   253: if_acmpne +40 -> 293
    //   256: aload 7
    //   258: aload_3
    //   259: checkcast 112	java/lang/Long
    //   262: invokevirtual 116	java/lang/Long:longValue	()J
    //   265: invokevirtual 120	com/alibaba/fastjson/serializer/SerializeWriter:writeLong	(J)V
    //   268: aload 7
    //   270: getstatic 50	com/alibaba/fastjson/serializer/SerializerFeature:WriteClassName	Lcom/alibaba/fastjson/serializer/SerializerFeature;
    //   273: invokevirtual 39	com/alibaba/fastjson/serializer/SerializeWriter:isEnabled	(Lcom/alibaba/fastjson/serializer/SerializerFeature;)Z
    //   276: ifeq +74 -> 350
    //   279: aload 7
    //   281: bipush 76
    //   283: invokevirtual 123	com/alibaba/fastjson/serializer/SerializeWriter:write	(C)V
    //   286: iload 9
    //   288: istore 8
    //   290: goto -145 -> 145
    //   293: aload_1
    //   294: aload 6
    //   296: invokevirtual 127	com/alibaba/fastjson/serializer/JSONSerializer:getObjectWriter	(Ljava/lang/Class;)Lcom/alibaba/fastjson/serializer/ObjectSerializer;
    //   299: aload_1
    //   300: aload_3
    //   301: iload 9
    //   303: iconst_1
    //   304: isub
    //   305: invokestatic 131	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   308: aload 5
    //   310: invokeinterface 133 5 0
    //   315: iload 9
    //   317: istore 8
    //   319: goto -174 -> 145
    //   322: aload 7
    //   324: bipush 93
    //   326: invokevirtual 82	com/alibaba/fastjson/serializer/SerializeWriter:append	(C)Lcom/alibaba/fastjson/serializer/SerializeWriter;
    //   329: pop
    //   330: aload_1
    //   331: aload 4
    //   333: invokevirtual 136	com/alibaba/fastjson/serializer/JSONSerializer:setContext	(Lcom/alibaba/fastjson/serializer/SerialContext;)V
    //   336: return
    //   337: astore_2
    //   338: aload_1
    //   339: aload 4
    //   341: invokevirtual 136	com/alibaba/fastjson/serializer/JSONSerializer:setContext	(Lcom/alibaba/fastjson/serializer/SerialContext;)V
    //   344: aload_2
    //   345: athrow
    //   346: astore_2
    //   347: goto -9 -> 338
    //   350: iload 9
    //   352: istore 8
    //   354: goto -209 -> 145
    //
    // Exception table:
    //   from	to	target	type
    //   126	142	337	finally
    //   172	180	337	finally
    //   184	189	337	finally
    //   217	223	337	finally
    //   230	242	337	finally
    //   256	286	337	finally
    //   293	315	337	finally
    //   145	161	346	finally
    //   322	330	346	finally
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.CollectionSerializer
 * JD-Core Version:    0.6.2
 */