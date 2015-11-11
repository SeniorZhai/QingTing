package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.graphics.Point;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.utils.OnPlayProcessListener;
import fm.qingting.utils.PlayProcessSyncUtil;
import java.util.Calendar;
import java.util.Locale;

public class SeekBarView extends QtView
  implements OnPlayProcessListener, AbsSeekBarElement.OnSeekBarChangeListener
{
  private static final String HIDEACCURATESEEK = "hideaccurateseek";
  private static final String HOURFORMAT = "%02d:%02d:%02d";
  public static final String PROGRESSCHANGED = "progresschanged";
  private static final String SHOWACCURATESEEK = "showaccurateseek";
  private final ViewLayout leftTimeLayout = this.standardLayout.createChildLT(150, 45, 10, 60, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mLeftTimeElement;
  private int mLeftTimeOffset = 0;
  private int mRightLength = 0;
  private TextViewElement mRightTimeElement;
  private SeekBarElement mSeekBarElement;
  private final ViewLayout rightTimeLayout = this.standardLayout.createChildLT(150, 45, 560, 60, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout seekLayout = this.standardLayout.createChildLT(720, 70, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 105, 720, 105, 0, 0, ViewLayout.FILL);

  public SeekBarView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mSeekBarElement = new SeekBarElement(paramContext);
    addElement(this.mSeekBarElement, i);
    this.mLeftTimeElement = new TextViewElement(paramContext);
    this.mLeftTimeElement.setColor(SkinManager.getNewPlaySubColor());
    this.mLeftTimeElement.setMaxLineLimit(1);
    this.mLeftTimeElement.setText("00:00:00");
    addElement(this.mLeftTimeElement);
    this.mRightTimeElement = new TextViewElement(paramContext);
    this.mRightTimeElement.setColor(SkinManager.getNewPlaySubColor());
    this.mRightTimeElement.setMaxLineLimit(1);
    this.mRightTimeElement.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    this.mRightTimeElement.setText("00:00:00");
    addElement(this.mRightTimeElement);
    PlayProcessSyncUtil.getInstance().addListener(this);
    this.mSeekBarElement.setSeekbarChangeListener(this);
    this.mSeekBarElement.setMax(PlayProcessSyncUtil.getInstance().getTotalLength());
    this.mSeekBarElement.initProgress(PlayProcessSyncUtil.getInstance().getCurrentPlayTime());
  }

  private String durationToStr(int paramInt)
  {
    int i = paramInt / 3600;
    int j = paramInt / 60;
    if (i < 0)
      return "";
    return String.format(Locale.US, "%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j % 60), Integer.valueOf(paramInt % 60) });
  }

  private String getCurrRelativeTime()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(11);
    int j = localCalendar.get(12);
    int k = localCalendar.get(13);
    if (i < 0)
      return "";
    return String.format(Locale.US, "%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) });
  }

  private String getLeftTime(int paramInt)
  {
    return durationToStr(this.mLeftTimeOffset + paramInt);
  }

  private String getRightTime(int paramInt)
  {
    return durationToStr(paramInt);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    PlayProcessSyncUtil.getInstance().removeListener(this);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("progressPosition"))
    {
      paramString = new Point();
      paramString.x = ((int)(PlayProcessSyncUtil.getInstance().getCurrentPlayRatio() * getWidth()));
      paramString.y = 0;
      return paramString;
    }
    if (paramString.equalsIgnoreCase("leftTimeOffset"))
      return Integer.valueOf(this.mLeftTimeOffset);
    if (paramString.equalsIgnoreCase("rightTime"))
      return Integer.valueOf(this.mRightLength);
    if (paramString.equalsIgnoreCase("progress"))
      return Integer.valueOf(this.mSeekBarElement.getProgress());
    return super.getValue(paramString, paramObject);
  }

  public void onManualSeek()
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.seekLayout.scaleToBounds(this.standardLayout);
    this.leftTimeLayout.scaleToBounds(this.standardLayout);
    this.rightTimeLayout.scaleToBounds(this.standardLayout);
    this.mSeekBarElement.measure(this.seekLayout);
    this.mLeftTimeElement.measure(this.leftTimeLayout);
    this.mRightTimeElement.measure(this.rightTimeLayout);
    this.mLeftTimeElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mRightTimeElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onProcessChanged()
  {
    int i = PlayProcessSyncUtil.getInstance().getCurrentPlayTime();
    this.mSeekBarElement.setProgress(i, false);
  }

  public void onProcessMaxChanged()
  {
    this.mSeekBarElement.setMax(PlayProcessSyncUtil.getInstance().getTotalLength());
  }

  public void onProgressChanged(AbsSeekBarElement paramAbsSeekBarElement, int paramInt, boolean paramBoolean)
  {
    this.mLeftTimeElement.setText(getLeftTime(paramInt));
    dispatchActionEvent("progresschanged", Integer.valueOf(paramInt));
  }

  public void onProgressPause()
  {
  }

  public void onProgressResume()
  {
  }

  public void onSeek(AbsSeekBarElement paramAbsSeekBarElement, float paramFloat)
  {
    PlayProcessSyncUtil.getInstance().attempSeek(paramFloat);
  }

  public void onStartTrackingTouch(AbsSeekBarElement paramAbsSeekBarElement)
  {
    dispatchActionEvent("showaccurateseek", null);
  }

  public void onStopTrackingTouch(AbsSeekBarElement paramAbsSeekBarElement)
  {
    dispatchActionEvent("hideaccurateseek", null);
  }

  public void onThumbClick(AbsSeekBarElement paramAbsSeekBarElement)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 0;
    int i = 1;
    if ((!paramString.equalsIgnoreCase("setNode")) || (paramObject == null));
    int k;
    label71: 
    do
    {
      do
      {
        return;
        paramString = (Node)paramObject;
      }
      while (!paramString.nodeName.equalsIgnoreCase("program"));
      paramString = (ProgramNode)paramString;
      k = paramString.getCurrPlayStatus();
      int m = paramString.channelType;
      if (k == 3)
      {
        if (m == 1)
        {
          if (i == 0)
            break label130;
          this.mLeftTimeOffset = j;
          if (i == 0)
            break label139;
        }
        for (i = paramString.getDuration(); ; i = paramString.endTime())
        {
          j = PlayProcessSyncUtil.getInstance().getCurrentPlayTime();
          this.mRightLength = i;
          this.mRightTimeElement.setText(getRightTime(i));
          this.mLeftTimeElement.setText(getLeftTime(j));
          return;
          i = 0;
          break;
          j = paramString.startTime();
          break label71;
        }
      }
    }
    while (k != 1);
    label130: label139: this.mLeftTimeOffset = paramString.startTime();
    this.mRightLength = paramString.endTime();
    this.mRightTimeElement.setText(getRightTime(this.mRightLength));
    this.mLeftTimeElement.setText(getCurrRelativeTime());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.SeekBarView
 * JD-Core Version:    0.6.2
 */