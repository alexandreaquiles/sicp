(ns exercises.chapter1.exercise1_20)

;Exercise 1.20:
; The process that a procedure generates is of course dependent on the rules
;   used by the interpreter.
; As an example, consider the iterative gcd procedure given above.
; Suppose we were to interpret this procedure using normal-order evaluation,
;   as discussed in Section 1.1.5.
; (The normal-order-evaluation rule for if is described in Exercise 1.5.)
; Using the substitution method (for normal order), illustrate the process
;   generated in evaluating (gcd 206 40) and indicate the remainder operations
;   that are actually performed.
; How many remainder operations are actually performed in the normal-order
;   evaluation of (gcd 206 40) ?
; In the applicative-order evaluation?

;GCD(206,40)  = GCD(40,6)
;             = GCD(6,4)
;             = GCD(4,2)
;             = GCD(2,0)
;             = 2

;From exercise 1.5:
; Assume that the evaluation rule for the special form if is the
;   same whether the interpreter is using normal or applicative order:
; The predicate expression is evaluated first, and the result determines
;   whether to evaluate the consequent or the alternative expression.

(defn gcd [a b] (if (= b 0) a (recur b (rem a b))))

;applicative-order evaluation
(gcd 206 40)
;(if (= 40 0) 206 (recur 40 (rem 206 40)))
;(if false 206 (recur 40 (rem 206 40)))
;              (recur 40 (rem 206 40))                                   ;rem
;              (recur 40 6)
;              (if (= 6 0) 40 (recur 6 (rem 40 6)))
;              (if false 40 (recur 6 (rem 40 6)))
;                           (recur 6 (rem 40 6))                         ;rem
;                           (recur 6 4)
;                           (if (= 4 0) 6 (recur 4 (rem 6 4)))
;                           (if false 6 (recur 4 (rem 6 4)))
;                                       (recur 4 (rem 6 4))              ;rem
;                                       (recur 4 2)
;                                       (if (= 2 0) 4 (recur 2 (rem 4 2)))
;                                       (if false 4 (recur 2 (rem 4 2)))
;                                                   (recur 2 (rem 4 2))  ;rem
;                                                   (recur 2 0)
;                                                   (if (= 0 0) 2 (recur 2 (rem 2 0)))
;                                                               2
; rem + rem + rem + rem
; rem performed 4 times

;normal-order evaluation
(gcd 206 40)
;a = 206, b = 40
;(if (= 40 0) 206 (recur 40 (rem 206 40)))
;(if   false  206 (recur 40 (rem 206 40)))
;                 (recur 40 (rem 206 40))
;                 a = 40, b = (rem 206 40) = 6
;                 (if (= (rem 206 40) 0) 40 (recur (rem 206 40) (rem 40 (rem 206 40)))) ;rem
;                 (if (=       6      0) 40 (recur (rem 206 40) (rem 40 (rem 206 40))))
;                 (if        false       40 (recur (rem 206 40) (rem 40 (rem 206 40))))
;                                           (recur (rem 206 40) (rem 40 (rem 206 40)))
;                                           a = (rem 206 40) = 6, b = (rem 40 (rem 206 40)) = 4
;                                           (if (= (rem 40 (rem 206 40)) 0) (rem 206 40) (recur (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))) ; 2 rem
;                                           (if (= (rem 40       4     ) 0) (rem 206 40) (recur (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))))
;                                           (if              false          (rem 206 40) (recur (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))
;                                                                                        (recur (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))
;                                                                                        a = (rem 40 (rem 206 40)) = 4, b = (rem (rem 206 40) (rem 40 (rem 206 40))) = 2
;                                                                                        (if (= (rem (rem 206 40) (rem 40 (rem 206 40))) 0) (rem 40 (rem 206 40)) (recur (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))))) ; 4 rem
;                                                                                        (if (=                     2                    0) (rem 40 (rem 206 40)) (recur (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))))
;                                                                                        (if                       false                    (rem 40 (rem 206 40)) (recur (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))))
;                                                                                                                                                                 (recur (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))))
;                                                                                                                                                                 a = (rem (rem 206 40) (rem 40 (rem 206 40))) = 2, b = (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) = 0
;                                                                                                                                                                 (if (= (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) 0) (rem (rem 206 40) (rem 40 (rem 206 40))) (recur (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) (rem (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))))) ; 7 rem
;                                                                                                                                                                 (if (=                           0                                          0) (rem (rem 206 40) (rem 40 (rem 206 40))) (recur (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) (rem (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))))
;                                                                                                                                                                 (if                             true                                           (rem (rem 206 40) (rem 40 (rem 206 40))) (recur (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) (rem (rem (rem 206 40) (rem 40 (rem 206 40))) (rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40))))))
;                                                                                                                                                                                                                                                (rem (rem 206 40) (rem 40 (rem 206 40))) ; 4 rem
;                                                                                                                                                                                                                                                                 2
; rem + 2 rem + 4 rem + 7 rem + 4 rem
; rem performed 18 times

(rem 206 40)                                                         ; => 6
(rem 40 (rem 206 40))                                                ; => 4
(rem (rem 206 40) (rem 40 (rem 206 40)))                             ; => 2
(rem (rem 40 (rem 206 40)) (rem (rem 206 40) (rem 40 (rem 206 40)))) ; => 0

