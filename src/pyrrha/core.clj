(ns pyrrha.core
  (:gen-class)
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.exchange  :as lx]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]
            [dk.ative.docjure.spreadsheet :as dj]
            [cheshire.core     :as cs]))

(def ^{:const true}
  xslx-mime "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

(def ^{:const true}
  default-exchange-name "")

(def ^{:const true}
  exchange-name "pyrrha")

(def ^{:const true}
  qname "pyrrha")

(defn compile-xslx
  [data]
  (dj/save-workbook! "my.xlsx" (dj/create-workbook "plan" data))
  (let [content (slurp "my.xlsx")]
    (clojure.java.io/delete-file "my.xlsx")
    content))

(defn message-handler
  [ch {:keys [content-type type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, content type: %s, type: %s"
                   (String. payload "UTF-8")
                   content-type
                   type))
  (lb/publish ch
              exchange-name
              "done"
              (compile-xslx (cs/parse-string (String. payload "UTF-8")))
              {:content-type xslx-mime}))

(defn -main
  [& args]
  (let [conn  (rmq/connect)
        ch    (lch/open conn)]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lx/declare ch exchange-name "topic")
    (lq/declare ch qname {:exclusive false :auto-delete true})
    (lc/blocking-subscribe ch qname message-handler {:auto-ack true})
    (rmq/close ch)
    (rmq/close conn)))
