package fm.qingting.qtradio.room;

public class StateMachine
{
  private static StateMachine _instance = null;
  private State currentState = null;
  public int nodeName = 1;

  public static StateMachine getInstance()
  {
    if (_instance == null)
      _instance = new StateMachine();
    return _instance;
  }

  public State getCurrState()
  {
    return this.currentState;
  }

  public void setCurrState(State paramState)
  {
    if (paramState == null)
      return;
    this.currentState = paramState;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.StateMachine
 * JD-Core Version:    0.6.2
 */