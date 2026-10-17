


/*LO PODRIA HABER HECHO AÑADIENDO "H" A LA PILA POR CADA A o B
 * Y AL ENTRAR UNA C, BORRAR UNA "H" DE LA PILA*/

mueve(q0,a,A,B,q0,A + 1,B).
mueve(q0,b,A,B,q0,A+1,B).
mueve(q0,c,A,B,q0,A,B+1).
mueve(q0,[],A,B,qf,A,B).

transita(q0,[],A,B,qf,A,B):-!.


transita(Qi, [X|Cola], ContadorAB, ContadorC, Qf, A, B):-

    X \= [],
    mueve(Qi, X, ContadorAB, ContadorC, Q, A1, B1),
    transita(Q, Cola, A1, B1, Qf, A, B).

acepta(X,R):-

    transita(q0,X, 0, 0, Q, A , B),
    Q = qf,
    A =:= B,
    R is 1,
    !.

solve_traza(A):- solve_traza_aux(A,0).


solve_traza_aux(true,_):-!.

solve_traza_aux((A, B),N) :-!, solve_traza_aux(A,N), solve_traza_aux(B,N).

solve_traza_aux(A,_):- predicate_property(A,built_in),!,call(A).


solve_traza_aux(A,N):-
    N1 is N + 1,
    clause(A,B),
    solve_traza_aux(B,N1),
    Tabular is 3 * N1,
    tab(Tabular),
    write(N1),
    tab(1),
    write(A),
    nl.


