(ns exercises.chapter1.exercise1_7)

; Exercise 1.7.
;   The good-enough? test used in computing square roots will not be very effective for finding the square roots of
;     very small numbers.
;   Also, in real computers, arithmetic operations are almost always performed with limited precision.
;   This makes our test inadequate for very large numbers.
;   Explain these statements, with examples showing how the test fails for small and large numbers.
;   An alternative strategy for implementing good-enough? is to watch how guess changes from one iteration to the
;     next and to stop when the change is a very small fraction of the guess.
;   Design a square-root procedure that uses this kind of end test.
;   Does this work better for small and large numbers?

;ANSWER:
; as numbers get smaller, the predetermined tolerance of 0.001 is not
;
; double precision floating point numbers use 64 bits.
; In the JVM, Clojure's environment:
; - the smallest positive nonzero double is 4.9E-324. (Double/MIN_VALUE)
; - the largest positive finite double is 1.7976931348623157E308 (Double/MAX_VALUE)
; - the smallest expoent is -1022 (Double/MIN_EXPONENT)
; - the largest expoent is 1023 (Double/MAX_EXPONENT)
; - the smallest positive normal is 2.2250738585072014E-308 (Double/MIN_NORMAL)

(defn square [x]
  (* x x))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(defn good-enough? [guess x]
  (println "good-enough? -> guess:" guess " , x:" x)
  (let [square-of-guess-to-radicand (abs (- (square guess) x))]
    (println "square-of-guess-to-radicand" square-of-guess-to-radicand)
    (< square-of-guess-to-radicand 0.001)))

(defn sqrt [x]
  (defn sqrt-iter [guess x]
    (if (good-enough? guess x)
            guess
            (sqrt-iter (improve guess x)
                        x)))
  (sqrt-iter 1.0 x))

(sqrt 0.1)
; => 0.316245562280389
; expected 0.316227766
; last square-of-guess-to-radicand: 1.1255662039411085E-5
;(- 0.316245562280389 0.316227766)
; => 1.779628038900416E-5 or ~0.000018
; OK

(sqrt 0.01)
; => 0.10032578510960605
; expected  0.1
; last square-of-guess-to-radicand: 6.526315785885042E-5
;(- 0.10032578510960605 0.1)
; => 3.2578510960604157E-4 or ~0.00033
;OK

(sqrt 0.007)
; => 0.08445065628268553
; expected 0.08366600265
; last square-of-guess-to-radicand: 1.3191334657629197E-4
;(- 0.08445065628268553 0.08366600265)
; => 7.846536326855252E-4 or ~0.00078
;OK

(sqrt 0.006)
; => 0.0785456883688794
; expected 0.07745966692
; last square-of-guess-to-radicand: 1.6942516134111664E-4
;(- 0.0785456883688794 0.07745966692)
; => 0.0010860214488793984
; OK

(sqrt 0.005)
; => 0.0722471690979458
; expected 0.07071067811
; last square-of-guess-to-radicand: 2.1965344266717483E-4
;(- 0.0722471690979458 0.07071067811)
;  => 0.00153649098794581
; NOT OK

(sqrt 0.004)
; => 0.06548128198973399
; expected 0.063245553
; last square-of-guess-to-radicand: 2.87798291019061E-4
;(- 0.06548128198973399 0.063245553)
; => 0.0022357289897339955
; NOT OK

(sqrt 0.003)
; => 0.05815193427238369
; expected 0.054772256
; last square-of-guess-to-radicand: 3.8164745961963243E-4
;(- 0.05815193427238369 0.054772256)
; => 0.003379678272383689
; NOT OK

(sqrt 0.002)
; => 0.050131352980478244
; expected 0.04472136
; last square-of-guess-to-radicand: 5.131525516533049E-4
;(- 0.050131352980478244 0.04472136)
; => 0.005409992980478243
; NOT OK

(sqrt 0.001)
; => 0.04124542607499115
; expected 0.031622777
; last square-of-guess-to-radicand: 7.011851721075595E-4
;(- 0.04124542607499115 0.031622777)
; => 0.00962264907499115
; NOT OK

(sqrt 0.0009)
; => 0.04030062264654547
; expected 0.03
; last square-of-guess-to-radicand: 7.241401856992538E-4
;(- 0.04030062264654547 0.03)
; => 0.010300622646545472
; NOT OK

