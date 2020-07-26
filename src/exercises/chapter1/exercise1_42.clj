(ns exercises.chapter1.exercise1_42)

; Exercise 1.42: Let
; f and g be two one-argument functions.
; The composition f after g is defined to be the function
;   x → f (g(x)).
; Define a procedure compose that implements composition.
; For example, if inc is a procedure that adds 1 to its argument,
;
;   ((compose square inc) 6)
;   49

(defn compose [f g]
  (fn [x] (f (g x))))

(defn square [x]
  (* x x))

((compose square inc) 6)
; => 49
