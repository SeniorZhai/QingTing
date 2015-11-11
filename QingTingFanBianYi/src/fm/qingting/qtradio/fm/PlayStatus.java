package fm.qingting.qtradio.fm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PlayStatus
  implements Parcelable
{
  public static final int BUFFER = 4098;
  public static final int BUFFER_CUSTOM = 4101;
  public static final int BUFFER_FULL = 4100;
  public static final Parcelable.Creator<PlayStatus> CREATOR = new Parcelable.Creator()
  {
    public PlayStatus createFromParcel(Parcel paramAnonymousParcel)
    {
      return new PlayStatus(paramAnonymousParcel);
    }

    public PlayStatus[] newArray(int paramAnonymousInt)
    {
      return null;
    }
  };
  public static final int DETAIL_MASK = 255;
  public static final int DO_PLAY = 4116;
  public static final int ERROR = 8192;
  public static final int INIT = 30583;
  public static final int PAUSE = 1;
  public static final int PLAY = 4096;
  public static final int PLAYING_MASK = 65280;
  public static final int PLAY_COMPLETE = 2;
  public static final int PLAY_PROGRESS = 4099;
  public static final int PLAY_START = 4097;
  public static final int SEEK_COMPLETE = 4113;
  public static final int SEEK_START = 4112;
  public static final int STOP = 0;
  public static final int WARNING_UNSTABLE = 16384;
  public long bufferLength = 0L;
  public long bufferTime = 0L;
  public long duration = 0L;
  public int state = 0;
  public long time = 0L;

  public PlayStatus()
  {
  }

  public PlayStatus(int paramInt)
  {
    this(paramInt, 0L, 0L);
  }

  public PlayStatus(int paramInt, long paramLong1, long paramLong2)
  {
    this(paramInt, paramLong1, paramLong2, 0L, 0L);
  }

  public PlayStatus(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    setPlayStatus(paramInt, paramLong1, paramLong2, paramLong3, paramLong4);
  }

  public PlayStatus(long paramLong1, long paramLong2)
  {
    this(4098, 0L, 0L, paramLong1, paramLong2);
  }

  public PlayStatus(Parcel paramParcel)
  {
    this.state = paramParcel.readInt();
    this.duration = paramParcel.readLong();
    this.time = paramParcel.readLong();
    this.bufferTime = paramParcel.readLong();
    this.bufferLength = paramParcel.readLong();
  }

  public PlayStatus clone()
  {
    return new PlayStatus(this.state, this.duration, this.time, this.bufferTime, this.bufferLength);
  }

  public int describeContents()
  {
    return 0;
  }

  public int getPlayingState()
  {
    return this.state & 0xFF00;
  }

  public int getState()
  {
    return this.state;
  }

  public void setPlayStatus(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    this.state = paramInt;
    this.duration = paramLong1;
    this.time = paramLong2;
    this.bufferTime = paramLong3;
    this.bufferLength = paramLong4;
  }

  public String toString()
  {
    String str1 = "unknown";
    String str2 = "";
    switch (this.state)
    {
    default:
    case 0:
    case 1:
    case 4096:
    case 4097:
    case 4098:
    case 4100:
    case 2:
    case 4112:
    case 4113:
    case 8192:
    case 4099:
    }
    while (true)
    {
      return String.format("%s %s", new Object[] { str1, str2 });
      str1 = "stop";
      continue;
      str1 = "pause";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "play";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "play_start";
      continue;
      str1 = "buffer";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "buffer_full";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "play_complete";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "seek_start";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "seek_complete";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "error";
      str2 = String.format("time:%d, duration:%d, bufferLength:%d, bufferTime:%d", new Object[] { Long.valueOf(this.time), Long.valueOf(this.duration), Long.valueOf(this.bufferLength), Long.valueOf(this.bufferTime) });
      continue;
      str1 = "play_progress";
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.state);
    paramParcel.writeLong(this.duration);
    paramParcel.writeLong(this.time);
    paramParcel.writeLong(this.bufferTime);
    paramParcel.writeLong(this.bufferLength);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fm.PlayStatus
 * JD-Core Version:    0.6.2
 */