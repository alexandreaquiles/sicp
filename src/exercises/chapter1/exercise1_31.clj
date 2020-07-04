(ns exercises.chapter1.exercise1_31)

;Exercise 1.31.
;a. The 'sum' procedure is only the simplest of a vast number of similar abstractions that can be captured as higher-order
;   procedures. 51
;   Write an analogous procedure called 'product' that returns the product of the values of a function at points over a
;   given range.
;   Show how to define 'factorial' in terms of 'product'.
;   Also use product to compute approximations to  using the formula 52
;       π/4 = 2/3 . 4/3 . 4/5 . 6/5 . 6/7 . 8/7 ...
;b.  If your 'product' procedure generates a recursive process, write one that generates an iterative process.
;   If it generates an iterative process, write one that generates a recursive process.

;Footnotes
; 51 The intent of exercises 1.31-1.33 is to demonstrate the expressive power that is attained by using an
;     appropriate abstraction to consolidate many seemingly disparate operations.
;   However, though accumulation and filtering are elegant ideas, our hands are somewhat tied in using them at this
;     point since we do not yet have data structures to provide suitable means of combination for these abstractions.
;   We will return to these ideas in section 2.2.3 when we show how to use sequences as interfaces for combining filters
;     and accumulators to build even more powerful abstractions.
;   We will see there how these methods really come into their own as a powerful and elegant approach to designing programs.
; 52 This formula was discovered by the seventeenth-century English mathematician John Wallis.

(defn product [term a next b]
  (loop [a a result 1]
    (if (> a b)
      result
      (recur (next a) (* (term a) result)))))

(defn factorial [a b]
  (product identity a inc b))

(assert (= 120 (factorial 1 5)))

(defn product-recursive [term a next b]
  (if (> a b)
    1
    (* (term a) (product-recursive term (next a) next b))))

(defn factorial-recursive [a b]
  (product identity a inc b))

(assert (= 120 (factorial-recursive 1 5)))

; π/4 = 2/3 . 4/3 . 4/5 . 6/5 . 6/7 . 8/7 ...

;(2.4.4.6)/(3.3.5.5) = (2/3).(4/3).(4/5).(6/5) = ((2.4)/(3.3)).((4.6)/(5.5)) = 0.853333...

;2/3 . 4/3 => a=1 => (a+1)/(a+2) . (a+3)/(a+2)
;4/5 . 6/5 => a=3 => (a+1)/(a+2) . (a+3)/(a+2)

;2/3 * 4/3 = 8/9
;2*4 / 3² = 8/9

(defn wallis-formula [a b]
  (defn square [x]
    (* x x))
  (defn wallis-term [a]
    (println "" (+ a 1) "/" (+ a 2) "." (+ a 3) "/" (+ a 2) "" )
    (* (/ (+ a 1) (+ a 2)) (/ (+ a 3) (+ a 2))))
  (defn wallis-next [a]
    (+ a 2))
  (product wallis-term a wallis-next b))

;π/4 ~= 0.78539816339


(wallis-formula 1 3)
; => 64/75 ~= 0.853333...

(double (wallis-formula 1 1000))
; => 28030534552594104595528154325627001563044865773649785167911199629535309116024665865641100740149078855430809683569029852867361302755445686802390902250162431283967282506179755859543158971738292204938702878433468321479886587804294034166825445810660730949927977675332616896714997462310378820687540093254475752281291237545033814844330928908182143286361029419050049491383453571904787141659248362246403291825489892332860453644292426723243174355432906067270724959448752910291737740134197275185566884086091450868012799214776859259850777934431653932840643501924469036971564648638389348344229847109908410925056
;    / 35671780323872920093242256783724106391676214109362710495132931284619475266689995380379811182045141364247561826266920190587972223494084903483413747749338448707907574393238490904347769439784453553384286897392391073141680922984739974813075471523603928496527005845584613716302329857944878352466698223226459074276384903517742872437285433944823851851199042547526266382952760358997958275725612429767978536100571422632952660793536581461685963671708597531788272085174929572480528901968218697958745133550442527677193277882433164511185066763653386836422659898505865811132514272323372965570755522938956968102525
; => 0.7857901763830665
