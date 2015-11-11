package com.ta.utdid2.core.persistent;

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

class XmlUtils
{
  public static final void beginDocument(XmlPullParser paramXmlPullParser, String paramString)
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

  public static final boolean convertValueToBoolean(CharSequence paramCharSequence, boolean paramBoolean)
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

  public static final int convertValueToInt(CharSequence paramCharSequence, int paramInt)
  {
    if (paramCharSequence == null)
      return paramInt;
    paramCharSequence = paramCharSequence.toString();
    int k = 1;
    int j = 0;
    int i = paramCharSequence.length();
    paramInt = 10;
    if ('-' == paramCharSequence.charAt(0))
    {
      k = -1;
      j = 0 + 1;
    }
    if ('0' == paramCharSequence.charAt(j))
    {
      if (j == i - 1)
        return 0;
      paramInt = paramCharSequence.charAt(j + 1);
      if ((120 == paramInt) || (88 == paramInt))
      {
        i = j + 2;
        paramInt = 16;
      }
    }
    while (true)
    {
      return Integer.parseInt(paramCharSequence.substring(i), paramInt) * k;
      i = j + 1;
      paramInt = 8;
      continue;
      i = j;
      if ('#' == paramCharSequence.charAt(j))
      {
        i = j + 1;
        paramInt = 16;
      }
    }
  }

  public static final int convertValueToList(CharSequence paramCharSequence, String[] paramArrayOfString, int paramInt)
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

  public static final int convertValueToUnsignedInt(String paramString, int paramInt)
  {
    if (paramString == null)
      return paramInt;
    return parseUnsignedIntAttribute(paramString);
  }

  public static final void nextElement(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i;
    do
      i = paramXmlPullParser.next();
    while ((i != 2) && (i != 1));
  }

  public static final int parseUnsignedIntAttribute(CharSequence paramCharSequence)
  {
    paramCharSequence = paramCharSequence.toString();
    int j = 0;
    int k = paramCharSequence.length();
    int i = 10;
    if ('0' == paramCharSequence.charAt(0))
    {
      if (k - 1 == 0)
        return 0;
      i = paramCharSequence.charAt(1);
      if ((120 == i) || (88 == i))
      {
        j = 0 + 2;
        i = 16;
      }
    }
    while (true)
    {
      return (int)Long.parseLong(paramCharSequence.substring(j), i);
      j = 0 + 1;
      i = 8;
      continue;
      if ('#' == paramCharSequence.charAt(0))
      {
        j = 0 + 1;
        i = 16;
      }
    }
  }

  public static final ArrayList readListXml(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (ArrayList)readValueXml(localXmlPullParser, new String[1]);
  }

  public static final HashMap readMapXml(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    return (HashMap)readValueXml(localXmlPullParser, new String[1]);
  }

