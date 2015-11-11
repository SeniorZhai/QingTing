package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.PaintFlagsDrawFilter;
import android.view.MotionEvent;

public class QtView extends ViewImpl
{
  private static final int ARRAY_CAPACITY_INCREMENT = 4;
  private static final int ARRAY_INITIAL_CAPACITY = 4;
  private final DrawFilter filter = new PaintFlagsDrawFilter(0, 67);
  private ViewElement[] mChildren = new ViewElement[4];
  private int mChildrenCount = 0;
  protected boolean mIgnoreTouchEvent = false;
  private boolean mInTouchMode = false;
  private int mLastTouchChildIndex = -1;

  public QtView(Context paramContext)
  {
    super(paramContext);
  }

  private void drawChildrens(Canvas paramCanvas)
  {
    if ((this.mChildren == null) || (this.mChildren.length == 0) || (this.mChildrenCount == 0))
      return;
    int i = 0;
    label25: ViewElement localViewElement;
    if (i < this.mChildrenCount)
    {
      localViewElement = this.mChildren[i];
      if (localViewElement.getVisiblity() != 4)
        break label55;
    }
    while (true)
    {
      i += 1;
      break label25;
      break;
      label55: localViewElement.draw(paramCanvas);
    }
  }

  private int handleChildrenTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mChildren == null) || (this.mChildren.length == 0) || (this.mChildrenCount == 0))
      return -1;
    int i = this.mChildrenCount - 1;
    if (i < 0)
      return -1;
    ViewElement localViewElement = this.mChildren[i];
    if (localViewElement.getVisiblity() == 4);
    while (!localViewElement.handleTouchEvent(paramMotionEvent))
    {
      i -= 1;
      break;
    }
    return i;
  }

  private int indexOfChild(ViewElement paramViewElement)
  {
    int k = this.mChildrenCount;
    ViewElement[] arrayOfViewElement = this.mChildren;
    int i = 0;
    while (true)
    {
      int j;
      if (i >= k)
        j = -1;
      do
      {
        return j;
        j = i;
      }
      while (arrayOfViewElement[i] == paramViewElement);
      i += 1;
    }
  }

  private void removeFromArray(int paramInt)
  {
    ViewElement[] arrayOfViewElement = this.mChildren;
    int i = this.mChildrenCount;
    if (paramInt == i - 1)
    {
      paramInt = this.mChildrenCount - 1;
      this.mChildrenCount = paramInt;
      arrayOfViewElement[paramInt] = null;
    }
    while (true)
    {
      invalidate();
      return;
      if ((paramInt < 0) || (paramInt >= i))
        break;
      System.arraycopy(arrayOfViewElement, paramInt + 1, arrayOfViewElement, paramInt, i - paramInt - 1);
      paramInt = this.mChildrenCount - 1;
      this.mChildrenCount = paramInt;
      arrayOfViewElement[paramInt] = null;
    }
    throw new IndexOutOfBoundsException();
  }

  private void removeFromArray(int paramInt1, int paramInt2)
  {
    ViewElement[] arrayOfViewElement = this.mChildren;
    int j = this.mChildrenCount;
    int i = Math.max(0, paramInt1);
    paramInt2 = Math.min(j, i + paramInt2);
    if (i == paramInt2)
      return;
    if (paramInt2 == j)
    {
      paramInt1 = i;
      if (paramInt1 < paramInt2);
    }
    while (true)
    {
      this.mChildrenCount -= paramInt2 - i;
      invalidate();
      return;
      arrayOfViewElement[paramInt1] = null;
      paramInt1 += 1;
      break;
      System.arraycopy(arrayOfViewElement, paramInt2, arrayOfViewElement, i, j - paramInt2);
      paramInt1 = j - (paramInt2 - i);
      while (paramInt1 < j)
      {
        arrayOfViewElement[paramInt1] = null;
        paramInt1 += 1;
      }
    }
  }

  public void addElement(ViewElement paramViewElement)
  {
    ViewElement[] arrayOfViewElement2 = this.mChildren;
    int i = this.mChildrenCount;
    int j = arrayOfViewElement2.length;
    ViewElement[] arrayOfViewElement1 = arrayOfViewElement2;
    if (j == i)
    {
      this.mChildren = new ViewElement[j + 4];
      System.arraycopy(arrayOfViewElement2, 0, this.mChildren, 0, j);
      arrayOfViewElement1 = this.mChildren;
    }
    paramViewElement.setParent(this);
    i = this.mChildrenCount;
    this.mChildrenCount = (i + 1);
    arrayOfViewElement1[i] = paramViewElement;
  }

  public void addElement(ViewElement paramViewElement, int paramInt)
  {
    paramViewElement.setOwenerId(paramInt);
    addElement(paramViewElement);
  }

  public ViewElement getChildAt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mChildrenCount))
      return null;
    return this.mChildren[paramInt];
  }

  public int getChildCount()
  {
    return this.mChildrenCount;
  }

  protected void handleSelfTouchEvent()
  {
    this.mIgnoreTouchEvent = false;
  }

  protected void ignoreSelfTouchEvent()
  {
    this.mIgnoreTouchEvent = true;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawChildrens(paramCanvas);
    paramCanvas.restore();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = true;
    boolean bool1;
    if (this.mIgnoreTouchEvent)
      bool1 = super.onTouchEvent(paramMotionEvent);
    int i;
    do
    {
      do
      {
        return bool1;
        if (paramMotionEvent.getAction() == 0)
          break;
        bool1 = bool2;
      }
      while (!this.mInTouchMode);
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        this.mLastTouchChildIndex = handleChildrenTouchEvent(paramMotionEvent);
        this.mInTouchMode = true;
        return true;
      case 2:
        i = handleChildrenTouchEvent(paramMotionEvent);
        bool1 = bool2;
      case 1:
      case 3:
      }
    }
    while (this.mLastTouchChildIndex == i);
    this.mInTouchMode = false;
    return true;
    handleChildrenTouchEvent(paramMotionEvent);
    return true;
  }

  public void removeAllElements()
  {
    int i = this.mChildrenCount;
    if (i <= 0)
      return;
    ViewElement[] arrayOfViewElement = this.mChildren;
    this.mChildrenCount = 0;
    i -= 1;
    while (true)
    {
      if (i < 0)
      {
        invalidate();
        return;
      }
      arrayOfViewElement[i] = null;
      i -= 1;
    }
  }

  public void removeElement(ViewElement paramViewElement)
  {
    int i = indexOfChild(paramViewElement);
    if (i >= 0)
      removeFromArray(i);
  }

  public void removeElements(int paramInt1, int paramInt2)
  {
    removeFromArray(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.QtView
 * JD-Core Version:    0.6.2
 */