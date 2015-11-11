package fm.qingting.qtradio.voice;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.speech.RecognitionService;
import android.speech.RecognitionService.Callback;
import fm.qingting.qtradio.model.InfoManager;
import java.util.ArrayList;

public class QTRecognitionService extends RecognitionService
{
  private RecognitionService.Callback mCallBack;
  private VoiceRecord.OnRecordListener mListener = new VoiceRecord.OnRecordListener()
  {
    public void onError()
    {
      if (QTRecognitionService.this.mCallBack != null);
      try
      {
        QTRecognitionService.this.mCallBack.error(0);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }

    public void onPrepare()
    {
      if (QTRecognitionService.this.mCallBack != null);
      try
      {
        QTRecognitionService.this.mCallBack.readyForSpeech(null);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }

    public void onStart()
    {
      if (QTRecognitionService.this.mCallBack != null);
      try
      {
        QTRecognitionService.this.mCallBack.beginningOfSpeech();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }

    public void onStop(byte[] paramAnonymousArrayOfByte)
    {
      if (QTRecognitionService.this.mCallBack != null)
        try
        {
          QTRecognitionService.this.mCallBack.endOfSpeech();
          paramAnonymousArrayOfByte = VoiceRecognizer.recognize(paramAnonymousArrayOfByte);
          if ((paramAnonymousArrayOfByte != null) && (!paramAnonymousArrayOfByte.isEmpty()))
          {
            Bundle localBundle = new Bundle();
            localBundle.putStringArrayList("results_recognition", paramAnonymousArrayOfByte);
            QTRecognitionService.this.mCallBack.results(localBundle);
            return;
          }
          QTRecognitionService.this.mCallBack.error(0);
          return;
        }
        catch (Exception paramAnonymousArrayOfByte)
        {
          try
          {
            QTRecognitionService.this.mCallBack.error(0);
            return;
          }
          catch (RemoteException paramAnonymousArrayOfByte)
          {
            paramAnonymousArrayOfByte.printStackTrace();
          }
        }
    }
  };
  private VoiceRecord mRecorder;

  protected void onCancel(RecognitionService.Callback paramCallback)
  {
    this.mRecorder.release();
  }

  protected void onStartListening(Intent paramIntent, RecognitionService.Callback paramCallback)
  {
    this.mCallBack = paramCallback;
    if (InfoManager.getInstance().hasConnectedNetwork())
    {
      this.mRecorder = VoiceRecord.getInstance();
      this.mRecorder.setListener(this.mListener);
      this.mRecorder.startRecord();
      return;
    }
    try
    {
      this.mCallBack.error(0);
      return;
    }
    catch (RemoteException paramIntent)
    {
      paramIntent.printStackTrace();
    }
  }

  protected void onStopListening(RecognitionService.Callback paramCallback)
  {
    this.mRecorder.stopRecord();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.voice.QTRecognitionService
 * JD-Core Version:    0.6.2
 */