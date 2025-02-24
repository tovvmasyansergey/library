// number,string,boolean
// let num = 2
// let course = 'assd'
// let isBoolean = true
// function render(){
//     console.log(num,course,isBoolean)
// }
// render()

//array
// let numb:number[] = [1,2,3];
// numb.forEach(n => n.toFixed())

//tuples
// let user:[number,string] = [1,'sd']
// console.log(user[0],user[1])
// const cords:[number,number][] = [
//     [1,2],[1,3],[3,4]
// ]
// console.log(cords[0][1]);

//Enums
// enum size {a=12,x="df",f="df"}
// console.log(size.a);
// console.log(size.x);
// var x:size = size.a
// if (x == 12){
//     console.log(x)
// }
//
// enum Direction {
//     up = "up",
//     down = "down",
//     left = "left",
//     right = "right"
// }
// var direction:Direction = Direction.left
// console.log(direction);

//function,overload
//1
// function calculator(income:number) : void{
//     console.log(34,income)
// }
// calculator(65)
// //2
// function calculator1(num : number) : string{
//     return `number ${num}`
// }
// console.log(calculator1(34))
// //3
// function calculator2(num?: number) : string{
//     return `number ${num}`
// }
// console.log(calculator2())
//4
// function calculator3(num: number = 34) : string{
//     return `number ${num}`
// }
// console.log(calculator3())
// function greet(name:string | null | undefined){
//     if (name){
//         console.log(name.toUpperCase())
//     }else {
//         console.log('Hola')
//     }
// }
// greet(null)
// greet(undefined)
// greet("rt")
//5
// function ml(a: number, v: number): number | string {
//     if (a > 34) {
//         return "hello"
//     }else {
//         return v
//     }
// }
// let ml1 = ml(1,2);
// console.log(ml1)
//6
// function makeName(name:string,surname:string,mid?:number):string{
//     if (mid){
//         return name + " " + surname + " " + mid
//     }else {
//         return name + " " + surname
//     }
// }
// console.log(makeName("as", "er"));
// 7
// function makeName(name:string,surname:string,mid:string = "hello"):string{
//     if (mid){
//         return name + " " + surname + " " + mid
//     }
//     return name + " " + surname
// }
// console.log(makeName("as", "er"));
// function mull(x: number, y: number): number {
//     return x * y;
// }
//
// function div(x: number, y: number): number {
//     return x / y;
// }
//8
// function sum(str:string,...numbers:number[]){
//     console.log(str)
// }
//9 overload
// function getItem(name: string): string;
// function getItem(name: string[]): string;
// function getItem(name: unknown): string {
//     if (typeof name === "string") {
//         return "string"
//     } else if (Array.isArray(name)) {
//         return "string"
//     }
//         return "unknown"
// }
// console.log(getItem("oi"));

//object
//
// let employee: {
//     readonly  id: number,
//     name: string,
//     method: (surname: string) => void
// } = {
//     id: 1,
//     name: "32",
//     method:(surname:string) => {
//         console.log(surname)}}
//
// type Employee = {
//     id:number,
//     name:string
// }

// |, &
// function fg(weight: number | string) {
//     if (typeof weight === "number") {
//         console.log(weight);
//     } else {
//         let number = parseInt(weight);
//         console.log(number);
//     }
// }
// fg(23)
// fg("34")
// // &
// type D = {
//     size: () => void
// }
// type O = {
//     weight: () => void
// }
// type L = D & O
// let l:L = {
//     size: () => {},
//     weight: () => {}
// }
//
// interface B{
//     name:string
// }
// interface A{
//     email:string
// }
// type C = A & B
// let c: C = {
//     name:"asd",
//     email:"asd"
// }
//
// interface B{
//     name:string
// }
// interface A{
//     email:string
// }
// type C = B | A
// function check(c:C){
//     if ("name" in c){
//         console.log(c.name)
//     }else {
//         console.log(c.email)
//     }
// }

//Literal
// type Q = 'w' | 'r'
// let  q: Q = 'r'
// type Metric = 'cm' | 'inch'
// let m:Metric = 'cm'
//
// let direction:"asd"|"asdf"|"wer";
// direction = "asd"
// direction = "asdf"
// direction = "wer"
//
// let response: 200 | 404 | 201;
// response = 200
//
// let is : false | true
// is = true

// ?, !, function
// type Customer = {
//     birthday: Date
// }
// function get(id: number): Customer | null{
//     return id === 0 ? null : {birthday:new Date()}
// }
// let cust = get(9)
// console.log(cust?.birthday?.getFullYear())
//
// const arr = [{name:"a"},{name:"c"},{a:"A"}]
// const el= arr.pop()?.name
// console.log(el)

