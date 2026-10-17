(deftemplate persona (slot nombre) (slot edad))


; Hechos iniciales

(deffacts colegas "Algunos colegas"
	(persona (nombre "Luis Perez Rubio")
		(edad 23)
	)
	(persona (nombre "Jimena Gonzalez Fernandez") 
		(edad 17)
	)
	(persona (nombre "Sol Ruiz Alonso") 
		(edad 18)
	)
	(persona (nombre "Juan Soto Garcia") 
		(edad 24)
	)
)

; Única regla

(defrule mayor-de-edad
	(persona 	(nombre ?nombre)
			(edad ?edad & :(>= ?edad 18)))
=>
	(printout t ?nombre " es mayor de edad." crlf)
)
