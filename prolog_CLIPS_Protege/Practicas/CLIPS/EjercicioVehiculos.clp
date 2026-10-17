;Solicitar al usuario que escoja el escenario a probar

(defrule Solicitar-escenario
	(declare(salience 1000))
=>
	(printout T crlf crlf "Bienvenido al asistente para el diagnostico de vehículos." crlf)
	(printout T "Dispone de dos escenarios de prueba." crlf "Responda 1 para el primer escenario y 2 para el segundo:")
	(assert (escenario = (read T)))
	(printout T crlf)
)

;Escenarios de prueba

(defrule escenario1 "El usuario introduce 1"
	(escenario 1)
=>
	(assert(motor estado noarranca))
	(assert(bateria indicador cero))
	(printout T "1. Se observa que el motor no arranca y que el indicador de batería marca cero" crlf)

)

(defrule escenario2 "El usuario introduce 2"
	(escenario 2)
=>
	(assert(motor estado separa))
	(assert(combustible indicador cero))
	(printout T "2. Se observa que el motor se para y que el indicador de combustible marca cero" crlf)
) 	


; Reglas iniciales comportamiento de motor


(defrule motor-no-arranca "Comportamiento 1 del motor"
	(motor estado noarranca)
=>
	(assert(potencia estado desconectada))
	(assert(combustible enmotor falso))
)
	

(defrule motor-se-para "Comportamiento 2 del motor"
	(motor estado separa)
=>
	(assert(combustible enmotor falso))
)

;Reglas intermedias y lecturas del indicador

(defrule potencia-desconectada-bateria-cero
	(potencia estado desconectada)
	(bateria indicador cero)
=>

	(assert(batería nivel baja))
	(printout T "Causa del problema: Bateria baja" crlf)
)

(defrule potencia-desconectada-fusible-roto
	(potencia estado desconectada)
	(fusible inspeccion roto)

=>
	(assert(fusible estado fundido))
	(printout T "Causa del problema: Fusible fundido" crlf)
)


(defrule motor-no-combustible
	(combustible enmotor falso)
	(combustible indicador cero)
=>
	(assert(combustible deposito vacio))
	(printout T "Causa del problema: Deposito de combustible vacio" crlf)
)








