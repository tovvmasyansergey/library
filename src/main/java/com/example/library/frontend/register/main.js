document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const nameInput = document.querySelector("input[placeholder='Enter your name']");
    const emailInput = document.querySelector("input[placeholder='Enter your email']");
    const passwordInput = document.querySelector("input[placeholder='Create password']");
    const confirmPasswordInput = document.querySelector("input[placeholder='Confirm password']");
    const termsCheckbox = document.querySelector(".policy input");

    function showError(input, message) {
        let errorSpan = input.closest(".input-box")?.querySelector(".error");
        if (!errorSpan) {
            errorSpan = document.createElement("span");
            errorSpan.classList.add("error");
            input.closest(".input-box")?.appendChild(errorSpan);
        }
        errorSpan.textContent = message;
    }

    function clearError(input) {
        let errorSpan = input.closest(".input-box")?.querySelector(".error");
        if (errorSpan) {
            errorSpan.textContent = "";
        }
    }

    // Очищаем ошибки при вводе в текстовые поля
    [nameInput, emailInput, passwordInput, confirmPasswordInput].forEach(input => {
        input.addEventListener("input", () => clearError(input));
    });

    // Очищаем ошибку чекбокса при изменении
    termsCheckbox.addEventListener("change", function () {
        const termsError = document.querySelector(".terms-error");
        if (termsError) termsError.textContent = "";
    });

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Останавливаем отправку формы

        let valid = true;
        document.querySelectorAll(".error").forEach(span => span.textContent = ""); // Чистим ошибки

        if (nameInput.value.trim() === "") {
            valid = false;
            showError(nameInput, "Name is required");
        }

        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(emailInput.value.trim())) {
            valid = false;
            showError(emailInput, "Enter a valid email address");
        }

        if (passwordInput.value.length < 6) {
            valid = false;
            showError(passwordInput, "Password must be at least 6 characters long");
        }

        if (passwordInput.value !== confirmPasswordInput.value) {
            valid = false;
            showError(confirmPasswordInput, "Passwords do not match");
        }

        // Проверка чекбокса
        let termsError = document.querySelector(".terms-error");
        if (!termsCheckbox.checked) {
            valid = false;
            if (!termsError) {
                // Создаём контейнер ошибки для чекбокса и размещаем его над ним
                termsError = document.createElement("span");
                termsError.classList.add("error", "terms-error");
                termsCheckbox.closest(".policy").insertBefore(termsError, termsCheckbox);
            }
            termsError.textContent = "You must accept the terms and conditions.";
        }

        if (valid) {
            alert("Registration successful!");
            form.submit();
        }
    });
});
