package jobs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import models.MongoClient;
import models.RabbitMQClient;
/*
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
*/
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart(async=true)
public class RabbitMessageListenerJob extends Job {

	public void doJob() {
		RabbitMQClient rabbitClient = new RabbitMQClient();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		MongoClient mongo = new MongoClient();
		try {
		    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		    mongo.showRecords();
/*		    Commented out for production deployment
 			Channel rabbitChannel = rabbitClient.getRideRequestChannel();
		    if(rabbitChannel != null) {
			    QueueingConsumer consumer = new QueueingConsumer(rabbitChannel);
			    rabbitChannel.basicConsume(rabbitClient.getRideQueue(), true, consumer);
			    
			    while (true) {
			      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			      String message = new String(delivery.getBody());
			      executor.execute(new RabbitMessageWorker(message));
			    }
		    }
		    else {
		    	Logger.error("Error in RabbitMessageListenerJob: Unable to find channel");
		    }
*/		}
	    catch(Exception ex) {
	    	Logger.error("Error in RabbitMessageListenerJob - "+ ex.toString());
	    }
	}
}
