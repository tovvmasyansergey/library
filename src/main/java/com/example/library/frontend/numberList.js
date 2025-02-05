
let counter = 0
let title  =document.createElement("h1")
title.textContent = "Hello"

let desc  =document.createElement("p")
desc.textContent = "Hello gh"

let list = document.createElement("ol")
let listItem1 = document.createElement("li")
listItem1.textContent = `number ${++counter}`
let listItem2 = document.createElement("li")
listItem2.textContent = `number ${++counter}`
list.append(listItem1,listItem2)

let action = document.createElement("button")
action.textContent = "Button"
action.onclick = function (){
    let value = inp.value;
    let listItem = document.createElement("li")
    listItem.textContent = `number ${++counter} ${value}`
    list.append(listItem)
    listItem2.textContent = `number ${counter}`

    inp.value = ""
}
//field text
let inp = document.createElement("input")
inp.placeholder = "Input name"
inp.type = "text"

document.body.append(title,desc)
document.body.prepend(list)
document.body.append(action,inp)
