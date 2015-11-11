package fm.qingting.qtradio.view.personalcenter.feedback;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import fm.qingting.framework.view.ScrollViewImpl;
import fm.qingting.qtradio.model.SharedCfg;

public class FeedbackView extends ScrollViewImpl
  implements View.OnClickListener, View.OnKeyListener
{
  private RelativeLayout mContentView;
  private EditText mEmail;
  private EditText mFeedBack;
  private String mFeedbackCategory;
  private String mFeedbackContract;
  private Button mSubmit;

  public FeedbackView(Context paramContext)
  {
    super(paramContext);
    this.mContentView = ((RelativeLayout)inflate(paramContext, 2130903045, null));
    addView(this.mContentView);
    this.mFeedBack = ((EditText)this.mContentView.findViewById(2131230745));
    this.mEmail = ((EditText)this.mContentView.findViewById(2131230746));
    this.mSubmit = ((Button)this.mContentView.findViewById(2131230747));
    this.mSubmit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackView.this.sendFeedbackMessage();
      }
    });
    this.mSubmit.setOnClickListener(this);
    this.mContentView.setOnClickListener(this);
    this.mEmail.setOnKeyListener(this);
    this.mFeedBack.setOnKeyListener(this);
    this.mFeedbackCategory = ("【" + SharedCfg.getInstance().getFeedbackCategory() + "】");
    this.mFeedbackContract = SharedCfg.getInstance().getFeedbackContactInfo();
    this.mFeedBack.setText(this.mFeedbackCategory);
    if ((this.mFeedbackContract != null) && (!this.mFeedbackContract.equalsIgnoreCase("")))
      this.mEmail.setText(this.mFeedbackContract);
  }

  private void sendFeedbackMessage()
  {
    String str = this.mEmail.getText().toString();
    if ((str != null) && (!str.equalsIgnoreCase("")))
      SharedCfg.getInstance().saveFeedBackContactInfo(str);
    dispatchActionEvent("sendDiscuss", this.mFeedBack.getText().toString().replace(this.mFeedbackCategory, ""));
    this.mFeedBack.setText("");
    closeIm();
  }

  public void closeIm()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.mSubmit)
    {
      sendFeedbackMessage();
      return;
    }
    closeIm();
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 67)
    {
      if (paramView != this.mEmail)
        break label48;
      if (this.mEmail.getText().toString().trim().equalsIgnoreCase(this.mFeedbackContract))
        this.mEmail.setText("");
    }
    while (true)
    {
      return false;
      label48: if ((paramView == this.mFeedBack) && (this.mFeedBack.getText().toString().trim().equalsIgnoreCase(this.mFeedbackCategory)))
        this.mFeedBack.setText("");
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContentView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mContentView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.feedback.FeedbackView
 * JD-Core Version:    0.6.2
 */