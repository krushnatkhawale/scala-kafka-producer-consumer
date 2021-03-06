import java.util.concurrent._
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

import scala.collection.JavaConversions._

class ScalaConsumerExample(val brokers: String,
                           val groupId: String,
                           val topic: String) {

  val props = createConsumerConfig(brokers, groupId)
  val consumer = new KafkaConsumer[String, String](props)
  var executor: ExecutorService = null

  def shutdown() = {
    if (consumer != null)
      consumer.close();
    if (executor != null)
      executor.shutdown();
  }

  def createConsumerConfig(brokers: String, groupId: String): Properties = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props
  }

  def run() = {
    consumer.subscribe(Collections.singletonList(this.topic))

    Executors.newSingleThreadExecutor.execute(new Runnable {
      override def run(): Unit = {
        while (true) {
          val records = consumer.poll(1000)

          for (record <- records) {
            System.out.println("Received message: (" + record.key() + ") at offset " + record.offset())
            System.out.println()
            System.out.println(record.value())
            System.out.println()

          }
        }
      }
    })
  }
}

object ScalaConsumerExample extends App {
  val example = new ScalaConsumerExample("ec2-35-159-32-107.eu-central-1.compute.amazonaws.com:9092", "group1", "fraud_messages_1")
  example.run()
}