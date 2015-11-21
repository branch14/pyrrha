(ns pyrrha.core
  (:gen-class)
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]
            [dk.ative.docjure.spreadsheet :as dj]
            [cheshire.core     :as cs]))

(def ^{:const true}
  default-exchange-name "")

(defn write-excel [data]
  (dj/save-workbook! "my.xlsx" (dj/create-workbook "plan" data)))

(defn message-handler
  [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s (%s), delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8")
                   (String. payload "UTF-8")
                   delivery-tag
                   content-type
                   type))
  (write-excel (cs/parse-string (String. payload "UTF-8"))))
    ;;(lb/publish ch default-exchange-name qname "Hello!" {:content-type "text/plain" :type "greetings.hi"})

(defn start-consumer
  "Starts a consumer in a separate thread"
  [ch queue-name]
  (lc/subscribe ch queue-name message-handler {:auto-ack true}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [conn  (rmq/connect)
        ch    (lch/open conn)
        qname "pyrrha"]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lq/declare ch qname {:exclusive false :auto-delete true})
    (lc/blocking-subscribe ch qname message-handler {:auto-ack true})
    (rmq/close ch)
    (rmq/close conn)))
