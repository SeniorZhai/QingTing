package com.umeng.message.proguard;

import java.util.Random;

public class q
{
  private static Random b = new Random();
  private String a = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";

  public static String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < 24)
    {
      localStringBuffer.append((char)(b.nextInt(25) + 65));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public String a(String paramString)
  {
    return a.a(this.a, paramString);
  }

  public String a(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = b.b(paramArrayOfByte, 2);
    return a.a(this.a, paramArrayOfByte);
  }

  public String b(String paramString)
  {
    return a.b(this.a, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.q
 * JD-Core Version:    0.6.2
 */