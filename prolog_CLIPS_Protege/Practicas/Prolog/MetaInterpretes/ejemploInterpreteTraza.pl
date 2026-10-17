solve_traza(G):-
    solve_traza_aux(G,0).



solve_traza_aux(true,_):-!.

solve_traza_aux((A, B),N) :-!, solve_traza_aux(A,N), solve_traza_aux(B,N).

solve_traza_aux(A,N):-
    N1 is N + 1,
    clause(A,B),
    solve_traza_aux(B,N1),
    Espacios is 3 * N1,
    tab(Espacios),
    write(N1),
    tab(1),
    write(A),
    nl.

conectado(w2, w1).
conectado(w3, w2).
valor(w1, 1).
valor(W,X):-conectado(W,V), valor(V,X).
