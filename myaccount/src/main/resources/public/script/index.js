(function() {
    const validator          = new WebSocket("ws://localhost:8080/customerform/validation");
    const sendContact        = document.querySelector("#sendContact");
    const validationProgress = document.querySelector("#validationPprogress");
    const name               = document.querySelector("#name");
    const email              = document.querySelector("#email");
    const phone              = document.querySelector("#phone");
    const bankAccount        = document.querySelector("#bankAccount");
    const deliveryAddress    = document.querySelector("#deliveryAddress");

    validateForm = function(event) {
        if (validator.readyState===WebSocket.OPEN) {
            validator.send(createCustomerFormJson());
        } else {
            console.log("Validation service is closed");
        }
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
        validationProgress.value = validation.score * 100;
        sendContact.disabled = !validation.valid;
    };

    name.onkeyup            = validateForm;
    email.onkeyup           = validateForm;
    phone.onkeyup           = validateForm;
    bankAccount.onkeyup     = validateForm;
    deliveryAddress.onkeyup = validateForm;
    validator.onmessage     = validationResponseHandler;
    validator.onclose       = function(error) {
        console.log("Validation service has closed the connection: " + error);
    };
})();
