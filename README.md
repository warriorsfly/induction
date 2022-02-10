#Induction
## Mission
- websocket server
- task server
- chat server

### For database development (default sql server)
- see `resources/application.properties`

*TODO*:
1. add init schema.sql
2. add sharding sphere
3. test db server tls version is too low, need to fix it


### For start up rabbitmq development
- install docker desktop
- shell `docker-compose up`
- go to rabbitmq container,shell:`rabbitmq-plugins enable rabbitmq_stomp`

*TODO*
- add test page