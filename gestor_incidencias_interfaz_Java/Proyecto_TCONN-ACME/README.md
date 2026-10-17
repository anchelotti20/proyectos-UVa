# TCONN-ACME - Sistema de Gestión de Empleados

Este proyecto implementa el subsistema de gestión para la central y bases de la empresa **TCONN-ACME**. La aplicación permite la gestión de empleados, roles y disponibilidad bajo una arquitectura de escritorio robusta.

## ⚙️ Arquitectura y Diseño

El sistema ha sido desarrollado siguiendo una **arquitectura de tres capas estricta**, asegurando la separación de responsabilidades y el cumplimiento del patrón **MVC (Modelo-Vista-Controlador)**:

1.  **Capa de Interfaz (Presentación):** Desarrollada con **Java Swing**. Utiliza un `GestorVistas` (patrón Singleton) para coordinar la navegación y controladores de vista para desacoplar la lógica de los componentes gráficos.
2.  **Capa de Negocio (Lógica de Dominio):** Contiene las entidades del modelo (`Empleado`, `Puesto`, `TurnoTecnico`, etc.) y los controladores de caso de uso que implementan las reglas de negocio (ej. RN-2 sobre estados de actividad y RN-3 sobre disponibilidad mínima por zona).
3.  **Capa de Persistencia (Acceso a Datos):** Implementa el patrón **DAO (Data Access Object)**. El acceso a la base de datos se realiza mediante JDBC, y la comunicación con la capa de negocio se hace a través de objetos **JSON** que actúan como DTOs, mapeando de forma segura enumerados y herencias complejas de PostgreSQL a Java.

## 🚀 Configuración del Entorno

### Requisitos
* **Java:** JDK 21.
* **Gestión de proyectos:** Maven.
* **Base de Datos:** PostgreSQL.

### Conexión a la Base de Datos
La aplicación lee la configuración de la base de datos a través de **variables de entorno** para mayor seguridad y flexibilidad:
* `DB_HOST`: URL de conexión, `jdbc:postgresql://virtual.lab.inf.uva.es:37072/tconn-acme`.
* `DB_USER`: Usuario de la base de datos.
* `DB_PASSWORD`: Contraseña de acceso.

*Nota: Para ejecución local en NetBeans, estas variables están configuradas en el archivo `nbactions.xml`*.

## 🧪 Pruebas de Aceptación

Para validar los Casos de Uso **Identificarse**, **Cambiar Disponibilidad**, **Consultar Incidencias** y **Atender Llamada**, se incluye un script de automatización de población de datos. **Todos los empleados de prueba tienen configurada la contraseña unificada: `1234`**.

### 1. Preparación del Entorno
Ejecute los scripts SQL (`createTablesActualizado.sql` y `pruebas_cuAtenderLlamada.sql`) que insertan los siguientes perfiles de prueba:

* **Tecnico1 (NIF: 10000001A):** Técnico en Zona Norte (Cumple RN-3, asignado, en turno).
* **Coord1 (NIF: 20000002B):** Coordinador activo.
* **Super1 (NIF: 30000003C):** Supervisor activo.
* **ERTE1 (NIF: 40000004D):** Empleado en ERTE.
* **Despedido1 (NIF: 50000005E):** Empleado despedido.
* **Vacaciones1 (NIF: 60000006F):** Empleado de vacaciones.
* **Baja1 (NIF: 70000007G):** Empleado de baja temporal.
* **TecSur1 (NIF: 10000010J):** Técnico en Zona Sur (Incumple RN-3, asignado, en turno).
* **TecFueraTurno (NIF: 12000012L):** Técnico cuyo turno fue ayer.
* **TecNoAsignado (NIF: 13000013M):** Técnico en turno, pero su último estado fue "Disponible".
* **Juan Perez (NIF: 11111111A):** Cliente particular con contrato activo (ESVLL111).
* **Empresa SA (CIF: B22222222):** Cliente empresarial con contrato activo (ESVLL222).
* **Luis Gomez (NIF: 33333333C):** Cliente particular con contrato inactivo (ESVLL333).

---

### 2. Ejecución: Caso de Uso "Identificarse" (Login)
Inicie la aplicación y verifique el comportamiento del login:

* **NIF: 10000001A / Pass: 1234** ➔ Debe iniciar sesión correctamente y redirigir a la vista de Técnico.
* **NIF: 20000002B / Pass: 1234** ➔ Debe iniciar sesión correctamente y mostrar mensaje de rol COORDINADOR.
* **NIF: 30000003C / Pass: 1234** ➔ Debe iniciar sesión correctamente y mostrar mensaje de rol SUPERVISOR.
* **NIF: 99999999Z / Pass: 1234** ➔ Error: *Usuario incorrecto* (No existe en BD).
* **NIF: 10000001A / Pass: 0000** ➔ Error: *Contraseña incorrecta*.
* **NIF: 40000004D / Pass: 1234** ➔ Error RN-2: *Empleado no activo* (En ERTE).
* **NIF: 50000005E / Pass: 1234** ➔ Error RN-2: *Empleado no activo* (Despedido).
* **NIF: 60000006F / Pass: 1234** ➔ Error RN-2: *Empleado no activo* (De vacaciones).
* **NIF: 70000007G / Pass: 1234** ➔ Error RN-2: *Empleado no activo* (Baja temporal).

---

### 3. Ejecución: Caso de Uso "Cambiar Disponibilidad" (Técnicos)
Una vez identificado con éxito como Técnico, pruebe los siguientes escenarios simulando la llegada a la base:

