(ns exercises.chapter2.exercise2_18)

; Exercise 2.18:
; Define a procedure reverse that takes a list as argument and returns a list of the same elements in
;   reverse order:
;
;   (reverse (list 1 4 9 16 25))
;   (25 16 9 4 1)

(defn reverse [list]
  (loop [list list reversed '()]
    (if (empty? list)
      reversed
      (recur (rest list) (cons (first list) reversed)))))

(reverse (list 1 4 9 16 25))
; => (25 16 9 4 1)

(reverse (list 1))
; => (1)

(reverse '())
; => ()
