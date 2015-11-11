package com.alipay.sdk.encrypt;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Rsa
{
  public static final String a = "SHA1WithRSA";
  private static final String b = "RSA";

  // ERROR //
  public static String a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 23	java/security/spec/X509EncodedKeySpec
    //   5: dup
    //   6: aload_1
    //   7: invokestatic 28	com/alipay/sdk/encrypt/Base64:a	(Ljava/lang/String;)[B
    //   10: invokespecial 31	java/security/spec/X509EncodedKeySpec:<init>	([B)V
    //   13: astore_1
    //   14: ldc 11
    //   16: invokestatic 37	java/security/KeyFactory:getInstance	(Ljava/lang/String;)Ljava/security/KeyFactory;
    //   19: aload_1
    //   20: invokevirtual 41	java/security/KeyFactory:generatePublic	(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
    //   23: astore_1
    //   24: ldc 43
    //   26: invokestatic 48	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   29: astore_3
    //   30: aload_3
    //   31: iconst_1
    //   32: aload_1
    //   33: invokevirtual 52	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   36: aload_0
    //   37: ldc 54
    //   39: invokevirtual 59	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   42: astore 4
    //   44: aload_3
    //   45: invokevirtual 63	javax/crypto/Cipher:getBlockSize	()I
    //   48: istore 7
    //   50: new 65	java/io/ByteArrayOutputStream
    //   53: dup
    //   54: invokespecial 66	java/io/ByteArrayOutputStream:<init>	()V
    //   57: astore_1
    //   58: iconst_0
    //   59: istore 5
    //   61: aload_1
    //   62: astore_0
    //   63: iload 5
    //   65: aload 4
    //   67: arraylength
    //   68: if_icmpge +52 -> 120
    //   71: aload_1
    //   72: astore_0
    //   73: aload 4
    //   75: arraylength
    //   76: iload 5
    //   78: isub
    //   79: iload 7
    //   81: if_icmpge +130 -> 211
    //   84: aload_1
    //   85: astore_0
    //   86: aload 4
    //   88: arraylength
    //   89: iload 5
    //   91: isub
    //   92: istore 6
    //   94: aload_1
    //   95: astore_0
    //   96: aload_1
    //   97: aload_3
    //   98: aload 4
    //   100: iload 5
    //   102: iload 6
    //   104: invokevirtual 70	javax/crypto/Cipher:doFinal	([BII)[B
    //   107: invokevirtual 73	java/io/ByteArrayOutputStream:write	([B)V
    //   110: iload 5
    //   112: iload 7
    //   114: iadd
    //   115: istore 5
    //   117: goto -56 -> 61
    //   120: aload_1
    //   121: astore_0
    //   122: new 56	java/lang/String
    //   125: dup
    //   126: aload_1
    //   127: invokevirtual 77	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   130: invokestatic 80	com/alipay/sdk/encrypt/Base64:a	([B)Ljava/lang/String;
    //   133: invokespecial 83	java/lang/String:<init>	(Ljava/lang/String;)V
    //   136: astore_2
    //   137: aload_1
    //   138: invokevirtual 86	java/io/ByteArrayOutputStream:close	()V
    //   141: aload_2
    //   142: areturn
    //   143: astore_0
    //   144: aload_0
    //   145: invokevirtual 89	java/io/IOException:printStackTrace	()V
    //   148: aload_2
    //   149: areturn
    //   150: astore_2
    //   151: aconst_null
    //   152: astore_1
    //   153: aload_1
    //   154: astore_0
    //   155: aload_2
    //   156: invokevirtual 90	java/lang/Exception:printStackTrace	()V
    //   159: aload_1
    //   160: ifnull +49 -> 209
    //   163: aload_1
    //   164: invokevirtual 86	java/io/ByteArrayOutputStream:close	()V
    //   167: aconst_null
    //   168: areturn
    //   169: astore_0
    //   170: aload_0
    //   171: invokevirtual 89	java/io/IOException:printStackTrace	()V
    //   174: aconst_null
    //   175: areturn
    //   176: astore_0
    //   177: aload_2
    //   178: astore_1
    //   179: aload_1
    //   180: ifnull +7 -> 187
    //   183: aload_1
    //   184: invokevirtual 86	java/io/ByteArrayOutputStream:close	()V
    //   187: aload_0
    //   188: athrow
    //   189: astore_1
    //   190: aload_1
    //   191: invokevirtual 89	java/io/IOException:printStackTrace	()V
    //   194: goto -7 -> 187
    //   197: astore_2
    //   198: aload_0
    //   199: astore_1
    //   200: aload_2
    //   201: astore_0
    //   202: goto -23 -> 179
    //   205: astore_2
    //   206: goto -53 -> 153
    //   209: aconst_null
    //   210: areturn
    //   211: iload 7
    //   213: istore 6
    //   215: goto -121 -> 94
    //
    // Exception table:
    //   from	to	target	type
    //   137	141	143	java/io/IOException
    //   2	58	150	java/lang/Exception
    //   163	167	169	java/io/IOException
    //   2	58	176	finally
    //   183	187	189	java/io/IOException
    //   63	71	197	finally
    //   73	84	197	finally
    //   86	94	197	finally
    //   96	110	197	finally
    //   122	137	197	finally
    //   155	159	197	finally
    //   63	71	205	java/lang/Exception
    //   73	84	205	java/lang/Exception
    //   86	94	205	java/lang/Exception
    //   96	110	205	java/lang/Exception
    //   122	137	205	java/lang/Exception
  }

  private static boolean a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString3 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.a(paramString3)));
      Signature localSignature = Signature.getInstance("SHA1WithRSA");
      localSignature.initVerify(paramString3);
      localSignature.update(paramString1.getBytes("utf-8"));
      boolean bool = localSignature.verify(Base64.a(paramString2));
      return bool;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }

  private static PublicKey b(String paramString1, String paramString2)
    throws NoSuchAlgorithmException, Exception
  {
    paramString2 = new X509EncodedKeySpec(Base64.a(paramString2));
    return KeyFactory.getInstance(paramString1).generatePublic(paramString2);
  }

  // ERROR //
  private static String c(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 116	java/security/spec/PKCS8EncodedKeySpec
    //   5: dup
    //   6: aload_1
    //   7: invokestatic 28	com/alipay/sdk/encrypt/Base64:a	(Ljava/lang/String;)[B
    //   10: invokespecial 117	java/security/spec/PKCS8EncodedKeySpec:<init>	([B)V
    //   13: astore_1
    //   14: ldc 11
    //   16: invokestatic 37	java/security/KeyFactory:getInstance	(Ljava/lang/String;)Ljava/security/KeyFactory;
    //   19: aload_1
    //   20: invokevirtual 121	java/security/KeyFactory:generatePrivate	(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;
    //   23: astore_1
    //   24: ldc 43
    //   26: invokestatic 48	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   29: astore_3
    //   30: aload_3
    //   31: iconst_2
    //   32: aload_1
    //   33: invokevirtual 52	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   36: aload_0
    //   37: invokestatic 28	com/alipay/sdk/encrypt/Base64:a	(Ljava/lang/String;)[B
    //   40: astore 4
    //   42: aload_3
    //   43: invokevirtual 63	javax/crypto/Cipher:getBlockSize	()I
    //   46: istore 7
    //   48: new 65	java/io/ByteArrayOutputStream
    //   51: dup
    //   52: invokespecial 66	java/io/ByteArrayOutputStream:<init>	()V
    //   55: astore_1
    //   56: iconst_0
    //   57: istore 5
    //   59: aload_1
    //   60: astore_0
    //   61: iload 5
    //   63: aload 4
    //   65: arraylength
    //   66: if_icmpge +52 -> 118
    //   69: aload_1
    //   70: astore_0
    //   71: aload 4
    //   73: arraylength
    //   74: iload 5
    //   76: isub
    //   77: iload 7
    //   79: if_icmpge +127 -> 206
    //   82: aload_1
    //   83: astore_0
    //   84: aload 4
    //   86: arraylength
    //   87: iload 5
    //   89: isub
    //   90: istore 6
    //   92: aload_1
    //   93: astore_0
    //   94: aload_1
    //   95: aload_3
    //   96: aload 4
    //   98: iload 5
    //   100: iload 6
    //   102: invokevirtual 70	javax/crypto/Cipher:doFinal	([BII)[B
    //   105: invokevirtual 73	java/io/ByteArrayOutputStream:write	([B)V
    //   108: iload 5
    //   110: iload 7
    //   112: iadd
    //   113: istore 5
    //   115: goto -56 -> 59
    //   118: aload_1
    //   119: astore_0
    //   120: new 56	java/lang/String
    //   123: dup
    //   124: aload_1
    //   125: invokevirtual 77	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   128: invokespecial 122	java/lang/String:<init>	([B)V
    //   131: astore_2
    //   132: aload_1
    //   133: invokevirtual 86	java/io/ByteArrayOutputStream:close	()V
    //   136: aload_2
    //   137: areturn
    //   138: astore_0
    //   139: aload_0
    //   140: invokevirtual 89	java/io/IOException:printStackTrace	()V
    //   143: aload_2
    //   144: areturn
    //   145: astore_2
    //   146: aconst_null
    //   147: astore_1
    //   148: aload_1
    //   149: astore_0
    //   150: aload_2
    //   151: invokevirtual 90	java/lang/Exception:printStackTrace	()V
    //   154: aload_1
    //   155: ifnull +49 -> 204
    //   158: aload_1
    //   159: invokevirtual 86	java/io/ByteArrayOutputStream:close	()V
    //   162: aconst_null
    //   163: areturn
    //   164: astore_0
    //   165: aload_0
    //   166: invokevirtual 89	java/io/IOException:printStackTrace	()V
    //   169: aconst_null
    //   170: areturn
    //   171: astore_0
    //   172: aload_2
    //   173: astore_1
    //   174: aload_1
    //   175: ifnull +7 -> 182
    //   178: aload_1
    //   179: invokevirtual 86	java/io/ByteArrayOutputStream:close	()V
    //   182: aload_0
    //   183: athrow
    //   184: astore_1
    //   185: aload_1
    //   186: invokevirtual 89	java/io/IOException:printStackTrace	()V
    //   189: goto -7 -> 182
    //   192: astore_2
    //   193: aload_0
    //   194: astore_1
    //   195: aload_2
    //   196: astore_0
    //   197: goto -23 -> 174
    //   200: astore_2
    //   201: goto -53 -> 148
    //   204: aconst_null
    //   205: areturn
    //   206: iload 7
    //   208: istore 6
    //   210: goto -118 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   132	136	138	java/io/IOException
    //   2	56	145	java/lang/Exception
    //   158	162	164	java/io/IOException
    //   2	56	171	finally
    //   178	182	184	java/io/IOException
    //   61	69	192	finally
    //   71	82	192	finally
    //   84	92	192	finally
    //   94	108	192	finally
    //   120	132	192	finally
    //   150	154	192	finally
    //   61	69	200	java/lang/Exception
    //   71	82	200	java/lang/Exception
    //   84	92	200	java/lang/Exception
    //   94	108	200	java/lang/Exception
    //   120	132	200	java/lang/Exception
  }

  private static String d(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new PKCS8EncodedKeySpec(Base64.a(paramString2));
      paramString2 = KeyFactory.getInstance("RSA").generatePrivate(paramString2);
      Signature localSignature = Signature.getInstance("SHA1WithRSA");
      localSignature.initSign(paramString2);
      localSignature.update(paramString1.getBytes("utf-8"));
      paramString1 = Base64.a(localSignature.sign());
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.encrypt.Rsa
 * JD-Core Version:    0.6.2
 */