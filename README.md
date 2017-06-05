# event-managment

## Getting Started
1. Install MySQL
2. Change the DB connection values in the application.yml file in src/main/resources
    * app.datasource.primary.url=<jdbc-url>
3. Run src/main/sql/event_management_ddl.sql script against your MySQL
4. Optional: Run src/test/sql/event_management_ddl.sql to seed with test data (1 event, 1 user)
4. Run 'mvn spring-boot:run' to start the service

## Events
### Add a new event
```POST /v1/events```
```json
{
  "title": "Daisy's Birthday", 
  "location":"Disneyland",
  "startTime":"2017-06-05T12:00:00",
  "endTime":"2017-06-05T17:00:00"
}
```
### Get all events
```GET /v1/events```

**Pagination:** ```GET /v1/events?page=2```

**Filter by title keyword:** ```GET /v1/events?titleKeyword=birthday```

### Get a specific event
```GET /v1/events/{eventId}```


### Delete an event
```DELETE /v1/events/{eventId}```

### Update an event
```PUT /v1/events/{eventId}```
```json
{
  "title": "Daisy's Birthday", 
  "location":"Disneyland",
  "startTime":"2017-06-10T12:00:00",
  "endTime":"2017-06-10T17:00:00"
}
```
## Users
### Add a user
```POST /v1/users```
```json
{
  "username": "mmouse", 
  "firstName":"Minnie",
  "lastName":"Mouse"
}
```

### Get all users
```GET /v1/users```

### Get a specific user
```GET /v1/users/{userId}```

### Delete a user
```DELETE /v1/users/{userId}```

### Update a user
```PUT /v1/users/{userId}```
```json
{
  "username": "mmouse2", 
  "firstName":"Minnie",
  "lastName":"Mouse"
}
```

## Invitations
### Invite users
```POST /v1/events/{eventId}/invitations```
```json
{
  "userId": 2
}
```

### Accept or decline invitation
```PUT /v1/events/{eventId}/invitations/{invitationId}```
```json
{
  "accept": true
}
```

### Get all invitations for an event
```GET /v1/events/{eventId}/invitations```

### Get a specific invitation
```GET /v1/events/{eventId}/invitations/{invitationId}```