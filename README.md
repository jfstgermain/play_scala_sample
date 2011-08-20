Intent and purposes
===================
Squeezing as much juice out of the http request 
-----------------------------------------------
1. A request is sent to the server
2. The request is received by the server
3. Artifacts are built and retreived from the persistence layer (DB, file system)
4. Business logic is executed
5. More artifacts might be built and retreived from the persistence layer
6. A view is built and assembled based on the on the previous steps
7. The response is sent by the server
8. The response is received by the web client

Depending on network capabilities and physical location, the client waits for steps to execute in a sequential order. For its request to be received by the server, for the business logic to execute, for the view to get built and for the response to return.

In some cases, when it could apply, it could be interesting to skip most of this and parallelize some of the steps while waiting for a response page. When a request hits the server, a cached view, empty of all data would be returned immediately before any processing has started. In parallel, the whole pertaining business logic could be executed with its result stored in a cache. The partial view would then perform an ajax call in order to retrieve the data stored in the cache for that request ID. (see this as the "Future" actor concept applied to http requests). FB does something similar \<paste link here\> Sofea / SOUI style.

Advantages
----------
* More responsive feel for the web client even if only a partial result is returned at first

Possible problems
-----------------
1. Large amount of data stored in the cache
2. Need a cache expiry mechanism - implies possible timeout issues.
3. When getting the data in the second round, if still being processed, need to wait for it to complete (use continuations? websockets?).
4. Need to test Play's concurrent transaction support in actors the way it's being used in my current implementation - [LongProcessActor.executeLongProcess()](https://github.com/jfstgermain/play_scala_sample/blob/master/app/actors/LongProcessActor.scala)

Solution
--------
* Cache views
* Use actors (Akka) to spawn business logic processes
* Cache response data identified by the session id (what about multiple clicks?  Have one bucket by session only that is being overwritten to prevent dead response data?)
* Automate data loading (ajax code should automatically be included in the response page for routes pointing to an AsyncController)


Main project hurdles
--------------------
* Outdated Akka plugin for Play
* Integration of Redis, MongoDB & Websockets with Play and or Scala not as easy & intuitive as for Node.js (a real breeze)
* Newest Play version (1.2) with async / promises features makes this implementation less relevant