package fm.qingting.qtradio.ad.platforms.mediav;

import android.content.Context;
import com.mediav.ads.sdk.interfaces.IMvLandingPageListener;
import com.mediav.ads.sdk.interfaces.IMvLandingPageView;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.utils.QTMSGManage;

class MediaVWebView
  implements IMvLandingPageView
{
  public void open(Context paramContext, String paramString, IMvLandingPageListener paramIMvLandingPageListener)
  {
    QTMSGManage.getInstance().sendStatistcsMessage("mediavAd", "click_ad");
    ControllerManager.getInstance().redirectToActiviyByUrl(paramString, null, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.platforms.mediav.MediaVWebView
 * JD-Core Version:    0.6.2
 */