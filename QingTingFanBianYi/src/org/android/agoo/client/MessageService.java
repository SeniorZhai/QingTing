package org.android.agoo.client;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.aM;
import com.umeng.message.proguard.aQ;
import org.android.agoo.net.mtop.IMtopSynClient;
import org.android.agoo.net.mtop.MtopRequest;
import org.android.agoo.net.mtop.MtopSyncClientV3;

class MessageService
{
  private static final String a = "MessageService";
  private static final String b = "message_db";
  private static final String c = "message";
  private static final int d = 1;
  private static final String e = "id";
  private static final String f = "type";
  private static final String g = "message";
  private static final String h = "notify";
  private static final String i = "report";
  private static final String j = "interval";
  private static final String k = "target_time";
  private static final String l = "create_time";
  private static final String m = "state";
  private static final String n = "body_code";
  private static final int o = 1;
  private static final int p = 0;
  private static volatile MessageService r = null;
  private volatile SQLiteOpenHelper q = null;
  private Context s;

  private MessageService(Context paramContext)
  {
    this.s = paramContext;
    this.q = new MessageDBHelper(paramContext);
  }

  private long a(long paramLong, int paramInt)
  {
    return aQ.a(paramInt * 60 * 1000) + paramLong;
  }

  // ERROR //
  private void a(String paramString1, String paramString2, String paramString3, int paramInt1, long paramLong, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: ldc 11
    //   2: new 83	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   9: ldc 86
    //   11: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_1
    //   15: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: ldc 92
    //   20: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: invokestatic 101	com/umeng/message/proguard/Q:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   29: aconst_null
    //   30: astore 12
    //   32: aload 12
    //   34: astore 9
    //   36: aload_2
    //   37: invokestatic 107	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   40: ifeq +127 -> 167
    //   43: ldc 109
    //   45: astore 10
    //   47: iconst_m1
    //   48: istore 13
    //   50: aload_3
    //   51: astore 11
    //   53: aload 12
    //   55: astore 9
    //   57: aload_3
    //   58: invokestatic 107	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   61: ifeq +7 -> 68
    //   64: ldc 109
    //   66: astore 11
    //   68: aload 12
    //   70: astore 9
    //   72: aload_0
    //   73: getfield 68	org/android/agoo/client/MessageService:q	Landroid/database/sqlite/SQLiteOpenHelper;
    //   76: invokevirtual 115	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   79: astore_2
    //   80: aload_2
    //   81: astore 9
    //   83: aload_2
    //   84: ldc 117
    //   86: bipush 9
    //   88: anewarray 4	java/lang/Object
    //   91: dup
    //   92: iconst_0
    //   93: aload_1
    //   94: aastore
    //   95: dup
    //   96: iconst_1
    //   97: iload 4
    //   99: invokestatic 123	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   102: aastore
    //   103: dup
    //   104: iconst_2
    //   105: iload 13
    //   107: invokestatic 123	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   110: aastore
    //   111: dup
    //   112: iconst_3
    //   113: iconst_0
    //   114: invokestatic 123	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   117: aastore
    //   118: dup
    //   119: iconst_4
    //   120: lload 5
    //   122: invokestatic 128	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   125: aastore
    //   126: dup
    //   127: iconst_5
    //   128: iload 7
    //   130: invokestatic 123	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   133: aastore
    //   134: dup
    //   135: bipush 6
    //   137: aload 11
    //   139: aastore
    //   140: dup
    //   141: bipush 7
    //   143: aload 10
    //   145: aastore
    //   146: dup
    //   147: bipush 8
    //   149: iload 8
    //   151: invokestatic 123	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   154: aastore
    //   155: invokevirtual 134	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   158: aload_2
    //   159: ifnull +7 -> 166
    //   162: aload_2
    //   163: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   166: return
    //   167: aload 12
    //   169: astore 9
    //   171: aload_2
    //   172: invokevirtual 143	java/lang/String:hashCode	()I
    //   175: istore 13
    //   177: aload_2
    //   178: astore 10
    //   180: goto -130 -> 50
    //   183: astore_1
    //   184: aload 9
    //   186: ifnull -20 -> 166
    //   189: aload 9
    //   191: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   194: return
    //   195: astore_1
    //   196: return
    //   197: astore_1
    //   198: aconst_null
    //   199: astore_2
    //   200: aload_2
    //   201: ifnull +7 -> 208
    //   204: aload_2
    //   205: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   208: aload_1
    //   209: athrow
    //   210: astore_1
    //   211: return
    //   212: astore_2
    //   213: goto -5 -> 208
    //   216: astore_1
    //   217: goto -17 -> 200
    //
    // Exception table:
    //   from	to	target	type
    //   36	43	183	java/lang/Throwable
    //   57	64	183	java/lang/Throwable
    //   72	80	183	java/lang/Throwable
    //   83	158	183	java/lang/Throwable
    //   171	177	183	java/lang/Throwable
    //   189	194	195	java/lang/Throwable
    //   36	43	197	finally
    //   57	64	197	finally
    //   72	80	197	finally
    //   171	177	197	finally
    //   162	166	210	java/lang/Throwable
    //   204	208	212	java/lang/Throwable
    //   83	158	216	finally
  }

