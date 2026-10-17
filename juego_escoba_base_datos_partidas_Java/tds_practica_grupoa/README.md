# tds_practica_grupoa

Este archivo README contiene la información referente a la primera  y segunda iteración de la práctica de la asignatura de Tecnologías para el desarrollo de Software.

El desarrollo de esta práctica corresponde a los alumnos Marcos Gómez Medina y Ángel Antolín Caramazana, ambos miembros del grupo A de prácticas de la asignatura.

## Iteración 1

### Descripción de la realización de la iteración

Cuando se afrontó por primera vez esta práctica, lo primero que realizamos fue crear el correspondiente proyecto en GitLab, con las diferentes especificaciones pertinentes, como modificar el pom, crear la rama develop...

Más tarde y en una de las horas de laboratorio de la asignatura, decidimos cómo nos dividiríamos para poder trabajar conjuntamente cada uno en una rama de develop y no molestarnos, procurando también crear las diferentes issues correspondientes.

Decidimos comenzar modelando la carta, para ello, seguimos los pasos establecidos para realizar un TDD correcto y que siguiera las condiciones explicadas en clase (primero test, obteniendo los test en rojo, luego implementando la funcionalidad para conseguirlos en verde).

Una vez hecho esto con Carta, podíamos empezar a modelar Baraja, así como Jugador y Mesa (todo con sus respectivos test).

Cada vez que finalizábamos una funcionalidad, cerramos la issue, indicábamos el tiempo tardado en la realización de esta y mergeamos en la rama develop.

Por último y con todo ya modelado, nos reunimos y decidimos crear una clase para gestionar la partida, donde tendrían lugar las diferentes funcionalidades que se desarrollan durante una partida de la escoba.

Los test de gestorPartidaTest fueron sin duda los más complicados, debido a la gran cantidad de test que necesitábamos probar, por ello y por la reutilización de código se añadió un fixture, en este caso un beforeEach con diferentes manos establecidas, conjuntos de listas interesantes y útiles para los test.

**OBSERVACIÓN: hay 3 test que son unitarios, es decir, solo funcionan para partida_escoba1.json ya que se trata de pruebas individuales para comprobar que al parsear las cartas se obtenían de manera correcta.**

Una vez conseguimos que todos los test funcionaran y estuviera todo implementado, nos dividimos, uno se encargaría de realizar la documentación y el otro, se encargaría de la realización de este archivo README.

### Clases que forman parte de la solución

Aquí se encuentran las diferentes clases que conforman nuestra solución:

* Carta: modela una carta para el juego de la escoba, está representada por su número, siendo un número del 1 al 40, con ambos incluidos, y el valor que tiene la carta en el propio juego.

* Baraja: modela una baraja, se trata de una lista con todas las cartas posibles, esto incluye las 10 cartas de cada palo (oros, bastos, espadas y copas).

* Mesa: representa la mesa del juego, donde disponemos de unas cartas con las que el jugador tendrá que realizar las diferentes jugadas.

* GestorPartida: clase que se encarga de toda la gestión de las funcionalidades que ocurren durante una partida.

* Jugador: modela un jugador y las diferentes funcionalidades que puede realizar un jugador.

* BarajaTest: contiene los diferentes test de la clase Baraja.

* CartaTest: contiene los diferentes test de la clase Carta.

* GestorPartidaTest: contiene los diferentes test de la clase GestorPartida.

* JugadorTest: contiene los diferentes test de la clase Jugador.

### Tiempo empleado por persona

El tiempo y las diferentes funcionalidades se han repartido bastante equitativamente, de manera que, hemos buscado trabajar los dos aproximadamente la misma cantidad de horas y de trabajo.

También hay que tener en cuenta, que ha habido ocasiones, en las que, como en todos los proyectos, hemos tenido dificultades, y en esas ocasiones hemos quedado en persona (o llamada) para tratar de ayudarnos entre nosotros a resolver el problema, por ello, aunque solo haya hecho commits uno, en verdad, estábamos los dos trabajando sobre lo mismo, aunque, intentamos en la medida de lo posible, no tener que meternos en la rama del otro para evitar males mayores y alguna confusión.

Además, también se tiene en cuenta los tiempos en los que hemos reflexionado sobre la solución, no solo el tiempo que hayamos tardado en resolver las diferentes issues.

Hemos intentado hacer una estimación aproximada del tiempo que hemos empleado cada uno: 

