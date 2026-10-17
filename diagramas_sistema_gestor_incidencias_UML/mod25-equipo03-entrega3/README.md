# mod25-equipo03-entrega3

Documento README del grupo 3 para la entrega 3 de Modelado de Sistemas Software.

Autores:
- Marcos Gómez Medina
- Ángel Antolín Caramazana

## Restricciones OCL

- No se pueden crear incidencias de contratos que no se encuentren en activo
context Incidencia::Incidencia(codigo: CodigoIncidencia, descripcion: String, criticidad: NivelCriticidad, contrato: Contrato)
pre: contrato.activo = true
post: self.estado = EstadoIncidencia:: sinResolver 


- Si un contrato ya no está activo, se anota la fecha de de fin de contrato
Suponemos que el DataType Fecha tiene una operacion "mismaFecha(fecha)" que comprueba si 2 fechas son iguales
context Contrato::asignarFechaFin(fechaFinal: Fecha)
pre: contrato.activo = true
post: contrato.activo = false and self.fechaFin.mismaFecha(fechaFinal)


- Los contratos de los clientes particulares comparten el mismo SLA
context Contrato
inv: self.allInstances -> forAll(c1, c2| c1.titular.oclIsTypeOF(ClienteParticular) and c2.titular.oclIsTypeOF(ClienteParticular) and c1 <> c2 implies c1.sla = c2.sla)

- Los clientes particulares no contratan servicios avanzados mientras que los clientes empresariales contratan servicios avanzados y básicos
context ServicioContratado
inv: self.oclIsTypeOF(ServicioAvanzadoContratado) = true implies self.contrato.titular -> forAll(t: Cliente | t.oclIsTypeOF(ClienteEmpresarial))

- Si un coordinador es el responsable de atender una Llamada es porque la llamada se produce en su turno de trabajo
Suponemos una operacion "horaFin()" para HorarioTurnoCoordinador que devuelve la hora de finalizacion del turno de tipo Hora, lo mismo para "HoraInicio()"
que devolveria la hora de inicio del turno
context Llamada
inv: self.hora > self.coordinador.turnoCoordinador -> last().horario.horaInicio() and self.hora < self.coordinador.turnoCoordinador -> horario.horaFin()


- No hay instrucciones ni operaciones remotas posteriores a una que haya resuelto la incidencias
context: Incidencia
inv: self.estado = EstadoIncidencia::resueltaRemotamente and self.accionRemota -> notEmpty() implies self.accionRemota -> last().resuelve = true


- El vehiculo asignado al tecnico pertenece a la misma base
context  Vehiculo
Suponemos que el DataType Direccion tiene una operacion "iguales" que comprueba si 2 direcciones son iguales y que el
DataType Ubicacion también tiene una operacion "mismasCoordenadas" que comprueba si 2 coordenadas son iguales
inv: self.base.direccion.iguales(self.tecnico.base.direccion) and self.base.ubicacion.mismasCoordenadas(self.tecnico.base.ubicacion)



- La empresa necesita contar con al menos 2 tecnicos disponibles por zona geografica
Suponemos una operacion en la clase ZonaGeografica "tecnicosDisponibles()" que devuelve una lista con los tecnicos cuyo estado es Disponible
context Tecnicos::cambiarEstado(estado: EstadoTecnico)
pre: estado <> EstadoTecnico:: disponible implies self.base.ZonaGeografica.tecnicosDisponibles() -> size() > 2
post: self.estado = estado


- Se utilizan las mismas unidades de forma consistente cuando se refieren al mismo tipo de material
context Material
inv: self.allInstances -> forAll(m1, m2 | m1 <> m2 and m1.tipoMaterial = m2.tipoMaterial implies m1.unidad = m2.unidad)


- Se crea una penalización si el tiempo total de resolución de una incidencia supera el tiempo máximo de 
resolución del SLA correspondiente. El coste de la penalización es el establecido en dicho SLA.
context Penalizacion::Penalizacion(incidencia:Incidencia, sla:SLA)
pre: incidencia.tiempoTotalResolucionEnMinutos > sla.tiempoResolucionMaximoEnHoras * 60
post: self.coste = sla.penalizacionPorIncumplimiento


- Tras finalizar un turno de trabajo, es obligatorio un período mínimo de descanso de 12 horas antes de poder 
ser asignado nuevamente.
Suponemos una operacion "horaFin()" para HorarioTurnoCoordinador que devuelve la hora de finalizacion del turno de tipo Hora y suponemos la operacion
"diferenciaHoras(hora: Hora)" para el DataType Hora que devuelve la difencia en horas entre la hora dada y la que llama a la operacion  
context Empleado::asignarEmpleado(horaInicio:Hora)
pre: self.oclIsTypeOF(Coordinador) implies self.oclAsType(Coordinador).turnoCoordinador -> last().horario.horaFin().diferenciaHoras(horaInicio) >= 12
pre: self.oclIsTypeOF(Tecnico) impies self.oclAsType(Tecnico).turnoTecnico -> last().horaFin.diferenciaHoras(horaInicio) >= 12



- Si un parte indica que queda pendiente de segunda visita o necesita escalado a un técnico de nivel superior, 
la incidencia tendrá acciones de servicio siguientes, la clase de servicio será consistente con esto y la fecha de creación 
de la acción siguiente coincidirá con la del parte.
context AccionDesServicio
inv: self.parte-> notEmpty() and 
(self.parte.estadoActual= EstadoIncidencia::parcialmenteResuelta or self.parte.estadoActual= EstadoIncidencia::escaladaAEspecialista)
implies self.siguientes-> notEmpty() and self.siguientes->forAll(sig: siguiente| sig.fechaDeCreacion.mismaFecha(sig.parte.firmaTecnico.fecha))

## Casos de uso

- **Actor:** Coordinador
  - **Caso de uso:** Anotar incidencia
  - **Nombre de la operacion:** `registarIncidencia(descripcion:String, criticidad:NivelCriticidad)`

- **Actor:** Tecnico
  - **Caso de uso:** Aceptar Incidencia
  - **Nombre de la operacion:** `aceptarIncidencia()`

- **Actor:** Coordinador
  - **Caso de uso:** Realizar Acciones Remotas
  - **Nombre de la operacion:** `accionRemota()`

- **Actor:** Coordinador
  - **Caso de uso:** Asignar Tecnico
  - **Nombre de la operacion:** `asignarTecnico()`

- **Actor:** Técnico
  - **Caso de uso:** Aplicar Procedimientos Tecnicos
  - **Nombre de la operacion:** `aplicarProcedimiento()`

- **Actor:** Técnico
  - **Caso de uso:** Rellenar Parte
  - **Nombre de la operacion:** `rellenarParte()`

- **Actor:** Técnico
  - **Caso de uso:** Escalar Incidencia A Especialista
  - **Nombre de la operacion:** `escalarIncidencia()`

- **Actor:** Técnico
  - **Caso de uso:** Resuelve Incidencia
  - **Nombre de la operacion:** `resolverIncidencia()`

- **Actor:** Técnico
  - **Caso de uso:** Informar Falta de Materiales
  - **Nombre de la operacion:** `faltanMateriales()`

# Diagrama Inicial
![DiagramaInicial](/images/DiagramaInicial.png)

# Diagrama Final
## En PlanTUML
![DiagramaUML](/images/DiagramaFinal.png)

## En VISUAL PARADIGM
![DiagramaUML](/images/DiagramaFinalVisual.jpg)

Nos parecía que el diagrama de estados generado en PlantUML no se veía con la suficiente claridad, por ello, hemos optado por pasarlo a Visual Paradigms.