  private void a(String paramString1, String paramString2, String paramString3, long paramLong, int paramInt1, int paramInt2)
  {
    if (paramLong < System.currentTimeMillis())
    {
      Q.c("MessageService", "sendAtTime messageId[" + paramString1 + "] targetTime[" + aM.a(paramLong) + "] <=currentTime[" + aM.a(System.currentTimeMillis()) + "]");
      return;
    }
    long l1 = a(paramLong, paramInt1);
    Q.c("MessageService", "sendAtTime message---->[" + paramString1 + "]serverTime--->[" + aM.a(paramLong) + "," + paramInt1 + " min]targetTime---->[" + aM.a(l1) + "]");
    Bundle localBundle = new Bundle();
    localBundle.putString("body", paramString2);
    localBundle.putString("id", paramString1);
    localBundle.putString("type", paramString3);
    localBundle.putBoolean("local", true);
    localBundle.putString("notify", "" + paramInt2);
    paramString2 = (AlarmManager)this.s.getSystemService("alarm");
    paramString3 = new Intent();
    paramString3.setAction("org.agoo.android.intent.action.RECEIVE");
    paramString3.setPackage(this.s.getPackageName());
    paramString3.putExtras(localBundle);
    paramString2.set(1, l1, PendingIntent.getBroadcast(this.s, paramString1.hashCode(), paramString3, 134217728));
  }

