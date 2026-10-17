(deftemplate oav-u "Plantilla univaluadados"
	(slot objeto (type SYMBOL))
	(slot atributo (type SYMBOL))
	(slot valor))

(deftemplate oav-m "Plantilla multivaluados"
	(slot objeto (type SYMBOL))
	(slot atributo (type SYMBOL))
	(slot valor))

(defrule eliminar-hecho-univaluado "Se eliminan los duplicados"
	(declare (salience 1000))
	?x <- (oav-u (objeto ?objeto) (atributo ?atributo) (valor ?valor1))
	?y <- (oav-u (objeto ?objeto) (atributo ?atributo) (valor ?valor2))
	(test (neq ?valor1 ?valor2))
	(test (< (fact-index ?x) (fact-index ?y)))
	=>
	(retract ?x)
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;; ESCENARIOS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;Regla que solicite el escenario a probar

(defrule solicitar-escenario 
	(declare (salience 10000))
=>
	(printout T crlf crlf "Sistema de Diagnostico Informatico")
	(printout T crlf "Introduzca el escenario a probar (1-movil) (2-tablet) (3-portatil) (4-ordenador): ")
	(assert (escenario = (read T)))
	(printout T crlf)
)


;Regla que compruebe si es un escenario

(defrule comprobar-escenario
	(escenario ?x)
	(not (escenario 1))
	(not (escenario 2))
	(not (escenario 3))
	(not (escenario 4))
=>
	(reset)
	(run)
)

;Escenarios Posibles

(defrule escenario-1 "Movil"
	(escenario 1)
=>
	(assert (oav-u (objeto movil) (atributo antiguedad) (valor 14)))
	(assert (oav-u (objeto movil) (atributo sistemaOperativo) (valor actual)))
	(assert (oav-u (objeto movil) (atributo antivirus) (valor inexistente)))
	(assert (oav-m (objeto movil) (atributo fallo) (valor apagaInesperado)))
)

(defrule escenario-2 "Tablet"
	(escenario 2)
=>
	(assert (oav-u (objeto tablet) (atributo antiguedad) (valor 20)))
	(assert (oav-u (objeto tablet) (atributo sistemaOperativo) (valor actual)))
	(assert (oav-u (objeto tablet) (atributo antivirus) (valor actualizado)))
	(assert (oav-m (objeto tablet) (atributo fallo) (valor apagaInesperado)))
	(assert (oav-m (objeto tablet) (atributo fallo) (valor errorArranque)))
	(assert (oav-m (objeto tablet) (atributo fallo) (valor noEnciende)))
	(assert (oav-m (objeto tablet) (atributo fallo) (valor falloBateria)))
	(assert (oav-m (objeto tablet) (atributo fallo) (valor golpesPresentes)))
)

(defrule escenario-3 "Portatil"
	(escenario 3)
=>
	(assert (oav-u (objeto portatil) (atributo antiguedad) (valor 23)))
	(assert (oav-u (objeto portatil) (atributo sistemaOperativo) (valor noActual)))
	(assert (oav-u (objeto portatil) (atributo antivirus) (valor caducado)))
	(assert (oav-m (objeto portatil) (atributo fallo) (valor noEnciende)))
)


(defrule escenario-4 "Ordenador"
	(escenario 4)
=>
	(assert (oav-u (objeto ordenador) (atributo antiguedad) (valor 10)))
	(assert (oav-u (objeto ordenador) (atributo sistemaOperativo) (valor actual)))
	(assert (oav-u (objeto ordenador) (atributo antivirus) (valor caducado)))
	(assert (oav-m (objeto ordenador) (atributo fallo) (valor ficherosCorruptos)))
	(assert (oav-m (objeto ordenador) (atributo fallo) (valor errorArranque)))
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;; COMPROBACION GARANTIA Y SISTEMA OP;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defrule comprobar-garantia "Rechaza dispositivos sin garantia o sistema no actual"
	(declare (salience 10))
	(oav-u (objeto ?objeto) (atributo antiguedad) (valor ?meses))
	(oav-u (objeto ?objeto) (atributo sistemaOperativo) (valor ?estado))
	(test(or (> ?meses 24)
		 (neq ?estado actual)))
=>
	(assert (oav-u (objeto ?objeto) (atributo estado) (valor rechazado)))
)

(defrule comprobar-garantia-sistema-aceptado "Acepta dispositivos con garantia y sistema actual"
	(declare (salience 10))
	(oav-u (objeto ?objeto) (atributo antiguedad) (valor ?meses))
	(oav-u (objeto ?objeto) (atributo sistemaOperativo) (valor ?estado))
	(test(and (<= ?meses 24)
		 (eq ?estado actual)))
=>
	(assert (oav-u (objeto ?objeto) (atributo estado) (valor enRevision)))
)	
	    
		
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;      DIAGNOSTICOS        ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrule problema-sistema-alimentacion "Comprueba un problema del sistema de alimentacion"
	(declare (salience 5))
	(oav-u (objeto ?objeto) (atributo estado) (valor enRevision))
	(oav-m (objeto ?objeto) (atributo fallo) (valor falloBateria))
	(or (oav-m (objeto ?objeto) (atributo fallo) (valor apagaInesperado))
	    (oav-m (objeto ?objeto) (atributo fallo) (valor noEnciende)))
=>
	(assert (oav-u (objeto ?objeto) (atributo problema) (valor sistemaAlimentacion)))
	(assert (oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada)))
)



