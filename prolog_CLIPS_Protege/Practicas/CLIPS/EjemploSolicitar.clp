(defrule Solicitar-escenario
	(declare(salience 1000))
=>
	(printout T crlf crlf "Bienvenido al asistente para el diagnostico de vehículos." crlf)
	(printout T "Dispone de dos escenarios de prueba." crlf "Responda 1 para el primer escenario y 2 para el segundo:")
	(assert (escenario = (read T)))
	(printout T crlf)
)



(escenario 1)
(escenario 2)