  public static MessageService getSingleton(Context paramContext)
  {
    try
    {
      if (r == null)
        r = new MessageService(paramContext);
      paramContext = r;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void addMessage(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    a(paramString1, paramString2, paramString3, 1, -1L, -1, paramInt);
  }

  public void deleteExpireTimeMessage()
  {
    deleteExpireTimeMessage(30);
  }

  // ERROR //
  public void deleteExpireTimeMessage(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: iload_1
    //   5: bipush 7
    //   7: if_icmple +100 -> 107
    //   10: aload_0
    //   11: getfield 68	org/android/agoo/client/MessageService:q	Landroid/database/sqlite/SQLiteOpenHelper;
    //   14: invokevirtual 115	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore 4
    //   19: aload 4
    //   21: astore_2
    //   22: aload 4
    //   24: astore_3
    //   25: aload 4
    //   27: new 83	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   34: ldc 243
    //   36: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: iload_1
    //   40: invokevirtual 172	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   43: ldc 245
    //   45: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokevirtual 248	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   54: aload 4
    //   56: ifnull +8 -> 64
    //   59: aload 4
    //   61: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   64: return
    //   65: astore 4
    //   67: aload_2
    //   68: astore_3
    //   69: ldc 11
    //   71: ldc 250
    //   73: aload 4
    //   75: invokestatic 255	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   78: pop
    //   79: aload_2
    //   80: ifnull -16 -> 64
    //   83: aload_2
    //   84: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   87: return
    //   88: astore_2
    //   89: return
    //   90: astore_2
    //   91: aload_3
    //   92: ifnull +7 -> 99
    //   95: aload_3
    //   96: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   99: aload_2
    //   100: athrow
    //   101: astore_2
    //   102: return
    //   103: astore_3
    //   104: goto -5 -> 99
    //   107: bipush 7
    //   109: istore_1
    //   110: goto -100 -> 10
    //
    // Exception table:
    //   from	to	target	type
    //   10	19	65	java/lang/Throwable
    //   25	54	65	java/lang/Throwable
    //   83	87	88	java/lang/Throwable
    //   10	19	90	finally
    //   25	54	90	finally
    //   69	79	90	finally
    //   59	64	101	java/lang/Throwable
    //   95	99	103	java/lang/Throwable
  }

  // ERROR //
  public void handleMessageAtTime(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    // Byte code:
    //   0: aload 4
    //   2: ldc_w 259
    //   5: invokevirtual 263	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   8: astore 6
    //   10: aload 6
    //   12: arraylength
    //   13: iconst_2
    //   14: if_icmpne +199 -> 213
    //   17: aload 6
    //   19: iconst_0
    //   20: aaload
    //   21: invokestatic 267	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   24: lstore 8
    //   26: aload 6
    //   28: iconst_1
    //   29: aaload
    //   30: invokestatic 271	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   33: istore 7
    //   35: lload 8
    //   37: ldc2_w 234
    //   40: lcmp
    //   41: ifeq +18 -> 59
    //   44: iload 7
    //   46: iconst_m1
    //   47: if_icmpeq +12 -> 59
    //   50: lload 8
    //   52: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   55: lcmp
    //   56: ifge +70 -> 126
    //   59: ldc 11
    //   61: new 83	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   68: ldc_w 273
    //   71: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: aload_1
    //   75: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: ldc_w 275
    //   81: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: lload 8
    //   86: invokestatic 159	com/umeng/message/proguard/aM:a	(J)Ljava/lang/String;
    //   89: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: ldc 161
    //   94: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   100: invokestatic 159	com/umeng/message/proguard/aM:a	(J)Ljava/lang/String;
    //   103: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: ldc 92
    //   108: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 277	com/umeng/message/proguard/Q:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   117: aload_0
    //   118: aload_1
    //   119: aload_2
    //   120: aload_3
    //   121: iload 5
    //   123: invokevirtual 279	org/android/agoo/client/MessageService:addMessage	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   126: lload 8
    //   128: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   131: lcmp
    //   132: iflt +30 -> 162
    //   135: aload_0
    //   136: aload_1
    //   137: aload_2
    //   138: aload_3
    //   139: iconst_0
    //   140: lload 8
    //   142: iload 7
    //   144: iload 5
    //   146: invokespecial 237	org/android/agoo/client/MessageService:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJII)V
    //   149: aload_0
    //   150: aload_1
    //   151: aload_2
    //   152: aload_3
    //   153: lload 8
    //   155: iload 7
    //   157: iload 5
    //   159: invokespecial 281	org/android/agoo/client/MessageService:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JII)V
    //   162: return
    //   163: astore 6
    //   165: ldc2_w 234
    //   168: lstore 8
    //   170: ldc 11
    //   172: new 83	java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   179: ldc_w 283
    //   182: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: aload 4
    //   187: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc_w 285
    //   193: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 277	com/umeng/message/proguard/Q:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   202: iconst_m1
    //   203: istore 7
    //   205: goto -170 -> 35
    //   208: astore 6
    //   210: goto -40 -> 170
    //   213: iconst_m1
    //   214: istore 7
    //   216: ldc2_w 234
    //   219: lstore 8
    //   221: goto -186 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   17	26	163	java/lang/Throwable
    //   26	35	208	java/lang/Throwable
  }

  // ERROR //
  public boolean hasMessageDuplicate(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: iconst_0
    //   13: istore 10
    //   15: iconst_0
    //   16: istore 9
    //   18: aload_0
    //   19: getfield 68	org/android/agoo/client/MessageService:q	Landroid/database/sqlite/SQLiteOpenHelper;
    //   22: invokevirtual 115	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   25: astore_3
    //   26: aload 7
    //   28: astore 4
    //   30: aload 6
    //   32: astore 5
    //   34: aload_3
    //   35: ldc_w 289
    //   38: iconst_1
    //   39: anewarray 139	java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: aload_1
    //   45: aastore
    //   46: invokevirtual 293	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_1
    //   50: iload 9
    //   52: istore 8
    //   54: aload_1
    //   55: ifnull +47 -> 102
    //   58: iload 9
    //   60: istore 8
    //   62: aload_1
    //   63: astore 4
    //   65: aload_1
    //   66: astore 5
    //   68: aload_1
    //   69: invokeinterface 299 1 0
    //   74: ifeq +28 -> 102
    //   77: aload_1
    //   78: astore 4
    //   80: aload_1
    //   81: astore 5
    //   83: aload_1
    //   84: iconst_0
    //   85: invokeinterface 303 2 0
    //   90: istore_2
    //   91: iload 9
    //   93: istore 8
    //   95: iload_2
    //   96: ifle +6 -> 102
    //   99: iconst_1
    //   100: istore 8
    //   102: aload_1
    //   103: ifnull +9 -> 112
    //   106: aload_1
    //   107: invokeinterface 304 1 0
    //   112: iload 8
    //   114: istore 9
    //   116: aload_3
    //   117: ifnull +11 -> 128
    //   120: aload_3
    //   121: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   124: iload 8
    //   126: istore 9
    //   128: iload 9
    //   130: ireturn
    //   131: astore_1
    //   132: aconst_null
    //   133: astore_3
    //   134: aload 5
    //   136: ifnull +10 -> 146
    //   139: aload 5
    //   141: invokeinterface 304 1 0
    //   146: iload 10
    //   148: istore 9
    //   150: aload_3
    //   151: ifnull -23 -> 128
    //   154: aload_3
    //   155: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   158: iconst_0
    //   159: ireturn
    //   160: astore_1
    //   161: iconst_0
    //   162: ireturn
    //   163: astore_1
    //   164: aconst_null
    //   165: astore_3
    //   166: aload 4
    //   168: ifnull +10 -> 178
    //   171: aload 4
    //   173: invokeinterface 304 1 0
    //   178: aload_3
    //   179: ifnull +7 -> 186
    //   182: aload_3
    //   183: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   186: aload_1
    //   187: athrow
    //   188: astore_3
    //   189: goto -3 -> 186
    //   192: astore_1
    //   193: goto -27 -> 166
    //   196: astore_1
    //   197: goto -63 -> 134
    //   200: astore_1
    //   201: iload 8
    //   203: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   18	26	131	java/lang/Throwable
    //   139	146	160	java/lang/Throwable
    //   154	158	160	java/lang/Throwable
    //   18	26	163	finally
    //   171	178	188	java/lang/Throwable
    //   182	186	188	java/lang/Throwable
    //   34	50	192	finally
    //   68	77	192	finally
    //   83	91	192	finally
    //   34	50	196	java/lang/Throwable
    //   68	77	196	java/lang/Throwable
    //   83	91	196	java/lang/Throwable
    //   106	112	200	java/lang/Throwable
    //   120	124	200	java/lang/Throwable
  }

  // ERROR //
  public void notice(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_0
    //   5: getfield 68	org/android/agoo/client/MessageService:q	Landroid/database/sqlite/SQLiteOpenHelper;
    //   8: invokevirtual 115	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   11: astore 4
    //   13: aload 4
    //   15: astore_2
    //   16: aload 4
    //   18: astore_3
    //   19: aload 4
    //   21: ldc_w 307
    //   24: iconst_1
    //   25: anewarray 4	java/lang/Object
    //   28: dup
    //   29: iconst_0
    //   30: aload_1
    //   31: aastore
    //   32: invokevirtual 134	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   35: aload 4
    //   37: ifnull +8 -> 45
    //   40: aload 4
    //   42: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   45: return
    //   46: astore_1
    //   47: aload_2
    //   48: astore_3
    //   49: ldc 11
    //   51: ldc 250
    //   53: aload_1
    //   54: invokestatic 255	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   57: pop
    //   58: aload_2
    //   59: ifnull -14 -> 45
    //   62: aload_2
    //   63: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   66: return
    //   67: astore_1
    //   68: return
    //   69: astore_1
    //   70: aload_3
    //   71: ifnull +7 -> 78
    //   74: aload_3
    //   75: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   78: aload_1
    //   79: athrow
    //   80: astore_1
    //   81: return
    //   82: astore_2
    //   83: goto -5 -> 78
    //
    // Exception table:
    //   from	to	target	type
    //   4	13	46	java/lang/Throwable
    //   19	35	46	java/lang/Throwable
    //   62	66	67	java/lang/Throwable
    //   4	13	69	finally
    //   19	35	69	finally
    //   49	58	69	finally
    //   40	45	80	java/lang/Throwable
    //   74	78	82	java/lang/Throwable
  }

  // ERROR //
  public void reloadMessageAtTime()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: ldc 11
    //   4: ldc_w 310
    //   7: invokestatic 101	com/umeng/message/proguard/Q:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   10: aload_0
    //   11: getfield 68	org/android/agoo/client/MessageService:q	Landroid/database/sqlite/SQLiteOpenHelper;
    //   14: invokevirtual 115	android/database/sqlite/SQLiteOpenHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore_1
    //   18: aload_1
    //   19: ldc_w 312
    //   22: invokevirtual 248	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   25: aload_1
    //   26: ldc_w 314
    //   29: iconst_1
    //   30: anewarray 139	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc_w 316
    //   38: aastore
    //   39: invokevirtual 293	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore_2
    //   43: aload_2
    //   44: astore_3
    //   45: aload_3
    //   46: ifnull +89 -> 135
    //   49: aload_3
    //   50: invokeinterface 319 1 0
    //   55: ifeq +80 -> 135
    //   58: aload_0
    //   59: aload_3
    //   60: iconst_0
    //   61: invokeinterface 323 2 0
    //   66: aload_3
    //   67: iconst_1
    //   68: invokeinterface 323 2 0
    //   73: aload_3
    //   74: iconst_2
    //   75: invokeinterface 323 2 0
    //   80: aload_3
    //   81: iconst_3
    //   82: invokeinterface 327 2 0
    //   87: aload_3
    //   88: iconst_4
    //   89: invokeinterface 303 2 0
    //   94: aload_3
    //   95: iconst_5
    //   96: invokeinterface 303 2 0
    //   101: invokespecial 281	org/android/agoo/client/MessageService:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JII)V
    //   104: goto -55 -> 49
    //   107: astore_2
    //   108: aload_3
    //   109: ifnull +9 -> 118
    //   112: aload_3
    //   113: invokeinterface 304 1 0
    //   118: aload_1
    //   119: ifnull +7 -> 126
    //   122: aload_1
    //   123: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   126: ldc 11
    //   128: ldc_w 329
    //   131: invokestatic 101	com/umeng/message/proguard/Q:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   134: return
    //   135: aload_3
    //   136: ifnull +9 -> 145
    //   139: aload_3
    //   140: invokeinterface 304 1 0
    //   145: aload_1
    //   146: ifnull -20 -> 126
    //   149: aload_1
    //   150: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   153: goto -27 -> 126
    //   156: astore_1
    //   157: goto -31 -> 126
    //   160: astore_2
    //   161: aconst_null
    //   162: astore_3
    //   163: aconst_null
    //   164: astore_1
    //   165: aload_3
    //   166: ifnull +9 -> 175
    //   169: aload_3
    //   170: invokeinterface 304 1 0
    //   175: aload_1
    //   176: ifnull +7 -> 183
    //   179: aload_1
    //   180: invokevirtual 137	android/database/sqlite/SQLiteDatabase:close	()V
    //   183: aload_2
    //   184: athrow
    //   185: astore_1
    //   186: goto -3 -> 183
    //   189: astore_2
    //   190: aconst_null
    //   191: astore_3
    //   192: goto -27 -> 165
    //   195: astore_2
    //   196: goto -31 -> 165
    //   199: astore_1
    //   200: goto -74 -> 126
    //   203: astore_1
    //   204: aconst_null
    //   205: astore_1
    //   206: goto -98 -> 108
    //   209: astore_2
    //   210: goto -102 -> 108
    //
    // Exception table:
    //   from	to	target	type
    //   49	104	107	java/lang/Throwable
    //   139	145	156	java/lang/Throwable
    //   149	153	156	java/lang/Throwable
    //   10	18	160	finally
    //   169	175	185	java/lang/Throwable
    //   179	183	185	java/lang/Throwable
    //   18	43	189	finally
    //   49	104	195	finally
    //   112	118	199	java/lang/Throwable
    //   122	126	199	java/lang/Throwable
    //   10	18	203	java/lang/Throwable
    //   18	43	209	java/lang/Throwable
  }

  public boolean report(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString3))
      bool1 = bool2;
    try
    {
      if (Integer.parseInt(paramString3) >= -1)
      {
        MtopRequest localMtopRequest = new MtopRequest();
        localMtopRequest.setApi("mtop.push.msg.report");
        localMtopRequest.setV("1.0");
        localMtopRequest.putParams("messageId", paramString1 + "@" + paramString4);
        localMtopRequest.putParams("mesgStatus", "4");
        if (!TextUtils.isEmpty(paramString2))
          localMtopRequest.putParams("taskId", paramString2);
        localMtopRequest.setDeviceId(BaseRegistrar.getRegistrationId(this.s));
        paramString1 = new MtopSyncClientV3();
        paramString1.setDefaultAppkey(P.n(this.s));
        paramString1.setDefaultAppSecret(P.p(this.s));
        paramString1.setBaseUrl(AgooSettings.getPullUrl(this.s));
        paramString1.getV3(this.s, localMtopRequest);
        bool1 = true;
      }
      return bool1;
    }
    catch (Throwable paramString1)
    {
      Q.e("MessageService", "[" + paramString3 + "] to Integer error", paramString1);
    }
    return false;
  }

  private static class MessageDBHelper extends SQLiteOpenHelper
  {
    public MessageDBHelper(Context paramContext)
    {
      super("message_db", null, 1);
    }

    private String a()
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("create table message");
      localStringBuffer.append("(");
      localStringBuffer.append("id text UNIQUE not null,");
      localStringBuffer.append("state integer,");
      localStringBuffer.append("body_code integer,");
      localStringBuffer.append("report long,");
      localStringBuffer.append("target_time long,");
      localStringBuffer.append("interval integer,");
      localStringBuffer.append("type text,");
      localStringBuffer.append("message text,");
      localStringBuffer.append("notify integer,");
      localStringBuffer.append("create_time date");
      localStringBuffer.append(");");
      return localStringBuffer.toString();
    }

    private String b()
    {
      return "CREATE INDEX body_code_index ON message(body_code)";
    }

    private String c()
    {
      return "CREATE INDEX id_index ON message(id)";
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      if (paramSQLiteDatabase != null);
      try
      {
        paramSQLiteDatabase.execSQL(a());
        paramSQLiteDatabase.execSQL(c());
        paramSQLiteDatabase.execSQL(b());
        return;
      }
      catch (Throwable paramSQLiteDatabase)
      {
        Q.d("MessageService", "messagedbhelper create", paramSQLiteDatabase);
      }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.MessageService
 * JD-Core Version:    0.6.2
 */