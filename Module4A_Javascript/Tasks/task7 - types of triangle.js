//Task 7 - Types of Triangle
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