(ns exercises.chapter2.exercise2_62)

; Exercise 2.62: Give a Θ(n) implementation of union-set for sets represented as ordered lists.

(defn union-set [set1 set2]
  (cond (empty? set1) set2
        (empty? set2) set1
        (= (first set1) (first set2)) (cons (first set1) (union-set (rest set1) (rest set2)))
        (< (first set1) (first set2)) (cons (first set1) (union-set (rest set1) set2))
        (> (first set1) (first set2)) (cons (first set2) (union-set set1 (rest set2)))))

(union-set '() '())
; => ()

(union-set '(1) '())
; => (1)

(union-set '() '(4))
; => (4)

(union-set '() '(4 5 6 7))
; => (4 5 6 7)

(union-set '(1 2 3) '())
; => (1 2 3)

(union-set '(1 2 3) '(4 5 6 7))
; => (1 2 3 4 5 6 7)

(union-set '(1 2 3 4) '(3 4 5 6 7))
; => (1 2 3 4 5 6 7)

(union-set '(1 3) '(5 7))
; => (1 3 5 7)

(union-set '(1 3) '(3 5 7))
; => (1 3 5 7)

(union-set '(1 5) '(3 7))
; => (1 3 5 7)
