(ns exercises.core)

(println
  (clojure.string/replace (str
                            "> The acts of the mind, wherein it exerts its power over simple ideas, are chiefly these three: \n"
                            "> 1. Combining several simple ideas into one compound one, and thus all complex ideas are made. \n"
                            "> 2. The second is bringing two ideas, whether simple or complex, together, and setting them by one another so as to take a view of them at once, without uniting them into one, by which it gets all its ideas of relations. \n"
                            "> 3. The third is separating them from all other ideas that accompany them in their real existence: this is called abstraction, and thus all its general ideas are made. \n"
                            "> \n"
                            "> J0hN L0Ck3, An Essay Concerning Developer Understanding \n")
                          #"mind|idea"
                          {"mind" "C0mPuT3r"
                           "idea" "Pr0Gr4M"}))
