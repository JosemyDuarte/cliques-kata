# Cliques Kata

Kata for Discovery Testing using Github and Twitter clients

## Requirements

Given​ ​a​ ​list​ ​of​ ​developer​ ​handles,​ ​find​ ​all​ ​the​ ​groups​ ​of​ ​“fully​ ​connected”​ ​developers.​ ​Two​ ​given developers​ ​are​ ​considered​ ​connected​ ​if:
- They​ ​follow​ ​each​ ​other​ ​in​ ​twitter
- They​ ​have​ ​a​ ​Github​ ​organization​ ​in​ ​common

Assume​ ​that​ ​people​ ​having​ ​the​ ​same​ ​handle​ ​both​ ​in​ ​Twitter​ ​and​ ​Github​ ​are​ ​actually​ ​the​ ​same person.

By​ ​“fully​ ​connected”​ ​we​ ​mean​ ​that​ ​they​ ​form​ ​a​ ​​clique​,​ ​or​ ​in​ ​other​ ​words,​ ​all​ ​the​ ​developers​ ​in​ ​a clique​ ​are​ ​connected​ ​to​ ​each​ ​other.​ ​All​ ​subgraphs​ ​of​ ​a​ ​clique​ ​are​ ​also​ ​cliques​ ​but​ ​we​ ​are interested​ ​just​ ​in​ ​the​ ​​maximal​​ ​ones​ ​(the​ ​ones​ ​that​ ​are​ ​not​ ​a​ ​subset​ ​of​ ​other​ ​clique)​ ​that​ ​have​ a​​ t least​ ​two​ ​developers​.

Both​ ​input​ ​and​ ​output​ ​will​ ​be​ ​textual.​ ​The​ ​input​ ​will​ ​be​ ​plain​ ​text​ ​file​ ​with​ ​user​ ​handles,​ ​e.g.:
```
user1
user2
...

usern
```
The​ ​output​ ​will​ ​have​ ​one​ ​clique​ ​per​ ​line​ ​with​ ​whitespace​ ​separated​ ​handles​ ​in​ ​alphabetical order.​ ​For​ ​example:
 ```
user1​ ​user3
user2​ ​user4​ ​user5
```

## Are you really going to try it?

This kata is evil! The core idea is to make you build something that is hard to check in real world (Wish you luck finding a Clique on twitter/github). This makes isolate those third party services to make it easier to test/mock.

If you are lucky enough to find a list of users that forms a clique, and want to try this, you'll need to read:

- [Twitter4J Auth Configuration](http://twitter4j.org/en/configuration.html)
- [Github Auth Configuration](http://github-api.kohsuke.org/)