# hashed-cache

## Overview

A sample project on how to do caching over a POST endpoint, by generating a hash over the values
which will remain same in the other requests.

## Features

- Endpoint `/v1/personal` to create a person record, example request body as below
```json
{
  "name": "John Doe",
  "profession": "Developer",
  "gender": "male"
}
```
- Endpoint `/v1/personal/search` to search for a person, example request body as below
```json
{
  "query_id": "e2c56db5-dffb-48d2-94f4-714bc1ff96c8",
  "id": 10,
  "name": "John"
}
```
- The second endpoint incorporates the cache mechanism as well, by ignoring the `query_id`

## Getting Started

### Prerequisites

- Redis instance for caching and PostgreSQL server as the database of choice
- Both the services, can be bought up by using the `datasource.yaml` in `resources` directory.
```shell
docker-compose -f resoruces/datasource.yaml -d up
```

### Installation

- Clone the repository:
```shell
git clone --branch <branch-name> https://github.com/anadinema/hashed-cache.git <directory>
```
- To run the application:
```shell
mvn quarkus:dev
```
- The properties can be changed in `application.properties` under the resource maven project.

## Acknowledgements

- Uses Quarkus - 3.11.1
- Database - PostgreSQL
- Caching infra - Redis (redis-stack)

## License

Licensed under the MIT License. See the [LICENSE](LICENSE) file for details.