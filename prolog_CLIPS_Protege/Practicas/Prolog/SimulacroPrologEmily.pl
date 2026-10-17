mover(q0, a, [z], [z], q1, [h|z], [z]).
mover(q0, b, [z], [z], q1, [h|z], [z]).
mover(q0, c, [z], [z], q1, [z], [h|z]).

mover(q1, a, [z], H, q1, [h|z], H).
mover(q1, b, [z], H, q1, [h|z], H).
mover(q1, c, H, [z], q1, H, [h|z]).

mover(q1, a, H1, H2, q1, [h | H1], H2).
mover(q1, b, H1, H2, q1, [h | H1], H2).
mover(q1, c, H1, H2, q1, H1, [h | H2]).

mover(q1, [], H, H, qf, H, H).


transita(q1, [], H, H, qf, [z], [z]) :- ! .

transita(QInitIter, [Cabeza|Resto], PilaAB, PilaC, QFinal, TAB, TC) :-
    transita(QFinalIter, Resto, PilaABActualizada, PilaCActualizada, QFinal, TAB, TC) ,
    mover(QInitIter, Cabeza, PilaAB, PilaC, QFinalIter, PilaABActualizada, PilaCActualizada) ,
    Cabeza \= [] .

acepta(Lista, Resultado):- !,
    Resultado is 1,
    Q = qf ,
    transita(q0, Lista, [z], [z], Q, _, _) .

solve(true) :- !.
solve((A, B)) :- !, solve(B), solve(A) .
solve(A) :- predicate_property(A,built_in), !, call(A).
solve(A) :- !, clause(A, B), solve(B) .
