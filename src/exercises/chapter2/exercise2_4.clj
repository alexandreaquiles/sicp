(ns exercises.chapter2.exercise2_4)

; Exercise 2.4:
; Here is an alternative procedural representation of pairs.
; For this representation, verify that
;   (car (cons x y)) yields x for any objects x and y.
;
;   (define (cons x y)
;     (lambda (m) (m x y)))
;   (define (car z)
;     (z (lambda (p q) p)))
;
; What is the corresponding definition of cdr?
; (Hint: To verify that this works, make use of the substitution model of Section 1.1.5.)

(defn cons [x y]
  (fn [m] (m x y)))
;WARNING: cons already refers to: #'clojure.core/cons

(defn car [z]
  (z (fn [p q] p)))

(defn cdr [z]
  (z (fn [p q] q)))

(car (cons 1 2))
; => 1

(cdr (cons 1 2))
; => 2

(car (cons 1 (cons 2 3)))
;=> 1

(cdr (cons 1 (cons 2 3)))
;=> #object[exercises.chapter2.exercise2_4$cons$fn__1589 ...

(car (cdr (cons 1 (cons 2 3))))
; => 2

(cdr (cdr (cons 1 (cons 2 3))))
; => 3

;(car (cdr (cdr (cons 1 (cons 2 3)))))
; Execution error (ClassCastException)
; class java.lang.Long cannot be cast to class clojure.lang.IFn

;(cdr (cdr (cdr (cons 1 (cons 2 3)))))
; Execution error (ClassCastException)
; class java.lang.Long cannot be cast to class clojure.lang.IFn