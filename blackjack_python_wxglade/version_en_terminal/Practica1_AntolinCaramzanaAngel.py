"""
Practica 1_Paradigmas de Programación
Antolín Caramazana, Ángel
"""

from externo import Mazo, Estrategia, CartaBase

class Carta (CartaBase):
    #Metodo para devolver el valor facial de la carta
    @property
    def valorCarta(self):

        num = self.ind

        if ((num % 13 + 1) == 1):
            return "A"      #Ases
        elif ((num % 13 + 1) == 11):
            return "J"      #J
        elif ((num % 13 + 1) == 12):
            return "Q"      #Q
        elif ((num % 13 + 1) == 13):
            return "K"      #K
        else:
            return num % 13 + 1
    #Metodo que devuelve el valor numerico de la carta
    def numero (self):
        return super().valor
    
    #Metodo para devolver el palo
    @property
    def palos(self):

        valor = self.ind
        
        if (valor >= 0 and valor <= 12):
            return "♠"
        elif (valor >= 13 and valor <= 25):
            return "♣"
        elif (valor >= 26 and valor <= 38):
            return "♥"
        else:
            return "♦"
        
    #Conjunto de metodos para imprimir las cartas
    def parteArriba(self):

        arriba = "╭───╮"
        return arriba
    
    def parteValor(self):
        if(self.valorCarta == 10):
            
            valor = "│ " + str(self.valorCarta) +"│"
        else:
            valor = "│  " + str(self.valorCarta) +"│"
        return valor
    
    def partePalo(self):
        
        palo = "│"+ self.palos + "  │"
        return palo

    def parteAbajo(self):

        abajo = "╰───╯"
        return abajo
"""
Metodo que dibuja las manos y cartas como en la representacion deluxe
Entradas: mano/manos a dibujar, lista de nombres, lista de apuestas, lista estados y si es necesario o no el espacio arriba para ajustar
Salidas: imprime las manos y cartas en la representacion deluxe
""" 
def dibujarCartas(mano, nombres, apuestas, estados, espacio):
    if (espacio == True):
        print()
    #Dibuja el nombre y la parte de arriba de las cartas
    for i in range(len(mano)):
        
        ajuste = max((len(nombres[i]) + 1), len(estadoMano(estados, i)))
        cadena = nombres[i] + ":"
        cadenaAjustada = cadena.rjust(ajuste)
        print(cadenaAjustada,end="")
        
        for  j, carta in enumerate(mano[i]):
            #Si es la ultima carta empieza una nueva linea
            if (i == (len(mano) - 1) and j == (len(mano[i]) - 1)):
                
                print(carta.parteArriba())
            else:
                
                print(carta.parteArriba(), end="")
            #Si hay mas de 1 mano y no es la ultima imprime el separador
            if (len(mano) >= 2 and j == (len(mano[i]) - 1) and i != (len(mano) - 1)):

                print(" │ ",end="")
    #Dibuja la parte del valor total y el valor facial de las cartas
    for i in range(len(mano)):

        ajuste = max((len(nombres[i]) + 1), len(estadoMano(estados, i)))
        cadena = "("+ str(valorMano(mano[i]))+ ")"
        cadenaAjustada = cadena.rjust(ajuste)
        print(cadenaAjustada,end="")
        
        for  j, carta in enumerate(mano[i]):

            if (i == (len(mano) - 1) and j == (len(mano[i]) - 1)):
                
                print(carta.parteValor())
            else:
                
                print(carta.parteValor(), end="")
            
            if (len(mano) >= 2 and j == (len(mano[i]) - 1) and i != (len(mano) - 1)):

                print(" │ ",end="")        
    
    #Dibuja la parte de la apuesta y el palo de las cartas
    for i in range(len(mano)):

        if (nombres[i] != "Croupier"):
            ajuste = max((len(nombres[i]) + 1), len(estadoMano(estados, i)))
            cadena = str(apuestas[i]) + "€"
            cadenaAjustada = cadena.rjust(ajuste)
            print(cadenaAjustada,end="")
        else:
            ajuste = max((len(nombres[i]) + 1), len(estadoMano(estados, i)))
            cadena = estadoMano(estados, i)
            cadenaAjustada = cadena.rjust(ajuste)
            print(cadenaAjustada,end="")
        
        for  j, carta in enumerate(mano[i]):

            if (i == (len(mano) - 1) and j == (len(mano[i]) - 1)):
                
                print(carta.partePalo())
            else:
                
                print(carta.partePalo(), end="")
            
            if (len(mano) >= 2 and j == (len(mano[i]) - 1) and i != (len(mano) - 1)):

                print(" │ ",end="") 
    #Dibuja el estado de la mano y la parte de abajo de las cartas  
    for i in range(len(mano)):

        if (nombres[i] != "Croupier" ):
            ajuste = max((len(nombres[i]) + 1), len(estadoMano(estados, i)))
            cadena = estadoMano(estados, i)
            cadenaAjustada = cadena.rjust(ajuste)
            print(cadenaAjustada,end="")
        else:

            print("         ",end="")

        for  j, carta in enumerate(mano[i]):

            if (i == (len(mano) - 1) and j == (len(mano[i]) - 1)):
                
                print(carta.parteAbajo())
            else:
                
                print(carta.parteAbajo(), end="")
            
            if (len(mano) >= 2 and j == (len(mano[i]) - 1) and i != (len(mano) - 1)):

                print(" │ ",end="")
