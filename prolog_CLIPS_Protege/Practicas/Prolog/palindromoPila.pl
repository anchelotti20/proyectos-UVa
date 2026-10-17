mueve(q0,a,[z],q0,[a|z]).
mueve(q0,b,[z],q0,[b|z]).
mueve(q0,a,[X|H],q0,[a|[X|H]]).
mueve(q0,b,[X|H],q0,[b|[X|H]]).
mueve(q0,e,A,q1,A).
mueve(q1,a,[a|H],q1,H).
mueve(q1,b,[b|H],q1,H).
mueve(q1,[],[z],qf,[z]).

delay(mueve(_,_,_,_,_)).

transita(q1,[],[z],qf,[z]):-!.

transita(Qi, [X|Y], Pila, Qf, T):-

    X \=[],
    mueve(Qi, X, Pila, Q, S),
    transita(Q, Y, S, Qf, T).

acepta(X,R):-

    transita(q0,X,[z],Q,_),
    Q = qf,
    R is 1,
    !.




solve(true):-!.
solve((A,B)):- !, solve(A), solve(B).
solve(A):- predicate_property(A,built_in),!,call(A).
solve(A):- !, clause(A,B), solve(B).

/**
* Meta interprete para metas diferidas,
* devuelve en una lista las cosas que se tienen que cumplir para que la meta final sea correcta
* hay que añadir delay(...) en las metas que se quieren diferir 
*/
dsolve(true, D, D):- !.
dsolve((A,B), D1, D2):- !, dsolve(A, D1, D3), dsolve(B, D3, D2).

dsolve(A, D, D):- delay(A), member(A,D), !.
dsolve(A,D, [A|D]):- delay(A), !.

dsolve(A,_,_):- predicate_property(A,built_in),!,call(A).
dsolve(A, D1, D2):- !, clause(A,B), dsolve(B, D1, D2).

solve_prueba(true, true):-!.
solve_prueba((A,B),(ProofA, ProofB)):- !, solve_prueba(A, ProofA), solve_prueba(B, ProofB).
solve_prueba(A, _):- predicate_property(A,built_in),!,call(A).
solve_prueba(A, (A:-Proof)):- !, clause(A,B), solve_prueba(B, Proof).

solve_traza(true):-!.
solve_traza((A,B)):- !, solve_traza(A), solve_traza(B).
solve_traza(A):- predicate_property(A,built_in),!,call(A).
solve_traza(A):- 
    write("Call: "), write(A), nl,
    clause(A,B), solve(B),
    write("Exit: "), write(B), nl.

solve_traza_tab(A):- solve_traza_tab_aux(A, 0).

solve_traza_tab_aux(true, _):- !.
solve_traza_tab_aux((A,B), N):- !, solve_traza_tab_aux(A, N), solve_traza_tab_aux(B, N).
solve_traza_tab_aux(A,_):- predicate_property(A,built_in),!,call(A).
solve_traza_tab_aux(A, N):- !,
    N1 is N + 1,
    clause(A,B),
    solve_traza_tab_aux(B, N1),
    Tab is 3 * N1,
    tab(Tab),
    write(N1),
    tab(1),
    write(A),
    nl.

/**
* Para usar este es necesario invertir todas las reglas que tengan mas de un componente
*/
solve_derecha_izquierda(true):-!.
solve_derecha_izquierda((A,B)):- solve_derecha_izquierda(B), solve_derecha_izquierda(A).
solve_derecha_izquierda(A):- predicate_property(A,built_in),!,call(A).
solve_derecha_izquierda(A):- !, clause(A,B), solve_derecha_izquierda(B).

solve_traza_tab_hasta_profundidad(A, Prf):- solve_traza_tab_hasta_profundidad_aux(A, Prf, 0).

solve_traza_tab_hasta_profundidad_aux(true, _, _):- !.
solve_traza_tab_hasta_profundidad_aux((A,B), Prf, N):- !, solve_traza_tab_hasta_profundidad_aux(A, Prf, N), solve_traza_tab_hasta_profundidad_aux(B, Prf, N).
solve_traza_tab_hasta_profundidad_aux(A,_,_):- predicate_property(A,built_in),!,call(A).
solve_traza_tab_hasta_profundidad_aux(A, Prf, N):- !,
    N1 is N + 1,
    N1 =< Prf,
    clause(A,B),
    solve_traza_tab_hasta_profundidad_aux(B, Prf, N1),
    Tab is 3 * N1,
    tab(Tab),
    write(N1),
    tab(1),
    write(A),
    nl.