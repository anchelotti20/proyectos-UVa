mueve(q0, a, [z], q0, [h,z]).
mueve(q0, a, [h|H], q0, [h|[h|H]]).
mueve(q0, b, [h|H], q1, H).
mueve(q1, b, [h|H], q1, H).
mueve(q1, b, [z], q1, [b,z]).
mueve(q1, b, [b|H], q1, [b|[b|H]]).
mueve(q1, c, [b|H], q2, H).
mueve(q2, c, [b|H], q2, H).


transita(q2, [], [z], qf, [z]).



transita(Qi, [X|Y], R, Qf, T):-
    
    X \= [],
    mueve(Qi, X, R, P, S),
    transita(P, Y, S, Qf, T).

/**
transita(Qi, [X|Y], R, Qf, T):-
    transita(P, Y, S, Qf, T),
    mueve(Qi, X, R, P, S),
    X \= [].
*/



acepta(X, R):-
    transita(q0, X, [z], Q, _),
    Q = qf,
    R is 1,
    !.

/**
acepta(X,R):-
    !,
    R is 1,
    Q = qf,
    transita(q0, X, [z], Q, _).
*/


solve_traza(A):- solve_traza_aux(A,0).


solve_traza_aux(true,_):-!.
solve_traza_aux((A,B), N):-!, solve_traza_aux(A, N), solve_traza_aux(B, N).

solve_traza_aux(A,_):- predicate_property(A,built_in),!,call(A).

solve_traza_aux(A, N):-
    N1 is N + 1,
    clause(A,B),
    solve_traza_aux(B, N1),
    Tabular is 3* N1,
    tab(Tabular),
    write(N1),
    tab(1),
    write(A),
    nl.

/*Ya modificada para que lea de derecha a izquierda*/
solve_pmax(A, Prf):- solve_pmax_aux(A, Prf, 0).

solve_pmax_aux(true,_,_):-!.
solve_pmax_aux((A,B), Prf, N):- solve_pmax_aux(B, Prf, N), solve_pmax_aux(A, Prf, N).

solve_pmax_aux(A,_,_):- predicate_property(A,built_in),!,call(A).

solve_pmax_aux(A, Prf, N):-
    N1 is N + 1,
    N1 =< Prf,
    clause(A,B),
    solve_pmax_aux(B, Prf, N1),
    Tabular is 3* N1,
    tab(Tabular),
    write(N1),
    tab(1),
    write(A),
    nl.
    
