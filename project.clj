(defproject pyrrha "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [dk.ative/docjure "1.9.0"]
                 [com.novemberain/langohr "3.4.1"]
                 [cheshire "5.5.0"]]
  :main ^:skip-aot pyrrha.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
