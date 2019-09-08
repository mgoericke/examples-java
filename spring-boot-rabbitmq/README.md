# Spring-Boot-RabbitMQ Demo

```bash
# ensure docker is running
./bin/stack.sh
```

to start a new stack with the following services (docker containers as defined in ./integration/docker-compose.yml) ...

* rabbitmq
    * management console listens at port 15672
    * user/pass - guest:guest
* producer
    * this application with profile 'producer'
    * creates new events - interval 2 seconds
* consumer_resources
    * this application with profile 'consumer'
    * listen for new events with routingKey 'resource.*'
* consumer_otherevents
    * this application with profile 'consumer'
    * listen for new events with routingKey 'otherevent'


check the logs ...
```
producer_1              ... [+] Message sent: 265720c0-9353-4ff9-ba4a-fda4fe29a884:resource.created
consumer_resources_1    ... [*] Message received: 265720c0-9353-4ff9-ba4a-fda4fe29a884:resource.*
producer_1              ... [+] Message sent: ba49fd61-cb16-4c64-9f06-cde6f46815cb:resource.edited
consumer_otherevents_1  ... [*] Message received: ba49fd61-cb16-4c64-9f06-cde6f46815cb:otherevent
```

to tear down the whole stack ...
```bash
./bin/teardown.sh
```

