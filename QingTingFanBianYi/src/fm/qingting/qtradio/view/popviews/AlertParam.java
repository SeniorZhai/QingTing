package fm.qingting.qtradio.view.popviews;

import java.util.ArrayList;
import java.util.List;

public class AlertParam
{
  private List<String> mButtons;
  private OnButtonClickListener mClickListener;
  private boolean mHasTip = false;
  private String mText;

  public void addForbidBox()
  {
    this.mHasTip = true;
  }

  public String getButton(int paramInt)
  {
    if ((this.mButtons != null) && (this.mButtons.size() > paramInt))
      return (String)this.mButtons.get(paramInt);
    return null;
  }

  public List<String> getButtons()
  {
    return this.mButtons;
  }

  public OnButtonClickListener getListener()
  {
    return this.mClickListener;
  }

  public String getMsg()
  {
    return this.mText;
  }

  public boolean hasForbidBox()
  {
    return this.mHasTip;
  }

  public void setButtons(List<String> paramList)
  {
    this.mButtons = paramList;
  }

  public void setOnButtonClickListener(OnButtonClickListener paramOnButtonClickListener)
  {
    this.mClickListener = paramOnButtonClickListener;
  }

  public void setText(String paramString)
  {
    this.mText = paramString;
  }

  public static class Builder
  {
    private List<String> mButtons;
    private boolean mHasTip;
    private AlertParam.OnButtonClickListener mListener;
    private String mText;

    public Builder addButton(String paramString)
    {
      if (this.mButtons == null)
        this.mButtons = new ArrayList();
      this.mButtons.add(paramString);
      return this;
    }

    public Builder addForbidBox()
    {
      this.mHasTip = true;
      return this;
    }

    public AlertParam create()
    {
      AlertParam localAlertParam = new AlertParam();
      localAlertParam.setText(this.mText);
      localAlertParam.setButtons(this.mButtons);
      if (this.mHasTip)
        localAlertParam.addForbidBox();
      localAlertParam.setOnButtonClickListener(this.mListener);
      return localAlertParam;
    }

    public Builder setListener(AlertParam.OnButtonClickListener paramOnButtonClickListener)
    {
      this.mListener = paramOnButtonClickListener;
      return this;
    }

    public Builder setMessage(String paramString)
    {
      this.mText = paramString;
      return this;
    }
  }

  public static abstract interface OnButtonClickListener
  {
    public abstract void onClick(int paramInt, boolean paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.AlertParam
 * JD-Core Version:    0.6.2
 */