(ns exercises.chapter2.exercise2_29)

; Exercise 2.29:
; A binary mobile consists of two branches, a left branch and a right branch.
; Each branch is a rod of a certain length, from which hangs either a weight or
;   another binary mobile.
; We can represent a binary mobile using compound data by constructing it from
;   two branches (for example, using list):
;
;   (define (make-mobile left right)
;     (list left right))
;
; A branch is constructed from a length (which must be a number) together with a
;   structure, which may be either a number (representing a simple weight) or
;   another mobile:
;
;   (define (make-branch length structure)
;     (list length structure))
;
; a. Write the corresponding selectors left-branch and right-branch,
;   which return the branches of a mobile, and branch-length and branch-structure,
;   which return the components of a branch.
; b. Using your selectors, define a procedure total-weight that returns the total
;   weight of a mobile.
; c. A mobile is said to be balanced if the torque applied by its top-left branch
;   is equal to that applied by its top-right branch (that is, if the length of the
;   left rod multiplied by the weight hanging from that rod is equal to the corresponding
;   product for the right side) and if each of the submobiles hanging off its branches
;   is balanced. Design a predicate that tests whether a binary mobile is balanced.
; d. Suppose we change the representation of mobiles so that the constructors are
;
;   (define (make-mobile left right) (cons left right))
;   (define (make-branch length structure)
;     (cons length structure))
;
;   How much do you need to change your programs to convert to the new representation?

(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

(def a-length 5)
(def a-weight 10)
(def a-rod (make-branch a-length a-weight))

(def another-length 2)
(def another-weight 25)
(def another-rod (make-branch another-length another-weight))

(def yet-another-length 10)
(def yet-another-weight 10)
(def yet-another-rod (make-branch yet-another-length yet-another-weight))

(def a-balanced-mobile (make-mobile a-rod a-rod))
(def another-balanced-mobile (make-mobile a-rod another-rod))
(def a-non-balanced-mobile (make-mobile a-rod yet-another-rod))

(def a-rod-with-a-balanced-mobile (make-branch 5 a-balanced-mobile) )
(def a-rod-with-a-non-balanced-mobile (make-branch 10 a-non-balanced-mobile) )

(def a-balanced-mobile-of-mobiles (make-mobile a-rod-with-a-balanced-mobile a-rod-with-a-balanced-mobile))
(def a-non-balanced-mobile-of-mobiles (make-mobile a-rod-with-a-balanced-mobile a-rod-with-a-non-balanced-mobile))

(def a-rod-with-a-non-balanced-mobile-of-mobiles (make-branch 10 a-non-balanced-mobile-of-mobiles))

(def a-non-balanced-mobile-of-mobiles-of-mobiles (make-mobile a-rod-with-a-balanced-mobile a-rod-with-a-non-balanced-mobile-of-mobiles))

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (first (rest mobile)))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (first (rest branch)))

; left-branch

(left-branch a-balanced-mobile)
; => (5 10)

(left-branch another-balanced-mobile)
; => (5 10)

(left-branch a-non-balanced-mobile)
; => (5 10)

(left-branch a-balanced-mobile-of-mobiles)
; => (5 ((5 10) (5 10)))

(left-branch a-non-balanced-mobile-of-mobiles)
; => (5 ((5 10) (5 10)))

(left-branch a-non-balanced-mobile-of-mobiles-of-mobiles)
; => (5 ((5 10) (5 10)))

; right-branch

(right-branch a-balanced-mobile)
; => (5 10)

(right-branch another-balanced-mobile)
; => (2 25)

(right-branch a-non-balanced-mobile)
; => (10 10)

(right-branch a-balanced-mobile-of-mobiles)
; => (5 ((5 10) (5 10)))

(right-branch a-non-balanced-mobile-of-mobiles)
; => (10 ((5 10) (10 10)))

