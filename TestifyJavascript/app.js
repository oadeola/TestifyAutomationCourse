
//Task 14 - My Personal Library 2

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

