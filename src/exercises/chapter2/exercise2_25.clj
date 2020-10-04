(ns exercises.chapter2.exercise2_25)

; Exercise 2.25:
; Give combinations of car's and cdr's that will pick 7 from each of the following lists:

(def list1 '(1 3 (5 7) 9))
(def list2 '((7)))
(def list3 '(1 (2 (3 (4 (5 (6 7)))))))

; list1

(rest list1)
; => (3 (5 7) 9)

(rest (rest list1))
; => ((5 7) 9)

(first (rest (rest list1)))
; => (5 7)

(rest (first (rest (rest list1))))
; => (7)

(first (rest (first (rest (rest list1)))))
; => 7

; list2

(rest list2)
; => ()

(first list2)
; => (7)

(first (first list2))
; => 7

(ffirst list2)
; => 7

; list3

(rest list3)
; => ((2 (3 (4 (5 (6 7))))))

(rest (rest list3))
; => ()

(first (rest list3))
; => (2 (3 (4 (5 (6 7)))))

(first (rest (first (rest (first (rest (first (rest (first (rest (first (rest list3))))))))))))
; => 7
