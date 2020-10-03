(ns exercises.chapter2.exercise2_24)

; Exercise 2.24:
; Suppose we evaluate the expression (list 1 (list 2 (list 3 4)))
; Give the result printed by the interpreter, the corresponding box-and-pointer structure,
;   and the interpretation of this as a tree (as in Figure 2.6).

(list 1 (list 2 (list 3 4)))
; => (1 (2 (3 4)))

(cons 1 (cons (cons 2 (cons (cons 3 (cons 4 '())) '())) '()))
; => (1 (2 (3 4)))

; images in:
;   /doc/images/exercise2_24