* Marcos Gómez Medina - Aproximadamente: 20h.
* Ángel Antolín Caramazana - Aproximadamente: 20h.

### Descripción de la aplicación de GitHub Copilot y otras herramientas de Inteligencia Artificial Generativa

Tal y como vimos en clase, GitHub Copilot en ocasiones puede ser muy útil, sobre todo, en el aspecto de la documentación, donde puede ahorrarnos algo de tiempo o la realización de algunos test en los que no nos habíamos siquiera planteado.

El chat de GitHub no lo hemos empleado, solo usábamos las sugerencias de código que proponía, y nos planteábamos si tenía sentido y era lo que necesitábamos, ya que había ocasiones en lo que no era lo que esperábamos.

Sin embargo, para lo que es la implementación de las funcionalidades, no lo hemos empleado, sentíamos que en ocasiones era un poco torpe y podía entorpecernos más de lo que podía ayudarnos.

En cuanto a otras herramientas generativas de código, hemos hecho alguna consulta puntual a ChatGPT, sobre pequeños errores de código, al estilo de, olvidarnos inicializar la variable, o llamadas a métodos que no tenían ese nombre concreto etc.

## ITERACION 2

### Descripción de la realización de la iteración 2

Para esta segunda iteración decidimos dividirnos las dos interfaces de manera que pudiéramos avanzar sin entrometernos en el avance del otro.

También hemos buscado obtener el 100% de cobertura para todo nuestro código anterior y una vez finalizadas las funcionalidades de la segunda iteración también para estas.

Hemos seguido el TDD, creando en primer lugar los test, luego implementando la funcionalidad requerida, y por último en las ocasiones necesarias se han utilizado los mocks para simular las interfaces.

Por último, cuando todo ya funcionaba de manera correcta se ha procedido a su documentación.

### Clases que forman parte de la solución de la iteración 2

Además de todas las clases anteriores, con sus respectivos test se han añadido otras clases necesarias como las siguientes:

(Solo especificamos las de la nueva iteración el resto se mantiene)

* GestorPartida: realiza las funcionalidades correspondientes al servicio de una partida, en nuestra solución tiene este nombre por motivos de continuidad de la iteración 1.

Ahora cuenta con más funcionalidades como validar una partida, donde hemos tenido en cuenta el feedback del profesor, y lo hemos generalizado para que el parseador que usábamos no solo sea para JSON, sino también para Strings con el formato adecuado.

Las funcionalidades son:

* Validar partida: permite comprobar si una partida es correcta a o no
    
    * Calcular los puntos de un jugador: esta funcionalidad, tal y como habíamos planteado la iteración 1 ya lo cubríamos.

    * Obtener resumen: funcionalidad también cubierta en la iteración 1.

    * Obtener estadísticas entre dos fechas: permite obtener las estadísticas de partidas, entre dos fechas dadas, donde nos apoyamos del método de la interfaz RepositorioPartida que nos ofrece la posibilidad de devolver una lista de partidas.

    * obtener estadísticas de un jugador: al igual que en la funcionalidad anterior, se realiza mediante la interfaz repositorioPartida, obteniendo una lista de partidas a través de un nombre para un jugador.

    * obtener top 10: sin duda, la parte más complicada del gestorPartida, para ello decidimos obtener una lista de partida entre dos fechas, siendo la fechaInicial muy antigua(la que hemos considerado) y la fecha del día de hoy, de manera que obtenemos un "histórico" de partidas, y luego a través de un mapa de par clave(String)-valor(número de victorias), lo fuimos ordenando, y luego nos quedamos con solo los 10 primeros.

    * obtener (o cargar partida): permite cargar una partida, a través de la interfaz repositorioPartida.

    * guardar partida: permite cargar una partida, a través de la interfaz.

    * eliminar partida: permite eliminar una partida, usando la interfaz repositorioPartida.

    * Actualizar Partida: actualiza una partida por medio de repositorioPartida.

* Parser: clase que permite parsear para obtener la carta de manera que nos sea lo más útil posible a la hora de realizar distintas comprobaciones, y que se adecue a nuestra modelización de las cartas.

* Ronda: clase que modela una ronda que se juega en una partida, nos es muy util para luego poder validar si una partida es correcta o no.

Una ronda se encuentra conformada por las cartas que se sitúa en la mesa al inicio de la ronda, las manos de los jugadores(una cada uno) y una lista de jugadas, donde los jugadores van anotando sus diferentes jugadas( la clase Jugada se explica más adelante).

