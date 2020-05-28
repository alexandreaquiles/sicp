(ns exercises.chapter1.exercise1_1
  (:use clojure.pprint))

; Exercise 1.1.
;   Below is a sequence of expressions.
;   What is the result printed by the interpreter in response to each expression?
;   Assume that the sequence is to be evaluated in the order in which it is presented.

(pprint 10)
;=> 10

(pprint (+ 5 3 4))
;=> 12

(pprint (- 9 1))
;=> 8

(pprint (/ 6 2))
;=> 3

(pprint (+ (* 2 4) (- 4 6)))
;=> 6

;(define a 3)
;Syntax error compiling at (sicp-exercises1.clj:6:1).
;Unable to resolve symbol: define in this context

(def a 3)
;=> #'loja.core/a

(def b (+ a 1))
;=> #'loja.core/b

(pprint (+ a b (* a b)))
;=> 19

(pprint (= a b))
;=> false

(pprint (if (and (> b a) (< b (* a b)))
  b
  a))
;=> 4

;(cond ((= a 4) 6)
;      ((= b 4) (+ 6 7 a))
;      (else 25))
;Syntax error macroexpanding clojure.core/cond at (sicp-exercises1.clj:13:1).
;cond requires an even number of forms

(pprint (cond (= a 4) 6
      (= b 4) (+ 6 7 a)
      :else 25))
;=> 16

(pprint (+ 2 (if (> b a) b a)))
;=> 6

(pprint (* (cond (> a b) a
         (< a b) b
         :else -1)
   (+ a 1)))
;=> 16
