; Definir plantilla

(deftemplate persona "Un ejemplo de plantilla"
	(multislot nombre)
	(slot edad)
	(slot color-ojos)
	(slot pelo )
)

; Hechos iniciales

(deffacts colegas "Algunos colegas"
	(persona (nombre "Luis Perez Rubio")
		(edad 23)
		(color-ojos azul)
		(pelo rubio)
	)
	(persona (nombre "Juan Soto Garcia") 
		(edad 24)
		(color-ojos gris) 
		(pelo castano)
	)
)

; Única regla

(defrule persona-sin-pelo-moreno
	(persona (nombre ?nombre) (pelo ~moreno))
=>
	(printout t ?nombre " no tiene el pelo moreno." crlf)
)

(defrule persona-con-pelo-moreno-o-rubio
	(persona (nombre ?nombre) (pelo negro | rubio))
=>
	(printout t ?nombre " tiene el pelo moreno o rubio." crlf)
)

(defrule pelo-moreno-o-rubio-y-otro-color
	(persona (nombre ?nombre1)(pelo ?color1 & moreno | rubio))
	(persona (nombre ?nombre2)(pelo ?color2 & ~?color1))
=>
	(printout t ?nombre1 " tiene el pelo " ?color1 "." crlf)
	(printout t ?nombre2 " tiene el pelo " ?color2 "." crlf)
)

