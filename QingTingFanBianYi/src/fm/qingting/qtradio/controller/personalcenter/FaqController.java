package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import android.content.res.Resources;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.FaqItem;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.faq.FaqListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FaqController extends ViewController
  implements INavigationBarListener
{
  private List<FaqItem> faqList = new ArrayList();
  private String first = null;
  private FaqListView itemView;
  private String second = null;

  public FaqController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "faq";
    this.itemView = new FaqListView(paramContext);
    attachView(this.itemView);
    paramContext = new NavigationBarView(paramContext);
    paramContext.setLeftItem(0);
    paramContext.setTitleItem(new NavigationBarItem("常见问题"));
    paramContext.setBarListener(this);
    setNavigationBar(paramContext);
  }

  private void getFaqList()
  {
    Object localObject1 = getContext().getResources().openRawResource(2131165186);
    try
    {
      localObject1 = new InputStreamReader((InputStream)localObject1, "utf-8");
      localObject1 = new BufferedReader((Reader)localObject1);
      try
      {
        while (true)
        {
          str = ((BufferedReader)localObject1).readLine();
          if (str == null)
            break;
          if (!str.startsWith("#q#"))
            break label79;
          this.first = str.substring(3);
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        String str;
        localUnsupportedEncodingException.printStackTrace();
        Object localObject2 = null;
        continue;
        label79: if (str.startsWith("#a#"))
        {
          this.second = str.substring(3);
          if (this.first != null)
          {
            this.faqList.add(new FaqItem(this.first, this.second));
            this.first = null;
            this.second = null;
          }
        }
      }
    }
  }

  private void openFeedBackController()
  {
    EventDispacthManager.getInstance().dispatchAction("showFeedbackPop", "faq");
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      getFaqList();
      if ((this.faqList != null) && (this.faqList.size() > 0))
        this.itemView.update(paramString, this.faqList);
    }
  }

  public void controllerDidPopped()
  {
    if (this.faqList != null)
    {
      this.faqList.clear();
      this.faqList = null;
    }
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("onclick"))
      openFeedBackController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.FaqController
 * JD-Core Version:    0.6.2
 */