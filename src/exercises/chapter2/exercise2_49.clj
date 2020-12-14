(ns exercises.chapter2.exercise2_49
  (:use exercises.chapter2.exercise2_23)
  (:use exercises.chapter2.exercise2_46)
  (:use exercises.chapter2.exercise2_47)
  (:use exercises.chapter2.exercise2_48)
  (:use exercises.picture))

; Exercise 2.49: Use 'segments->painter' to define the following primitive painters:
; a. The painter that draws the outline of the designated frame.
; b. The painter that draws an “X” by connecting opposite corners of the frame.
; c. The painter that draws a diamond shape by connecting the midpoints of the sides
;     of the frame.
; d. The wave painter.

(defn draw-line-from-vectors [start-vector end-vector]
  (let [x1 (xcor-vect start-vector)
        y1 (ycor-vect start-vector)
        x2 (xcor-vect end-vector)
        y2 (ycor-vect end-vector)]
    (draw-line [x1 y1] [x2 y2])))

(defn frame-coordinates-map [frame]
  (fn [v]
    (add-vect
      (origin-frame frame)
      (add-vect (scale-vect (xcor-vect v) (edge1-frame frame))
                (scale-vect (ycor-vect v) (edge2-frame frame))))))

(defn segments->painter [segment-list]
  (fn [frame]
    (for-each (fn [segment] (draw-line-from-vectors
                              ((frame-coordinates-map frame) (start-segment segment))
                              ((frame-coordinates-map frame) (end-segment segment))))
              segment-list)))

(def a-whole-window-frame (make-frame (make-vect 0 0)
                                      (make-vect width 0)
                                      (make-vect 0 height)))

(def a-small-frame (make-frame (make-vect 50 50)
                               (make-vect 100 0)
                               (make-vect 0 200)))

(def a-twisted-frame (make-frame (make-vect 200 50)
                                 (make-vect 200 100)
                                 (make-vect 150 200)))

(def bottom-left (make-vect 0 0))
(def top-left (make-vect 0 1))
(def bottom-right (make-vect 1 0))
(def top-right (make-vect 1 1))

(def middle-left   (make-vect 0.0 0.5))
(def middle-top    (make-vect 0.5 1.0))
(def middle-right  (make-vect 1.0 0.5))
(def middle-bottom (make-vect 0.5 0.0))

; a
(def outline
  (segments->painter (list (make-segment bottom-left top-left)
                           (make-segment top-left top-right)
                           (make-segment top-right bottom-right)
                           (make-segment bottom-right bottom-left))))

;(draw outline a-whole-window-frame)
;(draw outline a-small-frame)
;(draw outline a-twisted-frame)

; b
(def opposite-corners
  (segments->painter (list (make-segment bottom-left top-right)
                           (make-segment top-left bottom-right))))

;(draw opposite-corners a-whole-window-frame)
;(draw opposite-corners a-small-frame)
;(draw opposite-corners a-twisted-frame)

; c
(def diamond-shape
  (segments->painter (list (make-segment middle-left middle-top)
                           (make-segment middle-top middle-right)
                           (make-segment middle-right middle-bottom)
                           (make-segment middle-bottom middle-left))))

;(draw diamond-shape a-whole-window-frame)
;(draw diamond-shape a-small-frame)
;(draw diamond-shape a-twisted-frame)

;d
(def george
  (segments->painter (list  (make-segment (make-vect 0.25 0.00) (make-vect 0.35 0.50))
                            (make-segment (make-vect 0.35 0.50) (make-vect 0.30 0.60))
                            (make-segment (make-vect 0.30 0.60) (make-vect 0.15 0.40))
                            (make-segment (make-vect 0.15 0.40) (make-vect 0.00 0.65))
                            (make-segment (make-vect 0.00 0.85) (make-vect 0.15 0.60))
                            (make-segment (make-vect 0.15 0.60) (make-vect 0.30 0.65))
                            (make-segment (make-vect 0.30 0.65) (make-vect 0.40 0.65))
                            (make-segment (make-vect 0.40 0.65) (make-vect 0.35 0.85))
                            (make-segment (make-vect 0.35 0.85) (make-vect 0.40 1.00))
                            (make-segment (make-vect 0.60 1.00) (make-vect 0.65 0.85))
                            (make-segment (make-vect 0.65 0.85) (make-vect 0.60 0.65))
                            (make-segment (make-vect 0.60 0.65) (make-vect 0.75 0.65))
                            (make-segment (make-vect 0.75 0.65) (make-vect 1.00 0.35))
                            (make-segment (make-vect 1.00 0.15) (make-vect 0.60 0.45))
                            (make-segment (make-vect 0.60 0.45) (make-vect 0.75 0.00))
                            (make-segment (make-vect 0.60 0.00) (make-vect 0.50 0.30))
                            (make-segment (make-vect 0.50 0.30) (make-vect 0.40 0.00)))))

;(draw george a-whole-window-frame)
;(draw george a-small-frame)
;(draw george a-twisted-frame)

(def diagonal
  (segments->painter (list (make-segment bottom-left top-right))))

;(draw diagonal a-whole-window-frame)
;(draw diagonal a-small-frame)
;(draw diagonal a-twisted-frame)