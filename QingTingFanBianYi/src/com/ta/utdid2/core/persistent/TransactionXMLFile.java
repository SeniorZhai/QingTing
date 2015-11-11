package com.ta.utdid2.core.persistent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public class TransactionXMLFile
{
  private static final Object GLOBAL_COMMIT_LOCK = new Object();
  public static final int MODE_PRIVATE = 0;
  public static final int MODE_WORLD_READABLE = 1;
  public static final int MODE_WORLD_WRITEABLE = 2;
  private File mPreferencesDir;
  private final Object mSync = new Object();
  private HashMap<File, MySharedPreferencesImpl> sSharedPrefs = new HashMap();

  public TransactionXMLFile(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      this.mPreferencesDir = new File(paramString);
      return;
    }
    throw new RuntimeException("Directory can not be empty");
  }

  private File getPreferencesDir()
  {
    synchronized (this.mSync)
    {
      File localFile = this.mPreferencesDir;
      return localFile;
    }
  }

  private File getSharedPrefsFile(String paramString)
  {
    return makeFilename(getPreferencesDir(), paramString + ".xml");
  }

  private static File makeBackupFile(File paramFile)
  {
    return new File(paramFile.getPath() + ".bak");
  }

  private File makeFilename(File paramFile, String paramString)
  {
    if (paramString.indexOf(File.separatorChar) < 0)
      return new File(paramFile, paramString);
    throw new IllegalArgumentException("File " + paramString + " contains a path separator");
  }

  // ERROR //
  public MySharedPreferences getMySharedPreferences(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 121	com/ta/utdid2/core/persistent/TransactionXMLFile:getSharedPrefsFile	(Ljava/lang/String;)Ljava/io/File;
    //   5: astore 10
    //   7: getstatic 32	com/ta/utdid2/core/persistent/TransactionXMLFile:GLOBAL_COMMIT_LOCK	Ljava/lang/Object;
    //   10: astore_1
    //   11: aload_1
    //   12: monitorenter
    //   13: aload_0
    //   14: getfield 41	com/ta/utdid2/core/persistent/TransactionXMLFile:sSharedPrefs	Ljava/util/HashMap;
    //   17: aload 10
    //   19: invokevirtual 125	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast 6	com/ta/utdid2/core/persistent/TransactionXMLFile$MySharedPreferencesImpl
    //   25: astore 6
    //   27: aload 6
    //   29: ifnull +16 -> 45
    //   32: aload 6
    //   34: invokevirtual 129	com/ta/utdid2/core/persistent/TransactionXMLFile$MySharedPreferencesImpl:hasFileChanged	()Z
    //   37: ifne +8 -> 45
    //   40: aload_1
    //   41: monitorexit
    //   42: aload 6
    //   44: areturn
    //   45: aload_1
    //   46: monitorexit
    //   47: aload 10
    //   49: invokestatic 63	com/ta/utdid2/core/persistent/TransactionXMLFile:makeBackupFile	(Ljava/io/File;)Ljava/io/File;
    //   52: astore_1
    //   53: aload_1
    //   54: invokevirtual 132	java/io/File:exists	()Z
    //   57: ifeq +16 -> 73
    //   60: aload 10
    //   62: invokevirtual 135	java/io/File:delete	()Z
    //   65: pop
    //   66: aload_1
    //   67: aload 10
    //   69: invokevirtual 139	java/io/File:renameTo	(Ljava/io/File;)Z
    //   72: pop
    //   73: aload 10
    //   75: invokevirtual 132	java/io/File:exists	()Z
    //   78: ifeq +11 -> 89
    //   81: aload 10
    //   83: invokevirtual 142	java/io/File:canRead	()Z
    //   86: ifne +3 -> 89
    //   89: aconst_null
    //   90: astore 4
    //   92: aconst_null
    //   93: astore 9
    //   95: aconst_null
    //   96: astore 8
    //   98: aconst_null
    //   99: astore 5
    //   101: aconst_null
    //   102: astore_3
    //   103: aload_3
    //   104: astore_1
    //   105: aload 10
    //   107: invokevirtual 132	java/io/File:exists	()Z
    //   110: ifeq +50 -> 160
    //   113: aload_3
    //   114: astore_1
    //   115: aload 10
    //   117: invokevirtual 142	java/io/File:canRead	()Z
    //   120: ifeq +40 -> 160
    //   123: new 144	java/io/FileInputStream
    //   126: dup
    //   127: aload 10
    //   129: invokespecial 147	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   132: astore 7
    //   134: aload 9
    //   136: astore_3
    //   137: aload 8
    //   139: astore 4
    //   141: aload 7
    //   143: invokestatic 153	com/ta/utdid2/core/persistent/XmlUtils:readMapXml	(Ljava/io/InputStream;)Ljava/util/HashMap;
    //   146: astore_1
    //   147: aload_1
    //   148: astore_3
    //   149: aload_1
    //   150: astore 4
    //   152: aload_1
    //   153: astore 5
    //   155: aload 7
    //   157: invokevirtual 156	java/io/FileInputStream:close	()V
    //   160: getstatic 32	com/ta/utdid2/core/persistent/TransactionXMLFile:GLOBAL_COMMIT_LOCK	Ljava/lang/Object;
    //   163: astore 5
    //   165: aload 5
    //   167: monitorenter
    //   168: aload 6
    //   170: ifnull +88 -> 258
    //   173: aload 6
    //   175: aload_1
    //   176: invokevirtual 160	com/ta/utdid2/core/persistent/TransactionXMLFile$MySharedPreferencesImpl:replace	(Ljava/util/Map;)V
    //   179: aload 6
    //   181: astore_3
    //   182: aload 5
    //   184: monitorexit
    //   185: aload_3
    //   186: areturn
    //   187: astore_3
    //   188: aload_1
    //   189: monitorexit
    //   190: aload_3
    //   191: athrow
    //   192: astore_1
    //   193: aconst_null
    //   194: astore_3
    //   195: aload 4
    //   197: astore_1
    //   198: new 144	java/io/FileInputStream
    //   201: dup
    //   202: aload 10
    //   204: invokespecial 147	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   207: astore_3
    //   208: aload_3
    //   209: invokevirtual 163	java/io/FileInputStream:available	()I
    //   212: newarray byte
    //   214: astore 4
    //   216: aload_3
    //   217: aload 4
    //   219: invokevirtual 167	java/io/FileInputStream:read	([B)I
    //   222: pop
    //   223: new 43	java/lang/String
    //   226: dup
    //   227: aload 4
    //   229: iconst_0
    //   230: aload 4
    //   232: arraylength
    //   233: ldc 169
    //   235: invokespecial 172	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   238: pop
    //   239: goto -79 -> 160
    //   242: astore_3
    //   243: aload_3
    //   244: invokevirtual 175	java/io/FileNotFoundException:printStackTrace	()V
    //   247: goto -87 -> 160
    //   250: astore_3
    //   251: aload_3
    //   252: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   255: goto -95 -> 160
    //   258: aload_0
    //   259: getfield 41	com/ta/utdid2/core/persistent/TransactionXMLFile:sSharedPrefs	Ljava/util/HashMap;
    //   262: aload 10
    //   264: invokevirtual 125	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   267: checkcast 6	com/ta/utdid2/core/persistent/TransactionXMLFile$MySharedPreferencesImpl
    //   270: astore 4
    //   272: aload 4
    //   274: astore_3
    //   275: aload 4
    //   277: ifnonnull -95 -> 182
    //   280: new 6	com/ta/utdid2/core/persistent/TransactionXMLFile$MySharedPreferencesImpl
    //   283: dup
    //   284: aload 10
    //   286: iload_2
    //   287: aload_1
    //   288: invokespecial 179	com/ta/utdid2/core/persistent/TransactionXMLFile$MySharedPreferencesImpl:<init>	(Ljava/io/File;ILjava/util/Map;)V
    //   291: astore_3
    //   292: aload_0
    //   293: getfield 41	com/ta/utdid2/core/persistent/TransactionXMLFile:sSharedPrefs	Ljava/util/HashMap;
    //   296: aload 10
    //   298: aload_3
    //   299: invokevirtual 183	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   302: pop
    //   303: goto -121 -> 182
    //   306: astore_1
    //   307: aload 5
    //   309: monitorexit
    //   310: aload_1
    //   311: athrow
    //   312: astore_1
    //   313: goto -6 -> 307
    //   316: astore_1
    //   317: aload_3
    //   318: astore_1
    //   319: goto -159 -> 160
    //   322: astore_1
    //   323: aload_3
    //   324: astore_1
    //   325: goto -165 -> 160
    //   328: astore_1
    //   329: aload_3
    //   330: astore_1
    //   331: goto -171 -> 160
    //   334: astore_1
    //   335: aload 4
    //   337: astore_1
    //   338: goto -178 -> 160
    //   341: astore_3
    //   342: goto -91 -> 251
    //   345: astore_3
    //   346: goto -103 -> 243
    //   349: astore_1
    //   350: aload 5
    //   352: astore_1
    //   353: aload 7
    //   355: astore_3
    //   356: goto -158 -> 198
    //
    // Exception table:
    //   from	to	target	type
    //   13	27	187	finally
    //   32	42	187	finally
    //   45	47	187	finally
    //   188	190	187	finally
    //   123	134	192	org/xmlpull/v1/XmlPullParserException
    //   208	239	242	java/io/FileNotFoundException
    //   198	208	250	java/io/IOException
    //   173	179	306	finally
    //   182	185	306	finally
    //   258	272	306	finally
    //   280	292	306	finally
    //   307	310	306	finally
    //   292	303	312	finally
    //   123	134	316	java/io/IOException
    //   141	147	322	java/io/IOException
    //   155	160	322	java/io/IOException
    //   123	134	328	java/io/FileNotFoundException
    //   141	147	334	java/io/FileNotFoundException
    //   155	160	334	java/io/FileNotFoundException
    //   208	239	341	java/io/IOException
    //   198	208	345	java/io/FileNotFoundException
    //   141	147	349	org/xmlpull/v1/XmlPullParserException
    //   155	160	349	org/xmlpull/v1/XmlPullParserException
  }

  private static final class MySharedPreferencesImpl
    implements MySharedPreferences
  {
    private static final Object mContent = new Object();
    private boolean hasChange = false;
    private final File mBackupFile;
    private final File mFile;
    private WeakHashMap<MySharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners;
    private Map mMap;
    private final int mMode;

    MySharedPreferencesImpl(File paramFile, int paramInt, Map paramMap)
    {
      this.mFile = paramFile;
      this.mBackupFile = TransactionXMLFile.makeBackupFile(paramFile);
      this.mMode = paramInt;
      if (paramMap != null);
      while (true)
      {
        this.mMap = paramMap;
        this.mListeners = new WeakHashMap();
        return;
        paramMap = new HashMap();
      }
    }

    private FileOutputStream createFileOutputStream(File paramFile)
    {
      Object localObject = null;
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
        paramFile = localFileOutputStream;
        return paramFile;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        while (true)
        {
          if (!paramFile.getParentFile().mkdir())
            return null;
          try
          {
            paramFile = new FileOutputStream(paramFile);
          }
          catch (FileNotFoundException paramFile)
          {
            paramFile = localObject;
          }
        }
      }
    }

    private boolean writeFileLocked()
    {
      if (this.mFile.exists())
        if (!this.mBackupFile.exists())
          if (this.mFile.renameTo(this.mBackupFile))
            break label44;
      while (true)
      {
        return false;
        this.mFile.delete();
        try
        {
          label44: FileOutputStream localFileOutputStream = createFileOutputStream(this.mFile);
          if (localFileOutputStream == null)
            continue;
          XmlUtils.writeMapXml(this.mMap, localFileOutputStream);
          localFileOutputStream.close();
          this.mBackupFile.delete();
          return true;
        }
        catch (IOException localIOException)
        {
          if ((!this.mFile.exists()) || (this.mFile.delete()))
            continue;
          return false;
        }
        catch (XmlPullParserException localXmlPullParserException)
        {
          label80: break label80;
        }
      }
    }

    public boolean checkFile()
    {
      return (this.mFile != null) && (new File(this.mFile.getAbsolutePath()).exists());
    }

    public boolean contains(String paramString)
    {
      try
      {
        boolean bool = this.mMap.containsKey(paramString);
        return bool;
      }
      finally
      {
      }
      throw paramString;
    }

    public MySharedPreferences.MyEditor edit()
    {
      return new EditorImpl();
    }

    public Map<String, ?> getAll()
    {
      try
      {
        HashMap localHashMap = new HashMap(this.mMap);
        return localHashMap;
      }
      finally
      {
      }
    }

    public boolean getBoolean(String paramString, boolean paramBoolean)
    {
      try
      {
        paramString = (Boolean)this.mMap.get(paramString);
        if (paramString != null)
          paramBoolean = paramString.booleanValue();
        return paramBoolean;
      }
      finally
      {
      }
      throw paramString;
    }

    public float getFloat(String paramString, float paramFloat)
    {
      try
      {
        paramString = (Float)this.mMap.get(paramString);
        if (paramString != null)
          paramFloat = paramString.floatValue();
        return paramFloat;
      }
      finally
      {
      }
      throw paramString;
    }

    public int getInt(String paramString, int paramInt)
    {
      try
      {
        paramString = (Integer)this.mMap.get(paramString);
        if (paramString != null)
          paramInt = paramString.intValue();
        return paramInt;
      }
      finally
      {
      }
      throw paramString;
    }

    public long getLong(String paramString, long paramLong)
    {
      try
      {
        paramString = (Long)this.mMap.get(paramString);
        if (paramString != null)
          paramLong = paramString.longValue();
        return paramLong;
      }
      finally
      {
      }
      throw paramString;
    }

    public String getString(String paramString1, String paramString2)
    {
      while (true)
      {
        try
        {
          paramString1 = (String)this.mMap.get(paramString1);
          if (paramString1 != null)
            return paramString1;
        }
        finally
        {
        }
        paramString1 = paramString2;
      }
    }

    public boolean hasFileChanged()
    {
      try
      {
        boolean bool = this.hasChange;
        return bool;
      }
      finally
      {
      }
    }

    public void registerOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
    {
      try
      {
        this.mListeners.put(paramOnSharedPreferenceChangeListener, mContent);
        return;
      }
      finally
      {
      }
      throw paramOnSharedPreferenceChangeListener;
    }

    public void replace(Map paramMap)
    {
      if (paramMap != null)
        try
        {
          this.mMap = paramMap;
          return;
        }
        finally
        {
        }
    }

    public void setHasChange(boolean paramBoolean)
    {
      try
      {
        this.hasChange = paramBoolean;
        return;
      }
      finally
      {
      }
    }

    public void unregisterOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
    {
      try
      {
        this.mListeners.remove(paramOnSharedPreferenceChangeListener);
        return;
      }
      finally
      {
      }
      throw paramOnSharedPreferenceChangeListener;
    }

    public final class EditorImpl
      implements MySharedPreferences.MyEditor
    {
      private boolean mClear = false;
      private final Map<String, Object> mModified = new HashMap();

      public EditorImpl()
      {
      }

      public MySharedPreferences.MyEditor clear()
      {
        try
        {
          this.mClear = true;
          return this;
        }
        finally
        {
        }
      }

      public boolean commit()
      {
        ArrayList localArrayList = null;
        HashSet localHashSet = null;
        synchronized (TransactionXMLFile.GLOBAL_COMMIT_LOCK)
        {
          int i;
          if (TransactionXMLFile.MySharedPreferencesImpl.this.mListeners.size() > 0)
            i = 1;
          while (true)
          {
            if (i != 0)
              localArrayList = new ArrayList();
            try
            {
              localHashSet = new HashSet(TransactionXMLFile.MySharedPreferencesImpl.this.mListeners.keySet());
              Iterator localIterator;
              Object localObject5;
              while (true)
              {
                Object localObject6;
                try
                {
                  if (this.mClear)
                  {
                    TransactionXMLFile.MySharedPreferencesImpl.this.mMap.clear();
                    this.mClear = false;
                  }
                  localIterator = this.mModified.entrySet().iterator();
                  if (!localIterator.hasNext())
                    break label215;
                  localObject6 = (Map.Entry)localIterator.next();
                  localObject5 = (String)((Map.Entry)localObject6).getKey();
                  localObject6 = ((Map.Entry)localObject6).getValue();
                  if (localObject6 != this)
                    break label195;
                  TransactionXMLFile.MySharedPreferencesImpl.this.mMap.remove(localObject5);
                  if (i == 0)
                    continue;
                  localArrayList.add(localObject5);
                }
                finally
                {
                }
                label185: throw localObject1;
                i = 0;
                break;
                label195: TransactionXMLFile.MySharedPreferencesImpl.this.mMap.put(localObject5, localObject6);
              }
              label215: this.mModified.clear();
              boolean bool = TransactionXMLFile.MySharedPreferencesImpl.this.writeFileLocked();
              if (bool)
                TransactionXMLFile.MySharedPreferencesImpl.this.setHasChange(true);
              if (i != 0)
              {
                i = localObject1.size() - 1;
                if (i >= 0)
                {
                  ??? = (String)localObject1.get(i);
                  localIterator = localHashSet.iterator();
                  while (localIterator.hasNext())
                  {
                    localObject5 = (MySharedPreferences.OnSharedPreferenceChangeListener)localIterator.next();
                    if (localObject5 != null)
                      ((MySharedPreferences.OnSharedPreferenceChangeListener)localObject5).onSharedPreferenceChanged(TransactionXMLFile.MySharedPreferencesImpl.this, (String)???);
                  }
                  i -= 1;
                }
              }
              return bool;
            }
            finally
            {
              break label185;
            }
          }
        }
      }

      public MySharedPreferences.MyEditor putBoolean(String paramString, boolean paramBoolean)
      {
        try
        {
          this.mModified.put(paramString, Boolean.valueOf(paramBoolean));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putFloat(String paramString, float paramFloat)
      {
        try
        {
          this.mModified.put(paramString, Float.valueOf(paramFloat));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putInt(String paramString, int paramInt)
      {
        try
        {
          this.mModified.put(paramString, Integer.valueOf(paramInt));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putLong(String paramString, long paramLong)
      {
        try
        {
          this.mModified.put(paramString, Long.valueOf(paramLong));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putString(String paramString1, String paramString2)
      {
        try
        {
          this.mModified.put(paramString1, paramString2);
          return this;
        }
        finally
        {
        }
        throw paramString1;
      }

      public MySharedPreferences.MyEditor remove(String paramString)
      {
        try
        {
          this.mModified.put(paramString, this);
          return this;
        }
        finally
        {
        }
        throw paramString;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.TransactionXMLFile
 * JD-Core Version:    0.6.2
 */