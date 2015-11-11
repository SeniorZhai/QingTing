package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.content.res.Resources;
import android.view.View.MeasureSpec;
import android.widget.EditText;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;

public class SearchNaviView extends ViewGroupViewImpl
{
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_VOICE = 2;
  private final ViewLayout editLayout = this.standardLayout.createChildLT(390, 60, 156, 27, ViewLayout.SCALE_FLAG_SLTCW);
  private SearchNaviBgView mBgView;
  private EditText mEditText;
  private int mStatusHeight = 0;
  private boolean mSupportTranslucentStatusBar = false;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchNaviView(Context paramContext)
  {
    super(paramContext);
    if (QtApiLevelManager.isApiLevelSupported(19))
    {
      this.mStatusHeight = NaviUtil.getStatusBarHeight(getResources());
      if (this.mStatusHeight > 0)
        bool = true;
      this.mSupportTranslucentStatusBar = bool;
    }
    if (this.mSupportTranslucentStatusBar)
      setBackgroundDrawable(getResources().getDrawable(2130837872));
    while (true)
    {
      this.mBgView = new SearchNaviBgView(paramContext);
      addView(this.mBgView);
      this.mEditText = new EditText(paramContext);
      this.mEditText.setBackgroundDrawable(null);
      this.mEditText.setHint("搜索电台，专辑，主播，节目");
      this.mEditText.setHintTextColor(-7434605);
      this.mEditText.setImeOptions(3);
      this.mEditText.setTextColor(-14013910);
      this.mEditText.setSingleLine();
      this.mEditText.setGravity(19);
      this.mEditText.clearFocus();
      addView(this.mEditText);
      return;
      setBackgroundColor(SkinManager.getNaviBgColor());
    }
  }

  protected EditText getEditText()
  {
    return this.mEditText;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mBgView.layout(0, this.mStatusHeight, this.standardLayout.width, this.mStatusHeight + this.standardLayout.height);
    this.mEditText.layout(this.editLayout.leftMargin, this.mStatusHeight + this.editLayout.topMargin, this.editLayout.getRight(), this.mStatusHeight + this.editLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mBgView);
    this.editLayout.scaleToBounds(this.standardLayout);
    this.editLayout.measureView(this.mEditText);
    this.mEditText.setTextSize(0, this.editLayout.height * 0.5F);
    this.mEditText.setPadding(0, 0, 0, 0);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height + this.mStatusHeight);
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.mBgView.setEventHandler(paramIEventHandler);
  }

  public void setType(int paramInt)
  {
    this.mBgView.setType(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchNaviView
 * JD-Core Version:    0.6.2
 */