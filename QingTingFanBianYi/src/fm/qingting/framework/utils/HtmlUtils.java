package fm.qingting.framework.utils;

import java.util.ArrayList;
import java.util.List;

public class HtmlUtils
{
  public static String formatTags(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramString.indexOf(paramArrayOfString1[0]);
    int k = paramString.indexOf(paramArrayOfString1[1]);
    int j = 0;
    while (true)
    {
      if ((i < 0) && (k < 0))
      {
        localStringBuilder.append(paramString.substring(j));
        return localStringBuilder.toString();
      }
      if (((i >= 0) && (k >= i)) || (k < 0))
        break;
      localStringBuilder.append(paramString.substring(j, k));
      j = k + paramArrayOfString1[1].length();
      k = paramString.indexOf(paramArrayOfString1[1], j);
    }
    int m = i + paramArrayOfString1[0].length();
    i = paramString.indexOf(paramArrayOfString1[0], m);
    int n = paramString.indexOf(paramArrayOfString1[1], m);
    int i1 = paramString.indexOf(paramArrayOfString2[0], m);
    k = paramString.indexOf(paramArrayOfString2[1], m);
    int i2 = paramString.indexOf(paramArrayOfString3[0], m);
    m = tagendPos(i, n, i1, k, i2, paramString.indexOf(paramArrayOfString3[1], m));
    label204: if (m != i2)
    {
      if (m != i)
        break label442;
      localStringBuilder.append(paramString.substring(j, i));
      localStringBuilder.append(paramArrayOfString1[1]);
    }
    while (true)
    {
      k = paramString.indexOf(paramArrayOfString1[1], i);
      m = paramString.indexOf(paramArrayOfString1[0], i);
      j = i;
      i = m;
      break;
      List localList = getBodyPos(paramString, paramArrayOfString3[0], paramArrayOfString3[1], i2);
      i = paramString.indexOf(paramArrayOfString1[0], ((Integer)localList.get(1)).intValue());
      n = paramString.indexOf(paramArrayOfString1[1], ((Integer)localList.get(1)).intValue());
      i1 = paramString.indexOf(paramArrayOfString2[0], ((Integer)localList.get(1)).intValue());
      k = paramString.indexOf(paramArrayOfString2[1], ((Integer)localList.get(1)).intValue());
      i2 = paramString.indexOf(paramArrayOfString3[0], ((Integer)localList.get(1)).intValue());
      m = tagendPos(i, n, i1, k, i2, paramString.indexOf(paramArrayOfString3[1], ((Integer)localList.get(1)).intValue()));
      break label204;
      label442: if (m != k)
        break label479;
      localStringBuilder.append(paramString.substring(j, k));
      localStringBuilder.append(paramArrayOfString1[1]);
      i = k;
    }
    label479: if (m == n)
      i = paramArrayOfString1[1].length();
    while (true)
    {
      localStringBuilder.append(paramString.substring(j, m + i));
      i = m + i;
      break;
      if (m == i1)
        i = paramArrayOfString2[0].length();
      else if (m == i2)
        i = paramArrayOfString3[0].length();
      else
        i = paramArrayOfString3[1].length();
    }
  }

  public static List<Integer> getBodyPos(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    int i = 0;
    ArrayList localArrayList = null;
    int i1 = paramString1.indexOf(paramString2, paramInt);
    int j;
    int k;
    label55: int m;
    if (i1 >= 0)
    {
      j = paramString1.indexOf(paramString2, paramString2.length() + i1);
      paramInt = paramString1.indexOf(paramString3, paramString2.length() + i1);
      if (j >= paramInt)
        break label189;
      k = 1;
      if (j < 0)
        break label195;
      m = 1;
      label63: if (i > 100)
        break label201;
    }
    label189: label195: label201: for (int n = 1; ; n = 0)
    {
      if ((n & (k & m)) == 0)
      {
        i = paramString3.length();
        localArrayList = new ArrayList();
        localArrayList.add(Integer.valueOf(i1));
        localArrayList.add(Integer.valueOf(paramInt + i));
        return localArrayList;
      }
      paramInt = ((Integer)getBodyPos(paramString1, paramString2, paramString3, j).get(1)).intValue();
      j = paramString1.indexOf(paramString2, paramInt);
      k = paramString1.indexOf(paramString3, paramInt);
      paramInt = k;
      if (k < 0)
        paramInt = paramString1.length();
      i += 1;
      break;
      k = 0;
      break label55;
      m = 0;
      break label63;
    }
  }

  public static int tagendPos(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    int i = paramInt1;
    if ((paramInt2 >= i) || (paramInt2 < 0))
    {
      paramInt1 = i;
      if (i >= 0);
    }
    else
    {
      paramInt1 = paramInt2;
    }
    if ((paramInt3 >= paramInt1) || (paramInt3 < 0))
    {
      paramInt2 = paramInt1;
      if (paramInt1 >= 0);
    }
    else
    {
      paramInt2 = paramInt3;
    }
    if ((paramInt4 >= paramInt2) || (paramInt4 < 0))
    {
      paramInt1 = paramInt2;
      if (paramInt2 >= 0);
    }
    else
    {
      paramInt1 = paramInt4;
    }
    if ((paramInt5 >= paramInt1) || (paramInt5 < 0))
    {
      paramInt2 = paramInt1;
      if (paramInt1 >= 0);
    }
    else
    {
      paramInt2 = paramInt5;
    }
    if ((paramInt6 >= paramInt2) || (paramInt6 < 0))
    {
      paramInt1 = paramInt2;
      if (paramInt2 >= 0);
    }
    else
    {
      paramInt1 = paramInt6;
    }
    return paramInt1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.HtmlUtils
 * JD-Core Version:    0.6.2
 */