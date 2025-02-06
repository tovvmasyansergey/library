// let, const
// const brand = 'BMW'
// const year = 2021
// document.write(`Car ${brand} - Year ${year} `)

// object
// let car = {
//     brand: 'BMW',
//     color: 'Black',
//     year: 2023,
// }
// document.write(car['brand'])

// == , ===
// if (2 == 2){
//     console.log("2==2")
// }
// if (2 === 2){
//     console.log("2===2")
// }
// if ("2" == 2){
//     console.log('"2"==2')
// }
// if ("2" === 2){
//     console.log('"2" === 2')
// }

// switch case
// const brand = 'Nissan'
// switch (brand) {
//     case 'BMW':
//         console.log('BMW')
//         break;
//     case 'Audi':
//         console.log('audi')
//         break;
//     case 'Nissan':
//         console.log('Nissan')
//         break
//     default:
//         console.log("not exist")
// }

//ternary
// const brand = 'BMW'
// const car = brand === 'Jeep' ? 'Jeep' : 'car'
// console.log(car)

//function
//1
// function cal(){
//     let x = "hello"
//     console.log(x)
//     return x
// }
// cal()
// console.log(cal())
//
//2
// const cal = function (num1,num2){
//     return num1 + num2;
// }
// console.log(cal(1,2))
//3
// const cal = (num1 , num2 ) => num1 + num2
// console.log(cal(1,2))

//array
// let ml = ["a","v","s"]
// console.log(Array.isArray(ml));
// console.log(ml[0]) //a
// ml.push("b")
// ml.push("b")
// ml.unshift("m")
// console.log(ml.pop());
// console.log(ml.includes("a"));
// console.log(ml)
// console.log((ml.map(function (index){return `welcome ${index}`})))

//object
// const w = "w"
// const car = {
//     a:"a",
//     b:"b",
//     c:"c",
//     w,
//     engine:{
//         type:"V8"
//     },
//     drive()  {
//         return `${this.a} - a`
//     }
// }
// car.e = "e"
// car.a = "q"
// console.log(car.engine)
// console.log(car)
// const  drive = car.drive()
// console.log(drive)
// console.log(car.drive())

//for each
// ml = [1,2,3,4,5]
// for (const arrayElement of ml) {
//     console.log(arrayElement)
// }
// function ingr(a){
//     console.log(a)
// }
// ml.forEach(ingr)
// console.log(ml[ml.length-1])

//dom












