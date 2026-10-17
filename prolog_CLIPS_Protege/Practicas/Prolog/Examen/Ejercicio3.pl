% Ejercicio3 Hecho por Angel Antolin Caramazana



% Estados posibles
mueve(q0, c, [z], q0, [a,b,b,a,z]).
mueve(q0, c, [X|H], q0, [a,b,b,a|[X|H]]).
mueve(q0, a, [a|H], q1, H).
mueve(q1, a, [a|H], q1, H).
mueve(q1, b, [b|H], q1, H).
mueve(q1, a, [z], q1, [b,z]).
mueve(q1, b, [b,z], q1, z).







% Maneja la lista de entrada y llama a mueve para ir cambiando de estado
transita(q1, [],[z], qf, [z]).


transita(Qi, [X|Y], Pila, Qf, T):-
         X\= [],
         mueve(Qi, X, Pila, Q, S),
         transita(Q, Y, S, Qf, T).


% Comprobacion final de que la cadena cumple con las aceptadas en el lenguaje
acepta(X,R):-
    transita(q0, X, [z], Q, _),
    Q = qf,
    R is 1,
    !.




% Meta-Interprete de traza que indenta las clausulas con 3 espacios y
% para al llegar a una profundidad maxima, el corte despues de
% solve_pmax_aux(B, Pmax, N1) esta porque si no me lo hacia 2 veces
solve_pmax(A, Pmax):- solve_pmax_aux(A,Pmax,0).


solve_pmax_aux(true,_,_):- !.
solve_pmax_aux((A,B),Pmax,N):- !, solve_pmax_aux(A,Pmax,N), solve_pmax_aux(B,Pmax,N).
solve_pmax_aux(A,_,_):- predicate_property(A, built_in), !, call(A).

solve_pmax_aux(A,Pmax,N):-
    N1 is N + 1,
    N1 =< Pmax,
    clause(A,B),
    solve_pmax_aux(B,Pmax, N1), !,
    Espacios is 3 * N1,
    tab(Espacios),
    write(N1),
    tab(1),
    write(A),
    nl.
