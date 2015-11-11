package com.taobao.newxp.common.b;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.XpListenersCenter.ExchangeDataRequestListener;
import com.taobao.newxp.controller.XpListenersCenter.STATUS;
import com.taobao.newxp.net.MMEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class e
{
  public static String a(String paramString)
  {
    String str = paramString;
    if (paramString.contains("/"))
      str = paramString.replace("/", "^$^");
    return str;
  }

  public static <T extends Promoter> List<T> a(List<T> paramList1, List<T> paramList2)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = paramList2.iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Promoter)((Iterator)localObject1).next();
      if (!a(((Promoter)localObject2).app_package_name, paramList1))
        localArrayList.add(localObject2);
    }
    if (ExchangeConstants.DEBUG_MODE)
    {
      paramList1 = new StringBuilder();
      localObject1 = new StringBuilder();
      localObject2 = localArrayList.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        paramList1.append(((Promoter)((Iterator)localObject2).next()).title);
        paramList1.append(", ");
      }
      paramList2 = paramList2.iterator();
      while (paramList2.hasNext())
      {
        ((StringBuilder)localObject1).append(((Promoter)paramList2.next()).title);
        ((StringBuilder)localObject1).append(", ");
      }
      Log.c(ExchangeConstants.LOG_TAG, "Showing next page data, before filtered: " + ((StringBuilder)localObject1).toString());
      Log.c(ExchangeConstants.LOG_TAG, "Showing next page data, after filtered: " + paramList1.toString());
    }
    return localArrayList;
  }

  public static void a(Context paramContext)
  {
    com.taobao.munion.base.d.a(paramContext);
  }

  public static void a(final ImageView paramImageView1, final ImageView paramImageView2, final Context paramContext, ExchangeDataService paramExchangeDataService, a parama)
  {
    paramExchangeDataService.requestDataAsyn(paramContext, new XpListenersCenter.ExchangeDataRequestListener()
    {
      public void dataReceived(int paramAnonymousInt, final List<Promoter> paramAnonymousList)
      {
        if (paramAnonymousInt == 0)
          this.a.a(0, paramAnonymousList);
        do
        {
          do
            return;
          while (this.b.landing_image == null);
          paramAnonymousList = new d.a()
          {
            public void a(d.b paramAnonymous2b)
            {
            }

            public void a(XpListenersCenter.STATUS paramAnonymous2STATUS)
            {
              if (paramAnonymous2STATUS == XpListenersCenter.STATUS.SUCCESS)
              {
                if ((e.1.this.b.new_image != null) && (e.1.this.b.new_image.trim().length() > 0))
                {
                  e.1.this.a.a(2, paramAnonymousList);
                  return;
                }
                e.1.this.a.a(1, paramAnonymousList);
                return;
              }
              e.1.this.a.a(0, paramAnonymousList);
            }
          };
          d.a(paramContext, paramImageView1, this.b.landing_image, false, paramAnonymousList, null);
        }
        while ((this.b.new_image == null) || (this.b.new_image.trim().length() <= 0));
        d.a(paramContext, paramImageView2, this.b.new_image, false, null, null);
      }
    }
    , true);
  }

  public static boolean a(int paramInt)
  {
    boolean bool = false;
    if (43 == paramInt);
    try
    {
      Class.forName("com.taobao.newxp.view.largeimage.LargeGallery");
      bool = true;
      return bool;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e(ExchangeConstants.LOG_TAG, "the LargeImage model is no exist.");
    }
    return false;
  }

  private static boolean a(String paramString, List<? extends Promoter> paramList)
  {
    if ((paramString == null) || (paramList == null) || (paramList.size() < 1))
      return false;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Promoter localPromoter = (Promoter)paramList.next();
      if ((!TextUtils.isEmpty(localPromoter.app_package_name)) && (paramString.equalsIgnoreCase(localPromoter.app_package_name)))
        return true;
    }
    return false;
  }

  public static boolean a(String paramString, boolean paramBoolean, String[] paramArrayOfString)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramArrayOfString == null) || (paramArrayOfString.length < 1) || (paramString == null))
    {
      bool1 = false;
      return bool1;
    }
    int j = paramArrayOfString.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        break label83;
      String str = paramArrayOfString[i];
      if (paramBoolean)
      {
        bool1 = bool2;
        if (str.equalsIgnoreCase(paramString))
          break;
      }
      if (!paramBoolean)
      {
        bool1 = bool2;
        if (str.equals(paramString))
          break;
      }
      i += 1;
    }
    label83: return false;
  }

  public static boolean a(Collection<? extends Promoter> paramCollection, Field paramField, Object paramObject)
  {
    return a(paramCollection, new Field[] { paramField }, new Object[] { paramObject });
  }

  public static boolean a(Collection<? extends Promoter> paramCollection, Field[] paramArrayOfField, Object[] paramArrayOfObject)
  {
    Promoter localPromoter;
    if ((paramCollection != null) && (paramCollection.size() > 0))
    {
      paramCollection = paramCollection.iterator();
      if (paramCollection.hasNext())
        localPromoter = (Promoter)paramCollection.next();
    }
    while (true)
    {
      int j;
      int k;
      int m;
      try
      {
        int n = paramArrayOfField.length;
        j = 0;
        i = 0;
        k = 0;
        if (j < n)
        {
          Field localField = paramArrayOfField[j];
          localField.setAccessible(true);
          m = i;
          if (localField.get(localPromoter) != paramArrayOfObject[k])
            break label118;
          m = i + 1;
          break label118;
        }
        j = paramArrayOfField.length;
        if (i != j)
          break;
        return true;
        return false;
        return false;
      }
      catch (Exception localException)
      {
      }
      break;
      label118: k += 1;
      j += 1;
      int i = m;
    }
  }

  public static String b(String paramString)
  {
    String str = paramString;
    if (paramString.contains("^$^"))
      str = paramString.replace("^$^", "/");
    return str;
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt, List<Promoter> paramList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.b.e
 * JD-Core Version:    0.6.2
 */