* **Secuencia Principal (Cumple RN-3):** Iniciar sesión con **Tecnico1 (10000001A)**. Seleccionar pasar a estado *No Disponible*. El sistema debe registrar el cambio exitosamente porque la Zona Norte tiene 2 técnicos adicionales disponibles.
* **Excepción (Incumple RN-3):** Iniciar sesión con **TecSur1 (10000010J)**. Seleccionar pasar a estado *No Disponible*. El sistema debe lanzar un error indicando que faltan técnicos en la Zona Sur, obligando a seleccionar *Disponible*.
* **Excepción (Fuera de turno):** Iniciar sesión con **TecFueraTurno (12000012L)**. Al intentar acceder, el sistema debe lanzar un error indicando que el técnico no se encuentra en turno actualmente.
* **Excepción (No asignado):** Iniciar sesión con **TecNoAsignado (13000013M)**. Al intentar registrar la llegada, el sistema debe bloquear el proceso indicando que el último estado no era "Asignado a incidencia".

---

### 4. Ejecución: Caso de Uso "Consultar Incidencias" (Supervisores)
Una vez identificado con éxito como Supervisor, iniciar sesión con **Super1 (30000003C)**, pruebe los siguiente escenario simulando la consulta de incidencias:
* **Secuencia Principal:** Seleccionar la fecha *7-mayo-2026* y pulsa *Confirmar*. El sistema debe mostrar en la parte derecha 4 incidencias ordenadas por la hora de la llamada. Selecciona una de las incidencias y pulsa *Consultar detalles*. El sistema debe mostrar otra pantalla con Not implemented yet.
* **Secuencia Principal (Con filtro):** Seleccionar la fecha *7-mayo-2026*, selecciona *Resueltas remotamente* y pulsa *Confirmar*. El sistema debe mostrar en la parte derecha 2 incidencias ordenadas por la hora de la llamada.
* **Excepción (Fecha incorrecta):** Seleccionar la fecha *31-febrero-2026* y pulsa *Confirmar*. El sistema debe mostrar un mensaje de error indicando que la fecha no es válida.
* **Excepción (Fecha posterior):** Seleccionar la fecha *1-julio-2026* y pulsa *Confirmar*. El sistema debe mostrar un mensaje de error indicando que la fecha es posterior a la fecha actual.
* **Excepción (Sin incidencias):** Seleccionar la fecha *1-enero-2026* y pulsa *Confirmar*. El sistema debe mostrar un mensaje indicando que no hay incidencias registradas para esa fecha.

---

### 5. Ejecución: Caso de Uso "Atender Llamada" (Coordinadores)
Una vez identificado con éxito como Coordinador, iniciar sesión con **Coord1 (20000002B)**, pruebe los siguientes escenarios simulando la recepción de llamadas:

* **Secuencia Principal (Cliente Particular):** Introducir teléfono *600111111* y pulsar en *Recibe Llamada*. Introducir nº de contrato *ESVLL111*, teléfono *600111111* y NIF *11111111A*, y pulsar *Confirmar*. En el panel derecho, introducir los datos del cliente (Nombre: *Juan Perez*, Vía: *Calle Mayor*, Nº: *1*, Piso: *2*, Puerta: *A*, CP: *47001*, Localidad y Provincia: *Valladolid*) y pulsar *Confirmar*. Finalmente, pulsar en *Realizar consulta*. El sistema registrará una llamada correcta.
* **Secuencia Principal (Cliente Empresarial):** Introducir teléfono *600222222* y pulsar *Recibe Llamada*. Introducir contrato *ESVLL222*, teléfono *600222222* y CIF *B22222222*, y pulsar *Confirmar*. En el panel derecho, introducir *Empresa SA* y los datos de su sede (*Poligono Norte, Nº 10, Piso 1, Puerta B, CP 47002, Valladolid, Valladolid*) y pulsar *Confirmar*. Pulsar *Realizar consulta*. El sistema registrará una llamada correcta.
* **Alternativa 3a (Sin datos iniciales):** Introducir número de teléfono y pulsar *Recibe llamada*. Dejar en blanco los datos de la llamada y pulsar *Sin Datos*. El sistema registrará directamente una llamada incorrecta en la base de datos.
* **Alternativa 4a (Contrato no en vigor o inexistente):** Recibir llamada e introducir los datos de un contrato caducado (Nº contrato *ESVLL333* y NIF *33333333C*). Al pulsar *Confirmar*, el sistema mostrará un error por contrato inválido y registrará una llamada incorrecta.
* **Alternativa 5a (Llamada Inconsistente):** Superar el primer panel con los datos correctos del contrato *ESVLL111*. En el panel de datos de cliente, pulsar en *Sin Datos*. Introducir texto en la caja de Inconsistencia (por ejemplo, "El cliente no conoce su código postal") y pulsar *Guardar*. El sistema registrará una llamada inconsistente.
* **Alternativas 6b, 6c y 6d (Datos incorrectos del cliente):** Superar el primer panel con el contrato *ESVLL111*. En el panel derecho, introducir datos falsos (por ejemplo, nombre *Manolo Perez*, o teléfono *666666666*, o un número de vía incorrecto) y pulsar *Confirmar*. El sistema informará del error manteniéndose a la espera de los datos correctos.
* **Alternativa 8a (Reportar Incidencia):** Confirmar exitosamente el primer y segundo panel. Pulsar el botón de *Reportar incidencia*. El sistema redirigirá a una ventana con el mensaje "Not implemented yet".

## 📁 Estructura del Repositorio
* `/models`: Contiene el modelo de diseño detallado en formato **Astah** (`TCONN-ACME.asta`).
* `/app/db`: Scripts de creación de tablas, población de pruebas estructuradas y configuración (`configdb.env`).
* `/app/netbeansProject`: Código fuente del proyecto Maven.

## 👥 Equipo de Desarrollo (Grupo 05)
* **Angel Antolin**
* **Elisa Martínez**
* **Marcos Gómez**