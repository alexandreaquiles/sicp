(ns exercises.chapter2.exercise2_47)

; Exercise 2.47:
; Here are two possible constructors for frames:
;
;   (define (make-frame origin edge1 edge2)
;       (list origin edge1 edge2))
;
;   (define (make-frame origin edge1 edge2)
;       (cons origin (cons edge1 edge2)))
;
; For each constructor supply the appropriate selectors to produce an implementation
;   for frames.

(defn make-frame [origin edge1 edge2] (list origin edge1 edge2))
(defn origin-frame [frame] (first frame))
(defn edge1-frame [frame] (first (rest frame)))
(defn edge2-frame [frame] (first (rest (rest frame))))

; or

; In Clojure, there are no pairs, so no way to do (cons origin (cons edge1 edge2))
;(defn make-frame [origin edge1 edge2] (cons origin (cons edge1 (cons edge2 nil))))
;(defn origin-frame [frame] (first frame))
;(defn edge1-frame [frame] (first (rest frame)))
;(defn edge2-frame [frame] (first (rest (rest frame))))