* Jugada: modela las diferentes jugadas que se realizan en la ronda, donde el jugador usa una carta de su mano y captura(si puede) algunas cartas que se encuentran en la mesa.

* ServicioJuegoMultiJugador: clase que modela el servicio de multijugador online, en ella se puede gestionar, unir y crear diferentes salas de juego. Las diferentes funcionalidades implementadas:

    * Unirse a una partida: permite ingresar a una sala a través de un identificador de sala, el nombre de un jugador y un código de acceso.
    Se apoya en la interfaz ServicioMultiJugador para lograrlo.

    * Listar partidas disponibles: devuelve una lista de tipo String en la que se listen todas las salas disponibles, usa la interfaz.

    * Gestionar Partida: desarrolla toda la lógica realizada en la primera iteración, se hace a través de la clase GestorPartida.

    * Enviar Jugada; permite que se envíen las cartas que se han jugado, no tienen por qué haber capturado, se envía la carta que se haya jugado por medio de la interfaz servicioPartida a través del identificador de la sala,el nombre del jugador y su carta jugada.

    * Actualizar estado de la partida: según se va sucediendo una partida, gracias a la lógica desarrollada en gestorPartida, se van actualizando las cartas en la mesa , y las manos de los jugadores.

    * Gestión del estado local y recepción de eventos: se simula la recepción y desarrollo de la partida.

Se implementa su propio gestor que va actualizando las cosas de forma local

* GestorPartidaMultiJugador: gestiona una partida multijugador.

Por su puesto, cada una de estas clases dispone de sus respectivas clases test, en la carpeta correspondiente, y donde se ha trabajado hasta lograr el 100% de la cobertura en cada una de clases, aproximadamente, aquí va el test ratio to-code:

### Ratio to-code

Ratio calculado: (líneas de código de la clase)/(líneas de código de los test para cubrir la clase).

Se trata de una pequeña aproximación entre las lineas de cógido necesarias para implementar las funcionalidades, y las líneas de codigo necesarias para cubrir la implementación de manera completa

* Baraja:0.49
* Carta:0.92
* GestorPartida:0.299
* GestorPartidaMultiJugador:0.65
* Jugada:0.77
* Jugador:0.6851
* Mesa:0.5511
* Parser:0.6260
* Partida:0.2650
* Ronda:0.3771
* ServicioJuegoMultiJugador:0.080

### Tiempo empleado por persona

Al igual que en la anterior iteración se ha buscado el máximo de equilibrio entre ambos miembros del equipo, dividiéndonos las interfaces y las funcionalidades.

Había ocasiones en las que era necesario que uno de los dos acabara algo para que el otro pudiera continuar así que ese tiempo fue aprovechado para realizar cobertura de los test y buscar cubrir todo al 100%.

Por supuesto, tal como se explicó anteriormente, se han necesitado de llamadas o reuniones para ayudar el uno al otro en alguna parte concreta por lo que en ocasiones ambos hemos trabajado juntos, aunque no en la misma rama para evitar problemas.

Se incluye en este tiempo empleado, el tiempo que en el que hemos razonado sobre cómo llegar a una solución y refactorizaciones anteriores.

Un tiempo aproximado es:

* Marcos Gómez Medina: 40 h aprox.
* Ángel Antolín Caramazana: 40h aprox.

### Descripción de la aplicación de GitHub Copilot y otras herramientas de Inteligencia Artificial Generativa

Al igual que en la primera iteración, han resultado muy útiles a la hora de realizar la documentación de cada clase, seguíamos sintiendo que entorpecía un poco en la realización de algunos métodos.

Uno de los usos más importantes ha sido por ejemplo en la creación de 55 partidas para obtener un top 10 , donde le pedimos al chat de GitHub Copilot que generara las variables  de Partida con el nombre ya cambiado, un identificador distinto y unos jugadores que le explicamos, para así tener que evitarnos un “copia pega” entre Partidas muy grande y que posiblemente nos llevase a muchos errores y dolores de cabeza.

También se preguntó en una ocasión que nos recordara el funcionamiento de un mapa, ya que no lo recordábamos.


Proyecto realizado por los alumnos Marcos Gómez Medina y Tecnologías para el desarrollo de Software.


## ITERACION 3


### Descripción de la realización de la iteración 3

