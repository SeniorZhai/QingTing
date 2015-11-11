package com.intowow.sdk.j;

import android.os.Environment;
import com.intowow.sdk.model.ADProfile;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class o
{
  private com.intowow.sdk.f.b a = null;

  public o(com.intowow.sdk.f.b paramb)
  {
    this.a = paramb;
  }

  private void b()
  {
    try
    {
      Object localObject1 = this.a.P();
      if ((localObject1 != null) && (((com.intowow.sdk.model.b)localObject1).a() > 0))
        localObject1 = ((com.intowow.sdk.model.b)localObject1).b().iterator();
      while (true)
      {
        boolean bool = ((Iterator)localObject1).hasNext();
        if (!bool)
          return;
        ADProfile localADProfile = (ADProfile)((Iterator)localObject1).next();
        Iterator localIterator = localADProfile.n().keySet().iterator();
        while (localIterator.hasNext())
        {
          Integer localInteger = (Integer)localIterator.next();
          if (localInteger.intValue() != 1)
            this.a.a(localADProfile.e(), localInteger.intValue());
        }
      }
    }
    finally
    {
    }
  }

  private void c()
  {
    while (true)
    {
      int i;
      try
      {
        this.a.J();
        this.a.K();
        this.a.L();
        File[] arrayOfFile = new File(k.a(this.a.O()).a()).listFiles();
        if ((arrayOfFile != null) && (arrayOfFile.length > 0))
        {
          int j = arrayOfFile.length;
          i = 0;
          if (i < j);
        }
        else
        {
          return;
        }
        File localFile = arrayOfFile[i];
        if (!localFile.getName().toLowerCase().equals(".nomedia"))
          localFile.delete();
      }
      finally
      {
      }
      i += 1;
    }
  }

  private void d()
  {
    int j = 0;
    while (true)
    {
      Object localObject2;
      Object localObject3;
      int k;
      int i;
      try
      {
        File localFile;
        if (this.a.M().equals("094e05b807c34f228af5531c8f7149b8"))
        {
          localObject2 = k.a(this.a.O()).a();
          localObject3 = String.format("%s/%s/%s", new Object[] { Environment.getExternalStorageDirectory().getAbsolutePath(), "AppleAd", "Creatives" });
          localFile = new File((String)localObject3);
          boolean bool = localFile.exists();
          if (bool);
        }
        else
        {
          return;
        }
        localObject3 = new File((String)localObject3).listFiles(new FilenameFilter()
        {
          public boolean accept(File paramAnonymousFile, String paramAnonymousString)
          {
            return paramAnonymousString.indexOf("_") > 0;
          }
        });
        if ((localObject3 != null) && (localObject3.length > 0))
        {
          k = localObject3.length;
          i = 0;
          break label230;
        }
        localObject2 = localFile.listFiles();
        if ((localObject2 != null) && (localObject2.length > 0))
        {
          k = localObject2.length;
          i = j;
          break label240;
        }
        localFile.delete();
        continue;
      }
      finally
      {
      }
      label163: Object localObject4 = localObject3[i];
      localObject4.renameTo(new File(localObject2 + localObject4.getName()));
      i += 1;
      label230: label240: 
      do
      {
        localObject2[i].delete();
        i += 1;
        continue;
        if (i < k)
          break label163;
        break;
      }
      while (i < k);
    }
  }

  public void a()
  {
    try
    {
      int i = Integer.parseInt(this.a.p());
      if (i != 3)
        i += 1;
      while (true)
      {
        if (i > 3)
        {
          this.a.d("3");
          return;
          d();
          break label84;
          c();
          break label84;
          b();
        }
        else
        {
          switch (i)
          {
          case 1:
          case 2:
          case 3:
          }
        }
        label84: i += 1;
      }
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.o
 * JD-Core Version:    0.6.2
 */