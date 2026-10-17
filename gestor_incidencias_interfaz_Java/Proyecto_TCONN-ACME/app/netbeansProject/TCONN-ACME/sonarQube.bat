mvn clean jacoco:prepare-agent test install jacoco:report sonar:sonar ^
  -Dsonar.projectKey="es.uva.inf.ds25-26-05" ^
  -Dsonar.projectName="Practica Diseno Software 2025-2026 Equipo 05" ^
  -Dsonar.host.url="https://sonarqube.inf.uva.es" ^
  -Dsonar.token="squ_d4bc07b2de7a98b8e2d2f99bf87a2a8044bd8b4a" ^