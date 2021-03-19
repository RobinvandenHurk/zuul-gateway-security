# User Injection Library
This library may be used in microservices behind the Gateway to easily inject user objects into the requests forwarded by the gateway

1. Build in Intellij (With custom artifact to /compiled)
2. `mvn install:install-file -Dfile=compiled/user-injection.jar -DgroupId=com.robinvandenhurk.gateway.library -DartifactId=library-user-injection -Dversion=0.2 -Dpackaging=jar`