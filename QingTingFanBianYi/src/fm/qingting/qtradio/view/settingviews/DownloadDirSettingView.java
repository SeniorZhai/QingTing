package fm.qingting.qtradio.view.settingviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import fm.qingting.downloadnew.DownloadUtils;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SingleCheckAdapter;
import fm.qingting.qtradio.view.scheduleview.SizeInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadDirSettingView extends ListViewImpl
  implements IEventHandler
{
  private SingleCheckAdapter adapter;
  private IAdapterIViewFactory factory;
  private List<String> mSdList;

  public DownloadDirSettingView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new SingleCheckSettingItemView(DownloadDirSettingView.this.getContext(), this.val$hash);
      }
    };
    this.adapter = new SingleCheckAdapter(new ArrayList(), this.factory);
    this.adapter.setEventHandler(this);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(17170445);
    setAdapter(this.adapter);
  }

  @TargetApi(9)
  private boolean checkIfValid(String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.exists())
      paramString.mkdir();
    return true;
  }

  private void getAllExterSdcardPath()
  {
    int j = 0;
    this.mSdList = SettingConfig.getStorageList();
    String str2 = getFirstExterPath();
    ArrayList localArrayList = new ArrayList();
    String str3 = GlobalCfg.getInstance(null).getDownloadPath();
    int i = j;
    if (this.mSdList != null)
    {
      i = j;
      if (this.mSdList.size() > 0)
      {
        i = 0;
        int k = 0;
        j = 0;
        while (i < this.mSdList.size())
        {
          String str1 = (String)this.mSdList.get(i);
          String str4 = str1 + File.separator + "QTDownloadRadio";
          this.mSdList.set(i, str4);
          int m;
          if (!checkIfValid(str4))
          {
            m = k;
            i += 1;
            k = m;
          }
          else
          {
            str1 = SizeInfo.getFileSize(DownloadUtils.getAvailableExternalMemorySize(str4));
            String str5 = SizeInfo.getFileSize(DownloadUtils.getTotalExternalMemorySize(str4));
            str1 = "（" + str1 + "可用，" + "共" + str5 + "）";
            if (TextUtils.equals(str4, str2 + File.separator + "QTDownloadRadio"))
              str1 = "存储卡1" + str1;
            while (true)
            {
              str5 = "位置：" + str4;
              localArrayList.add(new SettingItem(str1, SettingItem.SettingType.check, str4, str5));
              m = k;
              if (!TextUtils.equals(str3, str4))
                break;
              j = i;
              m = k;
              break;
              if (this.mSdList.size() == 2)
              {
                str1 = "存储卡2" + str1;
              }
              else
              {
                str1 = "存储卡2" + k + str1;
                k += 1;
              }
            }
          }
        }
        i = j;
      }
    }
    this.adapter.setData(localArrayList);
    this.adapter.checkIndex(i);
  }

  private String getFirstExterPath()
  {
    return Environment.getExternalStorageDirectory().getPath();
  }

  private void initData()
  {
    getAllExterSdcardPath();
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      if (paramObject1.type.equalsIgnoreCase("check"))
      {
        int i = paramObject1.position;
        this.adapter.checkIndex(i);
        InfoManager.getInstance().root().mDownLoadInfoNode.changeDownloadPath((String)this.mSdList.get(i));
      }
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      initData();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.DownloadDirSettingView
 * JD-Core Version:    0.6.2
 */