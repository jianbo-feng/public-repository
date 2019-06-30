package com.feng.rabbitmq;

import com.feng.rabbitmq.entity.User;
import com.feng.rabbitmq.sender.DirectSender;
import com.feng.rabbitmq.sender.FanoutSender;
import com.feng.rabbitmq.sender.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {

	@Autowired
	private FanoutSender fanoutSender;
	@Autowired
	private TopicSender topicSender;
	@Autowired
	private DirectSender directSender;

	/**
	 * Fanout测试
	 * @throws Exception
	 */
	@Test
	public void testFanout() throws Exception {
		User user = new User();
		user.setId("1");
		user.setName("feng");
		fanoutSender.send(user);
	}



	/**
	 * TOPIC测试
	 * @throws Exception
	 */
	@Test
	public void testTopic() throws Exception {
		User user = new User();
		user.setId("1");
		user.setName("feng");
		topicSender.send(user);
	}

	/**
	 * DIRECT测试
	 * @throws Exception
	 */
	@Test
	public void testDirect() throws Exception {
		User user = new User();
		user.setId("1");
		user.setName("feng");
		directSender.send(user);
	}
}
