(ns leiningen.new.tadam-lite-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "tadam-lite-template"))

(defn tadam-lite-template
  "Main"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' tadam-lite-template project.")
    (->files data
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/urls.clj" (render "urls.clj" data)]
             ["src/{{sanitized}}/views/public.clj" (render "views/public.clj" data)]
             ["src/{{sanitized}}/resources/public/css/main.css" "resources/public/css/main.css")]
             ["src/{{sanitized}}/resources/public/js/main.js" "resources/public/js/main.js"]
             ["src/{{sanitized}}/resources/public/img/rabbit.svg" "resources/public/img/rabbit.svg"]
             ["src/{{sanitized}}/resources/templates/layouts/base.html" "resources/templates/layouts/base.html"]
             ["src/{{sanitized}}/resources/templates/public/welcome.html" "resources/templates/public/welcome.html"]
             ["src/{{sanitized}}/resources/templates/public/404.html" "resources/templates/public/404.html"])))