"""
Metodo que reparte las primeras cartas
Entradas: objeto mazo, manos jugador y croupier, apuesta, estados jugador y croupier
Salidas: introduce en las listas las primeras cartas de croupier y jugador
""" 
def repartoIncial (mazo, jugador, crupier):
    
    #Reparte al croupier    
    crupier[0].append(mazo.reparte())
    
    #Reparte al jugador
    for _ in range (2):
        
        jugador[0].append(mazo.reparte())  
"""
Calcula el valor total de la mano sumando el de las cartas, tiene en cuenta los ases
Entradas: mano a hallar su valor
Salidas: valor total de las cartas de la mano
""" 
def valorMano(mano):
    
    valorTotal = 0
    ases = 0
    #Cuenta los ases y suma todos como si fueran 11 para posteriormente ajustarlos
    for carta in mano:
        
        valor = carta.numero()
        
        if (valor == 1):
            
            ases = ases + 1
            valorTotal = valorTotal + 11
        
        else:
            valorTotal = valorTotal + valor

    #Mientras el valor sea mayor a 21 o halla algun as, ajusta el valor de los ases a 1
    while (valorTotal > 21 and ases > 0):
        
        valorTotal = valorTotal - 10
        ases = ases - 1
    
    return valorTotal
"""
Metodo que comprueba si hay BlackJack en el primer reparto
Entradas: mano inicial del jugador
Salidas: si hay o no blackjack
"""       
def comprobarBlackjack (manoJugador):

    if (valorMano(manoJugador) == 21):

        print(f'''*****************
*** BLACKJACK ***
*****************
              ''')
        return True
    else:
        return False
"""
Metodo para imprimir el estado de la mano
Entradas: lista de estados y mano de la cual se quiere poner el estado
Salidas: estado de la mano
"""
def estadoMano (listaEstados, mano):

    if (listaEstados[mano] == 0):
        
        return "Activa"
    
    elif (listaEstados[mano] == 1):

        return "Cerrada"
    
    else:
        return "PASADA"
"""
Metodo para comprobar si hay manos activas
Entradas: lista de estados
Salidas: si hay o no manos aun activas
"""    
def manosActivas (lista):

    comprobar = False

    for i in range(len(lista)):

        if (lista[i] == 0):

            comprobar = True
    
    return comprobar
"""
Comprueba si la mano se puede separar para dar o no la opcion
Entradas: ambas cartas de la mano
Salidas: si es posible o no separar la mano
"""
def separarPosible (carta1, carta2):

    separar = False

    if (carta1.numero() == carta2.numero()):
        
        separar = True
        return separar
    else:
        return separar
"""
Metodo para separar la mano en 2
Entradas: mano a separar
Salidas: una lista con la carta separada
"""
def separarMano (mano):

    aux = []

    for i in range(len(mano)):
    
        if (i == 1):
    
            aux.append(mano[i])
            mano = aux
            return mano
"""
Metodo para poner el resultado de cada mano en la contabilizacion de resultados
Entradas: valor de la mano, valor de la mano del croupier, apuesta de la mano
Salidas: un string para poner en la contabilizacion
"""
def contabilizacion (valorJugador, valorCrupier, apuesta):

    if (valorJugador > 21):

        return "-" + str(apuesta)
    
    elif (valorCrupier > 21 and valorJugador <= 21):

        return "+" + str(apuesta)
    
    elif (valorJugador <= 21 and valorJugador > valorCrupier):

        return "+" + str(apuesta)

    elif (valorJugador == valorCrupier):

        return "0"
    
    elif (valorJugador < 21 and valorJugador < valorCrupier):

        return "-" + str(apuesta)
    
