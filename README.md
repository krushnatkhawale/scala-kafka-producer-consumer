# Amazon EC2 Kafka setup

```bash
ssh -i "demo.pem" ec2-user@ec2-35-159-32-107.eu-central-1.compute.amazonaws.com
Last login: Fri Dec 14 12:44:12 2018 from 82-132-231-27.dab.02.net

       __|  __|_  )
       _|  (     /   Amazon Linux AMI
      ___|\___|___|

https://aws.amazon.com/amazon-linux-ami/2018.03-release-notes/
[ec2-user@ip-172-31-7-25 ~]$ grep "9092" /opt/kafka_2.11-2.1.0/config/server.properties 
#     listeners = PLAINTEXT://your.host.name:9092
listeners=PLAINTEXT://ec2-35-159-32-107.eu-central-1.compute.amazonaws.com:9092
#advertised.listeners=PLAINTEXT://your.host.name:9092
[ec2-user@ip-172-31-7-25 ~]$ 
```

# Kafka Producer

```javascript
 Sent: ProducerRecord(topic=fraud_messages_1, partition=null, key=1544790908500, 
            {
              "customerId": "180094108369013",
              "firstName": "Martyn",
              "lastName": "Lewis",
              "transNum": "80f5177be11f0bcd768e06a0b1b294c8",
              "transDate": "2012-01-01T00:00:00.000+05:30",
              "transTime": "00:12:15",
              "unixTime": 1325376735,
              "category": "personal_care",
              "merchant": "Hills-Boyer",
              "amount": 64.0,
              "merchLatitude": 51.41097,
              "merchLongitude": -0.83493
            },    
    timestamp=null)
```


# Kafka Consumer

```javascript
Received message: (1544790860531) at offset 255

{"customerId" : "180094108369013", "firstName" : "Martyn", "lastName" : "Lewis", 
"transNum" : "80f5177be11f0bcd768e06a0b1b294c8", "transDate" : "2012-01-01T00:00:00.000+05:30", 
"transTime" : "00:12:15", "unixTime" : 1325376735, "category" : "personal_care", 
"merchant" : "Hills-Boyer", "amount" : 64.0, "merchLatitude" : 51.41097, "merchLongitude" : -0.83493}

Received message: (1544790890861) at offset 256

{"customerId" : "180094108369013", "firstName" : "Martyn", "lastName" : "Lewis", 
"transNum" : "80f5177be11f0bcd768e06a0b1b294c8", "transDate" : "2012-01-01T00:00:00.000+05:30", 
"transTime" : "00:12:15", "unixTime" : 1325376735, "category" : "personal_care", 
"merchant" : "Hills-Boyer", "amount" : 64.0, "merchLatitude" : 51.41097, "merchLongitude" : -0.83493}

Received message: (1544790908500) at offset 257

{"customerId" : "180094108369013", "firstName" : "Martyn", "lastName" : "Lewis", 
"transNum" : "80f5177be11f0bcd768e06a0b1b294c8", "transDate" : "2012-01-01T00:00:00.000+05:30", 
"transTime" : "00:12:15", "unixTime" : 1325376735, "category" : "personal_care", 
"merchant" : "Hills-Boyer", "amount" : 64.0, "merchLatitude" : 51.41097, "merchLongitude" : -0.83493}
```