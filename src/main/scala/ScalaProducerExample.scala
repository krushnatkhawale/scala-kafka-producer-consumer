import java.util.{Date, Properties}

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}


object ScalaProducerExample extends App {

  val john = aMessage(
    "180094108369013",
    "John", "Holland",
    "80f5177be11f0bcd768e06a0b1b294c8",
    "2012-01-01T00:00:00.000+05:30", "00:12:15", 1325376735,
    "personal_care", "Hills-Boyer",
    64,
    39.011566, -119.937831
  )

  val carla = aMessage(
    "4368593032190508",
    "Carla", "Fleming",
    "7933d389bf8ef8a11a8a301da6e6bc6c",
    "2012-01-01T00:00:00.000+05:30", "00:16:58", 1325377018,
    "gas_transport", "Friesen-DAmore",
    133,
    40.149071, -75.589697)

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

  val topic = "fraud_messages_1"
  val brokers = "ec2-35-159-32-107.eu-central-1.compute.amazonaws.com:9092"
  val props = new Properties()

  props.put("bootstrap.servers", brokers)
  props.put("client.id", "ScalaProducerExample")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)
  val data = new ProducerRecord[String, String](topic, transNum, john)

  producer.send(data)
  System.out.println(" Sent: " + data)
  producer.close()


  def aMessage(customerId: String, firstName: String, lastName: String, transNum: String, transDate: String,
               transTime: String, unixTime: Long, category: String, merchant: String, amount: Double,
               merchLatitude: Double, merchLongitude: Double) = {
    "{" +
      "\"customerId\" : \"" + customerId + "\", " +
      "\"firstName\" : \"" + firstName + "\", " +
      "\"lastName\" : \"" + lastName + "\", " +
      "\"transNum\" : \"" + transNum + "\", " +
      "\"transDate\" : \"" + transDate + "\", " +
      "\"transTime\" : \"" + transTime + "\", " +
      "\"unixTime\" : " + unixTime + ", " +
      "\"category\" : \"" + category + "\", " +
      "\"merchant\" : \"" + merchant + "\", " +
      "\"amount\" : " + amount + ", " +
      "\"merchLatitude\" : " + merchLatitude + ", " +
      "\"merchLongitude\" : " + merchLongitude + "" +
      "}"
  }
}