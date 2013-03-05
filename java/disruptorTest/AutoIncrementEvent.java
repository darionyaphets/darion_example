package disruptorTest;
import com.lmax.disruptor.EventFactory;
import com.trevorbernard.disruptor.examples.ValueEvent;

public final class AutoIncrementEvent
{
	private long counter = 0;

	public long getCounter()
	{
		return counter;
	}

	public void setCounter(long counter)
	{
		this.counter = counter;
	}

	public final static EventFactory<AutoIncrementEvent> EVENT_FACTORY = new EventFactory<AutoIncrementEvent>()
	{
		public AutoIncrementEvent newInstance()
		{
			return new AutoIncrementEvent();
		}
	};
}