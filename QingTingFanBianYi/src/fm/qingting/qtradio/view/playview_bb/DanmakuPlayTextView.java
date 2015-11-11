package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.DanmakuPlayController;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.PlayProcessSyncUtil;
import fm.qingting.utils.QTMSGManage;
import java.util.HashMap;

public class DanmakuPlayTextView extends ViewGroupViewImpl
{
  private EditText mEditText;
  private boolean mLastFocus = false;
  private Button mSendBtn;
  private final ViewLayout sendLayout = this.standardLayout.createChildLT(120, 68, 590, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 98, 720, 98, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(570, 68, 10, 15, ViewLayout.SCALE_FLAG_SLTCW);

  public DanmakuPlayTextView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-12632257);
    this.mEditText = new EditText(paramContext);
    this.mEditText.setPadding(15, 5, 15, 5);
    this.mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });
    this.mEditText.setHint("发表评论...");
    this.mEditText.setBackgroundResource(2130837532);
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
        if (DanmakuPlayTextView.this.mEditText.getText().length() > 0)
          DanmakuPlayTextView.this.mSendBtn.setEnabled(true);
      }
    });
    this.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if ((paramAnonymousView == DanmakuPlayTextView.this.mEditText) && (paramAnonymousBoolean));
      }
    });
    addView(this.mEditText);
    this.mSendBtn = new Button(paramContext);
    this.mSendBtn.setEnabled(false);
    this.mSendBtn.setText("发送");
    this.mSendBtn.setPadding(0, 0, 0, 0);
    this.mSendBtn.setBackgroundColor(-3552823);
    this.mSendBtn.setTextColor(-12632257);
    this.mSendBtn.setBackgroundResource(2130837527);
    this.mSendBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (IMAgent.getInstance().sendBarrage(DanmakuPlayTextView.this.mEditText.getText().toString(), null))
        {
          ((InputMethodManager)DanmakuPlayTextView.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(DanmakuPlayTextView.this.getApplicationWindowToken(), 0);
          paramAnonymousView = new HashMap();
          paramAnonymousView.put("text", DanmakuPlayTextView.this.mEditText.getText().toString());
          paramAnonymousView.put("time", Integer.valueOf(IMAgent.getInstance().getAddBarrageTime()));
          DanmakuPlayController.getInstance(DanmakuPlayTextView.this.getContext()).config("addDanmaku", paramAnonymousView);
          DanmakuPlayTextView.this.mEditText.setText("");
          DanmakuPlayTextView.this.mSendBtn.setEnabled(false);
        }
      }
    });
    addView(this.mSendBtn);
  }

  protected void isFocus(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Node localNode;
      int i;
      int j;
      int k;
      if (paramBoolean != this.mLastFocus)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("danmaku_text");
        localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
        if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
        {
          i = PlayProcessSyncUtil.getInstance().getCurrentPlayTime();
          j = i / 60;
          k = i % 60;
          if (j <= 0)
            break label136;
          this.mEditText.setHint("在" + j + "分" + k + "秒处评论");
        }
      }
      while (true)
      {
        j = ((ProgramNode)localNode).id;
        IMAgent.getInstance().addBarrageInfo(j, i);
        this.mLastFocus = true;
        return;
        label136: this.mEditText.setHint("在" + k + "秒处评论");
      }
    }
    this.mEditText.setHint("发表评论...");
    this.mLastFocus = false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mEditText.layout(this.textLayout.leftMargin, this.textLayout.topMargin, this.textLayout.getRight(), this.textLayout.getBottom());
    this.mSendBtn.layout(this.sendLayout.leftMargin, this.sendLayout.topMargin, this.sendLayout.getRight(), this.sendLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.textLayout.measureView(this.mEditText);
    this.sendLayout.scaleToBounds(this.standardLayout);
    this.sendLayout.measureView(this.mSendBtn);
    this.mEditText.setTextSize(0, SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuPlayTextView
 * JD-Core Version:    0.6.2
 */