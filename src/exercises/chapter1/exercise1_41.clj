(ns exercises.chapter1.exercise1_41)

; Exercise 1.41:
; Define a procedure double that takes a procedure of one argument as argument and returns a procedure that applies
;   the original procedure twice.
; For example, if inc is a procedure that adds 1 to its argument, then (double inc) should be a procedure that adds 2.
; What value is returned by
;
;   (((double (double double)) inc) 5)

(defn double [f]
  (fn [x] (f (f x))))

((double inc) 5)


; => 7
; same as
;   ((fn [x] (inc (inc x))) 5)
; or
;   (inc (inc 5))

((double dec) 5)
; => 3
; same as (dec (dec 5)) => 3

(((double double) inc) 5)
; => 9
; same as
;(((fn [x] (double (double x))) inc) 5)
;(((fn [x] (double (fn [y] (x (x y))))) inc) 5)
;(((fn [x] (fn [z] ((fn [y] (x (x y))) ((fn [y] (x (x y))) z)))) inc) 5)
; => 9

(((double (double double)) inc) 5)
; => 21
