package fm.qingting.utils;

import android.util.SparseIntArray;
import java.util.HashMap;
import java.util.Map;

public enum RecommendStatisticsUtil
{
  INSTANCE;

  private boolean mPause = false;
  private Map<Integer, SparseIntArray> mapBannerIntArray = new HashMap();
  private Map<Integer, SparseIntArray> mapIntArray = new HashMap();

  public void addBannerData(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      return;
    if (this.mapBannerIntArray.get(Integer.valueOf(paramInt2)) == null)
    {
      localSparseIntArray = new SparseIntArray();
      localSparseIntArray.put(paramInt1, localSparseIntArray.get(paramInt1) + 1);
      this.mapBannerIntArray.put(Integer.valueOf(paramInt2), localSparseIntArray);
      return;
    }
    SparseIntArray localSparseIntArray = (SparseIntArray)this.mapBannerIntArray.get(Integer.valueOf(paramInt2));
    localSparseIntArray.put(paramInt1, localSparseIntArray.get(paramInt1) + 1);
  }

  public void addValidData(int paramInt1, int paramInt2)
  {
    if (this.mPause);
    while (paramInt1 < 0)
      return;
    if (this.mapIntArray.get(Integer.valueOf(paramInt2)) == null)
    {
      localSparseIntArray = new SparseIntArray();
      localSparseIntArray.put(paramInt1, localSparseIntArray.get(paramInt1) + 1);
      this.mapIntArray.put(Integer.valueOf(paramInt2), localSparseIntArray);
      return;
    }
    SparseIntArray localSparseIntArray = (SparseIntArray)this.mapIntArray.get(Integer.valueOf(paramInt2));
    localSparseIntArray.put(paramInt1, localSparseIntArray.get(paramInt1) + 1);
  }

  public void pause()
  {
    this.mPause = true;
  }

  public void resume()
  {
    this.mPause = false;
  }

  public void sendLog()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.RecommendStatisticsUtil
 * JD-Core Version:    0.6.2
 */