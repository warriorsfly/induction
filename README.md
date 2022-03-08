# Col
## Mission
- websocket server
- task server
- chat server
## Stacks
- Spring boot stomp with rabbitmq
- Spring boot webflux
- reactive database connection
- maybe redis include

### For database development (default sql server)
- see `resources/application.properties`

*TODO*:
- [ ] add init schema.sql
- [ ] add sharding sphere
- [ ] test db server tls version is too low, need to fix it


### For start up rabbitmq development
- install docker desktop
- shell `docker-compose up`
- go to rabbitmq container,shell:`rabbitmq-plugins enable rabbitmq_stomp`

*TODO*

- [ ]  add test page