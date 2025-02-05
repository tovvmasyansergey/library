let index = 0
function getAge(year) {
    let result = 2025 - year
    if (result > 6) {
        return `${result} (student)`
    }
    return result
}
function getStudentListUl() {
    return document.createElement("ul")
}
function getStudentLi(index, name, year, point) {
    let li = document.createElement("li")
    let span = document.createElement("span")
    let remove = document.createElement("button")
    let check = document.createElement("button")

    span.textContent = `${index} ${name} ${getAge(year)} ${point}`
    remove.textContent = "remove"
    check.textContent = "check"

    remove.onclick = function () {
        li.remove()
    }
    let flag = false
    check.onclick = function () {
        if (point > 40 && flag === false) {
            span.textContent = `${span.textContent} (good)`
            flag = true
        }
    }
    li.append(span, check, remove)
    return li
}

function getAddStudentBlock() {
    let box = document.createElement("div")
    let name = document.createElement("input")
    name.placeholder = "input name"
    name.type = "text"
    let year = document.createElement("input")
    year.placeholder = "input year"
    year.type = "number"
    let point = document.createElement("input")
    point.placeholder = "input point"
    point.type = "number"

    let add = document.createElement("button");
    add.textContent = "add"

    add.onclick = function () {
        let nameValue = name.value
        let yearValue = Number(year.value)
        let pointValue = Number(point.value)

        let studentLi = getStudentLi(++index, nameValue, yearValue, pointValue);
        studentListUl.append(studentLi)
        name.value = ""
        year.value = ""
        point.value = ""
    }
    box.append(name, year, point, add)
    return box
}

let addStudentBlock = getAddStudentBlock();
let studentListUl = getStudentListUl();

document.body.append(addStudentBlock, studentListUl)

