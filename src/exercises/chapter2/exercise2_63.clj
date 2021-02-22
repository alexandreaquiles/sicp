(ns exercises.chapter2.exercise2_63)

; Exercise 2.63: Each of the following two procedures converts a binary tree to a list.
;
; (define (tree->list-1 tree)
;   (if (null? tree)
;     '()
;     (append (tree->list-1 (left-branch tree))
;             (cons (entry tree)
;                   (tree->list-1
;                     (right-branch tree))))))
;
; (define (tree->list-2 tree)
;   (define (copy-to-list tree result-list)
;     (if (null? tree)
;       result-list
;       (copy-to-list (left-branch tree)
;                     (cons (entry tree)
;                           (copy-to-list
;                               (right-branch tree)
;                               result-list)))))
;    (copy-to-list tree '()))
;
; a. Do the two procedures produce the same result for every tree? If not, how do the results differ? What lists
;     do the two procedures produce for the trees in Figure 2.16?
;b. Do the two procedures have the same order of growth in the number of steps required to convert a balanced tree
;     with n elements to a list? If not, which one grows more slowly?

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (first (rest (rest tree))))
(defn make-tree [entry left right]
  (list entry left right))

(def an-example-tree
  (make-tree 7
             (make-tree 3
                        (make-tree 1 nil nil)
                        (make-tree 5 nil nil))

             (make-tree 9
                        nil
                        (make-tree 11 nil nil))))

(def another-example-tree
  (make-tree 3
             (make-tree 1 nil nil)
             (make-tree 7
                        (make-tree 5 nil nil)
                        (make-tree 9
                                   nil
                                   (make-tree 11 nil nil)))))

(def yet-another-example-tree
  (make-tree 5
             (make-tree 3
                        (make-tree 1 nil nil)
                        nil)

             (make-tree 9
                        (make-tree 7 nil nil)
                        (make-tree 11 nil nil))))

(defn tree->list-1 [tree]
 (if (empty? tree)
   '()
   (concat (tree->list-1 (left-branch tree))
           (cons (entry tree)
                 (tree->list-1
                   (right-branch tree))))))

(defn tree->list-2 [tree]
 (defn copy-to-list [tree result-list]
   (if (empty? tree)
     result-list
     (copy-to-list (left-branch tree)
                   (cons (entry tree)
                         (copy-to-list
                             (right-branch tree)
                             result-list)))))
  (copy-to-list tree '()))

; a)

(tree->list-1 an-example-tree)
; => (1 3 5 7 9 11)

(tree->list-2 an-example-tree)
; => (1 3 5 7 9 11)

(tree->list-1 another-example-tree)
; => (1 3 5 7 9 11)

(tree->list-2 another-example-tree)
; => (1 3 5 7 9 11)

(tree->list-1 yet-another-example-tree)
; => (1 3 5 7 9 11)

(tree->list-2 yet-another-example-tree)
; => (1 3 5 7 9 11)

; b)

; tree->list-1
; O (n log n)

; tree->list-2
; O (n)