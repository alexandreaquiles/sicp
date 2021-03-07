(ns exercises.chapter2.exercise2_65)

; Exercise 2.65: Use the results of Exercise 2.63 and Exercise 2.64 to give Θ(n) implementations of union-set and
;   intersection-set for sets implemented as (balanced) binary trees.

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (first (rest (rest tree))))
(defn make-tree [entry left right]
  (list entry left right))

(defn tree->list [tree]
  (defn copy-to-list [tree result-list]
    (if (empty? tree)
      result-list
      (copy-to-list (left-branch tree)
                    (cons (entry tree)
                          (copy-to-list
                            (right-branch tree)
                            result-list)))))
  (copy-to-list tree '()))

(defn partial-tree [elts n]
  (if (= n 0)
    (cons '() elts)
    (let [left-size (quot (dec n) 2)]
      (let [left-result (partial-tree elts left-size)]
        (let [left-tree (first left-result)
              non-left-elts (rest left-result)
              right-size (- n (inc left-size))]
          (let [this-entry (first non-left-elts)
                right-result (partial-tree (rest non-left-elts) right-size)]
            (let [right-tree (first right-result)
                  remaining-elts (rest right-result)]
              (cons (make-tree this-entry
                               left-tree
                               right-tree)
                    remaining-elts))))))))

(defn list->tree [elements]
  (first (partial-tree elements (count elements))))

(defn union-set-for-sets-as-lists [set1 set2]
  (cond (empty? set1) set2
        (empty? set2) set1
        (= (first set1) (first set2)) (cons (first set1) (union-set-for-sets-as-lists (rest set1) (rest set2)))
        (< (first set1) (first set2)) (cons (first set1) (union-set-for-sets-as-lists (rest set1) set2))
        (> (first set1) (first set2)) (cons (first set2) (union-set-for-sets-as-lists set1 (rest set2)))))

(defn intersection-set-for-sets-as-lists [set1 set2]
  (if (or (empty? set1) (empty? set2))
    '()
    (let [x1 (first set1) x2 (first set2)]
      (cond (= x1 x2) (cons x1 (intersection-set-for-sets-as-lists (rest set1) (rest set2)))
            (< x1 x2) (intersection-set-for-sets-as-lists (rest set1) set2)
            (< x2 x1) (intersection-set-for-sets-as-lists set1 (rest set2))))))

(def an-example-tree
  (make-tree 7
             (make-tree 3
                        (make-tree 1 nil nil)
                        (make-tree 5 nil nil))

             nil))

(def another-example-tree
  (make-tree 3
             (make-tree 1 nil nil)
             (make-tree 7
                        (make-tree 5 nil nil)
                        (make-tree 9
                                   nil
                                   (make-tree 11 nil nil)))))

(defn intersection-set [tree1 tree2]
  (let [list1 (tree->list tree1)
        list2 (tree->list tree2)
        intersection-list (intersection-set-for-sets-as-lists list1 list2)
        intersection-tree (list->tree intersection-list)]
    intersection-tree))

(intersection-set an-example-tree another-example-tree)
; => (3 (1 () ()) (5 () (7 () ())))

(defn union-set [tree1 tree2]
  (let [list1 (tree->list tree1)
        list2 (tree->list tree2)
        union-list (union-set-for-sets-as-lists list1 list2)
        union-tree (list->tree union-list)]
    union-tree))

(union-set an-example-tree another-example-tree)
; => (5 (1 () (3 () ())) (9 (7 () ()) (11 () ())))