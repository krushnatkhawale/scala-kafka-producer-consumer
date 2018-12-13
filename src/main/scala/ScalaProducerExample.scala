import java.util.{Date, Properties}

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}


object ScalaProducerExample extends App {

  val date = new Date()

  val customerId = "customerId"
  val firstName = "firstName"
  val lastName = "lastName"
  val transNum = "" + System.currentTimeMillis()
  val transDate = date
  val transTime = date
  val unixTime = date.getTime
  val category = "s"
  val merchant = "s"
  val amount = 1.0
  val merchLatitude = 2.0
  val merchLongitude = 3.0

  val msg = "{" +
    "\"customerId\" : \"" + customerId + "\"," +
    "\"firstName\" : \"" + firstName + "\"," +
    "\"lastName\" : \"" + lastName + "\"," +
    "\"transNum\" : \"" + transNum + "\"," +
    "\"transDate\" : \"" + transDate + "\"," +
    "\"transTime\" : \"" + transTime + "\"," +
    "\"unixTime\" : " + unixTime + "," +
    "\"category\" : \"" + category + "\"," +
    "\"merchant\" : \"" + merchant + "\"," +
    "\"amount\" : " + amount + "," +
    "\"merchLatitude\" : " + merchLatitude + "," +
    "\"merchLongitude\" : " + merchLongitude + "" +
    "}"

  val events = args(0).toInt
  val topic = args(1)
  val brokers = args(2)
  val props = new Properties()

  props.put("bootstrap.servers", brokers)
  props.put("client.id", "ScalaProducerExample")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)
  val data = new ProducerRecord[String, String](topic, transNum, msg)

  producer.send(data)
  System.out.println(" Sent: " + data)
  producer.close()
}