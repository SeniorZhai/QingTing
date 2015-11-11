package fm.qingting.framework.utils;

public class PinYinUtils
{
  private static final int GB_SP_DIFF = 160;
  private static final char[] firstLetter = { 97, 98, 99, 100, 101, 102, 103, 104, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 119, 120, 121, 122 };
  private static final int[] secPosvalueList = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

  private static char convert(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j;
    if (i >= paramArrayOfByte.length)
    {
      j = paramArrayOfByte[0] * 100 + paramArrayOfByte[1];
      if ((j >= secPosvalueList[0]) && (j < secPosvalueList[23]))
        i = 0;
    }
    while (true)
    {
      if (i >= 23)
      {
        return '-';
        paramArrayOfByte[i] = ((byte)(paramArrayOfByte[i] - 160));
        i += 1;
        break;
      }
      if ((j >= secPosvalueList[i]) && (j < secPosvalueList[(i + 1)]))
        return firstLetter[i];
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.PinYinUtils
 * JD-Core Version:    0.6.2
 */