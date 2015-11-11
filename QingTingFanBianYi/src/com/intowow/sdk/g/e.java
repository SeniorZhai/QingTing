package com.intowow.sdk.g;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import com.intowow.sdk.b.d;
import com.intowow.sdk.b.d.a;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.b.k.b;
import com.intowow.sdk.e.b.b;
import com.intowow.sdk.e.b.c;
import com.intowow.sdk.j.g;
import com.intowow.sdk.j.g.a;
import com.intowow.sdk.j.h;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.c;
import com.intowow.sdk.model.ADProfile.c.a;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.e;
import com.intowow.sdk.model.ADProfile.n;
import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class e
  implements k.b
{
  private com.intowow.sdk.b.k a = null;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;

  public e(com.intowow.sdk.b.k paramk)
  {
    this.a = paramk;
  }

  private void a(final long paramLong)
  {
    if (this.a.f().v())
    {
      this.e = false;
      return;
    }
    if ((this.a.f() == null) || (this.a.f().F() == null))
    {
      this.e = false;
      return;
    }
    final String str = this.a.f().F().f();
    Object localObject = new File(com.intowow.sdk.j.k.a(this.a.b()).b() + "_assets.zip");
    if (((File)localObject).exists())
      ((File)localObject).delete();
    localObject = new com.intowow.sdk.e.a();
    ((com.intowow.sdk.e.a)localObject).a(b.c.a);
    ((com.intowow.sdk.e.a)localObject).c(str);
    ((com.intowow.sdk.e.a)localObject).b(com.intowow.sdk.j.k.a(this.a.b()).b() + "_assets.zip");
    ((com.intowow.sdk.e.a)localObject).a(new b.b()
    {
      public void a(com.intowow.sdk.e.a paramAnonymousa)
      {
      }

      public void a(com.intowow.sdk.e.a paramAnonymousa, int paramAnonymousInt)
      {
        e.e(e.this, false);
        if (com.intowow.sdk.a.e.a)
          h.b("Assets Download Faild %s", new Object[] { str });
      }

      public void b(com.intowow.sdk.e.a paramAnonymousa)
      {
      }

      public void c(com.intowow.sdk.e.a paramAnonymousa)
      {
        if (e.a(e.this, paramAnonymousa.e()))
          e.a(e.this).d().post(new Runnable()
          {
            public void run()
            {
              e.a(e.this).f().a("Y", this.b);
              e.e(e.this, false);
              if (com.intowow.sdk.a.e.a)
                h.b("Assets Download Completed ...", new Object[0]);
            }
          });
        while (!com.intowow.sdk.a.e.a)
          return;
        h.b("Assets unzip failed ...", new Object[0]);
      }
    });
    this.a.h().a((com.intowow.sdk.e.a)localObject);
  }

  private void a(Bundle paramBundle)
  {
    a();
  }

  // ERROR //
  private boolean a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iconst_0
    //   3: istore 8
    //   5: new 156	java/io/FileInputStream
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 157	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   13: astore_2
    //   14: new 159	java/util/zip/ZipInputStream
    //   17: dup
    //   18: new 161	java/io/BufferedInputStream
    //   21: dup
    //   22: aload_2
    //   23: invokespecial 164	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   26: invokespecial 165	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   29: astore_3
    //   30: aload_3
    //   31: invokevirtual 169	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   34: astore 6
    //   36: aload 6
    //   38: ifnonnull +62 -> 100
    //   41: aload_3
    //   42: invokevirtual 172	java/util/zip/ZipInputStream:close	()V
    //   45: aload_2
    //   46: invokevirtual 175	java/io/InputStream:close	()V
    //   49: new 74	java/io/File
    //   52: dup
    //   53: aload_1
    //   54: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
    //   57: astore_1
    //   58: aload_1
    //   59: invokevirtual 108	java/io/File:exists	()Z
    //   62: ifeq +8 -> 70
    //   65: aload_1
    //   66: invokevirtual 111	java/io/File:delete	()Z
    //   69: pop
    //   70: iconst_1
    //   71: istore 9
    //   73: aload_3
    //   74: ifnull +7 -> 81
    //   77: aload_3
    //   78: invokevirtual 172	java/util/zip/ZipInputStream:close	()V
    //   81: iload 9
    //   83: istore 8
    //   85: aload_2
    //   86: ifnull +11 -> 97
    //   89: aload_2
    //   90: invokevirtual 175	java/io/InputStream:close	()V
    //   93: iload 9
    //   95: istore 8
    //   97: iload 8
    //   99: ireturn
    //   100: new 177	java/io/ByteArrayOutputStream
    //   103: dup
    //   104: invokespecial 178	java/io/ByteArrayOutputStream:<init>	()V
    //   107: astore 4
    //   109: sipush 1024
    //   112: newarray byte
    //   114: astore 5
    //   116: aload 6
    //   118: invokevirtual 183	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   121: astore 6
    //   123: new 185	java/io/FileOutputStream
    //   126: dup
    //   127: new 76	java/lang/StringBuilder
    //   130: dup
    //   131: aload_0
    //   132: getfield 39	com/intowow/sdk/g/e:a	Lcom/intowow/sdk/b/k;
    //   135: invokevirtual 79	com/intowow/sdk/b/k:b	()Landroid/content/Context;
    //   138: invokestatic 84	com/intowow/sdk/j/k:a	(Landroid/content/Context;)Lcom/intowow/sdk/j/k;
    //   141: invokevirtual 86	com/intowow/sdk/j/k:b	()Ljava/lang/String;
    //   144: invokestatic 92	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   147: invokespecial 95	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   150: aload 6
    //   152: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: invokespecial 186	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   161: astore 6
    //   163: aload_3
    //   164: aload 5
    //   166: invokevirtual 190	java/util/zip/ZipInputStream:read	([B)I
    //   169: istore 7
    //   171: iload 7
    //   173: iconst_m1
    //   174: if_icmpne +39 -> 213
    //   177: aload 6
    //   179: invokevirtual 191	java/io/FileOutputStream:close	()V
    //   182: aload_3
    //   183: invokevirtual 194	java/util/zip/ZipInputStream:closeEntry	()V
    //   186: goto -156 -> 30
    //   189: astore_1
    //   190: aload_3
    //   191: astore_1
    //   192: aload_1
    //   193: ifnull +7 -> 200
    //   196: aload_1
    //   197: invokevirtual 172	java/util/zip/ZipInputStream:close	()V
    //   200: aload_2
    //   201: ifnull -104 -> 97
    //   204: aload_2
    //   205: invokevirtual 175	java/io/InputStream:close	()V
    //   208: iconst_0
    //   209: ireturn
    //   210: astore_1
    //   211: iconst_0
    //   212: ireturn
    //   213: aload 4
    //   215: aload 5
    //   217: iconst_0
    //   218: iload 7
    //   220: invokevirtual 198	java/io/ByteArrayOutputStream:write	([BII)V
    //   223: aload 6
    //   225: aload 4
    //   227: invokevirtual 202	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   230: invokevirtual 205	java/io/FileOutputStream:write	([B)V
    //   233: aload 4
    //   235: invokevirtual 208	java/io/ByteArrayOutputStream:reset	()V
    //   238: goto -75 -> 163
    //   241: astore_1
    //   242: aload_3
    //   243: ifnull +7 -> 250
    //   246: aload_3
    //   247: invokevirtual 172	java/util/zip/ZipInputStream:close	()V
    //   250: aload_2
    //   251: ifnull +7 -> 258
    //   254: aload_2
    //   255: invokevirtual 175	java/io/InputStream:close	()V
    //   258: aload_1
    //   259: athrow
    //   260: astore_1
    //   261: iconst_1
    //   262: ireturn
    //   263: astore_2
    //   264: goto -6 -> 258
    //   267: astore_1
    //   268: aconst_null
    //   269: astore_3
    //   270: aconst_null
    //   271: astore_2
    //   272: goto -30 -> 242
    //   275: astore_1
    //   276: aconst_null
    //   277: astore_3
    //   278: goto -36 -> 242
    //   281: astore_1
    //   282: aconst_null
    //   283: astore_1
    //   284: aload_3
    //   285: astore_2
    //   286: goto -94 -> 192
    //   289: astore_1
    //   290: aconst_null
    //   291: astore_1
    //   292: goto -100 -> 192
    //
    // Exception table:
    //   from	to	target	type
    //   30	36	189	java/lang/Exception
    //   41	70	189	java/lang/Exception
    //   100	163	189	java/lang/Exception
    //   163	171	189	java/lang/Exception
    //   177	186	189	java/lang/Exception
    //   213	238	189	java/lang/Exception
    //   196	200	210	java/lang/Exception
    //   204	208	210	java/lang/Exception
    //   30	36	241	finally
    //   41	70	241	finally
    //   100	163	241	finally
    //   163	171	241	finally
    //   177	186	241	finally
    //   213	238	241	finally
    //   77	81	260	java/lang/Exception
    //   89	93	260	java/lang/Exception
    //   246	250	263	java/lang/Exception
    //   254	258	263	java/lang/Exception
    //   5	14	267	finally
    //   14	30	275	finally
    //   5	14	281	java/lang/Exception
    //   14	30	289	java/lang/Exception
  }

  private void b(Bundle paramBundle)
  {
  }

  private void c()
  {
    Object localObject1 = this.a.f();
    if ((localObject1 == null) || (((com.intowow.sdk.f.b)localObject1).N()));
    while (true)
    {
      return;
      if (com.intowow.sdk.j.k.e())
      {
        HashMap localHashMap = new HashMap();
        try
        {
          File[] arrayOfFile = new File(com.intowow.sdk.j.k.a(this.a.b()).a()).listFiles();
          if ((arrayOfFile != null) && (arrayOfFile.length != 0))
          {
            localObject1 = ((com.intowow.sdk.f.b)localObject1).e();
            label92: int j;
            int i;
            if ((localObject1 != null) && (((List)localObject1).size() > 0))
            {
              localObject1 = ((List)localObject1).iterator();
              if (((Iterator)localObject1).hasNext());
            }
            else
            {
              if (localHashMap.size() == 0)
                continue;
              j = arrayOfFile.length;
              i = 0;
            }
            while (i < j)
            {
              localObject1 = arrayOfFile[i];
              if (((File)localObject1).getName().toLowerCase().equals(".nomedia"))
              {
                break label356;
                ADProfile localADProfile = (ADProfile)((Iterator)localObject1).next();
                if (localADProfile.s() == ADProfile.n.c)
                  break label92;
                ADProfile.e locale = localADProfile.p();
                Iterator localIterator = locale.a().iterator();
                while (localIterator.hasNext())
                {
                  Object localObject2 = locale.b((ADProfile.d)localIterator.next());
                  if (((ADProfile.c)localObject2).a() != ADProfile.c.a.d)
                  {
                    localObject2 = localADProfile.e() + "_" + ADProfile.c.a(null, ((ADProfile.c)localObject2).b());
                    if (((String)localObject2).indexOf("null") == -1)
                      localHashMap.put(localObject2, "");
                  }
                }
                break label92;
              }
              if ((((File)localObject1).getName().indexOf("_") <= 0) || (((File)localObject1).getName().indexOf('.') <= 0))
                ((File)localObject1).delete();
              else if (!localHashMap.containsKey(((File)localObject1).getName().substring(0, ((File)localObject1).getName().indexOf('.'))))
                ((File)localObject1).delete();
              label356: i += 1;
            }
          }
        }
        catch (Exception localException)
        {
        }
      }
    }
  }

  private void c(Bundle paramBundle)
  {
    try
    {
      paramBundle = (ADProfile)paramBundle.getParcelable("ADPROFILE");
      if (com.intowow.sdk.j.j.a(this.a.f().e(paramBundle.h()[0])))
      {
        com.intowow.sdk.j.j.a(this.a.b());
        return;
      }
      com.intowow.sdk.j.j.b(this.a.b());
      return;
    }
    catch (Exception paramBundle)
    {
    }
  }

  private void d()
  {
    this.b = true;
    if (this.a.f() != null);
    for (String str = this.a.f().M(); ; str = com.intowow.sdk.j.i.b(this.a.b()))
    {
      str = String.format("%s/%s.json", new Object[] { com.intowow.sdk.a.e.d, str });
      this.a.g().a(str, null, new d.a()
      {
        public void a(int paramAnonymousInt)
        {
          e.a(e.this, false);
        }

        public void a(JSONObject paramAnonymousJSONObject)
        {
          try
          {
            long l = paramAnonymousJSONObject.optLong("updated_time");
            com.intowow.sdk.f.b localb = e.a(e.this).f();
            if ((localb.F() != null) && (localb.F().a == l))
              e.a(e.this, false);
            while (true)
            {
              if (localb.B())
              {
                e.c(e.this, true);
                e.c(e.this);
              }
              if (localb.A())
              {
                e.d(e.this, true);
                e.d(e.this);
              }
              e.e(e.this);
              paramAnonymousJSONObject = paramAnonymousJSONObject.optJSONObject("asset_config");
              if (paramAnonymousJSONObject != null)
              {
                l = paramAnonymousJSONObject.optLong("updated_time", -1L);
                if (l > localb.w())
                  localb.x();
                e.e(e.this, true);
                e.a(e.this, l);
              }
              label146: e.a(e.this, false);
              return;
              localb.d(paramAnonymousJSONObject);
              if (localb.z())
              {
                e.b(e.this, true);
                e.b(e.this);
              }
            }
          }
          catch (Exception paramAnonymousJSONObject)
          {
            break label146;
          }
        }
      });
      return;
    }
  }

  private void d(Bundle paramBundle)
  {
    while (true)
    {
      try
      {
        Object localObject1 = this.a.f();
        if (localObject1 == null)
          return;
        g localg = new g(com.intowow.sdk.a.e.a);
        String str = URLDecoder.decode(paramBundle.getString("snapshot_url"), "utf-8");
        JSONObject localJSONObject1 = new JSONObject();
        paramBundle = com.intowow.sdk.j.k.a(this.a.b());
        localJSONObject1.put("device_id", ((com.intowow.sdk.f.b)localObject1).a(0, ""));
        localJSONObject1.put("crystal_id", ((com.intowow.sdk.f.b)localObject1).M());
        localJSONObject1.put("ot", 0);
        localJSONObject1.put("level", com.intowow.sdk.j.c.d());
        localJSONObject1.put("screen_width", com.intowow.sdk.j.c.e());
        localJSONObject1.put("screen_height", com.intowow.sdk.j.c.f());
        localJSONObject1.put("dpi", com.intowow.sdk.j.c.c());
        localJSONObject1.put("cr", com.intowow.sdk.j.i.e(this.a.b()));
        localJSONObject1.put("mf", com.intowow.sdk.j.i.c());
        localJSONObject1.put("dm", com.intowow.sdk.j.i.b());
        localJSONObject1.put("max_external_storage", com.intowow.sdk.j.k.a(paramBundle.a()));
        localJSONObject1.put("available_external_storage", paramBundle.f());
        localJSONObject1.put("max_internal_storage", com.intowow.sdk.j.k.a(this.a.b().getCacheDir().getAbsolutePath()));
        localJSONObject1.put("available_internal_storage", com.intowow.sdk.j.k.b(this.a.b().getCacheDir().getAbsolutePath()));
        localJSONObject1.put("asset_storage", com.intowow.sdk.j.k.c(paramBundle.b()));
        localJSONObject1.put("creative_storage", com.intowow.sdk.j.k.c(paramBundle.a()));
        localJSONObject1.put("sv", com.intowow.sdk.j.i.d());
        localJSONObject1.put("av", com.intowow.sdk.j.i.d(this.a.b()));
        Object localObject2 = new JSONArray();
        JSONObject localJSONObject2 = new JSONObject();
        Object localObject3 = new JSONArray();
        Object localObject4 = ((com.intowow.sdk.f.b)localObject1).c();
        int i;
        if (localObject4 != null)
        {
          localObject4 = ((List)localObject4).iterator();
          if (((Iterator)localObject4).hasNext());
        }
        else
        {
          localJSONObject2.put("adstate", localObject3);
          ((JSONArray)localObject2).put(localJSONObject2);
          localJSONObject2 = new JSONObject();
          localObject3 = new JSONArray();
          localObject4 = ((com.intowow.sdk.f.b)localObject1).d();
          if (localObject4 != null)
          {
            localObject4 = ((List)localObject4).iterator();
            if (((Iterator)localObject4).hasNext())
              continue;
          }
          localJSONObject2.put("adFreqcapState", localObject3);
          ((JSONArray)localObject2).put(localJSONObject2);
          localJSONObject2 = new JSONObject();
          localObject3 = new JSONArray();
          localObject4 = ((com.intowow.sdk.f.b)localObject1).b();
          if (localObject4 != null)
          {
            i = 0;
            if (i < ((SparseArray)localObject4).size())
              continue;
          }
          localJSONObject2.put("preference", localObject3);
          ((JSONArray)localObject2).put(localJSONObject2);
          localJSONObject1.put("db_info", localObject2);
          localObject2 = new JSONArray();
          if (com.intowow.sdk.j.k.e())
          {
            paramBundle = new File(paramBundle.a());
            if (paramBundle.exists())
            {
              paramBundle = paramBundle.listFiles();
              j = paramBundle.length;
              i = 0;
              continue;
            }
          }
          localJSONObject1.put("storage_info", localObject2);
          paramBundle = new JSONObject();
          localObject2 = ((com.intowow.sdk.f.b)localObject1).H();
          if (localObject2 == null)
            continue;
          paramBundle.put("stored", com.intowow.sdk.model.l.a((com.intowow.sdk.model.l)localObject2));
          localObject2 = this.a.p();
          if (localObject2 == null)
            continue;
          localJSONObject2 = new JSONObject();
          localObject3 = ((Map)localObject2).keySet().iterator();
          if (((Iterator)localObject3).hasNext())
            continue;
          paramBundle.put("pending", localJSONObject2);
          localJSONObject1.put("req_info", paramBundle);
          paramBundle = ((com.intowow.sdk.f.b)localObject1).h(".serving_cfg");
          localObject2 = ((com.intowow.sdk.f.b)localObject1).h(".ph_cfg");
          localObject1 = ((com.intowow.sdk.f.b)localObject1).h(".adlist");
          if (paramBundle == null)
            break label1211;
          localJSONObject1.put("serving_cfg", new JSONObject(paramBundle));
          if (localObject2 == null)
            break label1218;
          paramBundle = (Bundle)localObject2;
          localJSONObject1.put("ph_cfg", new JSONObject(paramBundle));
          if (localObject1 == null)
            continue;
          paramBundle = (Bundle)localObject1;
          localJSONObject1.put("ad_list", new JSONObject(paramBundle));
          paramBundle = new JSONObject();
          paramBundle.put("snapshot", localJSONObject1);
          localg.a(str, paramBundle, new g.a()
          {
            public void a(int paramAnonymousInt, String paramAnonymousString)
            {
            }

            public void a(int paramAnonymousInt, String paramAnonymousString, Exception paramAnonymousException)
            {
            }
          });
          return;
        }
        Object localObject5 = (com.intowow.sdk.model.c)((Iterator)localObject4).next();
        JSONObject localJSONObject3 = new JSONObject();
        localJSONObject3.put("adid", ((com.intowow.sdk.model.c)localObject5).a());
        localJSONObject3.put("last_view", ((com.intowow.sdk.model.c)localObject5).b());
        localJSONObject3.put("state", ADProfile.n.values()[localObject5.d()].toString());
        ((JSONArray)localObject3).put(localJSONObject3);
        continue;
        localObject5 = (com.intowow.sdk.model.a)((Iterator)localObject4).next();
        localJSONObject3 = new JSONObject();
        localJSONObject3.put("adid", ((com.intowow.sdk.model.a)localObject5).a());
        localJSONObject3.put("slidingWindow", ((com.intowow.sdk.model.a)localObject5).b());
        localJSONObject3.put("firstView", ((com.intowow.sdk.model.a)localObject5).c());
        localJSONObject3.put("impressions", ((com.intowow.sdk.model.a)localObject5).d());
        ((JSONArray)localObject3).put(localJSONObject3);
        continue;
        localObject5 = new JSONObject();
        int j = ((SparseArray)localObject4).keyAt(i);
        ((JSONObject)localObject5).put("id", j);
        ((JSONObject)localObject5).put("value", ((SparseArray)localObject4).get(j));
        ((JSONArray)localObject3).put(localObject5);
        i += 1;
        continue;
        localJSONObject2 = paramBundle[i];
        localObject3 = new JSONObject();
        ((JSONObject)localObject3).put("name", localJSONObject2.getName());
        ((JSONObject)localObject3).put("size", localJSONObject2.length());
        ((JSONObject)localObject3).put("updated_time", localJSONObject2.lastModified());
        ((JSONArray)localObject2).put(localObject3);
        i += 1;
        continue;
        paramBundle.put("stored", new JSONObject());
        continue;
        localObject4 = (String)((Iterator)localObject3).next();
        localJSONObject2.put((String)localObject4, ((Map)localObject2).get(localObject4));
        continue;
        paramBundle.put("pending", new JSONObject());
        continue;
        paramBundle = "{}";
        continue;
        if (i < j)
          continue;
        continue;
      }
      catch (Exception paramBundle)
      {
        return;
      }
      label1211: paramBundle = "{}";
      continue;
      label1218: paramBundle = "{}";
    }
  }

  private void e()
  {
    String str = com.intowow.sdk.j.a.a(this.a.b(), this.a.f());
    if (str == null)
    {
      this.f = false;
      return;
    }
    this.a.g().a(str, null, new d.a()
    {
      public void a(int paramAnonymousInt)
      {
        e.c(e.this, false);
      }

      public void a(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          com.intowow.sdk.f.b localb = e.a(e.this).f();
          if (localb == null)
          {
            e.c(e.this, false);
            return;
          }
          if (localb.b(paramAnonymousJSONObject))
          {
            e.c(e.this, false);
            if ((!e.f(e.this)) && (localb.A()))
            {
              e.d(e.this, true);
              e.d(e.this);
            }
          }
          label72: e.c(e.this, false);
          return;
        }
        catch (Exception paramAnonymousJSONObject)
        {
          break label72;
        }
      }
    });
  }

  private void e(Bundle paramBundle)
  {
    int i = paramBundle.getInt("adid");
    final com.intowow.sdk.f.b localb = this.a.f();
    if ((localb == null) || (!localb.v()));
    while (true)
    {
      return;
      if ((localb.F() != null) && (!com.intowow.sdk.j.l.a(localb.F().a())))
        paramBundle = localb.F().a();
      while (!com.intowow.sdk.j.l.a(paramBundle))
      {
        paramBundle = String.format("%s?adid=%d&level=%d&crystal_id=%s", new Object[] { paramBundle, Integer.valueOf(i), Integer.valueOf(com.intowow.sdk.j.c.d()), localb.M() });
        this.a.g().a(paramBundle, null, new d.a()
        {
          public void a(int paramAnonymousInt)
          {
            com.intowow.sdk.b.e.a(e.a(e.this).b()).p();
            if (localb != null)
            {
              if (paramAnonymousInt == 0)
                localb.a(com.intowow.sdk.model.k.f);
            }
            else
              return;
            localb.a(com.intowow.sdk.model.k.g);
          }

          public void a(JSONObject paramAnonymousJSONObject)
          {
            try
            {
              JSONObject localJSONObject = paramAnonymousJSONObject.getJSONObject("delivery_setting");
              long l = localb.s();
              localJSONObject.put("start_date", 0L);
              localJSONObject.put("end_date", (l + 31536000000L) / 1000L);
              localJSONObject.put("time_slots", "111111111111111111111111");
              localb.a(paramAnonymousJSONObject);
              return;
            }
            catch (Exception paramAnonymousJSONObject)
            {
            }
          }
        });
        return;
        if (!com.intowow.sdk.j.l.a(com.intowow.sdk.a.e.e))
          paramBundle = com.intowow.sdk.a.e.e;
        else
          paramBundle = null;
      }
    }
  }

  private void f()
  {
    String str = this.a.f().D();
    if (str == null)
    {
      this.c = false;
      return;
    }
    this.a.g().a(str, null, new d.a()
    {
      public void a(int paramAnonymousInt)
      {
        e.b(e.this, false);
      }

      public void a(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          e.a(e.this).f().c(paramAnonymousJSONObject);
          if (e.a(e.this).f().z())
            e.b(e.this);
          label37: e.b(e.this, false);
          return;
        }
        catch (Exception paramAnonymousJSONObject)
        {
          break label37;
        }
      }
    });
  }

  private void g()
  {
    com.intowow.sdk.f.b localb = this.a.f();
    if (localb == null);
    String str;
    do
    {
      return;
      str = com.intowow.sdk.j.i.c(this.a.b());
      if (!localb.j())
      {
        this.a.j().b();
        localb.a(true);
        localb.c(str);
        return;
      }
    }
    while (str.compareTo(localb.o()) <= 0);
    this.a.j().c();
    localb.c(str);
  }

  private void h()
  {
    String str = com.intowow.sdk.j.a.a(this.a);
    if (str == null)
    {
      this.d = false;
      return;
    }
    this.a.g().a(str, null, new d.a()
    {
      public void a(int paramAnonymousInt)
      {
        e.d(e.this, false);
      }

      public void a(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          e.a(e.this).f().e(paramAnonymousJSONObject);
          label14: e.d(e.this, false);
          return;
        }
        catch (Exception paramAnonymousJSONObject)
        {
          break label14;
        }
      }
    });
  }

  public void a()
  {
    while (true)
    {
      try
      {
        boolean bool = com.intowow.sdk.h.k.a(this.a.c().a());
        if (!bool)
          return;
        if ((!this.b) && (!this.c) && (!this.d) && (!this.e) && (!this.f))
        {
          i = 0;
          if ((i != 0) || (!this.a.f().y()))
            continue;
          d();
          continue;
        }
      }
      finally
      {
      }
      int i = 1;
    }
  }

  public void a(Message paramMessage)
  {
    try
    {
      paramMessage = paramMessage.getData();
      h.b localb = h.b.values()[paramMessage.getInt("type")];
      switch (b()[localb.ordinal()])
      {
      case 2:
        c();
        a(paramMessage);
        return;
      case 3:
      case 12:
      case 14:
      case 13:
      }
    }
    catch (Exception paramMessage)
    {
      h.a(paramMessage);
      return;
    }
    b(paramMessage);
    return;
    e(paramMessage);
    return;
    d(paramMessage);
    return;
    c(paramMessage);
    return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.g.e
 * JD-Core Version:    0.6.2
 */