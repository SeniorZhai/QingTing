package fm.qingting.websocket;

import android.annotation.TargetApi;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class HybiParser
{
  private static final int BYTE = 255;
  private static final int FIN = 128;
  private static final List<Integer> FRAGMENTED_OPCODES = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2) });
  private static final int LENGTH = 127;
  private static final int MASK = 128;
  private static final int MODE_BINARY = 2;
  private static final int MODE_TEXT = 1;
  private static final int OPCODE = 15;
  private static final List<Integer> OPCODES = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10) });
  private static final int OP_BINARY = 2;
  private static final int OP_CLOSE = 8;
  private static final int OP_CONTINUATION = 0;
  private static final int OP_PING = 9;
  private static final int OP_PONG = 10;
  private static final int OP_TEXT = 1;
  private static final int RSV1 = 64;
  private static final int RSV2 = 32;
  private static final int RSV3 = 16;
  private static final String TAG = "HybiParser";
  private ByteArrayOutputStream mBuffer = new ByteArrayOutputStream();
  private WebSocketClient mClient;
  private boolean mClosed = false;
  private boolean mFinal;
  private int mLength;
  private int mLengthSize;
  private byte[] mMask = new byte[0];
  private boolean mMasked;
  private boolean mMasking = true;
  private int mMode;
  private int mOpcode;
  private byte[] mPayload = new byte[0];
  private int mStage;

  public HybiParser(WebSocketClient paramWebSocketClient)
  {
    this.mClient = paramWebSocketClient;
  }

  private static long byteArrayToLong(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte.length < paramInt2)
      throw new IllegalArgumentException("length must be less than or equal to b.length");
    long l = 0L;
    int i = 0;
    while (i < paramInt2)
    {
      l += ((paramArrayOfByte[(i + paramInt1)] & 0xFF) << (paramInt2 - 1 - i) * 8);
      i += 1;
    }
    return l;
  }

  private byte[] decode(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException(paramString);
  }

  private void emitFrame()
    throws IOException
  {
    int i = 0;
    Object localObject = mask(this.mPayload, this.mMask, 0);
    int j = this.mOpcode;
    if (j == 0)
    {
      if (this.mMode == 0)
        throw new ProtocolError("Mode was not set.");
      this.mBuffer.write((byte[])localObject);
      if (this.mFinal)
      {
        localObject = this.mBuffer.toByteArray();
        if (this.mMode != 1)
          break label94;
        this.mClient.getListener().onMessage(encode((byte[])localObject));
        reset();
      }
    }
    label94: 
    do
    {
      return;
      this.mClient.getListener().onMessage((byte[])localObject);
      break;
      if (j == 1)
      {
        if (this.mFinal)
        {
          localObject = encode((byte[])localObject);
          this.mClient.getListener().onMessage((String)localObject);
          return;
        }
        this.mMode = 1;
        this.mBuffer.write((byte[])localObject);
        return;
      }
      if (j == 2)
      {
        if (this.mFinal)
        {
          this.mClient.getListener().onMessage((byte[])localObject);
          return;
        }
        this.mMode = 2;
        this.mBuffer.write((byte[])localObject);
        return;
      }
      if (j == 8)
      {
        if (localObject.length >= 2)
          i = localObject[0] * 256 + localObject[1];
        if (localObject.length > 2);
        for (localObject = encode(slice((byte[])localObject, 2)); ; localObject = null)
        {
          this.mClient.getListener().onDisconnect(i, (String)localObject);
          return;
        }
      }
      if (j == 9)
      {
        if (localObject.length > 125)
          throw new ProtocolError("Ping payload too large");
        this.mClient.sendFrame(frame((byte[])localObject, 10, -1));
        return;
      }
    }
    while (j != 10);
    encode((byte[])localObject);
  }

  private String encode(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, "UTF-8");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw new RuntimeException(paramArrayOfByte);
  }

  private byte[] frame(Object paramObject, int paramInt1, int paramInt2)
  {
    if (this.mClosed)
      return null;
    int j;
    label32: int m;
    int i;
    label49: int k;
    label59: int n;
    label78: byte[] arrayOfByte;
    if ((paramObject instanceof String))
    {
      paramObject = decode((String)paramObject);
      if (paramInt2 <= 0)
        break label270;
      j = 2;
      m = paramObject.length + j;
      if (m > 125)
        break label276;
      i = 2;
      if (!this.mMasking)
        break label296;
      k = 4;
      n = i + k;
      if (!this.mMasking)
        break label302;
      k = 128;
      arrayOfByte = new byte[m + n];
      arrayOfByte[0] = ((byte)((byte)paramInt1 | 0xFFFFFF80));
      if (m > 125)
        break label308;
      arrayOfByte[1] = ((byte)(k | m));
    }
    while (true)
    {
      if (paramInt2 > 0)
      {
        arrayOfByte[n] = ((byte)((int)Math.floor(paramInt2 / 256) & 0xFF));
        arrayOfByte[(n + 1)] = ((byte)(paramInt2 & 0xFF));
      }
      System.arraycopy(paramObject, 0, arrayOfByte, j + n, paramObject.length);
      if (this.mMasking)
      {
        paramObject = new byte[4];
        paramObject[0] = ((byte)(int)Math.floor(Math.random() * 256.0D));
        paramObject[1] = ((byte)(int)Math.floor(Math.random() * 256.0D));
        paramObject[2] = ((byte)(int)Math.floor(Math.random() * 256.0D));
        paramObject[3] = ((byte)(int)Math.floor(Math.random() * 256.0D));
        System.arraycopy(paramObject, 0, arrayOfByte, i, paramObject.length);
        mask(arrayOfByte, paramObject, n);
      }
      return arrayOfByte;
      paramObject = (byte[])paramObject;
      break;
      label270: j = 0;
      break label32;
      label276: if (m <= 65535)
      {
        i = 4;
        break label49;
      }
      i = 10;
      break label49;
      label296: k = 0;
      break label59;
      label302: k = 0;
      break label78;
      label308: if (m <= 65535)
      {
        arrayOfByte[1] = ((byte)(k | 0x7E));
        arrayOfByte[2] = ((byte)(int)Math.floor(m / 256));
        arrayOfByte[3] = ((byte)(m & 0xFF));
      }
      else
      {
        arrayOfByte[1] = ((byte)(k | 0x7F));
        arrayOfByte[2] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 56.0D)) & 0xFF));
        arrayOfByte[3] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 48.0D)) & 0xFF));
        arrayOfByte[4] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 40.0D)) & 0xFF));
        arrayOfByte[5] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 32.0D)) & 0xFF));
        arrayOfByte[6] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 24.0D)) & 0xFF));
        arrayOfByte[7] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 16.0D)) & 0xFF));
        arrayOfByte[8] = ((byte)((int)Math.floor(m / Math.pow(2.0D, 8.0D)) & 0xFF));
        arrayOfByte[9] = ((byte)(m & 0xFF));
      }
    }
  }

  private byte[] frame(String paramString, int paramInt1, int paramInt2)
  {
    return frame(paramString, paramInt1, paramInt2);
  }

  private byte[] frame(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return frame(paramArrayOfByte, paramInt1, paramInt2);
  }

  private int getInteger(byte[] paramArrayOfByte)
    throws HybiParser.ProtocolError
  {
    long l = byteArrayToLong(paramArrayOfByte, 0, paramArrayOfByte.length);
    if ((l < 0L) || (l > 2147483647L))
      throw new ProtocolError("Bad integer: " + l);
    return (int)l;
  }

  private static byte[] mask(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if (paramArrayOfByte2.length == 0);
    while (true)
    {
      return paramArrayOfByte1;
      int i = 0;
      while (i < paramArrayOfByte1.length - paramInt)
      {
        paramArrayOfByte1[(paramInt + i)] = ((byte)(paramArrayOfByte1[(paramInt + i)] ^ paramArrayOfByte2[(i % 4)]));
        i += 1;
      }
    }
  }

  private void parseExtendedLength(byte[] paramArrayOfByte)
    throws HybiParser.ProtocolError
  {
    this.mLength = getInteger(paramArrayOfByte);
    if (this.mMasked);
    for (int i = 3; ; i = 4)
    {
      this.mStage = i;
      return;
    }
  }

  private void parseLength(byte paramByte)
  {
    boolean bool;
    if ((paramByte & 0x80) == 128)
    {
      bool = true;
      this.mMasked = bool;
      this.mLength = (paramByte & 0x7F);
      if ((this.mLength < 0) || (this.mLength > 125))
        break label67;
      if (!this.mMasked)
        break label62;
    }
    label62: for (paramByte = 3; ; paramByte = 4)
    {
      this.mStage = paramByte;
      return;
      bool = false;
      break;
    }
    label67: if (this.mLength == 126);
    for (paramByte = 2; ; paramByte = 8)
    {
      this.mLengthSize = paramByte;
      this.mStage = 2;
      return;
    }
  }

  private void parseOpcode(byte paramByte)
    throws HybiParser.ProtocolError
  {
    int i;
    int j;
    if ((paramByte & 0x40) == 64)
    {
      i = 1;
      if ((paramByte & 0x20) != 32)
        break label63;
      j = 1;
      label22: if ((paramByte & 0x10) != 16)
        break label68;
    }
    label63: label68: for (int k = 1; ; k = 0)
    {
      if ((i == 0) && (j == 0) && (k == 0))
        break label74;
      throw new ProtocolError("RSV not zero");
      i = 0;
      break;
      j = 0;
      break label22;
    }
    label74: if ((paramByte & 0x80) == 128);
    for (boolean bool = true; ; bool = false)
    {
      this.mFinal = bool;
      this.mOpcode = (paramByte & 0xF);
      this.mMask = new byte[0];
      this.mPayload = new byte[0];
      if (OPCODES.contains(Integer.valueOf(this.mOpcode)))
        break;
      throw new ProtocolError("Bad opcode");
    }
    if ((!FRAGMENTED_OPCODES.contains(Integer.valueOf(this.mOpcode))) && (!this.mFinal))
      throw new ProtocolError("Expected non-final packet");
    this.mStage = 1;
  }

  private void reset()
  {
    this.mMode = 0;
    this.mBuffer.reset();
  }

  @TargetApi(9)
  private byte[] slice(byte[] paramArrayOfByte, int paramInt)
  {
    return Arrays.copyOfRange(paramArrayOfByte, paramInt, paramArrayOfByte.length);
  }

  public void close(int paramInt, String paramString)
  {
    if (this.mClosed)
      return;
    this.mClient.send(frame(paramString, 8, paramInt));
    this.mClosed = true;
  }

  public byte[] frame(String paramString)
  {
    return frame(paramString, 1, -1);
  }

  public byte[] frame(byte[] paramArrayOfByte)
  {
    return frame(paramArrayOfByte, 2, -1);
  }

  public void ping(String paramString)
  {
    this.mClient.send(frame(paramString, 9, -1));
  }

  public void start(HappyDataInputStream paramHappyDataInputStream)
    throws IOException
  {
    while (true)
    {
      if (paramHappyDataInputStream.available() == -1)
      {
        this.mClient.getListener().onDisconnect(0, "EOF");
        return;
      }
      switch (this.mStage)
      {
      default:
        break;
      case 0:
        parseOpcode(paramHappyDataInputStream.readByte());
        break;
      case 1:
        parseLength(paramHappyDataInputStream.readByte());
        break;
      case 2:
        parseExtendedLength(paramHappyDataInputStream.readBytes(this.mLengthSize));
        break;
      case 3:
        this.mMask = paramHappyDataInputStream.readBytes(4);
        this.mStage = 4;
        break;
      case 4:
      }
      this.mPayload = paramHappyDataInputStream.readBytes(this.mLength);
      emitFrame();
      this.mStage = 0;
    }
  }

  public static class HappyDataInputStream extends DataInputStream
  {
    public HappyDataInputStream(InputStream paramInputStream)
    {
      super();
    }

    public byte[] readBytes(int paramInt)
      throws IOException
    {
      byte[] arrayOfByte = new byte[paramInt];
      readFully(arrayOfByte);
      return arrayOfByte;
    }
  }

  public static class ProtocolError extends IOException
  {
    public ProtocolError(String paramString)
    {
      super();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.websocket.HybiParser
 * JD-Core Version:    0.6.2
 */