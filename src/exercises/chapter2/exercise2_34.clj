(ns exercises.chapter2.exercise2_34)

; Exercise 2.34: Evaluating a polynomial in x at a given value of x can be formulated as an accumulation.
; We evaluate the polynomial
;
;   an.x^n + an−1.x^n−1 + ... + a1.x + a0
;
; using a well-known algorithm called Horner’s rule, which structures the computation as
;
;   (... (an.x + an−1)x + ... + a1)x + a0 .
;
; In other words, we start with an, multiply by x, add an−1, multiply by x, and so on, until we reach a0.
; Fill in the following template to produce a procedure that evaluates a polynomial using Horner’s rule.
; Assume that the coefficients of the polynomial are arranged in a sequence, from a0 through an.
;
;   (define (horner-eval x coefficient-sequence)
;     (accumulate (lambda (this-coeff higher-terms) ⟨ ?? ⟩ )
;                 0
;                 coefficient-sequence))
;
; For example, to compute 1 + 3x + 5x³ + x⁵ at x = 2 you would evaluate
;
;   (horner-eval 2 (list 1 3 0 5 0 1))

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn horner-eval [x coefficient-sequence]
  (accumulate (fn [this-coeff higher-terms] (+ (* x higher-terms) this-coeff) )
              0
              coefficient-sequence))

(horner-eval 2 (list 1 3 0 5 0 1))
; => 79

(+ 1 (* 3 2) (* 5 (Math/pow 2 3)) (Math/pow 2 5))
; => 79.0

(+ (* (+ (* (+ (* (+ (* (+ (* 1 2) 0) 2) 5) 2) 0) 2) 3) 2) 1)
; => 79