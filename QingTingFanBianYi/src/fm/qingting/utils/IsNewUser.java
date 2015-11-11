package fm.qingting.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.FileOutputStream;

public class IsNewUser
{
  private static String TAG = "UserInfo";
  private static String filename = "user-new.dat";
  private static boolean sIsNewUser = false;

  // ERROR //
  public static boolean isNewUser(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 37	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 41	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 47	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   12: getfield 53	android/content/pm/PackageInfo:versionCode	I
    //   15: istore_3
    //   16: new 55	java/io/BufferedReader
    //   19: dup
    //   20: new 57	java/io/InputStreamReader
    //   23: dup
    //   24: aload_0
    //   25: getstatic 21	fm/qingting/utils/IsNewUser:filename	Ljava/lang/String;
    //   28: invokevirtual 61	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   31: invokespecial 64	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   34: invokespecial 67	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   37: astore_0
    //   38: aload_0
    //   39: invokevirtual 70	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: invokestatic 76	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   45: istore_2
    //   46: aload_0
    //   47: invokevirtual 79	java/io/BufferedReader:close	()V
    //   50: getstatic 15	fm/qingting/utils/IsNewUser:TAG	Ljava/lang/String;
    //   53: new 81	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   60: ldc 84
    //   62: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: iload_2
    //   66: invokevirtual 91	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   69: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   75: pop
    //   76: iload_2
    //   77: iload_3
    //   78: if_icmpne +35 -> 113
    //   81: iconst_1
    //   82: istore 4
    //   84: iload 4
    //   86: putstatic 17	fm/qingting/utils/IsNewUser:sIsNewUser	Z
    //   89: getstatic 17	fm/qingting/utils/IsNewUser:sIsNewUser	Z
    //   92: ireturn
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 103	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   98: iconst_0
    //   99: istore_3
    //   100: goto -84 -> 16
    //   103: astore_0
    //   104: iconst_0
    //   105: istore_2
    //   106: aload_0
    //   107: invokevirtual 104	java/lang/Exception:printStackTrace	()V
    //   110: goto -60 -> 50
    //   113: iconst_0
    //   114: istore 4
    //   116: goto -32 -> 84
    //   119: astore_0
    //   120: goto -14 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   0	16	93	android/content/pm/PackageManager$NameNotFoundException
    //   16	46	103	java/lang/Exception
    //   46	50	119	java/lang/Exception
  }

  public static void newUserAdded(Context paramContext)
  {
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      try
      {
        while (true)
        {
          paramContext = paramContext.openFileOutput(filename, 0);
          paramContext.write(String.valueOf(i).getBytes());
          paramContext.close();
          return;
          localNameNotFoundException = localNameNotFoundException;
          localNameNotFoundException.printStackTrace();
          int i = 0;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.IsNewUser
 * JD-Core Version:    0.6.2
 */