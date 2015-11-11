package fm.qingting.framework.view;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;
import android.widget.Button;
import android.widget.Checkable;

public class MyCheckBox extends Button
  implements Checkable
{
  private boolean mBroadcasting;
  private boolean mChecked;
  private OnMyCheckedChangeListener mOnCheckedChangeListener;
  private OnMyCheckedChangeListener mOnCheckedChangeWidgetListener;

  public MyCheckBox(Context paramContext)
  {
    super(paramContext);
  }

  public boolean isChecked()
  {
    return this.mChecked;
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setChecked(paramParcelable.checked);
    requestLayout();
  }

  public Parcelable onSaveInstanceState()
  {
    setFreezesText(true);
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.checked = isChecked();
    return localSavedState;
  }

  public boolean performClick()
  {
    toggle();
    return super.performClick();
  }

  public void setChecked(boolean paramBoolean)
  {
    if (this.mChecked != paramBoolean)
    {
      this.mChecked = paramBoolean;
      refreshDrawableState();
      if (!this.mBroadcasting);
    }
    else
    {
      return;
    }
    this.mBroadcasting = true;
    if (this.mOnCheckedChangeListener != null)
      this.mOnCheckedChangeListener.onCheckedChanged(this, this.mChecked);
    if (this.mOnCheckedChangeWidgetListener != null)
      this.mOnCheckedChangeWidgetListener.onCheckedChanged(this, this.mChecked);
    this.mBroadcasting = false;
  }

  public void setOnCheckedChangeListener(OnMyCheckedChangeListener paramOnMyCheckedChangeListener)
  {
    this.mOnCheckedChangeListener = paramOnMyCheckedChangeListener;
  }

  void setOnCheckedChangeWidgetListener(OnMyCheckedChangeListener paramOnMyCheckedChangeListener)
  {
    this.mOnCheckedChangeWidgetListener = paramOnMyCheckedChangeListener;
  }

  public void toggle()
  {
    if (this.mChecked);
    for (boolean bool = false; ; bool = true)
    {
      setChecked(bool);
      return;
    }
  }

  public static abstract interface OnMyCheckedChangeListener
  {
    public abstract void onCheckedChanged(MyCheckBox paramMyCheckBox, boolean paramBoolean);
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public MyCheckBox.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MyCheckBox.SavedState(paramAnonymousParcel, null);
      }

      public MyCheckBox.SavedState[] newArray(int paramAnonymousInt)
      {
        return new MyCheckBox.SavedState[paramAnonymousInt];
      }
    };
    boolean checked;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.checked = ((Boolean)paramParcel.readValue(null)).booleanValue();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public String toString()
    {
      return "CompoundButton.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + this.checked + "}";
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeValue(Boolean.valueOf(this.checked));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.MyCheckBox
 * JD-Core Version:    0.6.2
 */