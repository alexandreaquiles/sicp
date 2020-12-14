(ns exercises.picture
  (:require [quil.core :as q]))

;(comment
;  "little example based on quil's docs"
;  (defn draw-line []
;    (quil.core/line 0 480 640 0))
;
;  (q/defsketch example
;               :title "Quil Example"
;               :draw draw-line
;               :size [640 480]))

; based on: https://github.com/SICPDistilled/escher

(def width 600)
(def height 600)

(defn draw-line [[x1 y1] [x2 y2]]
  (q/line [x1 (- height y1)] [x2 (- height y2)]))

(def whole-window {:origin [0 0]
                   :e1     [width 0]
                   :e2     [0 height]})

(def frame1 {:origin [200 50]
             :e1     [200 100]
             :e2     [150 200]})

(def frame2 {:origin [50 50]
             :e1     [100 0]
             :e2     [0 200]})

(defn draw
  ([painter] (draw painter whole-window))
  ([painter frame]
   (q/defsketch painter
                :title "Painter"
                :draw (fn []
                        (q/background 255)
                        (painter frame))
                :size [width height])))

(defn add-vec [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn sub-vec [[x1 y1] [x2 y2]]
  [(- x1 x2) (- y1 y2)])

(defn scale-vec [[x y] s]
  [(* s x) (* s y)])

(defn frame-coord-map
  [{:keys [origin e1 e2]}]
  (fn [[x y]]
    (add-vec origin
             (add-vec (scale-vec e1 x)
                      (scale-vec e2 y)))))

(defn segment-painter [segment-list]
  (fn [frame]
    (let [m (frame-coord-map frame)]
      (doseq [[start end] segment-list]
        (draw-line (m start) (m end))))))

(def wave (segment-painter [
                              [[0.25 0.00] [0.35 0.50]]
                              [[0.35 0.50] [0.30 0.60]]
                              [[0.30 0.60] [0.15 0.40]]
                              [[0.15 0.40] [0.00 0.65]]
                              [[0.00 0.85] [0.15 0.60]]
                              [[0.15 0.60] [0.30 0.65]]
                              [[0.30 0.65] [0.40 0.65]]
                              [[0.40 0.65] [0.35 0.85]]
                              [[0.35 0.85] [0.40 1.00]]
                              [[0.60 1.00] [0.65 0.85]]
                              [[0.65 0.85] [0.60 0.65]]
                              [[0.60 0.65] [0.75 0.65]]
                              [[0.75 0.65] [1.00 0.35]]
                              [[1.00 0.15] [0.60 0.45]]
                              [[0.60 0.45] [0.75 0.00]]
                              [[0.60 0.00] [0.50 0.30]]
                              [[0.50 0.30] [0.40 0.00]]
                            ]))

(defn transform-picture [p origin e1 e2]
  (fn [frame]
    (let [map (frame-coord-map frame)
          new-origin (map origin)]
      (p {:origin new-origin
          :e1     (sub-vec (map e1) new-origin)
          :e2     (sub-vec (map e2) new-origin)}))))

(defn flip-vert [p]
  (transform-picture p [0 1] [1 1] [0 0]))

(defn flip-horiz [p]
  (transform-picture p [1 0] [0 0] [1 1]))

(defn rotate90 [p]
  (transform-picture p [1 0] [1 1] [0 0]))

(defn rotate180 [p]
  (rotate90 (rotate90 p)))

(defn beside [p1 p2]
  (let [split [0.5 0]
        left (transform-picture p1 [0 0] split [0 1])
        right (transform-picture p2 split [1 0] [0.5 1])]
    (fn [frame]
      (left frame)
      (right frame))))

(defn below [p1 p2]
  (let [split [0.0 0.5]
        bottom (transform-picture p1 [0 0] [1 0] split)
        top (transform-picture p2 split [1 0.5] [0 1])]
    (fn [frame]
      (bottom frame)
      (top frame))))
