(ns exercises.chapter2.exercise2_26
  (:use exercises.chapter2.text))

; Exercise 2.26:
; Suppose we define x and y to be two lists:
;
;   (define x (list 1 2 3))
;   (define y (list 4 5 6))
;
; What result is printed by the interpreter in response to
;   evaluating each of the following expressions:
;
;   (append x y)
;   (cons x y)
;   (list x y)

(def x1 (list 1 2 3))
(def y1 (list 4 5 6))

; In Clojure

(append x1 y1)
; => (1 2 3 4 5 6)

(cons x1 y1)
; => ((1 2 3) 4 5 6)

(list x1 y1)
; => ((1 2 3) (4 5 6))

; In Scheme:

; (append x y)
;   '(1 2 3 4 5 6)

; (cons x y)
;   '((1 2 3) 4 5 6)

; (list x y)
;   '((1 2 3) (4 5 6))
