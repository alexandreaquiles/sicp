(ns exercises.chapter2.exercise2_66)

; Exercise 2.66: Implement the lookup procedure for the case where the set of records is structured as a binary tree,
;   ordered by the numerical values of the keys.

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (first (rest (rest tree))))
(defn make-tree [entry left right]
  (list entry left right))

(def an-example-tree-of-records
  (make-tree '(7111 "John")
             (make-tree '(3222 "Mary")
                        (make-tree '(1333 "Lucas") nil nil)
                        (make-tree '(5444 "Alex") nil nil))

             nil))

(defn lookup [given-key tree-of-records]
  (if (empty? tree-of-records)
    false
    (let [record (entry tree-of-records)
          key (first record)]
      (cond (= given-key key) record
            (< given-key key) (lookup given-key (left-branch tree-of-records))
            :else             (lookup given-key (right-branch tree-of-records))))))

(lookup 1333 an-example-tree-of-records)
; => (1333 "Lucas")

(lookup 9555 an-example-tree-of-records)
; => false