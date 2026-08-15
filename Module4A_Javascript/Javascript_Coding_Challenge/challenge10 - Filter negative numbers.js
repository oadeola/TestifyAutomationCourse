//Challenge 10 - Create a function that filters out negative number

function removeNegativeNumbers(array) {
    return array.filter(x => x >= 0);
}

const x = [4, -6, 12, -2, 0, 9, -1];

console.log(removeNegativeNumbers(x));