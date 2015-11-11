package com.neusoft.parse;

import java.util.HashMap;

public class DataParser
{
  public static final String APP_ID = "APP_ID";
  public static final String APP_ID_LEN = "APP_ID_LEN";
  public static final String CRC = "CRC";
  public static final String DATA_COUNT = "DATA_COUNT";
  public static final String DATA_INDEX = "DATA_%d";
  public static final String DATA_INDEX_LEN = "DATA_%d_LEN";
  public static final String FLOW_ID = "FLOW_ID";
  public static final String LOGIC_ID = "LOGIC_ID";
  public static final String LOGIC_ID_LEN = "LOGIC_ID_LEN";
  public static final String TOTAL_LEN = "TOTAL_LEN";
  private HashMap a = new HashMap();

  public static String createData(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return null;
    int m = paramString1.getBytes().length;
    int n = paramString2.getBytes().length;
    if (paramArrayOfString != null);
    for (int i = paramArrayOfString.length; ; i = 0)
    {
      int[] arrayOfInt = new int[i];
      int k = m + 16 + 2 + n + 2;
      int j = 0;
      StringBuilder localStringBuilder;
      if (j >= i)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("A6A6");
        localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(paramInt) }));
        localStringBuilder.append(String.format("%08x", new Object[] { Integer.valueOf(k + 4) }));
        localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(m) }));
        localStringBuilder.append(paramString1);
        localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(n) }));
        localStringBuilder.append(paramString2);
        localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(i) }));
        paramInt = 0;
      }
      while (true)
      {
        if (paramInt >= i)
        {
          localStringBuilder.append(String.format("%04x", new Object[] { Integer.valueOf(CRC16.crcProcess(localStringBuilder.toString().getBytes())) }));
          return localStringBuilder.toString();
          arrayOfInt[j] = paramArrayOfString[j].getBytes().length;
          k += arrayOfInt[j] + 8;
          j += 1;
          break;
        }
        localStringBuilder.append(String.format("%08x", new Object[] { Integer.valueOf(arrayOfInt[paramInt]) }));
        localStringBuilder.append(paramArrayOfString[paramInt]);
        paramInt += 1;
      }
    }
  }

  public String getAppID()
  {
    return (String)this.a.get("APP_ID");
  }

  public String getCRC()
  {
    return (String)this.a.get("CRC");
  }

  public String getData(int paramInt)
  {
    String str = String.format("DATA_%d", new Object[] { Integer.valueOf(paramInt) });
    return (String)this.a.get(str);
  }

  public String[] getData()
  {
    int j = getDataCount();
    String[] arrayOfString = new String[j];
    int i = 0;
    while (true)
    {
      if (i >= j)
        return arrayOfString;
      String str = String.format("DATA_%d", new Object[] { Integer.valueOf(i) });
      arrayOfString[i] = ((String)this.a.get(str));
      i += 1;
    }
  }

  public int getDataCount()
  {
    return ((Integer)this.a.get("DATA_COUNT")).intValue();
  }

  public int getFlowID()
  {
    return ((Integer)this.a.get("FLOW_ID")).intValue();
  }

  public String getLogicID()
  {
    return (String)this.a.get("LOGIC_ID");
  }

  public boolean parse(byte[] paramArrayOfByte)
  {
    this.a.clear();
    if (paramArrayOfByte == null);
    do
    {
      do
        return false;
      while ((CRC16.crcProcess(paramArrayOfByte, 0, paramArrayOfByte.length - 4) != Integer.parseInt(new String(paramArrayOfByte, paramArrayOfByte.length - 4, 4), 16)) || (!new String(paramArrayOfByte, 0, 4).equalsIgnoreCase("A6A6")));
      i = Integer.parseInt(new String(paramArrayOfByte, 4, 2), 16);
      this.a.put("FLOW_ID", Integer.valueOf(i));
      i = Integer.parseInt(new String(paramArrayOfByte, 6, 8), 16);
    }
    while (i != paramArrayOfByte.length);
    this.a.put("TOTAL_LEN", Integer.valueOf(i));
    int i = Integer.parseInt(new String(paramArrayOfByte, 14, 2), 16);
    this.a.put("APP_ID_LEN", Integer.valueOf(i));
    String str1 = new String(paramArrayOfByte, 16, i);
    i += 16;
    this.a.put("APP_ID", str1);
    str1 = new String(paramArrayOfByte, i, 2);
    i += 2;
    int j = Integer.parseInt(str1, 16);
    this.a.put("LOGIC_ID_LEN", Integer.valueOf(j));
    str1 = new String(paramArrayOfByte, i, j);
    i += j;
    this.a.put("LOGIC_ID", str1);
    int k = Integer.parseInt(new String(paramArrayOfByte, i, 2), 16);
    this.a.put("DATA_COUNT", Integer.valueOf(k));
    j = i + 2;
    i = 0;
    while (true)
    {
      if (i >= k)
      {
        paramArrayOfByte = new String(paramArrayOfByte, j, 4);
        this.a.put("CRC", paramArrayOfByte);
        return true;
      }
      String str2 = String.format("DATA_%d_LEN", new Object[] { Integer.valueOf(i) });
      str1 = String.format("DATA_%d", new Object[] { Integer.valueOf(i) });
      String str3 = new String(paramArrayOfByte, j, 8);
      j += 8;
      int m = Integer.parseInt(str3, 16);
      this.a.put(str2, Integer.valueOf(m));
      str2 = new String(paramArrayOfByte, j, m);
      j += m;
      this.a.put(str1, str2);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.parse.DataParser
 * JD-Core Version:    0.6.2
 */