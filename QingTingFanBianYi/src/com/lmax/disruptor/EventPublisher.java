package com.lmax.disruptor;

public class EventPublisher<E>
{
  private final RingBuffer<E> ringBuffer;

  public EventPublisher(RingBuffer<E> paramRingBuffer)
  {
    this.ringBuffer = paramRingBuffer;
  }

  private void translateAndPublish(EventTranslator<E> paramEventTranslator, long paramLong)
  {
    try
    {
      paramEventTranslator.translateTo(this.ringBuffer.get(paramLong), paramLong);
      return;
    }
    finally
    {
      this.ringBuffer.publish(paramLong);
    }
    throw paramEventTranslator;
  }

  public void publishEvent(EventTranslator<E> paramEventTranslator)
  {
    translateAndPublish(paramEventTranslator, this.ringBuffer.next());
  }

  public boolean tryPublishEvent(EventTranslator<E> paramEventTranslator, int paramInt)
  {
    try
    {
      translateAndPublish(paramEventTranslator, this.ringBuffer.tryNext(paramInt));
      return true;
    }
    catch (InsufficientCapacityException paramEventTranslator)
    {
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.EventPublisher
 * JD-Core Version:    0.6.2
 */