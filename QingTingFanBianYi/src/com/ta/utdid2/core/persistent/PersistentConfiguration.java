package com.ta.utdid2.core.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.ta.utdid2.android.utils.StringUtils;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PersistentConfiguration
{
  private static final String KEY_TIMESTAMP = "t";
  private static final String KEY_TIMESTAMP2 = "t2";
  private boolean mCanRead = false;
  private boolean mCanWrite = false;
  private String mConfigName = "";
  private Context mContext = null;
  private SharedPreferences.Editor mEditor = null;
  private String mFolderName = "";
  private boolean mIsLessMode = false;
  private boolean mIsSafety = false;
  private MySharedPreferences.MyEditor mMyEditor = null;
  private MySharedPreferences mMySP = null;
  private SharedPreferences mSp = null;
  private TransactionXMLFile mTxf = null;

  public PersistentConfiguration(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mIsSafety = paramBoolean1;
    this.mIsLessMode = paramBoolean2;
    this.mConfigName = paramString2;
    this.mFolderName = paramString1;
    this.mContext = paramContext;
    long l2 = 0L;
    long l4 = 0L;
    if (paramContext != null)
    {
      this.mSp = paramContext.getSharedPreferences(paramString2, 0);
      l2 = this.mSp.getLong("t", 0L);
    }
    String str = Environment.getExternalStorageState();
    if (StringUtils.isEmpty(str))
    {
      this.mCanWrite = false;
      this.mCanRead = false;
    }
    while (true)
    {
      long l1;
      long l3;
      if (!this.mCanRead)
      {
        l1 = l4;
        l3 = l2;
        if (!this.mCanWrite);
      }
      else
      {
        l1 = l4;
        l3 = l2;
        if (paramContext != null)
        {
          l1 = l4;
          l3 = l2;
          if (!StringUtils.isEmpty(paramString1))
          {
            this.mTxf = getTransactionXMLFile(paramString1);
            l1 = l4;
            l3 = l2;
            if (this.mTxf != null)
            {
              l1 = l4;
              l3 = l2;
            }
          }
        }
      }
      try
      {
        this.mMySP = this.mTxf.getMySharedPreferences(paramString2, 0);
        l1 = l4;
        l3 = l2;
        l4 = this.mMySP.getLong("t", 0L);
        if (!paramBoolean2)
          if (l2 > l4)
          {
            l1 = l4;
            l3 = l2;
            copySPToMySP(this.mSp, this.mMySP);
            l1 = l4;
            l3 = l2;
            this.mMySP = this.mTxf.getMySharedPreferences(paramString2, 0);
            l3 = l2;
            l1 = l4;
            label328: if ((l3 != l1) || ((l3 == 0L) && (l1 == 0L)))
            {
              l2 = System.currentTimeMillis();
              if ((!this.mIsLessMode) || ((this.mIsLessMode) && (l3 == 0L) && (l1 == 0L)))
                if (this.mSp != null)
                {
                  paramContext = this.mSp.edit();
                  paramContext.putLong("t2", l2);
                  paramContext.commit();
                }
            }
          }
        try
        {
          if (this.mMySP != null)
          {
            paramContext = this.mMySP.edit();
            paramContext.putLong("t2", l2);
            paramContext.commit();
          }
          return;
          if (str.equals("mounted"))
          {
            this.mCanWrite = true;
            this.mCanRead = true;
            continue;
          }
          if (str.equals("mounted_ro"))
          {
            this.mCanRead = true;
            this.mCanWrite = false;
            continue;
          }
          this.mCanWrite = false;
          this.mCanRead = false;
          continue;
          if (l2 < l4)
          {
            l1 = l4;
            l3 = l2;
            copyMySPToSP(this.mMySP, this.mSp);
            l1 = l4;
            l3 = l2;
            this.mSp = paramContext.getSharedPreferences(paramString2, 0);
            l1 = l4;
            l3 = l2;
            break label328;
          }
          l1 = l4;
          l3 = l2;
          if (l2 != l4)
            break label328;
          l1 = l4;
          l3 = l2;
          copySPToMySP(this.mSp, this.mMySP);
          l1 = l4;
          l3 = l2;
          this.mMySP = this.mTxf.getMySharedPreferences(paramString2, 0);
          l1 = l4;
          l3 = l2;
          break label328;
          l1 = l4;
          l3 = l2;
          l2 = this.mSp.getLong("t2", 0L);
          l1 = l4;
          l3 = l2;
          l4 = this.mMySP.getLong("t2", 0L);
          if ((l2 < l4) && (l2 > 0L))
          {
            l1 = l4;
            l3 = l2;
            copySPToMySP(this.mSp, this.mMySP);
            l1 = l4;
            l3 = l2;
            this.mMySP = this.mTxf.getMySharedPreferences(paramString2, 0);
            l1 = l4;
            l3 = l2;
            break label328;
          }
          if ((l2 > l4) && (l4 > 0L))
          {
            l1 = l4;
            l3 = l2;
            copyMySPToSP(this.mMySP, this.mSp);
            l1 = l4;
            l3 = l2;
            this.mSp = paramContext.getSharedPreferences(paramString2, 0);
            l1 = l4;
            l3 = l2;
            break label328;
          }
          if ((l2 == 0L) && (l4 > 0L))
          {
            l1 = l4;
            l3 = l2;
            copyMySPToSP(this.mMySP, this.mSp);
            l1 = l4;
            l3 = l2;
            this.mSp = paramContext.getSharedPreferences(paramString2, 0);
            l1 = l4;
            l3 = l2;
            break label328;
          }
          if ((l4 == 0L) && (l2 > 0L))
          {
            l1 = l4;
            l3 = l2;
            copySPToMySP(this.mSp, this.mMySP);
            l1 = l4;
            l3 = l2;
            this.mMySP = this.mTxf.getMySharedPreferences(paramString2, 0);
            l1 = l4;
            l3 = l2;
            break label328;
          }
          l1 = l4;
          l3 = l2;
          if (l2 != l4)
            break label328;
          l1 = l4;
          l3 = l2;
          copySPToMySP(this.mSp, this.mMySP);
          l1 = l4;
          l3 = l2;
          this.mMySP = this.mTxf.getMySharedPreferences(paramString2, 0);
          l1 = l4;
          l3 = l2;
        }
        catch (Exception paramContext)
        {
        }
      }
      catch (Exception paramContext)
      {
        break label328;
      }
    }
  }

  private boolean checkSDCardXMLFile()
  {
    if (this.mMySP != null)
    {
      boolean bool = this.mMySP.checkFile();
      if (!bool)
        commit();
      return bool;
    }
    return false;
  }

  private void copyMySPToSP(MySharedPreferences paramMySharedPreferences, SharedPreferences paramSharedPreferences)
  {
    if ((paramMySharedPreferences != null) && (paramSharedPreferences != null))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      if (paramSharedPreferences != null)
      {
        paramSharedPreferences.clear();
        paramMySharedPreferences = paramMySharedPreferences.getAll().entrySet().iterator();
        while (paramMySharedPreferences.hasNext())
        {
          Object localObject = (Map.Entry)paramMySharedPreferences.next();
          String str = (String)((Map.Entry)localObject).getKey();
          localObject = ((Map.Entry)localObject).getValue();
          if ((localObject instanceof String))
            paramSharedPreferences.putString(str, (String)localObject);
          else if ((localObject instanceof Integer))
            paramSharedPreferences.putInt(str, ((Integer)localObject).intValue());
          else if ((localObject instanceof Long))
            paramSharedPreferences.putLong(str, ((Long)localObject).longValue());
          else if ((localObject instanceof Float))
            paramSharedPreferences.putFloat(str, ((Float)localObject).floatValue());
          else if ((localObject instanceof Boolean))
            paramSharedPreferences.putBoolean(str, ((Boolean)localObject).booleanValue());
        }
        paramSharedPreferences.commit();
      }
    }
  }

  private void copySPToMySP(SharedPreferences paramSharedPreferences, MySharedPreferences paramMySharedPreferences)
  {
    if ((paramSharedPreferences != null) && (paramMySharedPreferences != null))
    {
      paramMySharedPreferences = paramMySharedPreferences.edit();
      if (paramMySharedPreferences != null)
      {
        paramMySharedPreferences.clear();
        paramSharedPreferences = paramSharedPreferences.getAll().entrySet().iterator();
        while (paramSharedPreferences.hasNext())
        {
          Object localObject = (Map.Entry)paramSharedPreferences.next();
          String str = (String)((Map.Entry)localObject).getKey();
          localObject = ((Map.Entry)localObject).getValue();
          if ((localObject instanceof String))
            paramMySharedPreferences.putString(str, (String)localObject);
          else if ((localObject instanceof Integer))
            paramMySharedPreferences.putInt(str, ((Integer)localObject).intValue());
          else if ((localObject instanceof Long))
            paramMySharedPreferences.putLong(str, ((Long)localObject).longValue());
          else if ((localObject instanceof Float))
            paramMySharedPreferences.putFloat(str, ((Float)localObject).floatValue());
          else if ((localObject instanceof Boolean))
            paramMySharedPreferences.putBoolean(str, ((Boolean)localObject).booleanValue());
        }
        paramMySharedPreferences.commit();
      }
    }
  }

  private File getRootFolder(String paramString)
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
    {
      paramString = new File(String.format("%s%s%s", new Object[] { localFile.getAbsolutePath(), File.separator, paramString }));
      if ((paramString != null) && (!paramString.exists()))
        paramString.mkdirs();
      return paramString;
    }
    return null;
  }

  private TransactionXMLFile getTransactionXMLFile(String paramString)
  {
    paramString = getRootFolder(paramString);
    if (paramString != null)
    {
      this.mTxf = new TransactionXMLFile(paramString.getAbsolutePath());
      return this.mTxf;
    }
    return null;
  }

  private void initEditor()
  {
    if ((this.mEditor == null) && (this.mSp != null))
      this.mEditor = this.mSp.edit();
    if ((this.mCanWrite) && (this.mMyEditor == null) && (this.mMySP != null))
      this.mMyEditor = this.mMySP.edit();
    checkSDCardXMLFile();
  }

  public void clear()
  {
    initEditor();
    long l = System.currentTimeMillis();
    if (this.mEditor != null)
    {
      this.mEditor.clear();
      this.mEditor.putLong("t", l);
    }
    if (this.mMyEditor != null)
    {
      this.mMyEditor.clear();
      this.mMyEditor.putLong("t", l);
    }
  }

  public boolean commit()
  {
    boolean bool2 = true;
    long l = System.currentTimeMillis();
    boolean bool1 = bool2;
    if (this.mEditor != null)
    {
      if ((!this.mIsLessMode) && (this.mSp != null))
        this.mEditor.putLong("t", l);
      bool1 = bool2;
      if (!this.mEditor.commit())
        bool1 = false;
    }
    if ((this.mSp != null) && (this.mContext != null))
      this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
    String str = Environment.getExternalStorageState();
    boolean bool3 = bool1;
    if (!StringUtils.isEmpty(str))
    {
      bool2 = bool1;
      if (str.equals("mounted"))
      {
        if (this.mMySP != null)
          break label277;
        TransactionXMLFile localTransactionXMLFile = getTransactionXMLFile(this.mFolderName);
        bool2 = bool1;
        if (localTransactionXMLFile != null)
        {
          this.mMySP = localTransactionXMLFile.getMySharedPreferences(this.mConfigName, 0);
          if (this.mIsLessMode)
            break label262;
          copySPToMySP(this.mSp, this.mMySP);
        }
      }
    }
    while (true)
    {
      this.mMyEditor = this.mMySP.edit();
      bool2 = bool1;
      label195: if (!str.equals("mounted"))
      {
        bool3 = bool2;
        if (str.equals("mounted_ro"))
        {
          bool3 = bool2;
          if (this.mMySP == null);
        }
      }
      else
      {
        bool3 = bool2;
      }
      try
      {
        if (this.mTxf != null)
        {
          this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
          bool3 = bool2;
        }
        return bool3;
        label262: copyMySPToSP(this.mMySP, this.mSp);
        continue;
        label277: bool2 = bool1;
        if (this.mMyEditor == null)
          break label195;
        bool2 = bool1;
        if (this.mMyEditor.commit())
          break label195;
        bool2 = false;
      }
      catch (Exception localException)
      {
      }
    }
    return bool2;
  }

  public Map<String, ?> getAll()
  {
    checkSDCardXMLFile();
    if (this.mSp != null)
      return this.mSp.getAll();
    if (this.mMySP != null)
      return this.mMySP.getAll();
    return null;
  }

  public boolean getBoolean(String paramString)
  {
    boolean bool = false;
    checkSDCardXMLFile();
    if (this.mSp != null)
      bool = this.mSp.getBoolean(paramString, false);
    while (this.mMySP == null)
      return bool;
    return this.mMySP.getBoolean(paramString, false);
  }

  public float getFloat(String paramString)
  {
    float f = 0.0F;
    checkSDCardXMLFile();
    if (this.mSp != null)
      f = this.mSp.getFloat(paramString, 0.0F);
    while (this.mMySP == null)
      return f;
    return this.mMySP.getFloat(paramString, 0.0F);
  }

  public int getInt(String paramString)
  {
    int i = 0;
    checkSDCardXMLFile();
    if (this.mSp != null)
      i = this.mSp.getInt(paramString, 0);
    while (this.mMySP == null)
      return i;
    return this.mMySP.getInt(paramString, 0);
  }

  public long getLong(String paramString)
  {
    long l = 0L;
    checkSDCardXMLFile();
    if (this.mSp != null)
      l = this.mSp.getLong(paramString, 0L);
    while (this.mMySP == null)
      return l;
    return this.mMySP.getLong(paramString, 0L);
  }

  public String getString(String paramString)
  {
    checkSDCardXMLFile();
    if (this.mSp != null)
    {
      String str = this.mSp.getString(paramString, "");
      if (!StringUtils.isEmpty(str))
        return str;
    }
    if (this.mMySP != null)
      return this.mMySP.getString(paramString, "");
    return "";
  }

  public void putBoolean(String paramString, boolean paramBoolean)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putBoolean(paramString, paramBoolean);
      if (this.mMyEditor != null)
        this.mMyEditor.putBoolean(paramString, paramBoolean);
    }
  }

  public void putFloat(String paramString, float paramFloat)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putFloat(paramString, paramFloat);
      if (this.mMyEditor != null)
        this.mMyEditor.putFloat(paramString, paramFloat);
    }
  }

  public void putInt(String paramString, int paramInt)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putInt(paramString, paramInt);
      if (this.mMyEditor != null)
        this.mMyEditor.putInt(paramString, paramInt);
    }
  }

  public void putLong(String paramString, long paramLong)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putLong(paramString, paramLong);
      if (this.mMyEditor != null)
        this.mMyEditor.putLong(paramString, paramLong);
    }
  }

  public void putString(String paramString1, String paramString2)
  {
    if ((!StringUtils.isEmpty(paramString1)) && (!paramString1.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.putString(paramString1, paramString2);
      if (this.mMyEditor != null)
        this.mMyEditor.putString(paramString1, paramString2);
    }
  }

  public void reload()
  {
    if ((this.mSp != null) && (this.mContext != null))
      this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
    String str = Environment.getExternalStorageState();
    if ((!StringUtils.isEmpty(str)) && ((str.equals("mounted")) || ((str.equals("mounted_ro")) && (this.mMySP != null))));
    try
    {
      if (this.mTxf != null)
        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void remove(String paramString)
  {
    if ((!StringUtils.isEmpty(paramString)) && (!paramString.equals("t")))
    {
      initEditor();
      if (this.mEditor != null)
        this.mEditor.remove(paramString);
      if (this.mMyEditor != null)
        this.mMyEditor.remove(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.PersistentConfiguration
 * JD-Core Version:    0.6.2
 */