  // ERROR //
  public static final int[] readThisIntArrayXml(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: ldc 125
    //   4: invokeinterface 129 3 0
    //   9: invokestatic 132	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   12: istore_3
    //   13: iload_3
    //   14: newarray int
    //   16: astore_2
    //   17: iconst_0
    //   18: istore_3
    //   19: aload_0
    //   20: invokeinterface 135 1 0
    //   25: istore 4
    //   27: iload 4
    //   29: iconst_2
    //   30: if_icmpne +164 -> 194
    //   33: aload_0
    //   34: invokeinterface 30 1 0
    //   39: ldc 137
    //   41: invokevirtual 36	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: ifeq +118 -> 162
    //   47: aload_2
    //   48: iload_3
    //   49: aload_0
    //   50: aconst_null
    //   51: ldc 139
    //   53: invokeinterface 129 3 0
    //   58: invokestatic 132	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   61: iastore
    //   62: iload_3
    //   63: istore 5
    //   65: aload_0
    //   66: invokeinterface 21 1 0
    //   71: istore 6
    //   73: iload 6
    //   75: istore 4
    //   77: iload 5
    //   79: istore_3
    //   80: iload 6
    //   82: iconst_1
    //   83: if_icmpne -56 -> 27
    //   86: new 13	org/xmlpull/v1/XmlPullParserException
    //   89: dup
    //   90: new 38	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   97: ldc 141
    //   99: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: aload_1
    //   103: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: ldc 143
    //   108: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   117: athrow
    //   118: astore_0
    //   119: new 13	org/xmlpull/v1/XmlPullParserException
    //   122: dup
    //   123: ldc 145
    //   125: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   128: athrow
    //   129: astore_0
    //   130: new 13	org/xmlpull/v1/XmlPullParserException
    //   133: dup
    //   134: ldc 147
    //   136: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   139: athrow
    //   140: astore_0
    //   141: new 13	org/xmlpull/v1/XmlPullParserException
    //   144: dup
    //   145: ldc 149
    //   147: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   150: athrow
    //   151: astore_0
    //   152: new 13	org/xmlpull/v1/XmlPullParserException
    //   155: dup
    //   156: ldc 151
    //   158: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   161: athrow
    //   162: new 13	org/xmlpull/v1/XmlPullParserException
    //   165: dup
    //   166: new 38	java/lang/StringBuilder
    //   169: dup
    //   170: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   173: ldc 153
    //   175: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: aload_0
    //   179: invokeinterface 30 1 0
    //   184: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   193: athrow
    //   194: iload_3
    //   195: istore 5
    //   197: iload 4
    //   199: iconst_3
    //   200: if_icmpne -135 -> 65
    //   203: aload_0
    //   204: invokeinterface 30 1 0
    //   209: aload_1
    //   210: invokevirtual 36	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   213: ifeq +5 -> 218
    //   216: aload_2
    //   217: areturn
    //   218: aload_0
    //   219: invokeinterface 30 1 0
    //   224: ldc 137
    //   226: invokevirtual 36	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   229: ifeq +11 -> 240
    //   232: iload_3
    //   233: iconst_1
    //   234: iadd
    //   235: istore 5
    //   237: goto -172 -> 65
    //   240: new 13	org/xmlpull/v1/XmlPullParserException
    //   243: dup
    //   244: new 38	java/lang/StringBuilder
    //   247: dup
    //   248: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   251: ldc 155
    //   253: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: aload_1
    //   257: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: ldc 157
    //   262: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: aload_0
    //   266: invokeinterface 30 1 0
    //   271: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   277: invokespecial 26	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   280: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	13	118	java/lang/NullPointerException
    //   0	13	129	java/lang/NumberFormatException
    //   47	62	140	java/lang/NullPointerException
    //   47	62	151	java/lang/NumberFormatException
  }

