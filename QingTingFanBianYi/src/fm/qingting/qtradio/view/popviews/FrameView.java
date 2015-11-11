package fm.qingting.qtradio.view.popviews;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.qtradio.voice.QTRecognitionService;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;

class FrameView extends QtView
  implements ViewElement.OnElementClickListener
{
  private static final int STATE_ERROR = 2;
  private static final int STATE_RECOGNITION = 1;
  private static final int STATE_SPEECH = 0;
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(510, 92, 0, 566, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineEightLayout = this.standardLayout.createChildLT(60, 4, 225, 230, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineFiveLayout = this.standardLayout.createChildLT(60, 4, 225, 260, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineFourLayout = this.standardLayout.createChildLT(60, 4, 225, 270, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineOneLayout = this.standardLayout.createChildLT(16, 4, 247, 300, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineSevenLayout = this.standardLayout.createChildLT(60, 4, 225, 240, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineSixLayout = this.standardLayout.createChildLT(60, 4, 225, 250, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineThreeLayout = this.standardLayout.createChildLT(52, 4, 229, 280, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineTwoLayout = this.standardLayout.createChildLT(36, 4, 239, 290, ViewLayout.SCALE_FLAG_SLTCW);
  private int mCurrentLineCount;
  private long mDelay = 60L;
  private Handler mHandler = new Handler();
  private Intent mIntent;
  private ImageViewElement[] mLineElement;
  private ButtonViewElement mLongButton;
  private Runnable mRunnable = new Runnable()
  {
    public void run()
    {
      switch (FrameView.this.mState)
      {
      case 1:
      default:
        return;
      case 0:
      }
      if (FrameView.this.mCurrentLineCount == 0)
      {
        FrameView.this.setLineVisible(4);
        FrameView.access$302(FrameView.this, 60L);
      }
      FrameView.this.mLineElement[FrameView.this.mCurrentLineCount].setVisible(0);
      FrameView.this.mHandler.postDelayed(FrameView.this.mRunnable, FrameView.this.mDelay);
      FrameView.access$102(FrameView.this, (FrameView.this.mCurrentLineCount + 1) % 8);
      FrameView.access$302(FrameView.this, FrameView.this.mCurrentLineCount * 20 + 60);
    }
  };
  private ImageViewElement mSeperatorElement;
  private ButtonViewElement mShortFirstButton;
  private ButtonViewElement mShortSecondButton;
  private SpeechRecognizer mSpeechRecognizer;
  private int mState;
  private ImageViewElement mStateCircleElement;
  private AnimatorImageViewElement mStateErrorElement;
  private TextViewElement mStateInfoElement;
  private RatationImageElement mStateRecongitionElement;
  private AnimatorImageViewElement mStateSpeechElement;
  private ImageViewElement mVerticalElement;
  private final ViewLayout seperatorLayout = this.standardLayout.createChildLT(510, 2, 0, 566, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(510, 658, 510, 658, 0, 0, ViewLayout.FILL);
  private final ViewLayout stateCircleLayout = this.standardLayout.createChildLT(228, 228, 141, 130, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout stateErrorLayout = this.standardLayout.createChildLT(136, 124, 187, 200, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout stateInfoLayout = this.standardLayout.createChildLT(510, 144, 0, 400, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout stateRecognitionLayout = this.standardLayout.createChildLT(36, 36, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout stateSpeechLayout = this.standardLayout.createChildLT(124, 188, 193, 170, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout verticalLayout = this.standardLayout.createChildLT(2, 92, 255, 566, ViewLayout.SCALE_FLAG_SLTCW);

  public FrameView(Context paramContext)
  {
    super(paramContext);
    setBackgroundResource(2130838039);
    this.mStateCircleElement = new ImageViewElement(paramContext);
    this.mStateCircleElement.setImageRes(2130838040);
    addElement(this.mStateCircleElement);
    this.mStateSpeechElement = new AnimatorImageViewElement(paramContext);
    this.mStateSpeechElement.setImageRes(2130838050);
    addElement(this.mStateSpeechElement);
    this.mStateRecongitionElement = new RatationImageElement(paramContext);
    this.mStateRecongitionElement.setThumbResource(2130838049);
    addElement(this.mStateRecongitionElement);
    this.mStateErrorElement = new AnimatorImageViewElement(paramContext);
    this.mStateErrorElement.setImageRes(2130838041);
    addElement(this.mStateErrorElement);
    this.mStateInfoElement = new TextViewElement(paramContext);
    this.mStateInfoElement.setMaxLineLimit(1);
    this.mStateInfoElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mStateInfoElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mStateInfoElement.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mStateInfoElement);
    this.mSeperatorElement = new ImageViewElement(paramContext);
    this.mSeperatorElement.setImageRes(2130838042);
    addElement(this.mSeperatorElement);
    this.mVerticalElement = new ImageViewElement(paramContext);
    this.mVerticalElement.setImageRes(2130838051);
    addElement(this.mVerticalElement);
    this.mShortFirstButton = new ButtonViewElement(paramContext);
    this.mShortFirstButton.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mShortFirstButton.setTextColor(SkinManager.getTextColorSubInfo());
    addElement(this.mShortFirstButton);
    this.mShortSecondButton = new ButtonViewElement(paramContext);
    this.mShortSecondButton.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mShortSecondButton.setTextColor(SkinManager.getTextColorHighlight());
    addElement(this.mShortSecondButton);
    this.mLongButton = new ButtonViewElement(paramContext);
    this.mLongButton.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mLongButton.setTextColor(SkinManager.getTextColorSubInfo());
    addElement(this.mLongButton);
    this.mLineElement = new ImageViewElement[8];
    int i = 0;
    while (i < 8)
    {
      this.mLineElement[i] = new ImageViewElement(paramContext);
      addElement(this.mLineElement[i]);
      i += 1;
    }
    this.mLineElement[0].setImageRes(2130838043);
    this.mLineElement[1].setImageRes(2130838044);
    this.mLineElement[2].setImageRes(2130838045);
    this.mLineElement[3].setImageRes(2130838046);
    this.mLineElement[4].setImageRes(2130838046);
    this.mLineElement[5].setImageRes(2130838046);
    this.mLineElement[6].setImageRes(2130838046);
    this.mLineElement[7].setImageRes(2130838046);
    this.mCurrentLineCount = 0;
    this.mShortFirstButton.setOnElementClickListener(this);
    this.mShortSecondButton.setOnElementClickListener(this);
    this.mLongButton.setOnElementClickListener(this);
    this.mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(paramContext, new ComponentName(paramContext, QTRecognitionService.class));
    if (this.mSpeechRecognizer == null)
      return;
    this.mSpeechRecognizer.setRecognitionListener(new RecognitionListener()
    {
      public void onBeginningOfSpeech()
      {
        FrameView.access$002(FrameView.this, 0);
        FrameView.this.setResource();
      }

      public void onBufferReceived(byte[] paramAnonymousArrayOfByte)
      {
      }

      public void onEndOfSpeech()
      {
        FrameView.access$002(FrameView.this, 1);
        FrameView.this.setResource();
      }

      public void onError(int paramAnonymousInt)
      {
        FrameView.access$002(FrameView.this, 2);
        FrameView.this.setResource();
      }

      public void onEvent(int paramAnonymousInt, Bundle paramAnonymousBundle)
      {
      }

      public void onPartialResults(Bundle paramAnonymousBundle)
      {
      }

      public void onReadyForSpeech(Bundle paramAnonymousBundle)
      {
      }

      public void onResults(Bundle paramAnonymousBundle)
      {
        if (paramAnonymousBundle != null)
        {
          paramAnonymousBundle = paramAnonymousBundle.getStringArrayList("results_recognition");
          if ((paramAnonymousBundle != null) && (!paramAnonymousBundle.isEmpty()))
          {
            paramAnonymousBundle = (String)paramAnonymousBundle.get(0);
            if ((paramAnonymousBundle != null) && (!paramAnonymousBundle.equalsIgnoreCase("")))
            {
              FrameView.this.mStateRecongitionElement.stopRunnable();
              FrameView.this.stopRunnable();
              FrameView.this.dispatchActionEvent("cancelPop", null);
              InfoManager.getInstance().root().mSearchNode.setVoiceSearch(true);
              ControllerManager.getInstance().redirectToSearchView(paramAnonymousBundle, true);
              QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_success");
            }
          }
        }
      }

      public void onRmsChanged(float paramAnonymousFloat)
      {
      }
    });
    this.mIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    startSpeech();
  }

  private void cancelPop()
  {
    stopRunnable();
    this.mStateRecongitionElement.stopRunnable();
    this.mSpeechRecognizer.cancel();
    EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
    QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_cancel");
  }

  private void setLineVisible(int paramInt)
  {
    int i = 0;
    while (i < 8)
    {
      this.mLineElement[i].setVisible(paramInt);
      i += 1;
    }
  }

  private void setResource()
  {
    setLineVisible(4);
    this.mCurrentLineCount = 0;
    switch (this.mState)
    {
    default:
      return;
    case 0:
      this.mStateSpeechElement.setVisible(0);
      this.mStateRecongitionElement.setVisible(4);
      this.mStateErrorElement.setVisible(4);
      this.mStateInfoElement.setText("···请说话···");
      this.mShortFirstButton.setVisible(0);
      this.mShortFirstButton.setText("取消");
      this.mShortSecondButton.setVisible(0);
      this.mShortSecondButton.setText("说完了");
      this.mVerticalElement.setVisible(0);
      this.mLongButton.setVisible(4);
      this.mLongButton.setText("取消");
      startRunnable();
      return;
    case 1:
      this.mStateSpeechElement.setVisible(4);
      this.mStateRecongitionElement.setVisible(0);
      this.mStateErrorElement.setVisible(4);
      this.mStateInfoElement.setText("···正在识别···");
      this.mShortFirstButton.setVisible(4);
      this.mShortSecondButton.setVisible(4);
      this.mVerticalElement.setVisible(4);
      this.mLongButton.setVisible(0);
      this.mLongButton.setText("取消");
      startRunnable();
      return;
    case 2:
    }
    this.mStateSpeechElement.setVisible(4);
    this.mStateRecongitionElement.setVisible(4);
    this.mStateErrorElement.setVisible(0);
    this.mStateErrorElement.setImageRes(2130838041);
    this.mStateInfoElement.setText("···出错啦，不能识别···");
    this.mShortFirstButton.setVisible(0);
    this.mShortSecondButton.setVisible(0);
    this.mShortSecondButton.setText("再试一次");
    this.mVerticalElement.setVisible(0);
    this.mLongButton.setVisible(4);
    stopRunnable();
  }

  private void startRunnable()
  {
    this.mHandler.removeCallbacks(this.mRunnable);
    this.mHandler.post(this.mRunnable);
  }

  private void stopRunnable()
  {
    this.mHandler.removeCallbacks(this.mRunnable);
  }

  public void close(boolean paramBoolean)
  {
    this.mSpeechRecognizer.destroy();
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mShortFirstButton)
      cancelPop();
    do
    {
      do
      {
        return;
        if (paramViewElement != this.mShortSecondButton)
          break;
        if (this.mState == 0)
        {
          this.mState = 1;
          setResource();
          this.mSpeechRecognizer.stopListening();
          QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_complete");
          return;
        }
      }
      while (this.mState != 2);
      this.mState = 0;
      setResource();
      this.mSpeechRecognizer.startListening(this.mIntent);
      QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_fail");
      return;
    }
    while ((paramViewElement != this.mLongButton) || (this.mState != 1));
    cancelPop();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.stateCircleLayout.scaleToBounds(this.standardLayout);
    this.stateSpeechLayout.scaleToBounds(this.standardLayout);
    this.stateRecognitionLayout.scaleToBounds(this.standardLayout);
    this.stateErrorLayout.scaleToBounds(this.standardLayout);
    this.stateInfoLayout.scaleToBounds(this.standardLayout);
    this.seperatorLayout.scaleToBounds(this.standardLayout);
    this.verticalLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.mStateCircleElement.measure(this.stateCircleLayout);
    this.mStateSpeechElement.measure(this.stateSpeechLayout);
    this.mStateRecongitionElement.measure(this.stateCircleLayout);
    this.mStateRecongitionElement.setThumbRadius(this.stateRecognitionLayout.width / 2);
    this.mStateErrorElement.measure(this.stateErrorLayout);
    this.mStateInfoElement.measure(this.stateInfoLayout);
    this.mSeperatorElement.measure(this.seperatorLayout);
    this.mVerticalElement.measure(this.verticalLayout);
    this.mShortFirstButton.measure(this.buttonLayout.getLeft(), this.buttonLayout.getTop(), this.buttonLayout.getLeft() + this.buttonLayout.width / 2, this.buttonLayout.getBottom());
    this.mShortSecondButton.measure(this.buttonLayout.getLeft() + this.buttonLayout.width / 2, this.buttonLayout.getTop(), this.buttonLayout.getRight(), this.buttonLayout.getBottom());
    this.mLongButton.measure(this.buttonLayout);
    this.lineOneLayout.scaleToBounds(this.standardLayout);
    this.lineTwoLayout.scaleToBounds(this.standardLayout);
    this.lineThreeLayout.scaleToBounds(this.standardLayout);
    this.lineFourLayout.scaleToBounds(this.standardLayout);
    this.lineFiveLayout.scaleToBounds(this.standardLayout);
    this.lineSixLayout.scaleToBounds(this.standardLayout);
    this.lineSevenLayout.scaleToBounds(this.standardLayout);
    this.lineEightLayout.scaleToBounds(this.standardLayout);
    this.mLineElement[0].measure(this.lineOneLayout);
    this.mLineElement[1].measure(this.lineTwoLayout);
    this.mLineElement[2].measure(this.lineThreeLayout);
    this.mLineElement[3].measure(this.lineFourLayout);
    this.mLineElement[4].measure(this.lineFiveLayout);
    this.mLineElement[5].measure(this.lineSixLayout);
    this.mLineElement[6].measure(this.lineSevenLayout);
    this.mLineElement[7].measure(this.lineEightLayout);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void startSpeech()
  {
    this.mState = 0;
    setResource();
    this.mSpeechRecognizer.startListening(this.mIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.FrameView
 * JD-Core Version:    0.6.2
 */