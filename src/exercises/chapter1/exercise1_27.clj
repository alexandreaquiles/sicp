(ns exercises.chapter1.exercise1_27)

;Exercise 1.27:
; Demonstrate that the Carmichael numbers listed in Footnote 1.47 really do fool the Fermat test.
; That is, write a procedure that takes an integer n and tests whether a^n is congruent to a modulo n
;   for every a < n, and try your procedure on the given Carmichael numbers.

;Footnote 1.47
; Numbers that fool the Fermat test are called Carmichael numbers , and little is known about them other than
;   that they are extremely rare.
; There are 255 Carmichael numbers below 100,000,000.
; The smallest few are 561, 1105, 1729, 2465, 2821, and 6601.
; In testing primality of very large numbers chosen at random, the chance of stumbling upon a value that fools
;   the Fermat test is less than the chance that cosmic radiation will cause the computer to make an error in
;   carrying out a “correct” algorithm.
; Considering an algorithm to be inadequate for the first reason but not for the second illustrates the
;   difference between mathematics and engineering.

(defn square [x] (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn complete-fermat-test [n]
  (defn try-it [a]
    (cond (= a 1) true
          (= (expmod a n n) a) (try-it (dec a))
          :else false))
  (try-it (dec n)))

(complete-fermat-test 2)
; => true

(complete-fermat-test 3)
; => true

(complete-fermat-test 4)
; => false

(assert (= '(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97)
           (filter complete-fermat-test (range 1 100))))

; Carmichael Numbers

;561 is not prime.  It is divisible by 3.
(complete-fermat-test 561)
; => true

;1105 is not prime.  It is divisible by 5.
(complete-fermat-test 1105)
; => true

;1729 is not prime.  It is divisible by 7.
(complete-fermat-test 1729)
; => true

;2465 is not prime.  It is divisible by 5.
(complete-fermat-test 2465)
; => true

;2821 is not prime.  It is divisible by 7.
(complete-fermat-test 2821)
; => true

;6601 is not prime.  It is divisible by 7.
(complete-fermat-test 6601)
; => true
