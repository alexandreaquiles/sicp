(ns exercises.chapter1.exercise1_46)

; Exercise 1.46:
; Several of the numerical methods described in this chapter are instances of an extremely general computational
;   strategy known as iterative improvement.
; Iterative improvement says that, to compute something, we start with an initial guess for the answer, test if the
;   guess is good enough, and otherwise improve the guess and continue the process using the improved guess as the
;   new guess.
; Write a procedure iterative-improve that takes two procedures as arguments: a method for telling whether a guess is
;   good enough and a method for improving a guess.
; iterative-improve should return as its value a procedure that takes a guess as argument and keeps improving the guess
;   until it is good enough.
; Rewrite the sqrt procedure of Section 1.1.7 and the fixed-point procedure of Section 1.3.3 in terms of
;   iterative-improve.

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(defn average [x y]
  (/ (+ x y) 2))

(defn square [x]
  (* x x))

(defn iterative-improve [good-enough? improve]
  (fn [guess]
    (if (good-enough? guess)
      guess
      (recur (improve guess)))))

(defn sqrt [x]
  ((iterative-improve
    (fn [guess] (< (abs (- (square guess) x)) 0.001))
    (fn [guess] (average guess (/ x guess))))
   1.0))

(sqrt 4)
; => 2.0000000929222947

(defn average-damp [f]
  (fn [x] (average x (f x))))

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  ((iterative-improve
     (fn [guess] (< (abs (- guess (f guess))) tolerance))
     (fn [guess] (f guess)))
     first-guess))

(defn sqrt-with-fixed-point [x]
  (fixed-point (average-damp (fn [y] (/ x y)))
               1.0))

(sqrt-with-fixed-point 4)
; => 2.0000000929222947