  public static final ArrayList readThisListXml(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
      localArrayList.add(readThisValueXml(paramXmlPullParser, paramArrayOfString));
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

  public static final HashMap readThisMapXml(XmlPullParser paramXmlPullParser, String paramString, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    HashMap localHashMap = new HashMap();
    int i = paramXmlPullParser.getEventType();
    if (i == 2)
    {
      localObject = readThisValueXml(paramXmlPullParser, paramArrayOfString);
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

  private static final Object readThisValueXml(XmlPullParser paramXmlPullParser, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    String str1 = paramXmlPullParser.getAttributeValue(null, "name");
    String str2 = paramXmlPullParser.getName();
    Object localObject;
    if (str2.equals("null"))
      localObject = null;
    int i;
    label226: label498: 
    do
    {
      while (true)
      {
        i = paramXmlPullParser.next();
        if (i == 1)
          break label636;
        if (i != 3)
          break label540;
        if (!paramXmlPullParser.getName().equals(str2))
          break label498;
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
                break label226;
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
        paramXmlPullParser = readThisIntArrayXml(paramXmlPullParser, "int-array", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return paramXmlPullParser;
      }
      if (str2.equals("map"))
      {
        paramXmlPullParser.next();
        paramXmlPullParser = readThisMapXml(paramXmlPullParser, "map", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return paramXmlPullParser;
      }
      if (str2.equals("list"))
      {
        paramXmlPullParser.next();
        paramXmlPullParser = readThisListXml(paramXmlPullParser, "list", paramArrayOfString);
        paramArrayOfString[0] = str1;
        return paramXmlPullParser;
      }
      throw new XmlPullParserException("Unknown tag: " + str2);
      throw new XmlPullParserException("Unexpected end tag in <" + str2 + ">: " + paramXmlPullParser.getName());
      if (i == 4)
        throw new XmlPullParserException("Unexpected text in <" + str2 + ">: " + paramXmlPullParser.getName());
    }
    while (i != 2);
    label540: throw new XmlPullParserException("Unexpected start tag in <" + str2 + ">: " + paramXmlPullParser.getName());
    label636: throw new XmlPullParserException("Unexpected end of document in <" + str2 + ">");
  }

  public static final Object readValueXml(XmlPullParser paramXmlPullParser, String[] paramArrayOfString)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getEventType();
    while (true)
    {
      if (i == 2)
        return readThisValueXml(paramXmlPullParser, paramArrayOfString);
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

  public static void skipCurrentTag(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getDepth();
    int j;
    do
      j = paramXmlPullParser.next();
    while ((j != 1) && ((j != 3) || (paramXmlPullParser.getDepth() > i)));
  }

  public static final void writeByteArrayXml(byte[] paramArrayOfByte, String paramString, XmlSerializer paramXmlSerializer)
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
        label118: paramString.append(j);
        j = m & 0xFF;
        if (j < 10)
          break label174;
        j = j + 97 - 10;
      }
      while (true)
      {
        paramString.append(j);
        i += 1;
        break;
        j += 48;
        break label118;
        label174: j += 48;
      }
    }
    paramXmlSerializer.text(paramString.toString());
    paramXmlSerializer.endTag(null, "byte-array");
  }

  public static final void writeIntArrayXml(int[] paramArrayOfInt, String paramString, XmlSerializer paramXmlSerializer)
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

  public static final void writeListXml(List paramList, OutputStream paramOutputStream)
    throws XmlPullParserException, IOException
  {
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    localXmlSerializer.setOutput(paramOutputStream, "utf-8");
    localXmlSerializer.startDocument(null, Boolean.valueOf(true));
    localXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
    writeListXml(paramList, null, localXmlSerializer);
    localXmlSerializer.endDocument();
  }

  public static final void writeListXml(List paramList, String paramString, XmlSerializer paramXmlSerializer)
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
      writeValueXml(paramList.get(i), null, paramXmlSerializer);
      i += 1;
    }
    paramXmlSerializer.endTag(null, "list");
  }

  public static final void writeMapXml(Map paramMap, OutputStream paramOutputStream)
    throws XmlPullParserException, IOException
  {
    FastXmlSerializer localFastXmlSerializer = new FastXmlSerializer();
    localFastXmlSerializer.setOutput(paramOutputStream, "utf-8");
    localFastXmlSerializer.startDocument(null, Boolean.valueOf(true));
    localFastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
    writeMapXml(paramMap, null, localFastXmlSerializer);
    localFastXmlSerializer.endDocument();
  }

  public static final void writeMapXml(Map paramMap, String paramString, XmlSerializer paramXmlSerializer)
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
      writeValueXml(paramString.getValue(), (String)paramString.getKey(), paramXmlSerializer);
    }
    paramXmlSerializer.endTag(null, "map");
  }

  public static final void writeValueXml(Object paramObject, String paramString, XmlSerializer paramXmlSerializer)
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
      writeByteArrayXml((byte[])paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof int[]))
    {
      writeIntArrayXml((int[])paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof Map))
    {
      writeMapXml((Map)paramObject, paramString, paramXmlSerializer);
      return;
    }
    if ((paramObject instanceof List))
    {
      writeListXml((List)paramObject, paramString, paramXmlSerializer);
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.XmlUtils
 * JD-Core Version:    0.6.2
 */