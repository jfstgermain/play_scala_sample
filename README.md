Intent and purposes===================Squeezing as much juice out of the http request -----------------------------------------------1. A request is sent to the server2. The request is received by the server3. Artifacts are built and retreived from the persistence layer4. Business logic is executed involving possibly IO (DB, file system...)5. More artifacts might be built and retreived from the persistence layer6. A view is built based on the on the previous steps7. The response is sent by the server8. The response is received by the web client