// let, unknown , cast
// let c = 34;
// let x :number;
// x = 2;
// let y = "hello";
// let z = -Infinity;
// let v = 34.56;
// let m:Number = 34;//create object
// let df = 'hello';
// let cv = `${v} hello`;
// let bn:boolean = true;
// // null undefined
// let result = null;
// result = 34
// let resul = undefined;
// resul = 45
// let ml : undefined | number;
//
// let x: unknown = "er";
// if (typeof x == "number"){
//     console.log(x + 90)
// }else if (typeof x == "string"){
//     console.log(x.length)
// }
//
// let x:unknown = 1
// console.log((x as number) + 2)

//interface
//1
// interface Person {
//     name:string,
//     surname:string
//     age:number
//     year?:number //необязательно
//     hello: () => void;
// }
// let person:Person = {
//     name:"a",
//     surname:"a",
//     age:23,
//     hello:function (){
//         console.log(this.name)}}
// console.log(person.age);
// console.log(person.surname);
// console.log(person.year);
// console.log(person.hello);
//
//2
// interface Person {
//     name:string,
//     surname:string
//     age:number
//     year?:number //необязательно
//     hello: () => void;
// }
// interface Employee extends Person{
//     employeeId:number
// }
// const worker:Employee = {
//     name:"a",
//     surname:"s",
//     age:23,
//     hello:function (){
//         console.log("hello")},
//     employeeId:34
// }

//class
// class Person{
//     protected name:string
//     constructor(name:string) {
//         this.name = name
//         this.greet()
//     }
//     greet(){
//         console.log(this.name)
//     }
// }
// class Employee extends Person{
//     callMe(){
//         console.log(this.name)
//     }
// }
// let p1:Person = new Person("mn")
// let p2:Employee = new Employee("er");
// p1.greet()
// p2.greet()

//abstract class
// abstract class Animal {
//     abstract makeSound(sound:number):void;
//     move() {
//         console.log("Move")
//         this.makeSound(45)
//     }
// }
// class Tiger extends Animal{
//     makeSound(sound: number): void {
//         console.log(sound)
//     }
// }
// class Dog extends Animal{
//     makeSound(sound: number): void {
//         console.log(sound)
//     }
// }
// let tiger:Tiger = new Tiger();
// let dog:Dog = new Dog();
// dog.makeSound(35)
// tiger.makeSound(34)
// tiger.move()
// dog.move()

//class impl interface
// interface Animal{
//     speak():void;
// }
// class Dog implements Animal{
//     private name:string;
//     constructor(name:string) {
//         this.name = name
//     }
//     speak(): void {
//         console.log(this.name)
//     }
//     test():string{
//         return this.name
//     }
// }
// const dog:Dog = new Dog("jk")
// const animal:Animal = dog
// dog.speak()
// console.log(dog.test());
// const animals = [dog,dog]

//static
// class Dog {
//     static a: number = 0;
//     name: string;
//
//     constructor(name: string) {
//         Dog.a++;
//         this.name = name
//     }
//     static deg(){
//         this.a--;
//     }
// }
// let dod:Dog = new Dog("er")
// console.log(Dog.a);
// Dog.deg()
// console.log(Dog.a);

//generic
// class Manager<T>{
//     private items:T [] = [];
//     addItem(item: T){
//         this.items.push(item);
//     }
//     getItem(index:number):T{
//         return this.items[index];
//     }
//     removeItem(index: number){
//         this.items.splice(index,1);
//     }
//     getAllItems():T[]{
//         return this.items;
//     }
// }
// let data = new Manager<string>()
//
// function getValue<K,V>(key:K,value:V,value1: V):V{
//     if (key){
//         return value;
//     }
//     return value1
// }
//
// console.log(getValue<number, string>(1, "hello", "goodbye"));

//instanceOf
// type C = string | number
// function check(numb:C):C{
//     if (typeof numb === "string"){
//         return numb + "asdf"
//     }else {
//         return numb + 67
//     }
// }
//
// type  Log = Warning | Info | Success;
//
// interface Warning {
//     type: "warning"
//     msg: string
// }
//
// interface Info {
//     type: "info"
//     msg: string
// }
//
// interface Success {
//     type: "success"
//     msg: string
// }
// function check(log: Log) {
//     switch (log.type) {
//         case "warning":
//             console.log(log.msg);
//             break;
//         case "warning":
//             console.log(log.msg);
//             break;
//         case "warning":
//             console.log(log.msg);
//             break;
//     }
// }











