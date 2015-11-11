package fm.qingting.framework.view;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;

public class QtListItemView extends ViewImpl
  implements View.OnClickListener
{
  private boolean mHighlighEnabled = false;
  private final Handler mPressDelayHandler = new Handler();
  private final Runnable mPressDelayRunnable = new Runnable()
  {
    public void run()
    {
      QtListItemView.this.mPressed = true;
      QtListItemView.this.invalidate();
    }
  };
  private boolean mPressed = false;
  private UnsetPressedState mUnsetPressedState;

  public QtListItemView(Context paramContext)
  {
    super(paramContext);
  }

  private void removeDelayTimer()
  {
    this.mPressDelayHandler.removeCallbacks(this.mPressDelayRunnable);
    if (this.mUnsetPressedState != null)
      this.mPressDelayHandler.removeCallbacks(this.mUnsetPressedState);
  }

  private void startDelayTimer()
  {
    this.mPressDelayHandler.removeCallbacks(this.mPressDelayRunnable);
    this.mPressDelayHandler.postDelayed(this.mPressDelayRunnable, ViewConfiguration.getTapTimeout());
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
    case 0:
    case 1:
    case 3:
    case 4:
    }
    while (true)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      if (this.mHighlighEnabled)
      {
        startDelayTimer();
        continue;
        if (this.mHighlighEnabled)
          removeDelayTimer();
        if (this.mPressed)
        {
          this.mPressed = false;
          invalidate();
        }
        else if (this.mHighlighEnabled)
        {
          if (this.mUnsetPressedState == null)
            this.mUnsetPressedState = new UnsetPressedState(null);
          this.mPressed = true;
          invalidate();
          this.mPressDelayHandler.postDelayed(this.mUnsetPressedState, ViewConfiguration.getPressedStateDuration());
          continue;
          if (this.mHighlighEnabled)
            removeDelayTimer();
          if (this.mPressed)
          {
            this.mPressed = false;
            invalidate();
          }
        }
      }
    }
  }

  protected boolean isItemPressed()
  {
    return this.mPressed;
  }

  public void onClick(View paramView)
  {
    onQtItemClick(paramView);
  }

  protected void onQtItemClick(View paramView)
  {
  }

  protected void setItemSelectedEnable()
  {
    this.mHighlighEnabled = true;
  }

  private final class UnsetPressedState
    implements Runnable
  {
    private UnsetPressedState()
    {
    }

    public void run()
    {
      if (QtListItemView.this.mPressed)
      {
        QtListItemView.this.mPressed = false;
        QtListItemView.this.invalidate();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.QtListItemView
 * JD-Core Version:    0.6.2
 */