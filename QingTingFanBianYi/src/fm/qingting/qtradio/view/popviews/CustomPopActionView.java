package fm.qingting.qtradio.view.popviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class CustomPopActionView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final int MAX_CNT = 3;
  private final ViewLayout cancelLayout = this.standardLayout.createChildLT(720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(136, 220, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private int mButtonCnt = 0;
  private ButtonViewElement mCancelElement;
  private CustomPopActionParam mCustomPopActionParam;
  private int mHash = hashCode();
  private PopActionButtonElement[] mItems;
  private LineElement mLineElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public CustomPopActionView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getNewPopBgColor(), SkinManager.getNewPopBgColor());
    addElement(this.mBg);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setColor(SkinManager.getNewPopTextColor());
    this.mTitleElement.setText("分享内容到", false);
    addElement(this.mTitleElement);
    this.mCancelElement = new ButtonViewElement(paramContext);
    this.mCancelElement.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    this.mCancelElement.setText("取消");
    addElement(this.mCancelElement);
    this.mCancelElement.setTextColor(SkinManager.getNewPopTextColor());
    this.mCancelElement.setOnElementClickListener(this);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  private int getLineCnt(int paramInt)
  {
    int i = paramInt / 3;
    if (paramInt % 3 == 0);
    for (paramInt = 0; ; paramInt = 1)
      return paramInt + i;
  }

  private void pickPhoto()
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT", null);
    localIntent.setType("image/*");
    ((Activity)getContext()).startActivityForResult(localIntent, 71);
  }

  private void takePhoto()
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
      localIntent.putExtra("output", Uri.parse("file:///sdcard/qt_danmaku_capture.jpg"));
      ((Activity)getContext()).startActivityForResult(localIntent, 73);
      return;
    }
    Toast.makeText(getContext(), "内存卡不存在", 1).show();
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      int i = this.titleLayout.height;
      int j = this.itemLayout.height;
      int k = getLineCnt(this.mButtonCnt);
      int m = this.cancelLayout.height;
      if (paramMotionEvent.getY() < this.standardLayout.height - (i + j * k + m))
      {
        dispatchActionEvent("cancelPop", null);
        return true;
      }
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mCancelElement == paramViewElement)
      dispatchActionEvent("cancelPop", null);
    if (this.mCustomPopActionParam == null);
    label304: 
    while (true)
    {
      return;
      List localList = this.mCustomPopActionParam.getButtons();
      if ((localList != null) && (localList.size() != 0))
      {
        int i = 0;
        while (true)
        {
          if (i >= this.mButtonCnt)
            break label304;
          if (paramViewElement == this.mItems[i])
            switch (((Integer)localList.get(i)).intValue())
            {
            default:
              return;
            case 0:
              QTMSGManage.getInstance().sendStatistcsMessage("AlbumViewAction", "shareChoose");
              dispatchActionEvent("cancelPopWithoutAnimation", null);
              EventDispacthManager.getInstance().dispatchAction("shareChoose", this.mCustomPopActionParam.getNode());
              return;
            case 1:
              QTMSGManage.getInstance().sendStatistcsMessage("VirtualProgramPopClick", "download");
              dispatchActionEvent("download", this.mCustomPopActionParam.getNode());
              return;
            case 2:
              dispatchActionEvent("cancelPopWithoutAnimation", null);
              ControllerManager.getInstance().redirectToAddAlarmView(this.mCustomPopActionParam.getNode());
              return;
            case 3:
              dispatchActionEvent("cancelPopWithoutAnimation", null);
              paramViewElement = ControllerManager.getInstance().getLastViewController();
              if ((paramViewElement == null) || (!paramViewElement.controllerName.equalsIgnoreCase("mainplayview")))
                break;
              paramViewElement.config("showSchedule", null);
              return;
            case 4:
              dispatchActionEvent("cancelPopWithoutAnimation", null);
              takePhoto();
              return;
            case 5:
              dispatchActionEvent("cancelPopWithoutAnimation", null);
              pickPhoto();
              return;
            case 6:
              QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "help");
              EventDispacthManager.getInstance().dispatchAction("showFeedbackPop", "faq");
              return;
            }
          i += 1;
        }
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = 0;
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.cancelLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    int i = this.mButtonCnt;
    int j = getLineCnt(i);
    int m = this.titleLayout.height;
    int n = this.itemLayout.height;
    int i1 = this.cancelLayout.height;
    m = this.standardLayout.height - (j * n + m + i1);
    this.mBg.measure(0, m, this.standardLayout.width, this.standardLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mTitleElement.setTranslationY(m);
    i = Math.min(i, 3);
    j = (this.standardLayout.width - this.itemLayout.width * i) / (i + 1);
    m = this.titleLayout.height + m;
    for (i = j; k < this.mButtonCnt; i = n)
    {
      n = i;
      if (k % 3 == 0)
        n = j;
      this.mItems[k].measure(this.itemLayout);
      this.mItems[k].setTranslationX(n);
      this.mItems[k].setTranslationY(m);
      i = m;
      if (k % 3 == 2)
        i = m + this.itemLayout.height;
      n += this.itemLayout.width + j;
      k += 1;
      m = i;
    }
    this.mCancelElement.measure(this.cancelLayout);
    this.mCancelElement.setTranslationY(this.standardLayout.height - this.cancelLayout.height);
    this.mCancelElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mLineElement.measure(this.lineLayout);
    this.mLineElement.setTranslationY(this.standardLayout.height - this.cancelLayout.height);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    int i = 0;
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      this.mCustomPopActionParam = ((CustomPopActionParam)paramObject);
      if (this.mCustomPopActionParam == null)
        requestLayout();
    }
    else
    {
      return;
    }
    paramString = this.mCustomPopActionParam.getButtons();
    if ((paramString == null) || (paramString.size() == 0))
    {
      requestLayout();
      return;
    }
    paramObject = this.mItems;
    this.mTitleElement.setText(this.mCustomPopActionParam.getTitle(), false);
    int j = paramString.size();
    int k;
    if (this.mButtonCnt == 0)
    {
      this.mItems = new PopActionButtonElement[j];
      while (i < j)
      {
        k = ((Integer)paramString.get(i)).intValue();
        paramObject = new PopActionButtonElement(getContext());
        this.mItems[i] = paramObject;
        paramObject.setAction(CustomPopAction.getName(k), CustomPopAction.getIcon(k));
        addElement(paramObject, this.mHash);
        paramObject.setOnElementClickListener(this);
        i += 1;
      }
      this.mButtonCnt = j;
    }
    while (true)
    {
      requestLayout();
      return;
      if (j > this.mButtonCnt)
      {
        this.mItems = new PopActionButtonElement[j];
        System.arraycopy(paramObject, 0, this.mItems, 0, this.mButtonCnt);
        i = 0;
        while (i < this.mButtonCnt)
        {
          k = ((Integer)paramString.get(i)).intValue();
          paramObject = this.mItems[i];
          paramObject.setAction(CustomPopAction.getName(k), CustomPopAction.getIcon(k));
          paramObject.setVisible(0);
          i += 1;
        }
        i = this.mButtonCnt;
        while (i < j)
        {
          paramObject = new PopActionButtonElement(getContext());
          k = ((Integer)paramString.get(i)).intValue();
          this.mItems[i] = paramObject;
          paramObject.setAction(CustomPopAction.getName(k), CustomPopAction.getIcon(k));
          addElement(paramObject);
          paramObject.setOnElementClickListener(this);
          i += 1;
        }
        this.mButtonCnt = j;
      }
      else
      {
        i = 0;
        while (i < j)
        {
          paramObject = this.mItems[i];
          k = ((Integer)paramString.get(i)).intValue();
          paramObject.setAction(CustomPopAction.getName(k), CustomPopAction.getIcon(k));
          paramObject.setVisible(0);
          i += 1;
        }
        i = j;
        while (i < this.mButtonCnt)
        {
          this.mItems[i].setVisible(4);
          i += 1;
        }
        this.mButtonCnt = j;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CustomPopActionView
 * JD-Core Version:    0.6.2
 */