(sqrt 0.0008)
; => 0.03934452001945049
; expected 0.02828427124
; last square-of-guess-to-radicand: 7.479912555609405E-4
;(- 0.03934452001945049 0.02828427124)
; => 0.011060248779450492
; NOT OK

(sqrt 0.0007)
; => 0.03837681529676414
; expected 0.02645751311
; last square-of-guess-to-radicand: 7.727799523219503E-4
;(- 0.03837681529676414 0.02645751311)
; => 0.011919302186764142
; NOT OK

(sqrt 0.0006)
; => 0.037397194007827136
; expected 0.02449489742
; last square-of-guess-to-radicand: 7.985501196590619E-4
;(- 0.037397194007827136 0.02449489742)
; => 0.012902296587827135
; NOT OK

(sqrt 0.0005)
; => 0.03640532954316447
; expected 0.02236067977
; last square-of-guess-to-radicand: 8.253480191464039E-4
;(- 0.03640532954316447 0.02236067977)
; => 0.014044649773164473
; NOT OK

(sqrt 0.0004)
; => 0.0354008825558513
; expected 0.02
; last square-of-guess-to-radicand: 8.532224857331766E-4
;(- 0.0354008825558513 0.02)
; => 0.015400882555851297
; NOT OK

(sqrt 0.0003)
; => 0.03438350032699598
; expected 0.01732050807
; last square-of-guess-to-radicand: 8.82225094736533E-4
;(- 0.03438350032699598 0.01732050807)
; => 0.017062992256995983
; NOT OK

(sqrt 0.0002)
; => 0.03335281609280434
; expected 0.01414213562
; last square-of-guess-to-radicand: 9.124103413204282E-4
;(- 0.03335281609280434 0.01414213562)
; => 0.01921068047280434
; NOT OK

(sqrt 0.0001)
; => 0.03230844833048122
; expected 0.01
; last square-of-guess-to-radicand: 9.438358335233747E-4
;(- 0.03230844833048122 0.01)
; => 0.02230844833048122
; NOT OK

(sqrt 0.00009)
; => 0.03220324381282134
; expected 0.00948683298
; last square-of-guess-to-radicand: 9.470489120680158E-4
;(- 0.03220324381282134 0.00948683298)
; => 0.02271641083282134
; NOT OK

(sqrt 1000000000000)
; 12 zeroes
; => 1000000.0
; expected 1000000
; OK

(sqrt 9700000000000)
; => 3114482.3004794875
; expected 3114482.30048
; OK

(sqrt 9775600000000)
; => 3126595.5926534534
; expected 3126595.59265
; OK

(sqrt 9775657571000)
; => 3126604.7992990734
; expected 3126604.79946
; OK

;(sqrt 9775657572000)
;good-enough? -> guess: 3126604.7994589917  , x: 9775657572000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126604.7994589917  , x: 9775657572000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126604.7994589917  , x: 9775657572000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126604.7994589917  , x: 9775657572000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126604.7994589917  , x: 9775657572000
;square-of-guess-to-radicand 0.001953125
;Execution error (StackOverflowError) at nrepl.bencode/eval302$fn (bencode.clj:394).
;null
;good-enough? -> guess: 3126604.7994589917  , x: 9775657572000

;(sqrt 9775000000000)
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000
;square-of-guess-to-radicand 0.001953125
;Execution error (StackOverflowError) at nrepl.bencode/eval302$fn (bencode.clj:394).
;null
;good-enough? -> guess: 3126499.6401726967  , x: 9775000000000

;(sqrt 9800000000000)
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3130495.1684997054  , x: 9800000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3130495.1684997054  , x: 9800000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3130495.1684997054  , x: 9800000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3130495.1684997054  , x: 9800000000000
;square-of-guess-to-radicand 0.001953125
;Execution error (StackOverflowError) at nrepl.bencode/eval302$fn (bencode.clj:394).
;null
;good-enough? -> guess: 3130495.1684997054  , x: 9800000000000

;(sqrt 9900000000000)
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3146426.544510455  , x: 9900000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3146426.544510455  , x: 9900000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3146426.544510455  , x: 9900000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3146426.544510455  , x: 9900000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3146426.544510455  , x: 9900000000000
;square-of-guess-to-radicand 0.001953125
;Execution error (StackOverflowError) at nrepl.bencode/eval302$fn (bencode.clj:394).
;null
;good-enough? -> guess: 3146426.544510455  , x: 9900000000000

