(ns exercises.chapter2.exercise2_59)

; Exercise 2.59: Implement the union-set operation for the unordered-list representation of sets.

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        :else (element-of-set? x (rest set))))

(defn union-set [set1 set2]
  (cond (empty? set1) set2
        (element-of-set? (first set1) set2) (union-set (rest set1) set2)
        :else (cons (first set1) (union-set (rest set1) set2))))

(union-set '() '())
; => ()

(union-set '(1) '())
; => (1)

(union-set '(1) '(2))
; => (1 2)

(union-set '(1) '(1 2))
; => (1 2)

(union-set '(1 2) '(1 2 3))
; => (1 2 3)