package fm.qingting.async.http.filter;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.PushParser;
import fm.qingting.async.TapCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.http.libcore.Memory;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public class GZIPInputFilter extends InflaterInputFilter
{
  private static final int FCOMMENT = 16;
  private static final int FEXTRA = 4;
  private static final int FHCRC = 2;
  private static final int FNAME = 8;
  protected CRC32 crc = new CRC32();
  boolean mNeedsHeader = true;

  public GZIPInputFilter()
  {
    super(new Inflater(true));
  }

  public static int unsignedToBytes(byte paramByte)
  {
    return paramByte & 0xFF;
  }

  public void onDataAvailable(final DataEmitter paramDataEmitter, final ByteBufferList paramByteBufferList)
  {
    if (this.mNeedsHeader)
    {
      paramByteBufferList = new PushParser(paramDataEmitter);
      paramByteBufferList.readBuffer(10).tap(new TapCallback()
      {
        int flags;
        boolean hcrc;

        private void next()
        {
          PushParser localPushParser = new PushParser(paramDataEmitter);
          DataCallback local2 = new DataCallback()
          {
            public void onDataAvailable(DataEmitter paramAnonymous2DataEmitter, ByteBufferList paramAnonymous2ByteBufferList)
            {
              if (GZIPInputFilter.1.this.hcrc)
                while (paramAnonymous2ByteBufferList.size() > 0)
                {
                  paramAnonymous2DataEmitter = paramAnonymous2ByteBufferList.remove();
                  GZIPInputFilter.this.crc.update(paramAnonymous2DataEmitter.array(), paramAnonymous2DataEmitter.arrayOffset() + paramAnonymous2DataEmitter.position(), paramAnonymous2DataEmitter.remaining());
                }
            }
          };
          if ((this.flags & 0x8) != 0)
            localPushParser.until((byte)0, local2);
          if ((this.flags & 0x10) != 0)
            localPushParser.until((byte)0, local2);
          if (this.hcrc)
            localPushParser.readBuffer(2);
          while (true)
          {
            localPushParser.tap(new TapCallback()
            {
              public void tap(byte[] paramAnonymous2ArrayOfByte)
              {
                if (paramAnonymous2ArrayOfByte != null)
                {
                  int i = Memory.peekShort(paramAnonymous2ArrayOfByte, 0, ByteOrder.LITTLE_ENDIAN);
                  if ((short)(int)GZIPInputFilter.this.crc.getValue() != i)
                  {
                    GZIPInputFilter.this.report(new IOException("CRC mismatch"));
                    return;
                  }
                  GZIPInputFilter.this.crc.reset();
                }
                GZIPInputFilter.this.mNeedsHeader = false;
                GZIPInputFilter.this.setDataEmitter(GZIPInputFilter.1.this.val$emitter);
              }
            });
            return;
            localPushParser.noop();
          }
        }

        public void tap(byte[] paramAnonymousArrayOfByte)
        {
          boolean bool = true;
          short s = Memory.peekShort(paramAnonymousArrayOfByte, 0, ByteOrder.LITTLE_ENDIAN);
          if (s != -29921)
          {
            GZIPInputFilter.this.report(new IOException(String.format("unknown format (magic number %x)", new Object[] { Short.valueOf(s) })));
            paramDataEmitter.setDataCallback(new NullDataCallback());
            return;
          }
          this.flags = paramAnonymousArrayOfByte[3];
          if ((this.flags & 0x2) != 0);
          while (true)
          {
            this.hcrc = bool;
            if (this.hcrc)
              GZIPInputFilter.this.crc.update(paramAnonymousArrayOfByte, 0, paramAnonymousArrayOfByte.length);
            if ((this.flags & 0x4) != 0)
              paramByteBufferList.readBuffer(2).tap(new TapCallback()
              {
                public void tap(byte[] paramAnonymous2ArrayOfByte)
                {
                  if (GZIPInputFilter.1.this.hcrc)
                    GZIPInputFilter.this.crc.update(paramAnonymous2ArrayOfByte, 0, 2);
                  int i = Memory.peekShort(paramAnonymous2ArrayOfByte, 0, ByteOrder.LITTLE_ENDIAN);
                  GZIPInputFilter.1.this.val$parser.readBuffer(i & 0xFFFF).tap(new TapCallback()
                  {
                    public void tap(byte[] paramAnonymous3ArrayOfByte)
                    {
                      if (GZIPInputFilter.1.this.hcrc)
                        GZIPInputFilter.this.crc.update(paramAnonymous3ArrayOfByte, 0, paramAnonymous3ArrayOfByte.length);
                      GZIPInputFilter.1.this.next();
                    }
                  });
                }
              });
            next();
            return;
            bool = false;
          }
        }
      });
      return;
    }
    super.onDataAvailable(paramDataEmitter, paramByteBufferList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.filter.GZIPInputFilter
 * JD-Core Version:    0.6.2
 */