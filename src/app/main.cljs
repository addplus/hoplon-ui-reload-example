(ns app.main
  (:require
    [hoplon.core :as h
     :refer-macros [defelem text with-init! with-page-load
                    with-timeout with-interval
                    when-tpl if-tpl case-tpl cond-tpl for-tpl loop-tpl]]
    [javelin.core
     :refer [cell? input? cell formula lens cell-map
             set-cell! alts! destroy-cell!]
     :refer-macros [cell= defc defc= set-cell!= dosync cell-doseq
                    with-let cell-let]]
    [hoplon.ui :as hui
     :refer [elem text-field submit image]
     :refer-macros [comp form window bind-in!]]
    [hoplon.ui.attrs :refer [+ - * / c r pt em px d]]
    [hoplon.ui.elems :refer [out mid in]]
    [app.state]))

(defn on-jsload []
  (js/console.log "Running on-jsload callback..."))

(js/console.log "Rendering app.main/content")
(defonce content (cell (elem "Loading...")))

(reset!
  content
  (elem :sh (r 1 1) :ah :mid :av :mid
        (elem :sh (r 1 1) :f (em 2) :b 1 :bc (c 0xff0000)
              "Index page")
        (elem (cell= (str "Counter: " app.state/counter)))))
