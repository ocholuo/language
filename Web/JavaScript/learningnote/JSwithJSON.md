
# JSON

[toc]

---

## JSON to web

loaded this object into a JavaScript program, 

parsed in a variable called `superHeroes`

access the data inside using
superHeroes.homeTown
superHeroes['active']
superHeroes['members'][1]['powers'][2]

## Converting between objects and text

```js
const header = document.querySelector('header');
const section = document.querySelector('section');

let requestURL = 'https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json';
let request = new XMLHttpRequest();
request.open('GET', requestURL);

request.responseType = 'text'; // now we're getting a string!
request.send();

request.onload = function() {
  const superHeroesText = request.response;   // get the string from the response
  const superHeroes = JSON.parse(superHeroesText); // convert it to an object
  populateHeader(superHeroes);
  showHeroes(superHeroes);
}

let myJSON = { "name": "Chris", "age": "38" };
myJSON
let myString = JSON.stringify(myJSON);
myString
```
