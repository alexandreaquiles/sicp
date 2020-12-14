(ns exercises.chapter2.exercise2_50
  (:use exercises.picture)
  (:use exercises.chapter2.exercise2_23)
  (:use exercises.chapter2.exercise2_46)
  (:use exercises.chapter2.exercise2_47)
  (:use exercises.chapter2.exercise2_48)
  (:use exercises.chapter2.exercise2_49))

; Exercise 2.50:
; Define the transformation 'flip-horiz', which flips painters horizontally, and transformations that rotate
;   painters counterclockwise by 180 degrees and 270 degrees.

(defn transform-painter [painter origin corner1 corner2]
  (fn [frame]
    (let [m (frame-coordinates-map frame)
          new-origin (m origin)]
      (painter (make-frame new-origin
                           (sub-vect (m corner1) new-origin)
                           (sub-vect (m corner2) new-origin))))))

(defn flip-vertically [painter]
  (transform-painter painter
                     (make-vect 0.0 1.0)
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 0.0)))

(defn shrink-to-upper-right [painter]
  (transform-painter painter
                     (make-vect 0.5 0.5)
                     (make-vect 1.0 0.5)
                     (make-vect 0.5 1.0)))

(defn rotates-90 [painter]
  (transform-painter painter
                     (make-vect 1.0 0.0)
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 0.0)))

(defn squash-inwards [painter]
  (transform-painter painter
                     (make-vect 0.0 0.0)
                     (make-vect 0.65 0.35)
                     (make-vect 0.35 0.65)))

(defn besides [painter1 painter2]
  (let [split-point (make-vect 0.5 0.0)
        paint-left (transform-painter painter1
                                      (make-vect 0.0 0.0)
                                      split-point
                                      (make-vect 0.0 1.0))
        paint-right (transform-painter painter2
                                       split-point
                                       (make-vect 1.0 0.0)
                                       (make-vect 0.5 1.0))]
    (fn [frame]
      (paint-left frame)
      (paint-right frame))))

(defn flip-horizontally [painter]
  (transform-painter painter
                     (make-vect 1.0 0.0)
                     (make-vect 0.0 0.0)
                     (make-vect 1.0 1.0)))

(draw george a-whole-window-frame)
(draw (flip-horizontally george) a-whole-window-frame)

(defn rotates-180 [painter]
  (transform-painter painter
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 1.0)
                     (make-vect 1.0 0.0)))

(draw (rotates-90 george) a-whole-window-frame)
(draw (rotates-180 george) a-whole-window-frame)

(defn rotates-180 [painter]
  (rotates-90 (rotates-90 painter)))

(draw (rotates-180 george) a-whole-window-frame)

(defn rotates-270 [painter]
  (rotates-90 (rotates-90 (rotates-90 painter))))

(draw (rotates-270 george) a-whole-window-frame)

(defn rotates-270 [painter]
  (rotates-90 (rotates-180 painter)))

(draw (rotates-270 george) a-whole-window-frame)
