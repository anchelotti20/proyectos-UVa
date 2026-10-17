mvn clean jacoco:prepare-agent test install jacoco:report sonar:sonar ^
 -Dsonar.projectKey=tds_practica_grupoa ^
 -Dsonar.projectName='tds_practica_grupoa' ^
 -Dsonar.host.url=https://sonarqube.inf.uva.es ^
 -Dsonar.token=sqp_0c163389e934d6b838e08e7e89520aa128b798d4