(ns exercises.chapter2.exercise2_60)

; Exercise 2.60: We specified that a set would be represented as a list with no duplicates.
; Now suppose we allow duplicates.
; For instance, the set {1, 2, 3} could be represented as the list (2 3 2 1 3 2 2).
; Design procedures element-of-set?, adjoin-set, union-set, and intersection-set that operate on this representation.
; How does the efficiency of each compare with the corresponding procedure for the non-duplicate representation?
; Are there applications for which you would use this representation in preference to the non-duplicate one?

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        :else (element-of-set? x (rest set))))

(element-of-set? 1 '(2 3 2 1 3 2 2))
; => true

(element-of-set? 2 '(2 3 2 1 3 2 2))
; => true

(element-of-set? 3 '(2 3 2 1 3 2 2))
; => true

(element-of-set? 4 '(2 3 2 1 3 2 2))
; => false

(defn adjoin-set [x set]
  (cons x set))

(adjoin-set 1 '())
; => (1)

(adjoin-set 1 '(1))
; => (1 1)

(adjoin-set 3 '(2 3))
; => (3 2 3)

(defn intersection-set [set1 set2]
  (cond (or (empty? set1) (empty? set2)) '()
        (element-of-set? (first set1) set2) (cons (first set1) (intersection-set (rest set1) set2))
        :else (intersection-set (rest set1) set2)))

(intersection-set '(1) '(2 3 2 1 3 2 2) )
; => (1)

(intersection-set '(2) '(2 3 2 1 3 2 2) )
; => (2)

(def union-set concat)

(union-set '(1 2 3) '(2 3 2 1 3 2 2))
; => (1 2 3 2 3 2 1 3 2 2)

(union-set '(4 5 5) '(2 3 2 1 3 2 2))
; => (4 5 5 2 3 2 1 3 2 2)

; element-of-set? – identical O(n) but n would be a bit larger
; adjoin-set - O(1) with repetition vs O(n)
; intersection-set – identical O(n) but n would be a bit larger
; union-set - both O(n) but n would be a bit larger

; applications that would adjoin-set more than other operations