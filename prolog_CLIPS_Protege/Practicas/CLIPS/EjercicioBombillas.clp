(deffacts hechos-iniciales
	(live outside true)
	
	(conectado w5 outside)
	
	(conectado l1 w0)
	
	(conectado l2 w4)
	
	(conectado p1 w3)
	
	(conectado p2 w6)
	
	(ok cb1 true)
	
	(ok cb2 true)
	
	(ok s1 true)
	(estado s1 down)
	
	(ok s2 true)
	(estado s2 up)
	
	
	
	(ok l1 true)
	(light l1 true)
	
	(ok s3 true)
	(estado s3 up)
	
	
	(ok l2 true)
	(light l2 true)
	
	(ok p1 true)
	(power p1 true)
	
	(ok p2 true)
	(power p2 true)
)
	
	
(defrule bombilla-enciende
	(light ?bombilla true)
	(ok ?bombilla true)
	(live ?bombilla true)
	=>
	(assert (lit ?bombilla true))	
)

(defrule enchufe-corriente
	(power ?enchufe true)
	(ok ?enchufe true)
	(live ?enchufe true)
	=>
	(assert (on ?enchufe true))
	
)

(defrule comunicar-encendido-bombilla
	(declare (salience -1000))
	(lit ?bombilla true)
	=>
	(printout t "La bombilla " ?bombilla " esta encendida" crlf)
)

(defrule comunicar-apagado-bombilla
	(declare (salience -1000))
	(light ?bombilla true)
	(not (lit ?bombilla true))
	=>
	(printout t "La bombilla " ?bombilla " esta apagada" crlf)
)

(defrule comunicar-corriente-enchufe
	(declare (salience -1000))
	(on ?enchufe true)
	=>
	(printout t "El enchufe " ?enchufe " le llega corriente" crlf)
)


(defrule cable-tension
	(conectado ?cable ?cable1)
	(live ?cable1 true)
	=>
	(assert (live ?cable true))
)

(defrule conexion-w5-w3
	(ok cb1 true)
	=>
	(assert(conectado w3 w5))
)

(defrule conexion-w5-w6
	(ok cb2 true)
	=>
	(assert (conectado w6 w5))
)

(defrule conexion-w1-w3
	(ok s1 true)
	(estado s1 up)
	=>
	(assert (conectado w1 w3))
)

(defrule conexion-w2-w3
	(ok s1 true)
	(estado s1 down)
	=>
	(assert (conectado w2 w3))
)

(defrule conexion-w0-w1
	(ok s2 true)
	(estado s2 up)
	=>
	(assert (conectado w0 w1))
)

(defrule conexion-w0-w2
	(ok s2 true)
	(estado s2 down)
	=>
	(assert (conectado w0 w2))
)

(defrule conexion-w4-w3
	(ok s3 true)
	(estado s3 up)
	=>
	(assert (conectado w4 w3))
)
