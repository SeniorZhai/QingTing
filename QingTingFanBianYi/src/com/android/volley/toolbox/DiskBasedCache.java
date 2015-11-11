package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.VolleyLog;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DiskBasedCache
  implements Cache
{
  private static final int CACHE_MAGIC = 538051844;
  private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
  private static final float HYSTERESIS_FACTOR = 0.9F;
  private final Map<String, CacheHeader> mEntries = new LinkedHashMap(16, 0.75F, true);
  private final int mMaxCacheSizeInBytes;
  private final File mRootDirectory;
  private long mTotalSize = 0L;

  public DiskBasedCache(File paramFile)
  {
    this(paramFile, 5242880);
  }

  public DiskBasedCache(File paramFile, int paramInt)
  {
    this.mRootDirectory = paramFile;
    this.mMaxCacheSizeInBytes = paramInt;
  }

  private String getFilenameForKey(String paramString)
  {
    int i = paramString.length() / 2;
    return String.valueOf(paramString.substring(0, i).hashCode()) + String.valueOf(paramString.substring(i).hashCode());
  }

  private void pruneIfNeeded(int paramInt)
  {
    if (this.mTotalSize + paramInt < this.mMaxCacheSizeInBytes)
      return;
    if (VolleyLog.DEBUG)
      VolleyLog.v("Pruning old cache entries.", new Object[0]);
    long l1 = this.mTotalSize;
    int i = 0;
    long l2 = SystemClock.elapsedRealtime();
    Iterator localIterator = this.mEntries.entrySet().iterator();
    label61: label70: CacheHeader localCacheHeader;
    if (!localIterator.hasNext())
    {
      if (VolleyLog.DEBUG)
        VolleyLog.v("pruned %d files, %d bytes, %d ms", new Object[] { Integer.valueOf(i), Long.valueOf(this.mTotalSize - l1), Long.valueOf(SystemClock.elapsedRealtime() - l2) });
    }
    else
    {
      localCacheHeader = (CacheHeader)((Map.Entry)localIterator.next()).getValue();
      if (!getFileForKey(localCacheHeader.key).delete())
        break label207;
      this.mTotalSize -= localCacheHeader.size;
    }
    while (true)
    {
      localIterator.remove();
      int j = i + 1;
      i = j;
      if ((float)(this.mTotalSize + paramInt) >= this.mMaxCacheSizeInBytes * 0.9F)
        break label61;
      i = j;
      break label70;
      break;
      label207: VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { localCacheHeader.key, getFilenameForKey(localCacheHeader.key) });
    }
  }

  private void putEntry(String paramString, CacheHeader paramCacheHeader)
  {
    if (!this.mEntries.containsKey(paramString));
    CacheHeader localCacheHeader;
    for (this.mTotalSize += paramCacheHeader.size; ; this.mTotalSize += paramCacheHeader.size - localCacheHeader.size)
    {
      this.mEntries.put(paramString, paramCacheHeader);
      return;
      localCacheHeader = (CacheHeader)this.mEntries.get(paramString);
    }
  }

  private static int read(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1)
      throw new EOFException();
    return i;
  }

  static int readInt(InputStream paramInputStream)
    throws IOException
  {
    return 0x0 | read(paramInputStream) << 0 | read(paramInputStream) << 8 | read(paramInputStream) << 16 | read(paramInputStream) << 24;
  }

  static long readLong(InputStream paramInputStream)
    throws IOException
  {
    return 0L | (read(paramInputStream) & 0xFF) << 0 | (read(paramInputStream) & 0xFF) << 8 | (read(paramInputStream) & 0xFF) << 16 | (read(paramInputStream) & 0xFF) << 24 | (read(paramInputStream) & 0xFF) << 32 | (read(paramInputStream) & 0xFF) << 40 | (read(paramInputStream) & 0xFF) << 48 | (read(paramInputStream) & 0xFF) << 56;
  }

  static String readString(InputStream paramInputStream)
    throws IOException
  {
    return new String(streamToBytes(paramInputStream, (int)readLong(paramInputStream)), "UTF-8");
  }

  static Map<String, String> readStringStringMap(InputStream paramInputStream)
    throws IOException
  {
    int j = readInt(paramInputStream);
    Object localObject;
    int i;
    if (j == 0)
    {
      localObject = Collections.emptyMap();
      i = 0;
    }
    while (true)
    {
      if (i >= j)
      {
        return localObject;
        localObject = new HashMap(j);
        break;
      }
      ((Map)localObject).put(readString(paramInputStream).intern(), readString(paramInputStream).intern());
      i += 1;
    }
  }

  private void removeEntry(String paramString)
  {
    CacheHeader localCacheHeader = (CacheHeader)this.mEntries.get(paramString);
    if (localCacheHeader != null)
    {
      this.mTotalSize -= localCacheHeader.size;
      this.mEntries.remove(paramString);
    }
  }

  private static byte[] streamToBytes(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte;
    while (true)
    {
      int i;
      int j;
      try
      {
        arrayOfByte = new byte[paramInt];
        i = 0;
        if (i < paramInt)
        {
          j = paramInputStream.read(arrayOfByte, i, paramInt - i);
          if (j != -1);
        }
        else
        {
          if (i == paramInt)
            break;
          throw new IOException("Expected " + paramInt + " bytes, read " + i + " bytes");
        }
      }
      catch (OutOfMemoryError paramInputStream)
      {
        throw new IOException();
      }
      i += j;
    }
    return arrayOfByte;
  }

  static void writeInt(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(paramInt >> 0 & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >> 24 & 0xFF);
  }

  static void writeLong(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((byte)(int)(paramLong >>> 0));
    paramOutputStream.write((byte)(int)(paramLong >>> 8));
    paramOutputStream.write((byte)(int)(paramLong >>> 16));
    paramOutputStream.write((byte)(int)(paramLong >>> 24));
    paramOutputStream.write((byte)(int)(paramLong >>> 32));
    paramOutputStream.write((byte)(int)(paramLong >>> 40));
    paramOutputStream.write((byte)(int)(paramLong >>> 48));
    paramOutputStream.write((byte)(int)(paramLong >>> 56));
  }

  static void writeString(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    writeLong(paramOutputStream, paramString.length);
    paramOutputStream.write(paramString, 0, paramString.length);
  }

  static void writeStringStringMap(Map<String, String> paramMap, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramMap != null)
    {
      writeInt(paramOutputStream, paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      while (true)
      {
        if (!paramMap.hasNext())
          return;
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        writeString(paramOutputStream, (String)localEntry.getKey());
        writeString(paramOutputStream, (String)localEntry.getValue());
      }
    }
    writeInt(paramOutputStream, 0);
  }

  public void clear()
  {
    int i = 0;
    while (true)
    {
      int j;
      try
      {
        File[] arrayOfFile = this.mRootDirectory.listFiles();
        if (arrayOfFile != null)
        {
          j = arrayOfFile.length;
        }
        else
        {
          this.mEntries.clear();
          this.mTotalSize = 0L;
          VolleyLog.d("Cache cleared.", new Object[0]);
          return;
          arrayOfFile[i].delete();
          i += 1;
        }
      }
      finally
      {
      }
      if (i < j);
    }
  }

  // ERROR //
  public Cache.Entry get(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 45	com/android/volley/toolbox/DiskBasedCache:mEntries	Ljava/util/Map;
    //   9: aload_1
    //   10: invokeinterface 187 2 0
    //   15: checkcast 8	com/android/volley/toolbox/DiskBasedCache$CacheHeader
    //   18: astore 4
    //   20: aload 4
    //   22: ifnonnull +10 -> 32
    //   25: aload 5
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: aload_0
    //   33: aload_1
    //   34: invokevirtual 155	com/android/volley/toolbox/DiskBasedCache:getFileForKey	(Ljava/lang/String;)Ljava/io/File;
    //   37: astore 7
    //   39: aconst_null
    //   40: astore_2
    //   41: aconst_null
    //   42: astore 6
    //   44: new 11	com/android/volley/toolbox/DiskBasedCache$CountingInputStream
    //   47: dup
    //   48: new 303	java/io/FileInputStream
    //   51: dup
    //   52: aload 7
    //   54: invokespecial 305	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   57: aconst_null
    //   58: invokespecial 308	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:<init>	(Ljava/io/InputStream;Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)V
    //   61: astore_3
    //   62: aload_3
    //   63: invokestatic 312	com/android/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
    //   66: pop
    //   67: aload 4
    //   69: aload_3
    //   70: aload 7
    //   72: invokevirtual 314	java/io/File:length	()J
    //   75: aload_3
    //   76: invokestatic 318	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:access$1	(Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)I
    //   79: i2l
    //   80: lsub
    //   81: l2i
    //   82: invokestatic 214	com/android/volley/toolbox/DiskBasedCache:streamToBytes	(Ljava/io/InputStream;I)[B
    //   85: invokevirtual 322	com/android/volley/toolbox/DiskBasedCache$CacheHeader:toCacheEntry	([B)Lcom/android/volley/Cache$Entry;
    //   88: astore_2
    //   89: aload_3
    //   90: ifnull +7 -> 97
    //   93: aload_3
    //   94: invokevirtual 325	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   97: aload_2
    //   98: astore_1
    //   99: goto -71 -> 28
    //   102: astore_1
    //   103: aload 5
    //   105: astore_1
    //   106: goto -78 -> 28
    //   109: astore 4
    //   111: aload 6
    //   113: astore_3
    //   114: aload_3
    //   115: astore_2
    //   116: ldc_w 327
    //   119: iconst_2
    //   120: anewarray 4	java/lang/Object
    //   123: dup
    //   124: iconst_0
    //   125: aload 7
    //   127: invokevirtual 330	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   130: aastore
    //   131: dup
    //   132: iconst_1
    //   133: aload 4
    //   135: invokevirtual 331	java/io/IOException:toString	()Ljava/lang/String;
    //   138: aastore
    //   139: invokestatic 173	com/android/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   142: aload_3
    //   143: astore_2
    //   144: aload_0
    //   145: aload_1
    //   146: invokevirtual 333	com/android/volley/toolbox/DiskBasedCache:remove	(Ljava/lang/String;)V
    //   149: aload 5
    //   151: astore_1
    //   152: aload_3
    //   153: ifnull -125 -> 28
    //   156: aload_3
    //   157: invokevirtual 325	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   160: aload 5
    //   162: astore_1
    //   163: goto -135 -> 28
    //   166: astore_1
    //   167: aload 5
    //   169: astore_1
    //   170: goto -142 -> 28
    //   173: astore_1
    //   174: aload_2
    //   175: ifnull +7 -> 182
    //   178: aload_2
    //   179: invokevirtual 325	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   182: aload_1
    //   183: athrow
    //   184: astore_1
    //   185: aload_0
    //   186: monitorexit
    //   187: aload_1
    //   188: athrow
    //   189: astore_1
    //   190: aload 5
    //   192: astore_1
    //   193: goto -165 -> 28
    //   196: astore_1
    //   197: aload_3
    //   198: astore_2
    //   199: goto -25 -> 174
    //   202: astore 4
    //   204: goto -90 -> 114
    //
    // Exception table:
    //   from	to	target	type
    //   93	97	102	java/io/IOException
    //   44	62	109	java/io/IOException
    //   156	160	166	java/io/IOException
    //   44	62	173	finally
    //   116	142	173	finally
    //   144	149	173	finally
    //   5	20	184	finally
    //   32	39	184	finally
    //   93	97	184	finally
    //   156	160	184	finally
    //   178	182	184	finally
    //   182	184	184	finally
    //   178	182	189	java/io/IOException
    //   62	89	196	finally
    //   62	89	202	java/io/IOException
  }

  public File getFileForKey(String paramString)
  {
    return new File(this.mRootDirectory, getFilenameForKey(paramString));
  }

  // ERROR //
  public void initialize()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 6
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 49	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   9: invokevirtual 340	java/io/File:exists	()Z
    //   12: ifne +36 -> 48
    //   15: aload_0
    //   16: getfield 49	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   19: invokevirtual 343	java/io/File:mkdirs	()Z
    //   22: ifne +23 -> 45
    //   25: ldc_w 345
    //   28: iconst_1
    //   29: anewarray 4	java/lang/Object
    //   32: dup
    //   33: iconst_0
    //   34: aload_0
    //   35: getfield 49	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   38: invokevirtual 330	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   41: aastore
    //   42: invokestatic 348	com/android/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: aload_0
    //   49: getfield 49	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   52: invokevirtual 296	java/io/File:listFiles	()[Ljava/io/File;
    //   55: astore 4
    //   57: aload 4
    //   59: ifnull -14 -> 45
    //   62: aload 4
    //   64: arraylength
    //   65: istore 7
    //   67: iload 6
    //   69: iload 7
    //   71: if_icmpge -26 -> 45
    //   74: aload 4
    //   76: iload 6
    //   78: aaload
    //   79: astore 5
    //   81: aconst_null
    //   82: astore_1
    //   83: aconst_null
    //   84: astore_3
    //   85: new 303	java/io/FileInputStream
    //   88: dup
    //   89: aload 5
    //   91: invokespecial 305	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   94: astore_2
    //   95: aload_2
    //   96: invokestatic 312	com/android/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
    //   99: astore_1
    //   100: aload_1
    //   101: aload 5
    //   103: invokevirtual 314	java/io/File:length	()J
    //   106: putfield 163	com/android/volley/toolbox/DiskBasedCache$CacheHeader:size	J
    //   109: aload_0
    //   110: aload_1
    //   111: getfield 151	com/android/volley/toolbox/DiskBasedCache$CacheHeader:key	Ljava/lang/String;
    //   114: aload_1
    //   115: invokespecial 350	com/android/volley/toolbox/DiskBasedCache:putEntry	(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
    //   118: aload_2
    //   119: ifnull +83 -> 202
    //   122: aload_2
    //   123: invokevirtual 351	java/io/FileInputStream:close	()V
    //   126: iload 6
    //   128: iconst_1
    //   129: iadd
    //   130: istore 6
    //   132: goto -65 -> 67
    //   135: astore_1
    //   136: aload_3
    //   137: astore_2
    //   138: aload 5
    //   140: ifnull +11 -> 151
    //   143: aload_2
    //   144: astore_1
    //   145: aload 5
    //   147: invokevirtual 160	java/io/File:delete	()Z
    //   150: pop
    //   151: aload_2
    //   152: ifnull -26 -> 126
    //   155: aload_2
    //   156: invokevirtual 351	java/io/FileInputStream:close	()V
    //   159: goto -33 -> 126
    //   162: astore_1
    //   163: goto -37 -> 126
    //   166: astore_2
    //   167: aload_1
    //   168: ifnull +7 -> 175
    //   171: aload_1
    //   172: invokevirtual 351	java/io/FileInputStream:close	()V
    //   175: aload_2
    //   176: athrow
    //   177: astore_1
    //   178: aload_0
    //   179: monitorexit
    //   180: aload_1
    //   181: athrow
    //   182: astore_1
    //   183: goto -57 -> 126
    //   186: astore_1
    //   187: goto -12 -> 175
    //   190: astore_3
    //   191: aload_2
    //   192: astore_1
    //   193: aload_3
    //   194: astore_2
    //   195: goto -28 -> 167
    //   198: astore_1
    //   199: goto -61 -> 138
    //   202: goto -76 -> 126
    //
    // Exception table:
    //   from	to	target	type
    //   85	95	135	java/io/IOException
    //   155	159	162	java/io/IOException
    //   85	95	166	finally
    //   145	151	166	finally
    //   5	45	177	finally
    //   48	57	177	finally
    //   62	67	177	finally
    //   122	126	177	finally
    //   155	159	177	finally
    //   171	175	177	finally
    //   175	177	177	finally
    //   122	126	182	java/io/IOException
    //   171	175	186	java/io/IOException
    //   95	118	190	finally
    //   95	118	198	java/io/IOException
  }

  public void invalidate(String paramString, boolean paramBoolean)
  {
    try
    {
      Cache.Entry localEntry = get(paramString);
      if (localEntry != null)
      {
        localEntry.softTtl = 0L;
        if (paramBoolean)
          localEntry.ttl = 0L;
        put(paramString, localEntry);
      }
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public void put(String paramString, Cache.Entry paramEntry)
  {
    try
    {
      pruneIfNeeded(paramEntry.data.length);
      File localFile = getFileForKey(paramString);
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
        CacheHeader localCacheHeader = new CacheHeader(paramString, paramEntry);
        localCacheHeader.writeHeader(localFileOutputStream);
        localFileOutputStream.write(paramEntry.data);
        localFileOutputStream.close();
        putEntry(paramString, localCacheHeader);
        return;
      }
      catch (IOException paramString)
      {
        while (true)
          if (!localFile.delete())
            VolleyLog.d("Could not clean up file %s", new Object[] { localFile.getAbsolutePath() });
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public void remove(String paramString)
  {
    try
    {
      boolean bool = getFileForKey(paramString).delete();
      removeEntry(paramString);
      if (!bool)
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { paramString, getFilenameForKey(paramString) });
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  static class CacheHeader
  {
    public String etag;
    public String key;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long size;
    public long softTtl;
    public long ttl;

    private CacheHeader()
    {
    }

    public CacheHeader(String paramString, Cache.Entry paramEntry)
    {
      this.key = paramString;
      this.size = paramEntry.data.length;
      this.etag = paramEntry.etag;
      this.serverDate = paramEntry.serverDate;
      this.ttl = paramEntry.ttl;
      this.softTtl = paramEntry.softTtl;
      this.responseHeaders = paramEntry.responseHeaders;
    }

    public static CacheHeader readHeader(InputStream paramInputStream)
      throws IOException
    {
      CacheHeader localCacheHeader = new CacheHeader();
      if (DiskBasedCache.readInt(paramInputStream) != 538051844)
        throw new IOException();
      localCacheHeader.key = DiskBasedCache.readString(paramInputStream);
      localCacheHeader.etag = DiskBasedCache.readString(paramInputStream);
      if (localCacheHeader.etag.equals(""))
        localCacheHeader.etag = null;
      localCacheHeader.serverDate = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.ttl = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.softTtl = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.responseHeaders = DiskBasedCache.readStringStringMap(paramInputStream);
      return localCacheHeader;
    }

    public Cache.Entry toCacheEntry(byte[] paramArrayOfByte)
    {
      Cache.Entry localEntry = new Cache.Entry();
      localEntry.data = paramArrayOfByte;
      localEntry.etag = this.etag;
      localEntry.serverDate = this.serverDate;
      localEntry.ttl = this.ttl;
      localEntry.softTtl = this.softTtl;
      localEntry.responseHeaders = this.responseHeaders;
      return localEntry;
    }

    public boolean writeHeader(OutputStream paramOutputStream)
    {
      try
      {
        DiskBasedCache.writeInt(paramOutputStream, 538051844);
        DiskBasedCache.writeString(paramOutputStream, this.key);
        if (this.etag == null);
        for (String str = ""; ; str = this.etag)
        {
          DiskBasedCache.writeString(paramOutputStream, str);
          DiskBasedCache.writeLong(paramOutputStream, this.serverDate);
          DiskBasedCache.writeLong(paramOutputStream, this.ttl);
          DiskBasedCache.writeLong(paramOutputStream, this.softTtl);
          DiskBasedCache.writeStringStringMap(this.responseHeaders, paramOutputStream);
          paramOutputStream.flush();
          return true;
        }
      }
      catch (IOException paramOutputStream)
      {
        VolleyLog.d("%s", new Object[] { paramOutputStream.toString() });
      }
      return false;
    }
  }

  private static class CountingInputStream extends FilterInputStream
  {
    private int bytesRead = 0;

    private CountingInputStream(InputStream paramInputStream)
    {
      super();
    }

    public int read()
      throws IOException
    {
      int i = super.read();
      if (i != -1)
        this.bytesRead += 1;
      return i;
    }

    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 != -1)
        this.bytesRead += paramInt1;
      return paramInt1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.DiskBasedCache
 * JD-Core Version:    0.6.2
 */