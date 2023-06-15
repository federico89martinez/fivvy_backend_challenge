DemoApplication - Challange - Fivvy

Required

Java 1.8
MongoDB Compass 1.37.0
Postman 10.14.7
Docker 24.0.2

Configuration

You need to change the following variables in MongoDBUtil

DATABASE_NAME [your_database]
COLLECTION_NAME [your_collection]
CONNECTION [local endpoint]  --mongodb://localhost:27017

Use start

Run the project in your IDE and check Postman for the following examples.

POST - DISCLAIMER:

EndPoint: http://localhost:8080/filter

Body:

{
"Id": "1",
"name": "federico",
"text": "I am programming",
"version": "1.0.0",
"create_at": "20/10/2023",
"update_at": "25/10/2023"
}

GET - DISCLAIMER:

EndPoint: http://localhost:8080/get-text?text=[yourText]

Result:

[
{
"_id": {
"timestamp": 1686834655,
"date": "2023-06-15T13:10:55.000+00:00"
},
"Id": 1,
"name": "federico",
"text": "I am programming",
"version": "1.0.0",
"create_at": "20/10/2023",
"update_at": "25/10/2023"
},
{
"_id": {
"timestamp": 1686858081,
"date": "2023-06-15T19:41:21.000+00:00"
},
"Id": 2,
"name": "federico",
"text": "I am programming",
"version": "1.0.0",
"create_at": "20/10/2023",
"update_at": "25/10/2023"
},
{
"_id": {
"timestamp": 1686859925,
"date": "2023-06-15T20:12:05.000+00:00"
},
"Id": 3,
"name": "federico",
"text": "I am programming",
"version": "1.0.0",
"create_at": "20/10/2023",
"update_at": "25/10/2023"
}
]

DELETE - DISCLAIMER:

EndPoint: http://localhost:8080/delete-text?text=[yourText]

PATCH - DISCLAIMER:

EndPoint: http://localhost:8080/update-text?text=[yourText]

POST - ACCEPTANCE:

http://localhost:8080/get-user?user_id=1

{
"disclaimer_id": 1,
"user_id": 2,
"create_at": "20/10/2023"
}

result

{
"disclaimer_id": 1,
"user_id": 2,
"create_at": "20/10/2023"
}



GET - ACCEPTANCE:

EndPoint: http://localhost:8080/get-user?user_id=2

result:

[
{
"_id": {
"timestamp": 1686861516,
"date": "2023-06-15T20:38:36.000+00:00"
},
"disclaimer_id": 1,
"user_id": 2,
"create_at": "20/10/2023"
}
]


Docker Environment:

docker build -t "demo-boot-fivvy" . 
docker pull mongo:latest
docker pull openjdk:8-jdk-slim
docker images
docker run --name demo-fivvy -p 8080:8080 demo-boot-fivvy:latest