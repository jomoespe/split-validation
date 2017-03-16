(function() {
    const validator          = new WebSocket("ws://localhost:8080/contact-form/validation");
    const sendContact        = document.querySelector("#sendContact");
    const validationProgress = document.querySelector("#validationPprogress");
    const name               = document.querySelector("#name");
    const email              = document.querySelector("#email");
    const phone              = document.querySelector("#phone");
    const bankAccount        = document.querySelector("#bankAccount");
    const deliveryAddress    = document.querySelector("#deliveryAddress");

    validateForm = function(event) {
        validator.send(createCustomerFormJson());
        return true;
    };
    
    createCustomerFormJson = function() {
        return JSON.stringify({
            name: name.value,
            email: email.value,
            phone: phone.value,
            bankAccount: bankAccount.value,
            deliveryAddress: deliveryAddress.value
        });
    };
    
    validationResponseHandler = function(event) {
        var validation = JSON.parse(event.data);
        console.log(validation);
        validationProgress.value = validation.score * 100;
        sendContact.disabled = !validation.valid;
    };

    name.onkeyup            = validateForm;
    email.onkeyup           = validateForm;
    phone.onkeyup           = validateForm;
    bankAccount.onkeyup     = validateForm;
    deliveryAddress.onkeyup = validateForm;
    validator.onmessage     = validationResponseHandler;
    validator.onerror       = function(error) {
        alert("Error de conexión con el servicio de validación. " + error);
    };
})();
