function getInput(placeholder,type,className){
    let input = document.createElement("input");
    input.placeholder = placeholder
    input.type = type
    input.classList.add(className)
    return input
}

let box = document.createElement("div");
box.classList.add("box")

box.append(getInput("Name","text","text-field"))

document.body.append(box)