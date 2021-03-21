(ns exercises.chapter2.exercise2_67)

; Exercise 2.67: Define an encoding tree and a sample message:
;
;  (define sample-tree
;     (make-code-tree (make-leaf 'A 4)
;                     (make-code-tree
;                       (make-leaf 'B 2)
;                       (make-code-tree
;                         (make-leaf 'D 1)
;                         (make-leaf 'C 1)))))
; (define sample-message '(0 1 1 0 0 1 0 1 0 1 1 1 0))
;
; Use the decode procedure to decode the message, and give the result.

(def append concat)

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

(defn choose-branch [bit branch]
  (cond (= bit 0) (left-branch branch)
        (= bit 1) (right-branch branch)
        :else (throw (Exception. "bad bit: CHOOSE-BRANCH"))))

(defn decode [bits tree]
  (defn decode-1 [bits current-branch]
    (if (empty? bits)
      '()
      (let [next-branch (choose-branch (first bits) current-branch)]
        (if (leaf? next-branch)
          (cons (symbol-leaf next-branch)
                (decode-1 (rest bits) tree))
          (decode-1 (rest bits) next-branch)))))
  (decode-1 bits tree))

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree
                    (make-leaf 'B 2)
                    (make-code-tree
                      (make-leaf 'D 1)
                      (make-leaf 'C 1)))))
(def sample-message '(0 1 1 0 0 1 0 1 0 1 1 1 0))

(decode sample-message sample-tree)
; => (A D A B B C A)

;   0 => A
; 110 => D
;   0 => A
;  10 => B
;  10 => B
; 111 => C
;   0 => A