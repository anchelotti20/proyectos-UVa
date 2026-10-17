% Ejercicio2 Hecho por Angel Antolin Caramazana


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




% Meta-Interprete de traza que indenta las clausulas con 3 espacios, el
% corte despues de solve_traza_aux(B,N1) esta porque si no me lo hacia 2
% veces

solve_traza(A):- solve_traza_aux(A,0).


solve_traza_aux(true,_):- !.
solve_traza_aux((A,B),N):- !, solve_traza_aux(A,N), solve_traza_aux(B,N).
solve_traza_aux(A,_):- predicate_property(A, built_in), !, call(A).

solve_traza_aux(A,N):-
    N1 is N + 1,
    clause(A,B),
    solve_traza_aux(B, N1), !,
    Espacios is 3 * N1,
    tab(Espacios),
    write(N1),
    tab(1),
    write(A),
    nl.