(right-branch a-non-balanced-mobile-of-mobiles-of-mobiles)
; => (10 ((5 ((5 10) (5 10))) (10 ((5 10) (10 10)))))

; branch-length

(branch-length (left-branch a-balanced-mobile))
; => 5

(branch-length (right-branch a-balanced-mobile))
; => 5

(branch-length (left-branch another-balanced-mobile))
; => 5

(branch-length (right-branch another-balanced-mobile))
; => 2

(branch-length (left-branch a-non-balanced-mobile))
; => 5

(branch-length (right-branch a-non-balanced-mobile))
; => 10

(branch-length (left-branch a-balanced-mobile-of-mobiles))
; => 5

(branch-length (right-branch a-balanced-mobile-of-mobiles))
; => 5

(branch-length (left-branch a-non-balanced-mobile-of-mobiles))
; => 5

(branch-length (right-branch a-non-balanced-mobile-of-mobiles))
; => 10

(branch-length (left-branch a-non-balanced-mobile-of-mobiles-of-mobiles))
; => 5

(branch-length (right-branch a-non-balanced-mobile-of-mobiles-of-mobiles))
; => 10


; branch-length

(branch-structure (left-branch a-balanced-mobile))
; => 10

(branch-structure (right-branch a-balanced-mobile))
; => 10

(branch-structure (left-branch another-balanced-mobile))
; => 10

(branch-structure (right-branch another-balanced-mobile))
; => 25

(branch-structure (left-branch a-non-balanced-mobile))
; => 10

(branch-structure (right-branch a-non-balanced-mobile))
; => 10

(branch-structure (left-branch a-balanced-mobile-of-mobiles))
; => ((5 10) (5 10))

(branch-structure (right-branch a-balanced-mobile-of-mobiles))
; => ((5 10) (5 10))

(branch-structure (left-branch a-non-balanced-mobile-of-mobiles))
; => ((5 10) (5 10))

(branch-structure (right-branch a-non-balanced-mobile-of-mobiles))
; => ((5 10) (10 10))

(branch-structure (left-branch a-non-balanced-mobile-of-mobiles-of-mobiles))
; => ((5 10) (5 10))

(branch-structure (right-branch a-non-balanced-mobile-of-mobiles-of-mobiles))
; => ((5 ((5 10) (5 10))) (10 ((5 10) (10 10))))


(defn total-weight-of-a-branch [branch]
  (let [structure (branch-structure branch)]
    (cond (not (seq? structure)) structure
          :else (total-weight structure))))

(defn total-weight [mobile]
  (+ (total-weight-of-a-branch (left-branch mobile))
     (total-weight-of-a-branch (right-branch mobile))))

; total-weight

(total-weight a-balanced-mobile)
; => 20

(total-weight another-balanced-mobile)
; => 35

(total-weight a-non-balanced-mobile)
; => 20

(total-weight a-balanced-mobile-of-mobiles)
; => 40

(total-weight a-non-balanced-mobile-of-mobiles)
; => 40

(total-weight a-non-balanced-mobile-of-mobiles-of-mobiles)
; => 60

(defn is-balanced? [mobile]
  (let [left (left-branch mobile)
        right (right-branch mobile)]
    (= (* (branch-length left) (total-weight-of-a-branch left))
       (* (branch-length right) (total-weight-of-a-branch right)))))

(is-balanced? a-balanced-mobile)
; => true

(is-balanced? another-balanced-mobile)
; => true

(is-balanced? a-non-balanced-mobile)
; => false

(is-balanced? a-balanced-mobile-of-mobiles)
; => true

(is-balanced? a-non-balanced-mobile-of-mobiles)
; => false

(is-balanced? a-non-balanced-mobile-of-mobiles-of-mobiles)
; => false

; In Clojure, the following doesn't make much sense,
;   as there are no pairs, only lists.

(defn make-mobile [left right]
  (cons left (cons right nil)))

(defn make-branch [length structure]
  (cons length (cons structure nil)))
