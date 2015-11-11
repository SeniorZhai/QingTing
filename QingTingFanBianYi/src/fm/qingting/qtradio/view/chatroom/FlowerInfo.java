package fm.qingting.qtradio.view.chatroom;

import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.UserInfo;
import java.util.HashMap;
import java.util.Map;

public class FlowerInfo
{
  public static final int FLOWER_INTERVAL = 7200000;
  public static final int MAX_FLOWER_CNT = 10;
  private static long MAX_FLOWER_INTERVAL = 10L;
  private static long mLastFlowerTime;
  private static Map<String, Integer> mLeftFlowerCnt = new HashMap();
  private static String mRoomId;

  static
  {
    mLastFlowerTime = 0L;
  }

  public static boolean allowFlower(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null);
    long l;
    do
    {
      return false;
      l = System.currentTimeMillis();
    }
    while ((l - mLastFlowerTime) / 1000L <= MAX_FLOWER_INTERVAL);
    mLastFlowerTime = l;
    return true;
  }

  public static void checkFlowerCnt(String paramString)
  {
    mRoomId = paramString;
    long l = SharedCfg.getInstance().getFlowerChangeTime(mRoomId);
    int j = (int)((System.currentTimeMillis() - l) / 7200000L);
    int i = j;
    if (j < 0)
      i = 0;
    if (increaseFlowerCnt(i) < 10)
      SharedCfg.getInstance().updateFlowerChangeTime(mRoomId, l + i * 7200000);
  }

  public static int decreaseFlowerCnt()
  {
    if (mRoomId == null);
    while (!mLeftFlowerCnt.containsKey(mRoomId))
      return 0;
    int i = ((Integer)mLeftFlowerCnt.get(mRoomId)).intValue();
    if (i == 10)
      SharedCfg.getInstance().updateFlowerChangeTime(mRoomId, System.currentTimeMillis());
    int j = i - 1;
    i = j;
    if (j < 0)
      i = 0;
    mLeftFlowerCnt.put(mRoomId, Integer.valueOf(i));
    SharedCfg.getInstance().updateFlowerCnt(mRoomId, i);
    return i;
  }

  public static int getFlowerCnt()
  {
    int i;
    if (mRoomId == null)
      i = 10;
    int j;
    do
    {
      return i;
      if (!mLeftFlowerCnt.containsKey(mRoomId))
        initFlowerCnt();
      j = ((Integer)mLeftFlowerCnt.get(mRoomId)).intValue();
      i = j;
    }
    while (j >= 0);
    return 0;
  }

  public static int increaseFlowerCnt(int paramInt)
  {
    int i = 10;
    if (mRoomId == null)
      return 0;
    int j = getFlowerCnt();
    if (paramInt == 0)
      return j;
    paramInt = j + paramInt;
    if (paramInt >= 10)
      paramInt = i;
    while (true)
    {
      mLeftFlowerCnt.put(mRoomId, Integer.valueOf(paramInt));
      SharedCfg.getInstance().updateFlowerCnt(mRoomId, paramInt);
      return paramInt;
    }
  }

  public static void initFlowerCnt()
  {
    int j = 10;
    int k = SharedCfg.getInstance().getFlowerCnt(mRoomId);
    int i = k;
    if (k < 0)
      i = 0;
    if (i > 10)
      i = j;
    while (true)
    {
      mLeftFlowerCnt.put(mRoomId, Integer.valueOf(i));
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.FlowerInfo
 * JD-Core Version:    0.6.2
 */