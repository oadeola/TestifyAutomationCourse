//Task 15 - My Personal Library 3

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
