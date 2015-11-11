package fm.qingting.qtradio.view.playview;

import android.content.Context;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.view.QtViewPager;
import java.util.Calendar;
import java.util.List;

class TraScheduleViewPager extends QtViewPager
{
  private static final String[] DATE = { "昨天", "今天", "明天" };
  private ChannelNode mChannelNode;

  public TraScheduleViewPager(Context paramContext)
  {
    super(paramContext);
    setTabBackgroundColor(0);
    setTabHasVerticalLine(false);
    setTabLineColor(872415231);
    setTabNormalTextColor(SkinManager.getNewPlaySubColor());
  }

  private List<ProgramNode> getProgramList(int paramInt)
  {
    if (this.mChannelNode == null)
      return null;
    return this.mChannelNode.getLstProgramNode(paramInt);
  }

  protected boolean enableSlide()
  {
    return true;
  }

  protected IView generateView(int paramInt)
  {
    return new TraScheduleListView(getContext());
  }

  protected int getSubViewCnt()
  {
    return 3;
  }

  protected String getTab(int paramInt)
  {
    return DATE[paramInt];
  }

  public void setNode(ChannelNode paramChannelNode)
  {
    this.mChannelNode = paramChannelNode;
  }

  protected void setSubViewData(IView paramIView, int paramInt)
  {
    int j = Calendar.getInstance().get(7);
    int i = j;
    if (j == 0)
      i = 7;
    int k = i - 1;
    j = k;
    if (k < 1)
      j = k + 7;
    int m = i + 1;
    k = m;
    if (m > 7)
      k = m - 7;
    if (paramInt == 0);
    while (true)
    {
      paramIView.update("setData", getProgramList(j));
      return;
      if (paramInt == 1)
        j = i;
      else
        j = k;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.TraScheduleViewPager
 * JD-Core Version:    0.6.2
 */