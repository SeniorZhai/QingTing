package com.intowow.sdk.h;

import android.os.Bundle;
import com.intowow.sdk.b.g.a;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.triggerresponse.TriggerResponse;

public class a
{
  public static Bundle a(String paramString, g.a parama, int paramInt1, int paramInt2)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.t.ordinal());
    localBundle.putString("token", paramString);
    localBundle.putInt("adid", parama.a);
    localBundle.putInt("place", parama.c);
    localBundle.putInt("ad_version", parama.d);
    localBundle.putInt("creative_id", parama.e);
    localBundle.putBoolean("engaged", parama.g);
    localBundle.putInt("duration", paramInt1);
    localBundle.putInt("percentage", paramInt2);
    return localBundle;
  }

  public static Bundle a(String paramString1, ADProfile paramADProfile, j paramj, int paramInt, String paramString2, TriggerResponse paramTriggerResponse)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.q.ordinal());
    localBundle.putInt("adid", paramADProfile.e());
    localBundle.putInt("ad_version", paramADProfile.k());
    localBundle.putInt("creative_id", paramADProfile.l());
    localBundle.putInt("ad_event_type", paramj.ordinal());
    localBundle.putInt("place", paramInt);
    localBundle.putString("placement", paramString2);
    localBundle.putString("token", paramString1);
    localBundle.putParcelable("response", paramTriggerResponse);
    return localBundle;
  }

  // ERROR //
  public static org.json.JSONObject a(Bundle paramBundle)
  {
    // Byte code:
    //   0: new 103	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 104	org/json/JSONObject:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: getstatic 110	com/intowow/sdk/h/f:x	Lcom/intowow/sdk/h/f;
    //   12: aload_0
    //   13: ldc 36
    //   15: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   18: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   21: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   24: aload_1
    //   25: getstatic 128	com/intowow/sdk/h/f:y	Lcom/intowow/sdk/h/f;
    //   28: aload_0
    //   29: ldc 92
    //   31: invokevirtual 132	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   34: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   37: aload_1
    //   38: getstatic 135	com/intowow/sdk/h/f:z	Lcom/intowow/sdk/h/f;
    //   41: aload_0
    //   42: ldc 43
    //   44: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   47: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   50: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   53: aload_1
    //   54: getstatic 137	com/intowow/sdk/h/f:a	Lcom/intowow/sdk/h/f;
    //   57: aload_0
    //   58: ldc 48
    //   60: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   63: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   66: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   69: aload_1
    //   70: getstatic 140	com/intowow/sdk/h/f:b	Lcom/intowow/sdk/h/f;
    //   73: aload_0
    //   74: ldc 53
    //   76: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   79: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   82: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   85: aload_1
    //   86: getstatic 143	com/intowow/sdk/h/f:o	Lcom/intowow/sdk/h/f;
    //   89: aload_0
    //   90: ldc 30
    //   92: invokevirtual 132	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   95: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   98: aload_1
    //   99: areturn
    //   100: astore_0
    //   101: aconst_null
    //   102: areturn
    //   103: astore_0
    //   104: aload_1
    //   105: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	100	java/lang/Exception
    //   8	98	103	java/lang/Exception
  }

  // ERROR //
  public static org.json.JSONObject a(ADProfile paramADProfile)
  {
    // Byte code:
    //   0: new 103	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 104	org/json/JSONObject:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: getstatic 110	com/intowow/sdk/h/f:x	Lcom/intowow/sdk/h/f;
    //   12: aload_0
    //   13: invokevirtual 79	com/intowow/sdk/model/ADProfile:e	()I
    //   16: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   19: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   22: aload_1
    //   23: getstatic 137	com/intowow/sdk/h/f:a	Lcom/intowow/sdk/h/f;
    //   26: aload_0
    //   27: invokevirtual 82	com/intowow/sdk/model/ADProfile:k	()I
    //   30: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   33: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   36: aload_1
    //   37: getstatic 140	com/intowow/sdk/h/f:b	Lcom/intowow/sdk/h/f;
    //   40: aload_0
    //   41: invokevirtual 85	com/intowow/sdk/model/ADProfile:l	()I
    //   44: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   47: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   50: aload_1
    //   51: areturn
    //   52: astore_0
    //   53: aconst_null
    //   54: areturn
    //   55: astore_0
    //   56: aload_1
    //   57: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	52	java/lang/Exception
    //   8	50	55	java/lang/Exception
  }

  // ERROR //
  public static org.json.JSONObject b(Bundle paramBundle)
  {
    // Byte code:
    //   0: new 103	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 104	org/json/JSONObject:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: getstatic 110	com/intowow/sdk/h/f:x	Lcom/intowow/sdk/h/f;
    //   12: aload_0
    //   13: ldc 36
    //   15: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   18: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   21: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   24: aload_1
    //   25: getstatic 147	com/intowow/sdk/h/f:u	Lcom/intowow/sdk/h/f;
    //   28: aload_0
    //   29: ldc 68
    //   31: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   34: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   37: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   40: aload_1
    //   41: getstatic 150	com/intowow/sdk/h/f:v	Lcom/intowow/sdk/h/f;
    //   44: aload_0
    //   45: ldc 70
    //   47: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   50: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   53: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   56: aload_1
    //   57: getstatic 135	com/intowow/sdk/h/f:z	Lcom/intowow/sdk/h/f;
    //   60: aload_0
    //   61: ldc 43
    //   63: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   66: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   72: aload_1
    //   73: getstatic 153	com/intowow/sdk/h/f:w	Lcom/intowow/sdk/h/f;
    //   76: aload_0
    //   77: ldc 58
    //   79: invokevirtual 157	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   82: invokestatic 162	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   85: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   88: aload_1
    //   89: getstatic 137	com/intowow/sdk/h/f:a	Lcom/intowow/sdk/h/f;
    //   92: aload_0
    //   93: ldc 48
    //   95: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   98: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   101: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   104: aload_1
    //   105: getstatic 140	com/intowow/sdk/h/f:b	Lcom/intowow/sdk/h/f;
    //   108: aload_0
    //   109: ldc 53
    //   111: invokevirtual 114	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   114: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   117: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   120: aload_1
    //   121: getstatic 143	com/intowow/sdk/h/f:o	Lcom/intowow/sdk/h/f;
    //   124: aload_0
    //   125: ldc 30
    //   127: invokevirtual 132	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   130: invokestatic 125	com/intowow/sdk/h/d:a	(Lorg/json/JSONObject;Lcom/intowow/sdk/h/f;Ljava/lang/Object;)V
    //   133: aload_1
    //   134: areturn
    //   135: astore_0
    //   136: aconst_null
    //   137: areturn
    //   138: astore_0
    //   139: aload_1
    //   140: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	135	java/lang/Exception
    //   8	133	138	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.h.a
 * JD-Core Version:    0.6.2
 */