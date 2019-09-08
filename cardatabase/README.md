# WIP:FullStack with Spring Boot 2 with React


## Run the application

Pre-requisite: Ensure you have docker daemon running on your machine.



Starts the stack (database (postgres) and the spring boot service) 
```
$ ./integration/stack.sh
```

Tears down the stack
```
$ ./integration/teardown.sh
```




## Backend

Is implemented as a Spring Boot Service with spring-boot-starter-data-rest (HAL). The backend service is secured with spring-security in combination with a signed JWT

### Authentication


![Authentication Flow](auth_flow.png)

### Login

```
$ http POST :8001/login username=user password=password

...

HTTP/1.1 200 
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTY0NTc5NzAyfQ.uK9FjjRCVmLU2YnluLOhLjiwJ7eqQv_tryzPkarK9fOdnbCg0fHQpri3fpzl0a-UHc86XSZFs8HUazxvz3ai_A
```

### Fetch resources

```
$ http :8001/api/cars/search/findByBrand brand==Nissan 'Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTY0NTc5NzAyfQ.uK9FjjRCVmLU2YnluLOhLjiwJ7eqQv_tryzPkarK9fOdnbCg0fHQpri3fpzl0a-UHc86XSZFs8HUazxvz3ai_A' 

...

HTTP/1.1 200 
{
    "_embedded": {
        "cars": [
            {
                "_links": {
                    "car": {
                        "href": "http://localhost:8001/api/cars/4"
                    }, 
                    "owner": {
                        "href": "http://localhost:8001/api/cars/4/owner"
                    }, 
                    "self": {
                        "href": "http://localhost:8001/api/cars/4"
                    }
                }, 
                "brand": "Nissan", 
                "color": "White", 
                "model": "Leaf", 
                "price": 18000, 
                "registerNumber": "ADF-1234", 
                "year": 2014
            }
        ]
    }, 
    "_links": {
        "self": {
            "href": "http://localhost:8001/api/cars/search/findByBrand?brand=Nissan"
        }
    }
}

```