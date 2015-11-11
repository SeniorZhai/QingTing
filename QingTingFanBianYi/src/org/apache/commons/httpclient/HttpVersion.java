package org.apache.commons.httpclient;

public class HttpVersion
  implements Comparable
{
  public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);
  public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
  public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);
  private int major = 0;
  private int minor = 0;

  public HttpVersion(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException("HTTP major version number may not be negative");
    this.major = paramInt1;
    if (paramInt2 < 0)
      throw new IllegalArgumentException("HTTP minor version number may not be negative");
    this.minor = paramInt2;
  }

  // ERROR //
  public static HttpVersion parse(String paramString)
    throws ProtocolException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 34	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 49
    //   10: invokespecial 39	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: ldc 51
    //   17: invokevirtual 57	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   20: ifne +30 -> 50
    //   23: new 45	org/apache/commons/httpclient/ProtocolException
    //   26: dup
    //   27: new 59	java/lang/StringBuffer
    //   30: dup
    //   31: invokespecial 60	java/lang/StringBuffer:<init>	()V
    //   34: ldc 62
    //   36: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   39: aload_0
    //   40: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   43: invokevirtual 70	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   46: invokespecial 71	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: ldc 51
    //   52: invokevirtual 75	java/lang/String:length	()I
    //   55: istore_3
    //   56: aload_0
    //   57: ldc 77
    //   59: iload_3
    //   60: invokevirtual 81	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   63: istore_2
    //   64: iload_2
    //   65: iconst_m1
    //   66: if_icmpne +30 -> 96
    //   69: new 45	org/apache/commons/httpclient/ProtocolException
    //   72: dup
    //   73: new 59	java/lang/StringBuffer
    //   76: dup
    //   77: invokespecial 60	java/lang/StringBuffer:<init>	()V
    //   80: ldc 83
    //   82: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   85: aload_0
    //   86: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   89: invokevirtual 70	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   92: invokespecial 71	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   95: athrow
    //   96: aload_0
    //   97: iload_3
    //   98: iload_2
    //   99: invokevirtual 87	java/lang/String:substring	(II)Ljava/lang/String;
    //   102: invokestatic 93	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   105: istore_3
    //   106: aload_0
    //   107: invokevirtual 75	java/lang/String:length	()I
    //   110: istore 4
    //   112: aload_0
    //   113: iload_2
    //   114: iconst_1
    //   115: iadd
    //   116: iload 4
    //   118: invokevirtual 87	java/lang/String:substring	(II)Ljava/lang/String;
    //   121: invokestatic 93	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   124: istore_2
    //   125: new 2	org/apache/commons/httpclient/HttpVersion
    //   128: dup
    //   129: iload_3
    //   130: iload_2
    //   131: invokespecial 19	org/apache/commons/httpclient/HttpVersion:<init>	(II)V
    //   134: areturn
    //   135: astore_1
    //   136: new 45	org/apache/commons/httpclient/ProtocolException
    //   139: dup
    //   140: new 59	java/lang/StringBuffer
    //   143: dup
    //   144: invokespecial 60	java/lang/StringBuffer:<init>	()V
    //   147: ldc 95
    //   149: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   152: aload_0
    //   153: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   156: invokevirtual 70	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   159: invokespecial 71	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   162: athrow
    //   163: astore_1
    //   164: new 45	org/apache/commons/httpclient/ProtocolException
    //   167: dup
    //   168: new 59	java/lang/StringBuffer
    //   171: dup
    //   172: invokespecial 60	java/lang/StringBuffer:<init>	()V
    //   175: ldc 97
    //   177: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   180: aload_0
    //   181: invokevirtual 66	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   184: invokevirtual 70	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   187: invokespecial 71	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   96	106	135	java/lang/NumberFormatException
    //   112	125	163	java/lang/NumberFormatException
  }

  public int compareTo(Object paramObject)
  {
    return compareTo((HttpVersion)paramObject);
  }

  public int compareTo(HttpVersion paramHttpVersion)
  {
    if (paramHttpVersion == null)
      throw new IllegalArgumentException("Version parameter may not be null");
    int j = getMajor() - paramHttpVersion.getMajor();
    int i = j;
    if (j == 0)
      i = getMinor() - paramHttpVersion.getMinor();
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof HttpVersion))
      return false;
    return equals((HttpVersion)paramObject);
  }

  public boolean equals(HttpVersion paramHttpVersion)
  {
    return compareTo(paramHttpVersion) == 0;
  }

  public int getMajor()
  {
    return this.major;
  }

  public int getMinor()
  {
    return this.minor;
  }

  public boolean greaterEquals(HttpVersion paramHttpVersion)
  {
    return compareTo(paramHttpVersion) >= 0;
  }

  public int hashCode()
  {
    return this.major * 100000 + this.minor;
  }

  public boolean lessEquals(HttpVersion paramHttpVersion)
  {
    return compareTo(paramHttpVersion) <= 0;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("HTTP/");
    localStringBuffer.append(this.major);
    localStringBuffer.append('.');
    localStringBuffer.append(this.minor);
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpVersion
 * JD-Core Version:    0.6.2
 */