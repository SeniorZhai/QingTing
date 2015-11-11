package fm.qingting.nativec;

import android.content.Context;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class NativeLaucher
{
  public static String RunExecutable(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    int j = 0;
    paramContext = "/data/data/" + paramString1;
    paramString2 = paramContext + "/lib/" + paramString2;
    String str = paramContext + "/" + paramString3;
    StringBuffer localStringBuffer = new StringBuffer();
    RunLocalUserCommand(paramString1, "dd if=" + paramString2 + " of=" + str, localStringBuffer);
    localStringBuffer.append(";");
    RunLocalUserCommand(paramString1, "chmod 777 " + str, localStringBuffer);
    localStringBuffer.append(";");
    paramContext = paramContext + "/" + paramString3;
    paramString2 = paramContext + " " + paramString4;
    paramContext = paramString2;
    int i;
    if (paramArrayOfString1 != null)
    {
      paramContext = paramString2;
      i = 0;
      while (i < paramArrayOfString1.length)
      {
        paramContext = paramContext + " " + paramArrayOfString1[i];
        i += 1;
      }
    }
    paramString2 = paramContext;
    if (paramArrayOfString2 != null)
    {
      i = j;
      while (true)
      {
        paramString2 = paramContext;
        if (i >= paramArrayOfString2.length)
          break;
        paramContext = paramContext + " " + paramArrayOfString2[i];
        i += 1;
      }
    }
    RunLocalUserCommand(paramString1, paramString2, localStringBuffer);
    localStringBuffer.append(";");
    return localStringBuffer.toString();
  }

  private static boolean RunLocalUserCommand(String paramString1, String paramString2, StringBuffer paramStringBuffer)
  {
    try
    {
      Process localProcess = Runtime.getRuntime().exec("sh");
      DataInputStream localDataInputStream = new DataInputStream(localProcess.getInputStream());
      DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
      localDataOutputStream.writeBytes("cd /data/data/" + paramString1 + "\n");
      localDataOutputStream.writeBytes(paramString2 + " &\n");
      localDataOutputStream.writeBytes("exit\n");
      localDataOutputStream.flush();
      localProcess.waitFor();
      paramString1 = new byte[localDataInputStream.available()];
      localDataInputStream.read(paramString1);
      paramString1 = new String(paramString1);
      if (paramStringBuffer != null)
        paramStringBuffer.append("CMD Result:\n" + paramString1);
      return true;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      if (paramStringBuffer != null)
        paramStringBuffer.append("Exception:" + paramString1.getMessage());
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.nativec.NativeLaucher
 * JD-Core Version:    0.6.2
 */