/*Concatenation learning starts here
const myFavoriteLanguage = 'Javascript'

const myAge = 28
const name = 'Adeola Oni'
const sentence = 'My name is ' + name + ' and I am ' + myAge + ' years old. My favorite programming language is ' + myFavoriteLanguage + '.'


console.log(typeof(myFavoriteLanguage))
console.log(typeof(myAge))
console.log(sentence)


const day = 'Friday'
const position = 5
const output = 'Today is ' + day + '. It is the ' + position + 'th day of the week.'

console.log(output)


Concatenation learning ends here*/

/*Boolean learning starts here
const isBritish = true
const isNight = false
const userDetails = null

console.log(typeof(isBritish))
console.log(typeof(isNight))
console.log(typeof(userDetails))

Boolean learning ends here*/

/*Arithmetic learning starts here
const addition = 5 + 10
const subtraction = 10 - 5
const multiplication = 5 * 10
const division = 10 / 5
const modulus = 40 % 6

console.log('Addition: ' + addition)
console.log('Subtraction: ' + subtraction)
console.log('Multiplication: ' + multiplication)
console.log('Division: ' + division)
console.log('Modulus: ' + modulus)

Arithmetic learning ends here*/

/*Comparison learning starts here
const comparison1 = 10 > 5
const comparison2 = 10 < 5
const comparison3 = 10 >= 5
const comparison4 = 10 <= 5
const comparison5 = 10 == 5
const comparison6 = 10 != 5
const comparison7 = 10 === (5*2)

console.log('Comparison 1 (10 > 5): ' + comparison1)
console.log('Comparison 2 (10 < 5): ' + comparison2)
console.log('Comparison 3 (10 >= 5): ' + comparison3)
console.log('Comparison 4 (10 <= 5): ' + comparison4)
console.log('Comparison 5 (10 == 5): ' + comparison5)
console.log('Comparison 6 (10 != 5): ' + comparison6)
console.log('Comparison 7 (10 === 10): ' + comparison7)

Comparison learning ends here*/

/*Logic learning starts here
const userName = 'Adeola'
const userAge = 32

const logic1 = (10 > 5) && (5 < 10)
const logic2 = (10 > 5) || (5 > 10)
const logic3 = !(10 > 5)
const logic4 = (userName === 'Adeola') && (userAge >= 18)

console.log('Logic 1 (10 > 5 && 5 < 10): ' + logic1)
console.log('Logic 2 (10 > 5 || 5 > 10): ' + logic2)
console.log('Logic 3 (!(10 > 5)): ' + logic3)
console.log('Logic 4 (userName === "Adeola" && userAge >= 18): ' + logic4)
console.log('Logic 5 (More Logic): ' + (userName === 'Vicky' || userAge < 18))

Logic learning ends here*/

/*Conditional learning starts here
const userAge = 15

if (userAge >= 18) {
    console.log('You are an adult.')
} else {
    console.log('You are not an adult.')
}

Conditional learning ends here*/


//Task 6 - If else learning starts here
// const number = 21;

// if (number % 2 === 0) {
//     console.log('The number is even.');
// } else {
//     console.log('The number is odd.');

// }

/*If elseif learning starts here
const userAge = 12;

if (userAge >= 18 && userAge <= 65) {
    console.log('You are an adult.')
} else if (userAge < 18) {
     console.log('You are not an adult.')
} else {
    console.log('You are a senior citizen.')
}

If elseif learning ends here*/

/*If elseif else learning starts here
const side1 = 3
const side2 = 2
const side3 = 4

if (side1 === side2 && side2 === side3) {
    console.log('Equilateral Triangle')
}
else if (side1 ===side2 || side2 === side3 || side1 === side3) {
    console.log('Isosceles Triangle')
}
 else {
    console.log('Scalene Triangle')
 }
 If elseif else learning ends here*/

 /*Switch learning starts here
 const day = 'Thursday'

switch (day) { 
    case 'Friday':
        console.log('TGIF!')
        break
    case 'Saturday':
        console.log('The weekend is here!')
        break
    case 'Sunday':
        console.log('Happy Sunday!')
        break
    case 'Thursday':
        console.log ('One more day to go!')
        break
    default:
        console.log('Go to work!')
}
        */

/*While learning starts here
let star = 1 //starting point
while (star <= 20) {
    if(star ===1) {
        console.log(star + ' star')
    } else {
        console.log(star + ' stars')
    }
    star = star + 1 //incrementing the value of star by 1    
} 
*/

/*For learning starts here
for (let star = 10; star <=100; star++) {
    if(star ===1){
         console.log(star + ' star')
    }
    else {
    console.log(star + ' stars')
    }
}


for (let number = 1; number <= 20; number = number + 1) {
    if (number % 2 === 1) {
        console.log(number + ' is an odd number')
    }

}
    */

/*Function learning starts here
function calculateAreaOfRectangle(length, width) {
    const area = length * width;
    console.log(area);
}

calculateAreaOfRectangle(4, 12);
*/

/*Return Function learning starts here
function converter(dollar) {
    const naira = dollar * 410;
    return naira;
}   
const dollar = 200;
const naira = converter(dollar);

console.log(dollar + ' dollars is equal to ' + naira + ' naira');



function temperatureConverter(celsius) {
    const fahrenheit = (celsius * 9/5) + 32;
    return fahrenheit;
}
const celsius = 25;
const fahrenheit = temperatureConverter(celsius);
console.log(celsius + '°C is equal to ' + fahrenheit + '°F');


function calculateAreaOfRectangle2(length, width) {
    const area = length * width;
    return area;
}

const area = calculateAreaOfRectangle2(30, 13);
console.log('Area of rectangle is: ' + area);
*/

