(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!! As of now..")
  1
  )

(defn parse-args [args]
  (into {} (map (fn [[k v]] [(keyword (.replace k "--" "")) v])
                                (partition 2 args))))


(println "Cleanliness is next to Godliness")
(defn train
  []
  (println "Choo Choo!!"))

(+ 1 (* 2 3) 4)
(str "It was the panda " "in the library " "with a dust buster")


(if false
  "abracadabra"
  "Hocus Pocus")

(do (println "done!") "hi" (println "moving on.."))

(def failed-protagonist-names
  ["Larry Potter"
   "Doreen the Explorer"
   "The Incredible Bulk"])

(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOMED!")))

(def failed-movie-titles
  ["Gone with the Moving Air" "Swellfellas"])


((get [+ -] 1) 1 2 3 4)
(map inc (vec (range 12 100 3)))

(defn x-chop
  "Describe the kind of chop you are inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))


(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Maris" "The Incredible Bulk")

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are just in case you need to cry over them. "
                (clojure.string/join "," unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat:" lat))
  (println (str "Treasure lng:" lng)))

(announce-treasure-location {:lat 28.22, :lng 45.23, :x 23})
(def d {:lat 28.22, :lng 45.23, :x 23})

(map
  #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

(= (#(* % 3) 8) ((fn [x] (* x 3)) 8))

(defn inc-maker
  "Custom incrementer"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))
(inc3 300)

(def asym-hobbit-body-parts [
                             {:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part
  [part]
  (let [old-name (:name part)
        new-name (clojure.string/replace old-name #"^left-" "right-")]
    {:name new-name :size (:size part)}))


(defn symmetrize-body-parts
  [body-parts]
  "Expects a sequence of :name and :size maps"
  (loop [asym-parts body-parts
         final-parts []]
    (if (empty? asym-parts)
      final-parts
      (let [[head & tail] asym-parts
            final-parts (conj final-parts head)]
        (if (needs-matching-part? head)
          (let [matching-part (make-matching-part head)]
            (recur tail (conj final-parts matching-part)))
          (recur tail final-parts))))))

(defn symmetrize-body-parts-reduce
  [body-parts]
  (reduce
    (fn [final-body-parts part]
      (let [final-parts (conj final-body-parts part)]
        (if (needs-matching-part? part)
          (let [matching-part (make-matching-part part)]
            (conj final-parts matching-part))
          final-parts)))
    []
    body-parts))

(defn sums-of-multiples-3-and-5
  [num]
  (let [nums (-> (range 1 num) vec)
        multiples (filter #(or (= 0 (mod % 3)) (= 0 (mod % 5))) nums)]
    (apply + multiples)))

(defn hit
  [asym-body-parts]
  (let [sym-body-parts (symmetrize-body-parts-reduce asym-body-parts)
        size-sum (reduce + 0 (map :size sym-body-parts))
        target (inc (rand size-sum))]
    (loop [[part & rest] sym-body-parts
           accum-size (:size part)]
      (println (str "size-sum: " size-sum " target: " target " accum-size: " accum-size " cur-size: " (:size part)))
      (if (> accum-size target)
        part
        (if (= accum-size (:size part))
          (recur rest accum-size)
          (recur rest (+ accum-size (:size part))))))))

(defn titleize
  [topic]
  (str topic " for the Brave and the True"))

(map titleize ["Hamsters", "Ragnarok"])
(map titleize '("Empathy" "Decorating"))
(map titleize #{"Elbows" "Soap Carving"})

(defn label-key-val
  [[key val]]
  (str "key: " key " val: " val))

(map label-key-val {:name "Edward" :occupation "perennial high-school scholar"})

(into {} (map (fn [[key val]]
       [key (inc val)]) {:max 20 :min 10}))

(def dalmation-list
  ["Pongo", "Perdita", "Puppy 1", "Puppy 2"])

(let [[pongo & dalmations] dalmation-list]
  [pongo dalmations])

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "done")
    (recur (inc iteration))))

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human :critter critter})
(map unify-diet-data human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (str "sum: " (sum numbers) " count: " (count numbers) "  average: " (avg numbers)))

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {} {:max 10 :min 5})
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key (inc val))
            new-map
            ))
        {} {:human 4.1 :critter 3.4})


(take 30 (range 1000))
(drop 2 (range 10))

(def food-journal
  [
   {:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 1 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 1 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 1 :human 3.7 :critter 3.6}
   ])

(def pre-march-folks
  (take-while #(< (:month %) 3) food-journal))

(some #(> (:human %) 5) food-journal)

(sort [3 2 1])

(defn is-prime?
  [num]
  (let [sqrt (inc (Math/sqrt num))]
    (not (some #(= 0 (mod num %)) (range 2 sqrt)))))

(defn fac
  [num]
  (let [factors (filter #(= 0 (mod num %)) (range 2 (inc (Math/sqrt num))))
        prime-factors (filter #(is-prime? %) factors)]
    (-> prime-factors sort last)))

