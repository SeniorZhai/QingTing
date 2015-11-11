package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.model.ShareObjectNode;
import fm.qingting.qtradio.share.ShareUtil;
import fm.qingting.utils.InputMethodUtil;

public class ShareMessagePopuView extends ViewGroupViewImpl
  implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener
{
  private static final boolean API19 = QtApiLevelManager.isApiLevelSupported(19);
  private static final int MESSAGE_LENGTH = 80;
  private final RectF mBgRectF = new RectF();
  private RelativeLayout mContentView = (RelativeLayout)inflate(getContext(), 2130903059, null);
  private EditText mInputEv;
  private int mMaxHeight = 0;
  private TextView mResetCountTv;
  private ShareObjectNode mShareObject;
  private TextView mSubmitTv;
  private TextView mTitleTv;
  private final Rect mWindRectF = new Rect();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public ShareMessagePopuView(Context paramContext)
  {
    super(paramContext);
    addView(this.mContentView);
    this.mTitleTv = ((TextView)this.mContentView.findViewById(2131230859));
    this.mSubmitTv = ((TextView)this.mContentView.findViewById(2131230860));
    this.mInputEv = ((EditText)this.mContentView.findViewById(2131230862));
    this.mResetCountTv = ((TextView)this.mContentView.findViewById(2131230863));
    this.mSubmitTv.setOnClickListener(this);
    this.mContentView.setOnClickListener(this);
    this.mInputEv.addTextChangedListener(this);
    this.mInputEv.setOnEditorActionListener(this);
    this.mInputEv.setSingleLine(false);
    this.mInputEv.setHorizontallyScrolling(false);
    ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(0, 2);
    if (API19)
      getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          ShareMessagePopuView.this.getWindowVisibleDisplayFrame(ShareMessagePopuView.this.mWindRectF);
          int i = ShareMessagePopuView.this.mWindRectF.height();
          if (ShareMessagePopuView.this.mMaxHeight < i)
            ShareMessagePopuView.access$102(ShareMessagePopuView.this, i);
          ShareMessagePopuView.this.requestLayout();
        }
      });
  }

  private void share()
  {
    InputMethodUtil.hideSoftInput(this);
    this.mShareObject.message = this.mInputEv.getEditableText().toString().trim();
    EventDispacthManager.getInstance().dispatchAction("shareToPlatform", this.mShareObject);
  }

  public void afterTextChanged(Editable paramEditable)
  {
    paramEditable = this.mInputEv.getEditableText().toString().trim();
    int i;
    Object localObject;
    if (paramEditable == null)
    {
      i = 0;
      localObject = new StringBuilder().append("");
      if (80 - i < 0)
        break label106;
    }
    label106: for (int j = 80 - i; ; j = 0)
    {
      localObject = j;
      this.mResetCountTv.setText((CharSequence)localObject);
      if (i > 80)
      {
        paramEditable = paramEditable.substring(0, 80);
        this.mInputEv.setText(paramEditable);
        this.mInputEv.setSelection(paramEditable.length());
      }
      return;
      i = paramEditable.length();
      break;
    }
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (!this.mBgRectF.contains(paramMotionEvent.getX(), paramMotionEvent.getY())))
    {
      dispatchActionEvent("cancelPop", null);
      InputMethodUtil.hideSoftInput(this);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.mSubmitTv)
      share();
    while (paramView != this.mContentView)
      return;
    InputMethodUtil.hideSoftInput(this);
  }

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 6)
    {
      share();
      return true;
    }
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (API19)
    {
      paramInt1 = this.mWindRectF.height();
      if (!API19)
        break label130;
    }
    label130: for (paramInt2 = this.mWindRectF.top; ; paramInt2 = 0)
    {
      paramInt4 = this.mContentView.getMeasuredWidth();
      int i = this.mContentView.getMeasuredHeight();
      paramInt3 = (this.standardLayout.width - paramInt4) / 2;
      paramInt4 = (paramInt4 + this.standardLayout.width) / 2;
      i = paramInt1 - i + paramInt2;
      paramInt1 += paramInt2;
      this.mBgRectF.set(paramInt3, i, paramInt4, paramInt1);
      this.mContentView.layout(paramInt3, i, paramInt4, paramInt1);
      return;
      paramInt1 = this.standardLayout.height;
      break;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.mContentView.measure(paramInt1, -2);
    if (API19);
    for (paramInt1 = this.mWindRectF.height(); ; paramInt1 = this.standardLayout.height)
    {
      setMeasuredDimension(this.standardLayout.width, paramInt1);
      return;
    }
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramObject != null)
    {
      this.mShareObject = ((ShareObjectNode)paramObject);
      this.mTitleTv.setText(ShareUtil.getTypeString(this.mShareObject.type));
      paramString = ShareUtil.getPreMessage(this.mShareObject.node);
      this.mInputEv.setText(ShareUtil.getPreMessage(this.mShareObject.node));
      this.mInputEv.setSelection(paramString.length());
      this.mInputEv.requestFocus();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ShareMessagePopuView
 * JD-Core Version:    0.6.2
 */