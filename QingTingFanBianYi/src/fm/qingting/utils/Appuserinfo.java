package fm.qingting.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.FileOutputStream;

public class Appuserinfo
{
  private static String filename = "user-new.dat";

  public static void newUserAdded(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      paramContext = paramContext.openFileOutput(filename, 0);
      paramContext.write(String.valueOf(i).getBytes());
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.Appuserinfo
 * JD-Core Version:    0.6.2
 */