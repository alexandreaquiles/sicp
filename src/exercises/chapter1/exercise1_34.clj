(ns exercises.chapter1.exercise1_34)

; Exercise 1.34:
; Suppose we define the procedure
;
;   (define (f g) (g 2))
;
; Then we have
;
;   (f square)
;   4
;
;   (f (lambda (z) (* z (+ z 1))))
;   6
;
; What happens if we (perversely) ask the interpreter to evaluate the combination (f f)?
; Explain.

(defn square [x] (* x x))

(defn f [g] (g 2))

(f square)
; => 4

(f (fn [z] (* z (+ z 1))))
; => 6

(f f)
;   (g 2) with g being f
;   (f 2)
; then
;   (2 2)
; but 2 is not a function.
; So we get:
;   Execution error (ClassCastException)
;   class java.lang.Long cannot be cast to class clojure.lang.IFn