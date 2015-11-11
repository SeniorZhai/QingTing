package fm.qingting.qtradio.model;

import android.content.Context;
import java.util.List;

public class PullMsgConfig
{
  private static PullMsgConfig _instance;
  private int enablePull;
  private String mCity;
  private Context mContext;
  private List<Node> mLstPullNodes;
  private String mRegion;
  private String phoneType = "android";

  public static PullMsgConfig getInstance()
  {
    if (_instance == null)
      _instance = new PullMsgConfig();
    return _instance;
  }

  public boolean getEnablePull()
  {
    if ((this.enablePull == -1) && (this.mContext != null))
      this.enablePull = GlobalCfg.getInstance(this.mContext).getEnablePull();
    return this.enablePull == 1;
  }

  public List<Node> getLstPullNodes()
  {
    if (this.mLstPullNodes == null)
      restoreFromDB();
    return this.mLstPullNodes;
  }

  public String getPhoneType()
  {
    return this.phoneType;
  }

  public String getPullCity()
  {
    if ((this.mCity == null) && (this.mContext != null))
      this.mCity = GlobalCfg.getInstance(this.mContext).getLocalCity();
    if (this.mCity == null)
      this.mCity = "上海";
    return this.mCity;
  }

  public String getPullRegion()
  {
    if ((this.mRegion == null) && (this.mContext != null))
      this.mRegion = GlobalCfg.getInstance(this.mContext).getLocalRegion();
    if (this.mRegion == null)
      this.mRegion = "上海市";
    return this.mRegion;
  }

  public void restoreFromDB()
  {
  }

  public void setContext(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void writeToDB()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PullMsgConfig
 * JD-Core Version:    0.6.2
 */