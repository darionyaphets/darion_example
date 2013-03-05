package disruptorTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class disruptorTest
{
	public static void main(String[] args)
	{
		ExecutorService service = Executors.newSingleThreadExecutor();
		Disruptor<AutoIncrementEvent> event = new Disruptor<AutoIncrementEvent>(
				AutoIncrementEvent.EVENT_FACTORY, 1024 * 32, service);

		EventHandler<AutoIncrementEvent> handler = new EventHandler<AutoIncrementEvent>()
		{
			@Override
			public void onEvent(AutoIncrementEvent event, long sequence,
					boolean end) throws Exception
			{
				System.out.println("auto increment : " + event.getCounter());
			}
		};

		event.handleEventsWith(handler);
		RingBuffer<AutoIncrementEvent> ringBuffer = event.start();

		for (int index = 0; index < 1000; index++)
		{
			long seqenceNum = ringBuffer.next();
			AutoIncrementEvent autoIncrementEvent = ringBuffer.get(seqenceNum);
			autoIncrementEvent.setCounter(index);
			ringBuffer.publish(seqenceNum);
		}
		
		event.shutdown();
		service.shutdown();
	}
}
