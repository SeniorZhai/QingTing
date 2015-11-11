package com.taobao.munion.base.utdid.b.a;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class e
{
  public static final int a(CharSequence paramCharSequence)
  {
    int i = 16;
    int j = 1;
    paramCharSequence = paramCharSequence.toString();
    int k = paramCharSequence.length();
    if ('0' == paramCharSequence.charAt(0))
    {
      if (k - 1 == 0)
        return 0;
      k = paramCharSequence.charAt(1);
      if ((120 == k) || (88 == k))
        j = 2;
    }
    while (true)
    {
      return (int)Long.parseLong(paramCharSequence.substring(j), i);
      i = 8;
      continue;
      if ('#' != paramCharSequence.charAt(0))
      {
        i = 10;
        j = 0;
      }
    }
  }

  public static final int a(CharSequence paramCharSequence, int paramInt)
  {
    int i = 1;
    if (paramCharSequence == null)
      return paramInt;
    paramCharSequence = paramCharSequence.toString();
    int k = paramCharSequence.length();
    int j;
    if ('-' == paramCharSequence.charAt(0))
      j = -1;
    for (paramInt = i; ; paramInt = 0)
    {
      if ('0' == paramCharSequence.charAt(paramInt))
      {
        if (paramInt == k - 1)
          return 0;
        i = paramCharSequence.charAt(paramInt + 1);
        if ((120 == i) || (88 == i))
        {
          i = paramInt + 2;
          paramInt = 16;
        }
      }
      while (true)
      {
        return Integer.parseInt(paramCharSequence.substring(i), paramInt) * j;
        i = paramInt + 1;
        paramInt = 8;
        continue;
        if ('#' == paramCharSequence.charAt(paramInt))
        {
          i = paramInt + 1;
          paramInt = 16;
        }
        else
        {
          i = paramInt;
          paramInt = 10;
        }
      }
      j = 1;
    }
  }

  public static final int a(CharSequence paramCharSequence, String[] paramArrayOfString, int paramInt)
  {
    if (paramCharSequence != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        if (paramCharSequence.equals(paramArrayOfString[i]))
          return i;
        i += 1;
      }
    }
    return paramInt;
  }

  public static final int a(String paramString, int paramInt)
  {
    if (paramString == null)
      return paramInt;
    return a(paramString);
  }

  public static final Object a(XmlPullParser paramXmlPullParser, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getEventType();
    while (true)
    {
      if (i == 2)
        return b(paramXmlPullParser, paramArrayOfString);
      if (i == 3)
        throw new XmlPullParserException("Unexpected end tag at: " + paramXmlPullParser.getName());
      if (i == 4)
        throw new XmlPullParserException("Unexpected text: " + paramXmlPullParser.getText());
      try
      {
        int j = paramXmlPullParser.next();
        i = j;
        if (j == 1)
          throw new XmlPullParserException("Unexpected end of document");
      }
      catch (Exception paramArrayOfString)
      {
      }
    }
    throw new XmlPullParserException("Unexpected call next(): " + paramXmlPullParser.getName());
  }

  public static final HashMap a(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (HashMap)a(localXmlPullParser, new String[1]);
  }

  public static final HashMap a(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    HashMap localHashMap = new HashMap();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
    {
      localObject = b(paramXmlPullParser, paramArrayOfString);
      if (paramArrayOfString[0] != null)
        localHashMap.put(paramArrayOfString[0], localObject);
    }
    while (i != 3)
    {
      Object localObject;
      int j = paramXmlPullParser.next();
      i = j;
      if (j != 1)
        break;
      throw new XmlPullParserException("Document ended before " + paramString + " end tag");
      throw new XmlPullParserException("Map value without name attribute: " + paramXmlPullParser.getName());
    }
    if (paramXmlPullParser.getName().equals(paramString))
      return localHashMap;
    throw new XmlPullParserException("Expected " + paramString + " end tag at: " + paramXmlPullParser.getName());
  }

  public static final void a(Object paramObject, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramObject == null)
    {
      paramXmlSerializer.startTag(null, "null");
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    if ((paramObject instanceof String))
    {
      paramXmlSerializer.startTag(null, "string");
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.text(paramObject.toString());
      paramXmlSerializer.endTag(null, "string");
      return;
    }
    String str;
    if ((paramObject instanceof Integer))
      str = "int";
    while (true)
    {
      paramXmlSerializer.startTag(null, str);
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.attribute(null, "value", paramObject.toString());
      paramXmlSerializer.endTag(null, str);
      return;
      if ((paramObject instanceof Long))
      {
        str = "long";
      }
      else if ((paramObject instanceof Float))
      {
        str = "float";
      }
      else if ((paramObject instanceof Double))
      {
        str = "double";
      }
      else
      {
        if (!(paramObject instanceof Boolean))
          break;
        str = "boolean";
      }
    }
    if ((paramObject instanceof byte[]))
    {
      a((byte[])paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof int[]))
    {
      a((int[])paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof Map))
    {
      a((Map)paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof List))
    {
      a((List)paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof CharSequence))
    {
      paramXmlSerializer.startTag(null, "string");
      if (paramString != null)
        paramXmlSerializer.attribute(null, "name", paramString);
      paramXmlSerializer.text(paramObject.toString());
      paramXmlSerializer.endTag(null, "string");
      return;
    }
    throw new RuntimeException("writeValueXml: unable to write value " + paramObject);
  }

  public static final void a(List paramList, OutputStream paramOutputStream)
    throws XmlPullParserException, IOException
  {
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    localXmlSerializer.setOutput(paramOutputStream, "utf-8");
    localXmlSerializer.startDocument(null, Boolean.valueOf(true));
    localXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
    a(paramList, null, localXmlSerializer);
    localXmlSerializer.endDocument();
  }

  public static final void a(List paramList, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramList == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "list");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      a(paramList.get(i), null, paramXmlSerializer);
      i += 1;
    }
    paramXmlSerializer.endTag(null, "list");
  }

  public static final void a(Map paramMap, OutputStream paramOutputStream)
    throws XmlPullParserException, IOException
  {
    a locala = new a();
    locala.setOutput(paramOutputStream, "utf-8");
    locala.startDocument(null, Boolean.valueOf(true));
    locala.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
    a(paramMap, null, locala);
    locala.endDocument();
  }

  public static final void a(Map paramMap, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramMap == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramMap = paramMap.entrySet().iterator();
    paramXmlSerializer.startTag(null, "map");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    while (paramMap.hasNext())
    {
      paramString = (Map.Entry)paramMap.next();
      a(paramString.getValue(), (String)paramString.getKey(), paramXmlSerializer);
    }
    paramXmlSerializer.endTag(null, "map");
  }

  public static void a(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getDepth();
    int j;
    do
      j = paramXmlPullParser.next();
    while ((j != 1) && ((j != 3) || (paramXmlPullParser.getDepth() > i)));
  }

  public static final void a(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
    if (i != 2)
      throw new XmlPullParserException("No start tag found");
    if (!paramXmlPullParser.getName().equals(paramString))
      throw new XmlPullParserException("Unexpected start tag: found " + paramXmlPullParser.getName() + ", expected " + paramString);
  }

  public static final void a(byte[] paramArrayOfByte, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramArrayOfByte == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "byte-array");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    int k = paramArrayOfByte.length;
    paramXmlSerializer.attribute(null, "num", Integer.toString(k));
    paramString = new StringBuilder(paramArrayOfByte.length * 2);
    int i = 0;
    if (i < k)
    {
      int m = paramArrayOfByte[i];
      int j = m >> 4;
      if (j >= 10)
      {
        j = j + 97 - 10;
        label119: paramString.append(j);
        j = m & 0xFF;
        if (j < 10)
          break label175;
        j = j + 97 - 10;
      }
      while (true)
      {
        paramString.append(j);
        i += 1;
        break;
        j += 48;
        break label119;
        label175: j += 48;
      }
    }
    paramXmlSerializer.text(paramString.toString());
    paramXmlSerializer.endTag(null, "byte-array");
  }

  public static final void a(int[] paramArrayOfInt, String paramString, XmlSerializer paramXmlSerializer)
    throws XmlPullParserException, IOException
  {
    if (paramArrayOfInt == null)
    {
      paramXmlSerializer.startTag(null, "null");
      paramXmlSerializer.endTag(null, "null");
      return;
    }
    paramXmlSerializer.startTag(null, "int-array");
    if (paramString != null)
      paramXmlSerializer.attribute(null, "name", paramString);
    int j = paramArrayOfInt.length;
    paramXmlSerializer.attribute(null, "num", Integer.toString(j));
    int i = 0;
    while (i < j)
    {
      paramXmlSerializer.startTag(null, "item");
      paramXmlSerializer.attribute(null, "value", Integer.toString(paramArrayOfInt[i]));
      paramXmlSerializer.endTag(null, "item");
      i += 1;
    }
    paramXmlSerializer.endTag(null, "int-array");
  }

  public static final boolean a(CharSequence paramCharSequence, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramCharSequence == null)
      return paramBoolean;
    if ((!paramCharSequence.equals("1")) && (!paramCharSequence.equals("true")))
    {
      paramBoolean = bool;
      if (!paramCharSequence.equals("TRUE"));
    }
    else
    {
      paramBoolean = true;
    }
    return paramBoolean;
  }

  private static final Object b(XmlPullParser paramXmlPullParser, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    Object localObject = null;
    String str1 = paramXmlPullParser.getAttributeValue(null, "name");
    String str2 = paramXmlPullParser.getName();
    if (str2.equals("null"));
    int i;
    label229: label505: 
    do
    {
      while (true)
      {
        i = paramXmlPullParser.next();
        if (i == 1)
          break label649;
        if (i != 3)
          break label549;
        if (!paramXmlPullParser.getName().equals(str2))
          break label505;
        paramArrayOfString[0] = str1;
        return localObject;
        if (str2.equals("string"))
        {
          localObject = "";
          do
            while (true)
            {
              i = paramXmlPullParser.next();
              if (i == 1)
                break label229;
              if (i == 3)
              {
                if (paramXmlPullParser.getName().equals("string"))
                {
                  paramArrayOfString[0] = str1;
                  return localObject;
                }
                throw new XmlPullParserException("Unexpected end tag in <string>: " + paramXmlPullParser.getName());
              }
              if (i != 4)
                break;
              localObject = (String)localObject + paramXmlPullParser.getText();
            }
          while (i != 2);
          throw new XmlPullParserException("Unexpected start tag in <string>: " + paramXmlPullParser.getName());
          throw new XmlPullParserException("Unexpected end of document in <string>");
        }
        if (str2.equals("int"))
        {
          localObject = Integer.valueOf(Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "value")));
        }
        else if (str2.equals("long"))
        {
          localObject = Long.valueOf(paramXmlPullParser.getAttributeValue(null, "value"));
        }
        else if (str2.equals("float"))
        {
          localObject = new Float(paramXmlPullParser.getAttributeValue(null, "value"));
        }
        else if (str2.equals("double"))
        {
          localObject = new Double(paramXmlPullParser.getAttributeValue(null, "value"));
        }
        else
        {
          if (!str2.equals("boolean"))
            break;
          localObject = Boolean.valueOf(paramXmlPullParser.getAttributeValue(null, "value"));
        }
      }
      if (str2.equals("int-array"))
      {
        paramXmlPullParser.next();
        paramXmlPullParser = c(paramXmlPullParser, "int-array", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return paramXmlPullParser;
      }
      if (str2.equals("map"))
      {
        paramXmlPullParser.next();
        paramXmlPullParser = a(paramXmlPullParser, "map", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return paramXmlPullParser;
      }
      if (str2.equals("list"))
      {
        paramXmlPullParser.next();
        paramXmlPullParser = b(paramXmlPullParser, "list", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return paramXmlPullParser;
      }
      throw new XmlPullParserException("Unknown tag: " + str2);
      throw new XmlPullParserException("Unexpected end tag in <" + str2 + ">: " + paramXmlPullParser.getName());
      if (i == 4)
        throw new XmlPullParserException("Unexpected text in <" + str2 + ">: " + paramXmlPullParser.getName());
    }
    while (i != 2);
    label549: throw new XmlPullParserException("Unexpected start tag in <" + str2 + ">: " + paramXmlPullParser.getName());
    label649: throw new XmlPullParserException("Unexpected end of document in <" + str2 + ">");
  }

  public static final ArrayList b(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (ArrayList)a(localXmlPullParser, new String[1]);
  }

  public static final ArrayList b(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
      localArrayList.add(b(paramXmlPullParser, paramArrayOfString));
    while (i != 3)
    {
      int j = paramXmlPullParser.next();
      i = j;
      if (j != 1)
        break;
      throw new XmlPullParserException("Document ended before " + paramString + " end tag");
    }
    if (paramXmlPullParser.getName().equals(paramString))
      return localArrayList;
    throw new XmlPullParserException("Expected " + paramString + " end tag at: " + paramXmlPullParser.getName());
  }

  public static final void b(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
  }

  // ERROR //
  public static final int[] c(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: ldc_w 285
    //   5: invokeinterface 308 3 0
    //   10: invokestatic 319	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   13: istore_3
    //   14: iload_3
    //   15: newarray int
    //   17: astore_2
    //   18: aload_0
    //   19: invokeinterface 63 1 0
    //   24: istore_3
    //   25: iconst_0
    //   26: istore 4
    //   28: iload_3
    //   29: iconst_2
    //   30: if_icmpne +172 -> 202
    //   33: aload_0
    //   34: invokeinterface 78 1 0
    //   39: ldc_w 297
    //   42: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   45: ifeq +124 -> 169
    //   48: aload_2
    //   49: iload 4
    //   51: aload_0
    //   52: aconst_null
    //   53: ldc 156
    //   55: invokeinterface 308 3 0
    //   60: invokestatic 319	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   63: iastore
    //   64: iload 4
    //   66: istore 5
    //   68: aload_0
    //   69: invokeinterface 90 1 0
    //   74: istore 6
    //   76: iload 5
    //   78: istore 4
    //   80: iload 6
    //   82: istore_3
    //   83: iload 6
    //   85: iconst_1
    //   86: if_icmpne -58 -> 28
    //   89: new 54	org/xmlpull/v1/XmlPullParserException
    //   92: dup
    //   93: new 68	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 69	java/lang/StringBuilder:<init>	()V
    //   100: ldc 118
    //   102: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload_1
    //   106: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: ldc 120
    //   111: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   120: athrow
    //   121: astore_0
    //   122: new 54	org/xmlpull/v1/XmlPullParserException
    //   125: dup
    //   126: ldc_w 366
    //   129: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   132: athrow
    //   133: astore_0
    //   134: new 54	org/xmlpull/v1/XmlPullParserException
    //   137: dup
    //   138: ldc_w 368
    //   141: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   144: athrow
    //   145: astore_0
    //   146: new 54	org/xmlpull/v1/XmlPullParserException
    //   149: dup
    //   150: ldc_w 370
    //   153: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   156: athrow
    //   157: astore_0
    //   158: new 54	org/xmlpull/v1/XmlPullParserException
    //   161: dup
    //   162: ldc_w 372
    //   165: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   168: athrow
    //   169: new 54	org/xmlpull/v1/XmlPullParserException
    //   172: dup
    //   173: new 68	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 69	java/lang/StringBuilder:<init>	()V
    //   180: ldc_w 374
    //   183: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_0
    //   187: invokeinterface 78 1 0
    //   192: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   201: athrow
    //   202: iload 4
    //   204: istore 5
    //   206: iload_3
    //   207: iconst_3
    //   208: if_icmpne -140 -> 68
    //   211: aload_0
    //   212: invokeinterface 78 1 0
    //   217: aload_1
    //   218: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifeq +5 -> 226
    //   224: aload_2
    //   225: areturn
    //   226: aload_0
    //   227: invokeinterface 78 1 0
    //   232: ldc_w 297
    //   235: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   238: ifeq +12 -> 250
    //   241: iload 4
    //   243: iconst_1
    //   244: iadd
    //   245: istore 5
    //   247: goto -179 -> 68
    //   250: new 54	org/xmlpull/v1/XmlPullParserException
    //   253: dup
    //   254: new 68	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 69	java/lang/StringBuilder:<init>	()V
    //   261: ldc 125
    //   263: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: aload_1
    //   267: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: ldc 127
    //   272: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload_0
    //   276: invokeinterface 78 1 0
    //   281: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   287: invokespecial 82	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   290: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	14	121	java/lang/NullPointerException
    //   0	14	133	java/lang/NumberFormatException
    //   48	64	145	java/lang/NullPointerException
    //   48	64	157	java/lang/NumberFormatException
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.utdid.b.a.e
 * JD-Core Version:    0.6.2
 */