package fm.qingting.utils;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CPUInfo
{
  public static final int FPU_NEON = 4;
  public static final int FPU_SOFT = 0;
  public static final int FPU_VFP = 1;
  public static final int FPU_VFPV3 = 2;
  private static String cpuArch;
  private static boolean cpuArchValid = false;
  private static int cpuFreq;
  private static boolean cpuFreqValid;
  private static int fpu;
  private static boolean fpuValid = false;

  static
  {
    cpuFreqValid = false;
  }

  // ERROR //
  public static String CPUArch()
  {
    // Byte code:
    //   0: getstatic 29	fm/qingting/utils/CPUInfo:cpuArchValid	Z
    //   3: ifne +68 -> 71
    //   6: ldc 39
    //   8: putstatic 41	fm/qingting/utils/CPUInfo:cpuArch	Ljava/lang/String;
    //   11: iconst_0
    //   12: istore_3
    //   13: new 43	java/util/Scanner
    //   16: dup
    //   17: new 45	java/io/BufferedReader
    //   20: dup
    //   21: new 47	java/io/FileReader
    //   24: dup
    //   25: ldc 49
    //   27: invokespecial 52	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   30: invokespecial 55	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   33: invokespecial 58	java/util/Scanner:<init>	(Ljava/lang/Readable;)V
    //   36: astore_1
    //   37: aload_1
    //   38: astore_0
    //   39: aload_1
    //   40: invokevirtual 62	java/util/Scanner:hasNext	()Z
    //   43: ifeq +16 -> 59
    //   46: iload_3
    //   47: ifeq +28 -> 75
    //   50: aload_1
    //   51: astore_0
    //   52: aload_1
    //   53: invokevirtual 65	java/util/Scanner:next	()Ljava/lang/String;
    //   56: putstatic 41	fm/qingting/utils/CPUInfo:cpuArch	Ljava/lang/String;
    //   59: aload_1
    //   60: ifnull +7 -> 67
    //   63: aload_1
    //   64: invokevirtual 68	java/util/Scanner:close	()V
    //   67: iconst_1
    //   68: putstatic 29	fm/qingting/utils/CPUInfo:cpuArchValid	Z
    //   71: getstatic 41	fm/qingting/utils/CPUInfo:cpuArch	Ljava/lang/String;
    //   74: areturn
    //   75: aload_1
    //   76: astore_0
    //   77: aload_1
    //   78: invokevirtual 65	java/util/Scanner:next	()Ljava/lang/String;
    //   81: ldc 70
    //   83: invokevirtual 76	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   86: istore 4
    //   88: iload 4
    //   90: ifeq -53 -> 37
    //   93: iconst_1
    //   94: istore_3
    //   95: goto -58 -> 37
    //   98: astore_2
    //   99: aconst_null
    //   100: astore_1
    //   101: aload_1
    //   102: astore_0
    //   103: aload_2
    //   104: invokevirtual 79	java/lang/Exception:printStackTrace	()V
    //   107: aload_1
    //   108: ifnull -41 -> 67
    //   111: aload_1
    //   112: invokevirtual 68	java/util/Scanner:close	()V
    //   115: goto -48 -> 67
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: ifnull +7 -> 129
    //   125: aload_0
    //   126: invokevirtual 68	java/util/Scanner:close	()V
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: goto -11 -> 121
    //   135: astore_2
    //   136: goto -35 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   13	37	98	java/lang/Exception
    //   13	37	118	finally
    //   39	46	131	finally
    //   52	59	131	finally
    //   77	88	131	finally
    //   103	107	131	finally
    //   39	46	135	java/lang/Exception
    //   52	59	135	java/lang/Exception
    //   77	88	135	java/lang/Exception
  }

  public static int CPUCost()
  {
    return CPUCost(android.os.Process.myPid());
  }

  public static int CPUCost(int paramInt)
  {
    while (true)
    {
      int i;
      try
      {
        Object localObject = Runtime.getRuntime().exec("top -d 1 -n 1");
        ((java.lang.Process)localObject).waitFor();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(((java.lang.Process)localObject).getInputStream()));
        localObject = localBufferedReader.readLine();
        if (localObject != null)
        {
          String[] arrayOfString = ((String)localObject).trim().split("\\s+");
          String str = arrayOfString[0];
          if (str.equals(String.valueOf(paramInt)))
          {
            Log.d("CPU cost", str + '|' + (String)localObject);
            int j = arrayOfString.length;
            i = 0;
            if (i < j)
            {
              localObject = arrayOfString[i];
              if (!((String)localObject).endsWith("%"))
                break label164;
              return Integer.parseInt(((String)localObject).substring(0, ((String)localObject).length() - 1)) * CPUCurFreq() / 100;
            }
          }
          localObject = localBufferedReader.readLine();
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return 0;
      label164: i += 1;
    }
  }

  // ERROR //
  public static int CPUCurFreq()
  {
    // Byte code:
    //   0: iconst_0
    //   1: putstatic 179	fm/qingting/utils/CPUInfo:cpuFreq	I
    //   4: aconst_null
    //   5: astore_0
    //   6: new 43	java/util/Scanner
    //   9: dup
    //   10: new 45	java/io/BufferedReader
    //   13: dup
    //   14: new 47	java/io/FileReader
    //   17: dup
    //   18: ldc 181
    //   20: invokespecial 52	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   23: invokespecial 55	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   26: invokespecial 58	java/util/Scanner:<init>	(Ljava/lang/Readable;)V
    //   29: astore_1
    //   30: aload_1
    //   31: astore_0
    //   32: aload_1
    //   33: invokevirtual 184	java/util/Scanner:hasNextInt	()Z
    //   36: ifeq +12 -> 48
    //   39: aload_1
    //   40: astore_0
    //   41: aload_1
    //   42: invokevirtual 187	java/util/Scanner:nextInt	()I
    //   45: putstatic 179	fm/qingting/utils/CPUInfo:cpuFreq	I
    //   48: aload_1
    //   49: ifnull +7 -> 56
    //   52: aload_1
    //   53: invokevirtual 68	java/util/Scanner:close	()V
    //   56: getstatic 179	fm/qingting/utils/CPUInfo:cpuFreq	I
    //   59: ireturn
    //   60: astore_2
    //   61: aconst_null
    //   62: astore_1
    //   63: aload_1
    //   64: astore_0
    //   65: aload_2
    //   66: invokevirtual 79	java/lang/Exception:printStackTrace	()V
    //   69: aload_1
    //   70: ifnull -14 -> 56
    //   73: aload_1
    //   74: invokevirtual 68	java/util/Scanner:close	()V
    //   77: goto -21 -> 56
    //   80: astore_2
    //   81: aload_0
    //   82: astore_1
    //   83: aload_2
    //   84: astore_0
    //   85: aload_1
    //   86: ifnull +7 -> 93
    //   89: aload_1
    //   90: invokevirtual 68	java/util/Scanner:close	()V
    //   93: aload_0
    //   94: athrow
    //   95: astore_2
    //   96: aload_0
    //   97: astore_1
    //   98: aload_2
    //   99: astore_0
    //   100: goto -15 -> 85
    //   103: astore_2
    //   104: goto -41 -> 63
    //
    // Exception table:
    //   from	to	target	type
    //   6	30	60	java/lang/Exception
    //   6	30	80	finally
    //   32	39	95	finally
    //   41	48	95	finally
    //   65	69	95	finally
    //   32	39	103	java/lang/Exception
    //   41	48	103	java/lang/Exception
  }

  // ERROR //
  public static int CPUMaxFreq()
  {
    // Byte code:
    //   0: getstatic 27	fm/qingting/utils/CPUInfo:cpuFreqValid	Z
    //   3: ifne +64 -> 67
    //   6: ldc 189
    //   8: putstatic 179	fm/qingting/utils/CPUInfo:cpuFreq	I
    //   11: aconst_null
    //   12: astore_0
    //   13: new 43	java/util/Scanner
    //   16: dup
    //   17: new 45	java/io/BufferedReader
    //   20: dup
    //   21: new 47	java/io/FileReader
    //   24: dup
    //   25: ldc 191
    //   27: invokespecial 52	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   30: invokespecial 55	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   33: invokespecial 58	java/util/Scanner:<init>	(Ljava/lang/Readable;)V
    //   36: astore_1
    //   37: aload_1
    //   38: astore_0
    //   39: aload_1
    //   40: invokevirtual 184	java/util/Scanner:hasNextInt	()Z
    //   43: ifeq +12 -> 55
    //   46: aload_1
    //   47: astore_0
    //   48: aload_1
    //   49: invokevirtual 187	java/util/Scanner:nextInt	()I
    //   52: putstatic 179	fm/qingting/utils/CPUInfo:cpuFreq	I
    //   55: aload_1
    //   56: ifnull +7 -> 63
    //   59: aload_1
    //   60: invokevirtual 68	java/util/Scanner:close	()V
    //   63: iconst_1
    //   64: putstatic 27	fm/qingting/utils/CPUInfo:cpuFreqValid	Z
    //   67: getstatic 179	fm/qingting/utils/CPUInfo:cpuFreq	I
    //   70: ireturn
    //   71: astore_2
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_1
    //   75: astore_0
    //   76: aload_2
    //   77: invokevirtual 79	java/lang/Exception:printStackTrace	()V
    //   80: aload_1
    //   81: ifnull -18 -> 63
    //   84: aload_1
    //   85: invokevirtual 68	java/util/Scanner:close	()V
    //   88: goto -25 -> 63
    //   91: astore_2
    //   92: aload_0
    //   93: astore_1
    //   94: aload_2
    //   95: astore_0
    //   96: aload_1
    //   97: ifnull +7 -> 104
    //   100: aload_1
    //   101: invokevirtual 68	java/util/Scanner:close	()V
    //   104: aload_0
    //   105: athrow
    //   106: astore_2
    //   107: aload_0
    //   108: astore_1
    //   109: aload_2
    //   110: astore_0
    //   111: goto -15 -> 96
    //   114: astore_2
    //   115: goto -41 -> 74
    //
    // Exception table:
    //   from	to	target	type
    //   13	37	71	java/lang/Exception
    //   13	37	91	finally
    //   39	46	106	finally
    //   48	55	106	finally
    //   76	80	106	finally
    //   39	46	114	java/lang/Exception
    //   48	55	114	java/lang/Exception
  }

  // ERROR //
  public static int FPU()
  {
    // Byte code:
    //   0: getstatic 25	fm/qingting/utils/CPUInfo:fpuValid	Z
    //   3: ifne +90 -> 93
    //   6: iconst_0
    //   7: putstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   10: new 43	java/util/Scanner
    //   13: dup
    //   14: new 45	java/io/BufferedReader
    //   17: dup
    //   18: new 47	java/io/FileReader
    //   21: dup
    //   22: ldc 49
    //   24: invokespecial 52	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   27: invokespecial 55	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: invokespecial 58	java/util/Scanner:<init>	(Ljava/lang/Readable;)V
    //   33: astore_1
    //   34: aload_1
    //   35: astore_0
    //   36: aload_1
    //   37: invokevirtual 62	java/util/Scanner:hasNext	()Z
    //   40: ifeq +120 -> 160
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 65	java/util/Scanner:next	()Ljava/lang/String;
    //   49: astore_2
    //   50: aload_1
    //   51: astore_0
    //   52: aload_2
    //   53: ldc 196
    //   55: invokevirtual 76	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   58: ifeq +39 -> 97
    //   61: aload_1
    //   62: astore_0
    //   63: getstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   66: iconst_1
    //   67: ior
    //   68: putstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   71: goto -37 -> 34
    //   74: astore_2
    //   75: aload_1
    //   76: astore_0
    //   77: aload_2
    //   78: invokevirtual 79	java/lang/Exception:printStackTrace	()V
    //   81: aload_1
    //   82: ifnull +7 -> 89
    //   85: aload_1
    //   86: invokevirtual 68	java/util/Scanner:close	()V
    //   89: iconst_1
    //   90: putstatic 25	fm/qingting/utils/CPUInfo:fpuValid	Z
    //   93: getstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   96: ireturn
    //   97: aload_1
    //   98: astore_0
    //   99: aload_2
    //   100: ldc 198
    //   102: invokevirtual 76	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   105: ifeq +31 -> 136
    //   108: aload_1
    //   109: astore_0
    //   110: getstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   113: iconst_2
    //   114: ior
    //   115: putstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   118: goto -84 -> 34
    //   121: astore_2
    //   122: aload_0
    //   123: astore_1
    //   124: aload_2
    //   125: astore_0
    //   126: aload_1
    //   127: ifnull +7 -> 134
    //   130: aload_1
    //   131: invokevirtual 68	java/util/Scanner:close	()V
    //   134: aload_0
    //   135: athrow
    //   136: aload_1
    //   137: astore_0
    //   138: aload_2
    //   139: ldc 200
    //   141: invokevirtual 76	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   144: ifeq -110 -> 34
    //   147: aload_1
    //   148: astore_0
    //   149: getstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   152: iconst_4
    //   153: ior
    //   154: putstatic 194	fm/qingting/utils/CPUInfo:fpu	I
    //   157: goto -123 -> 34
    //   160: aload_1
    //   161: ifnull -72 -> 89
    //   164: aload_1
    //   165: invokevirtual 68	java/util/Scanner:close	()V
    //   168: goto -79 -> 89
    //   171: astore_0
    //   172: aconst_null
    //   173: astore_1
    //   174: goto -48 -> 126
    //   177: astore_2
    //   178: aconst_null
    //   179: astore_1
    //   180: goto -105 -> 75
    //
    // Exception table:
    //   from	to	target	type
    //   36	43	74	java/lang/Exception
    //   45	50	74	java/lang/Exception
    //   52	61	74	java/lang/Exception
    //   63	71	74	java/lang/Exception
    //   99	108	74	java/lang/Exception
    //   110	118	74	java/lang/Exception
    //   138	147	74	java/lang/Exception
    //   149	157	74	java/lang/Exception
    //   36	43	121	finally
    //   45	50	121	finally
    //   52	61	121	finally
    //   63	71	121	finally
    //   77	81	121	finally
    //   99	108	121	finally
    //   110	118	121	finally
    //   138	147	121	finally
    //   149	157	121	finally
    //   10	34	171	finally
    //   10	34	177	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.CPUInfo
 * JD-Core Version:    0.6.2
 */