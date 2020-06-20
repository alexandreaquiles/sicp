(ns exercises.chapter1.exercise1_22)

;Exercise 1.22:
;
; Most Lisp implementations include a prim-
;itive called runtime that returns an integer that specifies
;the amount of time the system has been running (mea-
;sured, for example, in microseconds). The following timed-
;prime-test procedure, when called with an integer n, prints
;n and checks to see if n is prime. If n is prime, the procedure
;prints three asterisks followed by the amount of time used
;in performing the test.

; (define (timed-prime-test n)
;   (newline)
;   (display n)
;   (start-prime-test n (runtime)))
; (define (start-prime-test n start-time)
;   (if (prime? n)
;       (report-prime (- (runtime) start-time))))
; (define (report-prime elapsed-time)
;   (display " *** ")
;   (display elapsed-time))
;
; Using this procedure, write a procedure search-for-primes
;that checks the primality of consecutive odd integers in a
;specified range. Use your procedure to find the three small-
;est primes larger than 1000; larger than 10,000; larger than
;100,000; larger than 1,000,000. Note the time needed to test
;each prime. Since the testing algorithm has order of growth
;of Θ √n), you should expect that testing for primes around
;10,000 should take about √10 times as long as testing for
;primes around 1000. Do your timing data bear this out?
;How well do the data for 100,000 and 1,000,000 support the
;Θ(√n) prediction? Is your result compatible with the notion
;that programs on your machine run in time proportional to
;the number of steps required for the computation?

(defn square [x] (* x x))

(defn divides? [a b] (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (recur n (inc test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn report-prime [elapsed-time]
  (print " *** ")
  (println (/ elapsed-time 1000.0) "μs")
  true)
(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (System/nanoTime) start-time))))
(defn timed-prime-test [n]
  (newline)
  (print n)
  (start-prime-test n (System/nanoTime)))

;(timed-prime-test 1999)
;=> 1999 *** 541.765 μs

(defn search-for-primes [min]
  (loop [counter 1 min min]
    (if (<= counter 3)
      (let [odd-min (if (odd? min) min (inc min))
            was-prime (timed-prime-test odd-min)
            new-counter (if was-prime (inc counter) counter)]
          (recur new-counter (inc odd-min))))))

(search-for-primes 10000)
;10001
;10003
;10005
;10007 *** 32.638 μs
;
;10009 *** 37.965 μs
;
;10011
;10013
;10015
;10017
;10019
;10021
;10023
;10025
;10027
;10029
;10031
;10033
;10035
;10037 *** 23.473 μs

(search-for-primes 100000)
;100001
;100003 *** 90.359 μs
;
;100005
;100007
;100009
;100011
;100013
;100015
;100017
;100019 *** 125.558 μs
;
;100021
;100023
;100025
;100027
;100029
;100031
;100033
;100035
;100037
;100039
;100041
;100043 *** 186.961 μs

(search-for-primes 1000000)
;1000001
;1000003 *** 358.816 μs
;
;1000005
;1000007
;1000009
;1000011
;1000013
;1000015
;1000017
;1000019
;1000021
;1000023
;1000025
;1000027
;1000029
;1000031
;1000033 *** 386.534 μs
;
;1000035
;1000037 *** 367.456 μs

; t₁₀₀₀₀   = (/ (+ 32.638 37.965 23.473) 3)    = 31.358666666666668
; t₁₀₀₀₀₀  = (/ (+ 90.359 125.558 186.961) 3)  = 134.2926666666667
; t₁₀₀₀₀₀₀ = (/ (+ 358.816 386.534 367.456) 3) = 370.93533333333335

;√10 = 3.16227766017

; t₁₀₀₀₀₀/t₁₀₀₀₀   = (/ 134.2926666666667 31.358666666666668) = 4.282473744632     ~= √18
; t₁₀₀₀₀₀₀/t₁₀₀₀₀₀ = (/ 370.93533333333335 134.2926666666667) = 2.7621413926796694 ~= √8

; Newer computers are faster. So the effects are more visible with larger numbers.
; When I tested it with 10^13 and 10^14, I got StackOverflow error.
; For it to work, I had to change 'find-divisor' to have Tail Call Optimization by using 'recur'.

(search-for-primes 10000000000000)
;10000000000001
;10000000000003
;10000000000005
;10000000000007
;10000000000009
;10000000000011
;10000000000013
;10000000000015
;10000000000017
;10000000000019
;10000000000021
;10000000000023
;10000000000025
;10000000000027
;10000000000029
;10000000000031
;10000000000033
;10000000000035
;10000000000037 *** 158768.093 μs
;
;10000000000039
;10000000000041
;10000000000043
;10000000000045
;10000000000047
;10000000000049
;10000000000051 *** 142721.97 μs
;
;10000000000053
;10000000000055
;10000000000057
;10000000000059
;10000000000061
;10000000000063
;10000000000065
;10000000000067
;10000000000069
;10000000000071
;10000000000073
;10000000000075
;10000000000077
;10000000000079
;10000000000081
;10000000000083
;10000000000085
;10000000000087
;10000000000089
;10000000000091
;10000000000093
;10000000000095
;10000000000097
;10000000000099 *** 128581.861 μs

(search-for-primes 100000000000000)

;100000000000001
;100000000000003
;100000000000005
;100000000000007
;100000000000009
;100000000000011
;100000000000013
;100000000000015
;100000000000017
;100000000000019
;100000000000021
;100000000000023
;100000000000025
;100000000000027
;100000000000029
;100000000000031 *** 477575.226 μs
;
;100000000000033
;100000000000035
;100000000000037
;100000000000039
;100000000000041
;100000000000043
;100000000000045
;100000000000047
;100000000000049
;100000000000051
;100000000000053
;100000000000055
;100000000000057
;100000000000059
;100000000000061
;100000000000063
;100000000000065
;100000000000067 *** 401933.321 μs
;
;100000000000069
;100000000000071
;100000000000073
;100000000000075
;100000000000077
;100000000000079
;100000000000081
;100000000000083
;100000000000085
;100000000000087
;100000000000089
;100000000000091
;100000000000093
;100000000000095
;100000000000097 *** 424886.357 μs

; t10¹³ (avg) = 143357.308
; t10¹⁴ (avg) = 434798.30133333337

; t10¹⁴ / t10¹³ = 3.032969210982487 (very close to √10 = 3.16227766017)
