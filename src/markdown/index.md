# Jobengine API

- - -

When developing mobile app, third party application and even just for a sake of separation
of concerns having an API layer ease the application architecture.
This _spike_ is meant to try out a real (but limited) implementaion of a RESTful API taking
into account all the typical burden of such a piece of code:

* Versioning
* Authorization
* Spring integration
* Testing
* Exception handling
* Performances

## Quick start

```shell
 $ curl http://localhost:8080/api-spike/api/adverts
```

# Accessing the APIs
<div class="alert alert-info">
  <strong>TO BE DONE</strong> here it should be presented how resources should be thought
  and how the POST is meant to create the PUT to update and the GET to obtain a resurce
  Another point could be the versioning policy
</div>

# Developing the APIs
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

## Authentication/Authorization
OAuth2 is a protocol that lets external apps request authorization to private details in a user’s
GitHub account without getting their password. This is preferred over Basic Authentication
because tokens can be limited to specific types of data, and can be revoked by users at any time.

All developers need to register their application before getting started. A registered OAuth
application is assigned a unique Client ID and Client Secret. The Client Secret should not
be shared.
<div class="alert alert-info">
  <strong>work in progress</strong>
</div>

## Spring integration
Spring is a first class citizen

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

# Notes
Controllare se sia possibile specificare nell'header quali tipi (mime type - HTML? / JSON / XML)
di risposte siamo in grado di fornire e blindare le altre


Manca il log del webserver jetty di test che e` stato istanziato per cui non si vede l'errore
server side ma solo client side.


Metafora chiave del valletto: " Many luxury cars come with a valet key. It is a special key you give the parking attendant and unlike your regular key, will only allow the car to be driven a short distance while blocking access to the trunk and the onboard cell phone. Regardless of the restrictions the valet key imposes, the idea is very clever. You give someone limited access to your car with a special key, while using another key to unlock everything else."

A cosa serve OAuth: "OAuth provides a method for users to grant third-party access to their resources without sharing their passwords. It also provides a way to grant limited access (in scope, duration, etc.)."

SSL: API under ssl server?


 Eran Hammer
http://hueniverse.com/2012/07/oauth-2-0-and-the-road-to-hell/
All the hard fought compromises on the mailing list, in meetings, in special design committees, and in back channels resulted in a specification that fails to deliver its two main goals – security and interoperability. In fact, one of the compromises was to rename it from a protocol to a framework, and another to add a disclaimer that warns that the specification is unlike to produce interoperable implementations.
