# dasKafka
Message Queuing project @ iti intake 40
The utility takes the record and counts the number of repeated words in the sentence then displays them

![hg](https://github.com/theJaxon/dasKafka/blob/master/Preview.jpg)

### Documentation:
* [Kafka](https://kafka.apache.org/quickstart) Get apache Kafka binaries from here.
* Extract the zip file and cd into the kafka folder

#### 1-Start ZooKeeper server:
```
.\bin\windows\zookeeper-server-start.bat config/zookeeper.properties
```
#### 2-Start Apache Kafka server:
```
.\bin\windows\kafka-server-start.bat config/server.properties
```
#### 3-Create a topic:
```
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic topicName
```
#### 4-List all topics:
```
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
```
#### 5-Start a consumer (optional)
```
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
```

#### Using the utility:
Enter the topic name that you created in step 3 in the first field, enter the record in the second field and press on the "Produce button", after that click the consume button (for now click it 2 times, there's an error which i wasn't able to solve).

