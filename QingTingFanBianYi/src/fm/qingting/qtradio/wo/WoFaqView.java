package fm.qingting.qtradio.wo;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fm.qingting.framework.view.ScrollViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class WoFaqView extends ScrollViewImpl
{
  private static final String FAQ = "免责声明\n中国联通2G/3G/4G用户开通“蜻蜓ＦＭ畅听流量包”业务后，使用蜻蜓ＦＭ手机客户端，除特别说明中提到的少数情况以外，其他操作产生的数据流量均不再计费（暂只支持中国大陆地区用户，海外地区用户不支持）。\n\n特别说明\n1. 资费：10元/月，开通期间使用蜻蜓FM手机客户端收听、下载节目，当月内流量全包。\n2. 业务开通后立即生效，每月月初自动续费。 包月业务退订后，当月可继续使用至月底，下月不再扣费。\n3. 联通沃3G预付费20元卡不支持开通此业务。\n4. 联通2G/3G/4G用户上网流量上限为6G当月流量超出上限后，联通将自动关闭上网功能，下月自动开通。\n5. 请保证使用开通号码联网，谨慎安装流量监控软件，使用业务时请尽量关闭WIFI，避免因WIFI和2G/3G网络却换频繁导致流量包失效。\n6. 如有疑问，请咨询客服：4000 666 976\n\n联络客服\n您在使用“蜻蜓FM畅听流量包”过程中有任何问题或建议，可以通过以下方式联系\n中国联通客服热线：10010\n蜻蜓FM客服热线：4000 666 976\n常见问题\n\n1. 如何查看“蜻蜓FM畅听流量包”是否开通成功？\n答：开通“蜻蜓FM畅听流量包”业务后，点击**→“蜻蜓FM畅听流量包”进入页面，即可查看具体开通情况。\n2. 开通“蜻蜓FM畅听流量包”可享受什么服务？\n答：开通“蜻蜓FM畅听流量包”业务后，在2G/3G网络下使用蜻蜓FM手机客户端在线收听/下载节目等产生的数据流量将免收流量费。推荐使用3G网络接入，体验更佳，观看更流畅。\n3. “蜻蜓FM畅听流量包”每月多少钱？\n答:资费为10元/月，订购用户月末如不退订，则次月1日系统自动续费。在联通的话费账单中“蜻蜓FM畅听流量包”显示为“蜻蜓FM”。\n4.\t如何退订“蜻蜓FM畅听流量包”业务？退订后，本月仍可享受包流量服务吗？\n答：打开蜻蜓FM客户端，点击更多→“蜻蜓FM畅听流量包”→“立即退订”即可退订。\n由于本月费用已扣除，退订成功后，您扔可继续享受包流量服务至本月月底，下月起不再收费。在本月重新订购不重复收费。\n5.\t开通“蜻蜓FM畅听流量包”业务后，使用蜻蜓FM手机客户端若仍然扣除常规流量费用，怎么处理？\n答：A、联络蜻蜓FM客服：4000 666 976\nB、蜻蜓FM服务人员在后台核实用户相关订购行为和扣费信息，在确认实为技术环节导致误扣常规流量后，给予用户相应的金额补偿。\nC、双卡双待手机，请先确认开通此服务的号码与上网号码是否一致。\n6.\t我订购“蜻蜓FM畅听流量包”业务，能否同时使用第三方流量监测软件？\n答：不能，若您已订购蜻蜓FM畅听流量包服务，同时使用第三方流量监测软件有可能导致您的包流量服务失效，请您谨慎使用，建议您卸载此类流量监控软件。\n7.\t联通3G预付费20元卡用户能使用“蜻蜓FM畅听流量包”服务吗？\n答：不能，会扣套餐内流量，在开通前的业务说明中会明确提及，建议您退订。\n8.\t国外（及港台地区）能订购“蜻蜓FM畅听流量包”服务吗？\n答：蜻蜓FM畅听流量包”暂只支持中国大陆地区，海外用户请谨慎，给您造成的不便敬请谅解。";
  private RelativeLayout mContentView;
  private TextView mFaqTv;
  private final ViewLayout mStandLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public WoFaqView(Context paramContext)
  {
    super(paramContext);
    this.mContentView = ((RelativeLayout)inflate(paramContext, 2130903069, null));
    addView(this.mContentView);
    this.mFaqTv = ((TextView)this.mContentView.findViewById(2131230884));
    this.mFaqTv.setText("免责声明\n中国联通2G/3G/4G用户开通“蜻蜓ＦＭ畅听流量包”业务后，使用蜻蜓ＦＭ手机客户端，除特别说明中提到的少数情况以外，其他操作产生的数据流量均不再计费（暂只支持中国大陆地区用户，海外地区用户不支持）。\n\n特别说明\n1. 资费：10元/月，开通期间使用蜻蜓FM手机客户端收听、下载节目，当月内流量全包。\n2. 业务开通后立即生效，每月月初自动续费。 包月业务退订后，当月可继续使用至月底，下月不再扣费。\n3. 联通沃3G预付费20元卡不支持开通此业务。\n4. 联通2G/3G/4G用户上网流量上限为6G当月流量超出上限后，联通将自动关闭上网功能，下月自动开通。\n5. 请保证使用开通号码联网，谨慎安装流量监控软件，使用业务时请尽量关闭WIFI，避免因WIFI和2G/3G网络却换频繁导致流量包失效。\n6. 如有疑问，请咨询客服：4000 666 976\n\n联络客服\n您在使用“蜻蜓FM畅听流量包”过程中有任何问题或建议，可以通过以下方式联系\n中国联通客服热线：10010\n蜻蜓FM客服热线：4000 666 976\n常见问题\n\n1. 如何查看“蜻蜓FM畅听流量包”是否开通成功？\n答：开通“蜻蜓FM畅听流量包”业务后，点击**→“蜻蜓FM畅听流量包”进入页面，即可查看具体开通情况。\n2. 开通“蜻蜓FM畅听流量包”可享受什么服务？\n答：开通“蜻蜓FM畅听流量包”业务后，在2G/3G网络下使用蜻蜓FM手机客户端在线收听/下载节目等产生的数据流量将免收流量费。推荐使用3G网络接入，体验更佳，观看更流畅。\n3. “蜻蜓FM畅听流量包”每月多少钱？\n答:资费为10元/月，订购用户月末如不退订，则次月1日系统自动续费。在联通的话费账单中“蜻蜓FM畅听流量包”显示为“蜻蜓FM”。\n4.\t如何退订“蜻蜓FM畅听流量包”业务？退订后，本月仍可享受包流量服务吗？\n答：打开蜻蜓FM客户端，点击更多→“蜻蜓FM畅听流量包”→“立即退订”即可退订。\n由于本月费用已扣除，退订成功后，您扔可继续享受包流量服务至本月月底，下月起不再收费。在本月重新订购不重复收费。\n5.\t开通“蜻蜓FM畅听流量包”业务后，使用蜻蜓FM手机客户端若仍然扣除常规流量费用，怎么处理？\n答：A、联络蜻蜓FM客服：4000 666 976\nB、蜻蜓FM服务人员在后台核实用户相关订购行为和扣费信息，在确认实为技术环节导致误扣常规流量后，给予用户相应的金额补偿。\nC、双卡双待手机，请先确认开通此服务的号码与上网号码是否一致。\n6.\t我订购“蜻蜓FM畅听流量包”业务，能否同时使用第三方流量监测软件？\n答：不能，若您已订购蜻蜓FM畅听流量包服务，同时使用第三方流量监测软件有可能导致您的包流量服务失效，请您谨慎使用，建议您卸载此类流量监控软件。\n7.\t联通3G预付费20元卡用户能使用“蜻蜓FM畅听流量包”服务吗？\n答：不能，会扣套餐内流量，在开通前的业务说明中会明确提及，建议您退订。\n8.\t国外（及港台地区）能订购“蜻蜓FM畅听流量包”服务吗？\n答：蜻蜓FM畅听流量包”暂只支持中国大陆地区，海外用户请谨慎，给您造成的不便敬请谅解。");
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.mStandLayout.scaleToBounds(i, paramInt2);
    paramInt2 = View.MeasureSpec.makeMeasureSpec(536870911, -2147483648);
    this.mContentView.measure(paramInt1, paramInt2);
    setMeasuredDimension(this.mStandLayout.width, this.mStandLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.WoFaqView
 * JD-Core Version:    0.6.2
 */