;(sqrt 10000000000000)
; 13 zeroes
; StackOverflowError (infinite recursion)
; expected ~3162277.66017
;good-enough? -> guess: 3162277.6601683795  , x: 10000000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3162277.6601683795  , x: 10000000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3162277.6601683795  , x: 10000000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3162277.6601683795  , x: 10000000000000
;square-of-guess-to-radicand 0.001953125
;good-enough? -> guess: 3162277.6601683795  , x: 10000000000000
;square-of-guess-to-radicand 0.001953125
;Execution error (StackOverflowError) at nrepl.bencode/eval302$fn (bencode.clj:394).
;null
;good-enough? -> guess: 3162277.6601683795  , x: 10000000000000

; The algorithm gets stuck for 9775657572000 and greater numbers because (improve guess x)
; keeps on yielding 3126604.7994589917 but (good-enough? guess x) keeps returning false.

; Ideas form:
;http://community.schemewiki.org/?sicp-ex-1.7

;iterates until guess and next guess are equal,  automatically produces answer
; to limit of system precision

;This seems to yield the best results both for small and large numbers

(defn good-enough? [guess x]
  (= (improve guess x) guess))

(sqrt 0.006)
; => 0.07745966692414834
; expected 0.07745966692
; OK

(sqrt 0.005)
; => 0.07071067811865475
; expected 0.07071067811
; OK (CORRECTED!)

(sqrt 0.0001)
; => 0.01
; expected 0.01
; OK (CORRECTED!)

(sqrt 1000000000000)
; 12 zeroes
; => 1000000.0
; expected 1000000
; OK

(sqrt 9775657571000)
; => 3126604.7992990734
; expected 3126604.79946
; OK

(sqrt 9775657572000)
; => 3126604.7994589917
; expected 3126604.79946
; OK (CORRECTED!)

(sqrt 10000000000000)
; 13 zeroes
; => 3162277.6601683795
; expected ~3162277.66017
; OK (CORRECTED!)


; Modified version to look at difference between iterations
(defn good-enough? [guess x]
  (< (abs (- (improve guess x) guess))
     (* guess 0.001)))

(sqrt 0.006)
; => 0.0774671749274704
; expected 0.07745966692
; OK

(sqrt 0.005)
; => 0.07072701650643465
; expected 0.07071067811
; OK (CORRECTED!)

(sqrt 0.0001)
; => 0.010000714038711746
; expected 0.01
; OK (CORRECTED!)

(sqrt 1000000000000)
; 12 zeroes
; => 1000454.9908041331
; expected 1000000
; NOT OK :(

(sqrt 9775657571000)
; => 3126604.7992990734
; expected 3126604.79946
; OK

(sqrt 9775657572000)
; => 3126741.353312766
; expected 3126604.79946
; NOT OK :(

(sqrt 10000000000000)
; 13 zeroes
; => 3162433.547242504
; expected ~3162277.66017
; NOT OK :(

;Alternate version, which adds an "oldguess" variable to the main function.

(defn good-enough? [guess oldguess]
  (< (abs (- guess oldguess))
     (* guess 0.001)))

(defn sqrt [x]
  (defn sqrt-iter [guess oldguess x]
    (if (good-enough? guess oldguess)
      guess
      (sqrt-iter (improve guess x) guess x)))
  (sqrt-iter 1.0 2.0 x))

(sqrt 0.006)
; => 0.0774596672879806
; expected 0.07745966692
; OK

(sqrt 0.005)
; => 0.07071068000579017
; expected 0.07071067811
; OK (CORRECTED!)

(sqrt 0.0001)
; => 0.010000000025490743
; expected 0.01
; OK (CORRECTED!)

(sqrt 1000000000000)
; 12 zeroes
; => 1000000.103461242
; expected 1000000
; OK (CORRECTED!)

(sqrt 9775657571000)
; => 3126604.8022809317
; expected 3126604.79946
; OK

(sqrt 9775657572000)
; => 3126604.8024408496
; expected 3126604.79946
; OK (CORRECTED)

(sqrt 10000000000000)
; 13 zeroes
; => 3162277.6640104805
; expected ~3162277.66017
; OK (CORRECTED)
