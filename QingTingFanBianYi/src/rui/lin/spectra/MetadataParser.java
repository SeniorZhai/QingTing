package rui.lin.spectra;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class MetadataParser
{
  public static final HashMap<String, String> ID3V2_TAGS = new HashMap()
  {
  };
  protected static int sLogLevel = 4;
  protected static String sLogTag = "MetadataParser";
  protected static Logger sLogger = new Logger(sLogLevel, sLogTag);

  public static HashMap<String, Vector<String>> parseID3v2(byte[] paramArrayOfByte)
  {
    int i = 1;
    if ((paramArrayOfByte != null) && (paramArrayOfByte[0] == 73) && (paramArrayOfByte[1] == 68) && (paramArrayOfByte[2] == 51));
    int j;
    int k;
    byte[] arrayOfByte;
    label150: HashMap localHashMap;
    label161: Object localObject1;
    int m;
    int n;
    label320: Object localObject2;
    while (true)
    {
      try
      {
        sLogger.info("ID3v2 mark detected", new Object[0]);
        if ((paramArrayOfByte[3] >= 3) && (paramArrayOfByte[3] <= 4))
        {
          sLogger.info("ID3v2 version %d", new Object[] { Byte.valueOf(paramArrayOfByte[3]) });
          j = paramArrayOfByte[6] << 21 | paramArrayOfByte[7] << 14 | paramArrayOfByte[8] << 7 | paramArrayOfByte[9];
          if ((paramArrayOfByte[5] & 0x80) == 0)
            break label926;
          k = 1;
          break label818;
          arrayOfByte = new byte[j];
          if (k != 0)
          {
            k = 0;
            break label891;
          }
          System.arraycopy(paramArrayOfByte, i, arrayOfByte, 0, j);
          localHashMap = new HashMap();
          i = 0;
          if ((j - i <= 10) || (arrayOfByte[i] < 65) || (arrayOfByte[i] > 90))
            break;
          localObject1 = new String(arrayOfByte, i, 4);
          k = arrayOfByte[(i + 4)];
          m = arrayOfByte[(i + 5)];
          n = arrayOfByte[(i + 6)];
          k = arrayOfByte[(i + 7)] | (k << 21 | m << 14 | n << 7);
          sLogger.info("TAG %s found with %d byte payload", new Object[] { localObject1, Integer.valueOf(k) });
          if (!ID3V2_TAGS.containsKey(localObject1))
            break label968;
          switch (arrayOfByte[i])
          {
          case 87:
            if (paramArrayOfByte == null)
              break label968;
            if (!((String)localObject1).equals("TXXX"))
              break label462;
            localObject2 = paramArrayOfByte.decode(ByteBuffer.wrap(arrayOfByte, i + 11, k - 1)).toString().split("");
            paramArrayOfByte = (String)ID3V2_TAGS.get(localObject1) + localObject2[0];
            localObject1 = new Vector(1);
            ((Vector)localObject1).add(localObject2[1]);
            localHashMap.put(paramArrayOfByte, localObject1);
          case 85:
          case 86:
          default:
          case 84:
          }
        }
      }
      catch (Exception paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
      }
      return null;
      paramArrayOfByte = Charset.forName("ISO-8859-1");
      continue;
      paramArrayOfByte = Charset.forName("UTF-16");
      continue;
      paramArrayOfByte = Charset.forName("UTF-16BE");
      continue;
      paramArrayOfByte = Charset.forName("UTF-8");
      continue;
      label462: localObject2 = paramArrayOfByte.decode(ByteBuffer.wrap(arrayOfByte, i + 11, k - 1)).toString().split("");
      paramArrayOfByte = (String)ID3V2_TAGS.get(localObject1);
      localObject1 = new Vector(Arrays.asList((Object[])localObject2));
    }
    if (((String)localObject1).equals("WXXX"))
      switch (arrayOfByte[(i + 10)])
      {
      case 0:
      case 1:
      case 2:
      case 3:
      }
    while (true)
    {
      if (paramArrayOfByte != null)
      {
        localObject2 = paramArrayOfByte.decode(ByteBuffer.wrap(arrayOfByte, i + 11, k - 1)).toString();
        m = ((String)localObject2).indexOf("");
        String str = ((String)localObject2).substring(0, m);
        m = paramArrayOfByte.encode(((String)localObject2).substring(0, m + 1)).limit();
        paramArrayOfByte = (String)ID3V2_TAGS.get(localObject1) + str;
        localObject1 = new Vector(1);
        ((Vector)localObject1).add(Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(arrayOfByte, i + 11 + m, k - 1 - m)).toString());
        localHashMap.put(paramArrayOfByte, localObject1);
        break label968;
        paramArrayOfByte = Charset.forName("ISO-8859-1");
        continue;
        paramArrayOfByte = Charset.forName("UTF-16");
        continue;
        paramArrayOfByte = Charset.forName("UTF-16BE");
        continue;
        paramArrayOfByte = Charset.forName("UTF-8");
        continue;
        paramArrayOfByte = Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(arrayOfByte, i + 10, k)).toString().split("");
        localObject1 = (String)ID3V2_TAGS.get(localObject1);
        localObject2 = new Vector(1);
        ((Vector)localObject2).add(paramArrayOfByte[0]);
        localHashMap.put(localObject1, localObject2);
        break label968;
        return localHashMap;
        label818: if ((paramArrayOfByte[5] & 0x40) != 0)
        {
          label827: if (i == 0)
            break label938;
          i = paramArrayOfByte[10];
          m = paramArrayOfByte[11];
          n = paramArrayOfByte[12];
          i = paramArrayOfByte[13] | (i << 21 | m << 14 | n << 7);
          j -= i;
          i += 10;
          break;
        }
        while (true)
        {
          label891: if (i >= j)
            break label150;
          if ((paramArrayOfByte[i] == 0) && (paramArrayOfByte[(i - 1)] == 255))
          {
            i += 1;
            continue;
            label926: k = 0;
            break label818;
            i = 0;
            break label827;
            label938: i = 10;
            break;
          }
          arrayOfByte[k] = paramArrayOfByte[i];
          k += 1;
          i += 1;
        }
      }
      label968: i = k + 10 + i;
      break label161;
      switch (arrayOfByte[(i + 10)])
      {
      case 0:
      case 1:
      case 2:
      case 3:
      }
      paramArrayOfByte = null;
      break label320;
      paramArrayOfByte = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     rui.lin.spectra.MetadataParser
 * JD-Core Version:    0.6.2
 */