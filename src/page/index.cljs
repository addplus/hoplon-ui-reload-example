(ns ^{:hoplon/page "index.html"} page.index
  (:require
    [devtools.core]
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
    [app.state]
    [app.main]))

(defc route
  (let [initial-route (hui/hash->route js/window.location.hash)]
    (if (empty? (first initial-route)) [[]] initial-route)))

(defn route-changed [rt]
  (js/console.log "Arrived to: " rt)
  (reset! route rt))

(defonce
  _
  (do
    (js/console.log "Evaluating page.index/dom")
    (devtools.core/install! [:custom-formatters :hints :async])
    (window
      :title "Hoplon reload example"
      :route route
      :styles ["app.css"]
      :routechanged route-changed
      :scripts []
      app.main/content)))
