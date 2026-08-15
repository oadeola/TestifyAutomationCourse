//Challenge 1: Calculate the sum of numbers within an array
const sumArray = [2, 4, 5, 6, 10, 15];

let sum = 0;

for (let i = 0; i < sumArray.length; i++) {
  sum += sumArray[i];
}

console.log('Sum of the numbers in the array:', sum); 