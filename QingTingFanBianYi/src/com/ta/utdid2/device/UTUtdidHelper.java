package com.ta.utdid2.device;

import com.ta.utdid2.android.utils.AESUtils;
import com.ta.utdid2.android.utils.Base64;
import java.util.Random;

public class UTUtdidHelper
{
  private static Random random = new Random();
  private String mAESKey = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";

  public static String generateRandomUTDID()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < 24)
    {
      localStringBuffer.append((char)(random.nextInt(25) + 65));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public String dePack(String paramString)
  {
    return AESUtils.decrypt(this.mAESKey, paramString);
  }

  public String pack(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = Base64.encodeToString(paramArrayOfByte, 2);
    return AESUtils.encrypt(this.mAESKey, paramArrayOfByte);
  }

  public String packUtdidStr(String paramString)
  {
    return AESUtils.encrypt(this.mAESKey, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.device.UTUtdidHelper
 * JD-Core Version:    0.6.2
 */