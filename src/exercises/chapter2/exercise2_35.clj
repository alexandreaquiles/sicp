(ns exercises.chapter2.exercise2_35)

; Exercise 2.35: Redefine count-leaves from Section 2.2.2 as an accumulation:
;
;   (define (count-leaves t)
;     (accumulate
;       ⟨ ?? ⟩ ⟨ ?? ⟩ (map ⟨ ?? ⟩ ⟨ ?? ⟩ )))

(defn count-leaves [x]
  (cond (not (seq? x)) 1
        (empty? x) 0
        :else (+ (count-leaves (first x))
                 (count-leaves (rest x)))))

(def x (cons (list 1 2) (list 3 4)))
(count-leaves x)
; => 4

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(defn count-leaves [tree]
  (accumulate + 0 (map (fn [item] (cond (not (seq? item)) 1
                                          (empty? item) 0
                                          :else (count-leaves item))) tree)))
(count-leaves x)
; => 4
