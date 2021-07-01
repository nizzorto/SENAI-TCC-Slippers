package br.com.slippers.api.config.validation;

public class ExceptionDTO {
    
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        if(message.contains("400 Bad Request")) {
            this.message = "CEP inválido!";
        } else if(message.contains("failed to resolve")) {
            this.message = "Falha na comunicação com a API do CEP. Verifique sua conexão com a internet.";
        } else if(message.contains("Not-null")) {
            this.message = "Usuário já existe";
        } else {
            this.message = message;
        }
    }

}
