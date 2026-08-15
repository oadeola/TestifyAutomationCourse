//Challenge 7 - Sort an array of numbers in descending order
function sortNumberDesc(array){
    return array.sort((a, b) => b - a);

}
const array = [15, 9, 2, 1, 25]

console.log("Sorted [15, 9, 2, 1, 25] in descending order is: " + sortNumberDesc(array));
