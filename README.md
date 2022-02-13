# Induction
## Mission
- websocket server
- task server
- chat server
## Stacks
- Spring boot stomp
- Spring boot webflux
- reactive database connection
- redis stream

### For database development (default postgres)
- see `resources/application.properties`

*TODO*:
- [ ] add init schema.sql
- [ ] add sharding sphere
- [ ] test db server tls version is too low, need to fix it


### For start up rabbitmq development
- install docker desktop
- shell `docker-compose up`

*TODO*

- [ ]  add test page