/*Array learning starts here
const myFriends = ['Sharon', 'Benjamin', 'Emily', 'Bookie', 'Frank'];

console.log(myFriends);

//Task12 - Pick your best Friend.
const bestFriend = myFriends[0];
console.log('My best friend is ' + bestFriend);
*/

/*Push and Pop learning starts here
/*const notEmptyArray = ['First Element', 'Second Element', 77, false];

for (let i = 0; i < notEmptyArray.length; i++) {
   console.log(notEmptyArray[i]);
}

emptyArray.push("First Element", "Second Element", 77, false);

notEmptyArray.push("Pushed Element");

notEmptyArray.pop();
notEmptyArray.pop();
notEmptyArray.pop();

console.log(notEmptyArray);
*/

/*Shift and Unshift learning starts here
const notEmptyArray = ['First Element', 'Second Element', 77, false];

//notEmptyArray.unshift("Extra Element");
notEmptyArray.shift();

console.log(notEmptyArray);
*/

/*Slice and Splice learning starts here
const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

months.slice(2, 6) // returns ["March", "April", "May", "June"]

console.log(months.slice(7, 12)) // returns ["August", "September", "October", "November", "December"]


const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

//months.splice(0,3) // removes ["January", "February", "March"]

//console.log(months, months.splice(2,6, 'New Insert here'))

console.log(months.splice(4,2))
console.log(months)
*/

/*IndexOf and LastIndexOf learning starts here
const months = ["January", "July", "February", "March", "April", "May", "June", "July", "August", "September", "July", "October", "February", "November", "December"];

console.log(months.indexOf("February")); // returns 2

console.log(months.lastIndexOf("July")); // returns 10

*/

/*Object learning starts here
const myObject = {
    name: "car",
    color: "red",
    price: 10000,
   // isNew: true,
   // features: ["air conditioning", "power steering", "cruise control"]
}

const door = {
    isOpen: false,
    material: "wood",
    height: 9,
}

console.log(myObject.color)
console.log(door.material)
console.log(door["isOpen"])

//Task13 - My Personal Library
const books = {
    title: "The Temptation",
    description: "A novel about love and betrayal",
    numberOfPages: 255,
    author: "Colleen Hoover",
    reading: true,
}
console.log(books.title)
console.log(books.description)
console.log(books.numberOfPages)
console.log(books['author'])
console.log(books['reading'])

*/

/*Object with methods learning starts here
const person = {
    name: "Janet",
    age: 30,
    siblings: ["John", "Jane", "Jack"],
    addSibling: function(name){ 
        person.siblings.push(name);
    },
    eyeColor: "blue",
    hairColor: ["brown", "blonde"],
    addHairColor: function(){
        person.hairColor.push("ginger");
}
}
person.addHairColor();
person.addSibling("Jake");
console.log(person)

*/

/*Task 14 - My Personal Library 2
const books = {
    title: "The Temptation",
    description: "A novel about love and betrayal",
    numberOfPages: 255,
    author: "Colleen Hoover",
    readingStatus: false,
    toggleReadingStatus: function(){
       if (books.readingStatus === true) {
           books.readingStatus = false;
       } else {
           books.readingStatus = true;
       }
    }
}
books.toggleReadingStatus();

console.log(books.title)
console.log(books.description)
console.log(books.numberOfPages)
console.log(books['author'])
console.log(books['readingStatus'])

*/

/*Complex Data Structure learning starts here
const person = {
    name: "Janet",
    age: 30,
    siblings: [
        {name: "John", age: 35},
        {name: "Jane", age: 32},
        {name: "Jack", age: 28}
    ],
    addSibling: function(name){ 
        person.siblings.push(name);
    },
    eyeColor: "blue",
    hairColor: ["brown", "blonde"],
    addHairColor: function(){
        person.hairColor.push("ginger");
}
}
person.addHairColor();
person.addSibling({name: "Jake", age: 25});
console.log(person)

*/

/*Task 15 - My Personal Library 3

const books = [
    {
        title: "The Temptation",
        description: "A novel about love and betrayal",
        numberOfPages: 255,
        author: "Colleen Hoover",
        readingStatus: false,
   },
     {
        title: "Child of the Morning",
        description: "A novel about love and betrayal",
        numberOfPages: 255,
        author: "Adetomi Adeyemi",
        readingStatus: true,
   },
     {
        title: "The Miracle Morning",
        description: "A novel about peace and happiness",
        numberOfPages: 485,
        author: "Sue Knight",
        readingStatus: false,
   },
     {
        title: "Mermaid's chair",
        description: "A novel about faith and love",
        numberOfPages: 1025,
        author: "Ben Harris",
        readingStatus: true,
   },
     {
        title: "Middle Daughter",
        description: "Grief, love, and family",
        numberOfPages: 322,
        author: "Chika Unigwe",
        readingStatus: false,
   },

]
for (let i = 0; i < books.length; i++) {
    if (books[i].readingStatus === true) {
        console.log(books[i].title + " (" + books[i].description + ")" + " by " + books[i].author + " with " + books[i].numberOfPages + " pages")
    }
}
*/

/*JSON learning starts here
const myObject = {
    name: 'vehicle',
    type: 'car',
    color: 'pink'
}

const json =JSON.stringify(myObject)
console.log(JSON.parse(json))
*/