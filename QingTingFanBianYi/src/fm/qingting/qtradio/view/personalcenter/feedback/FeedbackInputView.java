package fm.qingting.qtradio.view.personalcenter.feedback;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.chatroom.ExpressionSpan;

public class FeedbackInputView extends ViewGroupViewImpl
{
  private final ViewLayout editLayout = this.standardLayout.createChildLT(543, 79, 37, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout inviteLayout = this.standardLayout.createChildLT(64, 49, 625, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(572, 8, 15, 81, ViewLayout.SCALE_FLAG_SLTCW);
  private EditText mEditText;
  private boolean mHasText = false;
  private ImageView mLineView;
  private TextView mSendView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 106, 720, 106, 0, 0, ViewLayout.FILL);

  public FeedbackInputView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1);
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((paramAnonymousView == FeedbackInputView.this.mSendView) && (FeedbackInputView.this.mHasText))
          FeedbackInputView.this.sendDiscuss();
      }
    };
    this.mSendView = new TextView(paramContext);
    this.mSendView.setText("发送");
    this.mSendView.setTextColor(SkinManager.getTextColorNormal());
    this.mSendView.setGravity(17);
    this.mSendView.setEnabled(false);
    addView(this.mSendView);
    this.mSendView.setOnClickListener(local1);
    this.mLineView = new ImageView(paramContext);
    this.mLineView.setBackgroundResource(2130837583);
    this.mLineView.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mLineView);
    this.mEditText = new EditText(paramContext);
    this.mEditText.setBackgroundResource(0);
    this.mEditText.setGravity(19);
    this.mEditText.setTextColor(SkinManager.getTextColorNormal());
    this.mEditText.setMaxLines(3);
    this.mEditText.clearFocus();
    this.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          FeedbackInputView.this.mLineView.setImageResource(2130837584);
          return;
        }
        FeedbackInputView.this.mLineView.setImageResource(2130837583);
      }
    });
    this.mEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousCharSequence.length() > 0)
        {
          if (!FeedbackInputView.this.mHasText)
            FeedbackInputView.this.mSendView.setEnabled(true);
          FeedbackInputView.access$102(FeedbackInputView.this, true);
          return;
        }
        if (FeedbackInputView.this.mHasText)
          FeedbackInputView.this.mSendView.setEnabled(false);
        FeedbackInputView.access$102(FeedbackInputView.this, false);
      }
    });
    addView(this.mEditText);
  }

  private boolean closeKeyBoard()
  {
    return ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
  }

  private int getDesiredHeight()
  {
    if (this.mEditText.getMeasuredHeight() > this.editLayout.height)
      return this.standardLayout.height + this.mEditText.getMeasuredHeight() - this.editLayout.height;
    return this.standardLayout.height;
  }

  private void openKeyBoard()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this.mEditText, 0);
  }

  private void sendDiscuss()
  {
    dispatchActionEvent("sendDiscuss", this.mEditText.getText().toString());
    this.mEditText.setText("");
    closeKeyBoard();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = 0;
    if (this.mEditText.getMeasuredHeight() > this.editLayout.height)
      paramInt1 = this.mEditText.getMeasuredHeight() - this.editLayout.height;
    this.mLineView.layout(this.lineLayout.leftMargin, this.lineLayout.topMargin + paramInt1, this.lineLayout.getRight(), this.lineLayout.getBottom() + paramInt1);
    this.mSendView.layout(this.inviteLayout.leftMargin, this.inviteLayout.topMargin + paramInt1, this.inviteLayout.getRight(), paramInt1 + this.inviteLayout.getBottom());
    this.mEditText.layout(this.editLayout.leftMargin, this.editLayout.topMargin, this.editLayout.getRight(), this.editLayout.topMargin + this.mEditText.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.inviteLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.editLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.measureView(this.mLineView);
    this.inviteLayout.measureView(this.mSendView);
    this.mSendView.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    this.mEditText.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    this.mEditText.setPadding(0, (int)(this.editLayout.height * 0.4F), 0, 0);
    this.mEditText.measure(this.editLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.editLayout.height, 0));
    setMeasuredDimension(this.standardLayout.width, getDesiredHeight());
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("replyToUser"))
    {
      paramString = (String)paramObject;
      this.mEditText.setText("@" + paramString + " ");
      this.mEditText.setSelection(this.mEditText.getText().length());
      this.mEditText.requestFocus();
      openKeyBoard();
    }
    int k;
    do
    {
      do
      {
        return;
        if (paramString.equalsIgnoreCase("closeKeyboard"))
        {
          this.mEditText.clearFocus();
          closeKeyBoard();
          return;
        }
        if (paramString.equalsIgnoreCase("openKeyboard"))
        {
          this.mEditText.requestFocus();
          openKeyBoard();
          return;
        }
        if (paramString.equalsIgnoreCase("enterReportSn"))
        {
          this.mEditText.setHint("输入歌名...");
          this.mEditText.requestFocus();
          openKeyBoard();
          return;
        }
        if (paramString.equalsIgnoreCase("exitReport"))
        {
          this.mEditText.setHint("");
          this.mEditText.setText("");
          return;
        }
      }
      while (!paramString.equalsIgnoreCase("delete"));
      k = this.mEditText.getSelectionStart();
    }
    while (k == 0);
    while (true)
    {
      int i;
      try
      {
        paramString = this.mEditText.getEditableText();
        if (paramString.charAt(k - 1) == ']')
        {
          i = k - 2;
          if ((i > -1) && (paramString.charAt(i) != '['))
            break label339;
          paramObject = (ExpressionSpan[])paramString.getSpans(i, k - 1, ExpressionSpan.class);
          if (paramObject != null)
          {
            int j = 0;
            if (j < paramObject.length)
            {
              paramString.removeSpan(paramObject[j]);
              j += 1;
              continue;
            }
            paramString.delete(i, k);
            return;
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      paramString.delete(k - 1, k);
      return;
      label339: i -= 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.feedback.FeedbackInputView
 * JD-Core Version:    0.6.2
 */