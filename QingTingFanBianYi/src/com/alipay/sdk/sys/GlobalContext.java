package com.alipay.sdk.sys;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.data.MspConfig;
import com.alipay.sdk.util.Utils;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

public class GlobalContext
{
  private static GlobalContext a;
  private Context b;
  private MspConfig c;

  public static GlobalContext a()
  {
    if (a == null)
      a = new GlobalContext();
    return a;
  }

  // ERROR //
  private static String a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: new 24	java/lang/ProcessBuilder
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 27	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   8: astore_0
    //   9: aload_0
    //   10: iconst_0
    //   11: invokevirtual 31	java/lang/ProcessBuilder:redirectErrorStream	(Z)Ljava/lang/ProcessBuilder;
    //   14: pop
    //   15: aload_0
    //   16: invokevirtual 35	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   19: astore_0
    //   20: new 37	java/io/DataOutputStream
    //   23: dup
    //   24: aload_0
    //   25: invokevirtual 43	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   28: invokespecial 46	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   31: astore_2
    //   32: new 48	java/io/DataInputStream
    //   35: dup
    //   36: aload_0
    //   37: invokevirtual 52	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   40: invokespecial 55	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   43: invokevirtual 59	java/io/DataInputStream:readLine	()Ljava/lang/String;
    //   46: astore_1
    //   47: aload_2
    //   48: ldc 61
    //   50: invokevirtual 65	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   53: aload_2
    //   54: invokevirtual 68	java/io/DataOutputStream:flush	()V
    //   57: aload_0
    //   58: invokevirtual 72	java/lang/Process:waitFor	()I
    //   61: pop
    //   62: aload_0
    //   63: invokevirtual 75	java/lang/Process:destroy	()V
    //   66: aload_1
    //   67: areturn
    //   68: astore_0
    //   69: aconst_null
    //   70: astore_0
    //   71: ldc 77
    //   73: astore_1
    //   74: aload_0
    //   75: invokevirtual 75	java/lang/Process:destroy	()V
    //   78: aload_1
    //   79: areturn
    //   80: astore_0
    //   81: aload_1
    //   82: areturn
    //   83: astore_1
    //   84: aconst_null
    //   85: astore_0
    //   86: aload_0
    //   87: invokevirtual 75	java/lang/Process:destroy	()V
    //   90: aload_1
    //   91: athrow
    //   92: astore_0
    //   93: aload_1
    //   94: areturn
    //   95: astore_0
    //   96: goto -6 -> 90
    //   99: astore_1
    //   100: goto -14 -> 86
    //   103: astore_1
    //   104: ldc 77
    //   106: astore_1
    //   107: goto -33 -> 74
    //   110: astore_2
    //   111: goto -37 -> 74
    //
    // Exception table:
    //   from	to	target	type
    //   0	20	68	java/lang/Exception
    //   74	78	80	java/lang/Exception
    //   0	20	83	finally
    //   62	66	92	java/lang/Exception
    //   86	90	95	java/lang/Exception
    //   20	47	99	finally
    //   47	62	99	finally
    //   20	47	103	java/lang/Exception
    //   47	62	110	java/lang/Exception
  }

  private static boolean a(String paramString)
  {
    char[] arrayOfChar = new char[65];
    char[] tmp6_5 = arrayOfChar;
    tmp6_5[0] = 65;
    char[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 66;
    char[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 67;
    char[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 68;
    char[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 69;
    char[] tmp31_26 = tmp26_21;
    tmp31_26[5] = 70;
    char[] tmp36_31 = tmp31_26;
    tmp36_31[6] = 71;
    char[] tmp42_36 = tmp36_31;
    tmp42_36[7] = 72;
    char[] tmp48_42 = tmp42_36;
    tmp48_42[8] = 73;
    char[] tmp54_48 = tmp48_42;
    tmp54_48[9] = 74;
    char[] tmp60_54 = tmp54_48;
    tmp60_54[10] = 75;
    char[] tmp66_60 = tmp60_54;
    tmp66_60[11] = 76;
    char[] tmp72_66 = tmp66_60;
    tmp72_66[12] = 77;
    char[] tmp78_72 = tmp72_66;
    tmp78_72[13] = 78;
    char[] tmp84_78 = tmp78_72;
    tmp84_78[14] = 79;
    char[] tmp90_84 = tmp84_78;
    tmp90_84[15] = 80;
    char[] tmp96_90 = tmp90_84;
    tmp96_90[16] = 81;
    char[] tmp102_96 = tmp96_90;
    tmp102_96[17] = 82;
    char[] tmp108_102 = tmp102_96;
    tmp108_102[18] = 83;
    char[] tmp114_108 = tmp108_102;
    tmp114_108[19] = 84;
    char[] tmp120_114 = tmp114_108;
    tmp120_114[20] = 85;
    char[] tmp126_120 = tmp120_114;
    tmp126_120[21] = 86;
    char[] tmp132_126 = tmp126_120;
    tmp132_126[22] = 87;
    char[] tmp138_132 = tmp132_126;
    tmp138_132[23] = 88;
    char[] tmp144_138 = tmp138_132;
    tmp144_138[24] = 89;
    char[] tmp150_144 = tmp144_138;
    tmp150_144[25] = 90;
    char[] tmp156_150 = tmp150_144;
    tmp156_150[26] = 97;
    char[] tmp162_156 = tmp156_150;
    tmp162_156[27] = 98;
    char[] tmp168_162 = tmp162_156;
    tmp168_162[28] = 99;
    char[] tmp174_168 = tmp168_162;
    tmp174_168[29] = 100;
    char[] tmp180_174 = tmp174_168;
    tmp180_174[30] = 101;
    char[] tmp186_180 = tmp180_174;
    tmp186_180[31] = 102;
    char[] tmp192_186 = tmp186_180;
    tmp192_186[32] = 103;
    char[] tmp198_192 = tmp192_186;
    tmp198_192[33] = 104;
    char[] tmp204_198 = tmp198_192;
    tmp204_198[34] = 105;
    char[] tmp210_204 = tmp204_198;
    tmp210_204[35] = 106;
    char[] tmp216_210 = tmp210_204;
    tmp216_210[36] = 107;
    char[] tmp222_216 = tmp216_210;
    tmp222_216[37] = 108;
    char[] tmp228_222 = tmp222_216;
    tmp228_222[38] = 109;
    char[] tmp234_228 = tmp228_222;
    tmp234_228[39] = 110;
    char[] tmp240_234 = tmp234_228;
    tmp240_234[40] = 111;
    char[] tmp246_240 = tmp240_234;
    tmp246_240[41] = 112;
    char[] tmp252_246 = tmp246_240;
    tmp252_246[42] = 113;
    char[] tmp258_252 = tmp252_246;
    tmp258_252[43] = 114;
    char[] tmp264_258 = tmp258_252;
    tmp264_258[44] = 115;
    char[] tmp270_264 = tmp264_258;
    tmp270_264[45] = 116;
    char[] tmp276_270 = tmp270_264;
    tmp276_270[46] = 117;
    char[] tmp282_276 = tmp276_270;
    tmp282_276[47] = 118;
    char[] tmp288_282 = tmp282_276;
    tmp288_282[48] = 119;
    char[] tmp294_288 = tmp288_282;
    tmp294_288[49] = 120;
    char[] tmp300_294 = tmp294_288;
    tmp300_294[50] = 121;
    char[] tmp306_300 = tmp300_294;
    tmp306_300[51] = 122;
    char[] tmp312_306 = tmp306_300;
    tmp312_306[52] = 48;
    char[] tmp318_312 = tmp312_306;
    tmp318_312[53] = 49;
    char[] tmp324_318 = tmp318_312;
    tmp324_318[54] = 50;
    char[] tmp330_324 = tmp324_318;
    tmp330_324[55] = 51;
    char[] tmp336_330 = tmp330_324;
    tmp336_330[56] = 52;
    char[] tmp342_336 = tmp336_330;
    tmp342_336[57] = 53;
    char[] tmp348_342 = tmp342_336;
    tmp348_342[58] = 54;
    char[] tmp354_348 = tmp348_342;
    tmp354_348[59] = 55;
    char[] tmp360_354 = tmp354_348;
    tmp360_354[60] = 56;
    char[] tmp366_360 = tmp360_354;
    tmp366_360[61] = 57;
    char[] tmp372_366 = tmp366_360;
    tmp372_366[62] = 43;
    char[] tmp378_372 = tmp372_366;
    tmp378_372[63] = 47;
    char[] tmp384_378 = tmp378_372;
    tmp384_378[64] = 61;
    tmp384_378;
    paramString = paramString.toCharArray();
    int k = paramString.length;
    int i = 0;
    boolean bool1 = false;
    boolean bool2;
    while (true)
    {
      bool2 = bool1;
      if (i >= k)
        break;
      int m = paramString[i];
      int n = arrayOfChar.length;
      int j = 0;
      bool1 = false;
      while (j < n)
      {
        if (m == arrayOfChar[j])
          bool1 = true;
        j += 1;
      }
      bool2 = bool1;
      if (!bool1)
        break;
      i += 1;
    }
    return bool2;
  }

  public static boolean d()
  {
    return false;
  }

  public static boolean e()
  {
    boolean bool = true;
    Object localObject = new String[5];
    localObject[0] = "/system/xbin/";
    localObject[1] = "/system/bin/";
    localObject[2] = "/system/sbin/";
    localObject[3] = "/sbin/";
    localObject[4] = "/vendor/bin/";
    int i = 0;
    try
    {
      while (i < localObject.length)
      {
        String str = localObject[i] + "su";
        if (new File(str).exists())
        {
          localObject = a(new String[] { "ls", "-l", str });
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            i = ((String)localObject).indexOf("root");
            int j = ((String)localObject).lastIndexOf("root");
            if (i != j);
          }
          else
          {
            bool = false;
          }
          return bool;
        }
        i += 1;
      }
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static String f()
  {
    return Utils.d();
  }

  public final void a(Context paramContext, MspConfig paramMspConfig)
  {
    this.b = paramContext.getApplicationContext();
    this.c = paramMspConfig;
  }

  public final Context b()
  {
    return this.b;
  }

  public final MspConfig c()
  {
    return this.c;
  }

  public final String g()
  {
    String str = UTDevice.getUtdid(this.b);
    if (!TextUtils.isEmpty(str))
    {
      char[] arrayOfChar1 = new char[65];
      char[] tmp21_20 = arrayOfChar1;
      tmp21_20[0] = 65;
      char[] tmp26_21 = tmp21_20;
      tmp26_21[1] = 66;
      char[] tmp31_26 = tmp26_21;
      tmp31_26[2] = 67;
      char[] tmp36_31 = tmp31_26;
      tmp36_31[3] = 68;
      char[] tmp41_36 = tmp36_31;
      tmp41_36[4] = 69;
      char[] tmp46_41 = tmp41_36;
      tmp46_41[5] = 70;
      char[] tmp51_46 = tmp46_41;
      tmp51_46[6] = 71;
      char[] tmp57_51 = tmp51_46;
      tmp57_51[7] = 72;
      char[] tmp63_57 = tmp57_51;
      tmp63_57[8] = 73;
      char[] tmp69_63 = tmp63_57;
      tmp69_63[9] = 74;
      char[] tmp75_69 = tmp69_63;
      tmp75_69[10] = 75;
      char[] tmp81_75 = tmp75_69;
      tmp81_75[11] = 76;
      char[] tmp87_81 = tmp81_75;
      tmp87_81[12] = 77;
      char[] tmp93_87 = tmp87_81;
      tmp93_87[13] = 78;
      char[] tmp99_93 = tmp93_87;
      tmp99_93[14] = 79;
      char[] tmp105_99 = tmp99_93;
      tmp105_99[15] = 80;
      char[] tmp111_105 = tmp105_99;
      tmp111_105[16] = 81;
      char[] tmp117_111 = tmp111_105;
      tmp117_111[17] = 82;
      char[] tmp123_117 = tmp117_111;
      tmp123_117[18] = 83;
      char[] tmp129_123 = tmp123_117;
      tmp129_123[19] = 84;
      char[] tmp135_129 = tmp129_123;
      tmp135_129[20] = 85;
      char[] tmp141_135 = tmp135_129;
      tmp141_135[21] = 86;
      char[] tmp147_141 = tmp141_135;
      tmp147_141[22] = 87;
      char[] tmp153_147 = tmp147_141;
      tmp153_147[23] = 88;
      char[] tmp159_153 = tmp153_147;
      tmp159_153[24] = 89;
      char[] tmp165_159 = tmp159_153;
      tmp165_159[25] = 90;
      char[] tmp171_165 = tmp165_159;
      tmp171_165[26] = 97;
      char[] tmp177_171 = tmp171_165;
      tmp177_171[27] = 98;
      char[] tmp183_177 = tmp177_171;
      tmp183_177[28] = 99;
      char[] tmp189_183 = tmp183_177;
      tmp189_183[29] = 100;
      char[] tmp195_189 = tmp189_183;
      tmp195_189[30] = 101;
      char[] tmp201_195 = tmp195_189;
      tmp201_195[31] = 102;
      char[] tmp207_201 = tmp201_195;
      tmp207_201[32] = 103;
      char[] tmp213_207 = tmp207_201;
      tmp213_207[33] = 104;
      char[] tmp219_213 = tmp213_207;
      tmp219_213[34] = 105;
      char[] tmp225_219 = tmp219_213;
      tmp225_219[35] = 106;
      char[] tmp231_225 = tmp225_219;
      tmp231_225[36] = 107;
      char[] tmp237_231 = tmp231_225;
      tmp237_231[37] = 108;
      char[] tmp243_237 = tmp237_231;
      tmp243_237[38] = 109;
      char[] tmp249_243 = tmp243_237;
      tmp249_243[39] = 110;
      char[] tmp255_249 = tmp249_243;
      tmp255_249[40] = 111;
      char[] tmp261_255 = tmp255_249;
      tmp261_255[41] = 112;
      char[] tmp267_261 = tmp261_255;
      tmp267_261[42] = 113;
      char[] tmp273_267 = tmp267_261;
      tmp273_267[43] = 114;
      char[] tmp279_273 = tmp273_267;
      tmp279_273[44] = 115;
      char[] tmp285_279 = tmp279_273;
      tmp285_279[45] = 116;
      char[] tmp291_285 = tmp285_279;
      tmp291_285[46] = 117;
      char[] tmp297_291 = tmp291_285;
      tmp297_291[47] = 118;
      char[] tmp303_297 = tmp297_291;
      tmp303_297[48] = 119;
      char[] tmp309_303 = tmp303_297;
      tmp309_303[49] = 120;
      char[] tmp315_309 = tmp309_303;
      tmp315_309[50] = 121;
      char[] tmp321_315 = tmp315_309;
      tmp321_315[51] = 122;
      char[] tmp327_321 = tmp321_315;
      tmp327_321[52] = 48;
      char[] tmp333_327 = tmp327_321;
      tmp333_327[53] = 49;
      char[] tmp339_333 = tmp333_327;
      tmp339_333[54] = 50;
      char[] tmp345_339 = tmp339_333;
      tmp345_339[55] = 51;
      char[] tmp351_345 = tmp345_339;
      tmp351_345[56] = 52;
      char[] tmp357_351 = tmp351_345;
      tmp357_351[57] = 53;
      char[] tmp363_357 = tmp357_351;
      tmp363_357[58] = 54;
      char[] tmp369_363 = tmp363_357;
      tmp369_363[59] = 55;
      char[] tmp375_369 = tmp369_363;
      tmp375_369[60] = 56;
      char[] tmp381_375 = tmp375_369;
      tmp381_375[61] = 57;
      char[] tmp387_381 = tmp381_375;
      tmp387_381[62] = 43;
      char[] tmp393_387 = tmp387_381;
      tmp393_387[63] = 47;
      char[] tmp399_393 = tmp393_387;
      tmp399_393[64] = 61;
      tmp399_393;
      char[] arrayOfChar2 = str.toCharArray();
      int m = arrayOfChar2.length;
      int j = 0;
      int i = 0;
      int k;
      while (true)
      {
        k = i;
        if (j >= m)
          break;
        int n = arrayOfChar2[j];
        int i1 = arrayOfChar1.length;
        k = 0;
        i = 0;
        while (k < i1)
        {
          if (n == arrayOfChar1[k])
            i = 1;
          k += 1;
        }
        k = i;
        if (i == 0)
          break;
        j += 1;
      }
      if (k != 0)
        return str;
    }
    return "";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.sys.GlobalContext
 * JD-Core Version:    0.6.2
 */