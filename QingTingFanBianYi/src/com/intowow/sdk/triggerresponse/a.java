package com.intowow.sdk.triggerresponse;

import com.intowow.sdk.a.e;
import com.intowow.sdk.j.f;
import com.intowow.sdk.j.g;
import com.intowow.sdk.j.h;
import com.intowow.sdk.j.l;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;

public class a
{
  public static void a(d paramd, String[] paramArrayOfString)
  {
    int i = 0;
    while (true)
    {
      String str3;
      try
      {
        String str2 = paramd.c().getAbsolutePath();
        synchronized (paramd.a())
        {
          if (i >= paramArrayOfString.length)
            return;
          str1 = paramArrayOfString[i];
          if (l.a(str1))
            break label336;
          str3 = paramd.d() + System.currentTimeMillis() + "_" + i;
          if (str1.indexOf("?") != -1)
          {
            str1 = str1 + "&ce_et=" + System.currentTimeMillis();
            Object localObject2 = str1.replaceAll("__OS__", "0");
            str1 = com.intowow.sdk.j.d.d(paramd.b());
            if (l.a(str1))
              break label345;
            str1 = l.c(str1);
            str1 = ((String)localObject2).replaceAll("__ANDROIDID__", str1);
            f.b(str2 + "/" + str3, str1);
            if (!e.a)
              break label336;
            localObject2 = new File(str2 + "/" + str3);
            if ((!((File)localObject2).exists()) || (!((File)localObject2).isFile()))
              break label318;
            h.b("third-party tracking write ok ! [%s] modify[%d] [%s]", new Object[] { str3, Long.valueOf(((File)localObject2).lastModified()), str1 });
          }
        }
      }
      finally
      {
      }
      String str1 = str1 + "?ce_et=" + System.currentTimeMillis();
      continue;
      label318: h.b("third-party tracking write error ! [%s] [%s]", new Object[] { str3, str1 });
      label336: i += 1;
      continue;
      label345: str1 = "";
    }
  }

  public static void a(final boolean paramBoolean, d paramd)
  {
    if (paramd == null);
    while (!paramd.f())
      return;
    paramd.e().submit(new Runnable()
    {
      public void run()
      {
        while (true)
        {
          int i;
          int j;
          try
          {
            Object localObject1 = a.this.a();
            File localFile1 = a.this.c();
            File[] arrayOfFile = a.a(localFile1, localObject1, a.this.d());
            if (arrayOfFile != null)
            {
              i = arrayOfFile.length;
              if (i != 0);
            }
            else
            {
              return;
            }
            int k = arrayOfFile.length;
            i = 0;
            if (i >= k)
              continue;
            localFile2 = arrayOfFile[i];
            String str = f.a(localFile2.getAbsolutePath(), "", "UTF-8");
            g localg = new g(paramBoolean);
            localg.a(20000);
            j = 3;
            break label265;
            boolean bool = a.this.f();
            if (!bool)
              return;
            h.b("third-party tracking send [%s] retry[%d]", new Object[] { localFile2.getName(), Integer.valueOf(j) });
            if (localg.a(str, 0))
            {
              h.b("third-party tracking success ! [%s]", new Object[] { localFile2.getName() });
              localFile2.delete();
            }
          }
          catch (Exception localException1)
          {
            return;
            if (j == 0)
              h.b("third-party tracking error ! [%s]", new Object[] { localFile2.getName() });
          }
          finally
          {
            File localFile2;
            a.this.g();
          }
          try
          {
            h.b("third-party tracking sleep ", new Object[0]);
            Thread.sleep(5000L);
          }
          catch (Exception localException2)
          {
          }
          label265: j -= 1;
          if (j < 0)
            i += 1;
        }
      }
    });
  }

  public static void a(boolean paramBoolean, d paramd, String[] paramArrayOfString)
  {
    a(paramd, paramArrayOfString);
    a(paramBoolean, paramd);
  }

  private static File[] b(File paramFile, Object paramObject, String paramString)
  {
    try
    {
      paramFile = paramFile.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return paramAnonymousString.startsWith(a.this);
        }
      });
      return paramFile;
    }
    finally
    {
    }
    throw paramFile;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.triggerresponse.a
 * JD-Core Version:    0.6.2
 */