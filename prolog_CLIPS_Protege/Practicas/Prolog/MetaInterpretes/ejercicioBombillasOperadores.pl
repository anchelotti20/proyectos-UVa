:-op(40, xfy, &).
:-op(50, xfy, --->).

true ---> down(s1).
true ---> up(s2).
true ---> up(s3).

true ---> ok(_).

ok(cb1) ---> connected_to(outside,w3).
true ---> connected_to(outside,w5).
ok(cb2) ---> connected_to(w5,w6).
down(s1) & ok(s1) ---> connected_to(w3,w2).
up(s1) & ok(s1) ---> connected_to(w3,w1).
up(s3) & ok(s3) ---> connected_to(w3,w4).
true ---> connected_to(w3,p1).
true ---> connected_to(w6,p2).
down(s2) & ok(s2) ---> connected_to(w2,w0).
up(s2) & ok(s2) ---> connected_to(w1,w0).
true ---> connected_to(w0,l1).
true ---> connected_to(w4,l2).

true ---> light(l1).
true ---> light(l2).
true ---> plug(p1).
true ---> plug(p2).

true ---> live(outside).


connected_to(W1,W) & live(W1) ---> live(W).
light(L) & ok(L) & live(L) ---> lit(L).
live(P) & ok(P) & plug(P) ---> suply(P).
