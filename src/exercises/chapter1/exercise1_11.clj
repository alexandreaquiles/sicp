(ns exercises.chapter1.exercise1_11)

; Exercise 1.11.
;   A function f is defined by the rule that
;     f(n) = n if n < 3
;   and
;     f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n >= 3.
;
;   Write a procedure that computes f by means of a recursive process.
;   Write a procedure that computes f by means of an iterative process.

; RECURSIVE PROCESS
(defn f [n]
  (if (< n 3)
      n
      (+ (f (- n 1)) (* 2 (f (- n 2))) (* 3 (f (- n 3))))))

(map f (range 10))
; => (0 1 2 4 11 25 59 142 335 796)

;(defn f [n]
;  (defn f-iter [n a b c]
;    (if (< n 3)
;      n
;      (f-iter (+  ))))
;  )
;
;




