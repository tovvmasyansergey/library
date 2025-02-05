let divElement = document.createElement("div")

let br = document.createElement("br")

let nameInp = document.createElement("input")
nameInp.placeholder = "Input name"
nameInp.type = "text"
let surnameInp = document.createElement("input")
surnameInp.placeholder = "Input surname"
surnameInp.type = "text"
let addBtn = document.createElement("button")
addBtn.textContent = "Add user"
let index = 0
let listUsers = document.createElement("ul")

function createUser(index, name, surname) {
    let user = document.createElement("li")
    user.textContent = `${index} ${name} ${surname}`
    return user
}

addBtn.onclick = function() {
    let name = nameInp.value
    let surname = surnameInp.value
    let user = createUser(++index, name, surname);
    nameInp.value = ""
    surnameInp.value = ""
    listUsers.append(user)
}

let nameDiv = document.createElement("div");
nameDiv.append(nameInp);

let surnameDiv = document.createElement("div");
surnameDiv.append(surnameInp);

let btnDiv = document.createElement("div");
btnDiv.append(addBtn);

divElement.append(nameDiv, surnameDiv, btnDiv);
document.body.append(divElement);
document.body.append(listUsers);
// divElement.append(nameInp)
// divElement.append(br)
// divElement.append(surnameInp)
// divElement.append(br)
// divElement.append(addBtn)
// document.body.append(divElement)
// document.body.append(listUsers)

