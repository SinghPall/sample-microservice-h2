# sample-microservice-h2
Sample ms to integrate with h2 database(JpaRepository with Custom repository)

- For CRUD, used JpaRepository. Also added CustomRepository.
- created interface for custom epository, given impl fo rthe same.
- Added CustomException for ResourceNotFoundException
- Added @Validated on controller class to apply validation on PathVariabls while getting ids in GET/UPDATE etc
- Rest end ponits GET : /persons, GET: /person/{id}, GET : /persondetails/{id} etc
