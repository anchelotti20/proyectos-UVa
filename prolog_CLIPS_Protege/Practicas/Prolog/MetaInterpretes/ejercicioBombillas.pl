builtin(A is B).
builtin(A > B).
builtin(A < B).
builtin(A = B).
builtin(A =:= B).
builtin(A =< B).
builtin(A >= B).
builtin(functor(T, F, N)).
builtin(read(X)).
builtin(write(X)).



down(s1).
up(s2).
up(s3).

ok(_).

connected_to(outside,w3):- ok(cb1).
connected_to(outside,w5).
connected_to(w5,w6):- ok(cb2).
connected_to(w3,w2):- down(s1), ok(s1).
connected_to(w3,w1):- up(s1), ok(s1).
connected_to(w3,w4):- up(s3), ok(s3).
connected_to(w3,p1).
connected_to(w6,p2).
connected_to(w2,w0):- down(s2), ok(s2).
connected_to(w1,w0):- up(s2),ok(s2).
connected_to(w0,l1).
connected_to(w4,l2).

light(l1).
light(l2).
plug(p1).
plug(p2).


live(outside).


live(W):- connected_to(W1,W), live(W1).
lit(L):- light(L), ok(L), live(L).
suply(P):- live(P), ok(P), plug(P).



solve(true):- !.
solve((A,B)) :-!, solve(A), solve(B).
solve(A):- builtin(A), !, A.
solve(A) :- clause(A, B), solve(B).



