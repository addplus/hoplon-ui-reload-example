# Hoplon UI reloading

It runs the `html`, `head` and `body` creation once with a `cell` as a content.
On code changes, as `boot-reload` tells the browser to reload, `app.main` namespace
will `reset!` the `content` cell with the latest DOM elements.

Run it with

```
boot dev; open http://localhost:8081
```
