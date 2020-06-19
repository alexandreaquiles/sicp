(ns exercises.chapter1.exercise1_25)

;Exercise 1.25:
; Alyssa P. Hacker complains that we went to a lot of extra work in writing expmod .
; After all, she says, since we already know how to compute exponentials, we could have simply written
;
; (define (expmod base exp m)
;   (remainder (fast-expt base exp) m))
;
; Is she correct? Would this procedure serve as well for our fast prime tester?
; Explain.

(defn square [x] (* x x))

(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(expmod 8652 10001 10001)
; => 6

(defn expmod-alyssa [base exp m]
  (rem (fast-expt base exp) m))

(expmod-alyssa 8652 10001 10001)
;Execution error (ArithmeticException)
;integer overflow

;From the text on expmod:
;(Two numbers are said to be congruent modulo n if they both have the same remainder when divided by n.
; The remainder of a number a when divided by n is also referred to as the remainder of a modulo n, or simply
;   as a modulo n.)
; This is very similar to the fast-expt procedure of Section 1.2.4.
; It uses successive squaring, so that the number of steps grows logarithmically with the exponent. 46

;Footnote 46
; The reduction steps in the cases where the exponent e is greater than 1 are based on the fact that,
;   for any integers x , y, and m, we can find the remainder of x times y modulo m by computing separately
;   the remainders of x modulo m and y modulo m, multiplying these, and then taking the remainder of the result
;   modulo m.
; For instance, in the case where e is even, we compute the remainder of b^e/2 modulo m, square this, and take
;   the remainder modulo m.
; This technique is useful because it means we can perform our computation without ever having to deal with
;   numbers much larger than m. (Compare Exercise 1.25.)

; Alyssa's version squares larger and larger numbers, until we get ArithmeticException/integer overflow.
