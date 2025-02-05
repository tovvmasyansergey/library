// 1
function hello() {
    console.log(12)
}
hello()

// 2
let index = 0
let midAge = 0
function welcome(name = "", surname = "",age = 0) {
    index++
    midAge += age
    document.write(`
    <p>
    ${index}
    name: ${name}
    surname: ${surname}
</p>
    `)
}

let userName = prompt("input name")
welcome(userName, "a",12)
welcome("d", "d",23)
welcome("v", "v",34)
document.write(`${midAge / index}`)

// 3
function getAge(dOB = 0){
    let year = 2025
    return year - dOB
}
let age = getAge
document.write(`Your age ${getAge(2002)}`)
console.log(age())


// 4
function getStrong(text) {
    return `<strong>${text}</strong>`
}
function printProduct(name, count, price, index) {
    let totalPrice = count * price
    document.write(`
    <p>${price}, name:${getStrong(name)}, count:${getStrong(count)}, price:${getStrong(price)}</p>
    `)
    return totalPrice
}
let index = 0
let total = 0
total += printProduct(prompt("input name"),Number(prompt("input count")),Number(prompt("input price")),++index)
total += printProduct(prompt("input name"),Number(prompt("input count")),Number(prompt("input price")),++index)
total += printProduct(prompt("input name"),Number(prompt("input count")),Number(prompt("input price")),++index)
console.log(total)

