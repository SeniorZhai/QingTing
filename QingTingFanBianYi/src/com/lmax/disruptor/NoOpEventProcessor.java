package com.lmax.disruptor;

public final class NoOpEventProcessor
  implements EventProcessor
{
  private final SequencerFollowingSequence sequence;

  public NoOpEventProcessor(Sequencer paramSequencer)
  {
    this.sequence = new SequencerFollowingSequence(paramSequencer, null);
  }

  public Sequence getSequence()
  {
    return this.sequence;
  }

  public void halt()
  {
  }

  public void run()
  {
  }

  private static final class SequencerFollowingSequence extends Sequence
  {
    private final Sequencer sequencer;

    private SequencerFollowingSequence(Sequencer paramSequencer)
    {
      super();
      this.sequencer = paramSequencer;
    }

    public long get()
    {
      return this.sequencer.getCursor();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.NoOpEventProcessor
 * JD-Core Version:    0.6.2
 */