"""
Metodo para calcular el balance final de la partida
Entradas: valor de la mano, valor de la mano del croupier, apuesta de la mano
Salida: lo que se va a sumar o restar al balance de la partida
"""
def balanceFinal (valorJugador, valorCrupier, apuesta):
    
    if (valorJugador > 21):

        return (apuesta * (-1))
    
    elif (valorCrupier > 21 and valorJugador <= 21):

        return apuesta
    
    elif (valorJugador <= 21 and valorJugador > valorCrupier):

        return apuesta

    elif (valorJugador == valorCrupier):

        return 0
    
    elif (valorJugador < 21 and valorJugador < valorCrupier):

        return (apuesta * (-1))


#*******************MAIN*****************************************************   

def Main():

    estrategia = Estrategia(Mazo.NUM_BARAJAS)
    mazo = Mazo(Carta, estrategia)
    
    print("*** BLACKJACK - PARADIGMAS DE PROGRAMACIÓN 2023/24 ***")
    print("¿Modo de ejecución?")
    modo = input ("[J]ugar [A]nalisis: ")

    #Control bucle modo de juego
    escogerModo = True

    while (escogerModo == True):

        #*******************************************************MODO JUEGO Y PREDETERMINADO****************************************
        if (modo == 'J' or modo == 'j' or modo == ''):

            escogerModo = False
            partida = True
            balance = 0
            numPartida = 1
            titulo = True
            noTitulo = False
            #Comienzo de la partida
            while (partida == True):
                
                #Variables a utilizar a lo largo del programa
                manoCrupier = [[]]                      #Mano del Croupier
                manoJugador = [[]]                      #Conjunto de manos del Jugador
                estadosJugador = [0]                    #Vector de estados de las manos del jugador 
                estadosCrupier = [0]                    #Estado de la mano del croupier
                apuesta = []                            #Vector para contener la apuesta de cada mano del jugador (por si separa y dobla)
                nombresManos = ["Mano"]                 #Vector con los nombres de las manos (para separar)
                nombreCrupier = ["Croupier"]            #Nombre de la mano del Croupier

                print()
                print("--- INICIO DE LA PARTIDA #" + str(numPartida) + " --- BALANCE = " + str(balance) + "€")
                
                #Si es juego, pregunta la apuesta, si es predeterminado pone 10
                if ((modo == 'J' or modo == 'j') or numPartida >= 2):
                    preguntaApuesta = input("¿Apuesta? [2] [10] [50] ")
                    apuestaCorrecta = True

                    #Bucle para evitar que se introduzca un valor érroneo en la apuesta
                    while (apuestaCorrecta == True):

                        if (preguntaApuesta == "2" or preguntaApuesta == "10" or preguntaApuesta == "50"):

                            apuesta.append(int(preguntaApuesta))
                            apuestaCorrecta = False
                    
                        else:

                            preguntaApuesta = input("¿Apuesta? [2] [10] [50] ")

                else:
                    apuesta.append(10)
    
                print()
                print("REPARTO INICIAL")
    
                #Reparto inicial
                repartoIncial(mazo, manoJugador, manoCrupier)
                dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)
                dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, noTitulo)

                #Comprobar blackjack
                if (comprobarBlackjack(manoJugador[0]) == True):
                    
                    balance = float(apuesta[0]) * (3/2)
                    print ("Ha ganado", str(balance) + "€!")

                    
                    if (modo == 'J' or modo == 'j' or numPartida >= 2):
                    
                        pregunta = True
                    
                    
                        #Control de volver a jugar una partida si hay blackjack
                        while (pregunta == True):
                        
                            continuar = input("¿Otra partida? [S/N]")

                            if (continuar == "S" or continuar == "s"):

                                partida = True
                                pregunta = False
                                numPartida = numPartida + 1
                    
                            elif (continuar == "N" or continuar == "n"):

                                partida = False
                                pregunta = False
                                print()
                                print("BALANCE FINAL: " + str(balance) + " €")
                        
                            else:

                                continuar = input("¿Otra partida? [S/N]")
                        
                    else:

                        numPartida = numPartida + 1


                #Inicio turno del jugador si no ha habido blackjack
                else:
                    print()
                    print("TURNO DEL JUGADOR")
                    turno = True
                    
                    #Bucle del turno
                    while (turno == True):
                        
                        #Si es modo juego pregunta los movimientos
                        if ((modo == 'J' or modo == 'j') or numPartida >= 2):

                            #Recorrido de las manos del jugador 
                            for i in range(len(manoJugador)):

                                if (estadosJugador[i] == 0):
                                
                                    #Comprueba si la mano tiene 2 cartas y si son del mismo valor para dar la opcion de separar
                                    if (len(manoJugador[i]) == 2 and separarPosible(manoJugador[i][0], manoJugador[i][1]) == True):

                                        jugada = input ("¿Jugada para " + nombresManos[i] + "? [P]edir [D]oblar [C]errar [S]eparar ")

                                    else:
                                
                                        jugada = input("¿Jugada para " + nombresManos[i] + "? [P]edir [D]oblar [C]errar ")
                                
                                    movimiento = True
                            
                                    #Pedir la jugada de la mano, si es incorrecto lo repite
                                    while (movimiento == True):
                                
                                        if (jugada == "P" or jugada == "p"):
                                    
                                            movimiento = False
                                            manoJugador[i].append(mazo.reparte())
                                    
                                            if (valorMano(manoJugador[i]) > 21):

                                                estadosJugador[i] = estadosJugador[i] + 2

                                        elif (jugada == "D" or jugada == "d"):
                                    
                                            movimiento = False
                                            manoJugador[i].append(mazo.reparte())
                                            apuesta[i] = apuesta[i] * 2
                                    
                                            if (valorMano(manoJugador[i]) > 21):

                                                estadosJugador[i] = estadosJugador[i] + 2
                                    
                                            else:
                                        
                                                estadosJugador[i] = estadosJugador[i] + 1

                                        elif (jugada == "C" or jugada == "c"):
                                    
                                            movimiento = False
                                            estadosJugador[i] = estadosJugador [i] + 1

                                        elif (jugada == "S" or jugada == "s"):
                                    
                                            movimiento = False
                                            manoJugador.append(separarMano(manoJugador[i]))
                                            del manoJugador[i][1]
                                            nombresManos.append(nombresManos [i] + "B")
                                            nombresManos[i] = nombresManos[i] + "A"
                                            estadosJugador.append(0)
                                            apuesta.append(apuesta[0])
                                    
                                        else:

                                            if (len(manoJugador[i]) == 2 and separarPosible(manoJugador[i][0], manoJugador[i][1]) == True):

                                                jugada = input ("¿Jugada para " + nombresManos[i] + "? [P]edir [D]oblar [C]errar [S]eparar ")

                                            else:
                                
                                                jugada = input("¿Jugada para " + nombresManos[i] + "? [P]edir [D]oblar [C]errar ")

                            dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, titulo)
                                                     
                            turno = manosActivas(estadosJugador)

                        else:
                            
                            manoJugador[0].append(mazo.reparte())
                            dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, titulo)
                                
                            if (valorMano(manoJugador[0]) < 21):

                                for c in range(len(manoJugador)):

                                    jugada = input("¿Jugada para " + nombresManos[c] + "? [P]edir [D]oblar [C]errar ")
                                    movimiento = True
                            
                                    #Pedir la jugada de la mano, si es incorrecto lo repite
                                    while (movimiento == True):
                                
                                        if (jugada == "P" or jugada == "p"):
                                    
                                            movimiento = False
                                            manoJugador[c].append(mazo.reparte())
                                    
                                            if (valorMano(manoJugador[c]) > 21):

                                                estadosJugador[c] = estadosJugador[c] + 2

                                        elif (jugada == "D" or jugada == "d"):
                                    
                                            movimiento = False
                                            manoJugador[c].append(mazo.reparte())
                                            apuesta[c] = apuesta[c] * 2
                                    
                                            if (valorMano(manoJugador[c]) > 21):

                                                estadosJugador[c] = estadosJugador[c] + 2
                                    
                                            else:
                                        
                                                estadosJugador[c] = estadosJugador[c] + 1

                                        elif (jugada == "C" or jugada == "c"):
                                    
                                            movimiento = False
                                            estadosJugador[c] = estadosJugador [c] + 1

                                        else:
                                
                                            jugada = input("¿Jugada para " + nombresManos[c] + "? [P]edir [D]oblar [C]errar ")
                                    
                                dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, titulo)
                                turno = manosActivas(estadosJugador)

                            else:

                                estadosJugador[0] = estadosJugador [0] + 2

                                turno = manosActivas(estadosJugador) 

                                
                    #FIN TURNO JUGADOR, INICIA EL DEL CRUPIER
                    print()
                    print("TURNO DEL CROUPIER")
                    dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)

                    while (valorMano(manoCrupier[0]) <= 17):
                        
                        manoCrupier[0].append(mazo.reparte())
                        
                        if (valorMano(manoCrupier[0]) > 21):

                            estadosCrupier[0] = estadosCrupier[0] + 2

                        dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)

                    #Fin de la partida y contabilizacion de resultados
                    print()
                    print("FIN DE LA PARTIDA")
                    #Mostrar las manos finales del croupier y del jugador
                    dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)
                    dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, noTitulo)

                    #Contabilizar los resultados
                    print()
                    print("CONTABILIZACION DE RESULTADOS")

                    for a, mano in enumerate(manoJugador):

                        print("* Croupier:", str(valorMano(manoCrupier[0])) + ", " + nombresManos[a] + ": " + str(valorMano(manoJugador[a])) + " -> " + contabilizacion(valorMano(manoJugador[a]), valorMano(manoCrupier[0]), apuesta[a]))

                    balancePartida = 0

                    for b, mano in enumerate(manoJugador):

                        balancePartida = balancePartida + (balanceFinal(valorMano(mano), valorMano(manoCrupier[0]), apuesta[b]))
                            
                    if (balancePartida >= 0):

                        print("Resultado de la partida: +" + str(balancePartida))

                    else:

                        print("Resultado de la partida: " + str(balancePartida))
                    
                    balance = balance + balancePartida

                    
                    if ((modo == 'J' or modo == 'j') or numPartida >= 2):
                        
                        pregunta = True

                        #Preguntar si se quieren seguir jugando partidas
                        while (pregunta == True):
                        
                            continuar = input("¿Otra partida? [S/N]")

                            if (continuar == "S" or continuar == "s"):

                                partida = True
                                pregunta = False
                                numPartida = numPartida + 1
                    
                            elif (continuar == "N" or continuar == "n"):

                                partida = False
                                pregunta = False
                                print()
                                print("BALANCE FINAL: " + str(balance) + " €")
                        
                            else:

                                continuar = input("¿Otra partida? [S/N]")

                    else:

                        numPartida = numPartida + 1

        #*****************************************************MODO ANALISIS***************************************
        elif (modo == 'A' or modo == 'a'):
            
            escogerModo = False
            escogerPartidas = False
            
            #Pregunta el numero de partidas a jugar
            partidasJugar = input("¿Número de partidas? ")

            while (escogerPartidas == False):
                 #Control para que se introduzca un numero de partidas correcto
                if (partidasJugar.isdigit()) == True:
                    
                    if (int(partidasJugar) > 0):

                        escogerPartidas = True
                
                    else:

                        partidasJugar = input("¿Número de partidas? ")
            
                else:

                    partidasJugar = input("¿Número de partidas? ")

            balance = 0
            numPartida = 1
            titulo = True
            noTitulo = False

            #Controla ahora por el numero de partidas
            while (numPartida <= int(partidasJugar)):
                
                #Variables a utilizar a lo largo del programa
                manoCrupier = [[]]                        
                manoJugador = [[]]                      
                estadosJugador = [0]                    
                estadosCrupier = [0]                   
                apuesta = []                             
                nombresManos = ["Mano"]    
                nombreCrupier = ["Croupier"]             

                print()
                print("--- INICIO DE LA PARTIDA #" + str(numPartida) + " --- BALANCE = " + str(balance) + "€")
                
                print("¿Apuesta? [2] [10] [50]:",estrategia.apuesta(apu_lo = 2, apu_med= 10, apu_hi= 50))
                apuesta.append(estrategia.apuesta(apu_lo = 2, apu_med= 10, apu_hi= 50))

                print()
                print("REPARTO INICIAL")

                #Reparto inicial
                repartoIncial(mazo, manoJugador, manoCrupier)
                dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)
                dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, noTitulo)
                
                #Comprobar blackjack
                if (comprobarBlackjack(manoJugador[0]) == True):
                    
                    balance = float(apuesta[0]) * (3/2)
                    print ("¡Has ganado", str(balance) + "€!")   
                    
                    #Comprueba si ya es la ultima partida para dar el balance final
                    if (numPartida == int(partidasJugar)):      

                        print() 
                        print("BALANCE FINAL: " + str(balance) + " €")
                    
                    numPartida = numPartida + 1
                
                #Inicio turno del jugador si no ha habido blackjack
                else:
                    print()
                    print("TURNO DEL JUGADOR")
                    turno = True
                    
                    #Bucle del turno
                    while (turno == True):
                        
                        #Recorrido de las manos del jugador 
                        for i in range(len(manoJugador)):

                            if (estadosJugador[i] == 0):
                                
                                #Comprueba si la mano tiene 2 cartas y si son del mismo valor para dar la opcion de separar
                                if (len(manoJugador[i]) == 2 and separarPosible(manoJugador[i][0], manoJugador[i][1]) == True):
                                    
                                    print("¿Jugada para " + nombresManos[i] + "? [P]edir [D]oblar [C]errar [S]eparar", estrategia.jugada(manoCrupier[0][0], manoJugador[i]))
                                    jugada = estrategia.jugada(manoCrupier[0][0], manoJugador[i])

                                else:
                                
                                    print("¿Jugada para " + nombresManos[i] + "? [P]edir [D]oblar [C]errar", estrategia.jugada(manoCrupier[0][0], manoJugador[i]))
                                    jugada = estrategia.jugada(manoCrupier[0][0], manoJugador[i])
                                
    
                                if (jugada == "P" or jugada == "p"):
                                    
                                    manoJugador[i].append(mazo.reparte())
                                    
                                    if (valorMano(manoJugador[i]) > 21):

                                        estadosJugador[i] = estadosJugador[i] + 2

                                elif (jugada == "D" or jugada == "d"):
                                    
                                    manoJugador[i].append(mazo.reparte())
                                    apuesta[i] = apuesta[i] * 2
                                    
                                    if (valorMano(manoJugador[i]) > 21):

                                        estadosJugador[i] = estadosJugador[i] + 2
                                    
                                    else:
                                        
                                        estadosJugador[i] = estadosJugador[i] + 1

                                elif (jugada == "C" or jugada == "c"):
                                    
                                    estadosJugador[i] = estadosJugador [i] + 1

                                elif (jugada == "S" or jugada == "s"):
                                    
                                    manoJugador.append(separarMano(manoJugador[i]))
                                    del manoJugador[i][1]
                                    nombresManos.append(nombresManos [i] + "B")
                                    nombresManos[i] = nombresManos[i] + "A"
                                    estadosJugador.append(0)
                                    apuesta.append(apuesta[0])
                                    

                        dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, titulo)
                        turno = manosActivas(estadosJugador)
         
                    #FIN TURNO JUGADOR, INICIA EL DEL CRUPIER
                    print("TURNO DEL CROUPIER")
                    dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)

                    while (valorMano(manoCrupier[0]) < 17):
                        
                        manoCrupier[0].append(mazo.reparte())
                        
                        if (valorMano(manoCrupier[0]) > 21):

                            estadosCrupier[0] = estadosCrupier[0] + 2

                        dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)
                    #Fin de la partida y contabilizacion de resultados
                    print()
                    print("FIN DE LA PARTIDA")

                    #Mostrar las manos finales del croupier y del jugador
                    dibujarCartas(manoCrupier, nombreCrupier, apuesta, estadosCrupier, noTitulo)
                    dibujarCartas(manoJugador, nombresManos, apuesta, estadosJugador, noTitulo)

                    #Contabilizar los resultados
                    print()
                    print("CONTABILIZACION DE RESULTADOS")

                    for a, mano in enumerate(manoJugador):

                        print("* Croupier:", str(valorMano(manoCrupier[0])) + ", " + nombresManos[a] + ": " + str(valorMano(manoJugador[a])) + " -> " + contabilizacion(valorMano(manoJugador[a]), valorMano(manoCrupier[0]), apuesta[a]))

                    balancePartida = 0

                    for b, mano in enumerate(manoJugador):

                        balancePartida = balancePartida + (balanceFinal(valorMano(mano), valorMano(manoCrupier[0]), apuesta[b]))
                            
                    if (balancePartida >= 0):

                        print("Resultado de la partida: +" + str(balancePartida))

                    else:

                        print("Resultado de la partida: " + str(balancePartida))
                    
                    balance = balance + balancePartida

                    #Comprueba si ya es la ultima partida para dar el balance final
                    if (numPartida == int(partidasJugar)):

                        print()
                        print("BALANCE FINAL: " + str(balance) + " €")
                    
                    numPartida = numPartida + 1                  
        #Entradas erróneas
        else:
            
            modo = input ("[J]ugar [A]nalisis: ")

if __name__=="__main__":
    Main()