(defrule problema-sistema-operativo "Comprueba un problema del sistema operativo"
	(oav-u (objeto ?objeto) (atributo estado) (valor enRevision))
	(not (oav-u (objeto ?objeto) (atributo problema) (valor sistemaAlimentacion)))
	(or (oav-m (objeto ?objeto) (atributo fallo) (valor errorArranque))
	    (oav-m (objeto ?objeto) (atributo fallo) (valor ficherosCorruptos)))
=>
	(assert (oav-u (objeto ?objeto) (atributo problema) (valor sistemaOperativo)))
	(assert (oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada)))
)


(defrule problema-virus-posible "Comprueba un problema por un posible virus"
	(oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada))
	(oav-u (objeto ?objeto) (atributo problema) (valor sistemaOperativo))
	(or (oav-u (objeto ?objeto) (atributo antivirus) (valor inexistente))
	    (oav-u (objeto ?objeto) (atributo antivirus) (valor caducado)))
=>
	(assert (oav-u (objeto ?objeto) (atributo problema) (valor virusPosible)))
)


(defrule problema-mal-uso "Comprueba un problema por mal uso"
	(oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada))
	(oav-u (objeto ?objeto) (atributo problema) (valor sistemaAlimentacion))
	(oav-m (objeto ?objeto) (atributo fallo) (valor golpesPresentes))
=>
	(assert (oav-u (objeto ?objeto) (atributo problema) (valor malUso)))
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;      IMPRIMIR RESULTADOS    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defrule comunicar-rechazo-garantia "Comunica que el rechazo se debe a falta de garantia"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor rechazado))
	(oav-u (objeto ?objeto) (atributo antiguedad) (valor ?meses & :(> ?meses 24)))
=>
	(printout t "El dispositivo " ?objeto " fue rechazado por superar el periodo de garantia (24 meses)" crlf)
) 
	
(defrule comunicar-rechazo-sistema-operativo "Comunica que el rechazo se debe al sistema operativo"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor rechazado))
	(oav-u (objeto ?objeto) (atributo sistemaOperativo) (valor ?estado))
	(test (neq ?estado actual))
=>
	(printout t "El dispositivo " ?objeto " fue rechazado por no tener un sistema operativo actual" crlf)
) 


(defrule comunicar-problema-alimentacion "Comunica un problema del sistema de alimentacion"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada))
	(oav-u (objeto ?objeto) (atributo problema) (valor sistemaAlimentacion))
=>
	(printout t "El dispositivo " ?objeto " tiene un problema en el sistema de alimentacion" crlf)
)

(defrule comunicar-problema-operativo "Comunica un problema del sistema operativo"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada))
	(oav-u (objeto ?objeto) (atributo problema) (valor sistemaOperativo))
=>
	(printout t "El dispositivo " ?objeto " tiene un problema en el sistema operativo" crlf)
)

(defrule comunicar-problema-virus "Comunica un problema por virus posible"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada))
	(oav-u (objeto ?objeto) (atributo problema) (valor virusPosible))
=>
	(printout t "El dispositivo " ?objeto " tiene un problema por un posible virus" crlf)
)


(defrule comunicar-problema-uso "Comunica un problema por mal uso"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor revisionCompletada))
	(oav-u (objeto ?objeto) (atributo problema) (valor malUso))
=>
	(printout t "El dispositivo " ?objeto " tiene un problema por mal uso" crlf)
)


(defrule comunicar-no-diagnostico "Comunica que no se ha hallado un diagnostico"
	(declare (salience -1000))
	(oav-u (objeto ?objeto) (atributo estado) (valor enRevision))
	(not (oav-u (objeto ?objeto) (atributo problema) (valor ?valor)))
=>
	(printout t "No se ha podido llegar a un diagnostico para el dispositivo: " ?objeto crlf)
)

	