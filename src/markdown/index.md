# What you cam find here?
An automatic API documentation can be found clicking on [Swagger](swagger/)

[api adverts](api/adverts)

# Documentation
## Overview
The overall structure of the project is quite straigthforward. There are three layers:
* __application__: where it happens to translate the domain model languge into something
understandable by the outside world and adapt to it. So the restful things - like REST
interface method definitions - will happens here.
* __domain__: will hold the domain logic
* __infrastructure__: will adapt to the persistence world.


## Exception handling
Exceptions are mapped in a  ``ControllerAdvice`` class: here is possible to map wich exception
will be mapped to what kind of response status code.
```java
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFound.class})
    protected ResponseEntity<Object> handleObjectNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "the requested resource is not existent", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
```


# References
http://www.baeldung.com/rest-with-spring-series/

* It should answer to the correct accept (JSON/XML)
* It should add an HATEOAS
