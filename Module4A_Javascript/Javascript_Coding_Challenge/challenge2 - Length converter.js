//Challenge 2: Create a length converter function that converts feet to inches, yards, and miles
function lengthConverter(feet) {
  const inches = feet * 12;
  const yards = feet / 3;
  const miles = feet / 5280;
  return { inches, yards, miles };
}
console.log(lengthConverter(24)); 
