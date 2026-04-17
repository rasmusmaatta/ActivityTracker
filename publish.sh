#!/bin/bash
./mvnw package -DskipTests
scp target/activitytracker-0.0.1-SNAPSHOT.jar rasmusm@softala.haaga-helia.fi:
ssh rasmusm@softala.haaga-helia.fi "java -jar activitytracker-0.0.1-SNAPSHOT.jar"