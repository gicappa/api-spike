
# Jobengine API
When developing mobile app, third party application and even just for a sake of separation
of concerns having an API layer ease the application architecture.
This _spike_ is meant to try out a real (but limited) implementaion of a RESTful API taking
into account all the typical burden of such a piece of code:

* Versioning
* Security
* Authorization
* Spring integration
* Testing
* Exception handling
* Performances

## Quick start

```shell
    $ curl http://localhost:8080/api-spike/api/adverts
```

## REST Conventions adopted
<div class="alert alert-info">
  <strong>TO BE DONE</strong> here it should be presented how resources should be thought
  and how the POST is meant to create the PUT to update and the GET to obtain a resurce
  Another point could be the versioning policy
</div>


## Code

The spike code is hosted on http://github.com/gicappa/api-spike

The overall structure of the project is quite straigthforward. There are three layers:

* __application__: where it happens to translate the domain model languge into something
 understandable by the outside world and adapt to it. So the restful things - like REST
interface method definitions - will happens here.
* __domain__: will hold the domain logic
* __infrastructure__: will adapt to the persistence world.

## Versioning
<div class="alert">
  <strong>to be done</strong>
</div>

## Security
<div class="alert">
  <strong>to be done</strong>
</div>

## Authorization
<div class="alert">
  <strong>to be done</strong>
</div>

## Spring integration
Spring is a first

## Testing
<div class="alert alert-info">
  <strong>to be done</strong>
</div>

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

## Performances
to be done

## References
http://www.baeldung.com/rest-with-spring-series/

* It should answer to the correct accept (JSON/XML)
* It should add an HATEOAS