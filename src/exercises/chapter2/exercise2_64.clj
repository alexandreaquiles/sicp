(ns exercises.chapter2.exercise2_64)

; Exercise 2.64:
; The following procedure list->tree converts an ordered list to a balanced binary tree.
; The helper procedure partial-tree takes as arguments an integer n and list of at least n elements and constructs
;   a balanced tree containing the first n elements of the list.
; The result returned by partial-tree is a pair (formed with cons ) whose car is the constructed tree and whose
;   cdr is the list of elements not included in the tree.

; (define (list->tree elements)
;   (car (partial-tree elements (length elements))))
; (define (partial-tree elts n)
;   (if (= n 0)
;       (cons '() elts)
;       (let ((left-size (quotient (- n 1) 2)))
;         (let ((left-result
;               (partial-tree elts left-size)))
;           (let ((left-tree (car left-result))
;                 (non-left-elts (cdr left-result))
;                 (right-size (- n (+ left-size 1))))
;             (let ((this-entry (car non-left-elts))
;                   (right-result
;                     (partial-tree
;                       (cdr non-left-elts)
;                       right-size)))
;             (let ((right-tree (car right-result))
;                   (remaining-elts
;                     (cdr right-result)))
;               (cons (make-tree this-entry
;                                left-tree
;                                right-tree)
;                     remaining-elts))))))))
;
; a. Write a short paragraph explaining as clearly as you can how partial-tree works.
;   Draw the tree produced by list->tree for the list (1 3 5 7 9 11) .
; b. What is the order of growth in the number of steps required by list->tree to convert a list of n elements?

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (first (rest (rest tree))))
(defn make-tree [entry left right]
  (list entry left right))

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

; a) partial-tree breaks the elements into 2 halves.
; Than, recursively, solves the problem for the left portion and uses the remaining elements to extract the first one as the current entry and to solve for the right portion.
; Then, it constructs a new tree with both the left and the right results and the current entry as the root.

(list->tree '(1 3 5 7 9 11))
; (5 (1 () (3 () ())) (9 (7 () ()) (11 () ())))

; b) The problem halves at each step but 2 recursive calls are made. So, it's O(n).