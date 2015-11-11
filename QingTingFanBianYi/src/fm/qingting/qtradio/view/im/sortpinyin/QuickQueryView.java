package fm.qingting.qtradio.view.im.sortpinyin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.TextView;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.qtradio.manager.SkinManager;

public class QuickQueryView extends ViewImpl
{
  private static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };
  private OnTouchingLetterChangeListener mChangeListener;
  private Paint mHighlightPaint = new Paint();
  private boolean mInTouchMode = false;
  private float mLastMotionY;
  private float mLastTextSize;
  private Paint mPaint = new Paint();
  private int mSelectedIndex = -1;
  private final Rect mTextBound = new Rect();
  private TextView mTextView;

  public QuickQueryView(Context paramContext)
  {
    super(paramContext);
    this.mPaint.setColor(SkinManager.getTextColorNormal());
    this.mHighlightPaint.setColor(SkinManager.getTextColorHighlight());
  }

  private void changeExternalViewState(String paramString)
  {
    if (paramString != null)
    {
      this.mTextView.setVisibility(0);
      this.mTextView.setText(paramString);
      return;
    }
    this.mTextView.setVisibility(4);
  }

  private void drawBg(Canvas paramCanvas)
  {
    if (this.mInTouchMode)
      paramCanvas.drawColor(SkinManager.getItemHighlightMaskColor());
  }

  private void drawLetters(Canvas paramCanvas)
  {
    int k = getWidth();
    int m = getHeight() / letters.length;
    if (this.mLastTextSize != m * 0.6F)
    {
      this.mLastTextSize = (m * 0.6F);
      this.mPaint.setTextSize(this.mLastTextSize);
      this.mHighlightPaint.setTextSize(this.mLastTextSize);
    }
    int i = 0;
    int j = 0;
    if (i < letters.length)
    {
      this.mPaint.getTextBounds(letters[i], 0, letters[i].length(), this.mTextBound);
      String str = letters[i];
      float f1 = (k - this.mTextBound.left - this.mTextBound.right) / 2;
      float f2 = (m - this.mTextBound.top - this.mTextBound.bottom) / 2 + j;
      if (this.mSelectedIndex == i);
      for (Paint localPaint = this.mHighlightPaint; ; localPaint = this.mPaint)
      {
        paramCanvas.drawText(str, f1, f2, localPaint);
        j += m;
        i += 1;
        break;
      }
    }
  }

  private int getSelectedIndex()
  {
    return (int)(this.mLastMotionY * letters.length / getHeight());
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawBg(paramCanvas);
    drawLetters(paramCanvas);
    paramCanvas.restore();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
      do
      {
        int i;
        do
        {
          do
          {
            return true;
            this.mInTouchMode = true;
            this.mLastMotionY = paramMotionEvent.getY();
            this.mSelectedIndex = getSelectedIndex();
          }
          while ((this.mSelectedIndex <= -1) || (this.mSelectedIndex >= letters.length));
          this.mChangeListener.onLetterChanged(letters[this.mSelectedIndex]);
          changeExternalViewState(letters[this.mSelectedIndex]);
          invalidate();
          return true;
          this.mLastMotionY = paramMotionEvent.getY();
          i = getSelectedIndex();
        }
        while (this.mSelectedIndex == i);
        this.mSelectedIndex = i;
      }
      while ((this.mSelectedIndex <= -1) || (this.mSelectedIndex >= letters.length));
      this.mChangeListener.onLetterChanged(letters[this.mSelectedIndex]);
      changeExternalViewState(letters[this.mSelectedIndex]);
      invalidate();
      return true;
    case 1:
    case 3:
    }
    this.mInTouchMode = false;
    changeExternalViewState(null);
    this.mSelectedIndex = -1;
    invalidate();
    return true;
  }

  public void setChangeListener(OnTouchingLetterChangeListener paramOnTouchingLetterChangeListener)
  {
    this.mChangeListener = paramOnTouchingLetterChangeListener;
  }

  public void setTextView(TextView paramTextView)
  {
    this.mTextView = paramTextView;
  }

  public static abstract interface OnTouchingLetterChangeListener
  {
    public abstract void onLetterChanged(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.sortpinyin.QuickQueryView
 * JD-Core Version:    0.6.2
 */