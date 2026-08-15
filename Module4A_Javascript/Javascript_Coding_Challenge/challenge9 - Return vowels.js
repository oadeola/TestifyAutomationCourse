//Challenge 9 - Return the number of vowels in a string
function countVowels(string) {
    let count = 0;
    const vowels = "aeiou";

    for (let i = 0; i < string.length; i++) {
        if (vowels.includes(string[i].toLowerCase())) {
            count++;
        }
    }

    return count;
}

console.log("Count : " + countVowels("Adeola is a Javascript programmer"));