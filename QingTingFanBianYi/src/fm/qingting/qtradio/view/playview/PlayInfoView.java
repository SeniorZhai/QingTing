package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.ProgramNode;
import java.util.List;

public class PlayInfoView extends QtView
{
  private final ViewLayout infoLayout = this.standardLayout.createChildLT(700, 50, 10, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mInfoElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 744, 720, 744, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(700, 64, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public PlayInfoView(Context paramContext)
  {
    super(paramContext);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setColor(-1);
    this.mTitleElement.setText("祝您开心每一天！", false);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    addElement(this.mTitleElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setMaxLineLimit(1);
    this.mInfoElement.setColor(SkinManager.getNewPlaySubColor());
    this.mInfoElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    addElement(this.mInfoElement);
  }

  private String getBroadCasters(List<BroadcasterNode> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return "";
    StringBuilder localStringBuilder = new StringBuilder("主播");
    int i = 0;
    while (i < paramList.size())
    {
      localStringBuilder.append(" " + ((BroadcasterNode)paramList.get(i)).nick);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public void close(boolean paramBoolean)
  {
    super.close(paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.infoLayout.scaleToBounds(this.standardLayout);
    paramInt1 = (this.standardLayout.height - this.titleLayout.height - this.infoLayout.getBottom()) / 2;
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getLargeTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mTitleElement.setTranslationY(paramInt1);
    this.mInfoElement.setTranslationY(paramInt1 + this.titleLayout.height);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setNode"))
    {
      paramString = (ProgramNode)paramObject;
      this.mTitleElement.setText(paramString.title);
      paramString = paramString.lstBroadcaster;
      this.mInfoElement.setText(getBroadCasters(paramString));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.PlayInfoView
 * JD-Core Version:    0.6.2
 */