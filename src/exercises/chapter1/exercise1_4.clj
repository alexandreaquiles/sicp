(ns exercises.chapter1.exercise1_4
  (:use clojure.pprint))

; Exercise 1.4.
;   Observe that our model of evaluation allows for combinations whose operators are compound expressions.
;   Use this observation to describe the behavior of the following procedure:
;
;       (define (a-plus-abs-b a b)
;         ((if (> b 0) + -) a b))

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

;(a-plus-abs-b 1 1)
;retrieve the body of the function

;((if (> b 0) + -) a b)
;replace the parameters

;((if (> 1 0) + -) 1 2)
;evaluate the operands

;((if true + -) 1 2)
;evaluate the operands

;(+ 1 2)

;3

(pprint
  (a-plus-abs-b 1 2))
; => 3

(pprint
  (a-plus-abs-b 1 -1))
; => 2
