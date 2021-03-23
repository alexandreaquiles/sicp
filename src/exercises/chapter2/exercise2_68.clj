(ns exercises.chapter2.exercise2_68)

; Exercise 2.68:
; The encode procedure takes as arguments a message and a tree and produces the list of bits
;   that gives the encoded message.
;
; (define (encode message tree)
;   (if (null? message)
;     '()
;     (append (encode-symbol (car message) tree)
;             (encode (cdr message) tree))))
;
; encode-symbol is a procedure, which you must write, that returns the list of bits that
;   encodes a given symbol according to a given tree.
; You should design encode-symbol so that it signals an error if the symbol is not in the
;   tree at all.
; Test your procedure by encoding the result you obtained in Exercise 2.67 with the sample
;   tree and seeing whether it is the same as the original sample message.

(def append concat)

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        :else (element-of-set? x (rest set))))

(defn make-leaf [symbol weight] (list 'leaf symbol weight))
(defn leaf? [object] (= (first object) 'leaf))
(defn symbol-leaf [x] (second x))
(defn weight-leaf [x] (nth x 2))

(defn left-branch [tree] (first tree))
(defn right-branch [tree] (second tree))
(defn symbols [tree]
  (if (leaf? tree)
    (list (symbol-leaf tree))
    (nth tree 2)))
(defn weight [tree]
  (if (leaf? tree)
    (weight-leaf tree)
    (nth tree 3)))

(defn make-code-tree [left right]
  (list left
        right
        (append (symbols left) (symbols right))
        (+ (weight left) (weight right))))

(defn encode-symbol [symbol tree]
  (loop [current-branch tree bits '()]
    (if (leaf? current-branch)
      bits
      (let [left (left-branch current-branch)
            right (right-branch current-branch)]
        (cond (element-of-set? symbol (symbols left)) (recur left (append bits '(0)))
              (element-of-set? symbol (symbols right)) (recur right (append bits '(1)))
              :else (throw (Exception. (str "bad symbol: ENCODE-SYMBOL " symbol))))))))

(defn encode [message tree]
  (if (empty? message)
    '()
    (append (encode-symbol (first message) tree)
            (encode (rest message) tree))))

(def a-tree
  (make-code-tree (make-leaf 'A 1)
                  (make-leaf 'B 1)))
; => ((leaf A 1) (leaf B 1) (A B) 2)

(assert (=
          '(0)
           (encode-symbol 'A a-tree)))

(assert (=
          '(1)
          (encode-symbol 'B a-tree)))

(assert (try
          (encode-symbol 'C a-tree)
            false
          (catch Exception _
            true)))
; => Execution error ... bad symbol: ENCODE-SYMBOL C

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree
                    (make-leaf 'B 2)
                    (make-code-tree
                      (make-leaf 'D 1)
                      (make-leaf 'C 1)))))

(assert (=
          '(0)
          (encode-symbol 'A sample-tree)))

(assert (=
          '(1 0)
          (encode-symbol 'B sample-tree)))

(assert (=
          '(1 1 1)
          (encode-symbol 'C sample-tree)))

(assert (=
          '(1 1 0)
          (encode-symbol 'D sample-tree)))

(encode '(A D A B B C A) sample-tree)
; => (0 1 1 0 0 1 0 1 0 1 1 1 0)
