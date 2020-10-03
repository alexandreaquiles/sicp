(ns exercises.chapter2.exercise2_30
  (:use exercises.chapter2.text))

; Exercise 2.30:
; Define a procedure square-tree analogous to the square-list procedure of Exercise 2.21.
; That is, square- tree should behave as follows:
;
;   (square-tree
;     (list 1
;           (list 2 (list 3 4) 5)
;           (list 6 7)))
;
; (1 (4 (9 16) 25) (36 49))
;
; Define square-tree both directly (i.e., without using any higher-order procedures)
;   and also by using map and recursion.

(defn square [x] (* x x))

(defn square-tree [tree]
  (cond (not (seq? tree)) (square tree)
        (empty? tree) nil
        :else (cons (square-tree (first tree))
                    (square-tree (rest tree)))))

(square-tree
       (list 1
             (list 2 (list 3 4) 5)
             (list 6 7)))
; => (1 (4 (9 16) 25) (36 49))

(defn square-tree-with-map [tree]
  (map (fn [sub-tree]
         (if (not (seq? sub-tree))
           (square sub-tree)
           (square-tree-with-map sub-tree)))
    tree))

(square-tree-with-map
  (list 1
        (list 2 (list 3 4) 5)
        (list 6 7)))
; => (1 (4 (9 16) 25) (36 49))