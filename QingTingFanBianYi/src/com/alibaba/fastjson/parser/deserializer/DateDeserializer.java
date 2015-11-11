package com.alibaba.fastjson.parser.deserializer;

public class DateDeserializer extends AbstractDateDeserializer
  implements ObjectDeserializer
{
  public static final DateDeserializer instance = new DateDeserializer();

  // ERROR //
  protected <T> T cast(com.alibaba.fastjson.parser.DefaultJSONParser paramDefaultJSONParser, java.lang.reflect.Type paramType, java.lang.Object paramObject1, java.lang.Object paramObject2)
  {
    // Byte code:
    //   0: aload 4
    //   2: ifnonnull +7 -> 9
    //   5: aconst_null
    //   6: astore_2
    //   7: aload_2
    //   8: areturn
    //   9: aload 4
    //   11: astore_2
    //   12: aload 4
    //   14: instanceof 23
    //   17: ifne -10 -> 7
    //   20: aload 4
    //   22: instanceof 25
    //   25: ifeq +19 -> 44
    //   28: new 23	java/util/Date
    //   31: dup
    //   32: aload 4
    //   34: checkcast 25	java/lang/Number
    //   37: invokevirtual 29	java/lang/Number:longValue	()J
    //   40: invokespecial 32	java/util/Date:<init>	(J)V
    //   43: areturn
    //   44: aload 4
    //   46: instanceof 34
    //   49: ifeq +86 -> 135
    //   52: aload 4
    //   54: checkcast 34	java/lang/String
    //   57: astore_2
    //   58: aload_2
    //   59: invokevirtual 38	java/lang/String:length	()I
    //   62: ifne +5 -> 67
    //   65: aconst_null
    //   66: areturn
    //   67: new 40	com/alibaba/fastjson/parser/JSONScanner
    //   70: dup
    //   71: aload_2
    //   72: invokespecial 43	com/alibaba/fastjson/parser/JSONScanner:<init>	(Ljava/lang/String;)V
    //   75: astore_3
    //   76: aload_3
    //   77: iconst_0
    //   78: invokevirtual 47	com/alibaba/fastjson/parser/JSONScanner:scanISO8601DateIfMatch	(Z)Z
    //   81: ifeq +17 -> 98
    //   84: aload_3
    //   85: invokevirtual 51	com/alibaba/fastjson/parser/JSONScanner:getCalendar	()Ljava/util/Calendar;
    //   88: invokevirtual 57	java/util/Calendar:getTime	()Ljava/util/Date;
    //   91: astore_1
    //   92: aload_3
    //   93: invokevirtual 60	com/alibaba/fastjson/parser/JSONScanner:close	()V
    //   96: aload_1
    //   97: areturn
    //   98: aload_3
    //   99: invokevirtual 60	com/alibaba/fastjson/parser/JSONScanner:close	()V
    //   102: aload_1
    //   103: invokevirtual 66	com/alibaba/fastjson/parser/DefaultJSONParser:getDateFormat	()Ljava/text/DateFormat;
    //   106: astore_1
    //   107: aload_1
    //   108: aload_2
    //   109: invokevirtual 72	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   112: astore_1
    //   113: aload_1
    //   114: areturn
    //   115: astore_1
    //   116: aload_3
    //   117: invokevirtual 60	com/alibaba/fastjson/parser/JSONScanner:close	()V
    //   120: aload_1
    //   121: athrow
    //   122: astore_1
    //   123: new 23	java/util/Date
    //   126: dup
    //   127: aload_2
    //   128: invokestatic 78	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   131: invokespecial 32	java/util/Date:<init>	(J)V
    //   134: areturn
    //   135: new 80	com/alibaba/fastjson/JSONException
    //   138: dup
    //   139: ldc 82
    //   141: invokespecial 83	com/alibaba/fastjson/JSONException:<init>	(Ljava/lang/String;)V
    //   144: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   76	92	115	finally
    //   107	113	122	java/text/ParseException
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.DateDeserializer
 * JD-Core Version:    0.6.2
 */