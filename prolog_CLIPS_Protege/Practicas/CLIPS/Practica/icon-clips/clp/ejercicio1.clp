(deftemplate   oav-m
 (slot objeto (type SYMBOL))
 (slot atributo (type SYMBOL))
 (slot valor))

(deftemplate oav-u
	 (slot objeto (type SYMBOL))
	 (slot atributo(type SYMBOL))
	 (slot valor))

(deffacts hechos-no-univaluados
	 (oav-u (objeto Juan)
		 (atributo edad)
		 (valor 35))
	 (oav-u (objeto Juan)
		 (atributo edad)
		 (valor 41))
)

(defrule eliminar-hecho-univaluado
	(declare (salience 1000))
	?x <- (oav-u (objeto ?objeto) (atributo ?atributo) (valor ?valor1))
	?y <- (oav-u (objeto ?objeto) (atributo ?atributo) (valor ?valor2)) 
	(test (neq ?valor1 ?valor2))
    (test (< (fact-index ?x) (fact-index ?y)))
	=>
	(retract ?x)
)