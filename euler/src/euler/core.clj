(ns euler.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn three-digit-palindrome
  []
  (-> (for [ a (range 100 1000)
            b (range 100 1000)
            :let [regular-product (str (* a b))
                  reverse-product (clojure.string/reverse regular-product)]
            :when (= regular-product reverse-product)]
        (* a b )) sort last))


(defn get-divisors
  [num]
  (conj (filter #(= 0 (mod num %)) (range 1 (/ num 2)))) num)

(defn triangle-numbers
  ([] (triangle-numbers 1 0))
  ([cnt sum] (cons (+ cnt sum) (lazy-seq (triangle-numbers (inc cnt) (+ cnt sum))))))

(defn foo [a b n]
  (loop [a a
         b b
         n n]
    (if (= 0 n)
      b
      (recur b (+ a (b * b)) (dec n) )
      )))

(defn simp
  []
  (loop [a 0]
    (println (str "a: " a))
    (if (> a 10)
      (println (str "done:" a))
      (recur (inc a)))))

(defn permutations
  [prefix items]
  (if (= 1 (count items))
    (str prefix items)

(defn permutations
  [items]
  (for [n (range (count items))
    :let [vec-items (vec items)
          cur-item (get vec-items n)
          rest-items (into (subvec vec-items 0 n) (subvec vec-items (inc n)))
          ]]
    ;; x + permutations(rest)
    (println (str "cur-item: " cur-item "rest-items: " rest-items))
    ))

;(let [in (slurp *in*)]
;  (println in)))

;; (foo 0 1 5)
;; (println (str (solve 0 1 5)))