Tras recibir el feedback de la anterior iteración, decidimos que lo primero a realizar sería corregir los errores anteriores para afrontar esta nueva iteración de la mejor manera posible.

Después de haberlos corregido, tambíen decidimos usar la herrameinta SonnarQube para analizar nuestro codigo y eliminar todas aquellas issues que nos detectara.

Una vez hecho eso configuramos la integración continua con el archivo correspondiente en gitlab, y la creación de la base de datos.

Por último nos separamos y uno se encargó de la refactorización y el otro de cubrir los test que nos faltaban para obtener la cobertura al 100%


### Clases que forman parte de la solución de la iteración 3

Además de todas las anteriores clases que formaron las iteraciones anteriores, en esta nueva y final iteración se han añadido las siguientes clases:

* IRepositorioPartida: se trata de la interfaz para la persistencia de partidas
* RepositorioPartida:implementa la interaz.
* HibernateUtil: clase de utilidad de Hibernate para obtener la sesion

### Ratio to-code 
Ratio calculado: (líneas de código de la clase)/(líneas de código de los test para cubrir la clase).

Se trata de una pequeña aproximación entre las lineas de cógido necesarias para implementar las funcionalidades, y las líneas de codigo necesarias para cubrir la implementación de manera completa.

* Baraja: 0.58
* Carta: 0.992
* GestorPartida: 0.42
* GestorPartidaMultiJugador: 0.59
* Jugada: 0.65
* Jugador: 0.66
* Mesa: 0.52
* Parser: 0.57
* Partida: 0.43
* Ronda: 0.56
* ServicioJuegoMultiJugador: 0.08
* ServicioPartida en Aislamiento: 0.32
* ServicioPartida con DB: 0.35


### Tiempo empleado por persona

Se ha intentado repartise lo más equitativo posible como en las anteriores entregas.

En esta iteración, ha habido mucho trabajo en conjunto en las diferentes reuniones que hemos tenido  y tiempo empleado pensando en cómo afrontar esta nueva iteración.

Aquí se incluye también el tiempo tardado en la preparación de la correcta configuración del SonnarQube que nos llevó algo de tiempo.

El trabajo por persona ha sido :
- Marcos Gómez Medina: 20 hrs.
- Ángel Antolín Caramazana: 25 hrs.

La diferencia de horas, se debe en su mayoría, al uso e implementación de la base de datos, que Ángel comprendía mejor y debido a enfermedad del otro miembro.


### Refactorizaciones aplicadas del catálogo de Fowler

* Replace Magic Number with Symbolic Constante: realizado en la clase Carta donde asignábamos los valores predefindios 8,9 y 10 para
en orden correspondiente sota,caballo y rey.
Con esta modificación hacemos que sea un código mucho más facil de mantener, más legible y logramos una mayor independencia.
En caso de ser necesario modificarlo, es ahora mucho más fácil de indetificar qué valor modificar y sobre todo entender por qué.

* Remove Control Flag: aplicado en la clase Jugador, ya no usa una bandera como controlador del método para devolver, si no que directamente se evuelve verdadero o falso.

* Extract Method: relizado en ServicioPartida, en lugar de comprobar directamente, movemos este código a un método separado (función en este caso que devuelve un boolean) y se reemplaza al anterior código.
Esto se realiza porque en ocasiones puede ser complicado entender qué se está realizando en el condicional que teníamos, sin embargo, con esta nueva función independiente logramos un código mucho más legible, fácil de comprender qué se está comprobando (también ayuda mucho tener un nombre de función muy descriptivo) y logramos una independencia mucho mayor.

### Aclaración sobre la cobertura

No ha sido posible conseguir una cobertura del 100% en todas las clases. En RepositorioPartida e HibernateUtil hay líneas que no tienen cobertura debido a que coinciden con bloques try-catch que se ejecutan cuando se lanza una excepción de Hibernate y no hemos sido capaces de conseguir test que lancen las excepciones necesarias en el momento que tendrían que lanzarse para ser recogidas por esos catch. 

### Descripción de la aplicación de GitHub Copilot y otras herramientas de Inteligencia Artificial Generativa

Se ha usado el GitHub Copilot para ayudarnos a realizar la documentación de las diferentes clases.

Gemini AI ha sido utilizada para ayudar con los problemas que surgían a la hora de implementar la base de datos, dando ayuda con el significado de los errores que surgian y la manera de solucionarlos





