(ns exercises.chapter2.exercise2_27
  (:use exercises.chapter2.text
        exercises.chapter2.exercise2_18))

; Exercise 2.27:
; Modify your reverse procedure of Exercise 2.18 to produce a deep-reverse
;   procedure that takes a list as argument and returns as its value the
;   list with its elements reversed and with all sublists deep-reversed
;   as well.
; For example,
;
;   (define x (list (list 1 2) (list 3 4)))
;
;   x
;   ((1 2) (3 4))
;
;   (reverse x)
;   ((3 4) (1 2))
;
;   (deep-reverse x)
;   ((4 3) (2 1))

(def x1 (list (list 1 2) (list 3 4)))

x1
; => ((1 2) (3 4))

(reverse x1)
; => ((3 4) (1 2))

(defn deep-reverse [items]
  (loop [items items reversed '()]
    (cond (empty? items) reversed
          (not (seq? (first items))) (recur (rest items) (cons (first items) reversed))
          :else (recur (rest items) (cons (deep-reverse (first items)) reversed)))))

(deep-reverse (list 1 2 3 4))
; => (4 3 2 1)

(deep-reverse x1)
; => ((4 3) (2 1))

(defn another-deep-reverse [items]
  (if (not (seq? items))
    items
    (reverse (map another-deep-reverse items))))

(another-deep-reverse (list 1 2 3 4))
; => (4 3 2 1)

(another-deep-reverse x1)
; => ((4 3) (2 1))

(defn yet-another-deep-reverse [items]
  (cond (not (seq? items)) items
        (empty? items) nil
        :else (append (yet-another-deep-reverse (rest items))
                      (list (yet-another-deep-reverse (first items))))))

(yet-another-deep-reverse (list 1 2 3 4))
; => (4 3 2 1)

(yet-another-deep-reverse x1)
; => ((4 3) (2 1))