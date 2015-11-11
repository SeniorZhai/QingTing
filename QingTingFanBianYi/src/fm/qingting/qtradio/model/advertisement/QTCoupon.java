package fm.qingting.qtradio.model.advertisement;

public class QTCoupon
{
  public String coupon = null;
  public String result = null;

  public String getResult()
  {
    if (this.result == null)
      this.result = "";
    if (this.coupon == null)
      this.coupon = "";
    return "&result=" + this.result + "&coupon=" + this.coupon;
  }

  public boolean islotterySuccess()
  {
    if ((this.result == null) || (this.result.equalsIgnoreCase("")));
    while ((this.result.equalsIgnoreCase("-1")) || (this.result.equalsIgnoreCase("1")) || (!this.result.equalsIgnoreCase("0")))
      return false;
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.advertisement.QTCoupon
 * JD-Core Version:    0.6.2
 */